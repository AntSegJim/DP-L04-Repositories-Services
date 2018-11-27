
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ReportRepository;
import domain.Attachment;
import domain.Complaint;
import domain.Report;

@Service
@Transactional
public class ReportService {

	@Autowired
	private ReportRepository	reportRepository;

	@Autowired
	private AttachmentService	attachmentService;

	@Autowired
	private ComplaintService	complaintService;


	//Metodos CRUD

	public Report create() {
		final Report res = new Report();
		res.setMoment(null);
		res.setDescription("");
		res.setPublished(0);
		res.setAttachment(null);
		res.setComplaint(new Complaint());
		return res;
	}

	public Report create(final Date moment, final String description, final int published, final Collection<Attachment> attachment, final Complaint complaint) {
		final Report report = new Report();
		report.setMoment(moment);
		report.setDescription(description);
		report.setPublished(published);
		report.setAttachment(attachment);
		report.setComplaint(complaint);

		return report;
	}
	//listing
	public Collection<Report> findAll() {
		return this.reportRepository.findAll();
	}

	public Report findOne(final int reportId) {
		return this.reportRepository.findOne(reportId);
	}

	//updating
	public Report save(final Report report) {

		Assert.isTrue(!(report.getMoment().equals(null)));
		Assert.isTrue(!(report.getDescription().equals("")));
		return this.reportRepository.save(report);
	}

	//deleting
	public void delete(final Report report) {
		this.reportRepository.delete(report);
	}

}
