package tp.logic;

import java.util.List;
import tp.persistence.*;



public class ConnectingCities {
	private Persistence persistence;

	public List<String[]> fetchCities() {
		Persistence persistence = new Persistence();
		return persistence.fetchCities();
	}

}
