
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
import domain.Application;
import domain.Category;
import domain.CreditCard;
import domain.Customer;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Warranty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class FixUpTaskServiceTest extends AbstractTest {

	@Autowired
	private ApplicationService	applicationService;
	@Autowired
	private FixUpTaskService	fixUpTaskService;
	@Autowired
	private WarrantyService		warrantyService;
	@Autowired
	private CategoryService		categoryService;
	@Autowired
	private CustomerService		customerService;
	@Autowired
	private CreditCardService	creditCardService;
	@Autowired
	private HandyWorkerService	handyWorkerService;


	@Test
	public void createFixUpTask() {
		final Customer customer = this.customerService.create();
		final Customer savedCustomer;
		final UserAccount uaCustomer = new UserAccount();
		uaCustomer.setPassword("hola");
		uaCustomer.setUsername("Antonio");
		uaCustomer.setAuthorities(customer.getUserAccount().getAuthorities());
		customer.setName("Antonio");
		customer.setAddress("calle Arahal");
		customer.setEmail("antonio@us.es");
		customer.setPhone("654321123");
		customer.setSurname("surnaeCustomer");
		customer.setUserAccount(uaCustomer);
		customer.setScore(10);

		savedCustomer = this.customerService.save(customer);
		final Warranty warranty, savedWarranty;
		warranty = this.warrantyService.create();
		final Collection<String> laws = warranty.getLaws();
		laws.add("Ley1");
		final Collection<String> terms = warranty.getTerms();
		terms.add("Term1");
		warranty.setLaws(laws);
		warranty.setTerms(terms);
		warranty.setTitle("TituloWarranty");
		savedWarranty = this.warrantyService.save(warranty);

		FixUpTask f;
		f = this.fixUpTaskService.create();
		f.setAddress("Calle Hola");
		f.setApplication(new HashSet<Application>());
		final Category c = this.categoryService.create();
		c.setName("Categoria3");
		c.setParent(c);
		f.setCategory(c);
		f.setCustomer(savedCustomer);
		f.setDescription("Description");
		f.setMaximunPrice(0.);
		f.setMoment(new Date());
		f.setPeriodTime(0);
		f.setTicker("123456-123qwe");
		f.setWarranty(savedWarranty);

		Assert.isTrue(f.getMoment() != null && f.getMoment().after(new Date()));
		Assert.isTrue(f.getCustomer().getName() == "Antonio");
		Assert.isTrue(f.getWarranty().getTerms().contains("Term1"));
	}

	@Test
	public void savedFixUpTask() {
		final Customer customer = this.customerService.create();
		final Customer savedCustomer;
		final UserAccount uaCustomer = new UserAccount();
		uaCustomer.setPassword("hola");
		uaCustomer.setUsername("Antonio");
		uaCustomer.setAuthorities(customer.getUserAccount().getAuthorities());
		customer.setName("Antonio");
		customer.setAddress("calle Arahal");
		customer.setEmail("antonio@us.es");
		customer.setPhone("654321123");
		customer.setSurname("surnaeCustomer");
		customer.setUserAccount(uaCustomer);
		customer.setScore(10);

		savedCustomer = this.customerService.save(customer);
		final Warranty warranty, savedWarranty;
		warranty = this.warrantyService.create();
		final Collection<String> laws = warranty.getLaws();
		laws.add("Ley1");
		final Collection<String> terms = warranty.getTerms();
		terms.add("Term1");
		warranty.setLaws(laws);
		warranty.setTerms(terms);
		warranty.setTitle("TituloWarranty");
		savedWarranty = this.warrantyService.save(warranty);

		FixUpTask f;
		f = this.fixUpTaskService.create();
		f.setAddress("Calle Hola");
		f.setApplication(new HashSet<Application>());
		final Category c = this.categoryService.create();
		c.setName("Categoria3");
		c.setParent(c);
		f.setCategory(c);
		f.setCustomer(savedCustomer);
		f.setDescription("Description");
		f.setMaximunPrice(0.);
		f.setMoment(new Date());
		f.setPeriodTime(0);
		f.setTicker("123456-123qwe");
		f.setWarranty(savedWarranty);
		final FixUpTask savedF = this.fixUpTaskService.save(f);

		final Collection<FixUpTask> fs = this.fixUpTaskService.findAll();
		Assert.isTrue(fs.contains(savedF));
	}

	@Test
	public void maxMinAvgDev() {
		final HandyWorker h = this.handyWorkerService.create();
		final UserAccount ua = new UserAccount();
		ua.setPassword("hola");
		ua.setUsername("Antonio");
		ua.setAuthorities(h.getUserAccount().getAuthorities());
		h.setName("Antonio");
		h.setAddress("calle Arahal");
		h.setEmail("antonio@us.es");
		h.setPhone("654321123");
		h.setSurname("surname");
		h.setUserAccount(ua);
		h.setScore(10);
		h.setMakeHandyWorker("makehandyworker");
		final HandyWorker savedHandyWorker = this.handyWorkerService.save(h);

		final Warranty warranty, savedWarranty;
		warranty = this.warrantyService.create();
		final Collection<String> laws = warranty.getLaws();
		laws.add("Ley1");
		final Collection<String> terms = warranty.getTerms();
		terms.add("Term1");
		warranty.setLaws(laws);
		warranty.setTerms(terms);
		warranty.setTitle("TituloWarranty");
		savedWarranty = this.warrantyService.save(warranty);

		Category category, savedCategory;
		category = this.categoryService.create();
		category.setName("CategoriaPrimera");
		category.setParent(category);
		savedCategory = this.categoryService.save(category);

		final Customer customer = this.customerService.create();
		final Customer savedCustomer;
		final UserAccount uaCustomer = new UserAccount();
		uaCustomer.setPassword("hola");
		uaCustomer.setUsername("Antonio");
		uaCustomer.setAuthorities(customer.getUserAccount().getAuthorities());
		customer.setName("Antonio");
		customer.setAddress("calle Arahal");
		customer.setEmail("antonio@us.es");
		customer.setPhone("654321123");
		customer.setSurname("surnaeCustomer");
		customer.setUserAccount(uaCustomer);
		customer.setScore(10);
		savedCustomer = this.customerService.save(customer);

		FixUpTask fix;
		FixUpTask savedFix;
		fix = this.fixUpTaskService.create();
		final Collection<Application> appList = new HashSet<Application>();
		fix.setAddress("adressFix");
		fix.setApplication(appList);
		fix.setCategory(savedCategory);
		fix.setCustomer(savedCustomer);
		fix.setDescription("Descripcion en fixUpTask");
		fix.setMaximunPrice(1.0);
		fix.setMoment(new Date());
		fix.setPeriodTime(12);
		fix.setWarranty(savedWarranty);
		fix.setTicker("123qweqwe2132");
		savedFix = this.fixUpTaskService.save(fix);

		CreditCard creditCard, savedCreditCard;
		creditCard = this.creditCardService.create();
		creditCard.setBrandName("Tarjeta1");
		creditCard.setCW(200);
		creditCard.setExpirationMonth(2);
		creditCard.setExpirationYear(1);
		creditCard.setNumber(12321321);
		creditCard.setHolderName("holdername");
		savedCreditCard = this.creditCardService.save(creditCard);

		final Application app, savedApp;
		app = this.applicationService.create();
		app.setCreditCard(savedCreditCard);
		app.setFixUpTask(savedFix);
		app.setStatus(1);
		app.setHandyWorker(savedHandyWorker);
		app.setMoment(new Date());
		app.setPrice(12.);
		savedApp = this.applicationService.save(app);
		savedFix = this.fixUpTaskService.save(fix);
		appList.add(savedApp);

		final Collection<Double> values = this.fixUpTaskService.maxMinAvgDevFixUpTask();
		Assert.isTrue(!values.isEmpty());
	}

}
