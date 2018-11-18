
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.CreditCard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CreditCardServiceTest {

	@Autowired
	private CreditCardService	CCService;


	@Test
	public void testCreateCreditCard() {
		CreditCard creditCard;
		creditCard = this.CCService.create();
		creditCard.setBrandName("VISA");
		creditCard.setHolderName("raul");
		creditCard.setNumber(101);
		creditCard.setExpirationMonth(2);
		creditCard.setExpirationYear(2019);
		creditCard.setCW(201);

		Assert.isTrue(creditCard.getBrandName().equals("VISA") && creditCard.getHolderName().equals("raul") && creditCard.getNumber() == 101 && creditCard.getExpirationMonth() == 2 && creditCard.getExpirationYear() == 2019 && creditCard.getCW() == 201);
	}

	@Test
	public void testSaveCreditCard() {
		CreditCard creditCard, saved;
		Collection<CreditCard> creditCards;
		creditCard = this.CCService.create();

		creditCard.setBrandName("VISA");
		creditCard.setHolderName("antonio");
		creditCard.setNumber(102);
		creditCard.setExpirationMonth(2);
		creditCard.setExpirationYear(2019);
		creditCard.setCW(202);

		saved = this.CCService.save(creditCard);
		creditCards = this.CCService.finaAll();
		Assert.isTrue(creditCards.contains(saved));
	}
	@Test
	public void testDeleteCreditCard() {
		CreditCard creditCard, saved;
		Collection<CreditCard> creditCards;
		creditCard = this.CCService.create();

		creditCard.setBrandName("VISA");
		creditCard.setHolderName("cristian");
		creditCard.setNumber(103);
		creditCard.setExpirationMonth(2);
		creditCard.setExpirationYear(2019);
		creditCard.setCW(203);

		saved = this.CCService.save(creditCard);
		this.CCService.delete(creditCard);
		creditCards = this.CCService.finaAll();
		Assert.isTrue(!creditCards.contains(saved));
	}

}
