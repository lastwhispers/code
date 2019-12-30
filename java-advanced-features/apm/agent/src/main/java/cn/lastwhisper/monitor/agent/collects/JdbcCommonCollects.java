package cn.lastwhisper.monitor.agent.collects;

import cn.lastwhisper.monitor.agent.AbstractCollects;
import cn.lastwhisper.monitor.agent.AgentLoader;
import cn.lastwhisper.monitor.agent.Collect;
import cn.lastwhisper.monitor.agent.NotProguard;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * jdbc 数据采集
 * Created by tommy on 17/7/16.
 */
@NotProguard
public class JdbcCommonCollects extends AbstractCollects implements Collect {
    @NotProguard
    public static final JdbcCommonCollects INSTANCE = new JdbcCommonCollects();

    private final static String[] connection_agent_methods = new String[]{"prepareStatement"};
    private final static String[] prepared_statement_methods = new String[]{"execute", "executeUpdate", "executeQuery"};
    private static final String beginSrc;
    private static final String endSrc;
    private static final String errorSrc;
    static {
        StringBuilder sbuilder = new StringBuilder();
        // connect
        beginSrc = "cn.lastwhisper.monitor.agent.collects.JdbcCommonCollects inst=cn.lastwhisper.monitor.agent.collects.JdbcCommonCollects.INSTANCE;";
        errorSrc = "inst.error(null,e);";
        endSrc = "result=inst.proxyConnection((java.sql.Connection)result);";
    }

    public boolean isTarget(String className, ClassLoader loader, CtClass ctclass) {
        if (className.equals("com.mysql.jdbc.NonRegisteringDriver")) {
            return true;
        }
        return false;
    }
    @NotProguard
    @Override
    public Statistics begin(String className, String method) {
        JdbcStatistics jdbcStat = new JdbcStatistics(super.begin(className, method));
        jdbcStat.logType = "sql";
        return jdbcStat;
    }

    @NotProguard
    @Override
    public void end(Statistics stat) {
        JdbcStatistics jdbcStat= (JdbcStatistics) stat;
        if (jdbcStat.jdbcUrl != null) {
            jdbcStat.databaseName = getDbName(jdbcStat.jdbcUrl);
        }
        super.end(stat);
    }

    @Override
    public void sendStatistics(Statistics stat) {
        sendStatisticByHttp(stat, "sqlLog");
    }

    @NotProguard
    public Connection proxyConnection(final Connection connection) {
        Object c = Proxy.newProxyInstance(JdbcCommonCollects.class.getClassLoader()
                , new Class[]{Connection.class}, new ConnectionHandler(connection));
        return (Connection) c;
    }


    public PreparedStatement proxyPreparedStatement(final PreparedStatement statement, JdbcStatistics jdbcStat) {
        Object c = Proxy.newProxyInstance(JdbcCommonCollects.class.getClassLoader()
                , new Class[]{PreparedStatement.class}, new PreparedStatementHandler(statement, jdbcStat));
        return (PreparedStatement) c;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, byte[] classfileBuffer, CtClass ctclass) throws Exception {
        AgentLoader byteLoader = new AgentLoader(className, loader, ctclass);
        CtMethod connectMethod = ctclass.getMethod("connect", "(Ljava/lang/String;Ljava/util/Properties;)Ljava/sql/Connection;");
//      connectMethod.getMethodInfo().getDescriptor();
        AgentLoader.MethodSrcBuild build = new AgentLoader.MethodSrcBuild();
        build.setBeginSrc(beginSrc);
        build.setErrorSrc(errorSrc);
        build.setEndSrc(endSrc);
        byteLoader.updateMethod(connectMethod, build);
        return byteLoader.toBytecode();
    }


    /**
     * connection 代理处理
     */
    public class ConnectionHandler implements InvocationHandler {
        private final Connection connection;

        private ConnectionHandler(Connection connection) {
            this.connection = connection;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            boolean isTargetMethod = false;
            for (String agentm : connection_agent_methods) {
                if (agentm.equals(method.getName())) {
                    isTargetMethod = true;
                }
            }
            Object result = null;
            JdbcStatistics jdbcStat = null;
            try {
                if (isTargetMethod) { // 获取PreparedStatement 开始统计
                    jdbcStat = (JdbcStatistics) JdbcCommonCollects.this.begin(null, null);
                    jdbcStat.jdbcUrl = connection.getMetaData().getURL();
                    jdbcStat.sql = (String) args[0];
                }
                result = method.invoke(connection, args);
                // 代理 PreparedStatement
                if (isTargetMethod && result instanceof PreparedStatement) {
                    PreparedStatement ps = (PreparedStatement) result;
                    result = proxyPreparedStatement(ps, jdbcStat);
                }
            } catch (Throwable e) {
                JdbcCommonCollects.this.error(jdbcStat, e);
                JdbcCommonCollects.this.end(jdbcStat);
                throw e;
            }
            return result;
        }
    }

    /**
     * PreparedStatement 代理处理
     */
    public class PreparedStatementHandler implements InvocationHandler {
        private final PreparedStatement statement;
        private final JdbcStatistics jdbcStat;

        public PreparedStatementHandler(PreparedStatement statement, JdbcStatistics jdbcStat) {
            this.statement = statement;
            this.jdbcStat = jdbcStat;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            boolean isTargetMethod = false;
            for (String agentm : prepared_statement_methods) {
                if (agentm.equals(method.getName())) {
                    isTargetMethod = true;
                }
            }
            Object result = null;
            try {
                result = method.invoke(statement, args);
            } catch (Throwable e) {
                if (isTargetMethod) {
                    JdbcCommonCollects.this.error(jdbcStat, e);
                }
                throw e;
            } finally {
                if (isTargetMethod) {
                    JdbcCommonCollects.this.end(jdbcStat);
                }
            }
            return result;
        }
    }

    @NotProguard
    public static class JdbcStatistics extends Statistics {
        // jdbc url
        public String jdbcUrl;
        // sql 语句
        public String sql;
        // 数据库名称
        public String databaseName;


        public JdbcStatistics(Statistics stat) {
            super(stat);
        }
    }

    private static String getDbName(String url) {
        int index = url.indexOf("?"); //$NON-NLS-1$
        if (index != -1) {
            String paramString = url.substring(index + 1, url.length());
            url = url.substring(0, index);
        }
        String dbName = url.substring(url.lastIndexOf("/") + 1);
        return dbName;
    }
}
