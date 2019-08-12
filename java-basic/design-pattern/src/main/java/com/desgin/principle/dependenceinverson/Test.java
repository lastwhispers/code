package com.desgin.principle.dependenceinverson;

/**
 * Create by lastwhisper on 2019/1/22
 */
public class Test {
    //v 1
//   public static void main(String[] args){
//       //高层次模块（Test）依赖于低层次模块（Person）
//       Person person = new Person();
//       person.studyJavaCourse();
//       person.studyFECourse();
//   }
    //v2
//    public static void main(String[] args){
//        Person person = new Person();
//        person.studyImoocCourse(new JavaCourse());
//        person.studyImoocCourse(new FECourse());
//        person.studyImoocCourse(new PythonCourse());
//    }
    //v3
//    public static void main(String[] args){
//        Person person = new Person(new JavaCourse());
//        person.studyImoocCourse();
//    }
    public static void main(String[] args) {
        Person person = new Person();
        person.setiCourse(new JavaCourse());
        person.studyImoocCourse();
        person.setiCourse(new FECourse());
        person.studyImoocCourse();
        person.setiCourse(new PythonCourse());
        person.studyImoocCourse();
    }
}
