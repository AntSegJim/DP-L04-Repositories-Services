
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import security.UserAccount;
import utilities.AbstractTest;
import domain.ProfileSocialNetwork;
import domain.Referee;

@Transactional
public class RefereeServiceTest extends AbstractTest {

	@Autowired
	private RefereeService				refereeService;

	@Autowired
	private ProfileSocialNetworkService	profileSocialNetworkService;


	@Test
	public void testCreateReferee() {

		Referee referee;
		referee = this.refereeService.create();
		final ProfileSocialNetwork profile = this.profileSocialNetworkService.create();

		profile.setNickName("jesus");
		profile.setNameSocialNetwork("github");
		profile.setLinkProfile("https://github.com/");

		final Collection<ProfileSocialNetwork> cprofile = new HashSet<>();
		cprofile.add(profile);
		final UserAccount uA = new UserAccount();
		uA.setPassword("hola");
		uA.setUsername("Jesus");
		uA.setAuthorities(referee.getUserAccount().getAuthorities());

		referee.setAddress("Dirección prueba");
		referee.setEmail("jesuseli@gmail.com");
		referee.setMiddleName("prueba");
		referee.setName("Pablo");
		referee.setNumberSocialProfiles(1);
		referee.setPhone("654456653");
		referee.setPhoto("https://hangouts.google.com/");
		referee.setProfileSocialNetwork(cprofile);
		referee.setSurname("Perez");
		referee.setUserAccount(uA);

		Assert.isTrue(referee.getAddress() == "Dirección prueba" && referee.getEmail() == "jesuseli@gmail.com" && referee.getMiddleName() == "prueba" && referee.getName() == "Pablo" && referee.getNumberSocialProfiles() == 1
			&& referee.getPhone() == "654456653" && referee.getPhoto() == "https://hangouts.google.com/" && referee.getProfileSocialNetwork() == cprofile && referee.getSurname() == "Perez" && referee.getUserAccount() == uA);

	}

	@Test
	public void testSaveReferee() {
		super.authenticate("admin");

		Referee referee, saved;
		final Collection<Referee> referees;
		referee = this.refereeService.create();
		final ProfileSocialNetwork profile = this.profileSocialNetworkService.create();

		profile.setNickName("jesus");
		profile.setNameSocialNetwork("github");
		profile.setLinkProfile("https://github.com/");

		final Collection<ProfileSocialNetwork> cprofile = new HashSet<>();
		cprofile.add(profile);
		final UserAccount uA = new UserAccount();
		uA.setPassword("hola");
		uA.setUsername("Jesus");
		uA.setAuthorities(referee.getUserAccount().getAuthorities());

		referee.setAddress("Dirección prueba");
		referee.setEmail("jesuseli@gmail.com");
		referee.setMiddleName("prueba");
		referee.setName("Pablo");
		referee.setNumberSocialProfiles(1);
		referee.setPhone("654456653");
		referee.setPhoto("https://hangouts.google.com/");
		referee.setProfileSocialNetwork(cprofile);
		referee.setSurname("Perez");
		referee.setUserAccount(uA);

		saved = this.refereeService.save(referee);
		referees = this.refereeService.findAll();
		Assert.isTrue(referees.contains(saved));

		super.authenticate(null);

	}

}
