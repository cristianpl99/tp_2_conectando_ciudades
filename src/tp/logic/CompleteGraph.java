package tp.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tp.logic.Edge;

public class CompleteGraph {

    public  Map<String[], List<Edge>> createCompleteGraph(List<String[]> selectedCities) {
    	
        Map<String[], List<Edge>> graph = new HashMap<>();

        // Crea un mapa vacío para cada vértice en la lista de vértices
        for (String[] vertex : selectedCities) {
            graph.put(vertex, new ArrayList<>());
        }

        // Agrega una arista entre cada par de vértices
        for (int i = 0; i < selectedCities.size(); i++) {
            for (int j = i + 1; j < selectedCities.size(); j++) {
                String[] vertex1 = selectedCities.get(i);
                String[] vertex2 = selectedCities.get(j);
        // Parsea los datos de latitud y longitud a double
                double peso = distanceInKilometers(
                		Double.parseDouble(vertex1[2]),Double.parseDouble (vertex1[3]),
                		Double.parseDouble(vertex2[2]), Double.parseDouble(vertex2[3]));
                
                Edge edge = new Edge(vertex1, vertex2, peso);
                graph.get(vertex1).add(edge);
                Edge edge2 = new Edge(vertex2, vertex1, peso);
                graph.get(vertex2).add(edge2);
            }
        }
        Primm primm = new Primm();
        return primm.findMST(graph);
    }
    
  // Metodo para calcular distancia
    
  	private static double distanceInKilometers(double lat1, double lon1, double lat2, double lon2) {
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
