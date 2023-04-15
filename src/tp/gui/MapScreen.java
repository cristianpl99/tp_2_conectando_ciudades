package tp.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;


public class MapScreen extends JFrame {

	private JPanel contentPane;
	private JMapViewer map;

	public MapScreen(List<String[]> mstCities) {
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

		List<Coordinate> coords = new ArrayList<>();

		for (String[] city : mstCities) {
			String name = city[0];
			double lat = Double.parseDouble(city[2]);
			double lon = Double.parseDouble(city[3]);

			MapMarkerDot marker = new MapMarkerDot(new Coordinate(lat, lon));
			marker.setName(name);
			marker.getStyle().setBackColor(Color.red);
			marker.getStyle().setColor(Color.blue);
			map.addMapMarker(marker);

			coords.add(new Coordinate(lat, lon));
		}

		MapPolygonImpl polygon = new MapPolygonImpl(coords);
		polygon.setBackColor(new Color(0, 0, 255, 50));
		polygon.setColor(new Color(0, 0, 255));

		map.addMapPolygon(polygon);
	}
}


