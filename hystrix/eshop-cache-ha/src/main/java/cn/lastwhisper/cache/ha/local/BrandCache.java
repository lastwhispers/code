package cn.lastwhisper.cache.ha.local;

import java.util.HashMap;
import java.util.Map;

/**
 * 品牌缓存
 * @author Administrator
 *
 */
public class BrandCache {

	private static Map<Long, String> brandMap = new HashMap<Long, String>();
	
	static {
		brandMap.put(1L, "iphone"); 
	}
	
	public static String getBrandName(Long brandId) {
		return brandMap.get(brandId);
	}
	
}
