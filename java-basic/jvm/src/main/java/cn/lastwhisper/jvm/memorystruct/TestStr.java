package cn.lastwhisper.jvm.memorystruct;

public class TestStr {
	public static void main(String[] args) {
		// 测试优化环境jdk8
		// 2.测试"ab"是否影响String s2=new String("a"+"b");
//		String s1 = "ab";
		// 1.测试"a"+"b"是否被jit编译器优化为"ab"
		// 优化为String s2=new String("ab");
//		String s2=new String("a"+"b");
		// 3.测试new String("a")+new String("a")是否会被优化
		// 编译器优化为String s = (new StringBuilder()).append(new String("a")).append(new String("a")).toString();
		//String s3 = new String("a")+new String("a");
		/*4.String拼接*/
		//String str1 = "str";
		//String str2 = "ing";
		//String str3 = "str" + "ing";//常量池中的对象
		//String str4 = str1 + str2; //在堆上创建的新的对象
		//String str5 = "string";//常量池中的对象
		//System.out.println(str3 == str4);//false
		//System.out.println(str3 == str5);//true
		//System.out.println(str4 == str5);//false
		String s1 = new String("abc");// 堆内存的地址值
		String s2 = "abc";
		System.out.println(s1 == s2);// 输出false,因为一个是堆内存，一个是常量池的内存，故两者是不同的。
		System.out.println(s1.equals(s2));// 输出true
	}
}
