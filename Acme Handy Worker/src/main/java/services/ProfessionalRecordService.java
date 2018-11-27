
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ProfessionalRecordRepository;
import domain.Curricula;
import domain.ProfessionalRecord;

@Service
@Transactional
public class ProfessionalRecordService {

	@Autowired
	private ProfessionalRecordRepository	PRRepo;
	@Autowired
	private CurriculaService				curriS;


	public ProfessionalRecord create() {
		final ProfessionalRecord professionalRecord = new ProfessionalRecord();
		final Collection<String> comments = new HashSet<String>();
		final Curricula c = this.curriS.create();

		professionalRecord.setNameCompany("");
		professionalRecord.setStartDate(new Date());
		professionalRecord.setEndDate(new Date());
		professionalRecord.setLink("");
		professionalRecord.setRole("");
		professionalRecord.setComments(comments);
		professionalRecord.setCurricula(c);
		return professionalRecord;
	}

	//listing
	public Collection<ProfessionalRecord> findAll() {
		return this.PRRepo.findAll();
	}
	public ProfessionalRecord findOne(final int professionalRecordId) {
		return this.PRRepo.findOne(professionalRecordId);
	}

	//updating
	public ProfessionalRecord save(final ProfessionalRecord professionalRecord) {
		ProfessionalRecord res = null;
		if (professionalRecord != null && professionalRecord.getStartDate() != null && professionalRecord.getStartDate().before(Calendar.getInstance().getTime()) && professionalRecord.getEndDate().before(Calendar.getInstance().getTime())
			&& professionalRecord.getCurricula() != null)
			res = this.PRRepo.save(professionalRecord);
		return res;
		//return this.PRRepo.save(professionalRecord);
	}

	//deleting
	public void delete(final ProfessionalRecord professionalRecord) {
		this.PRRepo.delete(professionalRecord);
	}

}
