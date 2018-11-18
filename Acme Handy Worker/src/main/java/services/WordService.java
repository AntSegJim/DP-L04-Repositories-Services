
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.WordRepository;
import domain.Word;

@Service
@Transactional
public class WordService {

	@Autowired
	private WordRepository	wordRepository;


	//Simple CRUD methods

	public Word create(final String name, final int value) {
		final Word word = new Word();
		word.setName(name);
		word.setValue(value);
		return word;
	}

	public Collection<Word> findAll() {
		return this.wordRepository.findAll();
	}

	public Word findOne(final int wordId) {
		return this.wordRepository.findOne(wordId);
	}

	public Word save(final Word word) {
		return this.wordRepository.save(word);
	}

	public void delete(final Word word) {
		this.wordRepository.delete(word);
	}

	//Other bussines methods

	public Collection<Word> GoodWords() {
		return this.wordRepository.goodWords();
	}

	public Collection<Word> BadWords() {
		return this.wordRepository.badWords();
	}

}
