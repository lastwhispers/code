package cn.cunchang.v2;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qiumu on 2020/10/10.
 */
@Slf4j(topic = "grayLog")
@Service("grayHelper")
public class GrayHelper {

    /**
     * 灰度权重规则key
     */
    public final static String GRAY_RULE_STR_KEY = "gray_rule_str_key";


    /**
     * 灰度开关key
     */
    public final static String GRAY_OPEN_KEY = "gray_open";


    /**
     * 灰度权重规则key
     */
    public final static String ALL_REQUEST_GRAY_KEY = "all_request_gray";


    /**
     * 灰度用户ID列表
     */
    public final static String GRAY_USER_ID_LIST = "grayUserIdList";

    /**
     * 请求配置列表
     */
    public final static String REQUEST_CONFIG_LIST = "requestConfigList";

    /**
     * 灰度权重map
     */
    private static Map<Integer,InterfaceWeightGroup> groupMap = new HashMap<Integer,InterfaceWeightGroup>();


    /**
     * 白名单用户ID列表
     */
    private static List<Integer> userIdList = new ArrayList<Integer>();

    @Resource
    private RedissonClient redissonClient;

    /**
     * 设置灰度是否开启
     * @param grayOpen
     */
    public void setGrayOpen(String grayOpen){

        try {
            RBucket<Boolean> rBucket = redissonClient.getBucket(GRAY_OPEN_KEY);
            rBucket.set(Boolean.valueOf(grayOpen));
        }catch (Exception e){
            log.error("error to setGrayOpen",e);
        }
    }

    /**
     * 设置灰度是否开启
     * @param allRequestGray
     */
    public void setAllRequestGray(String allRequestGray){

        try {
            RBucket<Boolean> rBucket = redissonClient.getBucket(ALL_REQUEST_GRAY_KEY);
            rBucket.set(Boolean.valueOf(allRequestGray));
        }catch (Exception e){
            log.error("error to setAllRequestGray",e);
        }
    }

    /**
     * 获取是否开启灰度
     * @return
     */
    public Boolean getGrayOpen(){

        try {
            RBucket<Boolean> rBucket = redissonClient.getBucket(GRAY_OPEN_KEY);
            if(rBucket == null || rBucket.get()==null){
                return false;
            }
            return rBucket.get();
        }catch (Exception e){
            log.error("error to getGrayOpen",e);
        }
        return false;
    }


    /**
     * 获取是否全部请求走灰度
     * @return
     */
    public Boolean getAllRequestGray(){

        try {
            RBucket<Boolean> rBucket = redissonClient.getBucket(ALL_REQUEST_GRAY_KEY);
            if(rBucket == null || rBucket.get()==null){
                return false;
            }
            return rBucket.get();
        }catch (Exception e){
            log.error("error to getAllRequestGray",e);
        }
        return false;
    }




