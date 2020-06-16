package cn.lastwhisper.basic.io.otherstream;

import java.io.Serializable;

/*
 * NotSerializableException:未序列化异常
 *
 * 类通过实现 java.io.Serializable 接口以启用其序列化功能。未实现此接口的类将无法使其任何状态序列化或反序列化。
 * 该接口居然没有任何方法，类似于这种没有方法的接口被称为标记接口。
 *
 * java.io.InvalidClassException:
 * cn.itcast_07.Person; local class incompatible:
 * stream classdesc serialVersionUID = -2071565876962058344,
 * local class serialVersionUID = -8345153069362641443
 *
 * 为什么会有问题呢?
 * 		Person类实现了序列化接口，那么它本身也应该有一个标记值。
 * 		这个标记值假设是100。
 * 		开始的时候：
 * 		Person.class -- id=100
 * 		wirte数据： oos.txt -- id=100
 * 		read数据: oos.txt -- id=100
 *
 * 		现在：
 * 		Person.class -- id=200
 * 		wirte数据： oos.txt -- id=100
 * 		read数据: oos.txt -- id=100
 *
 * 注意：
 * 		我一个类中可能有很多的成员变量，有些我不想进行序列化。请问该怎么办呢?
 * 		使用transient关键字声明不需要序列化的成员变量
 */
public class Person implements Serializable {
	private static final long serialVersionUID = -2071565876962058344L;

	private String name;

	// private int age;

	private transient int age;

	// int age;

	public Person() {
		super();
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
}
