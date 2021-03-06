
package services;

import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.Authority;
import security.UserAccount;
import domain.Customer;
import domain.Endorsement;
import domain.MessageBox;
import domain.ProfileSocialNetwork;

@Service
@Transactional
public class CustomerService {

	//------------------------Managed repository---------------------
	@Autowired
	private CustomerRepository	customerRepository;
	@Autowired
	private MessageBoxService	messageBoxService;


	//------------------------Simple CRUD methods---------------------
	public Customer create() {
		final Customer c = new Customer();
		c.setAddress("");
		c.setEmail("");
		c.setEndorseCustomer(new HashSet<Endorsement>());
		c.setMiddleName("");
		c.setName("");
		c.setNumberSocialProfiles(0);
		c.setPhone("");
		c.setPhoto("");
		c.setProfileSocialNetwork(new HashSet<ProfileSocialNetwork>());
		c.setReceiveEndorseFromCustomer(new HashSet<Endorsement>());
		c.setScore(0);
		c.setSurname("");
		//PREGUNTAR
		final UserAccount user = new UserAccount();
		user.setAuthorities(new HashSet<Authority>());
		final Authority ad = new Authority();
		ad.setAuthority(Authority.CUSTOMER);
		user.getAuthorities().add(ad);
		c.setUserAccount(user);

		return c;
	}
	public Collection<Customer> findAll() {
		return this.customerRepository.findAll();
	}

	public Customer findOne(final int customerId) {
		return this.customerRepository.findOne(customerId);
	}
	public Customer save(final Customer c) {
		Customer res = null;

		Assert.isTrue(c != null && c.getName() != null && c.getSurname() != null && c.getName() != "" && c.getSurname() != "" && c.getUserAccount() != null, "CustomerService.save -> Name or Surname invalid");

		final String regexEmail1 = "[^@]+@[^@]+\\.[a-zA-Z]{2,}";
		final Pattern patternEmail1 = Pattern.compile(regexEmail1);
		final Matcher matcherEmail1 = patternEmail1.matcher(c.getEmail());

		final String regexEmail2 = "^[A-z0-9]+\\s*[A-z0-9\\s]*\\s\\<[A-z0-9]+\\@[A-z0-9]+\\.[A-z0-9.]+\\>";
		final Pattern patternEmail2 = Pattern.compile(regexEmail2);
		final Matcher matcherEmail2 = patternEmail2.matcher(c.getEmail());
		Assert.isTrue(matcherEmail1.find() == true && matcherEmail2.find() == true, "CustomerService.save -> Correo inv�lido");

		if (c.getPhone() != "" || c.getPhone() != null) {
			final String regexTelefono = "^\\+[1-9][0-9]{0,2}\\ \\([1-9][0-9]{0,2}\\)\\ [0-9]{4,}$|^\\+[1-9][0-9]{0,2}\\ [0-9]{4,}$|^[0-9]{4,}$";
			final Pattern patternTelefono = Pattern.compile(regexTelefono);
			final Matcher matcherTelefono = patternTelefono.matcher(c.getEmail());
			Assert.isTrue(matcherTelefono.find() == true, "CustomerService.save -> Correo inv�lido");
		}
		res = this.customerRepository.save(c);
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
	public Customer customerByUserAccount(final Integer id) {
		return this.customerRepository.customerUserAccount(id);
	}
}
