
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.SectionRepository;
import domain.Section;

@Service
@Transactional
public class SectionService {

	@Autowired
	private SectionRepository	SRepo;


	//Metodo create
	public Section create() {
		final Section s = new Section();
		s.setNumber(0);
		s.setTitle("");
		s.setPieceOfText("");
		return s;
	}

	//Metodo findAll
	public Collection<Section> finaAll() {
		return this.SRepo.findAll();
	}
	public Section findOne(final int SectionId) {
		return this.SRepo.findOne(SectionId);
	}
	public Section save(final Section s) {
		return this.SRepo.save(s);
	}
	public void delete(final Section s) {
		this.SRepo.delete(s);
	}
}
