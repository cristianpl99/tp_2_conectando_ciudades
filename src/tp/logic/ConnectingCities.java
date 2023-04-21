package tp.logic;

import java.util.List;

import tp.persistence.Persistence;

public class ConnectingCities {
	private Persistence persistence;

	public List<City> fetchCities() {
		persistence = new Persistence();
		return persistence.fetchCities();
	}

	// Recibe las ciudades y retorna el AGM

	public WeightedGraph minimumSpanningTree(List<City> selectedCities) {
		CompleteGraph completeGraph = new CompleteGraph();
		return completeGraph.createCompleteGraph(selectedCities);
	}

}
