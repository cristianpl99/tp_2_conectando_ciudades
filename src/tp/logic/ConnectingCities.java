package tp.logic;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.Normalizer;
import java.util.List;

import org.json.JSONException;

import tp.dal.DolarAPI;
import tp.dal.Data;
import tp.dal.IvalueLoader;

public class ConnectingCities {
	private Data dataLoader;
	public static double dolarValue;
	List<String> validProvinces;
	private Double costPerKilometerInUSD;
	private Double increaseLongDistanceCost;
	private Double fixedCrossProvincialCost;

	public ConnectingCities(double costPerKilometerInUSD, double increaseLongDistanceCost,
			double fixedCrossProvincialCost) throws Exception {
		this.costPerKilometerInUSD = costPerKilometerInUSD;
		this.increaseLongDistanceCost = increaseLongDistanceCost;
		this.fixedCrossProvincialCost = fixedCrossProvincialCost;
		dataLoader = new Data();
		IvalueLoader valueLoader = new DolarAPI();	
		ConnectingCities.dolarValue = valueLoader.getDolarValue();
		this.validProvinces = dataLoader.getProvinces();
	}

	public ConnectingCities() {
	}

	public double getDolarValue() {
		return dolarValue;
	}

	public List<City> fetchCities() throws JSONException, IOException {	
		return dataLoader.getCities();
	}

	public WeightedGraph minimumSpanningTree(List<City> selectedCities) throws Exception {
		CompleteGraph completeGraph = new CompleteGraph(costPerKilometerInUSD, increaseLongDistanceCost,
				fixedCrossProvincialCost);
		WeightedGraph completeWeightedGraph = completeGraph.createCompleteWeightedGraph(selectedCities);
		Prim prim = new Prim();
		return prim.primTraversal(completeWeightedGraph);
	}

	public City createCity(String name, String province, double latitude, double longitude)
			throws InvalidParameterException, JSONException, IOException {
		if (!validateCityParams(name, province, latitude, longitude)) {
			throw new InvalidParameterException("Los parametros ingresados para la ciudad no son v�lidos.");
		}
		City city = new City(name, province, latitude, longitude);
		return city;
	}
	
	public City createCityFromAPI(String name, String province, double latitude, double longitude)
			throws InvalidParameterException, JSONException, IOException {
		City city = new City(name, province, latitude, longitude);
		return city;
	}

	public boolean validateCityParams(String name, String province, double latitude, double longitude)
			throws JSONException, IOException {
		if (latitude < -54 || latitude > -22 || longitude < -70 || longitude > -53) {
			return false;
		}

		boolean isValidProvince = false;
		for (String p : this.validProvinces) {
			if (removeAccents(p).equalsIgnoreCase(removeAccents(province))) {
				isValidProvince = true;
				break;
			}
		}
		return isValidProvince;
	}

	// Metodo auxiliar para comparar Strings con tilde
	private String removeAccents(String text) {
		return Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
				.toLowerCase();
	}
}
