
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FixUpTaskRepository;
import security.LoginService;
import security.UserAccount;
import domain.Customer;
import domain.FixUpTask;

@Service
@Transactional
public class FixUpTaskService {

	@Autowired
	private FixUpTaskRepository	fixUpTaskRepository;

	@Autowired
	private CustomerService		customerService;


	public FixUpTask create() {
		final FixUpTask f = new FixUpTask();
		f.setAddress("");
		f.setApplication(null);
		f.setCategory(null);
		f.setCustomer(null);
		f.setDescription("");
		f.setMaximunPrice(0.);
		f.setMoment(null);
		f.setPeriodTime(0);
		f.setTicker("");
		f.setWarranty(null);
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
}
