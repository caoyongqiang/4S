package com.sanqing.po;

import java.io.Serializable;
import java.util.Date;

/** н�� */
public class Stipend implements Serializable {
    private Long id;		//н����Ϣ���
    private String name;	//Ա������
    private Float phoneNumber;	//��н��
    private Float eat;		//����
    private Float house;	//����
    private Date granttime;	// ���ʷ���ʱ��
    private Float duty;		//ȫ�ڽ�
    private Float scot;		//��˰
    private Float punishment;//����
    private Float other;	//���ⲹ��
    private Float totalize;	//�ܼ�

    public Stipend(String name, Float phoneNumber, Float eat, Float house, Date granttime, Float duty, Float scot, Float punishment, Float other, Float totalize) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.eat = eat;
        this.house = house;
        this.granttime = granttime;
        this.duty = duty;
        this.scot = scot;
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

    public Float getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(Float phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Float getEat() {
        return this.eat;
    }

    public void setEat(Float eat) {
        this.eat = eat;
    }

    public Float getHouse() {
        return this.house;
    }

    public void setHouse(Float house) {
        this.house = house;
    }

    /**
     * @return Returns the granttime.
     */
    public Date getGranttime() {
        return granttime;
    }
    /**
     * @param granttime The granttime to set.
     */
    public void setGranttime(Date granttime) {
        this.granttime = granttime;
    }
    public Float getDuty() {
        return this.duty;
    }

    public void setDuty(Float duty) {
        this.duty = duty;
    }

    public Float getScot() {
        return this.scot;
    }

    public void setScot(Float scot) {
        this.scot = scot;
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
        toStr.append("    eat = " + this.eat + ";\n");
        toStr.append("    house = " + this.house + ";\n");
        toStr.append("    granttime = " + this.granttime + ";\n");
        toStr.append("    duty = " + this.duty + ";\n");
        toStr.append("    scot = " + this.scot + ";\n");
        toStr.append("    punishment = " + this.punishment + ";\n");
        toStr.append("    other = " + this.other + ";\n");
        toStr.append("    totalize = " + this.totalize + ";\n");
        toStr.append("    ];\n");
        return toStr.toString();
    }

}
