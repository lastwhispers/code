package com.desgin.pattern.creational.simplefactory;

/**
 * Create by lastwhisper on 2019/1/23
 */
public class VideoFactory {
    /**
     * 根据Class字节码创建对象
     * @param c
     * @return
     */
    public static Video getVideo(Class c){
        Video video = null;
        try {
            video = (Video) Class.forName(c.getName()).newInstance();
            return video;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static Video getVideo(String type){
//        if("java".equalsIgnoreCase(type)){
//            return new JavaVideo();
//        }else if("python".equalsIgnoreCase(type)){
//            return new PythonVideo();
//        }
//        return null;
//    }
}
