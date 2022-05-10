package cn.cunchang.v1;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Slf4j(topic = "gray")
public class GrayHelper {

    /**
     * 灰度权重规则key
     */
    public final static String GRAY_RULE_STR_KEY = "gray_rule_str_key";

    /**
     * 灰度权重map
     */
    private static Map<Integer,InterfaceWeightGroup> groupMap = new HashMap<Integer,InterfaceWeightGroup>();


    private Map<String,String> grayRuleCache;


    /**
     * 灰度是否开启
     */
    private static boolean grayOpen = false;


    /**
     * 灰度开启的情况下是否全部走灰度
     */
    private static boolean allRequestGray = false;


    /**
     * 加载灰度权重规则
     * 配置格式:
     * {"requestConfig":[{"id":1,"grayNumber":100,"normarlNumber":9900},{"id":2,"grayNumber":1000,"normarlNumber":9000}],"allRequestGray":true,"grayOpen":true}
     */
    public void loadRule(){

        try {
            String grayRule = grayRuleCache.get(GRAY_RULE_STR_KEY);

            if(StringUtils.isBlank(grayRule)){
                log.error("gray_rule_str is null");
                return;
            }
            JSONObject  jsonObject = JSONObject.parseObject(grayRule);
            //是否开启灰度
            boolean go = jsonObject.getBoolean("grayOpen");

            grayOpen = go;

            //灰度开启后是否全部走灰度
            boolean arg = jsonObject.getBoolean("allRequestGray");

            allRequestGray = arg;

            JSONArray jsonArray = jsonObject.getJSONArray("requestConfig");
            if(CollectionUtils.isEmpty(jsonArray)){
                log.warn("requestConfig is empty");
                return;
            }
            for (Object o : jsonArray) {
                JSONObject j = (JSONObject)o;
                Integer id = j.getInteger("id");
                WeightCategory grayWeightCategory = new WeightCategory(Constant.GRAY,j.getInteger("grayNumber"));
                WeightCategory normalWeightCategory = new WeightCategory(Constant.NORMAL,j.getInteger("normarlNumber"));
                InterfaceWeightGroup interfaceWeightGroup = new InterfaceWeightGroup(id,grayWeightCategory,normalWeightCategory);
                groupMap.put(id,interfaceWeightGroup);
            }
            log.info("loadRule finish,groupMap:{}",JSONObject.toJSONString(groupMap));


        }catch (Exception e){
            log.error("load loadRule error",e);
        }

    }

    /**
     * 是否命中灰度
     * @param requestConfigId
     * @return
     */
    public boolean isGray(Integer requestConfigId){
        try {
            if(!grayOpen){
                log.info("grayOpen is false");
                return false;
            }
            if(requestConfigId == null){
                return false;
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

    public static void main(String[] args) {

        JSONArray jsonArray = new JSONArray();
        for (int i =0; i < 3 ;i ++) {
            JSONObject requestConfig1 = new JSONObject();
            requestConfig1.put("id",i +1);
            requestConfig1.put("grayNumber",100);
            requestConfig1.put("normalNumber",9900);
            jsonArray.add(requestConfig1);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("requestConfigList",jsonArray);

        System.out.println(jsonArray);



        //JSONArray jsonArray = JSONObject.parseArray(rBucket.get());
        if(CollectionUtils.isEmpty(jsonArray)){
            log.warn("requestConfig is empty");
            return;
        }
        for (Object o : jsonArray) {
            JSONObject j = (JSONObject)o;
            Integer id = j.getInteger("id");
            WeightCategory grayWeightCategory = new WeightCategory(Constant.GRAY,j.getInteger(Constant.GRAY_NUMBER));
            WeightCategory normalWeightCategory = new WeightCategory(Constant.NORMAL,j.getInteger(Constant.NORMAL_NUMBER));
            InterfaceWeightGroup interfaceWeightGroup = new InterfaceWeightGroup(id,grayWeightCategory,normalWeightCategory);
            groupMap.put(id,interfaceWeightGroup);
        }
        log.info("loadRule finish,groupMap={}",JSONObject.toJSONString(groupMap));
    }



}
