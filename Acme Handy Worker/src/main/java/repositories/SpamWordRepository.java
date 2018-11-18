
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SpamWord;

@Repository
public interface SpamWordRepository extends JpaRepository<SpamWord, Integer> {

	@Query("select s from SpamWord s")
	Collection<SpamWord> findAllSpamWord();

	@Query("select s from SpamWord where s.id = ?1")
	SpamWord findSpamWord(Integer id);
}
