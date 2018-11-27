
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.MiscellaneousRecordRepository;
import domain.Curricula;
import domain.MiscellaneousRecord;

@Service
@Transactional
public class MiscellaneousRecordService {

	@Autowired
	private MiscellaneousRecordRepository	MRRepo;
	@Autowired
	private CurriculaService				curriS;


	public MiscellaneousRecord create() {
		final MiscellaneousRecord miscellaneousRecord = new MiscellaneousRecord();
		final Collection<String> comments = new HashSet<String>();
		final Curricula c = this.curriS.create();

		miscellaneousRecord.setTitle("");
		miscellaneousRecord.setLink("");
		miscellaneousRecord.setComments(comments);
		miscellaneousRecord.setCurricula(c);
		return miscellaneousRecord;
	}

	//listing
	public Collection<MiscellaneousRecord> findAll() {
		return this.MRRepo.findAll();
	}
	public MiscellaneousRecord findOne(final int miscellaneousRecordId) {
		return this.MRRepo.findOne(miscellaneousRecordId);
	}

	//updating
	public MiscellaneousRecord save(final MiscellaneousRecord miscellaneousRecord) {
		MiscellaneousRecord res = null;
		if (miscellaneousRecord != null && miscellaneousRecord.getTitle() != null && miscellaneousRecord.getTitle() != "" && miscellaneousRecord.getCurricula() != null)
			res = this.MRRepo.save(miscellaneousRecord);
		return res;
		//return this.MRRepo.save(miscellaneousRecord);
	}

	//deleting
	public void delete(final MiscellaneousRecord miscellaneousRecord) {
		this.MRRepo.delete(miscellaneousRecord);
	}

}
