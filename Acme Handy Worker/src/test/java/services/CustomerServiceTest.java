
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import security.UserAccount;
import utilities.AbstractTest;
import domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CustomerServiceTest extends AbstractTest {

	@Autowired
	private CustomerService	customerService;


	@Test
	public void testCreateCustomer() {
		Customer c;
		c = this.customerService.create();
		final UserAccount uaCustomer = new UserAccount();
		uaCustomer.setPassword("hola");
		uaCustomer.setUsername("Antonio");
		uaCustomer.setAuthorities(c.getUserAccount().getAuthorities());

		c.setSurname("Segura");
		c.setName("Antonio");
		c.setAddress("calle Arahal");
		c.setEmail("antonio@us.es");
		c.setPhone("654321123");
		c.setUserAccount(uaCustomer);
		c.setScore(10);
		Assert.isTrue(c.getName().equals("Antonio"), "CustomerService.create() -> nombre no coincide");
		Assert.isTrue(c.getSurname().equals("Segura"), "CustomerService.create() -> apellido no coincide");
		Assert.isTrue(c.getAddress().equals("calle Arahal"), "CustomerService.create() -> calle no coincide");
		Assert.isTrue(c.getEmail().equals("antonio@us.es"), "CustomerService.create() -> email no coincide");
		Assert.isTrue(c.getName().equals("654321123"), "CustomerService.create() -> telefono no coincide");
	}

	@Test
	public void saveCustomer() {
		Customer c;
		final Customer savedC;
		Collection<Customer> customers;
		c = this.customerService.create();
		final UserAccount uaCustomer = new UserAccount();
		uaCustomer.setPassword("hola");
		uaCustomer.setUsername("Antonio");
		uaCustomer.setAuthorities(c.getUserAccount().getAuthorities());

		c.setSurname("Segura");
		c.setName("Antonio");
		c.setAddress("calle Arahal");
		c.setEmail("antonio@us.es");
		c.setPhone("654321123");
		c.setUserAccount(uaCustomer);
		c.setScore(10);
		savedC = this.customerService.save(c);
		customers = this.customerService.findAll();
		Assert.isTrue(customers.contains(savedC), "CustomerServiceTest.save -> No guardado");
	}

}
