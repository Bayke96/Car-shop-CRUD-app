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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;



import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



public class Usuarios {
	private static JTextField txtACedula;
	private static JTextField txtAContraseña;
	private static JTextField txtMCedula;
	private static JTable tablausuarios;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame VentanaUsuarios = new JFrame("Electro Autos Carrillo - Usuarios");
		VentanaUsuarios.getContentPane().setBackground(new Color(0, 153, 255));
		VentanaUsuarios.setSize(1000, 620);
		VentanaUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		VentanaUsuarios.setResizable(false);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		VentanaUsuarios.setLocation(dim.width/2-VentanaUsuarios.getSize().width/2, dim.height/2-VentanaUsuarios.getSize().height/2);
		VentanaUsuarios.getContentPane().setLayout(null);
		
		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.setFocusPainted(false);
		btnUsuarios.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnUsuarios.setForeground(new Color(255, 255, 255));
		btnUsuarios.setBackground(Color.BLUE);
		btnUsuarios.setIcon(new ImageIcon(Usuarios.class.getResource("/imagenes/UsuariosIcon.png")));
		btnUsuarios.setBounds(6, 6, 235, 123);
		VentanaUsuarios.getContentPane().add(btnUsuarios);
		
		JButton btnInventario = new JButton("  Inventario");
		btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaUsuarios.dispose();
				Inventario.main(args);
			}
		});
		btnInventario.setFocusPainted(false);
		btnInventario.setIcon(new ImageIcon(Usuarios.class.getResource("/imagenes/InventarioIcon.png")));
		btnInventario.setForeground(new Color(0, 153, 255));
		btnInventario.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnInventario.setBackground(new Color(255, 255, 255));
		btnInventario.setBounds(248, 6, 235, 123);
		VentanaUsuarios.getContentPane().add(btnInventario);
		
		JButton btnVentas = new JButton("  Ventas");
		btnVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaUsuarios.dispose();
				Ventas.main(args);
			}
		});
		btnVentas.setFocusPainted(false);
		btnVentas.setIcon(new ImageIcon(Usuarios.class.getResource("/imagenes/VentasIcon.png")));
		btnVentas.setForeground(new Color(0, 153, 255));
		btnVentas.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnVentas.setBackground(new Color(255, 255, 255));
		btnVentas.setBounds(495, 6, 235, 123);
		VentanaUsuarios.getContentPane().add(btnVentas);
		
		JButton btnOpciones = new JButton("  Opciones");
		btnOpciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaUsuarios.dispose();
				Opciones.main(args);
			}
		});
		btnOpciones.setFocusPainted(false);
		btnOpciones.setIcon(new ImageIcon(Usuarios.class.getResource("/imagenes/OpcionesIcon.png")));
		btnOpciones.setForeground(new Color(0, 153, 255));
		btnOpciones.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnOpciones.setBackground(new Color(255, 255, 255));
		btnOpciones.setBounds(742, 6, 235, 123);
		VentanaUsuarios.getContentPane().add(btnOpciones);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(16, 141, 954, 10);
		VentanaUsuarios.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(16, 576, 954, 10);
		VentanaUsuarios.getContentPane().add(panel_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(26, 163, 940, 406);
		VentanaUsuarios.getContentPane().add(tabbedPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Crear", new ImageIcon(Usuarios.class.getResource("/imagenes/AddUser.png")), panel_2, "Agregar un nuevo usuario");
		tabbedPane.setForegroundAt(0, new Color(0, 153, 255));
		tabbedPane.setBackgroundAt(0, new Color(255, 255, 255));
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("AGREGAR NUEVO USUARIO");
		lblNewLabel.setForeground(new Color(0, 153, 255));
		lblNewLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLUE));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel.setBounds(6, 6, 275, 41);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("C\u00E9dula de Identidad");
		lblNewLabel_1.setForeground(new Color(0, 153, 255));
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(6, 76, 148, 29);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setForeground(new Color(0, 153, 255));
		lblContrasea.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblContrasea.setBounds(6, 148, 148, 29);
		panel_2.add(lblContrasea);
		
		JLabel lblPrivilegios = new JLabel("Privilegios");
		lblPrivilegios.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrivilegios.setForeground(new Color(0, 153, 255));
		lblPrivilegios.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblPrivilegios.setBounds(6, 218, 148, 29);
		panel_2.add(lblPrivilegios);
		
		txtACedula = new JTextField();
		
		txtACedula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtACedula.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO CEDULA ES OBLIGATORIO", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtACedula.getText().matches("[0-9]+")){
					JOptionPane.showMessageDialog(null, "ERROR: SOLO SE ACEPTAN NÚMEROS EN EL CAMPO (CEDULA)", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					txtACedula.selectAll();
					return;
				}
				
				txtAContraseña.requestFocus();
			}
		});
		txtACedula.setForeground(new Color(51, 51, 255));
		txtACedula.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtACedula.setHorizontalAlignment(SwingConstants.CENTER);
		txtACedula.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 153, 255)));
		txtACedula.setBounds(159, 76, 122, 28);
		panel_2.add(txtACedula);
		txtACedula.setColumns(10);
		
		txtAContraseña = new JTextField();
		
		txtAContraseña.setForeground(new Color(51, 51, 255));
		txtAContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		txtAContraseña.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 153, 255)));
		txtAContraseña.setBounds(159, 148, 122, 28);
		panel_2.add(txtAContraseña);
		txtAContraseña.setColumns(10);
		
		JComboBox comboBoxARango = new JComboBox();
		comboBoxARango.setForeground(new Color(255, 255, 255));
		comboBoxARango.setBackground(new Color(0, 153, 255));
		comboBoxARango.setFont(new Font("SansSerif", Font.BOLD, 12));
		comboBoxARango.setModel(new DefaultComboBoxModel(new String[] {"Elegir", "Administrador", "Vendedor", "Consultor"}));
		comboBoxARango.setBounds(157, 219, 192, 26);
		panel_2.add(comboBoxARango);
		
		JComboBox comboBoxMCedula = new JComboBox();
		JComboBox comboBoxECedula = new JComboBox();
		
		JButton btnAceptar = new JButton("Crear");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtACedula.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO CEDULA ES OBLIGATORIO", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtACedula.getText().matches("[0-9]+")){
					JOptionPane.showMessageDialog(null, "ERROR: SOLO SE ACEPTAN NÚMEROS EN EL CAMPO (CEDULA)", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					txtACedula.requestFocus();
					txtACedula.selectAll();
					return;
				}
				if(txtAContraseña.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO CONTRASEÑA ES OBLIGATORIO", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(comboBoxARango.getSelectedItem().toString().equalsIgnoreCase("Elegir")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE ELEGIR UN RANGO PARA EL USUARIO", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				BD usuario = new BD();
				try {
					usuario.CrearUsuario(Integer.parseInt(txtACedula.getText()), txtAContraseña.getText(), comboBoxARango.getSelectedItem().toString());
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				txtACedula.setText("");
				txtAContraseña.setText("");
				comboBoxARango.setSelectedItem("Elegir");
				JOptionPane.showMessageDialog(null, "USUARIO AGREGADO EXITOSAMENTE", "Electro Auto Carrillo", JOptionPane.INFORMATION_MESSAGE);
				
				
				DefaultTableModel dm = (DefaultTableModel) tablausuarios.getModel();
				int rowCount = dm.getRowCount();
				//Remove rows one by one from the end of the table
				for (int i = rowCount - 1; i >= 0; i--) {
				    dm.removeRow(i);
				}
				
				 BD conexion = new BD();
				   Statement stmt = null;
				    conexion.Conectar("electro_auto");
				    try{
				    	DefaultTableModel model = (DefaultTableModel) tablausuarios.getModel();
				    	String selectSQL = "SELECT Cedula, Rango FROM Usuarios";
						  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
						  ResultSet rs = preparedStatement.executeQuery(selectSQL);
						  while (rs.next()) {
						  	
						  	
						  	
						  	
						  	model.addRow(new Object[]{rs.getInt("Cedula"), rs.getString("Rango")});
						  	
						  }
						  
					}
					catch(Exception ay){
							  System.err.println(ay);
						  }
				    conexion.Desconectar();
				    
				   conexion = new BD();
				    stmt = null;
				    conexion.Conectar("Electro_Auto");
				    try{
				    	
				    	String selectSQL = "SELECT Cedula FROM Usuarios";
						  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
						  ResultSet rs = preparedStatement.executeQuery(selectSQL);
						  comboBoxMCedula.setModel(new DefaultComboBoxModel(new String[] {"Elegir"}));
						  comboBoxECedula.setModel(new DefaultComboBoxModel(new String[] {"Elegir"}));
						  while (rs.next()) {
						  	comboBoxMCedula.addItem(rs.getInt("Cedula"));
						  	comboBoxECedula.addItem(rs.getInt("Cedula"));
						  }
						  
					}
					catch(Exception xe){
							  System.err.println(xe);
						  }
			}
		});
		btnAceptar.setForeground(new Color(255, 255, 255));
		btnAceptar.setBackground(new Color(0, 153, 255));
		btnAceptar.setBounds(731, 6, 98, 26);
		panel_2.add(btnAceptar);
		
		JComboBox comboBoxNRango = new JComboBox();
		
		JButton btnBorrar = new JButton("Resetear");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtACedula.setText("");
				txtAContraseña.setText("");
				comboBoxARango.setSelectedItem("Elegir");
			}
		});
		btnBorrar.setForeground(new Color(255, 255, 255));
		btnBorrar.setBackground(new Color(0, 153, 255));
		btnBorrar.setBounds(831, 6, 98, 26);
		panel_2.add(btnBorrar);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		tabbedPane.addTab("Actualizar", new ImageIcon(Usuarios.class.getResource("/imagenes/EditUser.png")), panel_3, "Modificar un usuario existente");
		panel_3.setLayout(null);
		
		JLabel lblActualizarUsuario = new JLabel("ACTUALIZAR USUARIO");
		lblActualizarUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualizarUsuario.setForeground(new Color(0, 153, 255));
		lblActualizarUsuario.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblActualizarUsuario.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLUE));
		lblActualizarUsuario.setBounds(12, 12, 275, 41);
		panel_3.add(lblActualizarUsuario);
		
		JLabel lblBuscarCedula = new JLabel("C\u00E9dula");
		lblBuscarCedula.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarCedula.setForeground(new Color(0, 153, 255));
		lblBuscarCedula.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblBuscarCedula.setBounds(32, 77, 148, 29);
		panel_3.add(lblBuscarCedula);
		
		
		comboBoxMCedula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMCedula.setText(comboBoxMCedula.getSelectedItem().toString());
			}
		});
		comboBoxMCedula.setModel(new DefaultComboBoxModel(new String[] {"Elegir"}));
		comboBoxMCedula.setForeground(Color.WHITE);
		comboBoxMCedula.setFont(new Font("SansSerif", Font.BOLD, 12));
		comboBoxMCedula.setBackground(new Color(0, 153, 255));
		comboBoxMCedula.setBounds(183, 78, 192, 26);
		panel_3.add(comboBoxMCedula);
		
		JPanel panel_4 = new JPanel();
		
		JLabel lblUCargo = new JLabel("");
		lblUCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblUCargo.setBounds(193, 179, 153, 27);
		panel_4.add(lblUCargo);
		
		JLabel lblUCedula = new JLabel("");
		lblUCedula.setHorizontalAlignment(SwingConstants.CENTER);
		lblUCedula.setBounds(193, 267, 153, 27);
		panel_4.add(lblUCedula);
		
		
		
		comboBoxECedula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BD op = new BD();
				op.Conectar("Electro_Auto");
				
				if(!comboBoxECedula.getSelectedItem().toString().equalsIgnoreCase("elegir")){
					try{
						  String selectSQL = "SELECT Rango FROM Usuarios WHERE Cedula = ?";
						  PreparedStatement preparedStatement = op.conn.prepareStatement(selectSQL);
						  preparedStatement.setInt(1, Integer.parseInt(comboBoxECedula.getSelectedItem().toString()));
						  ResultSet rs = preparedStatement.executeQuery();
						  while (rs.next()) {
						  	String Rango = rs.getString("Rango");
						  	lblUCargo.setText(Rango);
						  }
						  lblUCedula.setText(comboBoxECedula.getSelectedItem().toString());
						  
					}
					catch(Exception ayy){
							  System.err.println(ayy);
						  }
				}
				
				
				
			}
		});
		
		BD conexion = new BD();
	    Statement stmt = null;
	    conexion.Conectar("Electro_Auto");
	    try{
	    	
	    	String selectSQL = "SELECT Cedula FROM Usuarios";
			  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
			  ResultSet rs = preparedStatement.executeQuery(selectSQL);
			  comboBoxMCedula.setModel(new DefaultComboBoxModel(new String[] {"Elegir"}));
			  comboBoxECedula.setModel(new DefaultComboBoxModel(new String[] {"Elegir"}));
			  while (rs.next()) {
			  	comboBoxMCedula.addItem(rs.getInt("Cedula"));
			  	comboBoxECedula.addItem(rs.getInt("Cedula"));
			  }
			  
		}
		catch(Exception xe){
				  System.err.println(xe);
			  }
		
		JLabel label = new JLabel("C\u00E9dula de Identidad");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 153, 255));
		label.setFont(new Font("SansSerif", Font.BOLD, 12));
		label.setBounds(32, 130, 148, 29);
		panel_3.add(label);
		
		txtMCedula = new JTextField();
		txtMCedula.setHorizontalAlignment(SwingConstants.CENTER);
		txtMCedula.setForeground(new Color(51, 51, 255));
		txtMCedula.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtMCedula.setColumns(10);
		txtMCedula.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 153, 255)));
		txtMCedula.setBounds(221, 130, 122, 28);
		panel_3.add(txtMCedula);
		
		JLabel label_1 = new JLabel("Privilegios");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(new Color(0, 153, 255));
		label_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		label_1.setBounds(32, 185, 148, 29);
		panel_3.add(label_1);
		
		
		comboBoxNRango.setModel(new DefaultComboBoxModel(new String[] {"Elegir", "Administrador", "Vendedor", "Consultor"}));
		comboBoxNRango.setForeground(Color.WHITE);
		comboBoxNRango.setFont(new Font("SansSerif", Font.BOLD, 12));
		comboBoxNRango.setBackground(new Color(0, 153, 255));
		comboBoxNRango.setBounds(183, 186, 192, 26);
		panel_3.add(comboBoxNRango);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxMCedula.getSelectedItem().toString().equalsIgnoreCase("Elegir")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE SELECCIONAR UN N° DE CEDULA", "Electro Auto Carrillo - ERROR", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtMCedula.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE INTRODUCIR UNA CEDULA OBLIGATORIAMENTE", "Electro Auto Carrillo - ERROR", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(comboBoxNRango.getSelectedItem().toString().equals("Elegir")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE SELECCIONAR RANGO PARA EL USUARIO", "Electro Auto Carrillo - ERROR", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				BD modificar = new BD();
				try {
					if(modificar.BuscarUsuario(comboBoxMCedula.getSelectedItem().toString()) == true && !comboBoxMCedula.getSelectedItem().toString().equalsIgnoreCase(txtMCedula.getText())){
						JOptionPane.showMessageDialog(null, "ERROR: ESTA CEDULA YA SE ENCUENTRA REGISTRADA EN EL SISTEMA!", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					modificar.ActualizarUsuario(Integer.parseInt(comboBoxMCedula.getSelectedItem().toString()), Integer.parseInt(txtMCedula.getText()), 
							comboBoxNRango.getSelectedItem().toString());
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				BD conexion = new BD();
			    Statement stmt = null;
			    conexion.Conectar("Electro_Auto");
			    try{
			    	
			    	String selectSQL = "SELECT Cedula FROM Usuarios";
					  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
					  ResultSet rs = preparedStatement.executeQuery(selectSQL);
					  comboBoxMCedula.setModel(new DefaultComboBoxModel(new String[] {"Elegir"}));
					  while (rs.next()) {
					  	comboBoxMCedula.addItem(rs.getInt("Cedula"));
					  }
					  
				}
				catch(Exception xe){
						  System.err.println(xe);
					  }
			    
			    txtMCedula.setText("");
			    comboBoxNRango.setSelectedItem("Elegir");
			    JOptionPane.showMessageDialog(null, "USUARIO MODIFICADO EXITOSAMENTE", "Electro Auto Carrillo", JOptionPane.INFORMATION_MESSAGE);
			    
			    DefaultTableModel dm = (DefaultTableModel) tablausuarios.getModel();
				int rowCount = dm.getRowCount();
				//Remove rows one by one from the end of the table
				for (int i = rowCount - 1; i >= 0; i--) {
				    dm.removeRow(i);
				}
				
				 conexion = new BD();
				   stmt = null;
				    conexion.Conectar("electro_auto");
				    try{
				    	DefaultTableModel model = (DefaultTableModel) tablausuarios.getModel();
				    	String selectSQL = "SELECT Cedula, Rango FROM Usuarios";
						  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
						  ResultSet rs = preparedStatement.executeQuery(selectSQL);
						  while (rs.next()) {
						  	
						  	
						  	
						  	
						  	model.addRow(new Object[]{rs.getInt("Cedula"), rs.getString("Rango")});
						  	
						  }
						  
					}
					catch(Exception ay){
							  System.err.println(ay);
						  }
				    conexion.Desconectar();
			}
		});
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setBackground(new Color(0, 153, 255));
		btnActualizar.setBounds(725, 12, 98, 26);
		panel_3.add(btnActualizar);
		
		JButton btnMBorrar = new JButton("Resetear");
		btnMBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtMCedula.setText("");
				comboBoxNRango.setSelectedItem("Elegir");
				
				BD conexion = new BD();
			    Statement stmt = null;
			    conexion.Conectar("Electro_Auto");
			    try{
			    	
			    	String selectSQL = "SELECT Cedula FROM Usuarios";
					  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
					  ResultSet rs = preparedStatement.executeQuery(selectSQL);
					  comboBoxMCedula.setModel(new DefaultComboBoxModel(new String[] {"Elegir"}));
					  while (rs.next()) {
					  	comboBoxMCedula.addItem(rs.getInt("Cedula"));
					  }
					  
				}
				catch(Exception xe){
						  System.err.println(xe);
					  }
			}
		});
		btnMBorrar.setForeground(Color.WHITE);
		btnMBorrar.setBackground(new Color(0, 153, 255));
		btnMBorrar.setBounds(825, 12, 98, 26);
		panel_3.add(btnMBorrar);
		tabbedPane.setForegroundAt(1, new Color(0, 153, 255));
		
		
		panel_4.setBackground(Color.WHITE);
		tabbedPane.addTab("Eliminar", new ImageIcon(Usuarios.class.getResource("/imagenes/Deleteuser.png")), panel_4, "Eliminar un usuario");
		panel_4.setLayout(null);
		
		JLabel lblEliminarUsuario = new JLabel("ELIMINAR USUARIO");
		lblEliminarUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminarUsuario.setForeground(new Color(0, 153, 255));
		lblEliminarUsuario.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblEliminarUsuario.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLUE));
		lblEliminarUsuario.setBounds(12, 12, 275, 41);
		panel_4.add(lblEliminarUsuario);
		
		JLabel label_2 = new JLabel("C\u00E9dula");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(new Color(0, 153, 255));
		label_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		label_2.setBounds(22, 76, 148, 29);
		panel_4.add(label_2);
		
		
		comboBoxECedula.setForeground(Color.WHITE);
		comboBoxECedula.setFont(new Font("SansSerif", Font.BOLD, 12));
		comboBoxECedula.setBackground(new Color(0, 153, 255));
		comboBoxECedula.setBounds(173, 77, 192, 26);
		panel_4.add(comboBoxECedula);
		
		JLabel lblNewLabel_2 = new JLabel("C\u00E9dula");
		lblNewLabel_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(193, 218, 153, 29);
		panel_4.add(lblNewLabel_2);
		
		JLabel label_3 = new JLabel("Cargo");
		label_3.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(153, 153, 153)));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(193, 138, 153, 29);
		panel_4.add(label_3);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxECedula.getSelectedItem().toString().equalsIgnoreCase("Elegir")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE ELEGIR UN N° DE CEDULA", "Electro Auto Carrillo - ERROR", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				BD conexion = new BD();
				
				try {
					conexion.EliminarUsuario(Integer.parseInt(comboBoxECedula.getSelectedItem().toString()));
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "USUARIO ELIMINADO EXITOSAMENTE", "Electro Auto Carrillo", JOptionPane.INFORMATION_MESSAGE);
				
				lblUCedula.setText("");
				lblUCargo.setText("");
				
				
			    Statement stmt = null;
			    conexion.Conectar("Electro_Auto");
			    try{
			    	
			    	String selectSQL = "SELECT Cedula FROM Usuarios";
					  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
					  ResultSet rs = preparedStatement.executeQuery(selectSQL);
					  comboBoxECedula.setModel(new DefaultComboBoxModel(new String[] {"Elegir"}));
					  comboBoxMCedula.setModel(new DefaultComboBoxModel(new String[] {"Elegir"}));
					  while (rs.next()) {
					  	comboBoxECedula.addItem(rs.getInt("Cedula"));
					  	comboBoxMCedula.addItem(rs.getInt("Cedula"));
					  }
					  
				}
				catch(Exception xe){
						  System.err.println(xe);
					  }
			    
			    
			    DefaultTableModel dm = (DefaultTableModel) tablausuarios.getModel();
				int rowCount = dm.getRowCount();
				//Remove rows one by one from the end of the table
				for (int i = rowCount - 1; i >= 0; i--) {
				    dm.removeRow(i);
				}
				
				  conexion = new BD();
				   stmt = null;
				    conexion.Conectar("electro_auto");
				    try{
				    	DefaultTableModel model = (DefaultTableModel) tablausuarios.getModel();
				    	String selectSQL = "SELECT Cedula, Rango FROM Usuarios";
						  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
						  ResultSet rs = preparedStatement.executeQuery(selectSQL);
						  while (rs.next()) {
						  	
						  	
						  	
						  	
						  	model.addRow(new Object[]{rs.getInt("Cedula"), rs.getString("Rango")});
						  	
						  }
						  
					}
					catch(Exception ay){
							  System.err.println(ay);
						  }
				    conexion.Desconectar();
			}
		});
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(new Color(0, 153, 255));
		btnEliminar.setBounds(725, 12, 98, 26);
		panel_4.add(btnEliminar);
		
		JButton btnEResetear = new JButton("Resetear");
		btnEResetear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblUCedula.setText("");
				lblUCargo.setText("");
				
				BD conexion = new BD();
			    Statement stmt = null;
			    conexion.Conectar("Electro_Auto");
			    try{
			    	
			    	String selectSQL = "SELECT Cedula FROM Usuarios";
					  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
					  ResultSet rs = preparedStatement.executeQuery(selectSQL);
					  comboBoxECedula.setModel(new DefaultComboBoxModel(new String[] {"Elegir"}));
					  while (rs.next()) {
					  	comboBoxECedula.addItem(rs.getInt("Cedula"));
					  }
					  
				}
				catch(Exception xe){
						  System.err.println(xe);
					  }
				
			}
		});
		btnEResetear.setForeground(Color.WHITE);
		btnEResetear.setBackground(new Color(0, 153, 255));
		btnEResetear.setBounds(825, 12, 98, 26);
		panel_4.add(btnEResetear);
		
		tabbedPane.setForegroundAt(2, new Color(0, 153, 255));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		tabbedPane.addTab("Visualizar", new ImageIcon(Usuarios.class.getResource("/imagenes/ViewIcon.png")), panel_5, "Visualizar a todos los usuarios");
		panel_5.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(0, 153, 255));
		scrollPane.setBounds(12, 12, 911, 346);
		panel_5.add(scrollPane);
		
		scrollPane.getViewport().setBackground(Color.WHITE);
		
		tablausuarios = new JTable();
		tablausuarios.setShowVerticalLines(false);
		tablausuarios.setOpaque(true);
		tablausuarios.setBackground(new Color(0, 153, 255));
		tablausuarios.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"<html><b>C\u00E9dula</b></html>", "<html><b>Cargo</b></html>"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		
		tablausuarios.setBorder(new MatteBorder(1, 0, 1, 0, (Color) Color.WHITE));
		tablausuarios.setRowSelectionAllowed(false);
		tablausuarios.setForeground(Color.WHITE);
		tablausuarios.setBackground(new Color(0, 153, 255));
		tablausuarios.setShowHorizontalLines(true);
		tablausuarios.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		tablausuarios.setGridColor(Color.WHITE);
		
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(Color.WHITE);
		headerRenderer.setForeground(new Color(30, 144, 255));
		headerRenderer.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 0; i < tablausuarios.getModel().getColumnCount(); i++) {
		        tablausuarios.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		}
		
		scrollPane.setViewportView(tablausuarios);
		
		 conexion = new BD();
		   stmt = null;
		    conexion.Conectar("electro_auto");
		    try{
		    	DefaultTableModel model = (DefaultTableModel) tablausuarios.getModel();
		    	String selectSQL = "SELECT Cedula, Rango FROM Usuarios";
				  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
				  ResultSet rs = preparedStatement.executeQuery(selectSQL);
				  while (rs.next()) {
				  	
				  	
				  	
				  	
				  	model.addRow(new Object[]{rs.getInt("Cedula"), rs.getString("Rango")});
				  	
				  }
				  
			}
			catch(Exception e){
					  System.err.println(e);
				  }
		    conexion.Desconectar();
		    
		    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		    centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		    tablausuarios.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		    tablausuarios.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		
		
		tabbedPane.setForegroundAt(3, new Color(0, 153, 255));
		
		VentanaUsuarios.setVisible(true);

	}
}
