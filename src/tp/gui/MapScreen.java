package tp.gui;

import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.openstreetmap.gui.jmapviewer.*;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;

import tp.logic.Arista;
import tp.logic.GrafoConPeso;

public class MapScreen extends JFrame {

	private JPanel contentPane;
	private JMapViewer map;

	public MapScreen(GrafoConPeso mst) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 450, 750);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setTitle("Map Viewer");
		map = new JMapViewer();
		getContentPane().add(map);
		map.setZoomControlsVisible(false);

		Coordinate coordinate = new Coordinate(-38, -66);
		map.setDisplayPosition(coordinate, 4);

		/*
		for (List<Edge> edges : mst.values()) {
		    for (Edge edge : edges) {
		        String[] origin = edge.getOrigin();
		        String[] destination = edge.getDestination();
		    
		        double originLat = Double.parseDouble(origin[2]);
		        double originLon = Double.parseDouble(origin[3]);
		        double destLat = Double.parseDouble(destination[2]);
		        double destLon = Double.parseDouble(destination[3]);
		        
		        String originName = origin[0];
		        String destName = destination[0];
		        
		        MapMarkerDot originMarker = new MapMarkerDot(new Coordinate(originLat, originLon));
		        originMarker.setName(originName);
		        originMarker.getStyle().setBackColor(Color.red);
		        originMarker.getStyle().setColor(Color.blue);
		        map.addMapMarker(originMarker);
		        
		        MapMarkerDot destMarker = new MapMarkerDot(new Coordinate(destLat, destLon));
		        destMarker.setName(destName);
		        destMarker.getStyle().setBackColor(Color.red);
		        destMarker.getStyle().setColor(Color.blue);
		        map.addMapMarker(destMarker);

		        
		        coords.add(new Coordinate(originLat, originLon));
		        coords.add(new Coordinate(destLat, destLon));
		    }
		}
		        
		        MapPolygonImpl line = new MapPolygonImpl(coords);
		        line.setColor(Color.red);
		        map.addMapPolygon(line);
		    }
		*/

		for (Arista edge : mst.getAristas()) {
		    String[] origin = edge.getVert1();
		    String[] destination = edge.getVert2();
		    
		    double originLat = Double.parseDouble(origin[2]);
		    double originLon = Double.parseDouble(origin[3]);
		    double destLat = Double.parseDouble(destination[2]);
		    double destLon = Double.parseDouble(destination[3]);
		    
		    String originName = origin[0];
		    String destName = destination[0];
		    
		    MapMarkerDot originMarker = new MapMarkerDot(new Coordinate(originLat, originLon));
		    originMarker.setName(originName);
		    originMarker.getStyle().setBackColor(Color.red);
		    originMarker.getStyle().setColor(Color.blue);
		    map.addMapMarker(originMarker);
		    
		    MapMarkerDot destMarker = new MapMarkerDot(new Coordinate(destLat, destLon));
		    destMarker.setName(destName);
		    destMarker.getStyle().setBackColor(Color.red);
		    destMarker.getStyle().setColor(Color.blue);
		    map.addMapMarker(destMarker);
		    
		    Coordinate c1 = new Coordinate(originLat, originLon);
		    Coordinate c2 = new Coordinate(destLat, destLon);
		    List<Coordinate> coords = new ArrayList<Coordinate>(Arrays.asList(c1,c2,c2));
		    MapPolygonImpl line = new MapPolygonImpl(coords);
			line.setColor(Color.red);
			map.addMapPolygon(line);
		}
		
		System.out.println(mst);

}
}


