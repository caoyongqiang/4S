package com.sanqing.po;

import java.io.Serializable;
import java.util.Date;

public class Repair implements Serializable {
    private Long id;	//ӦƸ��Ա���
    private String name;
    private String car;//��ѧרҵ
    private String plateNumber;
    private String tel;
    private Date createtime;//����ʱ��
    private String repairDetail;	//��ϸ����
    private Byte isstock;	//�Ƿ����

    public Repair(Long id,String name, String car, String plateNumber, String tel, Date createtime, String repairDetail, Byte isstock) {
        this.id=id;
        this.name = name;
        this.car = car;
        this.plateNumber = plateNumber;
        this.tel = tel;
        this.createtime = createtime;
        this.repairDetail = repairDetail;
        this.isstock = isstock;
    }
    public Repair() {
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
    
    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getRepairDetail() {
        return this.repairDetail;
    }

    public void setRepairDetail(String repairDetail) {
        this.repairDetail = repairDetail;
    }

    public Byte getIsstock() {
        return this.isstock;
    }

    public void setIsstock(Byte isstock) {
        this.isstock = isstock;
    }

    public String toString() {
        StringBuffer toStr = new StringBuffer();
        toStr.append("[Repair] = [\n");
        toStr.append("    id = " + this.id + ";\n");
        toStr.append("    name = " + this.name + ";\n");
        toStr.append("    car = " + this.car + ";\n");
        toStr.append("    plateNumber = " + this.plateNumber + ";\n");
        toStr.append("    tel = " + this.tel + ";\n");
        toStr.append("    createtime = " + this.createtime+ ";\n");
        toStr.append("    repairDetail = " + this.repairDetail+ ";\n");
        toStr.append("    isstock = " + this.isstock + ";\n");
        toStr.append("    ];\n");
        return toStr.toString();
    }

}
