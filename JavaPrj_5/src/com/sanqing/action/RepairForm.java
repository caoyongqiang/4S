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
    private String car;

    /** nullable persistent field */
    private String plateNumber;

    /** nullable persistent field */
    private String tel;

    /** nullable persistent field */
    private String repairDetail;

    /** nullable persistent field */
    private Byte isstock;

    private String createtime;

    /** full constructor */
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        this.id=null;
        this.name = null;
        this.car = null;
        this.plateNumber = null;
        this.tel = null;
        this.createtime=null;
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
        toStr.append("    car = " + this.car + ";\n");
        toStr.append("    plateNumber = " + this.plateNumber + ";\n");
        toStr.append("    tel = " + this.tel + ";\n");
        toStr.append("    createtime = " + this.createtime+ ";\n");
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
        j.setRepairDetail(this.getRepairDetail());
        j.setCreatetime(DateUtil.parseToDate(this.getCreatetime(),DateUtil.yyyyMMddHHmmss));
        j.setPlateNumber(this.getPlateNumber());
        j.setId(this.getId());
        j.setIsstock(this.getIsstock());
        j.setName(this.getName());
        j.setCar(this.getCar());
        j.setTel(this.getTel());
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
