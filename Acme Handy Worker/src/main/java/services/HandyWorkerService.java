
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.HandyWorkerRepository;
import domain.HandyWorker;

@Service
@Transactional
public class HandyWorkerService {

	@Autowired
	private HandyWorkerRepository	handyWorkerRepository;


	public HandyWorker create() {
		final HandyWorker h = new HandyWorker();
		h.setAddress("");
		h.setEmail("");
		h.setEndorseHWorker(null);
		h.setMiddleName("");
		h.setName("");
		h.setNumberSocialProfiles(0);
		h.setPhone("");
		h.setPhoto("");
		h.setProfileSocialNetwork(null);
		h.setReceiveEndorseFromHWorker(null);
		h.setScore(0);
		h.setSurname("");
		h.setUserAccount(null);
		h.setMakeHandyWorker("");
		return h;
	}

	public Collection<HandyWorker> findAll() {
		return this.handyWorkerRepository.findAll();
	}

	public HandyWorker findOne(final int handyWorkerId) {
		return this.handyWorkerRepository.findOne(handyWorkerId);
	}

	public HandyWorker save(final HandyWorker handyWorker) {
		return this.handyWorkerRepository.save(handyWorker);
	}

	public void delete(final HandyWorker handyWorker) {
		this.handyWorkerRepository.delete(handyWorker);
	}

	//Other bussines methods
	//como lo tenemos
	public HandyWorker handyWorkerByTutorial(final Integer tutorialId) {
		return this.handyWorkerRepository.handyWorkerInfo(tutorialId);
	}
	//como yo creo que es
	public Collection<HandyWorker> handyWorkersByTutorial(final Integer tutorialId) {
		return this.handyWorkerRepository.handyWorkersInfo(tutorialId);
	}

	public Collection<HandyWorker> handyWorkerMoreTentPercentApplicatonsAccepted() {
		return this.handyWorkerMoreTentPercentApplicatonsAccepted();
	}

	public Collection<HandyWorker> handyWorkerByUserAccount(final Integer userAccountId) {
		return this.handyWorkerByUserAccount(userAccountId);
	}
}
