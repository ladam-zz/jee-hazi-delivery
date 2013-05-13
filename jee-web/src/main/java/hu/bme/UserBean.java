/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.bme;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author karszak
 */
@ManagedBean
@SessionScoped
public class UserBean {
    private String username;
    private boolean isDispatcher;

    public boolean isIsDispatcher() {
        return isDispatcher;
    }

    public void setIsDispatcher(boolean isDispatcher) {
        this.isDispatcher = isDispatcher;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
