
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CreditCardRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.CreditCard;

@Service
@Transactional
public class CreditCardService {

	@Autowired
	private CreditCardRepository	CCRepo;


	//Metodo create
	public CreditCard create() {
		final CreditCard cc = new CreditCard();
		cc.setBrandName("");
		cc.setHolderName("");
		cc.setNumber(0);
		cc.setExpirationMonth(0);
		cc.setExpirationYear(0);
		cc.setCW(0);
		return cc;
	}
	public CreditCard save(final CreditCard cc) {
		final UserAccount user = LoginService.getPrincipal();
		Assert.isTrue(user.getAuthorities().contains(Authority.CUSTOMER));
		Assert.isTrue(cc != null && cc.getBrandName() != null && cc.getHolderName() != null && cc.getBrandName() != "" && cc.getHolderName() != "");
		return this.CCRepo.save(cc);

	}
}
