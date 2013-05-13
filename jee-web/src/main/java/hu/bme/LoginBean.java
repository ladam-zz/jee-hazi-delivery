/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.bme;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author karszak
 */
@ManagedBean
@RequestScoped
public class LoginBean {

    /*@EJB
    private UserBean bean;*/
    @EJB
    private SessionBean sessionBean;
    private String loginName;
    private String password;
    private String result;

    public String login() {
       boolean isok = sessionBean.authRunner(loginName, password);
        
        /*if (!password.equals("admin")) {
            result = "wrong";
            return null;
      
         }

         bean.setUsername(loginName);*/
        if (isok) {
            return "success";
        } else {
            return "unauthorized";
        }
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

/*    public UserBean getBean() {
        return bean;
    }
*/
    public SessionBean getSessionBean() {
        return sessionBean;
    }
}
