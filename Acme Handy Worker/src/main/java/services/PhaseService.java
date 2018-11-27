
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PhaseRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Application;
import domain.HandyWorker;
import domain.Phase;

@Service
@Transactional
public class PhaseService {

	@Autowired
	private PhaseRepository		phaseRepository;

	@Autowired
	private HandyWorkerService	handyWorkerService;


	public Phase create() {
		final Phase res = new Phase();
		res.setTitle("");
		res.setDescription("");
		res.setStartMoment(null);
		res.setEndMoment(null);
		res.setApplication(new Application());
		return res;
	}

	public Phase create(final String title, final String description, final Date startMoment, final Date endMoment, final Application application) {
		final Phase phase = new Phase();
		phase.setTitle(title);
		phase.setDescription(description);
		phase.setStartMoment(startMoment);
		phase.setEndMoment(endMoment);
		phase.setApplication(application);

		return phase;
	}
	//listing
	public Collection<Phase> findAll() {
		return this.phaseRepository.findAll();
	}

	public Phase findOne(final int phaseId) {
		final UserAccount ac = LoginService.getPrincipal();
		Assert.isTrue(ac.getAuthorities().contains(Authority.HANDYWORKER));
		return this.phaseRepository.findOne(phaseId);
	}

	//updating
	public Phase save(final Phase phase) {
		final UserAccount ac = LoginService.getPrincipal();
		Assert.isTrue(ac.getAuthorities().contains(Authority.HANDYWORKER));

		Assert.isTrue(!(phase.getTitle().equals("") && !(phase.getTitle().equals(null))));
		Assert.isTrue(!(phase.getStartMoment().equals(null) && !(phase.getStartMoment().before(Calendar.getInstance().getTime()))));
		return this.phaseRepository.save(phase);
	}

	//deleting
	public void delete(final Phase phase) {
		final UserAccount ac = LoginService.getPrincipal();
		Assert.isTrue(ac.getAuthorities().contains(Authority.HANDYWORKER));
		this.phaseRepository.delete(phase);
	}

	public Collection<Phase> findAllByHandyWorker() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		final HandyWorker c = this.handyWorkerService.handyWorkerUserAccount(userAccount.getId());
		return this.phaseRepository.findAllHandyWorkerPhase(c.getId());
	}

}
