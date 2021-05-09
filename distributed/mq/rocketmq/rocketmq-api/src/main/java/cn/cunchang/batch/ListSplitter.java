package cn.cunchang.batch;

import org.apache.rocketmq.common.message.Message;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ListSplitter implements Iterator<List<Message>> {
    private final int SIZE_LIMIT = 1024 * 1024 *4;
    private final List<Message> messages;
    private int currIndex;

    public ListSplitter(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean hasNext() {
        return currIndex < messages.size();
    }

    @Override
    public List<Message> next() {
        // 获取开始切割的消息索引(消息字节小于4MB的消息索引)
        int startIndex = getStartIndex();
        // 切割结束位置
        int nextIndex = startIndex;
        // 临时存放切割出的消息列表的总字节数
        int totalSize = 0;
        // 切割消息列表,从起始位置切割,直到切割出的消息列表的总字节数超过4MB为止
        for (; nextIndex < messages.size() ; nextIndex++) {
            // 计算遍历的消息的字节并累加,当超过4MB时退出循环,记录nextIndex;用于切分list
            Message message = messages.get(nextIndex);
            int tmpSize = calcMessageSize(message);
            if (tmpSize + totalSize > SIZE_LIMIT){
                break;
            }else {
                totalSize += tmpSize;
            }
        }
        List<Message> subList = this.messages.subList(startIndex, nextIndex);
        // 存放切割结束位置
        currIndex = nextIndex;
        return subList;
    }

    /**
     * 计算开始切割的位置，切割的对象是消息列表，最小粒度就是一个消息对象(一条消息的字节大于4MB不会对该消息对象进行切割)
     * 获取起始切割位置就是找到单条消息字节小于4MB的消息索引。对大于4MB的消息跳过。
     */
    private int getStartIndex(){
        // 计算开始索引的消息的字节数
        Message currMessage = messages.get(currIndex);
        int tmpSize = calcMessageSize(currMessage);
        // 下一个开始位置的消息的字节数不能大于4MB
        while (tmpSize > SIZE_LIMIT) {
            currIndex ++;
            Message message = messages.get(currIndex);
            tmpSize = calcMessageSize(message);
        }
        return currIndex;
    }

    private int calcMessageSize(Message message){
        int tmpSize = message.getTopic().length() + message.getBody().length;
        Map<String, String> properties = message.getProperties();
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            tmpSize += entry.getKey().length() + entry.getValue().length();
        }
        // 增加日志的开销20字节
        tmpSize = tmpSize+20;
        return tmpSize;
    }
}
