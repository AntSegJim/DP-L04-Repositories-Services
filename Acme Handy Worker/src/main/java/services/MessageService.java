
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.MessageRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Administrator;
import domain.Customer;
import domain.HandyWorker;
import domain.Message;
import domain.MessageBox;
import domain.Referee;
import domain.Sponsor;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository		MRepo;
	@Autowired
	private CustomerService			CService;
	@Autowired
	private HandyWorkerService		HWService;
	@Autowired
	private RefereeService			RService;
	@Autowired
	private SponsorService			SService;
	@Autowired
	private MessageBoxService		MBService;
	@Autowired
	private AdministratorService	AService;


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
		else
			message.setSender(this.AService.create());
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
		final UserAccount user = LoginService.getPrincipal();
		if (user.getAuthorities().contains(Authority.CUSTOMER)) {
			final Customer c = this.CService.create();
			final List<MessageBox> boxes = this.MBService.findMessageBoxActor(c.getId());
			for (int i = 0; i < boxes.size(); i++)
				if (boxes.get(i).getName().toUpperCase().equals("TRASHBOX")) {
					if (boxes.get(i).getMessages().contains(message))
						this.MRepo.delete(message);
				} else {
					boxes.get(i).getMessages().remove(message);
					for (int j = i + 1; j < boxes.size(); j++)
						if (boxes.get(j).getName().toUpperCase().equals("TRASHBOX"))
							boxes.get(j).getMessages().add(message);
				}
		} else if (user.getAuthorities().contains(Authority.HANDYWORKER)) {
			final HandyWorker hw = this.HWService.create();
			final List<MessageBox> boxes = this.MBService.findMessageBoxActor(hw.getId());
			for (int i = 0; i < boxes.size(); i++)
				if (boxes.get(i).getName().toUpperCase().equals("TRASHBOX")) {
					if (boxes.get(i).getMessages().contains(message))
						this.MRepo.delete(message);
				} else {
					boxes.get(i).getMessages().remove(message);
					for (int j = i + 1; j < boxes.size(); j++)
						if (boxes.get(j).getName().toUpperCase().equals("TRASHBOX"))
							boxes.get(j).getMessages().add(message);
				}
		} else if (user.getAuthorities().contains(Authority.SPONSOR)) {
			final Sponsor s = this.SService.create();
			final List<MessageBox> boxes = this.MBService.findMessageBoxActor(s.getId());
			for (int i = 0; i < boxes.size(); i++)
				if (boxes.get(i).getName().toUpperCase().equals("TRASHBOX")) {
					if (boxes.get(i).getMessages().contains(message))
						this.MRepo.delete(message);
				} else {
					boxes.get(i).getMessages().remove(message);
					for (int j = i + 1; j < boxes.size(); j++)
						if (boxes.get(j).getName().toUpperCase().equals("TRASHBOX"))
							boxes.get(j).getMessages().add(message);
				}
		} else if (user.getAuthorities().contains(Authority.REFEREE)) {
			final Referee r = this.RService.create();
			final List<MessageBox> boxes = this.MBService.findMessageBoxActor(r.getId());
			for (int i = 0; i < boxes.size(); i++)
				if (boxes.get(i).getName().toUpperCase().equals("TRASHBOX")) {
					if (boxes.get(i).getMessages().contains(message))
						this.MRepo.delete(message);
				} else {
					boxes.get(i).getMessages().remove(message);
					for (int j = i + 1; j < boxes.size(); j++)
						if (boxes.get(j).getName().toUpperCase().equals("TRASHBOX"))
							boxes.get(j).getMessages().add(message);
				}
		} else {
			final Administrator admin = this.AService.create();
			final List<MessageBox> boxes = this.MBService.findMessageBoxActor(admin.getId());
			for (int i = 0; i < boxes.size(); i++)
				if (boxes.get(i).getName().toUpperCase().equals("TRASHBOX")) {
					if (boxes.get(i).getMessages().contains(message))
						this.MRepo.delete(message);
				} else {
					boxes.get(i).getMessages().remove(message);
					for (int j = i + 1; j < boxes.size(); j++)
						if (boxes.get(j).getName().toUpperCase().equals("TRASHBOX"))
							boxes.get(j).getMessages().add(message);
				}

		}
	}
}
