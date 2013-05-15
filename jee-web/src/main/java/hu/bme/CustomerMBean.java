/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.bme;

/**
 *
 * @author karszak
 */

import java.io.Serializable;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class CustomerMBean implements Serializable{
    @EJB
    private SessionBean sessionBean;
    @ManagedProperty(value="#{param.customerID}")
    private String id;
    @ManagedProperty(value="#{param.customerName}")
    private String name;
    @ManagedProperty(value="#{param.customerAddr}")
    private String addr;
    @ManagedProperty(value="#{param.customerTel}")
    private String tel;

    public String update() {
        if (!tel.matches("^([0-9\\(\\)\\/\\+ \\-]*)$")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("regexp pattern: ^([0-9\\(\\)\\/\\+ \\-]*)$"));
            return null;
        } else {
            /*FacesContext fc = FacesContext.getCurrentInstance();
            String id_str = getIDParam(fc);*/
            sessionBean.updateCustomer(Long.valueOf(id), name, addr, tel);
            return "Success!";
        }
    }
        
    public String getIDParam(FacesContext fc){          
            Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
            return params.get("id"); 
    }
    
    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    
}
