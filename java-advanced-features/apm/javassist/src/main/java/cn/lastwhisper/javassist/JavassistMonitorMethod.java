package cn.lastwhisper.javassist;

import javassist.*;

/**
 * 新建一个方法，将被调用方法copy到新方法
 * 再被调用方法中进行监控新方法。
 * @author lastwhisper
 */
public class JavassistMonitorMethod {
    public static void main(String[] args) throws NotFoundException, CannotCompileException {

        ClassPool pool = new ClassPool(true);
        String targetClassName = "cn.lastwhisper.javassist.StringUtil";
        //String targetClassName = StringUtil.class.getName(); //导致类加载器提前加载StringUtil
        CtClass targetClass = pool.get(targetClassName);
        CtMethod method = targetClass.getDeclaredMethod("addString");
        CtMethod agentMethod = CtNewMethod.copy(method, method.getName() + "$agent", targetClass, null);
        targetClass.addMethod(agentMethod);
        String src = "{"
                + "long begin = System.nanoTime();"
                + "Object result=" + method.getName() + "$agent($$);"
                + "long end = System.nanoTime();"
                + "System.out.println(\"耗时:\"+(end-begin));"
                + "return ($r)result;"
                + "}";
        method.setBody(src);
        // 载入至当前ClassLoader
        targetClass.toClass();
        StringUtil util = new StringUtil();//此时类加载器还未加载StringUtil,StringUtil已经被修改
        util.addString(1000);
    }
}
