package cn.lastwhisper.jpa.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Entity
 *       作用：指定当前类是实体类。
 * @Table
 *       作用：指定实体类和表之间的对应关系。
 *         	属性：
 *         		name：指定数据库表的名称
 * @Id
 *       作用：指定当前字段是主键。
 * @GeneratedValue
 *       作用：指定主键的生成方式。。
 *         	属性：
 *         		strategy ：指定主键生成策略。
 * @Column
 *       作用：指定实体类属性和数据库表之间的对应关系
 *         	属性：
 *         		name：指定数据库表的列名称。
 *         		unique：是否唯一
 *         		nullable：是否可以为空
 *         		inserttable：是否可以插入
 *         		updateable：是否可以更新
 *         		columnDefinition: 定义建表时创建此列的DDL
 *         		secondaryTable: 从表名。如果此列不建在主表上（默认建在主表），该属性定义该列所在从表的名字搭建开发环境[重点]
 *
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "cst_customer")
public class Customer implements Serializable {
    /**
     * 客户编号
     */
    @Id//声明为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //配置主键的生成策略
    @Column(name = "cust_id") //指定和表中cust_id字段的映射关系
    private Long custId;
    /**
     * 客户名称
     */
    @Column(name = "cust_name") //指定和表中cust_name字段的映射关系
    private String custName;
    /**
     * 客户信息来源
     */
    @Column(name = "cust_source")//指定和表中cust_source字段的映射关系
    private String custSource;
    /**
     * 客户所属行业
     */
    @Column(name = "cust_industry")//指定和表中cust_industry字段的映射关系
    private String custIndustry;
    /**
     * 客户级别
     */
    @Column(name = "cust_level")//指定和表中cust_level字段的映射关系
    private String custLevel;
    /**
     * 客户联系地址
     */
    @Column(name = "cust_address")//指定和表中cust_address字段的映射关系
    private String custAddress;
    /**
     * 客户联系电话
     */
    @Column(name = "cust_phone")//指定和表中cust_phone字段的映射关系
    private String custPhone;


}
