
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Phase;

@Repository
public interface PhaseRepository extends JpaRepository<Phase, Integer> {

	@Query("select c from Phase c join c.Application f where f.handyWorker.id = ?1")
	public Collection<Phase> findAllHandyWorkerPhase(Integer id);

}
