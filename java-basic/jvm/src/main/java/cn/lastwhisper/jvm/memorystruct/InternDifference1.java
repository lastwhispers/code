package cn.lastwhisper.jvm.memorystruct;

public class InternDifference1 {
	public static void main(String[] args) {
		String str1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.intern() == str1);
		//https://segmentfault.com/a/1190000011543995
		//sun.misc.Version.init();方法中已经存在"java" 
		// 修改为new StringBuilder("ja").append("ava").toString(); true
		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern() == str2);//str2.intern()字符串常量池、str2堆中
	}
}
