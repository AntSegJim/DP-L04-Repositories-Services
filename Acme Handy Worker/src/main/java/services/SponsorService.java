
package services;

import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SponsorRepository;
import security.Authority;
import security.UserAccount;
import domain.MessageBox;
import domain.ProfileSocialNetwork;
import domain.Sponsor;

@Service
@Transactional
public class SponsorService {

	@Autowired
	private SponsorRepository	sponsorRepository;
	@Autowired
	private MessageBoxService	messageBoxService;


	public Sponsor create() {
		final Sponsor s = new Sponsor();
		s.setAddress("");
		s.setEmail("");
		s.setMiddleName("");
		s.setName("");
		s.setNumberSocialProfiles(0);
		s.setPhone("");
		s.setPhoto("");
		s.setProfileSocialNetwork(new HashSet<ProfileSocialNetwork>());
		s.setSurname("");
		//PREGUNTAR
		final UserAccount user = new UserAccount();
		user.setAuthorities(new HashSet<Authority>());
		final Authority ad = new Authority();
		ad.setAuthority(Authority.SPONSOR);
		user.getAuthorities().add(ad);
		s.setUserAccount(user);

		return s;
	}

	public Collection<Sponsor> findAll() {
		return this.sponsorRepository.findAll();
	}

	public Sponsor findOne(final int sponsorId) {
		return this.sponsorRepository.findOne(sponsorId);
	}

	public Sponsor save(final Sponsor s) {
		Sponsor res = null;
		Assert.isTrue(s.getName() != null && s.getSurname() != null && s.getName() != "" && s.getSurname() != "" && s.getUserAccount() != null, "SponsorService.save -> Name or Surname invalid");

		final String regex = "[^@]+@[^@]+\\.[a-zA-Z]{2,}";
		final Pattern pattern = Pattern.compile(regex);
		final Matcher matcher = pattern.matcher(s.getEmail());
		Assert.isTrue(matcher.find() == true, "SponsorService.save -> Correo inválido");

		res = this.sponsorRepository.save(s);
		final MessageBox mb1 = this.messageBoxService.create();
		mb1.setName("inbox");
		mb1.setActor(res);
		final MessageBox mb2 = this.messageBoxService.create();
		mb2.setName("outbox");
		mb2.setActor(res);
		final MessageBox mb3 = this.messageBoxService.create();
		mb3.setName("spambox");
		mb3.setActor(res);
		final MessageBox mb4 = this.messageBoxService.create();
		mb4.setName("trashbox");
		mb4.setActor(res);

		return res;
	}
}
