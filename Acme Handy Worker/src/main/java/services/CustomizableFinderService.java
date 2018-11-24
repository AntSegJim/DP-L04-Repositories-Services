
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.CustomizableFinderRepository;
import domain.CustomizableFinder;

@Service
@Transactional
public class CustomizableFinderService {

	@Autowired
	private CustomizableFinderRepository	customizableFinderRepository;


	public CustomizableFinder create() {
		final CustomizableFinder res = new CustomizableFinder();

		return res;
	}

	public CustomizableFinder create(final int resultNumber, final int timeCache) {
		final CustomizableFinder customizableFinder = new CustomizableFinder();
		customizableFinder.setResultNumber(resultNumber);
		customizableFinder.setTimeCache(timeCache);

		return customizableFinder;
	}
	//listing
	public Collection<CustomizableFinder> findAll() {
		return this.customizableFinderRepository.findAll();
	}

	public CustomizableFinder findOne(final int customizableFinderId) {
		return this.customizableFinderRepository.findOne(customizableFinderId);
	}

	//updating
	public CustomizableFinder save(final CustomizableFinder customizableFinder) {
		return this.customizableFinderRepository.save(customizableFinder);
	}

	//deleting
	public void delete(final CustomizableFinder customizableFinder) {
		this.customizableFinderRepository.delete(customizableFinder);
	}

}
