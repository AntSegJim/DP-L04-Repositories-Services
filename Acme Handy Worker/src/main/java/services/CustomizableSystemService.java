
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.CustomizableSystemRepository;
import domain.CustomizableSystem;

@Service
@Transactional
public class CustomizableSystemService {

	@Autowired
	private CustomizableSystemRepository	customizableSystemRepository;


	public CustomizableSystem create(final String name, final String banner, final String messageWelcomePage, final String VATPercentage, final String telephoneCode) {
		final CustomizableSystem customizableSystem = new CustomizableSystem();
		customizableSystem.setName(name);
		customizableSystem.setBanner(banner);
		customizableSystem.setMessageWelcomePage(messageWelcomePage);
		customizableSystem.setVATPercentage(VATPercentage);
		customizableSystem.setTelephoneCode(telephoneCode);

		return customizableSystem;
	}
	//listing
	public Collection<CustomizableSystem> findAll() {
		return this.customizableSystemRepository.findAll();
	}

	public CustomizableSystem findOne(final int customizableSystemId) {
		return this.customizableSystemRepository.findOne(customizableSystemId);
	}

	//updating
	public CustomizableSystem save(final CustomizableSystem customizableSystem) {
		return this.customizableSystemRepository.save(customizableSystem);
	}

	//deleting
	public void delete(final CustomizableSystem customizableSystem) {
		this.customizableSystemRepository.delete(customizableSystem);
	}

}
