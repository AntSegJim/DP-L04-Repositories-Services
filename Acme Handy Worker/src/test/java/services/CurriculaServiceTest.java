
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import security.UserAccount;
import domain.Curricula;
import domain.HandyWorker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CurriculaServiceTest {

	@Autowired
	private CurriculaService	curriS;
	@Autowired
	private HandyWorkerService	handyWorkerService;


	@Test
	public void testCreateCurricula() {
		Curricula curricula;
		curricula = this.curriS.create();
		HandyWorker h;
		final HandyWorker saved;
		h = this.handyWorkerService.create();
		final UserAccount ua = new UserAccount();
		ua.setPassword("hola");
		ua.setUsername("Antonio");
		ua.setAuthorities(h.getUserAccount().getAuthorities());

		h.setName("Antonio");
		h.setAddress("calle Arahal");
		h.setEmail("antonio@us.es");
		h.setPhone("654321123");
		h.setUserAccount(ua);
		h.setSurname("SurnameHandy");
		saved = this.handyWorkerService.save(h);

		curricula.setHandyWorker(saved);
		Assert.isTrue(curricula.getTicker() != null);
	}
	@Test
	public void testSaveCurricula() {
		Curricula curricula, savedC;
		Collection<Curricula> cs;
		curricula = this.curriS.create();
		HandyWorker h;
		final HandyWorker savedH;
		h = this.handyWorkerService.create();
		final UserAccount ua = new UserAccount();
		ua.setPassword("hola");
		ua.setUsername("Antonio");
		ua.setAuthorities(h.getUserAccount().getAuthorities());

		h.setName("Antonio");
		h.setAddress("calle Arahal");
		h.setEmail("antonio@us.es");
		h.setPhone("654321123");
		h.setUserAccount(ua);
		h.setSurname("SurnameHandy");
		savedH = this.handyWorkerService.save(h);

		curricula.setHandyWorker(savedH);
		savedC = this.curriS.save(curricula);
		cs = this.curriS.findAll();
		Assert.isTrue(cs.contains(savedC));
	}

}
