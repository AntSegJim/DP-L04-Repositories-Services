
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
		creditCard = this.CCService.create("VISA", "raul", 101, 2, 2019, 201);
		Assert.isTrue(creditCard.getBrandName().equals("VISA") && creditCard.getHolderName().equals("raul") && creditCard.getNumber() == 101 && creditCard.getExpirationMonth() == 2 && creditCard.getExpirationYear() == 2019 && creditCard.getCW() == 201);
	}

	@Test
	public void testSaveCreditCard() {
		CreditCard creditCard, saved;
		Collection<CreditCard> creditCards;
		creditCard = this.CCService.create("VISA", "antonio", 102, 2, 2019, 202);

		saved = this.CCService.save(creditCard);
		creditCards = this.CCService.finaAll();
		Assert.isTrue(creditCards.contains(saved));
	}
	@Test
	public void testDeleteCreditCard() {
		CreditCard creditCard, saved;
		Collection<CreditCard> creditCards;
		creditCard = this.CCService.create("VISA", "cristian", 103, 2, 2019, 203);

		saved = this.CCService.save(creditCard);
		this.CCService.delete(saved);
		creditCards = this.CCService.finaAll();
		Assert.isTrue(!creditCards.contains(saved));
	}

}