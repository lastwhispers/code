package cn.cunchang.transaction;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionListenerImpl implements TransactionListener {

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
        String tags = msg.getTags();
        if(StringUtils.contains(tags,"TagA")){
            // 事务提交
            return LocalTransactionState.COMMIT_MESSAGE;
        }else if(StringUtils.contains(tags,"TagB")){
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }else{
            return LocalTransactionState.UNKNOW;
        }
    }

    /**
     * 本地事务执行返回LocalTransactionState.UNKNOW时进行回查事务状态
     *
     * @param msg
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        System.err.println("TransactionListenerImpl checkLocalTransaction 回调消息检查 msg:" + msg);
        String tags = msg.getTags();
        if(StringUtils.contains(tags,"TagC")){
            return LocalTransactionState.COMMIT_MESSAGE;
        }else if(StringUtils.contains(tags,"TagD")){
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }else{
            return LocalTransactionState.UNKNOW;
        }
    }
}