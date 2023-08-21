package cn.cunchang.json.biz;

import cn.cunchang.json.dto.ContractExceedDTO;
import cn.cunchang.json.dto.ContractRiskApproveRuleDTO;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author kaisui
 * @description
 * @date 2022/12/8
 */
@Slf4j
public class Biz {

    static String jsonConfig="{\"isDeleted\":\"n\",\"conditions\":[{\"order\":0,\"isDeleted\":\"n\",\"approveType\":\"TYPE_LEVEL_BY_LEVEL_TO_PRESIDENT_DD\",\"ruleType\":\"overdue\",\"approveNodeTip\":\"OVER_DUE_STEP_BY_STEP\",\"detailTip\":\"CONTRACT_OVERDUE\",\"desc\":\"合同过期，采购经理逐级封顶到总裁DD。\"},{\"ratioExpression\":\"ratio>1\",\"contractTypeSet\":[\"common_frame\"],\"order\":1,\"isDeleted\":\"n\",\"approveType\":\"TYPE_LEVEL_BY_LEVEL_TO_PRESIDENT_DD\",\"ruleType\":\"overratio\",\"approveNodeTip\":\"OVERRATIO_STEP_BY_STEP_100\",\"detailTip\":\"CONTRACT_OVERRATIO_100\",\"desc\":\"单项目合同超框，采购经理逐级封顶到总裁DD。\"},{\"ratioExpression\":\"ratio>1.2\",\"contractTypeSet\":[\"price_frame\"],\"purchaseCategoryNotContainSet\":[\"OXM\"],\"order\":2,\"isDeleted\":\"n\",\"approveType\":\"TYPE_LEVEL_BY_LEVEL_TO_PRESIDENT_DD\",\"ruleType\":\"overratio\",\"approveNodeTip\":\"OVERRATIO_STEP_BY_STEP_120\",\"detailTip\":\"CONTRACT_OVERRATIO_120\",\"desc\":\"框架合同超框120%（大于）且非OXM类目，采购经理逐级封顶到总裁DD。\"},{\"ratioExpression\":\"ratio>1.5\",\"contractTypeSet\":[\"price_frame\"],\"purchaseCategorySet\":[\"OXM\"],\"order\":3,\"isDeleted\":\"n\",\"approveType\":\"TYPE_LEVEL_BY_LEVEL_TO_PRESIDENT_DD\",\"ruleType\":\"overratio\",\"approveNodeTip\":\"OVERRATIO_STEP_BY_STEP_150\",\"detailTip\":\"CONTRACT_OVERRATIO_150\",\"desc\":\"框架合同超框150%（大于）且为OXM类目，采购经理逐级封顶到总裁DD。\"},{\"ratioExpression\":\"1<ratio && ratio<=1.2\",\"contractTypeSet\":[\"price_frame\"],\"purchaseCategoryNotContainSet\":[\"OXM\"],\"order\":4,\"isDeleted\":\"n\",\"approveType\":\"TYPE_LEVEL_BY_LEVEL_TO_PRESIDENT_DDD\",\"ruleType\":\"overratio\",\"approveNodeTip\":\"OVERRATIO_STEP_BY_STEP_100\",\"detailTip\":\"CONTRACT_OVERRATIO_100\",\"desc\":\"框架合同超框100%＜x≤120%，采购经理逐级封顶到总裁DDD。\"},{\"ratioExpression\":\"1<ratio && ratio<=1.5\",\"contractTypeSet\":[\"price_frame\"],\"purchaseCategorySet\":[\"OXM\"],\"order\":5,\"isDeleted\":\"n\",\"approveType\":\"TYPE_LEVEL_BY_LEVEL_TO_PRESIDENT_DDD\",\"ruleType\":\"overratio\",\"approveNodeTip\":\"OVERRATIO_STEP_BY_STEP_100\",\"detailTip\":\"CONTRACT_OVERRATIO_100\",\"desc\":\"框架合同超框100%＜x≤150%且为OXM类目，采购经理逐级封顶到总裁DDD。\"}]}";

    ExpressionParser expressionParser = new SpelExpressionParser();

