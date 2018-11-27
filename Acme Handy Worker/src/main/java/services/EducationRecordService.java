
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.EducationRecordRepository;
import domain.Curricula;
import domain.EducationRecord;

@Service
@Transactional
public class EducationRecordService {

	@Autowired
	private EducationRecordRepository	ERRepo;
	@Autowired
	private CurriculaService			curriS;


	public EducationRecord create() {
		final EducationRecord educationRecord = new EducationRecord();
		final Collection<String> comments = new HashSet<String>();
		final Curricula c = this.curriS.create();

		educationRecord.setTitleDiploma("");
		educationRecord.setStartDate(new Date());
		educationRecord.setEndDate(new Date());
		educationRecord.setInstitution("");
		educationRecord.setLink("");
		educationRecord.setComment(comments);
		educationRecord.setCurricula(c);
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
		EducationRecord res = null;
		if (educationRecord != null && educationRecord.getTitleDiploma() != null && educationRecord.getTitleDiploma() != "" && educationRecord.getStartDate() != null && educationRecord.getStartDate().before(Calendar.getInstance().getTime())
			&& educationRecord.getEndDate().before(Calendar.getInstance().getTime()) && educationRecord.getInstitution() != null && educationRecord.getInstitution() != "" && educationRecord.getCurricula() != null)
			res = this.ERRepo.save(educationRecord);
		return res;
		//return this.ERRepo.save(educationRecord);
	}

	//deleting
	public void delete(final EducationRecord educationRecord) {
		this.ERRepo.delete(educationRecord);
	}
}
