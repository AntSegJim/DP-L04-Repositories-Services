
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import security.UserAccount;
import domain.Application;
import domain.Category;
import domain.CreditCard;
import domain.Customer;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Section;
import domain.Sponsor;
import domain.Sponsorship;
import domain.Tutorial;
import domain.Warranty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class HandyWorkerServiceTest {

	@Autowired
	private HandyWorkerService	handyWorkerService;
	@Autowired
	private TutorialService		tutorialService;
	@Autowired
	private SectionService		sectionService;
	@Autowired
	private ApplicationService	applicationService;
	@Autowired
	private CreditCardService	crediCardService;
	@Autowired
	private SponsorshipService	sponsorshipService;
	@Autowired
	private SponsorService		sponsorService;
	@Autowired
	private FixUpTaskService	fixUpTaskService;
	@Autowired
	private WarrantyService		warrantyService;
	@Autowired
	private CategoryService		categoryService;
	@Autowired
	private CustomerService		customerService;


	//---------------------- Test ----------------------
	@Test
	public void testCreateHandyWorker() {
		HandyWorker s;
		s = this.handyWorkerService.create();
		final UserAccount ua = new UserAccount();
		ua.setPassword("hola");
		ua.setUsername("Antonio");
		ua.setAuthorities(s.getUserAccount().getAuthorities());

		s.setName("Antonio");
		s.setAddress("calle Arahal");
		s.setEmail("antonio@us.es");
		s.setPhone("654321123");
		s.setUserAccount(ua);

		Assert.isTrue(s.getUserAccount().getId() == ua.getId());
	}
	@Test
	public void testSaveHandyWorker() {
		HandyWorker h, saved;
		Collection<HandyWorker> hs;
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

		saved = this.handyWorkerService.save(h);
		hs = this.handyWorkerService.findAll();
		Assert.isTrue(hs.contains(saved));
	}

	@Test
	public void handyWorkerByTutorial() {
		final HandyWorker h = this.handyWorkerService.create();
		final UserAccount ua = new UserAccount();
		ua.setPassword("hola");
		ua.setUsername("Antonio");
		ua.setAuthorities(h.getUserAccount().getAuthorities());

		h.setName("Antonio");
		h.setAddress("calle Arahal");
		h.setEmail("antonio@us.es");
		h.setPhone("654321123");
		h.setUserAccount(ua);
		h.setScore(10);
		h.setMakeHandyWorker("makehandyworker");

		final Tutorial t = this.tutorialService.create();
		t.setHandyWorker(h);
		t.setTitle("Tutorial1");
		t.setMoment(new Date());
		t.setSummary("Summary");
		final Tutorial tsaved = this.tutorialService.save(t);
		final Section s = this.sectionService.create();
		s.setNumber(1);
		s.setPieceOfText("Piece");
		s.setTitle("Titulo");
		final Collection<Section> ss = this.sectionService.findAll();
		t.setSection(ss);

		final HandyWorker h2 = this.handyWorkerService.handyWorkerByTutorial(tsaved.getId());
		Assert.isTrue(h2.getId() == h.getId(), "HandyWorkerServiceTest.handyWorkerByTutorial -> Los handy Workers no son iguales y deberian");
	}

	@Test
	public void handyWorkerMoreTentPercentApplicatonsAccepted() {

		final HandyWorker h = this.handyWorkerService.create();
		final UserAccount ua = new UserAccount();
		ua.setPassword("hola");
		ua.setUsername("Antonio");
		ua.setAuthorities(h.getUserAccount().getAuthorities());
		h.setName("Antonio");
		h.setAddress("calle Arahal");
		h.setEmail("antonio@us.es");
		h.setPhone("654321123");
		h.setUserAccount(ua);
		h.setScore(10);
		h.setMakeHandyWorker("makehandyworker");
		final HandyWorker savedHandyWorker = this.handyWorkerService.save(h);

		Sponsor sponsor, saved;
		sponsor = this.sponsorService.create();
		final UserAccount uaSponsor = new UserAccount();
		uaSponsor.setPassword("hola");
		uaSponsor.setUsername("Antonio");
		uaSponsor.setAuthorities(sponsor.getUserAccount().getAuthorities());
		sponsor.setName("Antonio");
		sponsor.setAddress("calle Arahal");
		sponsor.setEmail("antonio@us.es");
		sponsor.setPhone("654321123");
		sponsor.setSurname("Segura");
		sponsor.setUserAccount(ua);
		saved = this.sponsorService.save(sponsor);

		CreditCard creditCard, savedCreditCard;
		creditCard = this.crediCardService.create();
		creditCard.setBrandName("Tarjeta1");
		creditCard.setCW(200);
		creditCard.setExpirationMonth(2);
		creditCard.setExpirationYear(1);
		creditCard.setNumber(12321321);
		creditCard.setHolderName("holdername");
		savedCreditCard = this.crediCardService.save(creditCard);

		Sponsorship sponsorship;
		//final Sponsorship savedship;
		sponsorship = this.sponsorshipService.create();
		sponsorship.setCreditCard(savedCreditCard);
		sponsorship.setLinkTargetPage("hola.com");
		sponsorship.setSponsor(saved);
		sponsorship.setUrlBanner("hola.com");
		//savedship = this.sponsorshipService.save(sponsorship);

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
		uaCustomer.setAuthorities(h.getUserAccount().getAuthorities());
		customer.setName("Antonio");
		customer.setAddress("calle Arahal");
		customer.setEmail("antonio@us.es");
		customer.setPhone("654321123");
		customer.setUserAccount(ua);
		customer.setScore(10);
		savedCustomer = this.customerService.save(customer);

		FixUpTask fix;
		final FixUpTask savedFix;
		fix = this.fixUpTaskService.create();
		fix.setAddress("adressFix");
		fix.setApplication(savedApp);
		fix.setCategory(savedCategory);
		fix.setCustomer(savedCustomer);
		fix.setDescription("Descripcion en fixUpTask");
		fix.setMaximunPrice(1.0);
		fix.setMoment(new Date());
		fix.setPeriodTime(12);
		fix.setWarranty(savedWarranty);
		fix.setTicker("123qweqwe2132");

		final Application app, savedApp;
		app = this.applicationService.create();
		app.setCreditCard(savedCreditCard);
		app.setFixUpTask(savedFix);
		app.setStatus(1);
		app.setHandyWorker(savedHandyWorker);
		app.setMoment(new Date());
		app.setPrice(12.);
		final Collection<HandyWorker> handyWorkers = this.handyWorkerService.handyWorkerMoreTentPercentApplicatonsAccepted();
		Assert.isTrue(handyWorkers.size() == 0, "HandyWorkerServiceTest.handyWorkerMoreTentPercentApplicatonsAccepted -> Los handy Workers no son iguales y deberian");
	}
}
