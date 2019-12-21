package cn.lastwhisper.springbootaop.core.common;

import cn.lastwhisper.springbootaop.core.annotation.LogOperation;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.validation.BindingResult;

import java.lang.reflect.Method;
import java.util.Objects;

public class JoinPointParesUtils {

    /**
     * 获取方法参数
     */
    public static String getMethodInfo(JoinPoint point) {
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) point.getSignature();

        //全限定名
        String className = methodSignature.getDeclaringType().getName();
        //方法名
        String methodName = methodSignature.getName();
        //参数名数组
        String[] parameterNames = methodSignature.getParameterNames();

        // 获取方法
        Method method = methodSignature.getMethod();
        // 获取方法上面的注解
        LogOperation logOperation = method.getAnnotation(LogOperation.class);
        String operate = "";
        if (logOperation != null) {
            operate = logOperation.value();
        }
        StringBuilder sb = null;
        if (Objects.nonNull(parameterNames)) {
            sb = new StringBuilder();
            for (int i = 0; i < parameterNames.length; i++) {
                Object arg = point.getArgs()[i];//参数值
                if (arg instanceof BindingResult) {
                    continue;
                }
                // 复杂对象
                if (arg != null && !isPrimitive(arg)) {
                    sb.append(parameterNames[i]).append(":").append(JSONObject.toJSON(arg)).append("; ");
                } else {
                    // null值或者基础数据类型
                    String parameterValue = arg != null ? arg.toString() : "null";
                    sb.append(parameterNames[i]).append(":").append(parameterValue).append("; ");
                }
            }
        }
        sb = sb == null ? new StringBuilder() : sb;
        return String.format("操作名称:[%s] | 全限定名:[%s] | 方法名:[%s] | 参数列表:[%s]", operate, className, methodName, sb.toString());
    }

    /**
     * 是否是基础数据类型
     */
    public static boolean isPrimitive(Object obj) {
        try {
            return ((Class) obj.getClass().getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }
}
