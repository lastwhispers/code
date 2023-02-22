package cn.cunchang.json;

import cn.cunchang.json.dto.ContractExceedDTO;
import cn.cunchang.json.dto.ContractRiskApproveRuleDTO;
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

    @Test
    public void test3() {
        String jsonStr = "{\"isDeleted\":\"n\",\"conditions\":[{\"order\":0,\"isDeleted\":\"n\",\"approveLevel\":\"老板DD\",\"ruleType\":\"overdue\",\"desc\":\"合同过期，财务经理逐级封顶到老板DD。\"},{\"ratioExpression\":\"ratio>1\",\"contractTypeSet\":[\"合同类型A\"],\"order\":1,\"isDeleted\":\"n\",\"approveLevel\":\"老板DD\",\"ruleType\":\"overratio\",\"desc\":\"单项目合同超框，财务经理逐级封顶到老板DD。\"},{\"ratioExpression\":\"ratio>1.2\",\"contractTypeSet\":[\"合同类型B\"],\"bizTypeNotContainSet\":[\"withdraw\"],\"order\":2,\"isDeleted\":\"n\",\"approveLevel\":\"老板DD\",\"ruleType\":\"overratio\",\"desc\":\"框架合同超框120%（大于）且非OXM类目，财务经理逐级封顶到老板DD。\"},{\"ratioExpression\":\"ratio>1.5\",\"contractTypeSet\":[\"合同类型B\"],\"bizTypeSet\":[\"withdraw\"],\"order\":3,\"isDeleted\":\"n\",\"approveLevel\":\"老板DD\",\"ruleType\":\"overratio\",\"desc\":\"框架合同超框150%（大于）且为OXM类目，采购经理逐级封顶到总裁DD。\"},{\"ratioExpression\":\"1<ratio && ratio<=1.2\",\"contractTypeSet\":[\"合同类型B\"],\"bizTypeNotContainSet\":[\"withdraw\"],\"order\":4,\"isDeleted\":\"n\",\"approveLevel\":\"老板DDD\",\"ruleType\":\"overratio\",\"desc\":\"框架合同超框100%＜x≤120%，财务经理逐级封顶到老板DDD。\"}]}";
        ContractRiskApproveRuleDTO contractRiskApproveRuleDTO = JSONObject.parseObject(jsonStr, ContractRiskApproveRuleDTO.class);
        System.out.println(contractRiskApproveRuleDTO.getIsDeleted());
        for (ContractRiskApproveRuleDTO.Condition condition : contractRiskApproveRuleDTO.getConditions()) {
            System.out.println(JSON.toJSONString(condition));
        }
    }

    @Test
    public void test4() {
        String jsonStr = "{\"contractCode\":\"S54202200048\",\"contractType\":\"common_frame\",\"currentPoPurchaseCategory\":[\"PS13\"],\"overdue\":true,\"poNumber\":\"PO200129522\",\"ratio\":0.500000000000}";
        ContractExceedDTO contractExceedDTO = JSONObject.parseObject(jsonStr, ContractExceedDTO.class);
        System.out.println(contractExceedDTO);
    }

}