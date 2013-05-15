/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.bme;

import hu.bme.entities.Runner;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author karszak
 */

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable{

    /*@EJB
    private UserBean bean;*/
    @EJB
    private SessionBean sessionBean;
    private String loginName;
    private String password;
    private String result;
    private Long currRunnerID = null;
    private boolean dispatcher = false;
    private boolean islogin = false;

    public String login() {
       
        Runner runner = sessionBean.authRunner(loginName, password);
        
        if (runner != null) {
            currRunnerID = runner.getId();
            islogin = true;
            try {
                dispatcher = runner.getDispatcher().booleanValue();
            } catch (NullPointerException npe) {
                dispatcher = false;
            }
            return "success";
        } else {
            return "unauthorized";
        }
    }
    
    public String logout() {
        currRunnerID = null;
        dispatcher = false;
        islogin = false;
        return "logged_out";
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public Long getCurrRunnerID() {
        return currRunnerID;
    }

    public void setCurrRunnerID(Long currRunnerID) {
        this.currRunnerID = currRunnerID;
    }
    
    public boolean isDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(boolean dispatcher) {
        this.dispatcher = dispatcher;
    }

    public boolean isIslogin() {
        return islogin;
    }

    public void setIslogin(boolean islogin) {
        this.islogin = islogin;
    }
    
    
}
