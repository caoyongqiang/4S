package com.sanqing.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.sanqing.po.Repair;
import com.sanqing.tool.DateUtil;

/**
 * @author BWeiMing
 *
 */
public class RepairForm extends ActionForm {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private String name;

    /** nullable persistent field */
    private Byte sex;

    /** nullable persistent field */
    private Integer repairTimes;

    /** nullable persistent field */
    private String repair;

    /** nullable persistent field */
    private String car;

    /** nullable persistent field */
    private String plateNumber;

    /** nullable persistent field */
    private String repairType;

    /** nullable persistent field */
    private String repairCost;

    /** nullable persistent field */
    private String tel;

    /** nullable persistent field */
    private String repairDetail;

    /** nullable persistent field */
    private Byte isstock;

    private String createtime;

    private String email;

    /**
     * @return Returns the email.
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /** full constructor */
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        this.id=null;
        this.name = null;
        this.sex = null;
        this.repairTimes = null;
        this.repair = null;
        this.car = null;
        this.plateNumber = null;
        this.repairType = null;
        this.repairCost = null;
        this.tel = null;
        this.createtime=null;
        this.email=null;
        this.repairDetail = null;
        this.isstock = null;
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

    public Byte getSex() {
        return this.sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Integer getRepairTimes() {
        return this.repairTimes;
    }

    public void setRepairTimes(Integer repairTimes) {
        this.repairTimes = repairTimes;
    }

    public String getRepair() {
        return this.repair;
    }

    public void setRepair(String repair) {
        this.repair = repair;
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

    public String getRepairType() {
        return this.repairType;
    }

    public void setRepairType(String repairType) {
        this.repairType = repairType;
    }

    public String getRepairCost() {
        return this.repairCost;
    }

    public void setRepairCost(String repairCost) {
        this.repairCost = repairCost;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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
        toStr.append("[RepairForm] = [\n");
        toStr.append("    id = " + this.id + ";\n");
        toStr.append("    name = " + this.name + ";\n");
        toStr.append("    sex = " + this.sex + ";\n");
        toStr.append("    repairTimes = " + this.repairTimes + ";\n");
        toStr.append("    repair = " + this.repair + ";\n");
        toStr.append("    car = " + this.car + ";\n");
        toStr.append("    plateNumber = " + this.plateNumber + ";\n");
        toStr.append("    repairType = " + this.repairType + ";\n");
        toStr.append("    repairCost = " + this.repairCost + ";\n");
        toStr.append("    tel = " + this.tel + ";\n");
        toStr.append("    createtime = " + this.createtime+ ";\n");
        toStr.append("    email= " + this.email+ ";\n");
        toStr.append("    repairDetail = " + this.repairDetail+ ";\n");
        toStr.append("    isstock = " + this.isstock + ";\n");
        toStr.append("    ];\n");
        return toStr.toString();
    }

    public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1) {
        return null;
    }

    public Repair populate(){
        Repair j=new Repair();
        j.setRepairTimes(this.getRepairTimes());
        j.setRepairDetail(this.getRepairDetail());
        j.setCreatetime(DateUtil.parseToDate(this.getCreatetime(),DateUtil.yyyyMMddHHmmss));
        j.setPlateNumber(this.getPlateNumber());
        j.setId(this.getId());
        j.setIsstock(this.getIsstock());
        j.setRepair(this.getRepair());
        j.setName(this.getName());
        j.setRepairCost(this.getRepairCost());
        j.setSex(this.getSex());
        j.setCar(this.getCar());
        j.setRepairType(this.getRepairType());
        j.setTel(this.getTel());
        j.setEmail(this.getEmail());
        return j;
    }
    /**
     * @return Returns the createtime.
     */
    public String getCreatetime() {
        return createtime;
    }
    /**
     * @param createtime The createtime to set.
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
