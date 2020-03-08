package cn.lastwhisper.test;


import cn.lastwhisper.monitor.agent.collects.SpringControllerCollects;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;
import javassist.NotFoundException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by tommy on 17/7/16.
 */

public class SpringControllerCollectsTest {
	@Test
	public void isTarget() throws NotFoundException {
		SpringControllerCollects ins = SpringControllerCollects.INSTANCE;
		String classname = "com.bit.monitoring.test.SpringControllerCollectsTest$SpringControllerMock";
		ClassLoader loader = SpringControllerCollectsTest.class
				.getClassLoader();
		ClassPool pool = new ClassPool();
		pool.insertClassPath(new LoaderClassPath(loader));
		CtClass cclass = pool.get(classname);
		Assert.assertTrue(ins.isTarget(classname, loader, cclass));
	}

	@Test
	public void transformTest() throws Exception {
		System.setProperty("$bit_server", "http://123.56.21.219:8860/receive");
		System.setProperty("$bit_key", "c4f3508aee6058f3");
		System.setProperty("$bit_secret", "966eedc1903454b8");

		SpringControllerCollects ins = SpringControllerCollects.INSTANCE;
		String classname = "com.bit.test.SpringControllerCollectsTest$SpringControllerMock";
		ClassLoader loader = SpringControllerCollectsTest.class
				.getClassLoader();
		ClassPool pool = new ClassPool();
		pool.insertClassPath(new LoaderClassPath(loader));
		CtClass cclass = pool.get(classname);
		String className = classname;
		byte[] classfileBuffer = null;
		if (ins.isTarget(className, loader, cclass)) {
			ins.transform(loader, className, classfileBuffer, cclass);
			Class cla = cclass.toClass();
			SpringControllerMock mock = new SpringControllerMock();
			mock.sayHello();
			System.out.println(mock.getName("", ""));
		} else {
			Assert.assertTrue(false);
		}

		Path path = new java.io.File(System.getProperty("user.dir")
				+ "/target/test-classes/" + cclass.getSimpleName() + ".class")
				.toPath();
		Files.write(path, cclass.toBytecode());
		// 留出时间用于上传线程执行
		Thread.sleep(2000);
	}

	@Test
	public void errorTest() throws Exception {
		SpringControllerCollects ins = SpringControllerCollects.INSTANCE;
		String classname = "com.bit.test.SpringControllerCollectsTest.SpringControllerMock";
		ClassLoader loader = SpringControllerCollectsTest.class
				.getClassLoader();
		ClassPool pool = new ClassPool();
		pool.insertClassPath(new LoaderClassPath(loader));
		CtClass cclass = pool.get(classname);
		String className = classname;
		byte[] classfileBuffer = null;
		if (ins.isTarget(className, loader, cclass)) {
			ins.transform(loader, className, classfileBuffer, cclass);
			Class cla = cclass.toClass();
			SpringControllerMock mock = new SpringControllerMock();
			// mock.sayHello();
			try {
				System.out.println(mock.getName("李大刚", "男"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Assert.assertTrue(false);
		}
		// 留出时间用于上传线程执行
		Thread.sleep(2000);
	}

	@Controller("aaa")
	@RequestMapping("/mock")
	public static class SpringControllerMock {
		// /mock/hello
		@RequestMapping("/hello")
		public void sayHello() {
			System.out.println("hello");
		}

		@RequestMapping(value = "/getName.do"/*, params = { "method=ddddd" }*/)
		@ResponseBody
		public String getName(String name, String sex) {
			if ("男".equals(sex)) {
				throw new RuntimeException("该方法传女不传男");
			}
			return "hanmeme";
		}

		@RequestMapping("{id}")
		public String product(ModelMap model, @PathVariable("id") String id) {
			return " 商品ID:" + id;
		}

	}
}
