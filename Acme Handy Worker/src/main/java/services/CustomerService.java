
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.CustomerRepository;
import domain.Customer;

@Service
@Transactional
public class CustomerService {

	//------------------------Managed repository---------------------
	@Autowired
	private CustomerRepository	customerRepository;


	//------------------------Simple CRUD methods---------------------
	public Customer create() {
		final Customer c = new Customer();
		c.setAddress("");
		c.setEmail("");
		c.setEndorseCustomer(null);
		c.setMiddleName("");
		c.setName("");
		c.setNumberSocialProfiles(0);
		c.setPhone("");
		c.setPhoto("");
		c.setProfileSocialNetwork(null);
		c.setReceiveEndorseFromCustomer(null);
		c.setScore(0);
		c.setSurname("");
		c.setUserAccount(null);
		return c;
	}
	public Collection<Customer> findAll() {
		return this.customerRepository.findAll();
	}

	public Customer findOne(final int customerId) {
		return this.customerRepository.findOne(customerId);
	}
	public Customer save(final Customer customer) {
		return this.customerRepository.save(customer);
	}

	public void delete(final Customer customer) {
		this.customerRepository.delete(customer);
	}

	//------------------------Other business methods---------------------
	public Customer customerByUserAccount(final Integer id) {
		return this.customerRepository.customerUserAccount(id);
	}
}
