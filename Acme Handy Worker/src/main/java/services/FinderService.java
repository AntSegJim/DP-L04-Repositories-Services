
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.FinderRepository;
import domain.Filter;
import domain.Finder;
import domain.FixUpTask;

@Service
@Transactional
public class FinderService {

	@Autowired
	private FinderRepository	finderRepository;
	@Autowired
	private FilterService		FService;


	// ---------- Simple CRUD methods ----------

	public Finder create() {
		final Finder f = new Finder();
		final Filter fi = this.FService.create();

		f.setFilter(fi);
		f.setFixUpTask(new HashSet<FixUpTask>());
		f.setMoment(new Date());
		return f;
	}
	public Collection<Finder> findAll() {
		return this.finderRepository.findAll();
	}
	public Finder findOne(final int finderId) {
		return this.finderRepository.findOne(finderId);
	}
	public Finder save(final Finder f) {
		Finder res = null;
		if (f != null && f.getMoment() != null)
			res = this.finderRepository.save(f);
		return res;
		//return this.finderRepository.save(f);
	}
	public void delete(final Finder f) {
		this.finderRepository.delete(f);
	}
}
