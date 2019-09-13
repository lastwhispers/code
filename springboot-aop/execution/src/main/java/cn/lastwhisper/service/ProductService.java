package cn.lastwhisper.service;

import cn.lastwhisper.anno.NeedSecured;
import cn.lastwhisper.anno.NeedSecuredClass;
import cn.lastwhisper.anno.NeedSecuredSource;
import cn.lastwhisper.log.Loggable;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Component;

/**
 * implements Loggable测试ObjectAspectConfig使用
 */
@Component
@NeedSecured //下面三个注解测试 AnnoAspectConfig 使用,且这三个注解可以被继承,即SubService也有
@NeedSecuredSource
@NeedSecuredClass
public class ProductService implements Loggable {

    public String getName(){
        System.out.println("getName for ProductService");
        return "product service";
    }

    public void exDemo() throws IllegalAccessException {
        System.out.println("exDemo for ProductService");
        // throw new IllegalAccessException("TEST");在使用AdviceAspectConfig时使用
        throw new IllegalAccessException("TEST");
    }

    public void findById(Long id){
        System.out.println("findById for ProductService");
    }

    //public void findByTwoArgs(String name,Long id){
    public void findByTwoArgs(Long id,String name){
        System.out.println("findByTwoArgs for ProductService");
    }

    // 测试ObjectAspectConfig使用
    //@Override
    public void log() {
        System.out.println("log for ProductService");
    }

}
