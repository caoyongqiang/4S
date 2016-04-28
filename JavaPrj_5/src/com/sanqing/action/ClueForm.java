package com.sanqing.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.sanqing.po.Clue;
import com.sanqing.tool.DateUtil;

/**
 * @author BWeiMing
 *
 */
public class ClueForm extends ActionForm {

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
    private String desireCar;

    /** nullable persistent field */
    private String visitTime;

    /** full constructor */
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        this.name = null;
        this.phoneNumber = null;
        this.idCard = null;
        this.house = null;
        this.desireCar = null;
        this.visitTime = null;
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
    
    public String getDesireCar() {
        return this.desireCar;
    }

    public void setDesireCar(String desireCar) {
        this.desireCar = desireCar;
    }
    
    public String getVisitTime() {
        return this.visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    public String toString() {
        StringBuffer toStr = new StringBuffer();
        toStr.append("[Clue] = [\n");
        toStr.append("    id = " + this.id + ";\n");
        toStr.append("    name = " + this.name + ";\n");
        toStr.append("    phoneNumber = " + this.phoneNumber + ";\n");
        toStr.append("    visitTime = " + this.visitTime+ ";\n");
        toStr.append("    idCard = " + this.idCard + ";\n");
        toStr.append("    house = " + this.house + ";\n");
        toStr.append("    desireCar = " + this.desireCar + ";\n");
        toStr.append("    ];\n");
        return toStr.toString();
    }

    public Clue populate(){
        Clue i=new Clue();
        i.setVisitTime(DateUtil.parseToDate(this.getVisitTime(),DateUtil.yyyyMMdd));
        i.setIdCard(this.getIdCard());
        i.setHouse(this.getHouse());
        i.setDesireCar(this.getDesireCar());
        i.setId(this.getId());
        i.setName(this.getName());
        i.setPhoneNumber(this.getPhoneNumber());
        return i;

    }

    public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1) {
        return null;
    }
}
