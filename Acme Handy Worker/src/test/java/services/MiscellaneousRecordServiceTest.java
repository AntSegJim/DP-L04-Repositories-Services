
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
import domain.HandyWorker;
import domain.MiscellaneousRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class MiscellaneousRecordServiceTest extends AbstractTest {

	@Autowired
	private MiscellaneousRecordService	miscellaneousRecordService;

	@Autowired
	private CurriculaService			curriS;

	@Autowired
	private HandyWorkerService			handyWorkerService;


	//---------------------- Test ----------------------
	@Test
	public void testCreateMiscellaneousRecord() {

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
		MiscellaneousRecord miscellaneousRecord;
		miscellaneousRecord = this.miscellaneousRecordService.create();
		miscellaneousRecord.setTitle("Acme Handy Worker");
		miscellaneousRecord.setLink("https://web.whatsapp.com/");
		miscellaneousRecord.setComments(comments);
		miscellaneousRecord.setCurricula(curricula);

		Assert.isTrue(miscellaneousRecord.getTitle() != null);
		Assert.isTrue(miscellaneousRecord.getLink() != null);
		Assert.isTrue(miscellaneousRecord.getCurricula() != null);

	}

	@Test
	public void testSaveMiscellaneousRecord() {
		super.authenticate("admin");
		MiscellaneousRecord miscellaneousRecord;
		final MiscellaneousRecord saved;
		Collection<MiscellaneousRecord> miscellaneousRecords;

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
		miscellaneousRecord = this.miscellaneousRecordService.create();
		miscellaneousRecord.setTitle("Acme Handy Worker");
		miscellaneousRecord.setLink("https://web.whatsapp.com/");
		miscellaneousRecord.setComments(comments);
		miscellaneousRecord.setCurricula(curricula);

		saved = this.miscellaneousRecordService.save(miscellaneousRecord);
		miscellaneousRecords = this.miscellaneousRecordService.findAll();
		Assert.isTrue(miscellaneousRecords.contains(saved));
		super.authenticate(null);
	}

	@Test
	public void testDeleteMiscellaneousRecord() {
		super.authenticate("admin");
		MiscellaneousRecord miscellaneousRecord;
		final MiscellaneousRecord saved;
		Collection<MiscellaneousRecord> miscellaneousRecords;

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
		miscellaneousRecord = this.miscellaneousRecordService.create();
		miscellaneousRecord.setTitle("Acme Handy Worker");
		miscellaneousRecord.setLink("https://web.whatsapp.com/");
		miscellaneousRecord.setComments(comments);
		miscellaneousRecord.setCurricula(curricula);

		saved = this.miscellaneousRecordService.save(miscellaneousRecord);

		this.miscellaneousRecordService.delete(saved);
		miscellaneousRecords = this.miscellaneousRecordService.findAll();
		Assert.isTrue(!miscellaneousRecords.contains(saved));

		super.authenticate(null);
	}

}
