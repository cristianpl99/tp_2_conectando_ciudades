package tp.logic;

import java.util.List;

public class CompleteGraph {
	private Double costPerKilometer;
	private Double increaseLongDistanceCost;
	private Double fixedCrossProvincialCost;

	public CompleteGraph(Double costPerKilometer, Double increaseLongDistanceCost, Double fixedCrossProvincialCost) {
		this.costPerKilometer = costPerKilometer;
		this.increaseLongDistanceCost = increaseLongDistanceCost;
		this.fixedCrossProvincialCost = fixedCrossProvincialCost;
	}

	public WeightedGraph createCompleteWeightedGraph(List<City> selectedCities) throws Exception {
		if (selectedCities.isEmpty()) {
			throw new IllegalArgumentException("La lista de ciudades no puede ser vacía");
		}

		WeightedGraph graph = new WeightedGraph(selectedCities.size(), selectedCities);

		for (int i = 0; i < selectedCities.size(); i++) {
			for (int j = i + 1; j < selectedCities.size(); j++) {
				City city1 = selectedCities.get(i);
				City city2 = selectedCities.get(j);
				double weight = calculateWeight(city1, city2);
				Edge edge = new Edge(city1, city2, weight);
				graph.addEdge(edge.city1, edge.city2, edge.peso);
			}
		}
		return graph;
	}

	public double calculateWeight(City city1, City city2) throws Exception {
		double distance = distanceInKilometers(city1.getLatitude(), city1.getLongitude(), city2.getLatitude(),
				city2.getLongitude());
		double edgeCost = distance * this.costPerKilometer;

		if (distance > 300) {
			double increaseFactor = 1 + (increaseLongDistanceCost / 100.0);
			edgeCost = edgeCost * increaseFactor;
		}
		if (!city1.getProvince().equals(city2.getProvince())) {
			edgeCost += fixedCrossProvincialCost;
		}
		edgeCost = Math.floor(edgeCost);
		return edgeCost;
	}

	public double distanceInKilometers(double lat1, double lon1, double lat2, double lon2) {
		final int R = 6371;

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return R * c;
	}

}
