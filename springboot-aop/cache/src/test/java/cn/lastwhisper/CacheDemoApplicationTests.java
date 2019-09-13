package cn.lastwhisper;

import cn.lastwhisper.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheDemoApplicationTests {

    @Autowired
    MenuService menuService;

    @Test
    public void testCache() {
        System.out.println("call:"+menuService.getMenuList());
        System.out.println("call:"+menuService.getMenuList());
    }

    //测试内部调用
    @Test
    public void testInnerCall() {
        System.out.println("call:"+menuService.getRecommends());
        System.out.println("call:"+menuService.getRecommends());
    }
}
