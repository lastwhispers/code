package cn.lastwhisper.order.repository;

import cn.lastwhisper.order.domain.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author lastwhisper
 * @date 2019/10/26
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
