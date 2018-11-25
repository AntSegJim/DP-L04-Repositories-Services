
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.FinderRepository;
import domain.Finder;

@Service
@Transactional
public class FinderService {

	@Autowired
	private FinderRepository	finderRepository;


	// ---------- Simple CRUD methods ----------

	public Finder create() {
		final Finder f = new Finder();
		f.setFilter(null);
		f.setFixUpTask(null);
		f.setMoment(null);
		return f;
	}
	public Collection<Finder> findAll() {
		return this.finderRepository.findAll();
	}
	public Finder findOne(final int finderId) {
		return this.finderRepository.findOne(finderId);
	}
	public Finder save(final Finder f) {
		return this.finderRepository.save(f);
	}
	public void delete(final Finder f) {
		this.finderRepository.delete(f);
	}
}
