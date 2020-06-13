package cn.lastwhisper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 请求参数的封装分析
 */
@Controller
public class ParameterController1 {

    /**
     * 请求参数封装的分析(通过HandlerMethodArgumentResolver接口下的各种实现类)
     *
     * 请求调用链
     * DispatcherServlet.doDispatch
     *   AbstractHandlerMethodAdapter.handle
     *      RequestMappingHandlerAdapter.handleInternal
     *      invokeHandlerMethod
     *          ServletInvocableHandlerMethod.invokeAndHandle
     *              InvocableHandlerMethod.invokeForRequest
     *              getMethodArgumentValues
     *                  RequestParamMethodArgumentResolver.resolveArgument(AbstractNamedValueMethodArgumentResolver.resolveArgument)
     *                  resolveName
     *
     * HandlerMethodArgumentResolver策略模式
     * @param username
     * @param age
     */
    @RequestMapping("testParam1")
    public String testParam(String username, Integer age) {
        System.out.println(username + "," + age);
        return "success";
    }

    /**
     * RequestParam的封装分析
     * RequestParamMethodArgumentResolver的resolveName
     */
    @RequestMapping("testParam2")
    public String testParam2(@RequestParam("username") String name, Integer age) {
        System.out.println(name + "," + age);
        return "success";
    }


    /**
     * RequestBody的封装分析
     * RequestResponseBodyMethodProcessor.resolveArgument
     *  readWithMessageConverters
     *
     */
    @RequestMapping("testParam3")
    public void testParam3(@RequestBody String body) {
        System.out.println(body);
    }

    /**
     * PathVariable的封装分析
     * PathVariableMethodArgumentResolver.resolveName
     *  handleResolvedValue
     */
    @RequestMapping("testParam4/{username}/{age}")
    public void testParam4(@PathVariable("username") String username, @PathVariable Integer age){
        System.out.println(username+","+age);
    }



}