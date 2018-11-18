
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.EndoserRecordRepository;
import domain.EndoserRecord;

@Service
@Transactional
public class EndoserRecordService {

	@Autowired
	private EndoserRecordRepository	ERRepo;


	public EndoserRecord create(final String name, final String email, final String phone, final String linkedln, final Collection<String> comments) {
		final EndoserRecord endoserRecord = new EndoserRecord();
		endoserRecord.setName(name);
		endoserRecord.setEmail(email);
		endoserRecord.setPhoneNumber(phone);
		endoserRecord.setLinkedln(linkedln);
		endoserRecord.setComments(comments);
		return endoserRecord;
	}

	//listing
	public Collection<EndoserRecord> findAll() {
		return this.ERRepo.findAll();
	}
	public EndoserRecord findOne(final int endoserRecordId) {
		return this.ERRepo.findOne(endoserRecordId);
	}

	//updating
	public EndoserRecord save(final EndoserRecord endoserRecors) {
		return this.ERRepo.save(endoserRecors);
	}

	//deleting
	public void delete(final EndoserRecord endoserRecord) {
		this.ERRepo.delete(endoserRecord);
	}
}
