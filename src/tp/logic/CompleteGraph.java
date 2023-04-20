package tp.logic;

import java.util.List;
import java.text.DecimalFormat;

public class CompleteGraph {

	public WeightedGraph createCompleteGraph(List<City> selectedCities) {

		WeightedGraph graph = new WeightedGraph(selectedCities.size(), selectedCities);

		// Agrega una arista entre cada par de v�rtices
		for (int i = 0; i < selectedCities.size(); i++) {
			for (int j = i + 1; j < selectedCities.size(); j++) {
				City city1 = selectedCities.get(i);
				City city2 = selectedCities.get(j);
				double weight = calculateWeight(city1, city2);

				Edge edge = new Edge(city1, city2, weight);
				graph.addEdge(edge.city1, edge.city2, edge.peso);
			}
		}
		return Prim.primTraversal(graph, selectedCities);
	}
	
	//no esta bien que este calculo este ac�. montos inventados
	private double calculateWeight(City city1, City city2) {
		double totalCost = 0;
		double costPerKilometer = distanceInKilometers(city1.getLatitude(), city1.getLongitude(),
				city2.getLatitude(), city2.getLongitude());
		if (costPerKilometer > 300) {
			totalCost = costPerKilometer * 1.1;	
		}
		
		if (city1.getProvince().equals(city2.getProvince())) {
			totalCost += 300;
		}
		return totalCost;
	}
	
	private double distanceInKilometers(double lat1, double lon1, double lat2, double lon2) {
  	    final int R = 6371; 

  	    double latDistance = Math.toRadians(lat2 - lat1);
  	    double lonDistance = Math.toRadians(lon2 - lon1);
  	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
  	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
  	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
  	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

  	    return R * c; 
  	}

}