package cn.lastwhisper.javassist;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.LoaderClassPath;
import javassist.NotFoundException;
/**
 * 一个代理实现ClassFileTransformer接口用于改变运行时的字节码（class File），
 * 这个改变发生在jvm加载这个类之前。对所有的类加载器有效。
 */
public class JavassistMonitorServlet implements ClassFileTransformer {
	// 在应用启动前调用
	public static void premain(String agentArgs, Instrumentation inst) {
		inst.addTransformer(new JavassistMonitorServlet());
	}

	@Override
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		// 屏蔽
		String target = "javax.servlet.http.HttpServlet";
		if (target.equals(className)) {
			try {
				return buildClass(target, loader);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public byte[] buildClass(String target, ClassLoader loader)
			throws NotFoundException, IOException, CannotCompileException {
		ClassPool pool = new ClassPool();
		pool.insertClassPath(new LoaderClassPath(loader));
		CtClass cla = pool.get(target);
		CtClass[] params = new CtClass[] {
				pool.get("javax.servlet.http.HttpServletRequest"),
				pool.get("javax.servlet.http.HttpServletResponse")
		};
		CtMethod method = cla.getDeclaredMethod("service", params);

		method.insertBefore("cn.lastwhisper.javassist.DispatcherServletCollect.begin($args");
		// 解决ClassNotFound，AppClassLoader与CommonClassLoader不同层级
		// 同时tomcat的类加载机制打破了双亲委派机制是从下往上找
		pool.get("cn.lastwhisper.javassist.DispatcherServletCollect").toClass(loader,null);
		return cla.toBytecode();

	}
}
