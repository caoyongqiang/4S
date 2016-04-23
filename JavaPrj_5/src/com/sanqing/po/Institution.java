package com.sanqing.po;

import java.io.Serializable;
import java.util.Date;

/** ���� */
public class Institution implements Serializable {
    private Long id;		//���ͱ��
    private String name;	//�������
    private String phoneNumber;	//����ԭ��
    private String idCard;	//����˵��
    private String house; //家庭住址
    private String desireCar; //欲购车型
    private Date visitTime;//����ʱ��

    /** full constructor */
    public Institution(String name, String phoneNumber, String idCard, String house, String desireCar, Date visitTime) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.idCard = idCard;
        this.house = house;
        this.desireCar = desireCar;
        this.visitTime = visitTime;
    }

    /** default constructor */
    public Institution() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdCard() {
        return this.idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getHouse() {
        return this.house;
    }

    public void setHouse(String house) {
        this.house = house;
    }
    
    public String getDesireCar() {
        return this.desireCar;
    }

    public void setDesireCar(String desireCar) {
        this.desireCar = desireCar;
    }
    
    public Date getVisitTime() {
        return this.visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }
    
    public String toString() {
        StringBuffer toStr = new StringBuffer();
        toStr.append("[Institution] = [\n");
        toStr.append("    id = " + this.id + ";\n");
        toStr.append("    name = " + this.name + ";\n");
        toStr.append("    phoneNumber = " + this.phoneNumber + ";\n");
        toStr.append("    visitTime = " + this.visitTime+ ";\n");
        toStr.append("    idCard = " + this.idCard + ";\n");
        toStr.append("    house = " + this.house + ";\n");
        toStr.append("    desireCar = " + this.desireCar + ";\n");
        toStr.append("    ];\n");
        return toStr.toString();
    }

}
