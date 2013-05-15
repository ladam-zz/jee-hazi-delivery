package hu.bme;

import hu.bme.entities.Customer;
import hu.bme.entities.Runner;
import hu.bme.entities.Delivery;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class StartupListener {

	@PersistenceContext
	EntityManager em;

	@PostConstruct
	public void onStartup() {
		if (em.createQuery("SELECT a FROM Delivery a").setMaxResults(1)
				.getResultList().isEmpty()) {

			Customer c = new Customer();
			c.setAddr("Csalogany 32");
			c.setName("Jozsi");
			em.persist(c);

			Customer c2 = new Customer();
			c2.setName("Zsuzsa");

			Runner r = new Runner();
                        r.setUname("runner1");
			r.setName("Futár Józsi");
                        r.setDispatcher(Boolean.TRUE);
                        r.setPwd("pwd");

			Delivery d = new Delivery();

			d.setItem("Stuff");
			d.setRunner(r);
			d.setReceiver(c);
			d.setSender(c2);
			em.persist(d);
			em.persist(c);
			em.persist(c2);
			em.persist(r);
                        Delivery d2 = new Delivery();
                        d2.setItem("Toll");
                        d2.setReceiver(c2);
                        d2.setSender(c);
                        em.persist(d2);
		}
	}
}
