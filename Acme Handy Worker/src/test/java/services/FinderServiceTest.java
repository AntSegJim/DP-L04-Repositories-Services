
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Finder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class FinderServiceTest extends AbstractTest {

	@Autowired
	private FinderService	finderService;


	//---------------------- Test ----------------------
	@Test
	public void testCreateFinder() {
		Finder f;
		f = this.finderService.create();
		f.setMoment(new Date());
		Assert.isTrue(f.getMoment() != null);
	}
	@Test
	public void testSaveFinder() {
		Finder f, savedF;
		f = this.finderService.create();
		f.setMoment(new Date());
		savedF = this.finderService.save(f);
		final Collection<Finder> fs = this.finderService.findAll();
		Assert.isTrue(fs.contains(savedF));
	}

	@Test
	public void testDeleteFinder() {
		Finder f, savedF;
		f = this.finderService.create();
		f.setMoment(new Date());
		savedF = this.finderService.save(f);
		this.finderService.delete(savedF);
		final Collection<Finder> fs = this.finderService.findAll();
		Assert.isTrue(!fs.contains(savedF));
	}

}
