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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class DeliveryMBean implements Serializable{
    @EJB
    private SessionBean sessionBean;
    @ManagedProperty(value="#{param.deliveryID}")
    private String id;
    @ManagedProperty(value="#{param.deliveryItem}")
    private String item;
    @ManagedProperty(value="#{param.deliverySender}")
    private String deliverySender;
    @ManagedProperty(value="#{param.deliveryReceiver}")
    private String deliveryReceiver;
    @ManagedProperty(value="#{param.deliveryRunner}")
    private String deliveryRunner;
    
    
    public String update() {
        FacesContext fc = FacesContext.getCurrentInstance();
        String id_str = getIDParam(fc);
        sessionBean.updateDelivery(Long.valueOf(id_str),item, Long.valueOf(deliverySender), Long.valueOf(deliveryReceiver),Long.valueOf(deliveryRunner));
        return "Success!";
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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDeliverySender() {
        return deliverySender;
    }

    public void setDeliverySender(String deliverySender) {
        this.deliverySender = deliverySender;
    }

    public String getDeliveryReceiver() {
        return deliveryReceiver;
    }

    public void setDeliveryReceiver(String deliveryReceiver) {
        this.deliveryReceiver = deliveryReceiver;
    }

    public String getDeliveryRunner() {
        return deliveryRunner;
    }

    public void setDeliveryRunner(String deliveryRunner) {
        this.deliveryRunner = deliveryRunner;
    }

    
}
