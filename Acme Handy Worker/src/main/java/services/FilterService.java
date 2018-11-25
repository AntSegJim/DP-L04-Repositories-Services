
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FilterRepository;
import security.LoginService;
import security.UserAccount;
import domain.Filter;
import domain.HandyWorker;

@Service
@Transactional
public class FilterService {

	@Autowired
	public FilterRepository		filterRepository;

	@Autowired
	public HandyWorkerService	handyWorkerService;


	public Filter create() {
		final Filter f = new Filter();
		f.setAddress("");
		f.setCategory(null);
		f.setDescription("");
		f.setEndDate(null);
		f.setHighPrice(0.);
		f.setLowPrice(0.);
		f.setStartDate(null);
		f.setTicker("");
		f.setWarranty(null);
		return f;
	}
	public Collection<Filter> findAll() {
		return this.filterRepository.findAll();
	}
	public Filter findOne(final int filterId) {
		return this.filterRepository.findOne(filterId);
	}
	public Filter save(final Filter filter) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		final Collection<HandyWorker> handyWorker = this.handyWorkerService.handyWorkerByUserAccount(userAccount.getId());
		Assert.isTrue(!handyWorker.isEmpty());
		return this.filterRepository.save(filter);
	}
	public void delete(final Filter filter) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		final Collection<HandyWorker> handyWorker = this.handyWorkerService.handyWorkerByUserAccount(userAccount.getId());
		Assert.isTrue(!handyWorker.isEmpty());
		this.filterRepository.delete(filter);
	}
}
