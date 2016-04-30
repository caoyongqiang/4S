package com.sanqing.po;

import java.io.Serializable;
import java.util.Date;

/** ��ѵ */
public class Service implements Serializable {
    private Long id;		//��ѵ���
    private String name;	//��ѵ���
    private String phoneNumber;
    private String requireDetail;	//��ѵ����
    private String car;	//��ѵ��ʦ
    private String plateNumber;	//��ѵ��Ա
    private Date createtime;//����ʱ��
    private Byte service;
    private String summarize;//�ܽ� 

    public Service(String name, String phoneNumber, String requireDetail, String car, String plateNumber, Date createtime, Byte service, String summarize) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.requireDetail = requireDetail;
        this.car = car;
        this.plateNumber = plateNumber;
        this.createtime = createtime;
        this.service=service;
        this.summarize = summarize;
    }

    /** default constructor */
    public Service() {
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

    public String getRequireDetail() {
        return this.requireDetail;
    }

    public void setRequireDetail(String requireDetail) {
        this.requireDetail = requireDetail;
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

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getSummarize() {
        return this.summarize;
    }

    public void setSummarize(String summarize) {
        this.summarize = summarize;
    }

    public String toString() {
        StringBuffer toStr = new StringBuffer();
        toStr.append("[Service] = [\n");
        toStr.append("    id = " + this.id + ";\n");
        toStr.append("    name = " + this.name + ";\n");
        toStr.append("    createtime = " + this.createtime+ ";\n");
        toStr.append("    phoneNumber = " + this.phoneNumber + ";\n");
        toStr.append("    requireDetail = " + this.requireDetail + ";\n");
        toStr.append("    car = " + this.car + ";\n");
        toStr.append("    plateNumber = " + this.plateNumber + ";\n");
        toStr.append("    service = " + this.service+ ";\n");
        toStr.append("    summarize = " + this.summarize + ";\n");
        toStr.append("    ];\n");
        return toStr.toString();
    }

    /**
     * @return Returns the service.
     */
    public Byte getService() {
        return service;
    }
    /**
     * @param service The service to set.
     */
    public void setService(Byte service) {
        this.service = service;
    }
}
