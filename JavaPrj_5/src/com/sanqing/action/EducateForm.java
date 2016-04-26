package com.sanqing.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.sanqing.po.Educate;
import com.sanqing.tool.DateUtil;

/**
 * @author BWeiMing
 *
 */
public class EducateForm extends ActionForm {
    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private String name;

    /** nullable persistent field */
    private String phoneNumber;

    /** nullable persistent field */
    private String begintime;

    /** nullable persistent field */
    private String endtime;

    /** nullable persistent field */
    private String requireDetail;

    /** nullable persistent field */
    private String car;

    /** nullable persistent field */
    private String plateNumber;

    /** nullable persistent field */
    private String createtime;

    /** nullable persistent field */
    private String effect;

    /** nullable persistent field */
    private String summarize;

    private Byte educate;

    /** full constructor */
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        this.name = null;
        this.phoneNumber = null;
        this.begintime = null;
        this.endtime = null;
        this.requireDetail = null;
        this.car = null;
        this.plateNumber = null;
        this.createtime = null;
        this.educate=null;
        this.effect = null;
        this.summarize = null;
    }


    public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1) {
        return null;
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

    public String getBegintime() {
        return this.begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return this.endtime;
    }

    public void setEndtime(String endtime) {
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

    public String getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(String createtime) {
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

    public Educate populate(){
        Educate e=new Educate();
        e.setBegintime(DateUtil.parseToDate(this.getBegintime(),DateUtil.yyyyMMdd));
        e.setCreatetime(DateUtil.parseToDate(this.getCreatetime(),DateUtil.yyyyMMddHHmmss));
        e.setRequireDetail(this.getRequireDetail());
        e.setEffect(this.getEffect());
        e.setEndtime(DateUtil.parseToDate(this.getEndtime(),DateUtil.yyyyMMdd));
        e.setId(this.getId());
        e.setName(this.getName());
        e.setPhoneNumber(this.getPhoneNumber());
        e.setPlateNumber(this.getPlateNumber());
        e.setSummarize(this.getSummarize());
        e.setEducate(this.getEducate());
        e.setCar(this.getCar());
        return e;
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
