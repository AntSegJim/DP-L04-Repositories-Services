
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Curricula;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CurriculaServiceTest {

	@Autowired
	private CurriculaService	curriS;


	@Test
	public void testCreateCurricula() {
		Curricula curricula;
		curricula = this.curriS.create();
		curricula.setTicker("12345p");
		Assert.isTrue(curricula.getTicker().equals("12345p"));
	}

	@Test
	public void testSaveCurricula() {
		Curricula curricula, saved;
		final Collection<Curricula> curriculas;
		curricula = this.curriS.create();

		curricula.setTicker("12345o");

		saved = this.curriS.save(curricula);
		curriculas = this.curriS.findAll();
		Assert.isTrue(curriculas.contains(saved));
	}
	@Test
	public void testDeleteCurricula() {
		Curricula curricula, saved;
		final Collection<Curricula> curriculas;
		curricula = this.curriS.create();

		curricula.setTicker("12345l");

		saved = this.curriS.save(curricula);
		this.curriS.delete(saved);
		curriculas = this.curriS.findAll();
		Assert.isTrue(!curriculas.contains(saved));
	}
}
