
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Report extends DomainEntity {

	private Date					moment;		// : Date {NotBlank}
	private String					description;	// : String {NotBlank}
	private int						published;		//: Integer {NotBlank}
	private Collection<Note>		note;
	private Collection<Attachment>	attachment;


	@NotNull
	@Temporal(TemporalType.DATE)
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public int getPublished() {
		return this.published;
	}

	public void setPublished(final int published) {
		this.published = published;
	}
	@Valid
	@OneToMany
	public Collection<Note> getNote() {
		return this.note;
	}

	public void setNote(final Collection<Note> note) {
		this.note = note;
	}
	@Valid
	@ManyToMany
	public Collection<Attachment> getAttachment() {
		return this.attachment;
	}

	public void setAttachment(final Collection<Attachment> attachment) {
		this.attachment = attachment;
	}

}
