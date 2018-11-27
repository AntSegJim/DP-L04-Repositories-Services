
package services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MessageBoxRepository;
import security.LoginService;
import security.UserAccount;
import domain.MessageBox;

@Service
@Transactional
public class MessageBoxService {

	private MessageBoxRepository	messageRepositoryBox;


	public MessageBox create() {
		final MessageBox box = new MessageBox();
		box.setName("");
		box.setMessages(null);
		box.setActor(null);
		return box;

	}

	public MessageBox save(final MessageBox box) {
		final UserAccount user = LoginService.getPrincipal();
		Assert.isTrue(box.getActor().getUserAccount().equals(user));
		Assert.isTrue(!(box.getName().trim().toUpperCase().equals("INBOX")));
		Assert.isTrue(!(box.getName().trim().toUpperCase().equals("OUTBOX")));
		Assert.isTrue(!(box.getName().trim().toUpperCase().equals("TRASHBOX")));
		Assert.isTrue(!(box.getName().trim().toUpperCase().equals("SPAMBOX")));
		return this.messageRepositoryBox.save(box);
	}

}
