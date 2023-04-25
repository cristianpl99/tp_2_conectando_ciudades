package tp.logic;

import java.util.List;

import tp.persistence.Persistence;

public class ConnectingCities {
	private Persistence persistence;
	private Double dolarBlueValue;
	private Double costPerKilometerInUSD;

	public ConnectingCities() throws Exception {
		this.dolarBlueValue = DolarAPI.getDolarBlueValue();
		this.costPerKilometerInUSD = 8.0;
	}

	public List<City> fetchCities() {
		persistence = new Persistence();
		return persistence.fetchCities();
	}

	public WeightedGraph minimumSpanningTree(List<City> selectedCities) throws Exception {
		CompleteGraph completeGraph = new CompleteGraph();
		WeightedGraph Gcomplete = completeGraph.createCompleteGraph(selectedCities);
		return Prim.primTraversal(Gcomplete);
	}

	public double costPerKilometer() {
		return costPerKilometerInUSD * dolarBlueValue;
	}
}
