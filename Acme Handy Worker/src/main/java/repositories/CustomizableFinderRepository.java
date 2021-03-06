
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.CustomizableFinder;

@Repository
public interface CustomizableFinderRepository extends JpaRepository<CustomizableFinder, Integer> {

}
