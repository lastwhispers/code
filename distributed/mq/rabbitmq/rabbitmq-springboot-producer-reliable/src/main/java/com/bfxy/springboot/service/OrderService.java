package com.bfxy.springboot.service;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bfxy.springboot.constant.Constants;
import com.bfxy.springboot.entity.BrokerMessageLog;
import com.bfxy.springboot.entity.Order;
import com.bfxy.springboot.mapper.BrokerMessageLogMapper;
import com.bfxy.springboot.mapper.OrderMapper;
import com.bfxy.springboot.producer.RabbitOrderSender;
import com.bfxy.springboot.utils.FastJsonConvertUtil;

@Service
public class OrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private BrokerMessageLogMapper brokerMessageLogMapper;
	
	@Autowired
	private RabbitOrderSender rabbitOrderSender;
	
	
	public void createOrder(Order order) throws Exception {
		// order current time 
		Date orderTime = new Date();
		// order insert
		orderMapper.insert(order);
		// log insert
		BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
		brokerMessageLog.setMessageId(order.getMessageId());
		//save order message as json
		brokerMessageLog.setMessage(FastJsonConvertUtil.convertObjectToJSON(order));
		brokerMessageLog.setStatus("0");
		brokerMessageLog.setNextRetry(DateUtils.addMinutes(orderTime, Constants.ORDER_TIMEOUT));
		brokerMessageLog.setCreateTime(new Date());
		brokerMessageLog.setUpdateTime(new Date());
		brokerMessageLogMapper.insert(brokerMessageLog);
		// order message sender
		rabbitOrderSender.sendOrder(order);
	}
	
}
