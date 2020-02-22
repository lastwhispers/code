package com.bfxy.springboot.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bfxy.springboot.entity.BrokerMessageLog;

public interface BrokerMessageLogMapper {
    int deleteByPrimaryKey(String messageId);

    int insert(BrokerMessageLog record);

    int insertSelective(BrokerMessageLog record);

    BrokerMessageLog selectByPrimaryKey(String messageId);

    int updateByPrimaryKeySelective(BrokerMessageLog record);

    int updateByPrimaryKey(BrokerMessageLog record);

    /**
     * 查询消息状态为0(发送中) 且已经超时的消息集合
     * @return
     */
	List<BrokerMessageLog> query4StatusAndTimeoutMessage();
	
	/**
	 * 重新发送统计count发送次数 +1
	 * @param messageId
	 * @param updateTime
	 */
	void update4ReSend(@Param("messageId")String messageId, @Param("updateTime")Date updateTime);
	/**
	 * 更新最终消息发送结果 成功 or 失败
	 * @param messageId
	 * @param status
	 * @param updateTime
	 */
	void changeBrokerMessageLogStatus(@Param("messageId")String messageId, @Param("status")String status, @Param("updateTime")Date updateTime);


	
}