    public Map<String,ContractRiskApproveRuleDTO.Condition> matchRule(ContractExceedDTO contractExceedDTO) {
        Map<String,ContractRiskApproveRuleDTO.Condition> result = new HashMap<>();
        ContractRiskApproveRuleDTO contractRiskApproveRule = JSON.parseObject(jsonConfig,ContractRiskApproveRuleDTO.class);
        List<ContractRiskApproveRuleDTO.Condition> conditions = contractRiskApproveRule.getConditions();
        if (CollectionUtils.isEmpty(conditions)) {
            log.warn("matchRule() 合同过期超框规则为空 => 【contractRiskApproveRule = {}】", JSON.toJSONString(contractRiskApproveRule));
            return result;
        }
        for (ContractRiskApproveRuleDTO.Condition condition : conditions) {
            if (condition.getRuleType().equals(RuleTypeEnum.OVER_DUE.getCode())) {
                if (!contractExceedDTO.getOverdue()) {
                    log.info("matchRule() 合同过期规则未匹配上,因为当前合同未过期 => 【contractExceedVO = {}】,【condition ={}】",
                            JSON.toJSONString(contractExceedDTO), JSON.toJSONString(condition));
                    continue;
                }
            } else if (condition.getRuleType().equals(RuleTypeEnum.OVER_RATIO.getCode())) {
                if(StringUtils.isNotBlank(condition.getRatioExpression())){
                    String ratioExpression = condition.getRatioExpression().replaceAll("ratio", contractExceedDTO.getRatio().toString());
                    if(Boolean.FALSE.equals(expressionParser.parseExpression(ratioExpression).getValue(Boolean.class))){
                        log.info("matchRule() 合同超框规则未匹配上,因为当前比率:{}不满足规则比率:{} => 【contractExceedVO = {}】,【condition ={}】", contractExceedDTO.getRatio(),condition.getRatioExpression(),
                                JSON.toJSONString(contractExceedDTO), JSON.toJSONString(condition));
                        continue;
                    }
                }
                if(!condition.getContractTypeSet().contains(contractExceedDTO.getContractType())){
                    log.info("matchRule() 合同超框规则未匹配上,因为当前合同类型:{}不满足规则合同类型:{} => 【contractExceedVO = {}】,【condition ={}】", contractExceedDTO.getContractType(),JSON.toJSONString(condition.getContractTypeSet()),
                            JSON.toJSONString(contractExceedDTO), JSON.toJSONString(condition));
                    continue;
                }
                if (CollectionUtils.isNotEmpty(condition.getPurchaseCategorySet())) {
                    // po 1、2、4；规则 4、5、6
                    Set<String> rulePurchaseCategorySet = new HashSet<>(condition.getPurchaseCategorySet());
                    rulePurchaseCategorySet.removeAll(contractExceedDTO.getCurrentPoPurchaseCategory());
                    if (rulePurchaseCategorySet.size() == condition.getPurchaseCategorySet().size()) {
                        log.info("matchRule() 合同超框规则未匹配上,因为当前po的采购类目:{}不匹配规则采购类目:{} => 【contractExceedVO = {}】,【condition ={}】", JSON.toJSONString(contractExceedDTO.getCurrentPoPurchaseCategory()), JSON.toJSONString(condition.getPurchaseCategorySet()),
                                JSON.toJSONString(contractExceedDTO), JSON.toJSONString(condition));
                        continue;
                    }
                }
                if (CollectionUtils.isNotEmpty(condition.getPurchaseCategoryNotContainSet())) {
                    // po 1、2、4；not Contain 规则 4、5、6
                    Set<String> rulePurchaseCategoryNotContainSet = new HashSet<>(condition.getPurchaseCategoryNotContainSet());
                    rulePurchaseCategoryNotContainSet.removeAll(contractExceedDTO.getCurrentPoPurchaseCategory());
                    if (rulePurchaseCategoryNotContainSet.size() != condition.getPurchaseCategoryNotContainSet().size()) {
                        log.info("matchRule() 合同超框规则未匹配上,因为当前po的采购类目:{}不匹配规则采购类目:{} => 【contractExceedVO = {}】,【condition ={}】", JSON.toJSONString(contractExceedDTO.getCurrentPoPurchaseCategory()), JSON.toJSONString(condition.getPurchaseCategorySet()),
                                JSON.toJSONString(contractExceedDTO), JSON.toJSONString(condition));
                        continue;
                    }
                }
            }
            log.info("matchRule() 合同超框规则匹配成功 => 【contractExceedVO = {}】,【condition ={}】",
                    JSON.toJSONString(contractExceedDTO), JSON.toJSONString(condition));
            ContractRiskApproveRuleDTO.Condition existCondition = result.get(condition.getRuleType());
            if(existCondition!=null){
                log.error("matchRule() 合同超框规则匹配异常,匹配到了两个相同类型的规则 => 【contractExceedVO = {}】,【existCondition ={}】,【newCondition ={}】",
                        JSON.toJSONString(contractExceedDTO), JSON.toJSONString(existCondition), JSON.toJSONString(condition));
            }
            result.put(condition.getRuleType(),condition);
        }
        return result;
    }

    public static void main(String[] args) {
        ContractRiskApproveRuleDTO contractRiskApproveRule = JSON.parseObject(jsonConfig,ContractRiskApproveRuleDTO.class);
        List<ContractRiskApproveRuleDTO.Condition> conditions = contractRiskApproveRule.getConditions().stream()
                .filter(condition -> Objects.equals(condition.getIsDeleted(), "n"))

//                .sorted(Comparator.comparingInt(ContractRiskApproveRuleDTO.Condition::getOrder)).collect(Collectors.toList());

                .sorted().collect(Collectors.toList());

    }
}
