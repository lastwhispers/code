package cn.lastwhisper.aop.config;

import cn.lastwhisper.aop.LogAspects;
import cn.lastwhisper.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AOP：面向切面编程，指在程序运行期间动态的将某段代码切入到指定方法指定位置进行运行的编程方式。
 *
 * 1）导入AOP依赖：spring-aspects
 * 2）准备一个业务处理类（MathCalculator）：在业务处理时将日志进行打印（方法执行前、方法执行后、方法出现异常时...）
 * 3）准备一个日志切面类（LogAspects）：在业务处理类运行的不同时期执行不同的通知
 *      通知方法：
 *          （1）前置通知（@Before）：logStart，在目标方法（div）执行之前运行
 *          （2）后置通知（@After）：logEnd，在目标方法（div）执行之后运行
 *          （3）返回通知（@AfterReturning）：logReturn，在目标方法（div）执行返回之后运行
 *          （4）异常通知（@AfterThrowing）：logException，在目标方法（div）执行时出现异常之后运行
 *          （5）环绕通知（@Around）：动态代理，手动推进目标方法运行（joinPoint.procced()）
 * 4）通知注解：给切面类的方法标注何时运行（@Before、@After...）
 * 5）将业务处理类和日志切面类都加入到容器中，并告诉Spring那个是切面类（@Aspect）
 * 6）给配置类中加 @EnableAspectJAutoProxy（开启基于注解的aop模式）
 *
 * AOP原理：看给容器中注册了什么组件，这个组件什么时候工作，这个组件的功能是什么？
 *  1）@EnableAspectJAutoProxy注解的功能
 *      导入组件@Import(AspectJAutoProxyRegistrar.class)
 *          AspectJAutoProxyRegistrar实现了ImportBeanDefinitionRegistrar接口
 *              利用AspectJAutoProxyRegistrar自定义给容器注册bean
 *              internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator
 *          给容器中注册了一个AnnotationAwareAspectJAutoProxyCreator组件
 *
 *  2）AnnotationAwareAspectJAutoProxyCreator
 *      AnnotationAwareAspectJAutoProxyCreator
 *          ——》父类 AspectJAwareAdvisorAutoProxyCreator
 *              ——》父类 AbstractAdvisorAutoProxyCreator
 *                  ——》父类 AbstractAutoProxyCreator
 *                      ——》implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *                      关注后置处理器（在bean初始化完成前后做事情）、自动装配BeanFactory
 *
 *      设置断点
 *      AbstractAutoProxyCreator.setBeanFactory(BeanFactoryAware)
 *      AbstractAutoProxyCreator.postProcessAfterInitialization、postProcessBeforeInstantiation等有后置处理器的逻辑
 *
 *      AbstractAdvisorAutoProxyCreator.setBeanFactory()——》initBeanFactory
 *
 *      AnnotationAwareAspectJAutoProxyCreator.initBeanFactory
 *
 *  3）AnnotationAwareAspectJAutoProxyCreator创建和注册的过程
 *      1）传入配置类，创建IOC容器
 *      2）注册配置类，AbstractApplicationContext.refresh()刷新容器
 *      3）PostProcessorRegistrationDelegate.registerBeanPostProcessors：注册bean的后置处理器来方便拦截bean的创建
 *         *注册bean的后置处理器的流程：
 *          1）获取IOC容器中已经定义的需要创建对象的所有BeanPostProcessor
 *          2）给容器中加入别的BeanPostProcessor
 *          3）分离BeanPostProcessor，分别注册BeanPostProcessor
 *              1.First, register the BeanPostProcessors that implement PriorityOrdered
 *              2.Next, register the BeanPostProcessors that implement Ordered.
 *              3.Now, register all regular BeanPostProcessors.
 *          4）注册BeanPostProcessor，实际上就是创建BeanPostProcessor对象，保存在容器中——————————————创建
 *              由于AnnotationAwareAspectJAutoProxyCreator实现了Ordered与xxxBeanPostProcessor接口在（3）的2步骤开始注册
 *              *AnnotationAwareAspectJAutoProxyCreator注册流程（AbstractAutowireCapableBeanFactory.doCreateBean()）
 *                  1.创建internalAutoProxyCreator的BeanPostProcessor（AnnotationAwareAspectJAutoProxyCreator）实例
 *                      internalAutoProxyCreator——》instanceWrapper = createBeanInstance(beanName, mbd, args)
 *                      AnnotationAwareAspectJAutoProxyCreator——》final Object bean = instanceWrapper.getWrappedInstance()
 *                  2.populateBean：给bean的属性赋值
 *                  3.initializeBean：初始化bean
 *                      *初始化bean的流程：
 *                          1.invokeAwareMethods：处理Aware接口的回调
 *                          2.applyBeanPostProcessorsBeforeInitialization：后置处理器的Before调用
 *                          3.invokeInitMethods：执行初始化方法
 *                          4.applyBeanPostProcessorsAfterInitialization：后置处理器的After调用
 *                  4. BeanPostProcessor（AnnotationAwareAspectJAutoProxyCreator）创建成功
 *                      AnnotationAwareAspectJAutoProxyCreator调用父类的setBeanFactory（AbstractAdvisorAutoProxyCreator.setBeanFactory）
 *                      父类的setBeanFactory调用自身的initBeanFactory
 *          5）把BeanPostProcessor注册到BeanFactory中（beanFactory.addBeanPostProcessor(postProcessor)）——————————————注册
 *   =======以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程========
 *
 *      AnnotationAwareAspectJAutoProxyCreator的后置处理器类型：InstantiationAwareBeanPostProcessor
 *   4）finishBeanFactoryInitialization(beanFactory)：完成BeanFactory初始化，创建剩下的单实例bean
 *       1）遍历获取容器中的所有的Bean，依次创建对象getBean(beanName);
 *            getBean——》doGetBean——》getSingleton
 *       2）创建bean
 *            1）先从缓存中检查手动注册的单例对象
 *                AbstractBeanFactory.doGetBean——》Object sharedInstance = getSingleton(beanName);
 *            2）createBean创建Bean
 *                [BeanPostProcessor在Bean实例创建前后调用]
 *                [InstantiationAwareBeanPostProcessor在Bean实例创建前先获取代理对象]//AnnotationAwareAspectJAutoProxyCreator
 *               1）、resolveBeforeInstantiation解析BeforeInstantiation，创建代理对象
 *                   bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);//InstantiationAwareBeanPostProcessor
 * 					 if (bean != null) {
 * 				        bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 *                    }
 *               2）、doCreateBean(3.4流程)，创建普通对象
 *
 *   5）AnnotationAwareAspectJAutoProxyCreator[InstantiationAwareBeanPostProcessor]的作用：
 *       1）Bean创建之前，调用postProcessBeforeInstantiation()
 *           关注mathCalculator、LogAspects创建
 *            1）判断当前Bean是否在advisedBeans(保存了所有需要增强的Bean)中
 *            2）判断当前Bean是否是基础类(Advice、Pointcut、Advisor、AopInfrastructureBean)或者是切面(Aspect)
 *            3）是否需要跳过
 *               1）拿到候选的增强器[List<Advisor> candidateAdvisors]，InstantiationModelAwarePointcutAdvisorImpl
 *                   如果是AspectJPointcutAdvisor返回true
 *               2）返回false
 *       2）、Bean创建之后，调用postProcessAfterInitialization()
 *           wrapIfNecessary方法会创建代理对象，返回给spring：
 *              1）、getAdvicesAndAdvisorsForBean获取当前Bean可应用的通知方法
 *              关注findEligibleAdvisors
 *                  1）、找到候选的所有通知方法
 *                  2）、获取当前Bean可应用的通知方法
 *                  3）、对通知方法进行排序
 *              2）、保存当前Bean到advisedBeans中
 *              3）、createProxy如果当前Bean需要增强，创建当前Bean的代理对象
 *                  1）、将通知方法保存到proxyFactory
 *                  2）、创建代理对象
 *                      JdkDynamicAopProxy
 *                      ObjenesisCglibAopProxy
 *       3）、以后从容器中获取到的就是这个Bean的代理对象，执行目标方法的时候，代理对象会执行通知方法
 *
 *   6）目标方法执行时的拦截器链（将每一个通知方法包装成拦截器）
 *       1）、CglibAopProxy.intercept()拦截目标方法执行
 *       2）、根据ProxyFactory获取将要执行的目标方法拦截器链
 *          this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass)
 *              1）、创建interceptorList保存拦截器链
 *              2）、遍历所有通知方法，转成Interceptor
 *                  registry.getInterceptors(advisor)的逻辑
 *                      如果是MethodInterceptor直接加入集合
 *                      如果不是，使用AdvisorAdapter将通知方法转为MethodInterceptor加入集合
 *       3）、没有拦截器链，执行目标方法
 *       4）、有拦截器链，CglibMethodInvocation.proceed()执行拦截器链
 *       5）、拦截器链的触发过程
 *              1）、如果没有拦截器执行目标方法，或者拦截器的索引等于拦截器数组-1（执行到最后一个拦截器）
 *              currentInterceptorIndex=-1
 *
 *     总结：
 *          1）、@EnableAspectJAutoProxy 开启AOP功能
 *          2）、@EnableAspectJAutoProxy 会给容器中注册一个组件AnnotationAwareAspectJAutoProxyCreator
 *          3）、AnnotationAwareAspectJAutoProxyCreator是一个后置处理器（InstantiationAwareBeanPostProcessor）
 *          4）、容器的创建流程：
 *              1）、registerBeanPostProcessors()注册后置处理器，创建AnnotationAwareAspectJAutoProxyCreator
 *              2）、finishBeanFactoryInitialization()初始化剩下的单实例Bean
 *                  1）、创建业务逻辑组件和切面组件
 *                  2）、resolveBeforeInstantiation->wrapIfNecessary
 *                      给业务组件创建代理类，将通知方法包装成增强器（Advisor）
 *          5）、执行目标方法
 *              1）、代理类执行目标方法
 *              2）、CglibAopProxy.intercept()拦截方法的执行
 *                  1）、将目标方法的增强器拦（Advisor），包装成截器链（MethodInterceptor）
 *                  2）、利用拦截器的链式机制，依次进入每个拦截器执行
 *                  3）、效果：
 *                      正常执行：前置通知-》目标方法-》后置通知-》返回通知
 *                      异常执行：前置通知-》目标方法-》后置通知-》异常通知
 *
 *
 *
 * @author lastwhisper
 */
@EnableAspectJAutoProxy // 开启基于注解的aop模式
@Configuration
public class MainConfigOfAop {

    @Bean
    public MathCalculator mathCalculator() {
        return new MathCalculator();
    }

    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }
}
