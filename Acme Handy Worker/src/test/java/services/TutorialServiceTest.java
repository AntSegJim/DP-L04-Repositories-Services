
package services;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Picture;
import domain.Section;
import domain.Sponsorship;
import domain.Tutorial;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TutorialServiceTest {

	@Autowired
	private TutorialService	TService;
	@Autowired
	private SectionService	SSection;


	@Test
	public void testCreateTutorial() {
		Tutorial tutorial;
		Section section;
		//Creo una coleccion de secciones para el tutorial ya que debe de tener al menos una
		section = this.SSection.create();
		section.setNumber(1);
		section.setTitle("Hola");
		section.setPieceOfText("Primera seccion");
		final Collection<Section> sections = new HashSet<>();
		Collections.addAll(sections, section);
		//Creo el tutotial una vez creado la seccion
		tutorial = this.TService.create();
		tutorial.setTitle("Primer tutorial");
		tutorial.setMoment(new Date(02 / 04 / 2018));
		tutorial.setSummary("Descripcion");
		tutorial.setPicture(new HashSet<Picture>());
		tutorial.setSection(sections);
		tutorial.setSponsorship(new HashSet<Sponsorship>());

		Assert.isTrue(tutorial.getTitle().equals("Primer tutorial") && tutorial.getMoment().equals(new Date(02 / 04 / 2018)) && tutorial.getSummary().equals("Descripcion") && tutorial.getPicture().equals(new HashSet<Picture>())
			&& tutorial.getSection().equals(sections) && tutorial.getSponsorship().equals(new HashSet<Sponsorship>()));
	}

	@Test
	public void testSaveTutorial() {
		//Tutorial
		Tutorial tutorial, savedT;
		//Section
		Section section, savedS;
		//Lista de Tutorial
		final Collection<Tutorial> tutorials;
		//Lista de secciones
		final Collection<Section> sections;
		//crear y guardar una seccion
		section = this.SSection.create();
		section.setNumber(2);
		section.setTitle("adios");
		section.setPieceOfText("segunda seccion");
		savedS = this.SSection.save(section);
		//devuelvo todos las secciones
		sections = this.SSection.finaAll();
		//crear y guardar un tutorial
		final Collection<Section> sectionsTutorial = new HashSet<>();
		Collections.addAll(sectionsTutorial, section);
		tutorial = this.TService.create();
		tutorial.setTitle("Primer tutorial");
		tutorial.setMoment(new Date(03 / 04 / 2018));
		tutorial.setSummary("Descripcion");
		tutorial.setPicture(new HashSet<Picture>());
		tutorial.setSection(sectionsTutorial);
		tutorial.setSponsorship(new HashSet<Sponsorship>());
		savedT = this.TService.save(tutorial);
		//devulvo todos los tutoriales
		tutorials = this.TService.findAll();
		//Compruebo
		Assert.isTrue(sections.contains(savedS));
		Assert.isTrue(tutorials.contains(savedT));
	}

	@Test
	public void testDeleteTutorial() {
		//Tutorial
		Tutorial tutorial, savedT;
		//Section
		Section section;
		//Lista de Tutorial
		final Collection<Tutorial> tutorials;
		//creamos una seccion
		section = this.SSection.create();
		section.setNumber(3);
		section.setTitle("bye");
		section.setPieceOfText("tercera seccion");
		//crear y guardar un tutorial
		final Collection<Section> sectionsTutorial = new HashSet<>();
		Collections.addAll(sectionsTutorial, section);
		tutorial = this.TService.create();
		tutorial.setTitle("segundo tutorial");
		tutorial.setMoment(new Date(01 / 04 / 2018));
		tutorial.setSummary("resumen del segundo tutorial");
		tutorial.setPicture(new HashSet<Picture>());
		tutorial.setSection(sectionsTutorial);
		tutorial.setSponsorship(new HashSet<Sponsorship>());
		savedT = this.TService.save(tutorial);
		//Borro el tutorial
		this.TService.delete(tutorial);
		//devulvo todos los tutoriales
		tutorials = this.TService.findAll();
		//Compruebo
		Assert.isTrue(!tutorials.contains(savedT));
	}
}
