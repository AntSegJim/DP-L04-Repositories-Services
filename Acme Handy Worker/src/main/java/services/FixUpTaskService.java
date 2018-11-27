
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FixUpTaskRepository;
import security.LoginService;
import security.UserAccount;
import domain.Application;
import domain.Category;
import domain.Customer;
import domain.FixUpTask;
import domain.Warranty;

@Service
@Transactional
public class FixUpTaskService {

	@Autowired
	private FixUpTaskRepository	fixUpTaskRepository;
	@Autowired
	private CustomerService		customerService;
	@Autowired
	private CategoryService		CService;
	@Autowired
	private WarrantyService		WService;


	public FixUpTask create() {
		final FixUpTask f = new FixUpTask();
		final Category ca = this.CService.create();
		final Warranty wa = this.WService.create();
		final Customer cus = this.customerService.create();
		f.setAddress("");
		f.setApplication(new HashSet<Application>());
		f.setCategory(ca);
		f.setCustomer(cus);
		f.setDescription("");
		f.setMaximunPrice(0.);
		f.setMoment(new Date());
		f.setPeriodTime(0);
		f.setTicker("");
		f.setWarranty(wa);
		return f;
	}
	public Collection<FixUpTask> findAll() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		final Customer c = this.customerService.customerByUserAccount(userAccount.getId());
		return this.fixUpTaskRepository.fixUpTasksCustomer(c.getId());
	}

	public FixUpTask findOne(final Integer id) {
		return this.fixUpTaskRepository.findOne(id);
	}
	public FixUpTask save(final FixUpTask f) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.isTrue(f.getCustomer().getUserAccount().equals(userAccount));
		return this.fixUpTaskRepository.save(f);
	}
	public void delete(final FixUpTask f) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.isTrue(f.getCustomer().getUserAccount().equals(userAccount));
		Assert.isTrue(this.fixUpTaskRepository.findAll().contains(f));
		this.fixUpTaskRepository.delete(f);
	}

	public Collection<FixUpTask> fixUpTasksByFinder(final Integer finderId) {
		return this.fixUpTasksByFinder(finderId);
	}
}
