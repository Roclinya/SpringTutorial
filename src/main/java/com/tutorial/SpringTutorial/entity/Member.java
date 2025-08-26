package com.tutorial.SpringTutorial.entity;

import jakarta.persistence.*;
import java.io.Serializable;
@Entity
//預設Table name即為class name, 如果要指定table name，則需加入@Table(name="tablename")
@Table(name = "member")
public class Member implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "eMail",nullable = false)
    private String eMail;
    @Column(name = "usrName",nullable = false)
    private String usrName;
    @Column(name = "usrPwd",nullable = false)
    private String usrPwd;

//    @Column(name = "phone_number",nullable = false)
//    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String string) {
        this.eMail = string;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String string) {
        this.usrName = string;
    }

    public String getUsrPwd() {
        return usrPwd;
    }

    public void setUsrPwd(String usrPwd) {
        this.usrPwd = usrPwd;
    }

//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
}
