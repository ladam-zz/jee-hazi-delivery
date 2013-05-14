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
public class RunnerMBean implements Serializable{
    @EJB
    private SessionBean sessionBean;
    @ManagedProperty(value="#{param.runnerID}")
    private String id;
    @ManagedProperty(value="#{param.runnerName}")
    private String name;
    @ManagedProperty(value="#{param.runnerPwd}")
    private String pwd;
    @ManagedProperty(value="#{param.runnerTel}")
    private String tel;
    @ManagedProperty(value = "#{param.runnerDispatcher}")
    private String dispatcher;

    public String update() {
        if (!tel.matches("^([0-9\\(\\)\\/\\+ \\-]*)$")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("regexp pattern: ^([0-9\\(\\)\\/\\+ \\-]*)$"));
            return null;
        } else {
        FacesContext fc = FacesContext.getCurrentInstance();
        String id_str = getIDParam(fc);
        sessionBean.updateRunner(Long.valueOf(id_str),name,pwd,tel,Boolean.valueOf(dispatcher));
        return "Success!";}
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(String dispatcher) {
        this.dispatcher = dispatcher;
    }

    
}
