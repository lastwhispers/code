package cn.lastwhisper.order.repository;

import cn.lastwhisper.order.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lastwhisper
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
