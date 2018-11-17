
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class CustomizableFinder extends DomainEntity {

	private int	resultNumber;
	private int	timeCache;


	//Getters and Setters

	public int getResultNumber() {
		return this.resultNumber;
	}

	public void setResultNumber(final int resultNumber) {
		this.resultNumber = resultNumber;
	}

	public int getTimeCache() {
		return this.timeCache;
	}

	public void setTimeCache(final int timeCache) {
		this.timeCache = timeCache;
	}

}
