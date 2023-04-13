package tp.gui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import tp.logic.ConnectingCities;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class HomeScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldLatitude;
	private JTextField textFieldLongitude;
	
	private List<String[]> cities;

	
	public HomeScreen() {
		
		setTitle("Programacion III - Conectando Ciudades");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 530);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ConnectingCities connectingCities = new ConnectingCities();
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(32, 52, 220, 22);
		contentPane.add(comboBox);
		
		
		
		cities = connectingCities.fetchCities();
		for (String[] e: cities) {
		    comboBox.addItem(e[0]);
		}
		
		JLabel lblNewLabel = new JLabel("Seleccione una ciudad de la lista");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(44, 27, 196, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblAgregarCiudadQue = new JLabel("Agregar ciudad no listada");
		lblAgregarCiudadQue.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarCiudadQue.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAgregarCiudadQue.setBounds(10, 168, 253, 22);
		contentPane.add(lblAgregarCiudadQue);
		
		JLabel lblName = new JLabel("Nombre de la ciudad");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblName.setBounds(32, 201, 220, 22);
		contentPane.add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(32, 247, 220, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblLatitud = new JLabel("Latitud");
		lblLatitud.setHorizontalAlignment(SwingConstants.CENTER);
		lblLatitud.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLatitud.setBounds(-13, 292, 126, 22);
		contentPane.add(lblLatitud);
		
		
		textFieldLatitude = new JTextField();
		textFieldLatitude.setColumns(10);
		textFieldLatitude.setBounds(195, 294, 57, 20);
		contentPane.add(textFieldLatitude);
		entryValidation(textFieldLatitude);
		
		JLabel lblNombreDeLa_1_1 = new JLabel("Longitud");
		lblNombreDeLa_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDeLa_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombreDeLa_1_1.setBounds(-51, 337, 220, 22);
		contentPane.add(lblNombreDeLa_1_1);
		
		textFieldLongitude = new JTextField();
		textFieldLongitude.setColumns(10);
		textFieldLongitude.setBounds(195, 339, 57, 20);
		contentPane.add(textFieldLongitude);
		entryValidation(textFieldLongitude);
		
		JLabel lblTitle = new JLabel("Lista de ciudades seleccionadas");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTitle.setBounds(336, 28, 196, 14);
		contentPane.add(lblTitle);
		
		JButton btnAddCity = new JButton("Agregar Ciudad");
		btnAddCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] city = new String[3];
				city[0] = textFieldName.getText();
				city[1] = textFieldLatitude.getText();
				city[2] = textFieldLongitude.getText();
				connectingCities.addCity(city);
				
				JOptionPane.showMessageDialog(null, "Ciudad agregada con exito", 
						"Mensaje", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		btnAddCity.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAddCity.setBounds(74, 428, 126, 36);
		contentPane.add(btnAddCity);
		
		JButton btnAddListedCity = new JButton("Agregar Ciudad");
		btnAddListedCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedCity = (String)comboBox.getSelectedItem();
				
				
				JLabel lblCity = new JLabel(selectedCity);
				lblCity.setHorizontalAlignment(SwingConstants.CENTER);
				lblCity.setFont(new Font("Tahoma", Font.BOLD, 30));
				lblCity.setBounds(380, 50, 140, 30);
				contentPane.add(lblCity);
			}
		});
		
		btnAddListedCity.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAddListedCity.setBounds(74, 110, 126, 36);
		contentPane.add(btnAddListedCity);
		
		JButton btnMST = new JButton("Generar AGM");
		btnMST.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMST.setBounds(394, 428, 126, 36);
		contentPane.add(btnMST);
		
		
	}
	
	//validador de entradas en las casillas
		private void entryValidation(JTextField jText) {
			jText.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					int key = e.getKeyChar();
					boolean numeros = key >= 48 && key <= 57;
					if ((!numeros || jText.getText().trim().length() == 2) || (key == 48 && jText.getText().trim().length() == 0)){
					    e.consume();
					}
				}
			});
		}
}
