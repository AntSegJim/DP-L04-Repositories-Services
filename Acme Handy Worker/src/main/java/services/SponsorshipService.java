
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SponsorshipRepository;
import domain.Sponsorship;

@Service
@Transactional
public class SponsorshipService {

	@Autowired
	private SponsorshipRepository	SRepo;
	@Autowired
	private CreditCardService		CCService;


	public Sponsorship create() {
		final Sponsorship sponsorship = new Sponsorship();
		sponsorship.setUrlBanner("");
		sponsorship.setLinkTargetPage("");
		sponsorship.setCreditCard(this.CCService.create());
		return sponsorship;
	}

	//listing
	public Collection<Sponsorship> findAll() {
		return this.SRepo.findAll();
	}
	public Sponsorship findOne(final int sponsorshipId) {
		return this.SRepo.findOne(sponsorshipId);
	}

	//updating
	public Sponsorship save(final Sponsorship sponsorship) {
		Assert.isTrue(sponsorship != null && sponsorship.getLinkTargetPage() != null && sponsorship.getLinkTargetPage() != "" && sponsorship.getUrlBanner() != null && sponsorship.getUrlBanner() != "" && sponsorship.getCreditCard() != null);
		return this.SRepo.save(sponsorship);
	}

	//deleting
	public void delete(final Sponsorship sponsorship) {
		this.SRepo.delete(sponsorship);
	}

}
