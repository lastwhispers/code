package cn.lastwhisper.aop.proxyfactory;

import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;


/**
 * 使用硬编码的方式来实现AOP（ProxyFactory）.
 *
 * @author jerry
 * @date 2020 -03-28 13:57:05
 */
public class ProxyFactoryTest {


    public static void useAdvisor() {
        ProxyFactory factoryBean = new ProxyFactory();
        //设置代理对象
        factoryBean.setTarget(new BookShopService());

        //使用表达式模式来匹配需要 增强的方法已经类，和 xml 的配置可以达到相同的效果
        AspectJExpressionPointcutAdvisor pointcutAdvisor = new AspectJExpressionPointcutAdvisor();
        pointcutAdvisor.setExpression("execution(* *.delete*()) or execution(* *.update*())");
        pointcutAdvisor.setAdvice(new MethodAdvice());
//        //直接可以设置监听所有执行的方法
//        factoryBean.addAdvice(new MethodAdvice());

        factoryBean.addAdvisor(pointcutAdvisor);


        BookShopService bean = (BookShopService) factoryBean.getProxy();

        System.out.println(bean.getClass() + ": updateBookInfo");
        bean.deleteBook();
        //没有保存
        bean.queryBook();
    }


    public static void main(String[] args) {
        useAdvisor();
    }

}