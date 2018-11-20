
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Attachment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AttachmentServiceTest {

	@Autowired
	private AttachmentService	attachmentService;


	@Test
	public void testSaveAttachment() {
		Attachment attachment, saved;
		Collection<Attachment> attachments;

		//Creando attachment

		attachment = this.attachmentService.create("https://github.com");

		saved = this.attachmentService.save(attachment);
		attachments = this.attachmentService.findAll();
		Assert.isTrue(attachments.contains(saved));

	}

	@Test
	public void testAttachmentById() {
		Attachment attachment;
		attachment = this.attachmentService.findOne(460);

		Assert.notNull(attachment);

	}

	@Test
	public void testDeleteCategory() {
		final Attachment attachment, saved;
		Collection<Attachment> attachments;

		attachment = this.attachmentService.create("https://trello.com");

		saved = this.attachmentService.save(attachment);
		this.attachmentService.delete(saved);

		attachments = this.attachmentService.findAll();

		Assert.isTrue(!attachments.contains(saved));
	}

}
