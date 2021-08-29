package cn.cunchang;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDao extends JpaRepository<OrderEntity, Long> {

    OrderEntity findByOrderId(Long orderId);

    OrderEntity findByUserIdAndOrderId(Integer userId,Long orderId);

    List<OrderEntity> findByUserId(Integer userId);
}
