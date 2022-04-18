package cn.itcast.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 虎哥
 */
@Data
@TableName("order_tbl")
public class Order {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String userId;
    private String commodityCode;
    private Integer count;
    private Integer money;
}
