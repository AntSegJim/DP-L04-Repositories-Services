
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

	//Metodo findAll
	public Collection<CreditCard> finaAll() {
		return this.CCRepo.findAll();
	}
	public CreditCard findOne(final int CreditCardId) {
		return this.CCRepo.findOne(CreditCardId);
	}
	public CreditCard save(final CreditCard cc) {
		CreditCard res = null;
		if (cc.getBrandName() != null && cc.getHolderName() != null && cc.getBrandName() != "" && cc.getHolderName() != "")
			res = this.CCRepo.save(cc);
		return res;
		//return this.CCRepo.save(cc);

	}
	public void delete(final CreditCard cc) {
		this.CCRepo.delete(cc);
	}

}
