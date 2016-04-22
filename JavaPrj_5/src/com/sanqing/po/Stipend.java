package com.sanqing.po;

import java.io.Serializable;
import java.util.Date;

/** н�� */
public class Stipend implements Serializable {
    private Long id;		//н����Ϣ���
    private String name;	//Ա������
    private String phoneNumber;	//��н��
    private String idCard;		//����
    private String house;	//����
    private Date purchaseTime;	// ���ʷ���ʱ��
    private String car;		//ȫ�ڽ�
    private Float carPrice;		//��˰
    private Float punishment;//����
    private Float other;	//���ⲹ��
    private Float totalize;	//�ܼ�

    public Stipend(String name, String phoneNumber, String idCard, String house, Date purchaseTime, String car, Float carPrice, Float punishment, Float other, Float totalize) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.idCard = idCard;
        this.house = house;
        this.purchaseTime = purchaseTime;
        this.car = car;
        this.carPrice = carPrice;
        this.punishment = punishment;
        this.other = other;
        this.totalize = totalize;
    }

    /** default constructor */
    public Stipend() {
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

    /**
     * @return Returns the purchaseTime.
     */
    public Date getPurchaseTime() {
        return purchaseTime;
    }
    /**
     * @param purchaseTime The purchaseTime to set.
     */
    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }
    public String getCar() {
        return this.car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public Float getCarPrice() {
        return this.carPrice;
    }

    public void setCarPrice(Float carPrice) {
        this.carPrice = carPrice;
    }

    public Float getPunishment() {
        return this.punishment;
    }

    public void setPunishment(Float punishment) {
        this.punishment = punishment;
    }

    public Float getOther() {
        return this.other;
    }

    public void setOther(Float other) {
        this.other = other;
    }

    public Float getTotalize() {
        return this.totalize;
    }

    public void setTotalize(Float totalize) {
        this.totalize = totalize;
    }

    public String toString() {
        StringBuffer toStr = new StringBuffer();
        toStr.append("[Stipend] = [\n");
        toStr.append("    id = " + this.id + ";\n");
        toStr.append("    name = " + this.name + ";\n");
        toStr.append("    phoneNumber = " + this.phoneNumber + ";\n");
        toStr.append("    idCard = " + this.idCard + ";\n");
        toStr.append("    house = " + this.house + ";\n");
        toStr.append("    purchaseTime = " + this.purchaseTime + ";\n");
        toStr.append("    car = " + this.car + ";\n");
        toStr.append("    carPrice = " + this.carPrice + ";\n");
        toStr.append("    punishment = " + this.punishment + ";\n");
        toStr.append("    other = " + this.other + ";\n");
        toStr.append("    totalize = " + this.totalize + ";\n");
        toStr.append("    ];\n");
        return toStr.toString();
    }

}
