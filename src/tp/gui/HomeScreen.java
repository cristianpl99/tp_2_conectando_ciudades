package tp.gui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import tp.logic.ConnectingCities;

public class HomeScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldLatitude;
	private JTextField textFieldLongitude;
	
	private List<String[]> cities;

	
	public HomeScreen() {
		
		setTitle("Programacion III - Conectando Ciudades");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 434);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> comboBox = new JComboBox();
		comboBox.setBounds(32, 52, 220, 22);
		contentPane.add(comboBox);
		
		
		ConnectingCities connectingCities = new ConnectingCities();
		cities = connectingCities.fetchCities();
		for (String[] e: cities) {
		    comboBox.addItem(e[0]);
		}
		
		JLabel lblNewLabel = new JLabel("Seleccione una ciudad de la lista");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(44, 27, 196, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblAgregarCiudadQue = new JLabel("Agregar ciudad que no est\u00E9 listada");
		lblAgregarCiudadQue.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarCiudadQue.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAgregarCiudadQue.setBounds(10, 116, 253, 22);
		contentPane.add(lblAgregarCiudadQue);
		
		JLabel lblName = new JLabel("Nombre de la ciudad");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblName.setBounds(32, 149, 220, 22);
		contentPane.add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(32, 182, 220, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblLatitud = new JLabel("Latitud");
		lblLatitud.setHorizontalAlignment(SwingConstants.CENTER);
		lblLatitud.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLatitud.setBounds(32, 221, 220, 22);
		contentPane.add(lblLatitud);
		
		textFieldLatitude = new JTextField();
		textFieldLatitude.setColumns(10);
		textFieldLatitude.setBounds(32, 254, 220, 20);
		contentPane.add(textFieldLatitude);
		
		JLabel lblNombreDeLa_1_1 = new JLabel("Longitud");
		lblNombreDeLa_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDeLa_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombreDeLa_1_1.setBounds(32, 288, 220, 22);
		contentPane.add(lblNombreDeLa_1_1);
		
		textFieldLongitude = new JTextField();
		textFieldLongitude.setColumns(10);
		textFieldLongitude.setBounds(32, 329, 220, 20);
		contentPane.add(textFieldLongitude);
		
		JLabel lblListaDeCiudades = new JLabel("Lista de ciudades seleccionadas");
		lblListaDeCiudades.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeCiudades.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblListaDeCiudades.setBounds(336, 28, 196, 14);
		contentPane.add(lblListaDeCiudades);
	}
}
