
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Complaint extends DomainEntity {

	private String	ticker;			// : String{Pattern(^[0-9]{6}[-][A-Z0-9] {6}$)}
	private Date	moment;
	private String	description;
	private int		numberAttachments;
	private Referee	referee;


	@Valid
	@ManyToOne(optional = false)
	public Referee getReferee() {
		return this.referee;
	}

	public void setReferee(final Referee referee) {
		this.referee = referee;
	}
	//	@Pattern(regexp = "^[0-9]{6}[-][A-Z0-9] {6}$)")
	@Column(unique = true)
	public String getTicker() {
		return this.ticker;
	}
	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	public Date getMoment() {
		return this.moment;
	}
	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public String getDescription() {
		return this.description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}

	public int getNumberAttachments() {
		return this.numberAttachments;
	}
	public void setNumberAttachments(final int numberAttachments) {
		this.numberAttachments = numberAttachments;
	}

}
