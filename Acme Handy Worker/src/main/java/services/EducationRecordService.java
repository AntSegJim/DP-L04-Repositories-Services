
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EducationRecordRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
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
		educationRecord.setTitleDiploma("");
		educationRecord.setStartDate(new Date());
		educationRecord.setEndDate(new Date());
		educationRecord.setInstitution("");
		educationRecord.setLink("");
		educationRecord.setComment(new HashSet<String>());
		educationRecord.setCurricula(this.curriS.create());
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
		final UserAccount user = LoginService.getPrincipal();
		Assert.isTrue(user.getAuthorities().contains(Authority.HANDYWORKER));
		Assert.isTrue(educationRecord != null && educationRecord.getTitleDiploma() != null && educationRecord.getTitleDiploma() != "" && educationRecord.getStartDate() != null && educationRecord.getStartDate().before(Calendar.getInstance().getTime())
			&& educationRecord.getEndDate().before(Calendar.getInstance().getTime()) && educationRecord.getInstitution() != null && educationRecord.getInstitution() != "" && educationRecord.getCurricula() != null);
		return this.ERRepo.save(educationRecord);
	}

	//deleting
	public void delete(final EducationRecord educationRecord) {
		this.ERRepo.delete(educationRecord);
	}
}
