
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Section;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SectionServiceTest {

	@Autowired
	private SectionService	SSection;


	@Test
	public void testCreateSection() {
		Section section;
		section = this.SSection.create();
		section.setNumber(1);
		section.setTitle("hola");
		section.setPieceOfText("Primera pieza de texto");
		Assert.isTrue(section.getNumber() == 1 && section.getTitle().equals("hola") && section.getPieceOfText().equals("Primera pieza de texto"));
	}

	@Test
	public void testSaveSection() {
		Section section, saved;
		Collection<Section> sections;
		section = this.SSection.create();
		section.setNumber(2);
		section.setTitle("adios");
		section.setPieceOfText("segunda pieza de texto");

		saved = this.SSection.save(section);
		sections = this.SSection.finaAll();
		Assert.isTrue(sections.contains(saved));
	}

	@Test
	public void testDeleteSection() {
		Section section, saved;
		Collection<Section> sections;
		section = this.SSection.create();
		section.setNumber(2);
		section.setTitle("adios");
		section.setPieceOfText("segunda pieza de texto");
		saved = this.SSection.save(section);

		this.SSection.delete(section);
		sections = this.SSection.finaAll();
		Assert.isTrue(!sections.contains(saved));
	}
}
