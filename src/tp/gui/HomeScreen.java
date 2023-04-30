package tp.gui;

import java.awt.Color;


import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
import javax.swing.JScrollPane;

import javax.swing.table.DefaultTableCellRenderer;


public class HomeScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldProvince;
	private JTextField textFieldLatitude;
	private JTextField textFieldLongitude;
	private List<City> cities;
	private List<City> selectedCities;
	private DefaultTableModel tableModel;
	private ConnectingCities connectingCities;

	public HomeScreen() throws Exception {

		setTitle("Programacion III - Conectando Ciudades");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 661, 559);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		selectedCities = new ArrayList<City>();

		connectingCities = new ConnectingCities();
		
		JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(299, 49, 336, 400);
        contentPane.add(scrollPane);

        tableModel = new DefaultTableModel(new Object[]{"Ciudad", "Provincia"}, 0);
        table.setModel(tableModel);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(32, 52, 220, 22);
		contentPane.add(comboBox);

		cities = connectingCities.fetchCities();
		for (City city : cities) {
			comboBox.addItem(city.getName() + " , " + city.getProvince());
		}

		createLabel("Seleccione una ciudad de la lista", 12, 44, 27, 196, 14);
		createLabel("Agregar ciudad no listada", 14, 55, 168, 253, 22);
		createLabel("Ciudad", 12, 32, 201, 220, 22);
		createLabel("Provincia", 12, 32, 266, 220, 22);
		createLabel("Latitud", 12, 25, 338, 126, 22);
		createLabel("Longitud", 12, 25, 371, 159, 22);
		createLabel("Lista de ciudades seleccionadas", 15, 343, 26, 257, 14);


		JTextField textFieldName = createTextField(32, 234, 220, 20, 10, JTextField.CENTER);
		JTextField textFieldProvince = createTextField(32, 299, 220, 20, 10, JTextField.CENTER);
		JTextField textFieldLatitude = createTextField(195, 340, 57, 20, 10, JTextField.CENTER);
		JTextField textFieldLongitude = createTextField(195, 373, 57, 20, 10, JTextField.CENTER);
		
		entryValidation(textFieldLatitude);
		entryValidation(textFieldLongitude);

		JButton btnAddListedCity = new JButton("Agregar Ciudad");
		btnAddListedCity.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (selectedCities.size() >= 20) {
		            showMessageDialog("Solo se permiten hasta 20 ciudades.");
		            return;
		        }
		        if (comboBox.getSelectedIndex() != -1) {
		            City city = cities.get(comboBox.getSelectedIndex());
		            if (!selectedCities.contains(city)) {
		                selectedCities.add(city);
		                addCityInTable(city.getName(), city.getProvince());
		            } else {
		                showMessageDialog("La ciudad ya ha sido agregada.");
		            }
		        }
		    }
		});
		btnAddListedCity.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAddListedCity.setBounds(10, 110, 126, 36);
		contentPane.add(btnAddListedCity);
		
		JButton btnDeleteCity = new JButton("Eliminar Ciudad");
		btnDeleteCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() != -1) {
					City city = cities.get(comboBox.getSelectedIndex());
					if (selectedCities.contains(city)) {
						showMessageDialog("La ciudad fue eliminada con exito.");
						selectedCities.remove(city);
						for (int i = 0; i < tableModel.getRowCount(); i++) {
							if (tableModel.getValueAt(i, 0).equals(city.getName())
									&& tableModel.getValueAt(i, 1).equals(city.getProvince())) {
								tableModel.removeRow(i);
								break;
							}
						}
					}
				}
			}
		});
		btnDeleteCity.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDeleteCity.setBounds(146, 110, 126, 36);
		contentPane.add(btnDeleteCity);

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
			                        addCityInTable(textFieldName.getText(), textFieldProvince.getText());
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
					MapScreen mapScreen = new MapScreen(mst);
					mapScreen.setResizable(false);
					mapScreen.setVisible(true);
				}
			}
		});	
		btnMST.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMST.setBounds(409, 473, 126, 36);
		contentPane.add(btnMST);

		createLabel("-54 < x < -22", 11, 92, 336, 96, 26);
		createLabel("-70 < x < -53", 11, 92, 370, 96, 26);

	}
	
	// Metodos Auxiliares
	
	private void createLabel(String text, int fontSize, int x, int y, int width, int height) {
	    JLabel label = new JLabel(text);
	    Font font = new Font("Tahoma", Font.BOLD, fontSize);
	    label.setFont(font);
	    label.setBounds(x, y, width, height);
	    contentPane.add(label);
	}
	
	private JTextField createTextField(int x, int y, int width, int height, int columns, int horizontalAlignment) {
	    JTextField textField = new JTextField();
	    textField.setHorizontalAlignment(horizontalAlignment);
	    textField.setColumns(columns);
	    textField.setBounds(x, y, width, height);
	    contentPane.add(textField);
	    return textField;
	}
	
	private void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private City createCity() {
		 return  connectingCities.createCity(textFieldName.getText(), textFieldProvince.getText(),
									Double.parseDouble(textFieldLatitude.getText()), Double.parseDouble(textFieldLongitude.getText()));
	}

	private void addCityInTable(String city, String province) {
		    Object[] row = {city, province};
		    tableModel.addRow(row);
		}

	private boolean missingCityData() {
		return textFieldName.getText().equals("") || textFieldProvince.getText().equals("")
				|| textFieldLatitude.getText().equals("") || textFieldLongitude.getText().equals("");
	}

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
