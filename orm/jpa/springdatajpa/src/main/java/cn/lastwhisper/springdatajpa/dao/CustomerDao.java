package cn.lastwhisper.springdatajpa.dao;

import cn.lastwhisper.springdatajpa.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * JpaRepository：用来完成基本CRUD操作
 * JpaSpecificationExecutor：用于复杂查询（分页等查询操作）
 * @author lastwhisper
 */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    /**
     * 查询所有Customer
     * @return java.util.List<cn.lastwhisper.springdatajpa.domain.Customer>
     */
    @Query(value = "from Customer")
    public List<Customer> findAllCustomer();

    /**
     * 根据custName查询所有Customer
     *  ?1代表参数的占位符，其中1对应方法中的参数索引
     * @param custName
     * @return java.util.Optional<cn.lastwhisper.springdatajpa.domain.Customer>
     */
    @Query(value = "from Customer where custName = ?1")
    public Optional<Customer> findCustomer(String custName);

    /**
     * 根据custId更新custName
     *  Modifying 来将该操作标识为修改查询，这样框架最终会生成一个更新的操作，而非查询
     * @param custName
     * @param custId
     * @return void
     */
    @Query(value = "update Customer set custName = ?1 where custId = ?2")
    @Modifying
    public void updateCustomer(String custName, Long custId);

    /**
     * nativeQuery : 使用本地sql的方式查询
     * @return void
     */
    @Query(value = "select * from cst_customer", nativeQuery = true)
    public List<Customer> findSql();

    /**
     * 驼峰命名方式查询
     * 根据客户名称查询客户
     * @param custName
     * @return
     */
    public Optional<Customer> findByCustName(String custName);
}
