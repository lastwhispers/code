package cn.cunchang.json.biz;

import cn.cunchang.json.dto.ContractExceedDTO;
import cn.cunchang.json.dto.ContractRiskApproveRuleDTO;
import com.google.common.collect.Sets;
import org.apache.commons.collections.MapUtils;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author kaisui
 * @description
 * @date 2022/12/8
 */
public class BizTest {

    Biz biz = new Biz();

    // 单项目未过期
    @Test
    public void test1(){
        ContractExceedDTO contractExceedDTO = new ContractExceedDTO();
        contractExceedDTO.setContractCode("1");
        contractExceedDTO.setContractType("common_frame");
        contractExceedDTO.setPoNumber("1");
        contractExceedDTO.setCurrentPoPurchaseCategory(Sets.newHashSet("PS1"));
        contractExceedDTO.setOverdue(false);
        contractExceedDTO.setRatio(new BigDecimal("1"));

        Map<String, ContractRiskApproveRuleDTO.Condition> matchRuleMap = biz.matchRule(contractExceedDTO);
        Assert.assertTrue(MapUtils.isEmpty(matchRuleMap));
    }
    // 单项目过期
    @Test
    public void test2(){
        ContractExceedDTO contractExceedDTO = new ContractExceedDTO();
        contractExceedDTO.setContractCode("1");
        contractExceedDTO.setContractType("common_frame");
        contractExceedDTO.setPoNumber("1");
        contractExceedDTO.setCurrentPoPurchaseCategory(Sets.newHashSet("PS1"));
        contractExceedDTO.setOverdue(true);
        contractExceedDTO.setRatio(new BigDecimal("1"));

        Map<String, ContractRiskApproveRuleDTO.Condition> matchRuleMap = biz.matchRule(contractExceedDTO);
        Assert.assertEquals(0, (int) matchRuleMap.get(RuleTypeEnum.OVER_DUE.getCode()).getOrder());
        Assert.assertNull(matchRuleMap.get(RuleTypeEnum.OVER_RATIO.getCode()));
    }

    // 单项目超框
    @Test
    public void test3(){
        ContractExceedDTO contractExceedDTO = new ContractExceedDTO();
        contractExceedDTO.setContractCode("1");
        contractExceedDTO.setContractType("common_frame");
        contractExceedDTO.setPoNumber("1");
        contractExceedDTO.setCurrentPoPurchaseCategory(Sets.newHashSet("PS1"));
        contractExceedDTO.setOverdue(false);
        contractExceedDTO.setRatio(new BigDecimal("1.0000001"));

        Map<String, ContractRiskApproveRuleDTO.Condition> matchRuleMap = biz.matchRule(contractExceedDTO);
        Assert.assertEquals(1, (int) matchRuleMap.get(RuleTypeEnum.OVER_RATIO.getCode()).getOrder());
        Assert.assertNull(matchRuleMap.get(RuleTypeEnum.OVER_DUE.getCode()));
    }

    // 单项目过期+超框1.0000001
    @Test
    public void test4(){
        ContractExceedDTO contractExceedDTO = new ContractExceedDTO();
        contractExceedDTO.setContractCode("1");
        contractExceedDTO.setContractType("common_frame");
        contractExceedDTO.setPoNumber("1");
        contractExceedDTO.setCurrentPoPurchaseCategory(Sets.newHashSet("PS1"));
        contractExceedDTO.setOverdue(true);
        contractExceedDTO.setRatio(new BigDecimal("1.0000001"));

        Map<String, ContractRiskApproveRuleDTO.Condition> matchRuleMap = biz.matchRule(contractExceedDTO);
        Assert.assertEquals(0, (int) matchRuleMap.get(RuleTypeEnum.OVER_DUE.getCode()).getOrder());
        Assert.assertEquals(1, (int) matchRuleMap.get(RuleTypeEnum.OVER_RATIO.getCode()).getOrder());
    }
    // 框架合同过期
    @Test
    public void test5(){
        ContractExceedDTO contractExceedDTO = new ContractExceedDTO();
        contractExceedDTO.setContractCode("1");
        contractExceedDTO.setContractType("price_frame");
        contractExceedDTO.setPoNumber("1");
        contractExceedDTO.setCurrentPoPurchaseCategory(Sets.newHashSet("PS1"));
        contractExceedDTO.setOverdue(true);
        contractExceedDTO.setRatio(new BigDecimal("1.0000000"));

        Map<String, ContractRiskApproveRuleDTO.Condition> matchRuleMap = biz.matchRule(contractExceedDTO);
        Assert.assertEquals(0, (int) matchRuleMap.get(RuleTypeEnum.OVER_DUE.getCode()).getOrder());
        Assert.assertNull(matchRuleMap.get(RuleTypeEnum.OVER_RATIO.getCode()));
    }
    // 框架合同过期+超框1.0000001
    @Test
    public void test6(){
        ContractExceedDTO contractExceedDTO = new ContractExceedDTO();
        contractExceedDTO.setContractCode("1");
        contractExceedDTO.setContractType("price_frame");
        contractExceedDTO.setPoNumber("1");
        contractExceedDTO.setCurrentPoPurchaseCategory(Sets.newHashSet("PS1"));
        contractExceedDTO.setOverdue(true);
        contractExceedDTO.setRatio(new BigDecimal("1.0000001"));

        Map<String, ContractRiskApproveRuleDTO.Condition> matchRuleMap = biz.matchRule(contractExceedDTO);
        Assert.assertEquals(0, (int) matchRuleMap.get(RuleTypeEnum.OVER_DUE.getCode()).getOrder());
        Assert.assertNull(matchRuleMap.get(RuleTypeEnum.OVER_RATIO.getCode()));
    }
}
