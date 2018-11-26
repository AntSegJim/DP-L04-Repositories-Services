
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.SponsorRepository;
import domain.Sponsor;

@Service
@Transactional
public class SponsorService {

	@Autowired
	private SponsorRepository	sponsorRepository;


	public Sponsor create() {
		final Sponsor s = new Sponsor();
		s.setAddress("");
		s.setEmail("");
		s.setMiddleName("");
		s.setName("");
		s.setNumberSocialProfiles(0);
		s.setPhone("");
		s.setPhoto("");
		s.setProfileSocialNetwork(null);
		s.setSurname("");
		s.setUserAccount(null);
		return s;
	}

	public Collection<Sponsor> findAll() {
		return this.sponsorRepository.findAll();
	}

	public Sponsor findOne(final int sponsorId) {
		return this.sponsorRepository.findOne(sponsorId);
	}

	public Sponsor save(final Sponsor sponsor) {
		return this.sponsorRepository.save(sponsor);
	}

	public void delete(final Sponsor sponsor) {
		this.sponsorRepository.delete(sponsor);
	}
}
