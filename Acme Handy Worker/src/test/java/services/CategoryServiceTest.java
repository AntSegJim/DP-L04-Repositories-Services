
package services;

import java.util.Collection;
import java.util.HashSet;

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
	public void testSaveCategory() {
		Category category, saved;
		Collection<Category> categories;

		//Creando categoria
		final Collection<Category> collection = new HashSet<>();
		category = this.categoryService.create("Silla", this.categoryService.findOne(396), collection);

		saved = this.categoryService.save(category);
		categories = this.categoryService.findAll();
		Assert.isTrue(categories.contains(saved));

	}

	@Test
	public void testCategoryById() {
		Category category;
		category = this.categoryService.findOne(397);

		Assert.notNull(category);

	}

	@Test
	public void testDeleteCategory() {
		Collection<Category> categories;

		categories = this.categoryService.findAll();
		this.categoryService.delete(this.categoryService.findOne(398));

		Assert.isTrue(!categories.contains(this.categoryService.findOne(398)));
	}

}
