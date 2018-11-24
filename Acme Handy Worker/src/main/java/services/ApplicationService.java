
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import security.LoginService;
import security.UserAccount;
import domain.Application;
import domain.Customer;

@Service
@Transactional
public class ApplicationService {

	@Autowired
	private ApplicationRepository	applicationRepository;

	@Autowired
	private CustomerService			customerService;


	public Application create() {
		final Application a = new Application();
		a.setComments(null);
		a.setCreditCard(null);
		a.setFixUpTask(null);
		a.setHandyWorker(null);
		a.setMoment(null);
		a.setPrice(0.);
		a.setStatus(0);
		return a;
	}
	public Collection<Application> findAll() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		final Customer c = this.customerService.customerByUserAccount(userAccount.getId());
		return this.applicationRepository.findAllCustomerApplication(c.getId());
	}

	public Application save(final Application a) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		final Customer c = this.customerService.customerByUserAccount(userAccount.getId());
		Assert.isTrue(Integer.valueOf(a.getFixUpTask().getCustomer().getId()).equals(c.getId()));
		return this.applicationRepository.save(a);
	}
}
