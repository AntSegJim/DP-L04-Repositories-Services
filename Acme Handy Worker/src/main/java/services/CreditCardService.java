
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.CreditCardRepository;
import domain.CreditCard;

@Service
@Transactional
public class CreditCardService {

	@Autowired
	private CreditCardRepository	CCRepo;


	//Metodo create
	public CreditCard create(final String brandName, final String holderName, final int number, final int expirationMonth, final int expirationYear, final int CW) {
		final CreditCard cc = new CreditCard();
		cc.setBrandName(brandName);
		cc.setHolderName(holderName);
		cc.setNumber(number);
		cc.setExpirationMonth(expirationMonth);
		cc.setExpirationYear(expirationYear);
		cc.setCW(CW);
		return cc;
	}

	//Metodo findAll
	public Collection<CreditCard> finaAll() {
		return this.CCRepo.findAll();
	}
	public CreditCard findOne(final int CreditCardId) {
		return this.CCRepo.findOne(CreditCardId);
	}
	public CreditCard save(final CreditCard cc) {
		return this.CCRepo.save(cc);
	}
	public void delete(final CreditCard cc) {
		this.CCRepo.delete(cc);
	}

}
