
package services;

import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.UserAccount;
import domain.Administrator;
import domain.Category;
import domain.MessageBox;
import domain.ProfileSocialNetwork;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository	adminRepo;
	@Autowired
	private MessageBoxService		messageBoxService;


	public Administrator create() {
		final Administrator admin = new Administrator();
		admin.setName("");
		admin.setMiddleName("");
		admin.setSurname("");
		admin.setPhoto("");
		admin.setEmail("");
		admin.setPhone("");
		admin.setAddress("");
		admin.setNumberSocialProfiles(0);
		//PREGUNTAR
		final UserAccount user = new UserAccount();
		user.setAuthorities(new HashSet<Authority>());
		final Authority ad = new Authority();
		ad.setAuthority(Authority.ADMIN);
		user.getAuthorities().add(ad);
		admin.setUserAccount(user);

		admin.setProfileSocialNetwork(new HashSet<ProfileSocialNetwork>());
		admin.setCategories(new HashSet<Category>());

		return admin;
	}

	//Listing
	public Collection<Administrator> findAll() {
		return this.adminRepo.findAll();
	}
	public Administrator findOne(final int adminId) {
		return this.adminRepo.findOne(adminId);
	}

	//Update
	public Administrator save(final Administrator admin) {
		Administrator res = null;
		Assert.isTrue(admin.getName() != null && admin.getName() != "" && admin.getSurname() != null && admin.getSurname() != "" && admin.getUserAccount() != null);

		final String regexEmail1 = "[^@]+@[^@]+\\.[a-zA-Z]{2,}";
		final Pattern patternEmail1 = Pattern.compile(regexEmail1);
		final Matcher matcherEmail1 = patternEmail1.matcher(admin.getEmail());

		final String regexEmail2 = "^[A-z0-9]+\\s*[A-z0-9\\s]*\\s\\<[A-z0-9]+\\@[A-z0-9]+\\.[A-z0-9.]+\\>";
		final Pattern patternEmail2 = Pattern.compile(regexEmail2);
		final Matcher matcherEmail2 = patternEmail2.matcher(admin.getEmail());

		final String regexEmail3 = "^[A-z0-9]+\\@$";
		final Pattern patternEmail3 = Pattern.compile(regexEmail3);
		final Matcher matcherEmail3 = patternEmail3.matcher(admin.getEmail());

		final String regexEmail4 = "^[A-z0-9]+\\s*[A-z0-9\\s]*\\s\\<[A-z0-9]+\\@\\>$";
		final Pattern patternEmail4 = Pattern.compile(regexEmail4);
		final Matcher matcherEmail4 = patternEmail4.matcher(admin.getEmail());

		Assert.isTrue(matcherEmail1.find() == true && matcherEmail2.find() == true && matcherEmail3.find() == true && matcherEmail4.find() == true, "CustomerService.save -> Correo inválido");

		if (admin.getPhone() != "" || admin.getPhone() != null) {
			final String regexTelefono = "^\\+[1-9][0-9]{0,2}\\ \\([1-9][0-9]{0,2}\\)\\ [0-9]{4,}$|^\\+[1-9][0-9]{0,2}\\ [0-9]{4,}$|^[0-9]{4,}$";
			final Pattern patternTelefono = Pattern.compile(regexTelefono);
			final Matcher matcherTelefono = patternTelefono.matcher(admin.getEmail());
			Assert.isTrue(matcherTelefono.find() == true, "CustomerService.save -> Correo inválido");
		}

		res = this.adminRepo.save(admin);
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
	//Delete
	public void delete(final Administrator admin) {
		this.adminRepo.delete(admin);
	}

}
