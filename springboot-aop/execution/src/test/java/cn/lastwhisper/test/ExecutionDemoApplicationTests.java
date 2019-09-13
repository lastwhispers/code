package cn.lastwhisper.test;

import cn.lastwhisper.bean.Product;
import cn.lastwhisper.log.LogService;
import cn.lastwhisper.service.ProductService;
import cn.lastwhisper.service.sub.SubService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExecutionDemoApplicationTests {

    @Autowired
    ProductService productService;

    @Autowired
    SubService subService;

    @Autowired
    LogService logService;

    // AdviceAspectConfig使用
    @Test
    public void test4() {
        productService.findById(1L);
        productService.findByTwoArgs(1L,"hello");
        productService.getName();
        subService.demo();
        try {
            productService.exDemo();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        logService.log();
        productService.log();
        logService.annoArg(new Product());
    }

    // AnnoAspectConfig使用
    @Test
    public void test3() {
        productService.findById(1L);
        productService.findByTwoArgs(1L,"hello");
        productService.getName();
        subService.demo();
        try {
            productService.exDemo();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        logService.log();
        productService.log();
        logService.annoArg(new Product());
    }

    // ObjectAspectConfig、ArgsAspectConfig使用
    @Test
    public void test2() {
        productService.findById(1L);
        //productService.findByTwoArgs("hello",1L);
        productService.findByTwoArgs(1L,"hello");
        productService.getName();
        subService.demo();
        try {
            productService.exDemo();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        logService.log();
    }

    // ExecutionAspectConfig、PkgTypeAspectConfig使用
    @Test
    public void test1() {
        productService.findById(1L);
        productService.findByTwoArgs(1L, "Hello");
        productService.getName();
        subService.demo();
        try {
            productService.exDemo();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
