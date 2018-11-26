
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.PhaseRepository;
import domain.Application;
import domain.Phase;

@Service
@Transactional
public class PhaseService {

	@Autowired
	private PhaseRepository		phaseRepository;

	@Autowired
	private ApplicationService	applicationservice;


	public Phase create() {
		final Phase res = new Phase();

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
		return this.phaseRepository.findOne(phaseId);
	}

	//updating
	public Phase save(final Phase phase) {
		return this.phaseRepository.save(phase);
	}

	//deleting
	public void delete(final Phase phase) {
		this.phaseRepository.delete(phase);
	}

}
