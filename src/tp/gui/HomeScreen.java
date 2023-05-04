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
    private int sliderValue = 0;
    private JLabel valueLabel;

    public HomeScreen() {
        setTitle("\"Programacion III - Conectando Ciudades\"");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 613, 440);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
      
        Icon backgroundIcon = new ImageIcon(getClass().getResource("/tp/images/background.png"));
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 772, 531);
        
      
      

        JSlider slider = new JSlider();
        slider.setBounds(133, 214, 307, 26);
        slider.setMinimum(1);
        slider.setMaximum(500);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                sliderValue = slider.getValue();
                valueLabel.setText("USD " + sliderValue);
            }
        });
        contentPane.add(slider);
        
        valueLabel = new JLabel("USD 1");
        valueLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.setBounds(184, 248, 200, 26);
        contentPane.add(valueLabel);
        
        JButton btnSelectCities = new JButton("Continuar a seleccion de ciudades");
        btnSelectCities.setBackground(new Color(192, 192, 192));
        btnSelectCities.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MainScreen launch;
				try {
					launch = new MainScreen((double)sliderValue);
					launch.setResizable(false);
	        		launch.setVisible(true);
	        		launch.setLocationRelativeTo(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        	}
        });
        btnSelectCities.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnSelectCities.setBounds(173, 342, 256, 48);
        contentPane.add(btnSelectCities);
        
        JLabel lblTitle1 = new JLabel();
        lblTitle1.setForeground(new Color(255, 255, 255));
        lblTitle1.setBackground(new Color(192, 192, 192));
        lblTitle1.setFont(new Font("Tahoma", Font.BOLD, 23));
        lblTitle1.setText("Bienvenidos al TP2 : Conectando Ciudades");
        lblTitle1.setBounds(42, 84, 500, 42);
        contentPane.add(lblTitle1);
        
        JLabel lblTitle2 = new JLabel("Ingrese el costo del kilometro de cableado");
        lblTitle2.setForeground(new Color(255, 255, 255));
        lblTitle2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitle2.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle2.setBounds(118, 177, 334, 26);
        contentPane.add(lblTitle2);
        
        contentPane.add(backgroundLabel);

        ImageIcon icono = new ImageIcon(getClass().getResource("/tp/images/earth_icon.png"));
        setIconImage(icono.getImage());
    }
}

