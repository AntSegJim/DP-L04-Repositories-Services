
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.SpamWordRepository;
import domain.SpamWord;

@Service
@Transactional
public class SpamWordService {

	// ---------- Manage Repository ----------

	@Autowired
	private SpamWordRepository	spamWordRepository;


	// ---------- Simple CRUD methods ----------

	public SpamWord create() {
		final SpamWord res = new SpamWord();
		res.setName("");
		return res;
	}
	public Collection<SpamWord> findAll() {
		return this.spamWordRepository.findAll();
	}
	public SpamWord findOne(final int spamWordId) {
		return this.spamWordRepository.findOne(spamWordId);
	}
	public SpamWord save(final SpamWord spamWord) {
		SpamWord res = null;
		if (spamWord != null && spamWord.getName() != null && spamWord.getName() != "")
			res = this.spamWordRepository.save(spamWord);
		return res;
		//return this.spamWordRepository.save(spamWord);
	}
	public void delete(final SpamWord spamWord) {
		this.spamWordRepository.delete(spamWord);
	}

}
