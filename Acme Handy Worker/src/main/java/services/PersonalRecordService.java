
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.PersonalRecordRepository;
import domain.PersonalRecord;

@Service
@Transactional
public class PersonalRecordService {

	@Autowired
	private PersonalRecordRepository	PRRepo;


	public PersonalRecord create(final String nameHandyWorker, final String photo, final String email, final String phone, final String linkedInProfile) {
		final PersonalRecord personalRecord = new PersonalRecord();
		personalRecord.setNameHandyWorker(nameHandyWorker);
		personalRecord.setPhoto(photo);
		personalRecord.setEmail(email);
		personalRecord.setPhone(phone);
		personalRecord.setLinkedInProfile(linkedInProfile);
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
		return this.PRRepo.save(personalRecord);
	}

	//deleting
	public void delete(final PersonalRecord personalRecord) {
		this.PRRepo.delete(personalRecord);
	}

}
