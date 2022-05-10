package cn.cunchang.v2;

/**
 * 付款计划、付款申请单、提现申请单leo迁移的接口列表
 * Created by qiumu on 2020/10/28.
 */
public enum UrlListEnum {

    PC进场费管理_付款计划_1(1,"leo/1.0/payPlan/list"),

    PC合同详情_付款计划_2(2,"leo/1.0/payPlan/detail"),

    PC合同详情_付款计划_新增付款计划_3(3,"leo/1.0/payPlan/add"),

    PC合同详情_付款计划_新增其他费用付款计划_4(4,"leo/1.0/other/fee/payPlan/add"),

    PC合同详情_付款计划_新增押金付款计划_5(5,"leo/1.0/deposit/plan/create"),

    PC合同详情_付款计划_删除_6(6,"leo/1.0/payPlan/delete"),

    PC合同详情_付款计划_发起请款_查询付款计划_7(7,"leo/1.0/payPlanLine/detail"),

    瓯柑那边调用_根据付款申请单号获取付款计划税前金额_8(8,"leo/1.0/payPlan/getByApplyNo"),

    付款成功后lvy回调leo更新付款计划为付款成功_9(9,"leo/1.0/payApply/pay/callback"),

    移动端付款申请单列表_10(10,"leo/search/payApply"),

    PC合同_付款计划_发起请款_提交_check_11(11,"leo/1.0/payPlan/check"),

    PC合同_付款计划_发起请款_提交_12(12,"leo/1.0/payPlan/edit"),

    付款申请单列表_查询条件subjectTypeList_13(13,"leo/1.0/scm/invoice/subjectType/list"),

    付款申请单列表List_14(14,"leo/1.0/payApply/list"),

    付款申请单列表sum_15(15,"leo/1.0/payApply/sumAmount"),

    直营付款申请单获取审批人_16(16,"leo/1.0/approval/task/checker/payment/apply"),

    KA付款申请单获取审批人_17(17,"leo/1.0/approval/task/checker/ka/payment/apply"),

    KA付款申请单获取审批人2_18(18,"leo/1.0/approval/task/checker/payment/apply/ka"),

    付款申请单回调_19(19,"leo/1.0/payApply/check/result"),

    付款申请单撤回_20(20,"leo/1.0/payPlan/recall"),

    PC付款申请单_发票核销_21(21,"leo/1.0/payApply/invoice/use"),

    PC付款申请单_切换票款顺序_22(22,"leo/1.0/payApply/invoice/updateMethod"),

    PC付款申请单_审批详情1_23(23,"leo/1.0/approval/purchasePlan/getProcessDetail"),

    PC付款申请单_审批详情2_24(24,"leo/1.0/payApply/check/detail"),

    PC付款申请单_申请详情_25(25,"leo/1.0/payApply/detail"),

    提现申请回调_26(26,"leo/1.0/approval/callback/merchant/withDraw/add"),

    商户提现流程审批流程获取任务节点的审批人接口_27(27,"leo/1.0/approval/task/checker/merchant/withDraw/add"),

    移动端提现申请列表_28(28,"/leo/search/withdrawals"),

    PC端_财务管理_提现申请_提现申请列表List_29(29,"leo/1.0/merchant/withDraw/queryWithdrawList"),

    PC端_财务管理_提现申请_提现申请列表Sum_30(30,"leo/1.0/merchant/withDraw/sumAmount"),

    PC端_财务管理_提现申请_提现申请列表查询条件1_31(31,"leo/1.0/h5/merchant/withDraw/clientStatusList"),

    PC端_财务管理_提现申请_提现申请列表查询条件2_32(32,"leo/1.0/h5/merchant/withDraw/statusList"),

    PC端_财务管理_提现申请_提现申请列表_审核_通过_拒绝_冻结_33(33,"leo/1.0/merchant/withDraw/financialOperationCheck"),

    PC端_财务管理_提现申请_提现申请列表_审批详情_34(34,"leo/1.0/approval/purchasePlan/getProcessDetail"),

    PC端_财务管理_提现申请_提现申请列表_审核_35(35,"leo/1.0/merchant/withDraw/getWithdrawalDetail"),

    PC端_财务管理_提现申请_提现申请列表_发票核对_查询发票_36(36,"leo/1.0/merchant/withDraw/getInvoiceInfo"),

    PC端_财务管理_提现申请_提现申请列表_发票核对_通过_不通过_37(37,"leo/1.0/merchant/withDraw/invoiceCheck"),

    移动端_审批中心_提现修改发票_38(38,"leo/1.0/merchant/withDraw/modifyInvoiceInfo"),

    PC端_财务管理_提现申请_先款后票白名单维护_39(39,"leo/1.0/withdrawals/whitelist/list"),

    PC端_财务管理_提现申请_先款后票白名单维护_新增_40(40,"leo/1.0/withdrawals/whitelist/batch"),

    PC端_财务管理_提现申请_先款后票白名单维护_作废_41(41,"leo/1.0/withdrawals/whitelist/{mainBizId}"),

    PC端_财务管理_提现申请_先款后票白名单维护_生效_作废_42(42,"leo/1.0/withdrawals/whitelist/enable/{mainBizId}"),

    PC端_财务管理_提现申请_先款后票白名单维护_导出_43(43,"leo/1.0/withdrawals/whitelist/export"),

    提现申请_导出_44(44,"leo/1.0/h5/merchant/withDraw/export"),

    lvy账单任务创建AccountFreezeTask_45(45,"leo/1.0/merchant/withDraw/recall/byLvy"),

    提现批量审核_查询_46(46,"leo/1.0/h5/merchant/withDraw/batch/getTaskInfo"),

    提现批量审核_下一步47(47,"leo/1.0/h5/merchant/withDraw/batch/getAuditDetailList"),

    批量处理审批任务48(48,"leo/1.0/h5/merchant/withDraw/batchHandleApprovalTask"),

    BD审核任务_49(49,"/leo/1.0/merchant/withDraw/addBdCheckOperatorRecord"),

    获取提现发票信息_50(50,"/leo/1.0/merchant/withdrawal/invoice"),

    获取批量审批提现单列表_51(51,"/leo/1.0/merchant/withDraw/batch/getList"),

    获取批量审批提现单列表总金额_52(52,"/leo/1.0/merchant/withDraw/batch/sumAmount"),

    批量审核提交_53(53,"/leo/1.0/merchant/withDraw/batch/submit"),

    提现先款后票_发票核销_54(54,"/leo/1.0/withdrawals/invoice/deduct"),

    ;




    /**
     * 接口ID
     */
    private Integer id ;


    /**
     *
     */
    private String url;


    UrlListEnum(Integer id,String url){
        this.id = id;
        this.url = url;
    }


    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }



}
