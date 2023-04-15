package tp.logic;

import java.util.List;
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
	
	//metodo para calcular distancia
	public static double distanceInKilometers(double lat1, double lon1, double lat2, double lon2) {
	    final int R = 6371; 

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	    return R * c; 
	}
	
	//este es el metodo que recibe las ciudades y retorna el AGM
	public List<String[]> minimumSpanningTree(List<String[]> selectedCities) {
		return selectedCities;
	}


}
