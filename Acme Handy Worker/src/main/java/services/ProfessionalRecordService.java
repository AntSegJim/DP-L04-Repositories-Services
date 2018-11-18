
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ProfessionalRecordRepository;
import domain.ProfessionalRecord;

@Service
@Transactional
public class ProfessionalRecordService {

	@Autowired
	private ProfessionalRecordRepository	PRRepo;


	public ProfessionalRecord create(final String nameCompany, final Date startDate, final Date endDate, final String link, final String role, final Collection<String> comments) {
		final ProfessionalRecord professionalRecord = new ProfessionalRecord();
		professionalRecord.setNameCompany(nameCompany);
		professionalRecord.setStartDate(startDate);
		professionalRecord.setEndDate(endDate);
		professionalRecord.setLink(link);
		professionalRecord.setRole(role);
		professionalRecord.setComments(comments);
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
		return this.PRRepo.save(professionalRecord);
	}

	//deleting
	public void delete(final ProfessionalRecord professionalRecord) {
		this.PRRepo.delete(professionalRecord);
	}

}
