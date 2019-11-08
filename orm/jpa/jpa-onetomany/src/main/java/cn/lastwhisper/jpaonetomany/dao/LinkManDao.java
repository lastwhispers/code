package cn.lastwhisper.jpaonetomany.dao;

import cn.lastwhisper.jpaonetomany.domain.Customer;
import cn.lastwhisper.jpaonetomany.domain.LinkMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 
 * @author lastwhisper
 * @date 2019/10/16
 */
public interface LinkManDao extends JpaRepository<LinkMan,Long>, JpaSpecificationExecutor<LinkMan> {
}
