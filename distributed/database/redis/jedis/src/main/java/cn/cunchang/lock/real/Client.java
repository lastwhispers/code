package cn.cunchang.lock.real;

import cn.cunchang.JedisUtil;

/**
 * @author cunchang
 */
public class Client {

    public static void main(String[] args) {
        // 校验参数或者干了什么
        LockUtil.wrongLock3_2(JedisUtil.getJedis(),"stock_lock_key",100, new CodeBlock() {
            @Override
            public void operatingSharedResource() {
                // 查库存
                // 库存-1
                // 更新库存
            }
        });
        // 封装结果或者干了什么
    }

}
