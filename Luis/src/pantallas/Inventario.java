package pantallas;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import org.apache.commons.lang3.text.WordUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.scene.input.KeyCode;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Inventario {
	private static JTextField txtANombre;
	private static JTextField txtAPrecio;
	private static JTextField txtAUnidades;
	private static JTextField txtMNombre;
	private static JTextField txtMPrecio;
	private static JTextField txtMUnidades;
	private static JTable tablarepuestos;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame VentanaInventario = new JFrame("Electro Autos Carrillo - Usuarios");
		VentanaInventario.getContentPane().setBackground(new Color(50, 205, 50));
		VentanaInventario.setSize(1000, 620);
		VentanaInventario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		VentanaInventario.setResizable(false);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		VentanaInventario.setLocation(dim.width/2-VentanaInventario.getSize().width/2, dim.height/2-VentanaInventario.getSize().height/2);
		VentanaInventario.getContentPane().setLayout(null);
		
		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaInventario.dispose();
				Usuarios.main(args);
			}
		});
		btnUsuarios.setFocusPainted(false);
		btnUsuarios.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnUsuarios.setForeground(new Color(0, 128, 0));
		btnUsuarios.setBackground(Color.WHITE);
		btnUsuarios.setIcon(new ImageIcon(Inventario.class.getResource("/imagenes/UsuariosIcon.png")));
		btnUsuarios.setBounds(6, 6, 235, 123);
		VentanaInventario.getContentPane().add(btnUsuarios);
		
		JButton btnInventario = new JButton("  Inventario");
		btnInventario.setFocusPainted(false);
		btnInventario.setIcon(new ImageIcon(Inventario.class.getResource("/imagenes/InventarioIcon.png")));
		btnInventario.setForeground(new Color(255, 255, 255));
		btnInventario.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnInventario.setBackground(new Color(0, 128, 0));
		btnInventario.setBounds(248, 6, 235, 123);
		VentanaInventario.getContentPane().add(btnInventario);
		
		JButton btnVentas = new JButton("  Ventas");
		btnVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaInventario.dispose();
				Ventas.main(args);
			}
		});
		btnVentas.setFocusPainted(false);
		btnVentas.setIcon(new ImageIcon(Inventario.class.getResource("/imagenes/VentasIcon.png")));
		btnVentas.setForeground(new Color(0, 128, 0));
		btnVentas.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnVentas.setBackground(new Color(255, 255, 255));
		btnVentas.setBounds(495, 6, 235, 123);
		VentanaInventario.getContentPane().add(btnVentas);
		
		JButton btnOpciones = new JButton("  Opciones");
		btnOpciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaInventario.dispose();
				Opciones.main(args);
			}
		});
		btnOpciones.setFocusPainted(false);
		btnOpciones.setIcon(new ImageIcon(Inventario.class.getResource("/imagenes/OpcionesIcon.png")));
		btnOpciones.setForeground(new Color(0, 128, 0));
		btnOpciones.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnOpciones.setBackground(new Color(255, 255, 255));
		btnOpciones.setBounds(742, 6, 235, 123);
		VentanaInventario.getContentPane().add(btnOpciones);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(16, 141, 954, 10);
		VentanaInventario.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(16, 576, 954, 10);
		VentanaInventario.getContentPane().add(panel_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(26, 163, 940, 406);
		VentanaInventario.getContentPane().add(tabbedPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Agregar", new ImageIcon(Inventario.class.getResource("/imagenes/AddItem.png")), panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblAgregarNuevoRepuesto = new JLabel("AGREGAR NUEVO REPUESTO");
		lblAgregarNuevoRepuesto.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarNuevoRepuesto.setForeground(new Color(0, 128, 0));
		lblAgregarNuevoRepuesto.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAgregarNuevoRepuesto.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(34, 139, 34)));
		lblAgregarNuevoRepuesto.setBounds(23, 12, 290, 49);
		panel_2.add(lblAgregarNuevoRepuesto);
		
		JLabel label_1 = new JLabel("Nombre");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(new Color(0, 128, 0));
		label_1.setBounds(31, 86, 143, 27);
		panel_2.add(label_1);
		
		JTextArea txtADescripcion = new JTextArea();
		txtADescripcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					txtAPrecio.requestFocus();
				}
			}
		});
		
		txtANombre = new JTextField();
		txtANombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtANombre.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO NOMBRE ES OBLIGATORIO", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				txtADescripcion.requestFocus();
			}
		});
		txtANombre.setForeground(new Color(0, 128, 0));
		txtANombre.setFont(new Font("Dialog", Font.BOLD, 12));
		txtANombre.setColumns(10);
		txtANombre.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 0)));
		txtANombre.setBounds(181, 89, 154, 20);
		panel_2.add(txtANombre);
		
		JLabel label_2 = new JLabel("Descripci\u00F3n");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(new Color(0, 128, 0));
		label_2.setBounds(31, 171, 143, 27);
		panel_2.add(label_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(182, 140, 189, 67);
		panel_2.add(scrollPane);
		
		
		txtADescripcion.setLineWrap(true);
		scrollPane.setViewportView(txtADescripcion);
		txtADescripcion.setForeground(new Color(0, 128, 0));
		txtADescripcion.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 0)));
		
		JLabel lblPrecioUnitario_2 = new JLabel("Precio Unitario");
		lblPrecioUnitario_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioUnitario_2.setForeground(new Color(0, 128, 0));
		lblPrecioUnitario_2.setBounds(31, 245, 143, 27);
		panel_2.add(lblPrecioUnitario_2);
		
		JComboBox comboBoxBRepuesto = new JComboBox();
		JComboBox comboBoxERepuesto = new JComboBox();
		JLabel lblTotalBs = new JLabel("Total: 0.00 BsS.");
		
		txtAUnidades = new JTextField();
		txtAUnidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtANombre.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE INTRODUCIR UN NOMBRE PARA EL Repuesto", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtADescripcion.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE INTRODUCIR UNA DESCRIPCIÓN PARA EL Repuesto", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtAPrecio.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO PRECIO ES OBLIGATORIO", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtAPrecio.getText().matches("[0-9]+([,.][0-9]{1,2})?")){
					JOptionPane.showMessageDialog(null, "ERROR: SOLO NUMEROS EN EL CAMPO PRECIO", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Double Precio = Double.parseDouble(txtAPrecio.getText().replaceAll(",", "."));
				if(Precio <= 0.0){
					JOptionPane.showMessageDialog(null, "ERROR: EL PRECIO DEBE SER MAYOR A 0!", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtAUnidades.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE INTRODUCIR UNA CANTIDAD DE UNIDADES PARA EL Repuesto", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtAUnidades.getText().matches("[0-9]+")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO UNIDADES SOLO ACEPTA NUMEROS ENTEROS", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(Integer.parseInt(txtAUnidades.getText().trim()) <= 0){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO UNIDADES SOLO ACEPTA NUMEROS MAYORES A 0!", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				
				
				
				
				
				
				BD op = new BD();
				try {
					if(op.BuscarRepuesto(txtANombre.getText().toLowerCase()) == true){
						JOptionPane.showMessageDialog(null, "ERROR: ESTE Repuesto YA EXISTE DENTRO DEL INVENTARIO", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					op.CrearRepuesto(txtANombre.getText().toLowerCase(), txtADescripcion.getText().toLowerCase(), 
							Precio, Integer.parseInt(txtAUnidades.getText()));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				txtANombre.setText("");
				txtADescripcion.setText("");
				txtAUnidades.setText("");
				txtAPrecio.setText("");
				
				JOptionPane.showMessageDialog(null, "Repuesto REGISTRADO", "Electro Auto Carrillo", JOptionPane.INFORMATION_MESSAGE);
				
				txtANombre.requestFocus();
				
				DefaultTableModel dm = (DefaultTableModel) tablarepuestos.getModel();
				int rowCount = dm.getRowCount();
				//Remove rows one by one from the end of the table
				for (int i = rowCount - 1; i >= 0; i--) {
				    dm.removeRow(i);
				}
				
				Double contador = 0.0;
				
				
				
				 BD conexion = new BD();
				  Statement stmt = null;
				    conexion.Conectar("electro_auto");
				    try{
				    	DefaultTableModel model = (DefaultTableModel) tablarepuestos.getModel();
				    	String selectSQL = "SELECT Codigo, Nombre, Descripcion, Precio, IVA, Unidades, Total FROM Repuestos ORDER BY Codigo";
						  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
						  ResultSet rs = preparedStatement.executeQuery(selectSQL);
						  while (rs.next()) {
						  	
						  	
						  	
						  	
						  	model.addRow(new Object[]{rs.getInt("Codigo"), WordUtils.capitalizeFully(rs.getString("Nombre")), rs.getString("Descripcion"),
						  			rs.getDouble("Precio"), rs.getDouble("IVA"), rs.getInt("Unidades"), rs.getDouble("Total")});
						  	contador += rs.getDouble("Total");
						  }
						  DecimalFormat df = new DecimalFormat("#,###.##"); 
						  lblTotalBs.setText("Total: " + df.format(contador) + " BsS.");
					}
					catch(Exception x){
							  System.err.println(x);
						  }
				
				
				
			    conexion.Conectar("Electro_Auto");
			    try{
			    	
			    	String selectSQL = "SELECT Nombre FROM Repuestos";
					  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
					  ResultSet rs = preparedStatement.executeQuery(selectSQL);
					  comboBoxBRepuesto.setModel(new DefaultComboBoxModel(new String[] {"Buscar"}));
					  comboBoxERepuesto.setModel(new DefaultComboBoxModel(new String[] {"Buscar"}));
					 
					  while (rs.next()) {
						  comboBoxBRepuesto.addItem(WordUtils.capitalizeFully(rs.getString("Nombre")));
						  comboBoxERepuesto.addItem(WordUtils.capitalizeFully(rs.getString("Nombre")));
					  }
			    }
					  catch(Exception ayo){
						  System.err.println(ayo);
					  }
				
				
			}
		});
		
		 
		
		txtAPrecio = new JTextField();
		txtAPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtAPrecio.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO PRECIO ES OBLIGATORIO", "Dato obligatorio", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtAPrecio.getText().matches("[0-9]+([,.][0-9]{1,2})?")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO PRECIO SOLO ACEPTA NUMEROS", "Error de validación", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Double Precio = Double.parseDouble(txtAPrecio.getText().replaceAll(",", "."));
				if(Precio <= 0.0){
					JOptionPane.showMessageDialog(null, "ERROR: EL PRECIO DEBE SER MAYOR A 0!", "Error de validación", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				txtAUnidades.requestFocus();
			}
		});
		txtAPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		txtAPrecio.setForeground(new Color(0, 128, 0));
		txtAPrecio.setFont(new Font("Dialog", Font.BOLD, 12));
		txtAPrecio.setColumns(10);
		txtAPrecio.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 0)));
		txtAPrecio.setBounds(219, 248, 105, 20);
		panel_2.add(txtAPrecio);
		
		JLabel label_4 = new JLabel("Unidades");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(new Color(0, 128, 0));
		label_4.setBounds(31, 317, 143, 27);
		panel_2.add(label_4);
		
		
		txtAUnidades.setHorizontalAlignment(SwingConstants.CENTER);
		txtAUnidades.setForeground(new Color(0, 128, 0));
		txtAUnidades.setFont(new Font("Dialog", Font.BOLD, 12));
		txtAUnidades.setColumns(10);
		txtAUnidades.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 0)));
		txtAUnidades.setBounds(219, 320, 105, 20);
		panel_2.add(txtAUnidades);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setFocusPainted(false);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtANombre.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE INTRODUCIR UN NOMBRE PARA EL Repuesto", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtADescripcion.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE INTRODUCIR UNA DESCRIPCIÓN PARA EL Repuesto", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtAPrecio.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO PRECIO ES OBLIGATORIO", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtAPrecio.getText().matches("[0-9]+([,.][0-9]{1,2})?")){
					JOptionPane.showMessageDialog(null, "ERROR: SOLO NUMEROS EN EL CAMPO PRECIO", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Double Precio = Double.parseDouble(txtAPrecio.getText().replaceAll(",", "."));
				if(Precio <= 0.0){
					JOptionPane.showMessageDialog(null, "ERROR: EL PRECIO DEBE SER MAYOR A 0!", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtAUnidades.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE INTRODUCIR UNA CANTIDAD DE UNIDADES PARA EL Repuesto", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtAUnidades.getText().matches("[0-9]+")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO UNIDADES SOLO ACEPTA NUMEROS ENTEROS", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(Integer.parseInt(txtAUnidades.getText().trim()) <= 0){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO UNIDADES SOLO ACEPTA NUMEROS MAYORES A 0!", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				BD op = new BD();
				try {
					if(op.BuscarRepuesto(txtANombre.getText().toLowerCase()) == true){
						JOptionPane.showMessageDialog(null, "ERROR: ESTE Repuesto YA EXISTE DENTRO DEL INVENTARIO", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					op.CrearRepuesto(txtANombre.getText().toLowerCase(), txtADescripcion.getText().toLowerCase(), 
							Precio, Integer.parseInt(txtAUnidades.getText()));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				txtANombre.setText("");
				txtADescripcion.setText("");
				txtAUnidades.setText("");
				txtAPrecio.setText("");
				
				JOptionPane.showMessageDialog(null, "Repuesto REGISTRADO", "Electro Auto Carrillo", JOptionPane.INFORMATION_MESSAGE);
				
				txtANombre.requestFocus();
				
				DefaultTableModel dm = (DefaultTableModel) tablarepuestos.getModel();
				int rowCount = dm.getRowCount();
				//Remove rows one by one from the end of the table
				for (int i = rowCount - 1; i >= 0; i--) {
				    dm.removeRow(i);
				}
				
				Double contador = 0.0;
				
				
				
				 BD conexion = new BD();
				  Statement stmt = null;
				    conexion.Conectar("electro_auto");
				    try{
				    	DefaultTableModel model = (DefaultTableModel) tablarepuestos.getModel();
				    	String selectSQL = "SELECT Codigo, Nombre, Descripcion, Precio, IVA, Unidades, Total FROM Repuestos ORDER BY Codigo";
						  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
						  ResultSet rs = preparedStatement.executeQuery(selectSQL);
						  while (rs.next()) {
						  	
						  	
						  	
						  	
						  	model.addRow(new Object[]{rs.getInt("Codigo"), WordUtils.capitalizeFully(rs.getString("Nombre")), rs.getString("Descripcion"),
						  			rs.getDouble("Precio"), rs.getDouble("IVA"), rs.getInt("Unidades"), rs.getDouble("Total")});
						  	contador += rs.getDouble("Total");
						  }
						  DecimalFormat df = new DecimalFormat("#,###.##"); 
						  lblTotalBs.setText("Total: " + df.format(contador) + " BsS.");
					}
					catch(Exception x){
							  System.err.println(x);
						  }
				
				
				
			    conexion.Conectar("Electro_Auto");
			    try{
			    	
			    	String selectSQL = "SELECT Nombre FROM Repuestos";
					  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
					  ResultSet rs = preparedStatement.executeQuery(selectSQL);
					  comboBoxBRepuesto.setModel(new DefaultComboBoxModel(new String[] {"Buscar"}));
					  comboBoxERepuesto.setModel(new DefaultComboBoxModel(new String[] {"Buscar"}));
					 
					  while (rs.next()) {
						  comboBoxBRepuesto.addItem(WordUtils.capitalizeFully(rs.getString("Nombre")));
						  comboBoxERepuesto.addItem(WordUtils.capitalizeFully(rs.getString("Nombre")));
					  }
			    }
					  catch(Exception ayo){
						  System.err.println(ayo);
					  }
				
				
			}
		});
		btnAgregar.setBackground(new Color(0, 128, 0));
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.setBounds(715, 12, 98, 26);
		panel_2.add(btnAgregar);
		
		JButton btnAResetear = new JButton("Resetear");
		btnAResetear.setFocusPainted(false);
		btnAResetear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtANombre.setText("");
				txtADescripcion.setText("");
				txtAPrecio.setText("");
				txtAUnidades.setText("");
			}
		});
		btnAResetear.setBackground(new Color(0, 128, 0));
		btnAResetear.setForeground(new Color(255, 255, 255));
		btnAResetear.setBounds(825, 12, 98, 26);
		panel_2.add(btnAResetear);
		tabbedPane.setForegroundAt(0, new Color(0, 100, 0));
		tabbedPane.setBackgroundAt(0, new Color(255, 255, 255));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		tabbedPane.addTab("Modificar", new ImageIcon(Inventario.class.getResource("/imagenes/UpdateItem.png")), panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblModificarRepuestoExistente = new JLabel("MODIFICAR REPUESTO EXISTENTE");
		lblModificarRepuestoExistente.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificarRepuestoExistente.setForeground(new Color(0, 128, 0));
		lblModificarRepuestoExistente.setFont(new Font("Dialog", Font.BOLD, 18));
		lblModificarRepuestoExistente.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(34, 139, 34)));
		lblModificarRepuestoExistente.setBounds(12, 12, 325, 49);
		panel_3.add(lblModificarRepuestoExistente);
		
		JTextArea txtMDescripcion = new JTextArea();
		
		JLabel label_5 = new JLabel("Nombre");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(new Color(0, 128, 0));
		label_5.setBounds(300, 85, 143, 27);
		panel_3.add(label_5);
		
		txtMNombre = new JTextField();
		txtMNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtMNombre.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO NOMBRE ES OBLIGATORIO", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				txtMDescripcion.requestFocus();
			}
		});
		txtMNombre.setForeground(new Color(0, 128, 0));
		txtMNombre.setFont(new Font("Dialog", Font.BOLD, 12));
		txtMNombre.setColumns(10);
		txtMNombre.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 0)));
		txtMNombre.setBounds(450, 88, 154, 20);
		panel_3.add(txtMNombre);
		
		JLabel label_6 = new JLabel("Descripci\u00F3n");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(new Color(0, 128, 0));
		label_6.setBounds(300, 170, 143, 27);
		panel_3.add(label_6);
		
		
		txtMPrecio = new JTextField();
		
		txtMDescripcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					txtMPrecio.requestFocus();
				}
			}
		});
		txtMDescripcion.setLineWrap(true);
		txtMDescripcion.setForeground(new Color(0, 128, 0));
		txtMDescripcion.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 0)));
		txtMDescripcion.setBounds(452, 140, 186, 64);
		panel_3.add(txtMDescripcion);
		
		JLabel lblPrecioUnitario_1 = new JLabel("Precio Unitario");
		lblPrecioUnitario_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioUnitario_1.setForeground(new Color(0, 128, 0));
		lblPrecioUnitario_1.setBounds(300, 244, 143, 27);
		panel_3.add(lblPrecioUnitario_1);
		
		txtMUnidades = new JTextField();
		
		
		txtMPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtMPrecio.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO PRECIO ES OBLIGATORIO", "Dato obligatorio", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtMPrecio.getText().matches("[0-9]+([,.][0-9]{1,2})?")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO PRECIO SOLO ACEPTA NUMEROS", "Error de validación", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Double Precio = Double.parseDouble(txtMPrecio.getText().replaceAll(",", "."));
				if(Precio <= 0.0){
					JOptionPane.showMessageDialog(null, "ERROR: EL PRECIO DEBE SER MAYOR A 0!", "Error de validación", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				txtMUnidades.requestFocus();
			}
		});
		txtMPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		txtMPrecio.setForeground(new Color(0, 128, 0));
		txtMPrecio.setFont(new Font("Dialog", Font.BOLD, 12));
		txtMPrecio.setColumns(10);
		txtMPrecio.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 0)));
		txtMPrecio.setBounds(488, 247, 105, 20);
		panel_3.add(txtMPrecio);
		
		JLabel label_8 = new JLabel("Unidades");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setForeground(new Color(0, 128, 0));
		label_8.setBounds(300, 316, 143, 27);
		panel_3.add(label_8);
		
		
		txtMUnidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(comboBoxBRepuesto.getSelectedItem().toString().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE ELEGIR UN Repuesto", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(txtMNombre.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE INTRODUCIR UN NOMBRE PARA EL Repuesto", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtMDescripcion.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE INTRODUCIR UNA DESCRIPCIÓN PARA EL Repuesto", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtMPrecio.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO PRECIO ES OBLIGATORIO", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtMPrecio.getText().matches("[0-9]+([,.][0-9]{1,2})?")){
					JOptionPane.showMessageDialog(null, "ERROR: SOLO NUMEROS EN EL CAMPO PRECIO", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Double Precio = Double.parseDouble(txtMPrecio.getText().replaceAll(",", "."));
				if(Precio <= 0.0){
					JOptionPane.showMessageDialog(null, "ERROR: EL PRECIO DEBE SER MAYOR A 0!", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtMUnidades.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE INTRODUCIR UNA CANTIDAD DE UNIDADES PARA EL Repuesto", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtMUnidades.getText().matches("[0-9]+")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO UNIDADES SOLO ACEPTA NUMEROS ENTEROS", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(Integer.parseInt(txtMUnidades.getText().trim()) <= 0){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO UNIDADES SOLO ACEPTA NUMEROS MAYORES A 0!", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				
				BD op = new BD();
				try {
					if(op.BuscarRepuesto(comboBoxBRepuesto.getSelectedItem().toString()) == true && !comboBoxBRepuesto.getSelectedItem().toString().equalsIgnoreCase(txtMNombre.getText())){
						JOptionPane.showMessageDialog(null, "ERROR: ESTE Repuesto YA EXISTE DENTRO DEL INVENTARIO!", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					op.ActualizarRepuesto(comboBoxBRepuesto.getSelectedItem().toString(), txtMNombre.getText(), 
							txtMDescripcion.getText(), Precio, Integer.parseInt(txtMUnidades.getText()));
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				JOptionPane.showMessageDialog(null, "Repuesto ACTUALIZADO", "Electro Auto Carrillo", JOptionPane.INFORMATION_MESSAGE);
				
				txtMNombre.setText("");
		  		txtMDescripcion.setText("");
		  		txtMPrecio.setText("");
		  		txtMUnidades.setText("");
		  		
		  		DefaultTableModel dm = (DefaultTableModel) tablarepuestos.getModel();
				int rowCount = dm.getRowCount();
				//Remove rows one by one from the end of the table
				for (int i = rowCount - 1; i >= 0; i--) {
				    dm.removeRow(i);
				}
				
				Double contador = 0.0;
				
				
				
				 BD conexion = new BD();
				  Statement stmt = null;
				    conexion.Conectar("electro_auto");
				    try{
				    	DefaultTableModel model = (DefaultTableModel) tablarepuestos.getModel();
				    	String selectSQL = "SELECT Codigo, Nombre, Descripcion, Precio, IVA, Unidades, Total FROM Repuestos ORDER BY Codigo";
						  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
						  ResultSet rs = preparedStatement.executeQuery(selectSQL);
						  while (rs.next()) {
						  	
						  	
						  	
						  	
						  	model.addRow(new Object[]{rs.getInt("Codigo"), WordUtils.capitalizeFully(rs.getString("Nombre")), rs.getString("Descripcion"),
						  			rs.getDouble("Precio"), rs.getDouble("IVA"), rs.getInt("Unidades"), rs.getDouble("Total")});
						  	contador += rs.getDouble("Total");
						  }
						  DecimalFormat df = new DecimalFormat("#,###.##"); 
						  lblTotalBs.setText("Total: " + df.format(contador) + " BsS.");
					}
					catch(Exception x){
							  System.err.println(x);
						  }
				
				
				
			    conexion.Conectar("Electro_Auto");
			    try{
			    	
			    	String selectSQL = "SELECT Nombre FROM Repuestos";
					  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
					  ResultSet rs = preparedStatement.executeQuery(selectSQL);
					  comboBoxBRepuesto.setModel(new DefaultComboBoxModel(new String[] {"Buscar"}));
					  comboBoxERepuesto.setModel(new DefaultComboBoxModel(new String[] {"Buscar"}));
					 
					  while (rs.next()) {
						  comboBoxBRepuesto.addItem(WordUtils.capitalizeFully(rs.getString("Nombre")));
						  comboBoxERepuesto.addItem(WordUtils.capitalizeFully(rs.getString("Nombre")));
					  }
			    }
					  catch(Exception ayo){
						  System.err.println(ayo);
					  }
				
			}
		});
		txtMUnidades.setHorizontalAlignment(SwingConstants.CENTER);
		txtMUnidades.setForeground(new Color(0, 128, 0));
		txtMUnidades.setFont(new Font("Dialog", Font.BOLD, 12));
		txtMUnidades.setColumns(10);
		txtMUnidades.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 0)));
		txtMUnidades.setBounds(488, 319, 105, 20);
		panel_3.add(txtMUnidades);
		
		JLabel lblBuscarRepuesto = new JLabel("Buscar Repuesto");
		lblBuscarRepuesto.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarRepuesto.setForeground(new Color(0, 128, 0));
		lblBuscarRepuesto.setBounds(83, 141, 143, 27);
		panel_3.add(lblBuscarRepuesto);
		
		
		comboBoxBRepuesto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				BD op = new BD();
				op.Conectar("Electro_Auto");
				
				if(!comboBoxBRepuesto.getSelectedItem().toString().equalsIgnoreCase("buscar")){
					try{
						  String selectSQL = "SELECT Nombre, Descripcion, Precio, Unidades FROM Repuestos WHERE Nombre = ?";
						  PreparedStatement preparedStatement = op.conn.prepareStatement(selectSQL);
						  preparedStatement.setString(1, comboBoxBRepuesto.getSelectedItem().toString().toLowerCase());
						 
						  ResultSet rs = preparedStatement.executeQuery();
						  while (rs.next()) {
						  	String Nombre = rs.getString("Nombre");
						  	String Descripcion = rs.getString("Descripcion");
						  	String Price = rs.getString("Precio");
						  	String Unidades = rs.getString("Unidades");
						  	txtMNombre.setText(WordUtils.capitalizeFully(Nombre));
						  	txtMDescripcion.setText(Descripcion);
						  	txtMPrecio.setText(Price);
						  	txtMUnidades.setText(Unidades);
						  }
						 
						  
					}
					catch(Exception ayy){
							  System.err.println(ayy);
						  }
				}
				
				
			}
		});
		comboBoxBRepuesto.setModel(new DefaultComboBoxModel(new String[] {"Buscar"}));
		comboBoxBRepuesto.setFont(new Font("Dialog", Font.BOLD, 12));
		comboBoxBRepuesto.setForeground(new Color(255, 255, 255));
		comboBoxBRepuesto.setBackground(new Color(0, 128, 0));
		comboBoxBRepuesto.setBounds(61, 171, 204, 26);
		panel_3.add(comboBoxBRepuesto);
		tabbedPane.setForegroundAt(1, new Color(0, 128, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		tabbedPane.addTab("Eliminar", new ImageIcon(Inventario.class.getResource("/imagenes/DeleteItem.png")), panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblEliminarRepuestoExistente = new JLabel("ELIMINAR REPUESTO EXISTENTE");
		lblEliminarRepuestoExistente.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminarRepuestoExistente.setForeground(new Color(0, 128, 0));
		lblEliminarRepuestoExistente.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEliminarRepuestoExistente.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(34, 139, 34)));
		lblEliminarRepuestoExistente.setBounds(12, 12, 325, 49);
		panel_4.add(lblEliminarRepuestoExistente);
		
		JLabel label_9 = new JLabel("Buscar Repuesto");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setForeground(new Color(0, 128, 0));
		label_9.setBounds(91, 153, 143, 27);
		panel_4.add(label_9);
		
		
		
		JLabel label_10 = new JLabel("Nombre");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setForeground(new Color(0, 128, 0));
		label_10.setBounds(417, 66, 143, 27);
		panel_4.add(label_10);
		
		JLabel lblPrecioUnitario = new JLabel("Precio Unitario");
		lblPrecioUnitario.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioUnitario.setForeground(new Color(0, 128, 0));
		lblPrecioUnitario.setBounds(417, 153, 143, 27);
		panel_4.add(lblPrecioUnitario);
		
		JLabel label_13 = new JLabel("Unidades");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setForeground(new Color(0, 128, 0));
		label_13.setBounds(417, 225, 143, 27);
		panel_4.add(label_13);
		
		JLabel txtENombre = new JLabel("");
		txtENombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtENombre.setForeground(new Color(105, 105, 105));
		txtENombre.setBounds(417, 107, 143, 27);
		panel_4.add(txtENombre);
		
		JLabel txtEPrecio = new JLabel("");
		txtEPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		txtEPrecio.setForeground(new Color(105, 105, 105));
		txtEPrecio.setBounds(417, 183, 143, 27);
		panel_4.add(txtEPrecio);
		
		JLabel txtEUnidades = new JLabel("");
		txtEUnidades.setHorizontalAlignment(SwingConstants.CENTER);
		txtEUnidades.setForeground(new Color(105, 105, 105));
		txtEUnidades.setBounds(417, 257, 143, 27);
		panel_4.add(txtEUnidades);
		
		JLabel lblIva = new JLabel("IVA ( 16% )");
		lblIva.setHorizontalAlignment(SwingConstants.CENTER);
		lblIva.setForeground(new Color(0, 128, 0));
		lblIva.setBounds(635, 121, 143, 27);
		panel_4.add(lblIva);
		
		JLabel txtEIVA = new JLabel("");
		txtEIVA.setHorizontalAlignment(SwingConstants.CENTER);
		txtEIVA.setForeground(new Color(105, 105, 105));
		txtEIVA.setBounds(635, 153, 143, 27);
		panel_4.add(txtEIVA);
		
		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setForeground(new Color(0, 128, 0));
		lblTotal.setBounds(635, 192, 143, 27);
		panel_4.add(lblTotal);
		
		JLabel txtETotal = new JLabel("");
		txtETotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtETotal.setForeground(new Color(105, 105, 105));
		txtETotal.setBounds(635, 224, 143, 27);
		panel_4.add(txtETotal);
		tabbedPane.setForegroundAt(2, new Color(0, 128, 0));
		
		
		comboBoxERepuesto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BD op = new BD();
				op.Conectar("Electro_Auto");
				
				if(!comboBoxERepuesto.getSelectedItem().toString().equalsIgnoreCase("buscar")){
					try{
						  String selectSQL = "SELECT Nombre, IVA, Precio, Unidades, Total FROM Repuestos WHERE Nombre = ?";
						  PreparedStatement preparedStatement = op.conn.prepareStatement(selectSQL);
						  preparedStatement.setString(1, comboBoxERepuesto.getSelectedItem().toString().toLowerCase());
						 
						  ResultSet rs = preparedStatement.executeQuery();
						  while (rs.next()) {
						  	String Nombre = rs.getString("Nombre");
						  
						  	String Price = rs.getString("Precio");
						  	String IVA = rs.getString("IVA");
						  	String Unidades = rs.getString("Unidades");
						  	String Total = rs.getString("Total");
						  	txtENombre.setText(WordUtils.capitalizeFully(Nombre));
						  	txtEPrecio.setText(Price + " BsS.");
						  	txtEUnidades.setText(Unidades);
						  	txtEIVA.setText(IVA + " BsS.");
						  	txtETotal.setText(Total + " BsS.");
						  }
						 
						  
					}
					catch(Exception ayy){
							  System.err.println(ayy);
						  }
				}
				
			}
		});
		comboBoxERepuesto.setForeground(Color.WHITE);
		comboBoxERepuesto.setFont(new Font("Dialog", Font.BOLD, 12));
		comboBoxERepuesto.setBackground(new Color(0, 128, 0));
		comboBoxERepuesto.setBounds(69, 183, 204, 26);
		panel_4.add(comboBoxERepuesto);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFocusPainted(false);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboBoxBRepuesto.getSelectedItem().toString().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE ELEGIR UN Repuesto", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(txtMNombre.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE INTRODUCIR UN NOMBRE PARA EL Repuesto", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtMDescripcion.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE INTRODUCIR UNA DESCRIPCIÓN PARA EL Repuesto", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtMPrecio.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO PRECIO ES OBLIGATORIO", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtMPrecio.getText().matches("[0-9]+([,.][0-9]{1,2})?")){
					JOptionPane.showMessageDialog(null, "ERROR: SOLO NUMEROS EN EL CAMPO PRECIO", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Double Precio = Double.parseDouble(txtMPrecio.getText().replaceAll(",", "."));
				if(Precio <= 0.0){
					JOptionPane.showMessageDialog(null, "ERROR: EL PRECIO DEBE SER MAYOR A 0!", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtMUnidades.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE INTRODUCIR UNA CANTIDAD DE UNIDADES PARA EL Repuesto", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtMUnidades.getText().matches("[0-9]+")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO UNIDADES SOLO ACEPTA NUMEROS ENTEROS", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(Integer.parseInt(txtMUnidades.getText().trim()) <= 0){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO UNIDADES SOLO ACEPTA NUMEROS MAYORES A 0!", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				BD op = new BD();
				try {
					if(op.BuscarRepuesto(comboBoxBRepuesto.getSelectedItem().toString()) == true && !comboBoxBRepuesto.getSelectedItem().toString().equalsIgnoreCase(txtMNombre.getText())){
						JOptionPane.showMessageDialog(null, "ERROR: ESTE Repuesto YA EXISTE DENTRO DEL INVENTARIO!", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					op.ActualizarRepuesto(comboBoxBRepuesto.getSelectedItem().toString(), txtMNombre.getText(), 
							txtMDescripcion.getText(), Precio, Integer.parseInt(txtMUnidades.getText()));
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				JOptionPane.showMessageDialog(null, "Repuesto ACTUALIZADO", "Electro Auto Carrillo", JOptionPane.INFORMATION_MESSAGE);
				
				txtMNombre.setText("");
		  		txtMDescripcion.setText("");
		  		txtMPrecio.setText("");
		  		txtMUnidades.setText("");
		  		
		  		DefaultTableModel dm = (DefaultTableModel) tablarepuestos.getModel();
				int rowCount = dm.getRowCount();
				//Remove rows one by one from the end of the table
				for (int i = rowCount - 1; i >= 0; i--) {
				    dm.removeRow(i);
				}
				
				Double contador = 0.0;
				
				
				
				 BD conexion = new BD();
				  Statement stmt = null;
				    conexion.Conectar("electro_auto");
				    try{
				    	DefaultTableModel model = (DefaultTableModel) tablarepuestos.getModel();
				    	String selectSQL = "SELECT Codigo, Nombre, Descripcion, Precio, IVA, Unidades, Total FROM Repuestos ORDER BY Codigo";
						  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
						  ResultSet rs = preparedStatement.executeQuery(selectSQL);
						  while (rs.next()) {
						  	
						  	
						  	
						  	
						  	model.addRow(new Object[]{rs.getInt("Codigo"), WordUtils.capitalizeFully(rs.getString("Nombre")), rs.getString("Descripcion"),
						  			rs.getDouble("Precio"), rs.getDouble("IVA"), rs.getInt("Unidades"), rs.getDouble("Total")});
						  	contador += rs.getDouble("Total");
						  }
						  DecimalFormat df = new DecimalFormat("#,###.##"); 
						  lblTotalBs.setText("Total: " + df.format(contador) + " BsS.");
					}
					catch(Exception x){
							  System.err.println(x);
						  }
				
				
				
			    conexion.Conectar("Electro_Auto");
			    try{
			    	
			    	String selectSQL = "SELECT Nombre FROM Repuestos";
					  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
					  ResultSet rs = preparedStatement.executeQuery(selectSQL);
					  comboBoxBRepuesto.setModel(new DefaultComboBoxModel(new String[] {"Buscar"}));
					  comboBoxERepuesto.setModel(new DefaultComboBoxModel(new String[] {"Buscar"}));
					 
					  while (rs.next()) {
						  comboBoxBRepuesto.addItem(WordUtils.capitalizeFully(rs.getString("Nombre")));
						  comboBoxERepuesto.addItem(WordUtils.capitalizeFully(rs.getString("Nombre")));
					  }
			    }
					  catch(Exception ayo){
						  System.err.println(ayo);
					  }
				
				
			}
		});
		  btnModificar.setForeground(Color.WHITE);
		  btnModificar.setBackground(new Color(0, 128, 0));
		  btnModificar.setBounds(715, 12, 98, 26);
		  panel_3.add(btnModificar);
		  
		  JButton btnMResetear = new JButton("Resetear");
		  btnMResetear.setFocusPainted(false);
		  btnMResetear.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		txtMNombre.setText("");
		  		txtMDescripcion.setText("");
		  		txtMPrecio.setText("");
		  		txtMUnidades.setText("");
		  		
		  		BD conexion = new BD();
			    Statement stmt = null;
			    conexion.Conectar("Electro_Auto");
			    try{
			    	
			    	String selectSQL = "SELECT Nombre FROM Repuestos";
					  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
					  ResultSet rs = preparedStatement.executeQuery(selectSQL);
					  comboBoxBRepuesto.setModel(new DefaultComboBoxModel(new String[] {"Buscar"}));
					  
					 
					  while (rs.next()) {
						  comboBoxBRepuesto.addItem(WordUtils.capitalizeFully(rs.getString("Nombre")));
					  }
			    }
					  catch(Exception ayo){
						  System.err.println(ayo);
					  }
		  		
		  		
		  	}
		  });
		  btnMResetear.setForeground(Color.WHITE);
		  btnMResetear.setBackground(new Color(0, 128, 0));
		  btnMResetear.setBounds(825, 12, 98, 26);
		  panel_3.add(btnMResetear);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		tabbedPane.addTab("Visualizar", new ImageIcon(Inventario.class.getResource("/imagenes/ViewItem.png")), panel_5, null);
		panel_5.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 255, 255));
		panel_6.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 100, 0)));
		panel_6.setBounds(12, 12, 911, 42);
		panel_5.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ordenar por");
		lblNewLabel.setForeground(new Color(0, 128, 0));
		lblNewLabel.setBounds(12, 12, 81, 16);
		panel_6.add(lblNewLabel);
		
		JButton btnReporte = new JButton("Generar reporte");
		btnReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 Document document = new Document();
				    // step 2
				    try {
						PdfWriter.getInstance(document, new FileOutputStream("C://reporte_inventario.pdf"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    // step 3
				    document.open();
				    // step 4
				    
				    LocalDateTime ldt = LocalDateTime.now();
				    
				    Double contadortotal = 0.0;
				    
				    Paragraph parrafo2 = new Paragraph("Electro Auto Carrillo FP");
				    Paragraph parrafo3 = new Paragraph("Llevando la satisfacción al sector automotriz\n\n");
				    
				    Paragraph paragraph = new Paragraph("Inventario de Repuestos - " + DateTimeFormatter.ofPattern("dd-MM-yyyy - hh:mm a", Locale.ENGLISH).format(ldt) + "\n\n");
				    paragraph.setAlignment(1);
				   
				    parrafo2.setAlignment(1);
				    parrafo3.setAlignment(1);
				    
				    try {
				    	
				    	document.add(parrafo2);
				    	document.add(parrafo3);
						document.add(paragraph);
					} catch (DocumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    

				PdfPTable tabla = new PdfPTable(7);
				
				com.itextpdf.text.Font f1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);
				f1.setColor(BaseColor.WHITE);
				
				com.itextpdf.text.Font f2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);
				f2.setColor(BaseColor.BLACK);
				
				PdfPCell cell = new PdfPCell(new Phrase("ID", f1));
				cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				BaseColor myColor = WebColors.getRGBColor("#008000");
				BaseColor blanco = WebColors.getRGBColor("#FFFFFF");
				cell.setBorder(Rectangle.BOTTOM);
				cell.setBackgroundColor(myColor);
				
				
				
				tabla.addCell(cell);
				cell = new PdfPCell(new Phrase("Nombre", f1));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				myColor = WebColors.getRGBColor("#008000");
				cell.setBorder(Rectangle.BOTTOM);
				cell.setBackgroundColor(myColor);
				
				tabla.addCell(cell);
				cell = new PdfPCell(new Phrase("Descripción", f1));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				myColor = WebColors.getRGBColor("#008000");
				cell.setBorder(Rectangle.BOTTOM);
				cell.setBackgroundColor(myColor);
				
				tabla.addCell(cell);
				cell = new PdfPCell(new Phrase("P.U", f1));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				myColor = WebColors.getRGBColor("#008000");
				cell.setBorder(Rectangle.BOTTOM);
				cell.setBackgroundColor(myColor);
				
				tabla.addCell(cell);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				
				cell = new PdfPCell(new Phrase("IVA", f1));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				myColor = WebColors.getRGBColor("#008000");
				cell.setBorder(Rectangle.BOTTOM);
				cell.setBackgroundColor(myColor);
				tabla.addCell(cell);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				
				cell = new PdfPCell(new Phrase("Unidades", f1));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				myColor = WebColors.getRGBColor("#008000");
				cell.setBorder(Rectangle.BOTTOM);
				cell.setBackgroundColor(myColor);
				tabla.addCell(cell);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				
				cell = new PdfPCell(new Phrase("Total", f1));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				myColor = WebColors.getRGBColor("#008000");
				cell.setBorder(Rectangle.BOTTOM);
				cell.setBackgroundColor(myColor);
				tabla.addCell(cell);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				
				
				
				
				
				
				
				   for(int aw=0;aw<tablarepuestos.getRowCount() ; aw++){
					   cell = new PdfPCell(new Phrase(tablarepuestos.getValueAt(aw, 0).toString(), f2));
					   cell.setBorder(Rectangle.BOTTOM);
					   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					   tabla.addCell(cell);
					   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					   cell = new PdfPCell(new Phrase(tablarepuestos.getValueAt(aw, 1).toString(), f2));
					   cell.setBorder(Rectangle.BOTTOM);
					   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					   tabla.addCell(cell);
					   cell = new PdfPCell(new Phrase(tablarepuestos.getValueAt(aw, 2).toString(), f2));
					   cell.setBorder(Rectangle.BOTTOM);
					   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					   tabla.addCell(cell);
					   DecimalFormat format = new DecimalFormat("0.##");
					   cell = new PdfPCell(new Phrase(format.format(Double.parseDouble(tablarepuestos.getValueAt(aw, 3).toString())), f2));
					   cell.setBorder(Rectangle.BOTTOM);
					   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					   tabla.addCell(cell);
					   cell = new PdfPCell(new Phrase(tablarepuestos.getValueAt(aw, 4).toString(), f2));
					   cell.setBorder(Rectangle.BOTTOM);
					   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					   tabla.addCell(cell);
					   cell = new PdfPCell(new Phrase(tablarepuestos.getValueAt(aw, 5).toString(), f2));
					   cell.setBorder(Rectangle.BOTTOM);
					   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					   tabla.addCell(cell);
					   cell = new PdfPCell(new Phrase(tablarepuestos.getValueAt(aw, 6).toString(), f2));
					   cell.setBorder(Rectangle.BOTTOM);
					   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					   tabla.addCell(cell);
					   
					   
					   contadortotal += Double.parseDouble(tablarepuestos.getValueAt(aw, 6).toString());
				    }

				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				   
				   DecimalFormat formato = new DecimalFormat("#,###.##");
				   double IVA = contadortotal * 0.16;
				   double Sub = contadortotal - IVA;
				   
				   Paragraph parrafo4 = new Paragraph("\n\n\nSub-TTL: " + formato.format(Sub) + " BsS.", f2);
				   Paragraph parrafo5 = new Paragraph("I.V.A (16%): " + formato.format(IVA) + " BsS.", f2);
				   Paragraph parrafo6 = new Paragraph("Total: " + formato.format(contadortotal) + " BsS.", f2);
				   
				   parrafo4.setAlignment(2);
				   parrafo4.setIndentationRight(50);
				   parrafo5.setAlignment(2);
				   parrafo5.setIndentationRight(50);
				   parrafo6.setAlignment(2);
				   parrafo6.setIndentationRight(50);

				    // Step 5
				    try {
						document.add(tabla);
						document.add(parrafo4);
						document.add(parrafo5);
						document.add(parrafo6);
						Desktop.getDesktop().open(new File("C://reporte_inventario.pdf"));
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				    // step 6
				    document.close();
				
			}
		});
		btnReporte.setFocusPainted(false);
		btnReporte.setBackground(new Color(0, 128, 0));
		btnReporte.setForeground(new Color(255, 255, 255));
		btnReporte.setBounds(760, 9, 139, 23);
		panel_6.add(btnReporte);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().toString().equalsIgnoreCase("Código")){
					
					
					DefaultTableModel dm = (DefaultTableModel) tablarepuestos.getModel();
					int rowCount = dm.getRowCount();
					//Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
					    dm.removeRow(i);
					}
					
					Double contador = 0.0;
					
					
					
					 BD conexion = new BD();
					  Statement stmt = null;
					    conexion.Conectar("electro_auto");
					    try{
					    	DefaultTableModel model = (DefaultTableModel) tablarepuestos.getModel();
					    	String selectSQL = "SELECT Codigo, Nombre, Descripcion, Precio, IVA, Unidades, Total FROM Repuestos ORDER BY Codigo";
							  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
							  ResultSet rs = preparedStatement.executeQuery(selectSQL);
							  while (rs.next()) {
							  	
							  	
							  	
							  	
							  	model.addRow(new Object[]{rs.getInt("Codigo"), WordUtils.capitalizeFully(rs.getString("Nombre")), rs.getString("Descripcion"),
							  			rs.getDouble("Precio"), rs.getDouble("IVA"), rs.getInt("Unidades"), rs.getDouble("Total")});
							  	contador += rs.getDouble("Total");
							  }
							  DecimalFormat df = new DecimalFormat("#,###.##"); 
							  
						}
						catch(Exception ay){
								  System.err.println(ay);
							  }
					
				}
				if(comboBox.getSelectedItem().toString().equalsIgnoreCase("Nombre")){
					DefaultTableModel dm = (DefaultTableModel) tablarepuestos.getModel();
					int rowCount = dm.getRowCount();
					//Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
					    dm.removeRow(i);
					}
					
					Double contador = 0.0;
					
					
					
					 BD conexion = new BD();
					  Statement stmt = null;
					    conexion.Conectar("electro_auto");
					    try{
					    	DefaultTableModel model = (DefaultTableModel) tablarepuestos.getModel();
					    	String selectSQL = "SELECT Codigo, Nombre, Descripcion, Precio, IVA, Unidades, Total FROM Repuestos ORDER BY Nombre";
							  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
							  ResultSet rs = preparedStatement.executeQuery(selectSQL);
							  while (rs.next()) {
							  	
							  	
							  	
							  	
							  	model.addRow(new Object[]{rs.getInt("Codigo"), WordUtils.capitalizeFully(rs.getString("Nombre")), rs.getString("Descripcion"),
							  			rs.getDouble("Precio"), rs.getDouble("IVA"), rs.getInt("Unidades"), rs.getDouble("Total")});
							  	contador += rs.getDouble("Total");
							  }
							  DecimalFormat df = new DecimalFormat("#,###.##"); 
							  
						}
						catch(Exception ay){
								  System.err.println(ay);
							  }
				}
				if(comboBox.getSelectedItem().toString().equalsIgnoreCase("Descripción")){
					DefaultTableModel dm = (DefaultTableModel) tablarepuestos.getModel();
					int rowCount = dm.getRowCount();
					//Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
					    dm.removeRow(i);
					}
					
					Double contador = 0.0;
					
					
					
					 BD conexion = new BD();
					  Statement stmt = null;
					    conexion.Conectar("electro_auto");
					    try{
					    	DefaultTableModel model = (DefaultTableModel) tablarepuestos.getModel();
					    	String selectSQL = "SELECT Codigo, Nombre, Descripcion, Precio, IVA, Unidades, Total FROM Repuestos ORDER BY Descripcion";
							  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
							  ResultSet rs = preparedStatement.executeQuery(selectSQL);
							  while (rs.next()) {
							  	
							  	
							  	
							  	
							  	model.addRow(new Object[]{rs.getInt("Codigo"), WordUtils.capitalizeFully(rs.getString("Nombre")), rs.getString("Descripcion"),
							  			rs.getDouble("Precio"), rs.getDouble("IVA"), rs.getInt("Unidades"), rs.getDouble("Total")});
							  	contador += rs.getDouble("Total");
							  }
							  DecimalFormat df = new DecimalFormat("#,###.##"); 
							  
						}
						catch(Exception ay){
								  System.err.println(ay);
							  }
				}
				if(comboBox.getSelectedItem().toString().equalsIgnoreCase("Precio")){
					DefaultTableModel dm = (DefaultTableModel) tablarepuestos.getModel();
					int rowCount = dm.getRowCount();
					//Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
					    dm.removeRow(i);
					}
					
					Double contador = 0.0;
					
					
					
					 BD conexion = new BD();
					  Statement stmt = null;
					    conexion.Conectar("electro_auto");
					    try{
					    	DefaultTableModel model = (DefaultTableModel) tablarepuestos.getModel();
					    	String selectSQL = "SELECT Codigo, Nombre, Descripcion, Precio, IVA, Unidades, Total FROM Repuestos ORDER BY Precio DESC";
							  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
							  ResultSet rs = preparedStatement.executeQuery(selectSQL);
							  while (rs.next()) {
							  	
							  	
							  	
							  	
							  	model.addRow(new Object[]{rs.getInt("Codigo"), WordUtils.capitalizeFully(rs.getString("Nombre")), rs.getString("Descripcion"),
							  			rs.getDouble("Precio"), rs.getDouble("IVA"), rs.getInt("Unidades"), rs.getDouble("Total")});
							  	contador += rs.getDouble("Total");
							  }
							  DecimalFormat df = new DecimalFormat("#,###.##"); 
							  
						}
						catch(Exception ay){
								  System.err.println(ay);
							  }
					
				}
				if(comboBox.getSelectedItem().toString().equalsIgnoreCase("IVA")){
					DefaultTableModel dm = (DefaultTableModel) tablarepuestos.getModel();
					int rowCount = dm.getRowCount();
					//Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
					    dm.removeRow(i);
					}
					
					Double contador = 0.0;
					
					
					
					 BD conexion = new BD();
					  Statement stmt = null;
					    conexion.Conectar("electro_auto");
					    try{
					    	DefaultTableModel model = (DefaultTableModel) tablarepuestos.getModel();
					    	String selectSQL = "SELECT Codigo, Nombre, Descripcion, Precio, IVA, Unidades, Total FROM Repuestos ORDER BY IVA DESC";
							  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
							  ResultSet rs = preparedStatement.executeQuery(selectSQL);
							  while (rs.next()) {
							  	
							  	
							  	
							  	
							  	model.addRow(new Object[]{rs.getInt("Codigo"), WordUtils.capitalizeFully(rs.getString("Nombre")), rs.getString("Descripcion"),
							  			rs.getDouble("Precio"), rs.getDouble("IVA"), rs.getInt("Unidades"), rs.getDouble("Total")});
							  	contador += rs.getDouble("Total");
							  }
							  DecimalFormat df = new DecimalFormat("#,###.##"); 
							  
						}
						catch(Exception ay){
								  System.err.println(ay);
							  }
				}
				if(comboBox.getSelectedItem().toString().equalsIgnoreCase("Unidades")){
					DefaultTableModel dm = (DefaultTableModel) tablarepuestos.getModel();
					int rowCount = dm.getRowCount();
					//Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
					    dm.removeRow(i);
					}
					
					Double contador = 0.0;
					
					
					
					 BD conexion = new BD();
					  Statement stmt = null;
					    conexion.Conectar("electro_auto");
					    try{
					    	DefaultTableModel model = (DefaultTableModel) tablarepuestos.getModel();
					    	String selectSQL = "SELECT Codigo, Nombre, Descripcion, Precio, IVA, Unidades, Total FROM Repuestos ORDER BY Unidades DESC";
							  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
							  ResultSet rs = preparedStatement.executeQuery(selectSQL);
							  while (rs.next()) {
							  	
							  	
							  	
							  	
							  	model.addRow(new Object[]{rs.getInt("Codigo"), WordUtils.capitalizeFully(rs.getString("Nombre")), rs.getString("Descripcion"),
							  			rs.getDouble("Precio"), rs.getDouble("IVA"), rs.getInt("Unidades"), rs.getDouble("Total")});
							  	contador += rs.getDouble("Total");
							  }
							  DecimalFormat df = new DecimalFormat("#,###.##"); 
							  
						}
						catch(Exception ay){
								  System.err.println(ay);
							  }
				}
				if(comboBox.getSelectedItem().toString().equalsIgnoreCase("Total")){
					DefaultTableModel dm = (DefaultTableModel) tablarepuestos.getModel();
					int rowCount = dm.getRowCount();
					//Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
					    dm.removeRow(i);
					}
					
					Double contador = 0.0;
					
					
					
					 BD conexion = new BD();
					  Statement stmt = null;
					    conexion.Conectar("electro_auto");
					    try{
					    	DefaultTableModel model = (DefaultTableModel) tablarepuestos.getModel();
					    	String selectSQL = "SELECT Codigo, Nombre, Descripcion, Precio, IVA, Unidades, Total FROM Repuestos ORDER BY Total DESC";
							  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
							  ResultSet rs = preparedStatement.executeQuery(selectSQL);
							  while (rs.next()) {
							  	
							  	
							  	
							  	
							  	model.addRow(new Object[]{rs.getInt("Codigo"), WordUtils.capitalizeFully(rs.getString("Nombre")), rs.getString("Descripcion"),
							  			rs.getDouble("Precio"), rs.getDouble("IVA"), rs.getInt("Unidades"), rs.getDouble("Total")});
							  	contador += rs.getDouble("Total");
							  }
							  DecimalFormat df = new DecimalFormat("#,###.##"); 
							  
						}
						catch(Exception ay){
								  System.err.println(ay);
							  }
				}
			}
		});
		comboBox.setBackground(new Color(144, 238, 144));
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo", "Nombre", "Descripci\u00F3n", "Precio", "IVA", "Unidades", "Total"}));
		comboBox.setBounds(97, 8, 207, 24);
		panel_6.add(comboBox);
		
		
		lblTotalBs.setForeground(new Color(128, 0, 0));
		lblTotalBs.setBounds(322, 12, 346, 18);
		panel_6.add(lblTotalBs);
		
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 67, 911, 291);
		scrollPane_1.setOpaque(false);
		scrollPane_1.setBorder(null);
		scrollPane_1.setBackground(new Color(0, 128, 0));
		
		scrollPane_1.getViewport().setBackground(Color.WHITE);
		
		panel_5.add(scrollPane_1);
		
		tablarepuestos = new JTable();
		tablarepuestos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"<html><b>ID</b></html>", "<html><b>Nombre</b></html>", "<html><b>Descripci\u00F3n</b></html>", "<html><b>P.U</b></html>", 
				"<html><b>IVA</b></html>", "<html><b>Unidades</b></html>", "<html><b>Total</b></html>"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablarepuestos.setFont(new Font("Dialog", Font.BOLD, 12));
		scrollPane_1.setViewportView(tablarepuestos);
		tabbedPane.setForegroundAt(3, new Color(0, 128, 0));
		
		tablarepuestos.setBorder(new MatteBorder(1, 0, 1, 0, (Color) Color.WHITE));
		tablarepuestos.setRowSelectionAllowed(false);
		tablarepuestos.setForeground(Color.WHITE);
		tablarepuestos.setBackground(new Color(0, 128, 0));
		tablarepuestos.setShowHorizontalLines(true);
		tablarepuestos.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		tablarepuestos.setGridColor(Color.WHITE);
		
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(Color.WHITE);
		headerRenderer.setForeground(new Color(0, 128, 0));
		headerRenderer.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 0; i < tablarepuestos.getModel().getColumnCount(); i++) {
			tablarepuestos.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		}
		
		
		DefaultTableModel dm = (DefaultTableModel) tablarepuestos.getModel();
		int rowCount = dm.getRowCount();
		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
		    dm.removeRow(i);
		}
		
		Double contador = 0.0;
		
		
		
		 BD conexion = new BD();
		  Statement stmt = null;
		    conexion.Conectar("electro_auto");
		    try{
		    	DefaultTableModel model = (DefaultTableModel) tablarepuestos.getModel();
		    	String selectSQL = "SELECT Codigo, Nombre, Descripcion, Precio, IVA, Unidades, Total FROM Repuestos ORDER BY Codigo";
				  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
				  ResultSet rs = preparedStatement.executeQuery(selectSQL);
				  while (rs.next()) {
				  	
				  	
				  	
				  	
				  	model.addRow(new Object[]{rs.getInt("Codigo"), WordUtils.capitalizeFully(rs.getString("Nombre")), rs.getString("Descripcion"),
				  			rs.getDouble("Precio"), rs.getDouble("IVA"), rs.getInt("Unidades"), rs.getDouble("Total")});
				  	contador += rs.getDouble("Total");
				  }
				  DecimalFormat df = new DecimalFormat("#,###.##"); 
				  lblTotalBs.setText("Total: " + df.format(contador) + " BsS.");
			}
			catch(Exception e){
					  System.err.println(e);
				  }
		
		    

		    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		    centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		    tablarepuestos.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		    tablarepuestos.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		    tablarepuestos.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		    tablarepuestos.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		    tablarepuestos.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
		    tablarepuestos.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
		    tablarepuestos.getColumnModel().getColumn(6).setCellRenderer( centerRenderer );
		    
		
		conexion = new BD();
	    stmt = null;
	    conexion.Conectar("Electro_Auto");
	    try{
	    	
	    	String selectSQL = "SELECT Nombre FROM Repuestos";
			  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
			  ResultSet rs = preparedStatement.executeQuery(selectSQL);
			  comboBoxBRepuesto.setModel(new DefaultComboBoxModel(new String[] {"Buscar"}));
			  comboBoxERepuesto.setModel(new DefaultComboBoxModel(new String[] {"Buscar"}));
			  
			 
			 
			
			  while (rs.next()) {
				  comboBoxBRepuesto.addItem(WordUtils.capitalizeFully(rs.getString("Nombre")));
				  comboBoxERepuesto.addItem(WordUtils.capitalizeFully(rs.getString("Nombre")));
			  }
			  
		}
		catch(Exception xe){
				  System.err.println(xe);
			  }
		
		
		
	    JButton btnEliminar = new JButton("Eliminar");
	    btnEliminar.setFocusPainted(false);
	    btnEliminar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		
	    		if(comboBoxERepuesto.getSelectedItem().toString().equalsIgnoreCase("buscar")){
	    			JOptionPane.showMessageDialog(null, "ERROR: DEBE ELEGIR UN REPUESTO PARA ELIMINAR", "Electro Auto Carrillo - ERROR", JOptionPane.ERROR_MESSAGE);
	    			return;
	    			
	    			
	    		}
	    		
	    		BD op = new BD();
	    		try {
					op.EliminarRepuesto(comboBoxERepuesto.getSelectedItem().toString());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		
	    		
	    		
	    		
	    		JOptionPane.showMessageDialog(null, "REPUESTO ELIMINADO", "Electro Auto Carrillo", JOptionPane.INFORMATION_MESSAGE);
	    		
	    		
	    		
	    		txtENombre.setText("");
		  		txtEPrecio.setText("");
		  		txtETotal.setText("");
		  		txtEIVA.setText("");
		  		txtEUnidades.setText("");
		  		
		  		
		  		DefaultTableModel dm = (DefaultTableModel) tablarepuestos.getModel();
				int rowCount = dm.getRowCount();
				//Remove rows one by one from the end of the table
				for (int i = rowCount - 1; i >= 0; i--) {
				    dm.removeRow(i);
				}
				
				Double contador = 0.0;
				
				
				
				 BD conexion = new BD();
				  Statement stmt = null;
				    conexion.Conectar("electro_auto");
				    try{
				    	DefaultTableModel model = (DefaultTableModel) tablarepuestos.getModel();
				    	String selectSQL = "SELECT Codigo, Nombre, Descripcion, Precio, IVA, Unidades, Total FROM Repuestos ORDER BY Codigo";
						  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
						  ResultSet rs = preparedStatement.executeQuery(selectSQL);
						  while (rs.next()) {
						  	
						  	
						  	
						  	
						  	model.addRow(new Object[]{rs.getInt("Codigo"), WordUtils.capitalizeFully(rs.getString("Nombre")), rs.getString("Descripcion"),
						  			rs.getDouble("Precio"), rs.getDouble("IVA"), rs.getInt("Unidades"), rs.getDouble("Total")});
						  	contador += rs.getDouble("Total");
						  }
						  DecimalFormat df = new DecimalFormat("#,###.##"); 
						  lblTotalBs.setText("Total: " + df.format(contador) + " BsS.");
					}
					catch(Exception x){
							  System.err.println(x);
						  }
				
				
				
			    conexion.Conectar("Electro_Auto");
			    try{
			    	
			    	String selectSQL = "SELECT Nombre FROM Repuestos";
					  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
					  ResultSet rs = preparedStatement.executeQuery(selectSQL);
					  comboBoxBRepuesto.setModel(new DefaultComboBoxModel(new String[] {"Buscar"}));
					  comboBoxERepuesto.setModel(new DefaultComboBoxModel(new String[] {"Buscar"}));
					 
					  while (rs.next()) {
						  comboBoxBRepuesto.addItem(WordUtils.capitalizeFully(rs.getString("Nombre")));
						  comboBoxERepuesto.addItem(WordUtils.capitalizeFully(rs.getString("Nombre")));
					  }
			    }
					  catch(Exception ayo){
						  System.err.println(ayo);
					  }
		  		
	    		
	    	}
	    });
		  btnEliminar.setForeground(Color.WHITE);
		  btnEliminar.setBackground(new Color(0, 128, 0));
		  btnEliminar.setBounds(715, 12, 98, 26);
		  panel_4.add(btnEliminar);
		  
		  JButton btnEResetear = new JButton("Resetear");
		  btnEResetear.setFocusPainted(false);
		  btnEResetear.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		
		  		txtENombre.setText("");
		  		txtEPrecio.setText("");
		  		txtETotal.setText("");
		  		txtEIVA.setText("");
		  		txtEUnidades.setText("");
		  		
		  		
		  		BD conexion = new BD();
			    Statement stmt = null;
			    conexion.Conectar("Electro_Auto");
			    try{
			    	
			    	String selectSQL = "SELECT Nombre FROM Repuestos";
					  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
					  ResultSet rs = preparedStatement.executeQuery(selectSQL);
					  comboBoxBRepuesto.setModel(new DefaultComboBoxModel(new String[] {"Buscar"}));
					  comboBoxERepuesto.setModel(new DefaultComboBoxModel(new String[] {"Buscar"}));
					  
					 
					 
					
					  while (rs.next()) {
						  comboBoxBRepuesto.addItem(WordUtils.capitalizeFully(rs.getString("Nombre")));
						  comboBoxERepuesto.addItem(WordUtils.capitalizeFully(rs.getString("Nombre")));
					  }
					  
				}
				catch(Exception xe){
						  System.err.println(xe);
					  }
		  		
			    
		  		
		  		
		  		
		  		
		  	}
		  });
		  
		  btnEResetear.setForeground(Color.WHITE);
		  btnEResetear.setBackground(new Color(0, 128, 0));
		  btnEResetear.setBounds(825, 12, 98, 26);
		  panel_4.add(btnEResetear);
		
		VentanaInventario.setVisible(true);
		
		if(!Acceso.Cargo.equalsIgnoreCase("Administrador")){
			btnUsuarios.setEnabled(false);
		}
		if(Acceso.Cargo.equalsIgnoreCase("Consultor")){
			tabbedPane.remove(panel_2);
			tabbedPane.remove(panel_3);
			tabbedPane.remove(panel_4);
		}

	}
}
