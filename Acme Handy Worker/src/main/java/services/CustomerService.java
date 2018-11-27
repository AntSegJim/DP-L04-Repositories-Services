
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.CustomerRepository;
import domain.Customer;
import domain.Endorsement;
import domain.MessageBox;
import domain.ProfileSocialNetwork;

@Service
@Transactional
public class CustomerService {

	//------------------------Managed repository---------------------
	@Autowired
	private CustomerRepository	customerRepository;
	private MessageBoxService MBService;


	//------------------------Simple CRUD methods---------------------
	public Customer create() {
		final Customer c = new Customer();
		c.setAddress("");
		c.setEmail("");
		c.setEndorseCustomer(new HashSet<Endorsement>());
		c.setMiddleName("");
		c.setName("");
		c.setNumberSocialProfiles(0);
		c.setPhone("");
		c.setPhoto("");
		c.setProfileSocialNetwork(new HashSet<ProfileSocialNetwork>());
		c.setReceiveEndorseFromCustomer(new HashSet<Endorsement>());
		c.setScore(0);
		c.setSurname("");
		//PREGUNTAR
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
		Customer res=null;
		res= this.customerRepository.save(customer);
		MessageBox mb1= this.MBService.create();
		mb1.setName("inbox");
		mb1.setActor(res);
		MessageBox mb2= this.MBService.create();
		mb2.setName("outbox");
		mb2.setActor(res);
		MessageBox mb3= this.MBService.create();
		mb3.setName("spambox");
		mb3.setActor(res);
		MessageBox mb4= this.MBService.create();
		mb4.setName("trashbox");
		mb4.setActor(res);
		return res;
	}

	public void delete(final Customer customer) {
		this.customerRepository.delete(customer);
	}

	//------------------------Other business methods---------------------
	public Customer customerByUserAccount(final Integer id) {
		return this.customerRepository.customerUserAccount(id);
	}
}
