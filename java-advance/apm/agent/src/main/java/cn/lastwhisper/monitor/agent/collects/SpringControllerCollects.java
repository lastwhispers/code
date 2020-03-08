package cn.lastwhisper.monitor.agent.collects;

import cn.lastwhisper.monitor.agent.AbstractCollects;
import cn.lastwhisper.monitor.agent.AgentLoader;
import cn.lastwhisper.monitor.agent.Collect;
import cn.lastwhisper.monitor.agent.NotProguard;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;

import java.nio.file.Files;

/**
 * Created by tommy on 17/7/16.
 */
@NotProguard
public class SpringControllerCollects extends AbstractCollects implements Collect {
    @NotProguard
    public static SpringControllerCollects INSTANCE = new SpringControllerCollects();
    private static final String beginSrc;
    private static final String endSrc;
    private static final String errorSrc;

    private String rootRequestUrl = "";

    static {
        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append("cn.lastwhisper.monitor.agent.collects.SpringControllerCollects instance= ");
        sbuilder.append("cn.lastwhisper.monitor.agent.collects.SpringControllerCollects.INSTANCE;\r\n");
        sbuilder.append("cn.lastwhisper.monitor.agent.collects.SpringControllerCollects.WebStatistics statistic =(cn.lastwhisper.monitor.agent.collects.SpringControllerCollects.WebStatistics)instance.begin(\"%s\",\"%s\");");
        sbuilder.append("statistic.urlAddress=\"%s\";");
        beginSrc = sbuilder.toString();
        sbuilder = new StringBuilder();
        sbuilder.append("instance.end(statistic);");
        endSrc = sbuilder.toString();
        sbuilder = new StringBuilder();
        sbuilder.append("instance.error(statistic,e);");
        errorSrc = sbuilder.toString();
    }

    @Override
    public boolean isTarget(String className, ClassLoader loader, CtClass ctclass) {
        boolean result = false;
        try {
            for (Object obj : ctclass.getAnnotations()) {
                // 通过正则表达示计算出RequestMapping 地址
                if (obj.toString().startsWith("@org.springframework.web.bind.annotation.RequestMapping")) {
                    rootRequestUrl = getAnnotationValue("value", obj.toString());
                } else if (obj.toString().startsWith("@org.springframework.stereotype.Controller")) {
                    result = true;
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println(String.format("bit apm run error targetClassName=%s errorMessage=%s",className,e.getClass().getSimpleName()+":"+e.getMessage()));
        }
        return result;
    }

    @NotProguard
    @Override
    public Statistics begin(String className, String method) {
        WebStatistics webStat = new WebStatistics(super.begin(className, method));
        webStat.controlName = className;
        webStat.methodName = method;
        webStat.logType="web";
        return  webStat;
    }

    @Override
    public void sendStatistics(Statistics stat) {
        sendStatisticByHttp(stat,"webLog");
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, byte[] classfileBuffer, CtClass ctclass) throws Exception {
        AgentLoader byteLoader = new AgentLoader(className, loader, ctclass);
        CtMethod[] methods = ctclass.getDeclaredMethods();
        for (CtMethod m : methods) {
            String requestUrl;
            // 屏蔽非公共方法
            if (!Modifier.isPublic(m.getModifiers())) {
                continue;
            }
            // 屏蔽静态方法
            if (Modifier.isStatic(m.getModifiers())) {
                continue;
            }
            // 屏蔽本地方法
            if (Modifier.isNative(m.getModifiers())) {
                continue;
            }
            // 必须带上 RequestMapping 注解
            if ((requestUrl = getRequestMappingValue(m)) == null) {
                continue;
            }

            AgentLoader.MethodSrcBuild build = new AgentLoader.MethodSrcBuild();
            build.setBeginSrc(String.format(beginSrc, className, m.getName(), rootRequestUrl + requestUrl));
            build.setEndSrc(endSrc);
            build.setErrorSrc(errorSrc);
            byteLoader.updateMethod(m, build);
        }
        return byteLoader.toBytecode();
    }


    private String getRequestMappingValue(CtMethod m) throws ClassNotFoundException {
        for (Object s : m.getAnnotations()) {
            if (s.toString().startsWith("@org.springframework.web.bind.annotation.RequestMapping")) {
                String val = getAnnotationValue("value", s.toString());
                return val==null?"/":val;
            }
        }
        return null;
    }

    @NotProguard
    public static class WebStatistics extends Statistics {
        public String urlAddress; //url 地址
        public String controlName; //服务名称
        public String methodName;// 方法名称
        public WebStatistics(Statistics s) {
            super(s);
        }
    }
}
