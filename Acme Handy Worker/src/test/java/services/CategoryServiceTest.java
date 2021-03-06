
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CategoryServiceTest extends AbstractTest {

	@Autowired
	private CategoryService	categoryService;


	@Test
	public void testCreateCategory() {
		Category c;
		c = this.categoryService.create();
		c.setName("Hijo");
		c.setParent(c);
		Assert.isTrue(c.getParent() != null && c.getName().equals("Hijo"));
	}

	@Test
	public void testSaveCategory() {
		Category saved;
		Collection<Category> categories;

		Category c;
		c = this.categoryService.create();
		c.setName("Hijo");
		c.setParent(c);

		saved = this.categoryService.save(c);
		categories = this.categoryService.findAll();
		Assert.isTrue(categories.contains(saved));

	}

	@Test
	public void testDeleteCategory() {
		final Category saved;
		Collection<Category> categories;

		Category c;
		c = this.categoryService.create();
		c.setName("Hijo");
		c.setParent(c);
		saved = this.categoryService.save(c);
		this.categoryService.delete(saved);

		categories = this.categoryService.findAll();

		Assert.isTrue(!categories.contains(saved));
	}
}
