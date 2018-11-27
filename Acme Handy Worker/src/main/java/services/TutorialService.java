
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.TutorialRepository;
import domain.Picture;
import domain.Section;
import domain.Sponsorship;
import domain.Tutorial;

@Service
@Transactional
public class TutorialService {

	@Autowired
	private TutorialRepository	TRepo;
	@Autowired
	private SectionService		SService;


	public Tutorial create() {
		final Tutorial tutorial = new Tutorial();
		final Section section = this.SService.create();
		//Me creo una lista de con una seccion ya que un tutorial cuando se crea tiene que tener
		//al menos una section porque es una composicion, asi que no puedo crear una lista vacia como
		//en picture y en sponsorship
		final Collection<Section> sections = new HashSet<>();
		Collections.addAll(sections, section);

		tutorial.setTitle("");
		tutorial.setMoment(new Date());
		tutorial.setSummary("");
		tutorial.setPicture(new HashSet<Picture>());
		tutorial.setSection(sections);
		tutorial.setSponsorship(new HashSet<Sponsorship>());
		return tutorial;
	}

	//listing
	public Collection<Tutorial> findAll() {
		return this.TRepo.findAll();
	}
	public Tutorial findOne(final int tutorialId) {
		return this.TRepo.findOne(tutorialId);
	}

	//updating
	public Tutorial save(final Tutorial tutorial) {
		Tutorial res = null;
		if (tutorial != null && tutorial.getTitle() != null && tutorial.getTitle() != "" && tutorial.getMoment() != null && tutorial.getMoment().before(Calendar.getInstance().getTime()) && tutorial.getSummary() != "" && !tutorial.getSection().isEmpty()
			&& tutorial.getSection() != null)
			res = this.TRepo.save(tutorial);
		return res;
		//return this.CRepo.save(curricula);
	}

	//deleting
	public void delete(final Tutorial tutorial) {
		this.TRepo.delete(tutorial);
	}
}
