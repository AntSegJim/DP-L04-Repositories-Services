
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select max(c.fixUpTask.size) from Customer c")
	public Integer maxFixUpTaskNumber();

	@Query("select min(c.fixUpTask.size) from Customer c")
	public Integer minFixUpTaskNumber();

	@Query("select avg(c.fixUpTask.size) from Customer c")
	public Integer avgFixUpTaskNumber();

	@Query("select sqrt(sum(c.fixUpTask.size * c.fixUpTask.size) / count(c.fixUpTask.size) - (avg(c.fixUpTask.size) * avg(c.fixUpTask.size))) from Customer c")
	public Integer devFixUpTaskNumber();
}
