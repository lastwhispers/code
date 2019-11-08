package cn.lastwhisper.springdatajpa;

import cn.lastwhisper.springdatajpa.dao.CustomerDao;
import cn.lastwhisper.springdatajpa.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

/**
 * Specifications动态查询
 * @author lastwhisper
 * @date 2019/10/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 分页查询
     */
    @Test
    public void testPage() {
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("custName").as(String.class), "阿里巴%");
            }
        };
        /*
         * 构造分页参数
         * 		Pageable : 接口
         * 			PageRequest实现了Pageable接口，调用构造方法的形式构造
         * 				第一个参数：页码（从0开始）
         * 				第二个参数：每页查询条数
         * Pageable pageable = new PageRequest(0, 5);
         */
        Pageable pageable = PageRequest.of(0, 5);
        
        Page<Customer> page = customerDao.findAll(specification, pageable);

        System.out.println("总记录数"+page.getTotalElements());
        System.out.println("总页数"+page.getTotalPages());
        page.getContent().forEach(System.out::println);

    }

    @Test
    public void testSpecifications() {
        Specification<Customer> specification = new Specification<Customer>() {
            /**
             *	root	：Root接口，代表查询的根对象，可以通过root获取实体中的属性
             *	query	：代表一个顶层查询对象，用来自定义查询
             *	cb		：用来构建查询，此对象里有很多条件方法
             **/
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("custName").as(String.class), "阿里巴%");
            }
        };

        Optional<Customer> op = customerDao.findOne(specification);
        if (op.isPresent()) System.out.println(op.get());

    }


}
