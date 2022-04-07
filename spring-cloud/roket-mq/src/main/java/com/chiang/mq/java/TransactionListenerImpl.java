package com.chiang.mq.java;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 事务监听实现
 *
 * @author Chiang
 */
public class TransactionListenerImpl implements TransactionListener {

    private final AtomicInteger transactionIndex = new AtomicInteger(0);
    private final ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    /**
     * 如果默认 COMMIT_MESSAGE ,将很快的把消息发送给消费端消费
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        int value = transactionIndex.getAndIncrement();
        // 0=未知信息，1=提交信息，2=回滚信息
        if(value < 9){
            localTrans.put(msg.getTransactionId(), 1);
        }else {
            localTrans.put(msg.getTransactionId(), 0);
        }

        logInfo(" 执行事务的消息 - executeLocalTransaction:" + msg.getTransactionId() + " 状态:"+ localTrans.get(msg.getTransactionId()) + "  执行信息："+new String(msg.getBody()));

        // 只有在 LocalTransactionState.COMMIT_MESSAGE 状态的消息，才会被Consumer消费
        return LocalTransactionState.COMMIT_MESSAGE;
    }

    private void logInfo(String s) {
        System.out.println(Thread.currentThread().getName() + s);
    }

    /**
     * 检查本地事务状态，并回应消息队列的检查请求。
     *
     * 由于RocketMQ迟迟没有收到消息的确认消息，因此主动询问这条prepare消息，是否正常，可以查询数据库看这条数据是否已经处理
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        Integer status = localTrans.get(msg.getTransactionId());

        logInfo(" 事务检查的消息 - checkLocalTransaction：" + msg.getTransactionId() + "  Status："+status + " 事务检查信息："+new String(msg.getBody(), StandardCharsets.UTF_8)+" ， 时间："+new Date());

        if (null != status) {
            switch (status) {
                case 0:
                    // 中间状态，它代表需要检查消息队列来确定状态。
                    return LocalTransactionState.UNKNOW;
                case 1:
                    // 提交事务，它允许消费者消费此消息。
                    return LocalTransactionState.COMMIT_MESSAGE;
                case 2:
                    // 回滚事务，它代表该消息将被删除，不允许被消费。
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                default:
            }
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
