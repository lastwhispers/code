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
 *  1）@EnableAspectJAutoProxy注解的功能（看给容器中注册了什么组件）
 *      导入组件@Import(AspectJAutoProxyRegistrar.class)
 *          AspectJAutoProxyRegistrar实现了ImportBeanDefinitionRegistrar接口
 *              利用AspectJAutoProxyRegistrar自定义给容器注册bean
 *          给容器中注册了一个AnnotationAwareAspectJAutoProxyCreator组件，组件名称叫做org.springframework.aop.config.internalAutoProxyCreator
 *
 *  2）AnnotationAwareAspectJAutoProxyCreator（看给容器中注册了什么组件）
 *      AnnotationAwareAspectJAutoProxyCreator
 *          ——》父类 AspectJAwareAdvisorAutoProxyCreator
 *              ——》父类 AbstractAdvisorAutoProxyCreator
 *                  ——》父类 AbstractAutoProxyCreator
 *                      implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *                      重点关注后置处理器（在bean初始化完成前后做事情）、获取BeanFactory
 *
 *      在整个继承结构中，给关注点设置断点
 *      AbstractAutoProxyCreator.setBeanFactory(BeanFactoryAware)，重写了setBeanFactory()
 *      AbstractAutoProxyCreator.postProcessAfterInitialization、postProcessBeforeInstantiation等有后置处理器的逻辑
 *
 *      AbstractAdvisorAutoProxyCreator.setBeanFactory()——》initBeanFactory，重写了setBeanFactory()
 *
 *      AspectJAwareAdvisorAutoProxyCreator没有需要关注的点
 *
 *      AnnotationAwareAspectJAutoProxyCreator.initBeanFactory
 *
 *  3）AnnotationAwareAspectJAutoProxyCreator创建和注册的过程（这个组件什么时候工作）
 *      1）传入配置类，创建IOC容器
 *      2）注册配置类，AbstractApplicationContext.refresh()刷新容器
 *      3）PostProcessorRegistrationDelegate.registerBeanPostProcessors：注册bean的后置处理器来方便拦截bean的创建
 *         *注册bean的后置处理器的流程：
 *          1）获取IOC容器中已经定义的（还未创建），需要创建对象的所有BeanPostProcessor
 *          2）给容器中加入别的BeanPostProcessor
 *          3）分离BeanPostProcessor，分别注册BeanPostProcessor
 *              1.First, register the BeanPostProcessors that implement PriorityOrdered
 *              2.Next, register the BeanPostProcessors that implement Ordered.
 *              3.Now, register all regular BeanPostProcessors.
 *          4）注册BeanPostProcessor，实际上就是创建BeanPostProcessor对象，保存在容器中——————————————创建
 *              由于AnnotationAwareAspectJAutoProxyCreator实现了Ordered与xxxBeanPostProcessor接口在（3）的2步骤开始注册
 *              *AnnotationAwareAspectJAutoProxyCreator注册流程（BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);）
 *                  1.创建名称为internalAutoProxyCreator的BeanPostProcessor（AnnotationAwareAspectJAutoProxyCreator）实例
 *                      internalAutoProxyCreator——》instanceWrapper = createBeanInstance(beanName, mbd, args)
 *                      AnnotationAwareAspectJAutoProxyCreator——》final Object bean = instanceWrapper.getWrappedInstance()
 *                  2.populateBean：给bean的属性赋值
 *                  3.initializeBean：初始化bean
 *                      *初始化bean的流程：
 *                          1.invokeAwareMethods：处理Aware接口的回调
 *                          2.applyBeanPostProcessorsBeforeInitialization：后置处理器的Before的回调
 *                          3.invokeInitMethods：执行初始化方法
 *                          4.applyBeanPostProcessorsAfterInitialization：后置处理器的After的回调
 *                  4. BeanPostProcessor（AnnotationAwareAspectJAutoProxyCreator）创建成功
 *                      AnnotationAwareAspectJAutoProxyCreator调用父类的setBeanFactory（AbstractAdvisorAutoProxyCreator.setBeanFactory）
 *                      父类的setBeanFactory调用自身的initBeanFactory
 *          5）registerBeanPostProcessors 把BeanPostProcessor注册到BeanFactory中（PostProcessorRegistrationDelegate.addBeanPostProcessor(postProcessor)）——————————————注册
 *   =======以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程========
 *
 *      AnnotationAwareAspectJAutoProxyCreator的后置处理器类型：InstantiationAwareBeanPostProcessor
 *      下面看看InstantiationAwareBeanPostProcessor的调用时机
 *   4）finishBeanFactoryInitialization(beanFactory)：完成BeanFactory初始化，创建剩下的单实例bean
 *       1）finishBeanFactoryInitialization——》preInstantiateSingletons
 *       遍历获取容器中的所有的beanDefinitionNames，依次创建FactoryBean和普通Bean
 *            getBean——》doGetBean——》getSingleton
 *       2）getSingleton() 创建bean
 *            1）先从缓存中检查手动注册的单例对象
 *                AbstractBeanFactory.doGetBean——》getSingleton(beanName);
 *            2）缓存没有就走createBean()创建Bean
 *                [BeanPostProcessor在Bean实例创建前后调用]
 *                [InstantiationAwareBeanPostProcessor在Bean实例创建前先获取代理对象]//AnnotationAwareAspectJAutoProxyCreator
 *               1）、resolveBeforeInstantiation解析BeforeInstantiation，创建代理对象
 *                   bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 *                   在这里会处理InstantiationAwareBeanPostProcessor，回调AbstractAutoProxyCreator.postProcessBeforeInstantiation方法
 * 					 if (bean != null) {
 * 				        bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 *                    }
 *               2）、代理对象创建失败的话，doCreateBean(3.3.4流程)，创建普通对象
 *  =======以上是InstantiationAwareBeanPostProcessor的调用时机========
 *
 *     下面看看InstantiationAwareBeanPostProcessor被调用时主要干什么事情（创建代理对象，将通知方法，包装成拦截器）
 *   5）AnnotationAwareAspectJAutoProxyCreator[InstantiationAwareBeanPostProcessor]的作用：
 *       1）Bean创建之前，调用postProcessBeforeInstantiation()
 *            1）判断当前Bean是否在advisedBeans(保存了所有需要增强的Bean)中
 *            2）判断当前Bean是否是基础类(Advice、Pointcut、Advisor、AopInfrastructureBean)或者是切面(是否有@Aspect，有就是切面)
 *            3）是否需要跳过
 *               1）拿到候选的增强器，就是切面里面的通知方法 [List<Advisor> candidateAdvisors]，增强器类型 InstantiationModelAwarePointcutAdvisor
 *                   如果是AspectJPointcutAdvisor返回true
 *               2）父类返回false
 *       2）、Bean创建之后，调用postProcessAfterInitialization()
 *           关注mathCalculator、LogAspects创建
 *           wrapIfNecessary方法会创建代理对象，返回给spring：
 *              1）、getAdvicesAndAdvisorsForBean获取当前Bean可应用的通知方法
 *              关注findEligibleAdvisors
 *                  1）、找到候选的所有通知方法。findCandidateAdvisors();所有切面都在这里面
 *                  2）、获取可应用到当前Bean的通知方法。findAdvisorsThatCanApply();
 *                      AopUtils.findAdvisorsThatCanApply这里面有切点表达式的匹配代码
 *                  3）、sortAdvisors(eligibleAdvisors)对通知方法进行排序，根据这排序器AnnotationAwareOrderComparator
 *              2）、保存当前Bean到advisedBeans(保存了所有需要增强的Bean)中——有待商议
 *              3）、createProxy()如果当前Bean需要增强，创建当前Bean的代理对象
 *                  1）、将通知方法Advisor保存到ProxyFactory中，默认会添加一个ExposeInvocationInterceptor，用于记录当前调用的MethodInvocation
 *                  2）、proxyFactory.getProxy()创建代理对象
 *                      createAopProxy()创建JdkDynamicAopProxy或者ObjenesisCglibAopProxy
 *                      然后JdkDynamicAopProxy.getProxy()获取代理类
 *                  3）、将代理类放到proxyTypes(key:Bean,value:代理类)中
 *       3）、以后从容器中获取到的就是这个Bean的代理对象，执行目标方法的时候，代理对象会执行通知方法
 *
 *  =======以上是InstantiationAwareBeanPostProcessor被调用时干什么事情========
 *      下面看看代理对象创建好了，我们在Spring获取Bean组件，并且调用方法时，增强方法是怎么工作的
 *
 *   6）目标方法执行时的拦截器链（将每一个通知方法包装成拦截器）
 *          断点放到Bean调用方法的地方，强制步入-步出，强制步入-步出，强制步入-步出，就会进入下面的方法
 *       1）、CglibAopProxy.intercept()拦截目标方法执行
 *       2）、根据ProxyFactory获取将要执行的目标方法拦截器链
 *          this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass)
 *          this.advised的实现类是ProxyFactory
 *              1）、创建interceptorList保存拦截器链
 *              2）、遍历所有通知方法，转成Interceptor，registry.getInterceptors(advisor)
 *                      如果是MethodInterceptor直接加入集合
 *                      如果不是，使用AdvisorAdapter将通知方法转为MethodInterceptor加入集合
 *       3）、没有拦截器链，执行目标方法
 *       4）、有拦截器链，CglibMethodInvocation.proceed()执行拦截器链
 *       5）、proceed()拦截器链的触发过程
 *              1）、如果拦截器的索引等于拦截器数组-1（最后一个拦截器调用完成或者一开始就没有拦截器），直接执行目标方法
 *              2）、如果jdk动态代理
 *              3）、如果cglib动态代理，MethodInterceptor.invoke(this);
 *                  这里的MethodInterceptor是所有的通知方法，this是CglibMethodInvocation
 *
 *              ExposeInvocationInterceptor，用于记录当前调用的MethodInvocation
 *
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
