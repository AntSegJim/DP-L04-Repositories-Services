
package services;

import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.HandyWorkerRepository;
import security.Authority;
import security.UserAccount;
import domain.Application;
import domain.Endorsement;
import domain.HandyWorker;
import domain.MessageBox;
import domain.ProfileSocialNetwork;

@Service
@Transactional
public class HandyWorkerService {

	@Autowired
	private HandyWorkerRepository	handyWorkerRepository;
	@Autowired
	private MessageBoxService		messageBoxService;


	public HandyWorker create() {
		final HandyWorker h = new HandyWorker();
		h.setAddress("");
		h.setEmail("");
		h.setEndorseHWorker(new HashSet<Endorsement>());
		h.setMiddleName("");
		h.setName("");
		h.setNumberSocialProfiles(0);
		h.setPhone("");
		h.setPhoto("");
		h.setProfileSocialNetwork(new HashSet<ProfileSocialNetwork>());
		h.setReceiveEndorseFromHWorker(new HashSet<Endorsement>());
		h.setScore(0);
		h.setSurname("");
		//PREGUNTAR
		final UserAccount user = new UserAccount();
		user.setAuthorities(new HashSet<Authority>());
		final Authority ad = new Authority();
		ad.setAuthority(Authority.HANDYWORKER);
		user.getAuthorities().add(ad);
		h.setUserAccount(user);

		h.setMakeHandyWorker("");
		h.setApplication(new HashSet<Application>());
		return h;
	}

	public Collection<HandyWorker> findAll() {
		return this.handyWorkerRepository.findAll();
	}

	public HandyWorker findOne(final int handyWorkerId) {
		return this.handyWorkerRepository.findOne(handyWorkerId);
	}

	public HandyWorker save(final HandyWorker h) {
		HandyWorker res = null;

		Assert.isTrue(h.getName() != null && h.getSurname() != null && h.getName() != "" && h.getSurname() != "" && h.getUserAccount() != null, "HandyWorkerService.save -> Name or Surname invalid");

		final String regex = "[^@]+@[^@]+\\.[a-zA-Z]{2,}";
		final Pattern pattern = Pattern.compile(regex);
		final Matcher matcher = pattern.matcher(h.getEmail());
		Assert.isTrue(matcher.find() == true, "HandyWorkerService.save -> Correo inválido");

		res = this.handyWorkerRepository.save(h);
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

	//Other bussines methods
	public HandyWorker handyWorkerByTutorial(final Integer tutorialId) {
		return this.handyWorkerRepository.handyWorkerInfo(tutorialId);
	}

	public Collection<HandyWorker> handyWorkerMoreTentPercentApplicatonsAccepted() {
		return this.handyWorkerRepository.handyWorkerMoreTentPercentApplicatonsAccepted();
	}

	//	public Collection<HandyWorker> handyWorkerByUserAccount(final Integer userAccountId) {
	//		return this.handyWorkerByUserAccount(userAccountId);
	//	}

	//Añadido por jesus para un metodo en phaseService
	public HandyWorker handyWorkerUserAccount(final Integer id) {
		return this.handyWorkerRepository.handyWorkerUserAccount(id);
	}
}
