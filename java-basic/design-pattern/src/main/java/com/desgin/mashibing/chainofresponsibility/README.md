
责任链实现
v1：多个过滤器逻辑写在一起
问题：如何支持可配置化拦截器？
v2：各个过滤器责任单一
问题：如何支持可配置化拦截器？
v3：通过一个上下文类，支持配置化
v4：
v4、v5：本质上差不多，但是v5通过模板方法，固定调用了流程，降低了使用门槛

