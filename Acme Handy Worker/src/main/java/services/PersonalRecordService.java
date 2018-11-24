
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.PersonalRecordRepository;
import domain.Curricula;
import domain.PersonalRecord;

@Service
@Transactional
public class PersonalRecordService {

	@Autowired
	private PersonalRecordRepository	PRRepo;
	@Autowired
	private CurriculaService			curriS;


	public PersonalRecord create() {
		final PersonalRecord personalRecord = new PersonalRecord();
		final Curricula c = this.curriS.create();

		personalRecord.setNameHandyWorker("");
		personalRecord.setPhoto("");
		personalRecord.setEmail("");
		personalRecord.setPhone("");
		personalRecord.setLinkedInProfile("");
		personalRecord.setCurricula(c);
		return personalRecord;
	}

	//listing
	public Collection<PersonalRecord> findAll() {
		return this.PRRepo.findAll();
	}
	public PersonalRecord findOne(final int personalRecordId) {
		return this.PRRepo.findOne(personalRecordId);
	}

	//updating
	public PersonalRecord save(final PersonalRecord personalRecord) {
		PersonalRecord res = null;
		if (personalRecord.getNameHandyWorker() != null && personalRecord.getNameHandyWorker() != "" && personalRecord.getCurricula() != null)
			res = this.PRRepo.save(personalRecord);
		return res;
		//return this.PRRepo.save(personalRecord);
	}

	//deleting
	public void delete(final PersonalRecord personalRecord) {
		this.PRRepo.delete(personalRecord);
	}

}
