
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.EducationRecordRepository;
import domain.EducationRecord;

@Service
@Transactional
public class EducationRecordService {

	@Autowired
	private EducationRecordRepository	ERRepo;


	public EducationRecord create(final String titleDiploma, final Date startDate, final Date endDate, final String institution, final String link, final Collection<String> comments) {
		final EducationRecord educationRecord = new EducationRecord();
		educationRecord.setTitleDiploma(titleDiploma);
		educationRecord.setStartDate(startDate);
		educationRecord.setEndDate(endDate);
		educationRecord.setInstitution(institution);
		educationRecord.setLink(link);
		educationRecord.setComment(comments);
		return educationRecord;
	}

	//listing
	public Collection<EducationRecord> findAll() {
		return this.ERRepo.findAll();
	}
	public EducationRecord findOne(final int educationRecordId) {
		return this.ERRepo.findOne(educationRecordId);
	}

	//updating
	public EducationRecord save(final EducationRecord educationRecord) {
		return this.ERRepo.save(educationRecord);
	}

	//deleting
	public void delete(final EducationRecord educationRecord) {
		this.ERRepo.delete(educationRecord);
	}
}
