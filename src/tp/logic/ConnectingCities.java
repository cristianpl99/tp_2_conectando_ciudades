package tp.logic;

import java.util.List;
import java.util.Map;

import tp.persistence.*;



public class ConnectingCities {
	private Persistence persistence;
	

	public List<String[]> fetchCities() {
		Persistence persistence = new Persistence();
		return persistence.fetchCities();
	}

	public void addCity(String[] city) {
		Persistence persistence = new Persistence();
		persistence.saveCity(city);	
	}
	
	
	// Recibe las ciudades y retorna el AGM
	
	public Map<String[], List<Edge>> minimumSpanningTree(List<String[]> selectedCities) {
		CompleteGraph completeGraph = new CompleteGraph();
		return completeGraph.createCompleteGraph(selectedCities);
	}


}
