package tp.logic;

import java.util.List;

import tp.persistence.*;

public class ConnectingCities {
	private Persistence persistence;

	public List<City> fetchCities() {
		persistence = new Persistence();
		return persistence.fetchCities();
	}

	public void addCity(String[] city) {
		Persistence persistence = new Persistence();
		persistence.saveCity(city);
	}

	// Recibe las ciudades y retorna el AGM

	public WeightedGraph minimumSpanningTree(List<City> selectedCities) {
		CompleteGraph completeGraph = new CompleteGraph();
		return completeGraph.createCompleteGraph(selectedCities);
	}

}
