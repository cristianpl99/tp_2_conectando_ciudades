package tp.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.Queue;

public class Primm {
	
	public Map<String[], List<Edge>> findMST(Map<String[], List<Edge>> grafo) {
	    Map<String[], List<Edge>> mst = new HashMap<>(); // árbol generador mínimo
	    Map<String, Boolean> visited = new HashMap<>(); // vértices visitados
	    String[] initialVertex = grafo.keySet().iterator().next(); // vértice inicial
	    visited.put(Arrays.toString(initialVertex), true);// marcamos el vértice inicial como visitado
	    Queue<String[]> queue = new LinkedList<>(); // cola para BFS
	    
	    queue.add(initialVertex); // agregamos el vértice inicial a la cola de BFS
	    
	    // Recorremos el grafo hasta que hayamos visitado todos los vértices
	    while (!queue.isEmpty() && mst.size() < grafo.size()) {
	        String[] vertex = queue.remove(); // sacamos un vértice de la cola
	        
	        // Recorremos las aristas del vértice actual
	        for (Edge edge : grafo.get(vertex)) {
	            String[] dest = edge.getDestination();
	            if (!visited.containsKey(dest)) { // si el vértice no ha sido visitado
	                visited.put(Arrays.toString(dest), true); // lo marcamos como visitado
	                queue.add(dest); // lo agregamos a la cola de BFS
	                
	                // Agregamos la arista al árbol generador mínimo
	                if (!mst.containsKey(edge.getOrigin())) {
	                    mst.put(edge.getOrigin(), new ArrayList<>());
	                }
	                mst.get(edge.getOrigin()).add(edge);
	            }
	        }
	    }
	    
	    printMst(mst);

	    
	    return mst;
	}

	private void printMst(Map<String[], List<Edge>> mst) {
		for (Map.Entry<String[], List<Edge>> entry : mst.entrySet()) {
	        String[] key = entry.getKey();
	        List<Edge> value = entry.getValue();
	        System.out.print(Arrays.toString(key) + ": ");
	        for (Edge edge : value) {
	            System.out.print(Arrays.toString(edge.getDestination()) + " ");
	        }
	        System.out.println();
	    }
	}
}
