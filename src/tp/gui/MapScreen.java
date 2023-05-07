package tp.gui;

import java.awt.Color;
import java.awt.Dimension;


import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openstreetmap.gui.jmapviewer.*;

import tp.logic.City;
import tp.logic.ConnectingCities;
import tp.logic.Edge;
import tp.logic.WeightedGraph;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Point;

@SuppressWarnings("serial")
public class MapScreen extends JFrame {
	private JPanel contentPane;
	private JMapViewer map;
	private DefaultTableModel tableModel;

	public MapScreen(WeightedGraph mst) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 200, 750, 750);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		setTitle("Programacion III - Conectando Ciudades");
		map = new JMapViewer();
		map.setCenter(new Point(1075, 745));
		map.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(map);
		map.setZoomControlsVisible(false);
		map.setLayout(null);

		ImageIcon icono = new ImageIcon(getClass().getResource("/tp/images/earth_icon.png"));
		setIconImage(icono.getImage());

		Coordinate coordinate = new Coordinate(-38, -66);
		map.setDisplayPosition(coordinate, 4);

		String[] columnNames = { "Ciudad Origen", "Provincia Origen", "Ciudad Destino", "Provincia Destino",
		        "Costo del trayecto" };
		Object[][] rowData = new Object[mst.getEdges().size()][5];
		int i = 0;
		int mstCost = 0;
		for (Edge edge : mst.getEdges()) {
		    City origin = edge.getCity1();
		    City destination = edge.getCity2();
		    rowData[i][0] = origin.getName();
		    rowData[i][1] = origin.getProvince();
		    rowData[i][2] = destination.getName();
		    rowData[i][3] = destination.getProvince();
		    rowData[i][4] = formatInteger(edge.getPeso()) + " USD";
		    mstCost += edge.getPeso();
		    i++;
		}

		tableModel = new DefaultTableModel(rowData, columnNames) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};

		JTable table = new JTable(tableModel);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, centerRenderer);
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Tahoma", Font.BOLD, 12));
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, centerRenderer);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 900, 250, 200);
		scrollPane.setPreferredSize(new Dimension(700, 200));
		contentPane.add(scrollPane);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        

        addLabel("COSTO RECORRIDO TOTAL: $ " + formatInteger(mstCost) + " USD", 10, 735);
        
        String currentDate = dateFormat.format(new Date());
        addLabel("COTIZACION DOLAR " + currentDate + " : $ " + ConnectingCities.dolarValue + " PESOS", 10, 760);
        
        addLabel("COSTO RECORRIDO TOTAL: $ " + formatInteger(mstCost * ConnectingCities.dolarValue) + " PESOS", 10, 785);

		for (Edge edge : mst.getEdges()) {
		    City origin = edge.getCity1();
		    City destination = edge.getCity2();

		    addMarker(origin, Color.blue);
		    addMarker(destination, Color.blue);
		    addLine(origin, destination, Color.red);
		}
	}

	private void addMarker(City city, Color color) {
		double lat = city.getLatitude();
		double lon = city.getLongitude();
		String name = city.getName();
		MapMarkerDot marker = new MapMarkerDot(new Coordinate(lat, lon));
		marker.setName(name);
		marker.getStyle().setBackColor(color);
		marker.getStyle().setColor(color);
		map.addMapMarker(marker);
	}

	private void addLine(City origin, City destination, Color color) {
		double lat1 = origin.getLatitude();
		double lon1 = origin.getLongitude();
		double lat2 = destination.getLatitude();
		double lon2 = destination.getLongitude();
		List<Coordinate> coords = Arrays.asList(new Coordinate(lat1, lon1), new Coordinate(lat2, lon2),
				new Coordinate(lat2, lon2));
		MapPolygonImpl line = new MapPolygonImpl(coords);
		line.setColor(color);
		map.addMapPolygon(line);
	}
	
	private void addLabel(String text, int x, int y) {
	    JLabel label = new JLabel(text);
	    label.setFont(new Font("Tahoma", Font.BOLD, 20));
	    label.setBounds(x, y, 700, 25);
	    contentPane.add(label);
	}

	private String formatInteger(double number) {
		long integerPart = (long) number;
		String strIntegerPart = Long.toString(integerPart);
		int length = strIntegerPart.length();
		StringBuilder sb = new StringBuilder(strIntegerPart);
		for (int i = length - 3; i > 0; i -= 3) {
			sb.insert(i, ',');
		}
		return sb.toString();
	}
}
