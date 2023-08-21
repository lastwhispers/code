package cn.cunchang;

import lombok.Data;

@Data
public class PaymentRequestOrderBO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 业务单号 <br/>
     * 1. 易快报单据编码
     */
    private String bizNo;

    /**
     * 易快报单据id
     */
    private String flowId;

    /**
     * 付款单流水号
     */
    private String payBillNo;

    /**
     * 单据类型
     */
    private Integer bizType;

    /**
     * 业务类型，对应付款单的业务类型
     */
    private Integer payBizType;

    /**
     * 单据类型名称
     */
    private String bizTypeStr;

    /**
     * 业务类型名称
     */
    private String payBizTypeStr;

    /**
     * 付款金额，单位分
     */
    private Long payAmount;

    /**
     * 税前金额，单位分
     */
    private Long preTaxAmount;

    /**
     * 付款主体
     */
    private Integer paymentCompany;

    /**
     * 合同id
     */
    private Long contractId;

    /**
     * 商家id
     */
    private Long shopId;

    /**
     * 商家名称
     */
    private String shopName;

    /**
     * 主体名称
     */
    private String subjectName;

    /**
     * 账户所属主体
     */
    private String accountSubject;

    /**
     * 结算方id
     */
    private Long settleSubjectId;

    /**
     * 大区/事业部
     */
    private String areaDepartment;

    /**
     * 所属城市
     */
    private String city;

    /**
     * 申请单说明
     */
    private String remark;

    /**
     * 附件信息
     */
    private String attachment;

    /**
     * 提交人ID
     */
    private String submitterId;

    /**
     * 提交人
     */
    private String submitter;

    /**
     * 单据提交时间
     */
    protected Long submitTime;

    // --------------- 收款人信息 -----------------

    /**
     * 收款人
     */
    private String revAccName;

    /**
     * 收款账号
     */
    private String revAccNo;

    /**
     * 开户银行名称
     */
    private String revBankName;

    /**
     * 开户银行支行名称
     */
    private String revBankBranch;

    /**
     * 银行联行号，直接支付需要
     */
    private String revBankNo;

    /**
     * 收款人身份证号，第三方支付身份证必传
     */
    private String revCardId;

    /**
     * 收款人手机号
     */
    private String revMobile;

    /**
     * 收方开户银行省份
     */
    private String revBankProvince;

    /**
     * 收方开户银行城市
     */
    private String revBankCity;
    /**
     * 付款主体在易快报code
     */
    private String ykbCompanyCode;
}
