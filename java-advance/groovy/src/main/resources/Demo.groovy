import cn.cunchang.Constants
import cn.cunchang.PaymentRequestOrderBO
import com.alibaba.fastjson.JSONObject

PaymentRequestOrderBO extract(JSONObject jsonObject) throws Exception {
    PaymentRequestOrderBO paymentRequestOrderBO = new PaymentRequestOrderBO()

    // value节点
    JSONObject value = jsonObject.getJSONObject(Constants.VALUE)
    // form节点
    JSONObject form = value.getJSONObject(Constants.FORM)

    // 合同ID
    String agentName = form.getString(Constants.u_代理商名称);
    paymentRequestOrderBO.setShopName(agentName);

    return paymentRequestOrderBO;
}
