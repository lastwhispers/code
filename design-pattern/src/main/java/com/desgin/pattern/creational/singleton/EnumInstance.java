package com.desgin.pattern.creational.singleton;

/**
 * Create by lastwhisper on 2019/1/26
 */
public enum EnumInstance {
    //单例对象
    INSTANCE;
//    INSTANCE
//    {
//        protected  void  printTest(){
//            System.out.println("Gaojun Print Test");
//        }
//    };
//    protected  abstract void printTest();
    /**
     * data测试
     */
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public static EnumInstance getInstance(){
        return INSTANCE;
    }
}
