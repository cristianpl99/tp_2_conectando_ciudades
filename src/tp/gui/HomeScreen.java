package tp.gui;

import java.awt.EventQueue;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class HomeScreen extends JFrame {

    private JPanel contentPane;
    private int sliderValue_1 = 0;
    private int sliderValue_2 = 0;
    private int sliderValue_3 = 0;
    private JLabel valueLabel_1;
    private JLabel valueLabel_2;
    private JLabel valueLabel_3;
	

    public HomeScreen() {
        setTitle("\"Programacion III - Conectando Ciudades\"");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 613, 536);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
      
        Icon backgroundIcon = new ImageIcon(getClass().getResource("/tp/images/background.png"));
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(-175, 0, 772, 531);
        


        JSlider slider_1 = new JSlider();
        slider_1.setBounds(122, 115, 307, 26);
        slider_1.setMinimum(1);
        slider_1.setMaximum(500);
        slider_1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                sliderValue_1 = slider_1.getValue();
                valueLabel_1.setText("USD " + sliderValue_1);
            }
        });
        contentPane.add(slider_1);
        
        valueLabel_1 = new JLabel("USD 1");
        valueLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        valueLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel_1.setBounds(421, 115, 200, 26);
        contentPane.add(valueLabel_1);
        
        JSlider slider_2 = new JSlider();
        slider_2.setMinimum(0);
        slider_2.setMaximum(100);
        slider_2.setBounds(122, 222, 307, 26);
        slider_2.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                sliderValue_2 = slider_2.getValue();
               
				valueLabel_2.setText(sliderValue_2 + " % ");
            }
        });
        contentPane.add(slider_2);
        
        valueLabel_2 = new JLabel("%");
        valueLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
        valueLabel_2.setBounds(437, 222, 200, 26);
        contentPane.add(valueLabel_2);
        
        JSlider slider_3 = new JSlider();
        slider_3.setMinimum(0);
        slider_3.setMaximum(500);
        slider_3.setBounds(122, 329, 307, 26);
        slider_3.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                sliderValue_3 = slider_3.getValue();    
				valueLabel_3.setText("USD " + sliderValue_3);
            }
        });
        contentPane.add(slider_3);
        
        
        valueLabel_3 = new JLabel("USD 1");
        valueLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
        valueLabel_3.setBounds(437, 329, 200, 26);
        contentPane.add(valueLabel_3);
        
        
        
        
        JButton btnSelectCities = new JButton("Continuar a seleccion de ciudades");
        btnSelectCities.setBackground(new Color(192, 192, 192));
        btnSelectCities.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MainScreen launch;
				try {
					launch = new MainScreen((double)sliderValue_1, (double) sliderValue_2, (double) sliderValue_3);
					launch.setResizable(false);
	        		launch.setVisible(true);
	        		launch.setLocationRelativeTo(null);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
        		
        	}
        });
        btnSelectCities.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnSelectCities.setBounds(145, 438, 256, 48);
        contentPane.add(btnSelectCities);
        
        JLabel lblTitle1 = new JLabel();
        lblTitle1.setForeground(new Color(255, 255, 255));
        lblTitle1.setBackground(new Color(192, 192, 192));
        lblTitle1.setFont(new Font("Tahoma", Font.BOLD, 23));
        lblTitle1.setText("Bienvenidos al TP2 : Conectando Ciudades");
        lblTitle1.setBounds(41, 25, 500, 42);
        contentPane.add(lblTitle1);
        
        JLabel lblTitle2 = new JLabel("Ingrese el costo del kilometro de cableado");
        lblTitle2.setForeground(new Color(255, 255, 255));
        lblTitle2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitle2.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle2.setBounds(117, 78, 334, 26);
        contentPane.add(lblTitle2);
        
        
        
        JLabel lblTitle3 = new JLabel("Aumento si la conexion tiene + 300km");
        lblTitle3.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle3.setForeground(Color.WHITE);
        lblTitle3.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitle3.setBounds(117, 166, 334, 26);
        contentPane.add(lblTitle3);
        
       
     
        
        JLabel lblCostoExtraSi = new JLabel("Costo extra si las localidades son de distintas provincias");
        lblCostoExtraSi.setHorizontalAlignment(SwingConstants.CENTER);
        lblCostoExtraSi.setForeground(Color.WHITE);
        lblCostoExtraSi.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCostoExtraSi.setBounds(77, 292, 420, 26);
        contentPane.add(lblCostoExtraSi);
        
        
        contentPane.add(backgroundLabel);
        ImageIcon icono = new ImageIcon(getClass().getResource("/tp/images/earth_icon.png"));
        setIconImage(icono.getImage());
    }
}

