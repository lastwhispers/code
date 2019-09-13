package cn.lastwhisper.jvm.memorystruct;

/**
 * Jdk6、7字符串常量池在永久代；jdk8字符串常量池在堆中
 * 1.jdk6环境下String.intern()；
 * 如果字符串常量池中已经包含一个等于此String对象的字符串,则返回代表池中这个字符串的String对象
 * 否则，将此String对象包含的字符串添加到常量池中，并返回此String对象的引用。 
 * 	false false
 * 2.jdk7环境下String.intern()；
 * 如果字符串常量池中已经包含一个等于此String对象的字符串,则返回代表池中这个字符串的String对象
 * 否则，如果该String对象已经存在于堆中，则将堆中对此对象的引用添加到字符串常量池中，并返回该引用；
 *  如果堆中不存在，则在池中创建该字符串，并返回其引用。 
 * false true
 * 
 * @author lastwhisper
 */
public class InternDifference {
    public static void main(String[] args) {
    	/**
    	 * 
    	 * 1.jdk6
    	 * String s1 = new String("a");//一个语句执行两个操作句话,"a"会在字符串常量池中创建；new String("a")会在堆里面创建
         * s1.intern();// 把首次遇到的字符串复制到永久代（字符串常量池）
         * String s2 = "a";//直接使用字符串常量池"a"
         * System.out.println(s1 == s2);//s1指向堆"a",s2指向常量池"a" false
         * 
                         * 优化为：String s3 = (new StringBuilder()).append(new String("a")).append(new String("a")).toString();
         * String s3 = new String("a") + new String("a");//只在堆中创建"aa",s3指向堆中"aa"
         * s3.intern();//发现"aa"在字符串常量池中不已经存在,在常字符串量池中创建"aa"（把首次遇到的字符串复制到永久代（字符串常量池））
         * String s4 = "aa";//s4直接使用字符串常量池"aa"
         * System.out.println(s3 == s4);//s3指向堆"aa",s4指向常量池"aa" false
    	 * 
    	 * 1.jdk7
    	 * String s1 = new String("a");//"a"会在字符串常量池中创建、new String("a")会在堆里面创建
         * s1.intern();//把首次遇到的字符串的引用复制到永久代（字符串常量池）
         * 			   //发现"a"在字符串常量池中已经存在，就不能在让常字符串量池中创建"a"引用指向堆中的"a"
         * String s2 = "a";//直接使用字符串常量池"a"
         * System.out.println(s1 == s2);//s1指向堆"a"，s2指向常量池"a" false
         * 
                         * 优化为：String s = (new StringBuilder()).append(new String("a")).append(new String("a")).toString();
         * String s3 = new String("a") + new String("a");//只在堆中创建"aa",s3指向堆中"aa"
         *	s3.intern();//把首次遇到的字符串的引用复制到永久代（字符串常量池）
         *				//发现"aa"在字符串常量池中不存在，让字符串常量池中引用堆中的"aa"
         * 	String s4 = "aa";// 直接使用字符串常量池"aa"（这个"aa"是从堆中的引用）
         * 	System.out.println(s3 == s4);//s3指向堆中"aa"，s4指向字符串常量池"aa"，而常量池"aa"是堆中"aa"的引用，所以s3==s4 true
    	 */
        String s1 = new String("a");
        s1.intern();
        String s2 = "a";
        System.out.println(s1 == s2);
        
        String s3 = new String("a") + new String("a");
        s3.intern();
        String s4 = "aa";
        System.out.println(s3 == s4);

        String str1 = "abcd";
        String str2 = new String("abcd");
        System.out.println(str1==str2);//false

    }
}
