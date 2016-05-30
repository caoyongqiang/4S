package com.sanqing.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.sanqing.po.Service;
import com.sanqing.tool.DateUtil;

/**
 * @author BWeiMing
 *
 */
public class ServiceForm extends ActionForm {
    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private String name;

    /** nullable persistent field */
    private String phoneNumber;

    /** nullable persistent field */
    private String requireDetail;

    /** nullable persistent field */
    private String car;

    /** nullable persistent field */
    private String plateNumber;

    /** nullable persistent field */
    private String createtime;

    /** nullable persistent field */
    private String summarize;

    private Byte service;

    /** full constructor */
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        this.name = null;
        this.phoneNumber = null;
        this.requireDetail = null;
        this.car = null;
        this.plateNumber = null;
        this.createtime = null;
        this.service=null;
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

    public Service populate(){
        Service e=new Service();
        e.setCreatetime(DateUtil.parseToDate(this.getCreatetime(),DateUtil.yyyyMMdd));
        e.setRequireDetail(this.getRequireDetail());
        e.setId(this.getId());
        e.setName(this.getName());
        e.setPhoneNumber(this.getPhoneNumber());
        e.setPlateNumber(this.getPlateNumber());
        e.setSummarize(this.getSummarize());
        e.setService(this.getService());
        e.setCar(this.getCar());
        return e;
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
