
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FilterRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Category;
import domain.Filter;
import domain.Warranty;

@Service
@Transactional
public class FilterService {

	@Autowired
	private FilterRepository	filterRepository;
	@Autowired
	private HandyWorkerService	handyWorkerService;
	@Autowired
	private CategoryService		CService;
	@Autowired
	private WarrantyService		WService;


	public Filter create() {
		final Filter f = new Filter();
		final Category ca = this.CService.create();
		final Warranty wa = this.WService.create();
		f.setAddress("");
		f.setCategory(ca);
		f.setDescription("");
		f.setEndDate(null);
		f.setHighPrice(0.);
		f.setLowPrice(0.);
		f.setStartDate(new Date());
		f.setTicker("");
		f.setWarranty(wa);
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
		Assert.isTrue(userAccount.getAuthorities().contains(Authority.HANDYWORKER));
		//		final Collection<HandyWorker> handyWorker = this.handyWorkerService.handyWorkerByUserAccount(userAccount.getId());
		//		Assert.isTrue(!handyWorker.isEmpty());

		return this.filterRepository.save(filter);
	}
	public void delete(final Filter filter) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().contains(Authority.HANDYWORKER));
		//		final Collection<HandyWorker> handyWorker = this.handyWorkerService.handyWorkerByUserAccount(userAccount.getId());
		//		Assert.isTrue(!handyWorker.isEmpty());
		this.filterRepository.delete(filter);
	}
}
