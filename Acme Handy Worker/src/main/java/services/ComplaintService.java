
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ComplaintRepository;
import security.Authority;
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
		res.setTicker("");
		res.setMoment(new Date());
		res.setDescription("");
		res.setNumberAttachments(0);
		res.setReferee(new Referee());
		res.setFixUpTask(new FixUpTask());
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
		Assert.isTrue(complaint != null && complaint.getMoment() != null && complaint.getReferee() != null && complaint.getFixUpTask() != null);
		return this.complaintRepository.save(complaint);
	}

	//	//deleting
	//	public void delete(final Complaint complaint) {
	//		this.complaintRepository.delete(complaint);
	//	}
	//------------------------Other business methods---------------------
	public Collection<Complaint> findAllByCustomer() {
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().contains(Authority.CUSTOMER));

		final Customer c = this.customerService.customerByUserAccount(userAccount.getId());
		return this.complaintRepository.findAllCustomerComplaint(c.getId());
	}

	public Collection<Complaint> findAllByReferee() {
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().contains(Authority.REFEREE));

		final Referee c = this.refereeService.refereeByUserAccount(userAccount.getId());
		return this.complaintRepository.findAllRefereeComplaint(c.getId());
	}

	public Collection<Complaint> findAllNoReferee() {
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().contains(Authority.REFEREE));

		return this.complaintRepository.findAllNoRefereeComplaint();

	}

}
