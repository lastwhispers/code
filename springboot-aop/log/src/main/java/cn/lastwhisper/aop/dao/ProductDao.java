package cn.lastwhisper.aop.dao;

import cn.lastwhisper.aop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product,Long> {
    //public Product findById(Long id);
}
