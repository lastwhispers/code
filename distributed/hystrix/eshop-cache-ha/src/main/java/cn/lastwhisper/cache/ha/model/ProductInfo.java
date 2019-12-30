package cn.lastwhisper.cache.ha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 商品信息
 * @author Administrator
 *
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo {

	private Long id;
	private String name;
	private Double price;
	private String pictureList;
	private String specification;
	private String service;
	private String color;
	private String size;
	private Long shopId;
	private String modifiedTime;

	// semaphore
	private Long cityId;
	private String cityName;

	// fallback
	private Long brandId;
	private String brandName;

}
