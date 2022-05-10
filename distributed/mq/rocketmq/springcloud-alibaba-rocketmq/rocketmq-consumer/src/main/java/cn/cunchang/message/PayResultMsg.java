package cn.cunchang.message;

/**
 * 支付结果消息
 * @author cunchang
 */
public class PayResultMsg {

    /**
     * 支付流水号
     */
    private String payNo;

    /**
     * 付款账号
     */
    private String payAccNo;

    /**
     * 收款账户
     */
    private String revAccNo;

    /**
     * 支付金额
     */
    private Long amount;

    /**
     * 支付结果，0失败 1成功
     */
    private Integer result;

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getPayAccNo() {
        return payAccNo;
    }

    public void setPayAccNo(String payAccNo) {
        this.payAccNo = payAccNo;
    }

    public String getRevAccNo() {
        return revAccNo;
    }

    public void setRevAccNo(String revAccNo) {
        this.revAccNo = revAccNo;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "PayResultMsg{" +
                "payNo='" + payNo + '\'' +
                ", payAccNo='" + payAccNo + '\'' +
                ", revAccNo='" + revAccNo + '\'' +
                ", amount=" + amount +
                ", result=" + result +
                '}';
    }
}
