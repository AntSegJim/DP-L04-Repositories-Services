
package services;

import java.util.Collection;
import java.util.Date;

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
		curricula.setTicker(CurriculaService.generateTicker(new Date()));
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
		final Collection<String> tickers = this.CRepo.tickerByCurricula();
		Assert.isTrue(user.getAuthorities().contains(Authority.HANDYWORKER));

		Assert.isTrue(curricula != null && curricula.getTicker() != null && curricula.getTicker() != "");
		return this.CRepo.save(curricula);
	}

	//deleting
	//	public void delete(final Curricula curricula) {
	//		this.CRepo.delete(curricula);
	//	}

	public static String generateTicker(final Date date) {
		final int tam = 6;
		final Integer ano = date.getYear() + 1900;
		final Integer mes = date.getMonth() + 1;
		final Integer dia = date.getDate();
		final String d = ano.toString().substring(ano.toString().length() - 2, ano.toString().length()) + mes.toString() + dia.toString();

		String ticker = "-";
		final String a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		for (int i = 0; i < tam; i++) {
			final Integer random = (int) (Math.floor(Math.random() * a.length()) % a.length());
			ticker = ticker + a.charAt(random);
		}

		return d + ticker;

	}

}
