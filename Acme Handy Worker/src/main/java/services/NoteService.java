
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NoteRepository;
import domain.Note;

@Service
@Transactional
public class NoteService {

	@Autowired
	private NoteRepository	noteRepository;

	@Autowired
	private ReportService	refereeService;


	public Note create() {
		final Note res = new Note();
		res.setMoment(new Date());
		res.setComment("");
		res.setOptionalComments(null);
		return res;

	}
	public Note create(final Date moment, final String comment, final Collection<String> optionalComments) {
		final Note note = new Note();
		note.setMoment(moment);
		note.setComment(comment);
		note.setOptionalComments(optionalComments);

		return note;
	}
	//listing
	public Collection<Note> findAll() {
		return this.noteRepository.findAll();
	}

	public Note findOne(final int noteId) {
		return this.noteRepository.findOne(noteId);
	}

	//updating
	public Note save(final Note note) {

		Assert.isTrue(!(note.getMoment().equals(null)));
		Assert.isTrue(!(note.getComment().equals("")));
		return this.noteRepository.save(note);
	}

	//deleting
	public void delete(final Note note) {
		this.noteRepository.delete(note);
	}

}
