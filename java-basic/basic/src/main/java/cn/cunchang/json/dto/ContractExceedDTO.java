package cn.cunchang.json.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractExceedDTO {
    /**
     * 下po时的合同code
     */
    private String contractCode;
    /**
     * 下po时的合同类型
     */
    private String contractType;
    /**
     * po的code
     */
    private String poNumber;
    /**
     * 当前po的采购类目
     */
    private Set<String> currentPoPurchaseCategory;
    /**
     * 是否超期
     */
    private Boolean overdue;
    /**
     * 合同清单以下po总金额/合同清单金额
     */
    private BigDecimal ratio;
}
