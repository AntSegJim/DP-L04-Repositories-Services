
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.FixUpTask;

public interface FixUpTaskRepository extends JpaRepository<FixUpTask, Integer> {

	@Query("select f from FixUpTask f where f.customer.id = ?1")
	public Collection<FixUpTask> fixUpTasksCustomer(Integer id);

	//Mal	@Query("select max(count(a.fixUpTask)), min(a.fixUpTask.size), avg(a.fixUpTask.size),sqrt(sum(a.fixUpTask.size * a.fixUpTask.size) / count(a.fixUpTask.size) - (avg(a.fixUpTask.size) * avg(a.fixUpTask.size)))  from Customer a")
	@Query() Acabar
	public Collection<Double> MinMaxAvDevFixUpTask();
	
	@Query("select f.fixUpTask from Finder f where f.id = ?1")
	public Collection<FixUpTask> fixUpTasksByFinder(Integer finderId);
}
//select count(f.customer) from FixUpTask f group by f.customer.id