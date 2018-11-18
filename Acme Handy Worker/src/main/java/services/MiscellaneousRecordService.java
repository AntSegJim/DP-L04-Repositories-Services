
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.MiscellaneousRecordRepository;
import domain.MiscellaneousRecord;

@Service
@Transactional
public class MiscellaneousRecordService {

	@Autowired
	private MiscellaneousRecordRepository	MRRepo;


	public MiscellaneousRecord create(final String title, final String link, final Collection<String> comments) {
		final MiscellaneousRecord miscellaneousRecord = new MiscellaneousRecord();
		miscellaneousRecord.setTitle(title);
		miscellaneousRecord.setLink(link);
		miscellaneousRecord.setComments(comments);
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
		return this.MRRepo.save(miscellaneousRecord);
	}

	//deleting
	public void delete(final MiscellaneousRecord miscellaneousRecord) {
		this.MRRepo.delete(miscellaneousRecord);
	}

}
