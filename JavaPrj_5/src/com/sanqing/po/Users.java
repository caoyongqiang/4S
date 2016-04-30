package com.sanqing.po;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable {
    private Long id;	//员工编号
    private String username; //员工用户名
    private String phoneNumber; //手机号
    private String password; //登录密码
    private Integer roleType;		//性别
    private String idCard;	//身份证号
    private Date createtime;//创建时间
    private String content;	//人员简介
    public Users(Long id,String username, String password, String phoneNumber, Integer roleType, String idCard, Date createtime, String content) {
        this.id=id;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.roleType = roleType;
        this.idCard = idCard;
        this.createtime = createtime;
        this.content = content;
    }
    public Users() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleType() {
        return this.roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public String getIdCard() {
        return this.idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        StringBuffer toStr = new StringBuffer();
        toStr.append("[Users] = [\n");
        toStr.append("    id = " + this.id + ";\n");
        toStr.append("    username = " + this.username + ";\n");
        toStr.append("    phoneNumber = " + this.phoneNumber + ";\n");
        toStr.append("    password = " + this.password + ";\n");
        toStr.append("    roleType = " + this.roleType + ";\n");
        toStr.append("    idCard = " + this.idCard+ ";\n");
        toStr.append("    createtime = " + this.createtime+ ";\n");
        toStr.append("    content = " + this.content + ";\n");
        toStr.append("    ];\n");
        return toStr.toString();
    }
}
