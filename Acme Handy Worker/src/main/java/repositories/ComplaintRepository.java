
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Complaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

	@Query("select c from Complaint c join c.fixUpTask f where f.customer.id = ?1")
	public Collection<Complaint> findAllCustomerComplaint(Integer id);

	@Query("select c from Complaint c where c.referee.id=?1")
	public Collection<Complaint> findAllRefereeComplaint(Integer id);
	
	@Querr("select c from Complaint c where c.referee=")

}
