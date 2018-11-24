
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.EndoserRecordRepository;
import domain.Curricula;
import domain.EndoserRecord;

@Service
@Transactional
public class EndoserRecordService {

	@Autowired
	private EndoserRecordRepository	ERRepo;
	@Autowired
	private CurriculaService		curriS;


	public EndoserRecord create() {
		final EndoserRecord endoserRecord = new EndoserRecord();
		final Collection<String> comments = new HashSet<String>();
		final Curricula c = this.curriS.create();

		endoserRecord.setName("");
		endoserRecord.setEmail("");
		endoserRecord.setPhoneNumber("");
		endoserRecord.setLinkedln("");
		endoserRecord.setComments(comments);
		endoserRecord.setCurricula(c);
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
	public EndoserRecord save(final EndoserRecord endoserRecord) {
		EndoserRecord res = null;
		if (endoserRecord.getName() != null && endoserRecord.getName() != "" && endoserRecord.getCurricula() != null)
			res = this.ERRepo.save(endoserRecord);
		return res;
	}

	//deleting
	public void delete(final EndoserRecord endoserRecord) {
		this.ERRepo.delete(endoserRecord);
	}
}
