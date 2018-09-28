package pantallas;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

public class Opciones {
	private static JPasswordField ClaveVieja;
	private static JPasswordField ClaveNueva;
	private static JPasswordField ClaveRepetir;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame VentanaOpciones = new JFrame("Electro Autos Carrillo - Usuarios");
		VentanaOpciones.getContentPane().setBackground(new Color(75, 0, 130));
		VentanaOpciones.setSize(1000, 620);
		VentanaOpciones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		VentanaOpciones.setResizable(false);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		VentanaOpciones.setLocation(dim.width/2-VentanaOpciones.getSize().width/2, dim.height/2-VentanaOpciones.getSize().height/2);
		VentanaOpciones.getContentPane().setLayout(null);
		
		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaOpciones.dispose();
				Usuarios.main(args);
			}
		});
		btnUsuarios.setFocusPainted(false);
		btnUsuarios.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnUsuarios.setForeground(new Color(75, 0, 130));
		btnUsuarios.setBackground(Color.WHITE);
		btnUsuarios.setIcon(new ImageIcon(Opciones.class.getResource("/imagenes/UsuariosIcon.png")));
		btnUsuarios.setBounds(6, 6, 235, 123);
		VentanaOpciones.getContentPane().add(btnUsuarios);
		
		JButton btnInventario = new JButton("  Inventario");
		btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaOpciones.dispose();
				Inventario.main(args);
			}
		});
		btnInventario.setFocusPainted(false);
		btnInventario.setIcon(new ImageIcon(Opciones.class.getResource("/imagenes/InventarioIcon.png")));
		btnInventario.setForeground(new Color(75, 0, 130));
		btnInventario.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnInventario.setBackground(new Color(255, 255, 255));
		btnInventario.setBounds(248, 6, 235, 123);
		VentanaOpciones.getContentPane().add(btnInventario);
		
		JButton btnVentas = new JButton("  Ventas");
		btnVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaOpciones.dispose();
				Ventas.main(args);
			}
		});
		btnVentas.setFocusPainted(false);
		btnVentas.setIcon(new ImageIcon(Opciones.class.getResource("/imagenes/VentasIcon.png")));
		btnVentas.setForeground(new Color(75, 0, 130));
		btnVentas.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnVentas.setBackground(new Color(255, 255, 255));
		btnVentas.setBounds(495, 6, 235, 123);
		VentanaOpciones.getContentPane().add(btnVentas);
		
		JButton btnOpciones = new JButton("  Opciones");
		btnOpciones.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 255)));
		btnOpciones.setFocusPainted(false);
		btnOpciones.setIcon(new ImageIcon(Opciones.class.getResource("/imagenes/OpcionesIcon.png")));
		btnOpciones.setForeground(Color.WHITE);
		btnOpciones.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnOpciones.setBackground(new Color(75, 0, 130));
		btnOpciones.setBounds(742, 6, 235, 123);
		VentanaOpciones.getContentPane().add(btnOpciones);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(16, 141, 954, 10);
		VentanaOpciones.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(16, 576, 954, 10);
		VentanaOpciones.getContentPane().add(panel_1);
		
		JLabel lblNewLabel = new JLabel("CAMBIO DE CONTRASE\u00D1A");
		lblNewLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel.setBounds(44, 175, 248, 31);
		VentanaOpciones.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a vieja");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(87, 257, 128, 16);
		VentanaOpciones.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNuevaContrasea = new JLabel("Nueva Contrase\u00F1a");
		lblNuevaContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevaContrasea.setForeground(Color.WHITE);
		lblNuevaContrasea.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNuevaContrasea.setBounds(87, 306, 128, 16);
		VentanaOpciones.getContentPane().add(lblNuevaContrasea);
		
		JLabel lblRepetirContrasea = new JLabel("Repetir contrase\u00F1a");
		lblRepetirContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblRepetirContrasea.setForeground(Color.WHITE);
		lblRepetirContrasea.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblRepetirContrasea.setBounds(87, 358, 128, 16);
		VentanaOpciones.getContentPane().add(lblRepetirContrasea);
		
		ClaveVieja = new JPasswordField();
		ClaveVieja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClaveNueva.requestFocus();
			}
		});
		ClaveVieja.setForeground(new Color(153, 50, 204));
		ClaveVieja.setFont(new Font("SansSerif", Font.BOLD, 12));
		ClaveVieja.setHorizontalAlignment(SwingConstants.CENTER);
		ClaveVieja.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(128, 128, 128)));
		ClaveVieja.setBounds(259, 251, 168, 31);
		VentanaOpciones.getContentPane().add(ClaveVieja);
		
		ClaveNueva = new JPasswordField();
		ClaveNueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClaveRepetir.requestFocus();
			}
		});
		ClaveNueva.setForeground(new Color(153, 50, 204));
		ClaveNueva.setFont(new Font("SansSerif", Font.BOLD, 12));
		ClaveNueva.setHorizontalAlignment(SwingConstants.CENTER);
		ClaveNueva.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(128, 128, 128)));
		ClaveNueva.setBounds(259, 300, 168, 31);
		VentanaOpciones.getContentPane().add(ClaveNueva);
		
		ClaveRepetir = new JPasswordField();
		ClaveRepetir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ClaveVieja.getPassword().length == 0){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE INTRODUCIR SU VIEJA CONTRASEÑA", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					ClaveVieja.requestFocus();
					ClaveVieja.selectAll();
					return;
				}
				if(ClaveNueva.getPassword().length == 0){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE INTRODUCIR SU NUEVA CONTRASEÑA", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					ClaveNueva.requestFocus();
					ClaveNueva.selectAll();
					return;
				}
				if(ClaveRepetir.getPassword().length == 0){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE REPETIR LA NUEVA CONTRASEÑA", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					ClaveRepetir.requestFocus();
					ClaveRepetir.selectAll();
					return;
				}
				if(String.valueOf(ClaveVieja.getPassword()).equals(String.valueOf(ClaveNueva.getPassword()))){
					JOptionPane.showMessageDialog(null, "ERROR: LA CONTRASEÑA NUEVA NO PUEDE SER LA MISMA A LA ANTERIOR", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					ClaveNueva.requestFocus();
					ClaveNueva.selectAll();
					return;
				}
				if(!String.valueOf(ClaveNueva.getPassword()).equals(String.valueOf(ClaveRepetir.getPassword()))){
					JOptionPane.showMessageDialog(null, "ERROR: LA CONTRASEÑA NUEVA NO HA SIDO ESCRITA IGUAL EN EL CAMPO REPETIR", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					ClaveNueva.requestFocus();
					ClaveNueva.selectAll();
					return;
				}
				
				
				
				BD op = new BD();
				if(op.VerificarContraseña(ClaveVieja.getPassword()) == false){
					JOptionPane.showMessageDialog(null, "ERROR: CONTRASEÑA ANTIGUA INCORRECTA", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);	
					ClaveVieja.requestFocus();
					ClaveVieja.selectAll();
			return;
		}
				try {
					op.ActualizarContraseña(ClaveNueva.getPassword());
					JOptionPane.showMessageDialog(null, "INFO: CONTRASEÑA CAMBIADA CON EXITO", "Electro Auto Carrillo", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		ClaveRepetir.setForeground(new Color(153, 50, 204));
		ClaveRepetir.setFont(new Font("SansSerif", Font.BOLD, 12));
		ClaveRepetir.setHorizontalAlignment(SwingConstants.CENTER);
		ClaveRepetir.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(128, 128, 128)));
		ClaveRepetir.setBounds(259, 351, 168, 31);
		VentanaOpciones.getContentPane().add(ClaveRepetir);
		
		JButton btnCambiar = new JButton("Cambiar contrase\u00F1a");
		btnCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				if(ClaveVieja.getPassword().length == 0){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE INTRODUCIR SU VIEJA CONTRASEÑA", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					ClaveVieja.requestFocus();
					ClaveVieja.selectAll();
					return;
				}
				if(ClaveNueva.getPassword().length == 0){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE INTRODUCIR SU NUEVA CONTRASEÑA", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					ClaveNueva.requestFocus();
					ClaveNueva.selectAll();
					return;
				}
				if(ClaveRepetir.getPassword().length == 0){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE REPETIR LA NUEVA CONTRASEÑA", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					ClaveRepetir.requestFocus();
					ClaveRepetir.selectAll();
					return;
				}
				if(String.valueOf(ClaveVieja.getPassword()).equals(String.valueOf(ClaveNueva.getPassword()))){
					JOptionPane.showMessageDialog(null, "ERROR: LA CONTRASEÑA NUEVA NO PUEDE SER LA MISMA A LA ANTERIOR", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					ClaveNueva.requestFocus();
					ClaveNueva.selectAll();
					return;
				}
				if(!String.valueOf(ClaveNueva.getPassword()).equals(String.valueOf(ClaveRepetir.getPassword()))){
					JOptionPane.showMessageDialog(null, "ERROR: LA CONTRASEÑA NUEVA NO HA SIDO ESCRITA IGUAL EN EL CAMPO REPETIR", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					ClaveNueva.requestFocus();
					ClaveNueva.selectAll();
					return;
				}
				
				
				
				BD op = new BD();
				if(op.VerificarContraseña(ClaveVieja.getPassword()) == false){
					JOptionPane.showMessageDialog(null, "ERROR: CONTRASEÑA ANTIGUA INCORRECTA", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);	
					ClaveVieja.requestFocus();
					ClaveVieja.selectAll();
			return;
		}
				try {
					op.ActualizarContraseña(ClaveNueva.getPassword());
					JOptionPane.showMessageDialog(null, "INFO: CONTRASEÑA CAMBIADA CON EXITO", "Electro Auto Carrillo", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCambiar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnCambiar.setBackground(new Color(75, 0, 130));
		btnCambiar.setForeground(new Color(255, 255, 255));
		btnCambiar.setBounds(129, 429, 163, 28);
		VentanaOpciones.getContentPane().add(btnCambiar);
		
		JButton btnResetear = new JButton("Resetear");
		btnResetear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClaveVieja.setText("");
				ClaveNueva.setText("");
				ClaveRepetir.setText("");
			}
		});
		btnResetear.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnResetear.setBackground(new Color(75, 0, 130));
		btnResetear.setForeground(new Color(255, 255, 255));
		btnResetear.setBounds(294, 429, 90, 28);
		VentanaOpciones.getContentPane().add(btnResetear);
		
		JLabel lblDatos = new JLabel("DATOS");
		lblDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatos.setForeground(Color.WHITE);
		lblDatos.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblDatos.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		lblDatos.setBounds(762, 188, 99, 31);
		VentanaOpciones.getContentPane().add(lblDatos);
		
		JLabel lblCdulaDeIdentidad = new JLabel("C\u00E9dula de Identidad");
		lblCdulaDeIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCdulaDeIdentidad.setForeground(Color.WHITE);
		lblCdulaDeIdentidad.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblCdulaDeIdentidad.setBounds(742, 257, 141, 16);
		VentanaOpciones.getContentPane().add(lblCdulaDeIdentidad);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo.setForeground(Color.WHITE);
		lblCargo.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblCargo.setBounds(742, 328, 151, 16);
		VentanaOpciones.getContentPane().add(lblCargo);
		
		JLabel lblUID = new JLabel("");
		lblUID.setHorizontalAlignment(SwingConstants.CENTER);
		lblUID.setForeground(new Color(102, 255, 0));
		lblUID.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblUID.setBounds(742, 273, 141, 16);
		VentanaOpciones.getContentPane().add(lblUID);
		
		lblUID.setText(Integer.toString(Acceso.Cedula));
		
		JLabel lblUCargo = new JLabel("");
		lblUCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblUCargo.setForeground(new Color(51, 255, 0));
		lblUCargo.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblUCargo.setBounds(742, 345, 151, 16);
		VentanaOpciones.getContentPane().add(lblUCargo);
		
		lblUCargo.setText(Acceso.Cargo);
		
		VentanaOpciones.setVisible(true);
		
		ClaveVieja.requestFocus();
		
		if(!Acceso.Cargo.equalsIgnoreCase("Administrador")){
			btnUsuarios.setEnabled(false);
		}

	}
}
