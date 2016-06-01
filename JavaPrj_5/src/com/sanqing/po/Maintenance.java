package com.sanqing.po;

import java.io.Serializable;
import java.util.Date;

/** ���� */
public class Maintenance implements Serializable {
    private Long id;	
    private String name;
    private String phoneNumber;	
    private String car;	
    private String plateNumber; 
    private String content;
    private Date preTime;
    private Date nextTime;

    /** full constructor */
    public Maintenance(String name, String phoneNumber, String car, String plateNumber, String content, Date preTime, Date nextTime) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.car = car;
        this.plateNumber = plateNumber;
        this.content = content;
        this.preTime = preTime;
        this.nextTime = nextTime;
    }

    /** default constructor */
    public Maintenance() {
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

    public String getCar() {
        return this.car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getPlateNumber() {
        return this.plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
    
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public Date getPreTime() {
        return this.preTime;
    }

    public void setPreTime(Date preTime) {
        this.preTime = preTime;
    }
    
    public Date getNextTime() {
        return this.nextTime;
    }

    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
    }
    
    public String toString() {
        StringBuffer toStr = new StringBuffer();
        toStr.append("[Clue] = [\n");
        toStr.append("    id = " + this.id + ";\n");
        toStr.append("    name = " + this.name + ";\n");
        toStr.append("    phoneNumber = " + this.phoneNumber + ";\n");
        toStr.append("    preTime = " + this.preTime+ ";\n");
        toStr.append("    nextTime = " + this.nextTime+ ";\n");
        toStr.append("    car = " + this.car + ";\n");
        toStr.append("    plateNumber = " + this.plateNumber + ";\n");
        toStr.append("    content = " + this.content + ";\n");
        toStr.append("    ];\n");
        return toStr.toString();
    }

}
