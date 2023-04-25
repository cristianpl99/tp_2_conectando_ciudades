package tp.gui;

import java.awt.Color;
import java.awt.Dimension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.openstreetmap.gui.jmapviewer.*;

import tp.logic.City;
import tp.logic.Edge;
import tp.logic.WeightedGraph;

public class MapScreen extends JFrame {

	private JPanel contentPane;
	private JMapViewer map;

	public MapScreen(WeightedGraph mst) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 650, 800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		setTitle("Map Viewer");
		map = new JMapViewer();
		contentPane.add(map);
		map.setZoomControlsVisible(false);

		Coordinate coordinate = new Coordinate(-38, -66);
		map.setDisplayPosition(coordinate, 4);

		String[] columnNames = { "Ciudad Origen", "Provincia Origen", "Ciudad Destino", "Provincia Destino",
				"Costo del trayecto" };
		Object[][] rowData = new Object[mst.getAristas().size() + 1][5];
		int i = 0;
		int mstCost = 0;
		for (Edge edge : mst.getAristas()) {
			City origin = edge.getCity1();
			City destination = edge.getCity2();
			rowData[i][0] = origin.getName();
			rowData[i][1] = origin.getProvince();
			rowData[i][2] = destination.getName();
			rowData[i][3] = destination.getProvince();
			rowData[i][4] = "$ " + edge.getPeso();
			mstCost += edge.getPeso();
			i++;
		}

		rowData[i][0] = "COSTO DEL TENDIDO";
		rowData[i][4] = "$ " + mstCost;

		JTable table = new JTable(new DefaultTableModel(rowData, columnNames));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, centerRenderer);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(200, 700, 250, 200);
		scrollPane.setPreferredSize(new Dimension(650, 200));
		contentPane.add(scrollPane);

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
	}
}
