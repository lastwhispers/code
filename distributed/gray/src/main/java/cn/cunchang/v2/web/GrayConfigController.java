package cn.cunchang.v2.web;

import cn.cunchang.v2.GrayHelper;
import cn.cunchang.v2.web.req.RequestConfigParam;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by qiumu on 2020/10/11.
 */

@Controller
@Slf4j(topic = "grayLog")
public class GrayConfigController {

    @Resource
    private GrayHelper grayHelper;

    @Resource
    private RedissonClient redissonClient;

    /**
     * 第一步:每台机器都进行设置灰度策略配置并加载
     * @param config
     * @return
     */
    @RequestMapping(value = {"/gray/loadRequestConfig"},method = RequestMethod.POST)
    @ResponseBody
    public String loadRequestConfig(@RequestBody RequestConfigParam config){

        boolean ok = false;
        try {
            String json = JSON.toJSONString(config);
            RBucket<String> rBucket = redissonClient.getBucket(GrayHelper.GRAY_RULE_STR_KEY);
            //保存60天
            rBucket.set(json,60 * 24 * 60 * 60, TimeUnit.SECONDS);

            ok = grayHelper.loadRule();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return "fail";
        }
        if(ok){
            return "ok";
        }
        return "fail";

    }

    /**
     * 第一步:设置灰度策略配置并加载
     * @param grayOpen
     * @return
     */
    @RequestMapping(value = {"/gray/setGrayOpen"})
    @ResponseBody
    public String setGrayOpen(@RequestParam("grayOpen") String grayOpen){

        try {
            grayHelper.setGrayOpen(grayOpen);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return "fail";
        }
        return "ok";
    }

    /**
     * 第二步:设置是否都走灰度请求
     * @param allRequestGray
     * @return
     */
    @RequestMapping(value = {"/gray/setAllRequestGray"})
    @ResponseBody
    public String setAllRequestGray(@RequestParam("allRequestGray") String allRequestGray){

        try {
            grayHelper.setAllRequestGray(allRequestGray);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return "fail";
        }
        return "ok";
    }

    /**
     * 获取请求配置
     * @return
     */
    @RequestMapping(value = {"/gray/getRequestConfig"})
    @ResponseBody
    public String getRequestConfig(){

        try {
            RBucket<String> rBucket = redissonClient.getBucket(GrayHelper.GRAY_RULE_STR_KEY);
            if(rBucket == null || rBucket.get() == null){
                return "empty";
            }
            return rBucket.get();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return "fail";
        }

    }

    /**
     * 查询灰度开关
     * @return
     */
    @RequestMapping(value = {"/gray/getGrayOpen"})
    @ResponseBody
    public Boolean getGrayOpen(){

        try {
            return grayHelper.getGrayOpen();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = {"/gray/getAllRequestGray"})
    @ResponseBody
    public Boolean getAllRequestGray(){

        try {
            return grayHelper.getAllRequestGray();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return false;
        }
    }



}
