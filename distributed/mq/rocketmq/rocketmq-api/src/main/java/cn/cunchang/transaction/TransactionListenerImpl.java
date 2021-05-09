package cn.cunchang.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionListenerImpl implements TransactionListener {
    private final AtomicInteger transactionIndex = new AtomicInteger(0);
    private final ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    /**
     * 执行本地事务
     *
     * @param msg
     * @param arg 回调参数
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        System.err.println("TransactionListenerImpl executeLocalTransaction 异步执行本地事务 msg:" + msg + " arg:" + arg);
        int value = transactionIndex.getAndIncrement();
        int status = value % 3;
        /**
         * 1、4、7 %3= 1
         */
        localTrans.put(msg.getTransactionId(), status);

        // 事务开启

        // 事务提交
        return LocalTransactionState.UNKNOW;
    }

    /**
     * 回查
     *
     * @param msg
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        System.err.println("TransactionListenerImpl checkLocalTransaction 回调消息检查 msg:" + msg);
        Integer status = localTrans.get(msg.getTransactionId());
        if (null != status) {
            switch (status) {
                case 0:
                    return LocalTransactionState.UNKNOW;
                case 1:
                    return LocalTransactionState.COMMIT_MESSAGE;
                case 2:
                    return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}