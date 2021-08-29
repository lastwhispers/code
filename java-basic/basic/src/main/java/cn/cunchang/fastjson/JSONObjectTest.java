package cn.cunchang.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.junit.Test;

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

    
}
