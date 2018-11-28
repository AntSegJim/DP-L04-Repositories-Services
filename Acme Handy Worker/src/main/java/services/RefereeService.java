
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RefereeRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Referee;

@Service
@Transactional
public class RefereeService {

	@Autowired
	private RefereeRepository	refereeRepository;


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
	public Referee save(final Referee referee) {
		final UserAccount ac = LoginService.getPrincipal();
		Assert.isTrue(ac.getAuthorities().contains(Authority.ADMIN));
		Assert.isTrue(!(referee.getName().equals("") && !(referee.getName().equals(null))));
		Assert.isTrue(!(referee.getSurname().equals("") && !(referee.getSurname().equals(null))));
		return this.refereeRepository.save(referee);
	}

	//deleting
	public void delete(final Referee referee) {
		this.refereeRepository.delete(referee);
	}
	//------------------------Other business methods---------------------
	public Referee refereeByUserAccount(final Integer userAccountId) {
		return this.refereeRepository.refereeUserAccount(userAccountId);
	}

}
