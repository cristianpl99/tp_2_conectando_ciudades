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

import tp.logic.City;
import tp.logic.Edge;
import tp.logic.WeightedGraph;

public class MapScreen extends JFrame {

	private JPanel contentPane;
	private JMapViewer map;

	public MapScreen(WeightedGraph mst) {
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

		for (Edge edge : mst.getAristas()) {
			City origin = edge.getCity1();
			City destination = edge.getCity2();

			double originLat = origin.getLatitude();
			double originLon = origin.getLongitude();
			double destLat = destination.getLatitude();
			double destLon = destination.getLongitude();

			String originName = origin.getName();
			String destName = destination.getName();

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
			List<Coordinate> coords = new ArrayList<Coordinate>(Arrays.asList(c1, c2, c2));
			MapPolygonImpl line = new MapPolygonImpl(coords);
			line.setColor(Color.red);
			map.addMapPolygon(line);
		}

		System.out.println(mst);

	}
}
