package cn.cunchang.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import org.junit.Test;

import java.util.HashMap;
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
        Map<String, List<String>> map2 = new HashMap<>();
        String jsonMap2 = "{\"BUSINESS_CONTACT\":[{\"sourceTypes\":[\"XXX1\",\"XXX2\"],\"supplierList\":[\"1\",\"2\"]},{\"sourceTypes\":[\"AAA1\",\"AAA2\"],\"supplierList\":[\"4\",\"5\"]}]}";
        JSONObject jsonObject = JSONObject.parseObject(jsonMap2);
        String jsonArrStr = jsonObject.getString("BUSINESS_CONTACT");
        List<Map<String, List<String>>> list1 = JSONObject.parseObject(jsonArrStr, new TypeReference<List<Map<String, List<String>>>>() {
        });
        for (Map<String, List<String>> kvList : list1) {
            List<String> sourceTypes = kvList.get("sourceTypes");
            for (String sourceType : sourceTypes) {
                map2.put(sourceType, kvList.get("supplierList"));
            }
        }
        System.out.println(JSON.toJSONString(map2));
    }


}