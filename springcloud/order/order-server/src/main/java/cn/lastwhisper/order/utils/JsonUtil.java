package cn.lastwhisper.order.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by 廖师兄
 * 2018-02-21 10:40
 */
public class JsonUtil {

	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 转换为json字符串
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * json转对象
	 * @param string
	 * @param classType
	 * @return
	 */
	public static Object fromJson(String string, Class classType) {
		try {
			return objectMapper.readValue(string, classType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * json转对象
	 * @param string
	 * @param typeReference
	 * @return
	 */
	public static Object fromJson(String string, TypeReference typeReference) {
		try {
			return objectMapper.readValue(string, typeReference);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
