
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

import domain.Phase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class PhaseServiceTest {

	@Autowired
	private PhaseService	phaseService;


	@Test
	public void testSavePhase() {
		Phase phase, saved;
		final Collection<Phase> phases;

		//Creando attachment
		final Date starTime = new Date(02 / 06 / 2017);
		final Date endTime = new Date(05 / 06 / 2017);
		phase = this.phaseService.create("FaseTest", "Fase para el Test", starTime, endTime);

		saved = this.phaseService.save(phase);
		phases = this.phaseService.findAll();
		Assert.isTrue(phases.contains(saved));

	}

	@Test
	public void testPhaseById() {
		Phase phase;
		phase = this.phaseService.findOne(465);

		Assert.notNull(phase);

	}

	@Test
	public void testDeletePhase() {
		Phase phase, saved;
		final Collection<Phase> phases;

		final Date starTime = new Date(02 / 06 / 2017);
		final Date endTime = new Date(05 / 06 / 2017);
		phase = this.phaseService.create("FaseTest", "Fase para el Test", starTime, endTime);

		saved = this.phaseService.save(phase);
		this.phaseService.delete(saved);

		phases = this.phaseService.findAll();

		Assert.isTrue(!phases.contains(saved));
	}

}