    /**
     * 加载灰度权重规则,grayUserIdList为白名单,白名单不为空,则优先走白名单策略,否则走接口权重策略。requestConfigList为接口灰度权重访问策略。
     * 配置格式:
     * {"grayUserIdList":[12323,12323,2323,23244],"requestConfigList":[{"normalNumber":80,"id":1,"grayNumber":20},{"normalNumber":80,"id":2,"grayNumber":20},{"normalNumber":80,"id":3,"grayNumber":20}]}
     */
    /**
     * 启动后自动加载
     * @return
     */
    @PostConstruct
    public synchronized boolean loadRule(){

        try {
            RBucket<String> rBucket = redissonClient.getBucket(GRAY_RULE_STR_KEY);

            if(rBucket == null || StringUtils.isBlank(rBucket.get())){
                log.error("gray_rule_str is null");
                return false;
            }
            //灰度策略json字符串
            String config = rBucket.get();
            log.info("config={}",config);
            JSONObject jsonObject = JSONObject.parseObject(config);

            //设置白名单
            JSONArray grayUserArrayList = jsonObject.getJSONArray(GRAY_USER_ID_LIST);

            //先清空在重新添加
            userIdList.clear();
            //不为空则重新添加
            if(!CollectionUtils.isEmpty(grayUserArrayList)){

                for (Object userId : grayUserArrayList){
                    userIdList.add((Integer)userId);
                }
            }


            //设置groupMap
            JSONArray jsonArray = jsonObject.getJSONArray(REQUEST_CONFIG_LIST);
            if(CollectionUtils.isEmpty(jsonArray)){
                log.warn("requestConfig is empty");
                return false;
            }
            for (Object o : jsonArray) {
                JSONObject j = (JSONObject)o;
                Integer id = j.getInteger("id");
                WeightCategory grayWeightCategory = new WeightCategory(Constant.GRAY,j.getInteger(Constant.GRAY_NUMBER));
                WeightCategory normalWeightCategory = new WeightCategory(Constant.NORMAL,j.getInteger(Constant.NORMAL_NUMBER));
                InterfaceWeightGroup interfaceWeightGroup = new InterfaceWeightGroup(id,grayWeightCategory,normalWeightCategory);
                groupMap.put(id,interfaceWeightGroup);
            }
            log.info("loadRule finish,userIdList={}",JSONObject.toJSONString(userIdList));
            log.info("loadRule finish,groupMap={}",JSONObject.toJSONString(groupMap));


        }catch (Exception e){
            log.error("load loadRule error",e);
            return false;
        }

        return true;
    }

    /**
     * 是否命中灰度
     * @param requestConfigId
     * @return
     */
    public boolean isGray(Integer requestConfigId){
        try {
            //如果灰度关闭则返回false
            if(!getGrayOpen()){
                log.info("grayOpen is false");
                return false;
            }
            //如果灰度打开,并且所有请求都走灰度则返回true
            if(getAllRequestGray()){
                log.info("access gray interface,requestConfigId={}",requestConfigId);
                return true;
            }
            if(requestConfigId == null){
                return false;
            }

            if(MapUtils.isEmpty(groupMap)){
                loadRule();
            }

            InterfaceWeightGroup interfaceWeightGroup = groupMap.get(requestConfigId);
            //找不到配置,则返回false
            if(interfaceWeightGroup == null){
                return false;
            }
            return interfaceWeightGroup.accessGray();
        }catch (Exception e){
            log.error("error to isGray",e);
            return false;
        }

    }

    /**
     * 是否命中灰度
     * @param requestConfigId 接口配置ID
     * @param userId 用户ID
     * @return
     */
    public boolean isGray(Integer requestConfigId,Integer userId){
        try {
            //如果灰度关闭则返回false
            if(!getGrayOpen()){
                log.info("grayOpen is false");
                return false;
            }
            //如果灰度打开,并且所有请求都走灰度则返回true
            if(getAllRequestGray()){
                log.info("access gray interface,requestConfigId={}",requestConfigId);
                return true;
            }

            if(requestConfigId == null){
                return false;
            }

            //如果用户白名单策略不为空,则优先走白名单判断
            if(!CollectionUtils.isEmpty(userIdList)){
                if(userIdList.contains(userId)){
                    log.info("access gray interface,requestConfigId={},userId={}",requestConfigId,userId);
                    return true;
                }
                return false;
            }

            if(MapUtils.isEmpty(groupMap)){
                loadRule();
            }

            InterfaceWeightGroup interfaceWeightGroup = groupMap.get(requestConfigId);
            //找不到配置,则返回false
            if(interfaceWeightGroup == null){
                return false;
            }
            return interfaceWeightGroup.accessGray();
        }catch (Exception e){
            log.error("error to isGray",e);
            return false;
        }

    }

    public static void main(String[] args){
        JSONArray jsonArray = new JSONArray(); 

            JSONObject requestConfig1 = new JSONObject();
        requestConfig1.put("id",1);
        requestConfig1.put("grayNumber",100);
        requestConfig1.put("normalNumber",9900);

        JSONObject requestConfig2 = new JSONObject();
        requestConfig2.put("id",2);
        requestConfig2.put("grayNumber",1000);
        requestConfig2.put("normalNumber",9000);

        jsonArray.add(requestConfig1);
        jsonArray.add(requestConfig2);


        System.out.println(jsonArray);
    }



}
