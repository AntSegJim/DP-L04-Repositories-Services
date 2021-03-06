
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import security.UserAccount;
import utilities.AbstractTest;
import domain.Customer;
import domain.Endorsement;
import domain.HandyWorker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class EndorsementServiceTest extends AbstractTest {

	// ------------ Service under test ------------
	@Autowired
	private EndorsementService	endorsementService;
	@Autowired
	private CustomerService		customerService;
	@Autowired
	private HandyWorkerService	handyWorkerService;


	//---------------------- Test ----------------------
	@Test
	public void testCreateEndorsement() {
		Endorsement e;
		e = this.endorsementService.create();
		final Collection<String> comments = new HashSet<String>();
		comments.add("Comentario1");
		e.setComments(comments);

		final Customer c, savedC;
		c = this.customerService.create();
		final UserAccount uaCustomer = new UserAccount();
		uaCustomer.setPassword("hola");
		uaCustomer.setUsername("Antonio");
		uaCustomer.setAuthorities(c.getUserAccount().getAuthorities());

		c.setSurname("Segura");
		c.setName("Antonio");
		c.setAddress("calle Arahal");
		c.setEmail("antonio@us.es");
		c.setPhone("654321123");
		c.setUserAccount(uaCustomer);
		c.setScore(10);
		savedC = this.customerService.save(c);

		HandyWorker h, savedH;
		h = this.handyWorkerService.create();
		final UserAccount ua = new UserAccount();
		ua.setPassword("hola");
		ua.setUsername("Antonio");
		ua.setAuthorities(h.getUserAccount().getAuthorities());

		h.setName("Antonio");
		h.setAddress("calle Arahal");
		h.setEmail("antonio@us.es");
		h.setPhone("654321123");
		h.setSurname("Segura");
		h.setUserAccount(ua);

		savedH = this.handyWorkerService.save(h);
		e.setCustomerReceiver(savedC);
		e.setHandyWorkerSender(savedH);
		e.setMoment(new Date());
		Assert.isTrue(e.getComments().contains("Comentario1"));
	}

	@Test
	public void testSaveEndorsement() {
		Endorsement e;
		final Endorsement savedE;
		e = this.endorsementService.create();
		final Collection<String> comments = new HashSet<String>();
		comments.add("Comentario1");
		e.setComments(comments);

		final Customer c, savedC;
		c = this.customerService.create();
		final UserAccount uaCustomer = new UserAccount();
		uaCustomer.setPassword("hola");
		uaCustomer.setUsername("Antonio");
		uaCustomer.setAuthorities(c.getUserAccount().getAuthorities());

		c.setSurname("Segura");
		c.setName("Antonio");
		c.setAddress("calle Arahal");
		c.setEmail("antonio@us.es");
		c.setPhone("654321123");
		c.setUserAccount(uaCustomer);
		c.setScore(10);
		savedC = this.customerService.save(c);

		HandyWorker h, savedH;
		h = this.handyWorkerService.create();
		final UserAccount ua = new UserAccount();
		ua.setPassword("hola");
		ua.setUsername("Antonio");
		ua.setAuthorities(h.getUserAccount().getAuthorities());

		h.setName("Antonio");
		h.setAddress("calle Arahal");
		h.setEmail("antonio@us.es");
		h.setPhone("654321123");
		h.setSurname("Segura");
		h.setUserAccount(ua);

		savedH = this.handyWorkerService.save(h);
		e.setCustomerReceiver(savedC);
		e.setHandyWorkerSender(savedH);
		e.setMoment(new Date());

		savedE = this.endorsementService.save(e);
		final Collection<Endorsement> es = this.endorsementService.findAll();
		Assert.isTrue(es.contains(savedE));
	}
	@Test
	public void testDeleteEndorsement() {
		Endorsement e;
		final Endorsement savedE;
		e = this.endorsementService.create();
		final Collection<String> comments = new HashSet<String>();
		comments.add("Comentario1");
		e.setComments(comments);

		final Customer c, savedC;
		c = this.customerService.create();
		final UserAccount uaCustomer = new UserAccount();
		uaCustomer.setPassword("hola");
		uaCustomer.setUsername("Antonio");
		uaCustomer.setAuthorities(c.getUserAccount().getAuthorities());

		c.setSurname("Segura");
		c.setName("Antonio");
		c.setAddress("calle Arahal");
		c.setEmail("antonio@us.es");
		c.setPhone("654321123");
		c.setUserAccount(uaCustomer);
		c.setScore(10);
		savedC = this.customerService.save(c);

		HandyWorker h, savedH;
		h = this.handyWorkerService.create();
		final UserAccount ua = new UserAccount();
		ua.setPassword("hola");
		ua.setUsername("Antonio");
		ua.setAuthorities(h.getUserAccount().getAuthorities());

		h.setName("Antonio");
		h.setAddress("calle Arahal");
		h.setEmail("antonio@us.es");
		h.setPhone("654321123");
		h.setSurname("Segura");
		h.setUserAccount(ua);

		savedH = this.handyWorkerService.save(h);
		e.setCustomerReceiver(savedC);
		e.setHandyWorkerSender(savedH);
		e.setMoment(new Date());

		savedE = this.endorsementService.save(e);
		this.endorsementService.delete(savedE);
		final Collection<Endorsement> es = this.endorsementService.findAll();
		Assert.isTrue(!es.contains(e));
	}
}
