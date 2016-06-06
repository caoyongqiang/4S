package com.sanqing.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.sanqing.po.CarOwners;
import com.sanqing.tool.DateUtil;
public class CarOwnersForm extends ActionForm {

    public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1) {
        return null;
    }
    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private String name;

    /** nullable persistent field */
    private String phoneNumber;

    /** nullable persistent field */
    private String idCard;

    /** nullable persistent field */
    private String house;

    /** nullable persistent field */
    private String purchaseTime;

    /** nullable persistent field */
    private String car;

    /** nullable persistent field */
    private Float carPrice;

    /** nullable persistent field */
    private String plateNumber;

    /** nullable persistent field */
    private Float other;

    /** nullable persistent field */
    private Float totalize;
    
    /** nullable persistent field */
    private String seller;

    /** full constructor */
    public void reset(ActionMapping mapping,HttpServletRequest request) {
        this.name = null;
        this.phoneNumber = null;
        this.idCard = null;
        this.house = null;
        this.purchaseTime = null;
        this.car = null;
        this.carPrice = null;
        this.plateNumber = null;
        this.other = null;
        this.totalize = null;
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

    public String getPlateNumber() {
        return this.plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
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
        toStr.append("[CarOwners] = [\n");
        toStr.append("    id = " + this.id + ";\n");
        toStr.append("    name = " + this.name + ";\n");
        toStr.append("    phoneNumber = " + this.phoneNumber + ";\n");
        toStr.append("    idCard = " + this.idCard + ";\n");
        toStr.append("    house = " + this.house + ";\n");
        toStr.append("    purchaseTime = " + this.purchaseTime + ";\n");
        toStr.append("    car = " + this.car + ";\n");
        toStr.append("    carPrice = " + this.carPrice + ";\n");
        toStr.append("    plateNumber = " + this.plateNumber + ";\n");
        toStr.append("    other = " + this.other + ";\n");
        toStr.append("    seller = " + this.seller + ";\n");
        toStr.append("    totalize = " + this.totalize + ";\n");
        toStr.append("    ];\n");
        return toStr.toString();
    }

    public CarOwners populate(){
        CarOwners s=new CarOwners();
        s.setPhoneNumber(this.getPhoneNumber());
        s.setCar(this.getCar());
        s.setIdCard(this.getIdCard());
        s.setHouse(this.getHouse());
        s.setId(this.getId());
        s.setPurchaseTime(DateUtil.parseToDate(this.getPurchaseTime(),DateUtil.yyyyMMdd));
        s.setName(this.getName());
        s.setOther(this.getOther());
        s.setPlateNumber(this.getPlateNumber());
        s.setCarPrice(this.getCarPrice());
        s.setTotalize(this.getTotalize());
        s.setSeller(this.getSeller());
        return s;
    }

    /**
     * @return Returns the purchaseTime.
     */
    public String getPurchaseTime() {
        return purchaseTime;
    }
    /**
     * @param purchaseTime The purchaseTime to set.
     */
    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}
}
