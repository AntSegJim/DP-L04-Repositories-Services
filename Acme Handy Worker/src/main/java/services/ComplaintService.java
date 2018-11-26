
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ComplaintRepository;
import security.LoginService;
import security.UserAccount;
import domain.Complaint;
import domain.Customer;
import domain.FixUpTask;
import domain.Referee;

@Service
@Transactional
public class ComplaintService {

	@Autowired
	private ComplaintRepository	complaintRepository;

	@Autowired
	private RefereeService		refereeService;

	@Autowired
	private CustomerService		customerService;


	//Metodos CRUD

	public Complaint create() {
		final Complaint res = new Complaint();
		return res;
	}

	public Complaint create(final String ticker, final Date moment, final String description, final int numberAttachments, final Referee referee, final FixUpTask fixUpTask) {
		final Complaint complaint = new Complaint();
		complaint.setTicker(ticker);
		complaint.setMoment(moment);
		complaint.setDescription(description);
		complaint.setNumberAttachments(numberAttachments);
		complaint.setReferee(referee);
		complaint.setFixUpTask(fixUpTask);

		return complaint;
	}

	//listing
	public Collection<Complaint> findAll() {
		return this.complaintRepository.findAll();
	}

	public Complaint findOne(final int complaintId) {
		return this.complaintRepository.findOne(complaintId);
	}

	//updating
	public Complaint save(final Complaint complaint) {
		return this.complaintRepository.save(complaint);
	}

	//deleting
	public void delete(final Complaint complaint) {
		this.complaintRepository.delete(complaint);
	}
	//------------------------Other business methods---------------------
	public Collection<Complaint> findAllByCustomer() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		final Customer c = this.customerService.customerByUserAccount(userAccount.getId());
		return this.complaintRepository.findAllCustomerComplaint(c.getId());
	}

	public Collection<Complaint> findAllByReferee() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		final Referee c = this.refereeService.refereeByUserAccount(userAccount.getId());
		return this.complaintRepository.findAllCustomerComplaint(c.getId());
	}

}
