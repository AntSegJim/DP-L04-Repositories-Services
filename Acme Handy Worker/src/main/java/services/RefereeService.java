
package services;

import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RefereeRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.MessageBox;
import domain.ProfileSocialNetwork;
import domain.Referee;

@Service
@Transactional
public class RefereeService {

	@Autowired
	private RefereeRepository	refereeRepository;
	@Autowired
	private MessageBoxService	messageBoxService;


	//Metodos CRUD

	public Referee create() {
		final Referee res = new Referee();

		res.setName("");
		res.setMiddleName("");
		res.setSurname("");
		res.setPhoto("");
		res.setEmail("");
		res.setPhone("");
		res.setAddress("");
		res.setNumberSocialProfiles(0);
		//PREGUNTAR
		final UserAccount user = new UserAccount();
		user.setAuthorities(new HashSet<Authority>());
		final Authority ad = new Authority();
		ad.setAuthority(Authority.REFEREE);
		user.getAuthorities().add(ad);
		res.setUserAccount(user);

		res.setProfileSocialNetwork(new HashSet<ProfileSocialNetwork>());
		return res;
	}

	public Referee create(final String name, final String middleName, final String surname, final String photo, final String email, final String phone, final String address, final Integer numberSocialProfiles) {
		final Referee referee = new Referee();
		referee.setName(name);
		referee.setMiddleName(middleName);
		referee.setSurname(surname);
		referee.setPhoto(photo);
		referee.setEmail(email);
		referee.setPhone(phone);
		referee.setAddress(address);
		referee.setNumberSocialProfiles(numberSocialProfiles);

		return referee;
	}
	//listing
	public Collection<Referee> findAll() {
		return this.refereeRepository.findAll();
	}

	public Referee findOne(final int refereeId) {
		return this.refereeRepository.findOne(refereeId);
	}

	//updating
	public Referee save(final Referee r) {
		final UserAccount ac = LoginService.getPrincipal();
		Assert.isTrue(ac.getAuthorities().contains(Authority.ADMIN));

		Referee res = null;

		Assert.isTrue(r != null && r.getName() != null && r.getSurname() != null && r.getName() != "" && r.getSurname() != "" && r.getUserAccount() != null, "RefereeService.save -> Name or Surname invalid");

		final String regex = "[^@]+@[^@]+\\.[a-zA-Z]{2,}";
		final Pattern pattern = Pattern.compile(regex);
		final Matcher matcher = pattern.matcher(r.getEmail());
		Assert.isTrue(matcher.find() == true, "RefereeService.save -> Correo inválido");

		res = this.refereeRepository.save(r);
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

	//------------------------Other business methods---------------------
	public Referee refereeByUserAccount(final Integer userAccountId) {
		return this.refereeRepository.refereeUserAccount(userAccountId);
	}

}
