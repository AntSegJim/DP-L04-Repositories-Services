
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.HandyWorker;

@Repository
public interface HandyWorkerRepository extends JpaRepository<HandyWorker, Integer> {

	//Tal y como lo tenemos
	@Query("select t.handyWorker from Tutorial t where t.id = ?1")
	public HandyWorker handyWorkerInfo(Integer tutorialId);

	@Query("select e.name from HandyWorker e where ((select sum(a.application.size)*1.0/(select count(h) from HandyWorker h) from HandyWorker a)) * 0.1 + ((select sum(a.application.size)*1.0/(select count(h) from HandyWorker h) from HandyWorker a)) < e.application.size order by e.application.size ASC;")
	public Collection<HandyWorker> handyWorkerMoreTentPercentApplicatonsAccepted();

	//	@Query("select h from HandyWorker h where h.userAccount.id = ?1")
	//	public Collection<HandyWorker> handyWorkerByUserAccount(Integer userAccountId);

	//Añadido por jesus para el metodo  findAllByHandyWorker de phaseService
	@Query("select c from HandyWorker c where c.userAccount.id = ?1")
	public HandyWorker handyWorkerUserAccount(Integer id);

}
