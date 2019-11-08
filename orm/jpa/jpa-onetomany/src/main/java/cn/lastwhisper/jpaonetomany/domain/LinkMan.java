package cn.lastwhisper.jpaonetomany.domain;

/**
 * 联系人
 * @author lastwhisper
 * @date 2019/10/16
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 联系人的实体类（数据模型）
 * @author Administrator
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cst_linkman")
public class LinkMan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lkm_id")
    private Long lkmId;
    @Column(name = "lkm_name")
    private String lkmName;
    @Column(name = "lkm_gender")
    private String lkmGender;
    @Column(name = "lkm_phone")
    private String lkmPhone;
    @Column(name = "lkm_mobile")
    private String lkmMobile;
    @Column(name = "lkm_email")
    private String lkmEmail;
    @Column(name = "lkm_position")
    private String lkmPosition;
    @Column(name = "lkm_memo")
    private String lkmMemo;

    /**
     * 多对一：联系人和客户
     */
    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY)
    //@ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "lkm_cust_id", referencedColumnName = "cust_id")
    private Customer customer;

    //@Override
    //public String toString() {
    //    return "LinkMan{" +
    //            "lkmId=" + lkmId +
    //            ", lkmName='" + lkmName + '\'' +
    //            ", lkmGender='" + lkmGender + '\'' +
    //            ", lkmPhone='" + lkmPhone + '\'' +
    //            ", lkmMobile='" + lkmMobile + '\'' +
    //            ", lkmEmail='" + lkmEmail + '\'' +
    //            ", lkmPosition='" + lkmPosition + '\'' +
    //            ", lkmMemo='" + lkmMemo + '\'' +
    //            ", customer=" + customer +
    //            '}';
    //}
}
