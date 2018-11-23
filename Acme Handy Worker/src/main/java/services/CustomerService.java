
package services;

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

	//------------------------Supporting services---------------------
	@Autowired
	private FixUpTaskService	fixUpTaskService;
	@Autowired
	private EndorsementService	endorsementService;
	@Autowired
	private ActorService		actorService;


	//------------------------Simple CRUD methods---------------------
	public Customer create() {

	}
}
