
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Category;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository	categoryRepository;


	//creating
	/*
	 * public Category create(final String name, final Category parent, final Collection<Category> soon) {
	 * final Category category = new Category();
	 * category.setName(name);
	 * category.setParent(parent);
	 * category.setSoon(soon);
	 * return category;
	 * }
	 */

	public Category create() {
		final Category category = new Category();
		category.setName("");
		//PREGUNTAR
		category.setParent(null);
		category.setSoon(new HashSet<Category>());
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
		final UserAccount user = LoginService.getPrincipal();
		Assert.isTrue(category.getName() != null && category.getName() != "" && !category.getSoon().contains(category.getParent()));
		Assert.isTrue(user.getAuthorities().contains(Authority.ADMIN));
		Assert.isTrue(!(category.getName().equals(null)));
		Assert.isTrue(!(category.getName().equals("")));
		Assert.isTrue(!(category.getParent().equals(null)));
		final Collection<String> names = this.categoryRepository.namesCategory();
		Assert.isTrue(!names.contains(category.getName().toUpperCase()));
		return this.categoryRepository.save(category);
	}

	//deleting
	public void delete(final Category category) {
		final UserAccount user = LoginService.getPrincipal();
		Assert.isTrue(user.getAuthorities().contains(Authority.ADMIN));
		Assert.isTrue(!((category.getName() == "CATEGORY")));

		//if (category.getSoon().isEmpty())
		this.categoryRepository.delete(category);
		//else {
		//final List<Category> soons = this.categoryRepository.categorySoonFromParent(category.getId());
		//for (int i = 0; i < soons.size(); i++)
		//this.categoryRepository.delete(soons.get(i));
		//this.delete(category);
		//}
	}

}
