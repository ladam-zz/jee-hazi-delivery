package hu.bme;

import hu.bme.entities.Customer;
import hu.bme.entities.Runner;
import hu.bme.entities.Delivery;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
@LocalBean
public class SessionBean {
    
    @PersistenceContext
    EntityManager em;

//---------------------------------------- Delivery ----------------------------------------   
    public void addDelivery(String item, String senderID, String receiverID, String runnerID){
    	Customer sender = em.find(Customer.class, Long.valueOf(senderID));
    	Customer receiver = em.find(Customer.class, Long.valueOf(receiverID));
    	Runner runner = em.find(Runner.class, Long.valueOf(runnerID));
    	
    	Delivery d=new Delivery();
        d.setItem(item);
        d.setSender(sender);
        d.setReceiver(receiver);
        d.setRunner(runner);
        em.persist(d);
    }

    public List<Delivery> getDeliveries(){
        return (List<Delivery>)em.createQuery("SELECT a FROM Delivery a").getResultList();
    }
    
    public void deleteDelivery(Delivery d) {
		d = em.merge(d);
		em.remove(d);
	}
    
        public void updateDelivery(Long id,String item, Long senderID, Long receiverID, Long runnerID) {
            Customer sender = em.find(Customer.class, senderID);
            Customer receiver = em.find(Customer.class, receiverID);
            Runner runner = em.find(Runner.class, runnerID);

            Delivery d = em.find(Delivery.class, id);
            d.setItem(item);
            d.setSender(sender);
            d.setReceiver(receiver);
            d.setRunner(runner);
            em.merge(d);
    }    
//---------------------------------------- Customer ----------------------------------------
    public void addCustomer(String name, String addr, String tel){
        Customer c= new Customer();
        c.setName(name);
        c.setAddr(addr);
        c.setTel(tel);
        em.persist(c);
    }

    public List<Customer> getCustomers(){
        return (List<Customer>)em.createQuery("SELECT a FROM Customer a").getResultList();
    }
    
    public void deleteCustomer(Customer c) {
		Customer removable = em.merge(c);
		em.remove(removable);
	}
    
    public void updateCustomer(Long id, String name, String addr,String tel) {
        Customer c = em.find(Customer.class, id);
        c.setName(name);
        c.setAddr(addr);
        c.setTel(tel);
        em.merge(c);
    }
//---------------------------------------- Runner ----------------------------------------    
    public void addRunner(String name, String uname, String pwd, String tel, String dispatcher){
        Runner r= new Runner();
        r.setName(name);
        r.setUname(uname);
        r.setPwd(pwd);
        r.setTel(tel);
        r.setDispatcher(Boolean.valueOf(dispatcher));
        em.persist(r);
    }

    public List<Runner> getRunners(){
        return (List<Runner>)em.createQuery("SELECT a FROM Runner a").getResultList();
    }

    public void deleteRunner(Runner r) {
		r = em.merge(r);
		em.remove(r);
	}
    public void updateRunner(Long id,String uname,String name, String pwd,String tel,Boolean dispatcher) {
        Runner r = em.find(Runner.class, id);
        r.setUname(uname);
        r.setName(name);
        r.setPwd(pwd);
        r.setTel(tel);
        r.setDispatcher(dispatcher);
        em.merge(r);
    }
    
    public boolean authRunner(String uname,String pwd){
        
        final Query query = em.createQuery("SELECT r FROM Runner r WHERE lower(r.uname) like :username");
        query.setParameter("username", uname.toLowerCase());
        List results = query.getResultList();
        Runner foundRunner;
        boolean isok = false;
        if (!results.isEmpty()) {
            // ignores multiple results
            foundRunner = (Runner)results.get(0);
            isok=foundRunner.getPwd().equals(pwd);
        }
        
        return isok;
    }
}