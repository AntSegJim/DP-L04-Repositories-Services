
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.SponsorshipRepository;
import domain.CreditCard;
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
		final CreditCard creditCard = this.CCService.create();

		sponsorship.setUrlBanner("");
		sponsorship.setLinkTargetPage("");
		sponsorship.setCreditCard(creditCard);
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
		Sponsorship res = null;
		if (sponsorship != null && sponsorship.getLinkTargetPage() != null && sponsorship.getLinkTargetPage() != "" && sponsorship.getUrlBanner() != null && sponsorship.getUrlBanner() != "" && sponsorship.getCreditCard() != null)
			res = this.SRepo.save(sponsorship);
		return res;
		//return this.CRepo.save(curricula);
	}

	//deleting
	public void delete(final Sponsorship sponsorship) {
		this.SRepo.delete(sponsorship);
	}

}
