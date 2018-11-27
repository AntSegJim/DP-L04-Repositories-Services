
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ProfileSocialNetworkRepository;
import domain.ProfileSocialNetwork;

@Service
@Transactional
public class ProfileSocialNetworkService {

	@Autowired
	private ProfileSocialNetworkRepository	profileSocialNetworkRepository;


	//CRUD
	public ProfileSocialNetwork create() {
		final ProfileSocialNetwork profile = new ProfileSocialNetwork();
		profile.setNickName("");
		profile.setNameSocialNetwork("");
		profile.setLinkProfile("");

		return profile;
	}

	public Collection<ProfileSocialNetwork> findAll() {
		return this.profileSocialNetworkRepository.findAll();
	}

	public ProfileSocialNetwork findOne(final int id) {
		return this.profileSocialNetworkRepository.findOne(id);
	}

	public ProfileSocialNetwork save(final ProfileSocialNetwork profile) {
		ProfileSocialNetwork res = null;
		//Falta URL
		if (profile != null && profile.getNameSocialNetwork() != null && profile.getNameSocialNetwork() != "" && profile.getNickName() != null && profile.getNickName() != null && profile.getLinkProfile() != null && profile.getLinkProfile() != "")
			res = this.profileSocialNetworkRepository.save(profile);
		return res;
		//return this.profileSocialNetworkRepository.save(profile);
	}

	public void delete(final ProfileSocialNetwork profile) {
		this.profileSocialNetworkRepository.delete(profile);
	}

}
