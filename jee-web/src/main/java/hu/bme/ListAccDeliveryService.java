/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.bme;

/**
 *
 * @author karszak
 */

import hu.bme.entities.Delivery;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "ListAccDelivery")
public class ListAccDeliveryService {
    @EJB
    private SessionBean sessionBean;
        
    @WebMethod(operationName = "getAcceptedDeliveries")
    public List<Delivery> getAcceptedDeliveries() {
        return sessionBean.getAcceptedDeliveries();
    }
}
