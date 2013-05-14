package hu.bme;

import hu.bme.entities.Customer;
import hu.bme.entities.Delivery;
import hu.bme.entities.Runner;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class MainMBean implements Serializable {

    @EJB
    private SessionBean sessionBean;

    public SessionBean getSessionBean() {
        return sessionBean;
    }
        
    private String name;
    private String addr;
    private String tel;
    private String uname;
    private String pwd;
    private String dispatcher;
    private String item;
    private String deliverySender;
    private String deliveryReceiver;
    private String deliveryRunner;

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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(String dispatcher) {
        this.dispatcher = dispatcher;
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
    
    public String doDelivery() {
        sessionBean.addDelivery(item, deliverySender, deliveryReceiver, deliveryRunner);
        return "saved";
    }

    public String doRunner() {
        if (!tel.matches("^([0-9\\(\\)\\/\\+ \\-]*)$")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("regexp minta: ^([0-9\\(\\)\\/\\+ \\-]*)$"));
            return null;
        }
        if(!sessionBean.isuiqUName(uname)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("felhasználónév nem egyedi!"));
            return null;
        }
        else {
            sessionBean.addRunner(name, uname, pwd, tel, dispatcher);
            return "saved";
        }
    }

    public String doCustomer() {
        if(!tel.matches("^([0-9\\(\\)\\/\\+ \\-]*)$")){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("regexp pattern: ^([0-9\\(\\)\\/\\+ \\-]*)$"));
        return null;
        } else {
        sessionBean.addCustomer(name, addr, tel);
        return "saved";}
    }

    public void deleteCustomer(Customer c) {
        sessionBean.deleteCustomer(c);
    }
     
    public void deleteRunner(Runner r) {
        sessionBean.deleteRunner(r);
    }
    
    public void deleteDelivery(Delivery d) {
        sessionBean.deleteDelivery(d);
    }
    
    public void owned(Delivery d, Long rid) {
        String runner_str = rid.toString();
        sessionBean.updateDelivery(d.getId(), d.getItem(), d.getSender().getId(), d.getReceiver().getId(), runner_str);
    }
}
