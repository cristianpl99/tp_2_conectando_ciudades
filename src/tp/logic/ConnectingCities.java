package tp.logic;

import java.util.List;

import tp.persistence.Persistence;

public class ConnectingCities {
	private Persistence persistence;
	private Double dolarBlueValue;
	private Double costPerKilometerInUSD;
	
	public ConnectingCities() throws Exception{
		this.dolarBlueValue =  DolarAPI.getDolarBlueValue();
		this.costPerKilometerInUSD = 15.0;
	}
	

	public List<City> fetchCities() {
		persistence = new Persistence();
		return persistence.fetchCities();
	}

	// Recibe las ciudades y retorna el AGM

	public WeightedGraph minimumSpanningTree(List<City> selectedCities) throws Exception {
		CompleteGraph completeGraph = new CompleteGraph();
		return completeGraph.createCompleteGraph(selectedCities);
	}
	
	public  double costPerKilometer(){
		return costPerKilometerInUSD * dolarBlueValue;
	}
}
