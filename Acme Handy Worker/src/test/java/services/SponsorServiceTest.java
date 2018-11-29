
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
import utilities.AbstractTest;
import domain.Sponsor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SponsorServiceTest extends AbstractTest {

	@Autowired
	private SponsorService	sponsorService;


	//---------------------- Test ----------------------
	@Test
	public void testCreateSponsor() {
		Sponsor s;
		s = this.sponsorService.create();
		final UserAccount ua = new UserAccount();
		ua.setPassword("hola");
		ua.setUsername("Antonio");
		ua.setAuthorities(s.getUserAccount().getAuthorities());

		s.setName("Antonio");
		s.setAddress("calle Arahal");
		s.setEmail("antonio@us.es");
		s.setPhone("654321123");
		s.setSurname("Segura");
		s.setUserAccount(ua);

		Assert.isTrue(s.getUserAccount().getId() == ua.getId());
	}
	@Test
	public void testSaveSponsor() {
		Sponsor s, saved;
		Collection<Sponsor> ss;
		s = this.sponsorService.create();
		final UserAccount ua = new UserAccount();
		ua.setPassword("hola");
		ua.setUsername("Antonio");
		ua.setAuthorities(s.getUserAccount().getAuthorities());

		s.setName("Antonio");
		s.setAddress("calle Arahal");
		s.setEmail("antonio@us.es");
		s.setPhone("654321123");
		s.setSurname("Segura");
		s.setUserAccount(ua);

		saved = this.sponsorService.save(s);
		ss = this.sponsorService.findAll();
		Assert.isTrue(ss.contains(saved));
	}

}
