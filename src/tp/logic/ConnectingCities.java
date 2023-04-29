package tp.logic;

import java.security.InvalidParameterException;
import java.text.Normalizer;
import java.util.ArrayList;
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

	public City createCity(String name, String province, double latitude, double longitude) throws InvalidParameterException {
		if (!validateCityParams(name, province, latitude, longitude)) {
			throw new InvalidParameterException("Los parámetros ingresados para la ciudad no son válidos.");
		}
		
		City city = new City(name, province, latitude, longitude);
		return city;
	}

	
	public boolean validateCityParams(String name, String province, double latitude, double longitude) {
	    if (latitude < -54 || latitude > -22 || longitude < -70 || longitude > -53) {
	        return false;
	    }
	    persistence = new Persistence();
	    ArrayList<String> validProvinces = persistence.fetchProvinces();
	    
	    boolean isValidProvince = false;
	    for (String p : validProvinces) {
	        if (removeAccents(p).equalsIgnoreCase(removeAccents(province))) {
	            isValidProvince = true;
	            break;
	        }
	    }
	    return isValidProvince;
	}

	// Metodo auxiliar para comparar Strings con tilde
	private String removeAccents(String text) {
		return Normalizer.normalize(text, Normalizer.Form.NFD)
		        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
		        .toLowerCase();
	}
}
