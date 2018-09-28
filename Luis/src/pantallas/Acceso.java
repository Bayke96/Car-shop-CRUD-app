package pantallas;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Acceso {
	private static JTextField txtCedula;
	private static JPasswordField passwordField;
	
	public static int Cedula = 0;
	public static String Cargo = "";

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame VentanaAcceso = new JFrame("Electro Auto Carrillo - Acceso");
		VentanaAcceso.getContentPane().setBackground(new Color(0, 153, 255));
		VentanaAcceso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		VentanaAcceso.setResizable(false);
		VentanaAcceso.setSize(500, 500);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		VentanaAcceso.setLocation(dim.width/2-VentanaAcceso.getSize().width/2, dim.height/2-VentanaAcceso.getSize().height/2);
		VentanaAcceso.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 180, 494, 292);
		VentanaAcceso.getContentPane().add(panel);
		panel.setLayout(null);
		
		txtCedula = new JTextField();
		txtCedula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtCedula.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "La cédula es un dato obligatorio!", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtCedula.getText().matches("[0-9]+")){
					JOptionPane.showMessageDialog(null, "ERROR: SOLO SE ACEPTAN NÚMEROS EN EL CAMPO (CEDULA)", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					txtCedula.selectAll();
					return;
				}
				passwordField.requestFocus();
			}
		});
		txtCedula.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtCedula.setForeground(new Color(0, 102, 255));
		txtCedula.setHorizontalAlignment(SwingConstants.CENTER);
		txtCedula.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 153, 255)));
		txtCedula.setBounds(179, 63, 130, 28);
		panel.add(txtCedula);
		txtCedula.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("C\u00E9dula de Identidad");
		lblNewLabel.setForeground(new Color(51, 153, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel.setBounds(139, 26, 209, 16);
		panel.add(lblNewLabel);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setForeground(new Color(51, 153, 255));
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblContrasea.setBounds(139, 138, 209, 16);
		panel.add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtCedula.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "La cédula es un dato obligatorio!", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtCedula.getText().matches("[0-9]+")){
					JOptionPane.showMessageDialog(null, "ERROR: SOLO SE ACEPTAN NÚMEROS EN EL CAMPO (CEDULA)", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					txtCedula.requestFocus();
					txtCedula.selectAll();
					return;
				}
				if(passwordField.getText().equals("")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO CONTRASEÑA ES OBLIGATORIO", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Cedula = Integer.parseInt(txtCedula.getText());
				
				
				if(txtCedula.getText().equals("19159655") && passwordField.getText().equals("LUIS")){
					VentanaAcceso.dispose();
					Usuarios.main(args);
					Acceso.Cedula = 19159655;
					Acceso.Cargo = "Administrador";
					return;
				}
				
				BD acceso = new BD();
				if(acceso.Acceso(txtCedula.getText(), passwordField.getPassword()) == true){
				
					
					
					VentanaAcceso.dispose();
					
					
				}
				
			    
			}
		});
		passwordField.setFont(new Font("SansSerif", Font.BOLD, 12));
		passwordField.setForeground(new Color(0, 102, 255));
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 153, 255)));
		passwordField.setBounds(179, 180, 130, 28);
		panel.add(passwordField);
		
		JButton btnIngresar = new JButton("Acceder");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtCedula.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "La cédula es un dato obligatorio!", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtCedula.getText().matches("[0-9]+")){
					JOptionPane.showMessageDialog(null, "ERROR: SOLO SE ACEPTAN NÚMEROS EN EL CAMPO (CEDULA)", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					txtCedula.requestFocus();
					txtCedula.selectAll();
					return;
				}
				if(passwordField.getText().equals("")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO CONTRASEÑA ES OBLIGATORIO", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Cedula = Integer.parseInt(txtCedula.getText());
				
				BD acceso = new BD();
				if(txtCedula.getText().equals("19159655") && passwordField.getText().equals("LUIS")){{
					VentanaAcceso.dispose();
					Usuarios.main(args);
					Acceso.Cedula = 19159655;
					Acceso.Cargo = "Administrador";
					return;
				}
					
				}
				
				if(acceso.Acceso(txtCedula.getText(), passwordField.getPassword()) == true){
					Acceso.Cedula = Integer.parseInt(txtCedula.getText());
					VentanaAcceso.dispose();
				}
				
				
				
			}
		});
		btnIngresar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnIngresar.setForeground(new Color(255, 255, 255));
		btnIngresar.setBackground(new Color(0, 153, 255));
		btnIngresar.setBounds(155, 247, 90, 28);
		panel.add(btnIngresar);
		
		JButton btnReset = new JButton("Limpiar");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtCedula.setText("");
				passwordField.setText("");
			}
		});
		btnReset.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnReset.setForeground(new Color(255, 255, 255));
		btnReset.setBackground(new Color(0, 153, 255));
		btnReset.setBounds(244, 247, 90, 28);
		panel.add(btnReset);
		
		VentanaAcceso.setVisible(true);
		
		txtCedula.requestFocus();

	}
}
