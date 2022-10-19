package cn.cunchang.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author cunchang
 * @date 2021/6/24 10:37 上午
 */
public class JSONObjectTest {


    @Test
    public void testOrder() {
        String str = "{\"S0209\":\"#S0201#+#S0203#+#S0205#+#S0207#\",\"S0210\":\"#S0202#+#S0204#+#S0206#+#S0208#\",\"S0212\":\"#S0211#/#S0210#\",\"S0213\":\"#S0210#+#S0211#\",\"S0214\":\"#S0213#/#S0209#\"}";
        JSONObject jsonObject = JSONObject.parseObject(str, Feature.OrderedField);
//        JSONObject jsonObject = JSONObject.parseObject(str);
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            System.out.println(entry);
        }
    }

    @Test
    public void test2() {
        String json2Map = "{\"categoryCode\":[\"SB01\",\"KF03\",\"KF02\",\"VES1\",\"OI13\"],\"deptNo\":[\"60951\",\"87791\",\"88123\"]}";
        String json2List = "[\"SB01\",\"KF03\",\"KF02\",\"VES1\",\"OI13\"]";

        Map<String,List<String>> map = JSONObject.parseObject(json2Map, new TypeReference<Map<String,List<String>>>() {});
        System.out.println(JSON.toJSONString(map));
        List<String> list = JSONObject.parseObject(json2List, new TypeReference<List<String>>() {});
        System.out.println(JSON.toJSONString(list));
    }
}
