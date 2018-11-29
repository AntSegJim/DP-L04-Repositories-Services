
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NoteRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Note;

@Service
@Transactional
public class NoteService {

	@Autowired
	private NoteRepository	noteRepository;
	@Autowired
	private ReportService	RService;


	public Note create() {
		final Note res = new Note();
		res.setMoment(new Date());
		res.setComment("");
		res.setOptionalComments(new HashSet<String>());
		res.setReport(this.RService.create());
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
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().contains(Authority.ADMIN) || userAccount.getAuthorities().contains(Authority.REFEREE) || userAccount.getAuthorities().contains(Authority.HANDYWORKER), "NoteService.save -> No estás autorizado.");

		Assert.isTrue(!(note.getMoment().equals(null)));
		Assert.isTrue(note != null && note.getReport() != null && note.getMoment().before(Calendar.getInstance().getTime()) && !(note.getComment().equals("")));
		return this.noteRepository.save(note);
	}

}
