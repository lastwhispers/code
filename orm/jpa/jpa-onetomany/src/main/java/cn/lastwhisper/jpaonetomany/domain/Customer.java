package cn.lastwhisper.jpaonetomany.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 客户
 * @author Administrator
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    /**
     * 一对多：客户和联系人
     */
    //  常规一对多配置
    //@OneToMany(targetEntity = LinkMan.class)
    //@JoinColumn(name = "lkm_cust_id", referencedColumnName = "cust_id") //设置mappedBy时不可以写
    //  主表放弃维护关系
    //@OneToMany(mappedBy="customer")
    //  延迟加载
    @OneToMany(mappedBy="customer",fetch = FetchType.LAZY)
    //@OneToMany(mappedBy="customer")
    private Set<LinkMan> linkMans = new HashSet<>();

    //@Override
    //public String toString() {
    //    return "Customer{" +
    //            "custId=" + custId +
    //            ", custName='" + custName + '\'' +
    //            ", custSource='" + custSource + '\'' +
    //            ", custIndustry='" + custIndustry + '\'' +
    //            ", custLevel='" + custLevel + '\'' +
    //            ", custAddress='" + custAddress + '\'' +
    //            ", custPhone='" + custPhone + '\'' +
    //            ", linkMans=" + linkMans +
    //            '}';
    //}
}
