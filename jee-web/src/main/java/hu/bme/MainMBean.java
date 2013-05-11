package hu.bme;

import hu.bme.entities.Customer;
import hu.bme.entities.Delivery;
import hu.bme.entities.Runner;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class MainMBean {

    @EJB
    private SessionBean SessionBean;

    public SessionBean getSessionBean() {
        return SessionBean;
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
        SessionBean.addDelivery(item, deliverySender, deliveryReceiver, deliveryRunner);
        return null;
    }

    public String doRunner() {
        SessionBean.addRunner(name, uname, pwd, tel, dispatcher);
        return null;
    }

    public String doCustomer() {
        SessionBean.addCustomer(name, addr, tel);
        return null;
    }

    public void deleteCustomer(Customer c) {
        SessionBean.deleteCustomer(c);
    }
     
    public void deleteRunner(Runner r) {
        SessionBean.deleteRunner(r);
    }
    
    public void deleteDelivery(Delivery d) {
        SessionBean.deleteDelivery(d);
    }
}
