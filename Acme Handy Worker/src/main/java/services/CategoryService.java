
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.CategoryRepository;
import domain.Category;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository	categoryRepository;


	//creating
	public Category create(final String name, final Category parent, final Collection<Category> soon) {
		final Category category = new Category();
		category.setName(name);
		category.setParent(parent);
		category.setSoon(soon);
		return category;
	}
	//listing
	public Collection<Category> findAll() {
		return this.categoryRepository.findAll();
	}

	public Category findOne(final int categoryId) {
		return this.categoryRepository.findOne(categoryId);
	}

	//updating
	public Category save(final Category category) {
		return this.categoryRepository.save(category);
	}

	//deleting
	public void delete(final Category category) {
		if (!(category.getName() == "CATEGORY"))
			this.categoryRepository.delete(category);
	}

}
