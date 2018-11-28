
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CurriculaRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Curricula;

@Service
@Transactional
public class CurriculaService {

	@Autowired
	private CurriculaRepository	CRepo;

	@Autowired
	private HandyWorkerService	handyWorkerService;


	public Curricula create() {
		final Curricula curricula = new Curricula();
		final UserAccount user = LoginService.getPrincipal();

		curricula.setTicker("");
		curricula.setHandyWorker(this.handyWorkerService.handyWorkerUserAccount(user.getId()));
		return curricula;
	}

	//listing
	public Collection<Curricula> findAll() {
		return this.CRepo.findAll();
	}
	public Curricula findOne(final int curriculaId) {
		return this.CRepo.findOne(curriculaId);
	}

	//updating
	public Curricula save(final Curricula curricula) {
		final UserAccount user = LoginService.getPrincipal();
		Assert.isTrue(user.getAuthorities().contains(Authority.HANDYWORKER));

		Assert.isTrue(curricula != null && curricula.getTicker() != null && curricula.getTicker() != "");
		return this.CRepo.save(curricula);
	}

	//deleting
	//	public void delete(final Curricula curricula) {
	//		this.CRepo.delete(curricula);
	//	}
}
