package cn.lastwhisper.javabasic.Reflect;

import java.lang.reflect.InvocationTargetException;

/**
 * @author lastwhisper
 * @desc
 * 	反射: 把java类中的各种结构(方法、属性、构造器、类名)映射成一个个的Java对象。
 *  * 1、获取Class对象
 *  *三种方式: Class.forName("完整路径")
 *  * 2、可以动态创建对象
 *  * clazz.getConstructor().newInstance()
 */
public class ReflectTest {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		//三种方式
		//1、对象.getClass()
		Iphone iphone =new Iphone();
		Class clazz = iphone.getClass();
		//2、类.class()
        clazz = Iphone.class;
		//3、Class.forName("包名.类名")
        clazz = Class.forName("cn.lastwhisper.javabasic.Reflect.Iphone");
		
		//创建对象
		/*Iphone iphone2 =(Iphone)clazz.newInstance();
		System.out.println(iphone2);*/
		Iphone iphone2 =(Iphone)clazz.getConstructor().newInstance();
		System.out.println(iphone2);
	}

}

class Iphone{
	public Iphone() {
		
	}
}
