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
import javax.swing.JButton;

import tp.logic.City;
import tp.logic.ConnectingCities;
import tp.logic.WeightedGraph;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.InvalidParameterException;
import java.awt.event.ActionEvent;

public class HomeScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldProvince;
	private JTextField textFieldLatitude;
	private JTextField textFieldLongitude;

	private List<City> cities;
	private List<City> selectedCities;

	private List<JLabel> cityLabels;
	private int dropValue;
	private int positionInTheSelected;

	private ConnectingCities connectingCities;

	public HomeScreen() throws Exception {

		setTitle("Programacion III - Conectando Ciudades");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 530);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		selectedCities = new ArrayList<>();

		connectingCities = new ConnectingCities();

		positionInTheSelected = 0;
		dropValue = 45;

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(32, 52, 220, 22);
		contentPane.add(comboBox);

		cities = connectingCities.fetchCities();
		for (City city : cities) {
			comboBox.addItem(city.getName() + " , " + city.getProvince());
		}

		cityLabels = new ArrayList<JLabel>();
		for (int i = 0; i < cities.size() - 1; i++) {
			cityLabels.add(new JLabel(""));
			contentPane.add(cityLabels.get(i));
		}

		JLabel lblTitle1 = new JLabel("Seleccione una ciudad de la lista");
		lblTitle1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTitle1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle1.setBounds(44, 27, 196, 14);
		contentPane.add(lblTitle1);

		JLabel lblTitle2 = new JLabel("Agregar ciudad no listada");
		lblTitle2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitle2.setBounds(10, 168, 253, 22);
		contentPane.add(lblTitle2);

		JLabel lblCityName = new JLabel("Ciudad");
		lblCityName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCityName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCityName.setBounds(32, 201, 220, 22);
		contentPane.add(lblCityName);

		textFieldName = new JTextField();
		textFieldName.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldName.setBounds(32, 234, 220, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);

		JLabel lblProvinceName = new JLabel("Provincia");
		lblProvinceName.setHorizontalAlignment(SwingConstants.CENTER);
		lblProvinceName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProvinceName.setBounds(32, 266, 220, 22);
		contentPane.add(lblProvinceName);

		textFieldProvince = new JTextField();
		textFieldProvince.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldProvince.setColumns(10);
		textFieldProvince.setBounds(32, 299, 220, 20);
		contentPane.add(textFieldProvince);

		JLabel lblLat = new JLabel("Latitud");
		lblLat.setHorizontalAlignment(SwingConstants.CENTER);
		lblLat.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLat.setBounds(-14, 338, 126, 22);
		contentPane.add(lblLat);

		textFieldLatitude = new JTextField();
		textFieldLatitude.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldLatitude.setColumns(10);
		textFieldLatitude.setBounds(195, 340, 57, 20);
		contentPane.add(textFieldLatitude);
		entryValidation(textFieldLatitude);

		JLabel lblLong = new JLabel("Longitud");
		lblLong.setHorizontalAlignment(SwingConstants.CENTER);
		lblLong.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLong.setBounds(-26, 371, 159, 22);
		contentPane.add(lblLong);

		textFieldLongitude = new JTextField();
		textFieldLongitude.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldLongitude.setColumns(10);
		textFieldLongitude.setBounds(195, 373, 57, 20);
		contentPane.add(textFieldLongitude);
		entryValidation(textFieldLongitude);

		JLabel lblTitle = new JLabel("Lista de ciudades seleccionadas");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitle.setBounds(299, 28, 257, 14);
		contentPane.add(lblTitle);

		JButton btnAddListedCity = new JButton("Agregar Ciudad");
		btnAddListedCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indexCity = comboBox.getSelectedIndex();
				if (selectedCities.contains(cities.get(indexCity))) {
					showMessageDialog("Ya agrego esta ciudad, agregue otra");
				} else {
					if (selectedCities.size() == 23) {
						showMessageDialog("Ya agrego el limite de ciudades permitidas");
					} else {
						selectedCities.add(cities.get(indexCity));
						if (selectedCities.size() != 0) {
							addCityLbl();
						}
					}
				}
			}
		});
		btnAddListedCity.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAddListedCity.setBounds(74, 107, 126, 36);
		contentPane.add(btnAddListedCity);

		JButton btnAddUnlistedCity = new JButton("Agregar Ciudad");
		btnAddUnlistedCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    if (missingCityData()) {
			        showMessageDialog("Faltan datos necesarios para agregar la ciudad");
			    } else {
			        try {
			            City newCity = createCity();
			            if (selectedCities.contains(newCity)) {
			                showMessageDialog("Ya agrego esta ciudad, agregue otra");
			            } else {
			                if (selectedCities.size() == 23) {
			                    showMessageDialog("Ya agrego el limite de ciudades permitidas");
			                } else {
			                    selectedCities.add(newCity);
			                    if (selectedCities.size() != 0) {
			                        addCityLbl();
			                    }
			                }
			            }
			        } catch (InvalidParameterException ex) {
			            showMessageDialog("Alguno de los datos ingresados no es correcto");
			        }
			    }
			}

		});
		btnAddUnlistedCity.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAddUnlistedCity.setBounds(74, 428, 126, 36);
		contentPane.add(btnAddUnlistedCity);

		JButton btnMST = new JButton("Generar AGM");
		btnMST.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedCities.size() == 0) {
					showMessageDialog("Seleccione almenos una ciudad");
				} else {
					WeightedGraph mst = null;
					try {
						mst = connectingCities.minimumSpanningTree(selectedCities);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					// pasa como parametro el AGM
					MapScreen mapScreen = new MapScreen(mst);
					mapScreen.setResizable(false);
					mapScreen.setVisible(true);
				}
			}
		});
		
		btnMST.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMST.setBounds(394, 428, 126, 36);
		contentPane.add(btnMST);

		JLabel limLatitude = new JLabel("-54 < x < -22");
		limLatitude.setHorizontalAlignment(SwingConstants.CENTER);
		limLatitude.setFont(new Font("Tahoma", Font.BOLD, 11));
		limLatitude.setBounds(92, 336, 96, 26);
		contentPane.add(limLatitude);

		JLabel limLongitude = new JLabel("-70 < x < -53");
		limLongitude.setHorizontalAlignment(SwingConstants.CENTER);
		limLongitude.setFont(new Font("Tahoma", Font.BOLD, 11));
		limLongitude.setBounds(92, 370, 96, 26);
		contentPane.add(limLongitude);

	}

	private void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
	}

	private void addCityLbl() {
		cityLabels.get(positionInTheSelected).setText(selectedCities.get(positionInTheSelected).getName());
		cityLabels.get(positionInTheSelected).setHorizontalAlignment(SwingConstants.CENTER);
		cityLabels.get(positionInTheSelected).setFont(new Font("Tahoma", Font.BOLD, 12));
		cityLabels.get(positionInTheSelected).setBounds(336, dropValue, 196, 14);
		dropValue += 16;
		positionInTheSelected += 1;

	}

	private City createCity() {
		 return  connectingCities.createCity(textFieldName.getText(), textFieldProvince.getText(),
									Double.parseDouble(textFieldLatitude.getText()), Double.parseDouble(textFieldLongitude.getText()));
	}

	private boolean missingCityData() {
		return textFieldName.getText().equals("") || textFieldProvince.getText().equals("")
				|| textFieldLatitude.getText().equals("") || textFieldLongitude.getText().equals("");
	}


	// validador de entradas en las casillas
	private void entryValidation(JTextField jText) {
		jText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				// valida que la entrada sea un valor numeral o "-" o "." y de maxima longitud 8
				boolean numeros = (key >= 48 && key <= 57) || key == 45 | key == 46;
				if ((!numeros || jText.getText().trim().length() == 8)) {
					e.consume();
				}
			}
		});
	}
}
