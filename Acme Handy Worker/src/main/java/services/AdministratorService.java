
package services;

import java.util.Collection;
import java.util.HashSet;

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
		//Restriccion de email,telefono

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
