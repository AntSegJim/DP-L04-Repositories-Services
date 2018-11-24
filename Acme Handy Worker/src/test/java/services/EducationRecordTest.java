
package services;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Curricula;
import domain.EducationRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class EducationRecordTest {

	@Autowired
	private EducationRecordService	ERService;
	@Autowired
	private CurriculaService		curriS;


	@Test
	public void testCreateEducationRecord() {
		EducationRecord educationRecord;
		Curricula curricula;

		curricula = this.curriS.create();
		curricula.setTicker("12345p");

		final Collection<String> comments = new HashSet<>();
		educationRecord = this.ERService.create();
		educationRecord.setTitleDiploma("hola");
		educationRecord.setStartDate(Date.valueOf("2015-03-09"));
		educationRecord.setEndDate(null);
		educationRecord.setInstitution("CL");
		educationRecord.setLink("");
		educationRecord.setComment(comments);
		educationRecord.setCurricula(curricula);

		Assert.isTrue(educationRecord.getTitleDiploma().equals("hola") && educationRecord.getStartDate().equals(Date.valueOf("2015-03-09")) && educationRecord.getEndDate().equals(null) && educationRecord.getInstitution().equals("CL")
			&& educationRecord.getLink().equals("") && educationRecord.getCurricula().equals(curricula));
	}

	@Test
	public void testSaveCurricula() {
		//Education
		EducationRecord educationRecord, savedE;
		//Curricula
		Curricula curricula, savedC;
		//Lista de education
		final Collection<EducationRecord> educationRecords;
		//Lista de curricula
		final Collection<Curricula> curriculas;

		//crear y guardar una curicula
		curricula = this.curriS.create();
		curricula.setTicker("12345o");
		savedC = this.curriS.save(curricula);

		//devuelvo todos los curricula
		curriculas = this.curriS.findAll();

		//crear y guardar un educationRecord
		final Collection<String> comments = new HashSet<>();
		educationRecord = this.ERService.create();
		educationRecord.setTitleDiploma("adios");
		educationRecord.setStartDate(Date.valueOf("2016-03-09"));
		educationRecord.setEndDate(null);
		educationRecord.setInstitution("FP");
		educationRecord.setLink("");
		educationRecord.setComment(comments);
		educationRecord.setCurricula(curricula);
		savedE = this.ERService.save(educationRecord);

		//devulvo todos los educationRecords
		educationRecords = this.ERService.findAll();

		//Compruebo
		Assert.isTrue(curriculas.contains(savedC));
		Assert.isTrue(educationRecords.contains(savedE));
	}
	/*
	 * @Test
	 * public void testDeleteCurricula() {
	 * Curricula curricula, saved;
	 * final Collection<Curricula> curriculas;
	 * curricula = this.curriS.create();
	 * 
	 * curricula.setTicker("12345l");
	 * 
	 * saved = this.curriS.save(curricula);
	 * this.curriS.delete(saved);
	 * curriculas = this.curriS.findAll();
	 * Assert.isTrue(!curriculas.contains(saved));
	 * }
	 */

}
