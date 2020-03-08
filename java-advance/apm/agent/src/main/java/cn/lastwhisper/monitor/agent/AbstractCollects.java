package cn.lastwhisper.monitor.agent;


import cn.lastwhisper.monitor.agent.collects.ErrorLog;
import cn.lastwhisper.monitor.agent.common.NetUtils;
import cn.lastwhisper.monitor.agent.json.JsonWriter;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tommy on 17/7/14.
 */
public abstract class AbstractCollects {
    // 统一线程池
    private final static ExecutorService threadService;

    private static final String localIp;
    private static long rejectedCount = 0;

    static {

        //采样率:自动、手动调节
        /**
         *  核心线程:50
         *  最大线程:200
         *  最大队例:1000
         */
        threadService = new ThreadPoolExecutor(20,
                100,
                20000L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1000),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        rejectedCount++;
                        System.err.println("upload Task  rejected from " +
                                executor.toString() + " rejectedCount:" + rejectedCount);
                    }
                });
        localIp = NetUtils.getLocalHost();
    }

    // 统计信息
    @NotProguard
    public static class Statistics {
        public String logType;
        private Long begin;
        private Long end;
        private Long userTime;
        private String errorMsg;
        private String errorType;
        private Long createTime;
        private String keyId;
        private String ip = localIp;


        public Statistics() {

        }

        public Statistics(Statistics copy) {
            this.begin = copy.begin;
            this.createTime = copy.createTime;
            this.end = copy.end;
            this.errorMsg = copy.errorMsg;
            this.errorType = copy.errorType;
            this.keyId = copy.keyId;
            this.ip = copy.ip;
            this.logType = copy.logType;
            this.userTime = copy.userTime;
        }


        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            if (begin != null) {
                sb.append("\"begin\":").append(begin);
            }
            if (end != null) {
                sb.append(", \"end\":").append(end);
            }
            if (errorMsg != null) {
                sb.append(", \"errorMsg\":\"").append(errorMsg).append('\"');
            }
            if (errorType != null) {
                sb.append(", \"errorType\":\"").append(errorType).append('\"');
            }
            if (createTime != null) {
                sb.append(", \"createTime\":").append(createTime);
            }
            if (keyId != null) {
                sb.append(", \"key\":\"").append(keyId).append('\"');
            }
            if (sb.substring(1, 1).equals(",")) {
                sb.delete(1, 2);
            }
            sb.append('}');
            return sb.toString();
        }

        public String toJsonString() {
            final StringBuilder sb = new StringBuilder("{");
            if (begin != null) {
                sb.append("\"begin\":").append(begin);
            }
            if (end != null) {
                sb.append(", \"end\":").append(end);
            }
            if (errorMsg != null) {
                sb.append(", \"errorMsg\":\"").append(errorMsg).append('\"');
            }
            if (errorType != null) {
                sb.append(", \"errorType\":\"").append(errorType).append('\"');
            }
            if (createTime != null) {
                sb.append(", \"createTime\":").append(createTime);
            }
            if (sb.substring(1, 2).equals(",")) {
                sb.delete(1, 2);
            }
            sb.append('}');
            return sb.toString();
        }

    }

    @NotProguard
    public Statistics begin(String className, String method) {
        Statistics s = new Statistics();
        s.begin = System.currentTimeMillis();
        s.createTime = System.currentTimeMillis();
        return s;
    }

    @NotProguard
    public void end(Statistics stat) {
        stat.end = System.currentTimeMillis();
        stat.userTime = stat.end - stat.begin;
        sendStatistics(stat);
        //  System.out.println("代理结束:" + stat.toString());
    }

    @NotProguard
    public void error(Statistics stat, Throwable throwable) {
        if (stat != null) {
            stat.errorMsg = throwable.getMessage();
            stat.errorType = throwable.getClass().getName();
            if (throwable instanceof InvocationTargetException) {
                stat.errorType = ((InvocationTargetException) throwable).getTargetException().getClass().getName();
                stat.errorMsg = ((InvocationTargetException) throwable).getTargetException().getMessage();
            }
        }
        if (throwable != null) {
            sendErrorStackByHttp("", throwable);
        }
    }

    /**
     * 发送统计信息
     *
     * @param stat
     */
    public abstract void sendStatistics(final Statistics stat);

    protected void sendErrorStackByHttp(String errorMsg, Throwable throwable) {
        ErrorLog errorLog = new ErrorLog();
        if (throwable instanceof InvocationTargetException) {
            errorLog.setErrorType(((InvocationTargetException) throwable).getTargetException().getClass().getName());
            errorLog.setErrorMsg(((InvocationTargetException) throwable).getTargetException().getMessage());
        } else {
            errorLog.setErrorType(throwable.getClass().getName());
            errorLog.setErrorMsg(throwable.getMessage());
        }
        errorLog.setKeyId(System.getProperty("$bit_key"));
        errorLog.setIp(localIp);
        errorLog.setLogType("error");
        errorLog.setCreateTime(System.currentTimeMillis());
        // 计算异常堆栈
        {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintStream s = new PrintStream(out);
            throwable.printStackTrace(s);
            errorLog.setStack(out.toString());
        }
        execHttp("errorLog", errorLog);
    }

    protected void execHttp(final String type, final Object data) {
        Runnable runn = new Runnable() {
            public void run() {
                try {
                    String remoteUrl = System.getProperty("$bit_server");
                    //  remoteUrl += "?";
                    String key = System.getProperty("$bit_key");
                    String secret = System.getProperty("$bit_secret");
                    long currentTime = System.currentTimeMillis();
                    // 计算签名
                    String sign = secret + key + type + currentTime + secret;
                    sign = getMD5(sign.toUpperCase());
                    String params = "";
                    params += "type=" + type;
                    params += "&sign=" + sign;
                    params += "&key=" + key;
                    params += "&time=" + currentTime;
                    params += "&data=" + URLEncoder.encode(toJson(data), "UTF-8");

                    URL url = new URL(remoteUrl);
                    PrintWriter out = null;
                    BufferedReader in = null;
                    String result = "";
                    try {
                        URL realUrl = new URL(remoteUrl);
                        // 打开和URL之间的连接
                        URLConnection conn = realUrl.openConnection();
                        // 设置通用的请求属性
                        conn.setRequestProperty("accept", "*/*");
//                        conn.setRequestProperty("connection", "Keep-Alive");
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        conn.setUseCaches(false);
                        // 发送POST请求必须设置如下两行
                        conn.setDoOutput(true);
                        conn.setDoInput(true);
                        conn.setConnectTimeout(5000);
                        conn.setReadTimeout(5000);
                        // 获取URLConnection对象对应的输出流
                        out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));

                        // 发送请求参数
                        out.print(params);
                        // flush输出流的缓冲
                        out.flush();
                        // 定义BufferedReader输入流来读取URL的响应
                        in = new BufferedReader(
                                new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        String line;
                        while ((line = in.readLine()) != null) {
                            result += line;
                        }
                        if (!"ok".equals(result)) {
                            System.err.println("bit apm upload fail :" + result);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("上传失败", e);
//            logger.error("发送 POST 请求出现异常！",e);
                    }
                    //使用finally块来关闭输出流、输入流
                    finally {
                        try {
                            if (out != null) {
                                out.close();
                            }
                            if (in != null) {
                                in.close();
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        threadService.execute(runn);
    }

    protected void sendStatisticByHttp(final Statistics stat, final String type) {
        // 通过后台线程发送至监控中心
        stat.keyId = System.getProperty("$bit_key");
        execHttp(type, stat);
    }

    public static String getAnnotationValue(String key, String annotationDesc) {
        String regex = String.format("value=\\{\".*\"\\}");
        Pattern r = Pattern.compile(regex);
        Matcher matcher = r.matcher(annotationDesc);
        if (matcher.find()) {
            return matcher.group().substring(key.length() + 3, matcher.group().length() - 2);
        }
        return null;
    }

    private static String toJson(Object obj) {
        Map<String, Object> item = new HashMap<String, Object>();
        item.put("TYPE", false);
        item.put(JsonWriter.SKIP_NULL_FIELDS, true);
        String json = JsonWriter.objectToJson(obj, item);
        return json;
    }

    public static String getMD5(String content) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(content.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
