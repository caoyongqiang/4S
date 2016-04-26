package com.sanqing.po;

import java.io.Serializable;
import java.util.Date;

/** ��ѵ */
public class Educate implements Serializable {
    private Long id;		//��ѵ���
    private String name;	//��ѵ���
    private String phoneNumber;	//��ѵĿ��
    private Date begintime;	//��ѵ��ʼʱ��
    private Date endtime;	//��ѵ����ʱ��
    private String requireDetail;	//��ѵ����
    private String car;	//��ѵ��ʦ
    private String plateNumber;	//��ѵ��Ա
    private Date createtime;//����ʱ��
    private Byte educate;	//��ѵ�Ƿ����
    private String effect;	//��ѵЧ��
    private String summarize;//�ܽ� 

    public Educate(String name, String phoneNumber, Date begintime, Date endtime, String requireDetail, String car, String plateNumber, Date createtime, Byte educate,String effect, String summarize) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.begintime = begintime;
        this.endtime = endtime;
        this.requireDetail = requireDetail;
        this.car = car;
        this.plateNumber = plateNumber;
        this.createtime = createtime;
        this.educate=educate;
        this.effect = effect;
        this.summarize = summarize;
    }

    /** default constructor */
    public Educate() {
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

    public Date getBegintime() {
        return this.begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return this.endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
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

    public String getEffect() {
        return this.effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getSummarize() {
        return this.summarize;
    }

    public void setSummarize(String summarize) {
        this.summarize = summarize;
    }

    public String toString() {
        StringBuffer toStr = new StringBuffer();
        toStr.append("[Educate] = [\n");
        toStr.append("    id = " + this.id + ";\n");
        toStr.append("    name = " + this.name + ";\n");
        toStr.append("    begintime = " + this.begintime+ ";\n");
        toStr.append("    endtime = " + this.endtime+ ";\n");
        toStr.append("    createtime = " + this.createtime+ ";\n");
        toStr.append("    phoneNumber = " + this.phoneNumber + ";\n");
        toStr.append("    requireDetail = " + this.requireDetail + ";\n");
        toStr.append("    car = " + this.car + ";\n");
        toStr.append("    plateNumber = " + this.plateNumber + ";\n");
        toStr.append("    educate = " + this.educate+ ";\n");
        toStr.append("    effect = " + this.effect + ";\n");
        toStr.append("    summarize = " + this.summarize + ";\n");
        toStr.append("    ];\n");
        return toStr.toString();
    }

    /**
     * @return Returns the educate.
     */
    public Byte getEducate() {
        return educate;
    }
    /**
     * @param educate The educate to set.
     */
    public void setEducate(Byte educate) {
        this.educate = educate;
    }
}
