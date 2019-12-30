package cn.lastwhisper.cache.ha.local;

import java.util.HashMap;
import java.util.Map;
/**
 * 本地缓存
 */
public class LocationCache {

	private static Map<Long, String> cityMap = new HashMap<>();
	
	static {
		cityMap.put(1L, "北京"); 
	}
	
	public static String getCityName(Long cityId) {
		return cityMap.get(cityId);
	}
	
}
