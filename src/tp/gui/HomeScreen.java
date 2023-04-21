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
	private int valorDeBajada;
	private int PosicionEnLasSelecionadas;

	public HomeScreen() {

		setTitle("Programacion III - Conectando Ciudades");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 530);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		selectedCities = new ArrayList<>();

		PosicionEnLasSelecionadas = 0;
		valorDeBajada = 45;

		ConnectingCities connectingCities = new ConnectingCities();

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(32, 52, 220, 22);
		contentPane.add(comboBox);

		cities = connectingCities.fetchCities();
		for (City city : cities) {
			comboBox.addItem(city.getName() + " , " + city.getProvince());
		}

		cityLabels = new ArrayList<JLabel>();
		for (int i = 0; i < cities.size() - 1; i++) {
			cityLabels.add(new JLabel("."));
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
		textFieldLatitude.setColumns(10);
		textFieldLatitude.setBounds(195, 340, 57, 20);
		contentPane.add(textFieldLatitude);
		entryValidation(textFieldLatitude);

		JLabel lblLong = new JLabel("Longitud");
		lblLong.setHorizontalAlignment(SwingConstants.CENTER);
		lblLong.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLong.setBounds(-55, 371, 220, 22);
		contentPane.add(lblLong);

		textFieldLongitude = new JTextField();
		textFieldLongitude.setColumns(10);
		textFieldLongitude.setBounds(195, 373, 57, 20);
		contentPane.add(textFieldLongitude);
		entryValidation(textFieldLongitude);

		JLabel lblTitle = new JLabel("Lista de ciudades seleccionadas");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTitle.setBounds(336, 28, 196, 14);
		contentPane.add(lblTitle);

		JButton btnAddListedCity = new JButton("Agregar Ciudad");
		btnAddListedCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indexCity = comboBox.getSelectedIndex();
				if (selectedCities.contains(cities.get(indexCity))) {
					JOptionPane.showMessageDialog(null, "Ya agrego esta ciudad, agregue otra", "Mensaje",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					selectedCities.add(cities.get(indexCity));

					// aux visualizacion
					for (City city : selectedCities) {
						System.out.println(city.getName());
					}

					if (selectedCities.size() != 0 && cityLabels.size() != 0) {
						cityLabels.get(PosicionEnLasSelecionadas)
								.setText(selectedCities.get(PosicionEnLasSelecionadas).getName());
						cityLabels.get(PosicionEnLasSelecionadas).setHorizontalAlignment(SwingConstants.CENTER);
						cityLabels.get(PosicionEnLasSelecionadas).setFont(new Font("Tahoma", Font.BOLD, 12));
						cityLabels.get(PosicionEnLasSelecionadas).setBounds(336, valorDeBajada, 196, 14);
						valorDeBajada += 16;
						PosicionEnLasSelecionadas += 1;
					}
				}
			}

		});
		btnAddListedCity.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAddListedCity.setBounds(74, 110, 126, 36);
		contentPane.add(btnAddListedCity);

		JButton btnAddUnlistedCity = new JButton("Agregar Ciudad");
		btnAddUnlistedCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				City newCity = new City();
				if (textFieldName.getText().equals("") || textFieldProvince.getText().equals("")
						|| textFieldLatitude.getText().equals("") || textFieldLongitude.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Agregue los datos faltantes", "Mensaje",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					newCity.setName(textFieldName.getText());
					newCity.setProvince(textFieldProvince.getText());
					newCity.setLatitude(Double.parseDouble(textFieldLatitude.getText()));
					newCity.setLongitude(Double.parseDouble(textFieldLongitude.getText()));
					if (selectedCities.contains(newCity)) {
						JOptionPane.showMessageDialog(null, "Ya agrego esta ciudad, agregue otra", "Mensaje",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						selectedCities.add(newCity);

						// aux visualizacion
						for (City city : selectedCities) {
							System.out.println(city.getName());
						}

						if (selectedCities.size() != 0 && cityLabels.size() != 0) {
							cityLabels.get(PosicionEnLasSelecionadas)
									.setText(selectedCities.get(PosicionEnLasSelecionadas).getName());
							cityLabels.get(PosicionEnLasSelecionadas).setHorizontalAlignment(SwingConstants.CENTER);
							cityLabels.get(PosicionEnLasSelecionadas).setFont(new Font("Tahoma", Font.BOLD, 12));
							cityLabels.get(PosicionEnLasSelecionadas).setBounds(336, valorDeBajada, 196, 14);
							valorDeBajada += 16;
							PosicionEnLasSelecionadas += 1;

							JOptionPane.showMessageDialog(null, "Ciudad agregada con exito", "Mensaje",
									JOptionPane.INFORMATION_MESSAGE);
						}
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
				// llama a un metodo que no esta implementado que devuelde el AGM
				WeightedGraph mst = connectingCities.minimumSpanningTree(selectedCities);
				// pasa como parametro el AGM
				MapScreen mapScreen = new MapScreen(mst);
				mapScreen.setResizable(false);
				mapScreen.setVisible(true);
			}

		});
		btnMST.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMST.setBounds(394, 428, 126, 36);
		contentPane.add(btnMST);

	}

	// validador de entradas en las casillas
	private void entryValidation(JTextField jText) {
		jText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				// valida que la entrada sea un valor numeral o "-" o "." y de maxima longitud 6
				boolean numeros = (key >= 48 && key <= 57) || key == 45 | key == 46;
				if ((!numeros || jText.getText().trim().length() == 6)) {
					e.consume();
				}
			}
		});
	}

}
