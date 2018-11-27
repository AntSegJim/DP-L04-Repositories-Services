
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.MessageRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Message;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository	MRepo;
	@Autowired
	private CustomerService		CService;
	@Autowired
	private HandyWorkerService	HWService;
	@Autowired
	private RefereeService		RService;
	@Autowired
	private SponsorService		SService;


	public Message create() {
		final Message message = new Message();
		message.setMoment(new Date());
		message.setSubject("");
		message.setBody("");
		message.setPriority(0);
		message.setTag("");

		final UserAccount user = LoginService.getPrincipal();
		if (user.getAuthorities().contains(Authority.CUSTOMER))
			message.setSender(this.CService.create());
		else if (user.getAuthorities().contains(Authority.HANDYWORKER))
			message.setSender(this.HWService.create());
		else if (user.getAuthorities().contains(Authority.REFEREE))
			message.setSender(this.RService.create());
		else if (user.getAuthorities().contains(Authority.SPONSOR))
			message.setSender(this.SService.create());
		else {
			//Falta administrador
		}
		message.setReceiver(new HashSet<Actor>());
		return message;
	}

	//Listing
	public Collection<Message> findAll() {
		return this.MRepo.findAll();
	}
	public Message findOne(final int messageId) {
		return this.MRepo.findOne(messageId);
	}

	//Update
	public Message save(final Message message) {
		Message res = null;
		if (message != null && message.getMoment() != null && message.getMoment().before(Calendar.getInstance().getTime()) && message.getPriority() >= 0 && message.getPriority() <= 2 && message.getSender() != null && !message.getReceiver().isEmpty()
			&& message.getReceiver() != null)
			res = this.MRepo.save(message);
		return res;
		//return this.MRepo.save(message);
	}
	//Delete
	public void delete(final Message message) {
		this.MRepo.delete(message);
	}
}
