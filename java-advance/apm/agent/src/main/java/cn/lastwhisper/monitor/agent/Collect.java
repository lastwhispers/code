package cn.lastwhisper.monitor.agent;

import javassist.CtClass;

/**
 * 采集接口 
 * Created by tommy on 17/7/14.
 */
public interface Collect {
	/**
	 * 判断是否为采集目录
	 * 
	 * @param className
	 * @param loader
	 * @param ctclass
	 * @return
	 */
	public boolean isTarget(String className, ClassLoader loader,
                            CtClass ctclass);

	/**
	 * 对目标类进行转
	 * @param loader
	 * @param className
	 * @param classfileBuffer
	 * @param ctclass
	 * @return
	 * @throws Exception
	 */
	public byte[] transform(ClassLoader loader, String className,
                            byte[] classfileBuffer, CtClass ctclass) throws Exception;
}
