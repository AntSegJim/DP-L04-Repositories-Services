
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MessageBoxRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Message;
import domain.MessageBox;

@Service
@Transactional
public class MessageBoxService {

	@Autowired
	private MessageBoxRepository	messageRepositoryBox;
	@Autowired
	private CustomerService			CService;
	@Autowired
	private HandyWorkerService		HWService;
	@Autowired
	private RefereeService			RService;
	@Autowired
	private SponsorService			SService;


	public MessageBox create() {
		final MessageBox box = new MessageBox();
		box.setName("");
		box.setMessages(new HashSet<Message>());

		final UserAccount user = LoginService.getPrincipal();
		if (user.getAuthorities().contains(Authority.CUSTOMER))
			box.setActor(this.CService.create());
		else if (user.getAuthorities().contains(Authority.HANDYWORKER))
			box.setActor(this.HWService.create());
		else if (user.getAuthorities().contains(Authority.REFEREE))
			box.setActor(this.RService.create());
		else if (user.getAuthorities().contains(Authority.SPONSOR))
			box.setActor(this.SService.create());
		else {
			//Falta administrador
		}
		return box;
	}

	//Linstin
	public Collection<MessageBox> findAll() {
		return this.messageRepositoryBox.findAll();
	}
	public MessageBox findOne(final int messageBoxId) {
		return this.messageRepositoryBox.findOne(messageBoxId);
	}

	//Update
	public MessageBox save(final MessageBox box) {
		final UserAccount user = LoginService.getPrincipal();
		Assert.isTrue(box.getActor().getUserAccount().equals(user));
		Assert.isTrue(!(box.getName().trim().toUpperCase().equals("INBOX")));
		Assert.isTrue(!(box.getName().trim().toUpperCase().equals("OUTBOX")));
		Assert.isTrue(!(box.getName().trim().toUpperCase().equals("TRASHBOX")));
		Assert.isTrue(!(box.getName().trim().toUpperCase().equals("SPAMBOX")));
		Assert.isTrue(box != null && box.getName() != null && box.getName() != "" && box.getActor() != null);
		return this.messageRepositoryBox.save(box);
	}

	//delete
	public void delete(final MessageBox messageBox) {
		this.messageRepositoryBox.delete(messageBox);
	}
}