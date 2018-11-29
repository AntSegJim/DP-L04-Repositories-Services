
package services;

import java.util.Collection;
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
import domain.Curricula;
import domain.EndoserRecord;
import domain.HandyWorker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class EndoserRecordServiceTest extends AbstractTest {

	@Autowired
	private EndoserRecordService	endoserRecordService;
	@Autowired
	private CurriculaService		curriS;

	@Autowired
	private HandyWorkerService		handyWorkerService;


	//---------------------- Test ----------------------
	@Test
	public void testCreateEndorseRecord() {

		//pasos previos

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

		//fin

		final Collection<String> comments = new HashSet<String>();
		comments.add("comentario1");
		EndoserRecord endorserRecord;
		endorserRecord = this.endoserRecordService.create();
		endorserRecord.setName("Acme Handy Worker");
		endorserRecord.setEmail("cristian@hotmail.com");
		endorserRecord.setPhoneNumber("657678987");
		endorserRecord.setLinkedln("https://web.whatsapp.com/");
		endorserRecord.setComments(comments);
		endorserRecord.setCurricula(curricula);

		Assert.isTrue(endorserRecord.getName() != null && endorserRecord.getName() != "");
		//PUEDEN FALTAR MÁS RESTRICCIONES

	}

	@Test
	public void testSaveEndorseRecord() {
		super.authenticate("admin");
		EndoserRecord endorserRecord;
		final EndoserRecord saved;
		Collection<EndoserRecord> endorseRecords;

		Curricula curricula;
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

		//fin

		final Collection<String> comments = new HashSet<String>();
		comments.add("comentario1");
		endorserRecord = this.endoserRecordService.create();
		endorserRecord.setName("Acme Handy Worker");
		endorserRecord.setEmail("cristian@hotmail.com");
		endorserRecord.setPhoneNumber("657678987");
		endorserRecord.setLinkedln("https://web.whatsapp.com/");
		endorserRecord.setComments(comments);
		endorserRecord.setCurricula(curricula);

		saved = this.endoserRecordService.save(endorserRecord);
		endorseRecords = this.endoserRecordService.findAll();
		Assert.isTrue(endorseRecords.contains(saved));
		super.authenticate(null);
	}

	@Test
	public void testDeleteEndorseRecord() {
		super.authenticate("admin");
		EndoserRecord endorserRecord;
		final EndoserRecord saved;
		final Collection<EndoserRecord> endoserRecords;

		Curricula curricula;
		curricula = this.curriS.create();
		HandyWorker h;
		final HandyWorker savedHandy;
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
		savedHandy = this.handyWorkerService.save(h);

		curricula.setHandyWorker(savedHandy);

		//fin

		final Collection<String> comments = new HashSet<String>();
		comments.add("comentario1");
		endorserRecord = this.endoserRecordService.create();
		endorserRecord.setName("Acme Handy Worker");
		endorserRecord.setEmail("cristian@hotmail.com");
		endorserRecord.setPhoneNumber("657678987");
		endorserRecord.setLinkedln("https://web.whatsapp.com/");
		endorserRecord.setComments(comments);
		endorserRecord.setCurricula(curricula);

		saved = this.endoserRecordService.save(endorserRecord);

		this.endoserRecordService.delete(saved);
		endoserRecords = this.endoserRecordService.findAll();
		Assert.isTrue(!endoserRecords.contains(savedHandy));

		super.authenticate(null);
	}

}
