package cn.cunchang.json.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 *
 * 审批规则
 */
@Data
public class ContractRiskApproveRuleDTO {
    /**
     * y 删除，n 未删除
     */
    private String isDeleted;
    /**
     * 规则集合
     */
    private List<Condition> conditions;

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    @Data
    public static class Condition implements Comparable<Condition>{
        /**
         * 金额比率表达式，true命中规则，false不命中
         * 1<ratio && ratio<=1.2
         */
        private String ratioExpression;
        /**
         * 合同类型
         *  框架合同、单项目合同
         */
        private Set<String> contractTypeSet;
        /**
         * 包含该采购类目，不命中规则
         */
        private Set<String> purchaseCategoryNotContainSet;
        /**
         * 包含该采购类目，命中规则
         */
        private Set<String> purchaseCategorySet;
        /**
         * 规则顺序
         */
        private Integer order;
        /**
         * y 删除，n 未删除
         */
        private String isDeleted;
        /**
         * 审批类型
         */
        private String approveType;
        /**
         * 审批文案
         */
        private String approveNodeTip;
        /**
         * 详情页文案
         */
        private String detailTip;
        /**
         * 规则类型：
         * overdue，超期
         * overratio，金额比率
         */
        private String ruleType;
        /**
         * 规则描述
         */
        private String desc;

        @Override
        public int compareTo(Condition condition) {
            return this.order-condition.order;
        }
    }
}