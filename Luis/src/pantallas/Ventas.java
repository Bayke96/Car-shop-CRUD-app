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
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

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

import javax.swing.JToggleButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ventas {
	private static JTextField txtACodigo;
	private static JTextField txtIdentidad;
	private static JTextField txtRIF;
	private static JTextField txtAUnidades;
	private static JTable TablaNuevaVenta;
	private static JTextField txtMCodigo;
	private static JTable TablaMVenta;
	private static JTextField txtECodigo;
	private static JTable TablaEVenta;
	private static JTextField txtBCodigo;
	private static JTable TablaVenta;
	private static JTextField txtTelefono;
	
	public static double Total = 0.0;
	public static int NVenta = 1;
	
	public static Hashtable<String, Integer> ht = new Hashtable<String, Integer>();

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame VentanaVentas = new JFrame("Electro Autos Carrillo - Usuarios");
		VentanaVentas.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				BD open = new BD();
			
				open.Conectar("Electro_Auto");
				
				try{
					open.conn.setAutoCommit(false);
					PreparedStatement preparedStatement = null;
					String insertTableSQL = "UPDATE Repuestos SET Unidades = Unidades + ? WHERE Nombre = ?";
					preparedStatement = open.conn.prepareStatement(insertTableSQL);
					for(int i = 0; i < TablaNuevaVenta.getRowCount(); i++){
						preparedStatement.setInt(1, Integer.parseInt(TablaNuevaVenta.getValueAt(i, 3).toString()));
						preparedStatement.setString(2, TablaNuevaVenta.getValueAt(i, 1).toString().toLowerCase());
						preparedStatement.addBatch();
					}
					
					
					
				
			preparedStatement.executeBatch();
			open.conn.commit();
			open.Desconectar();
				}
				catch(Exception yo){
					yo.printStackTrace();
				}
			}
		});
		
		VentanaVentas.getContentPane().setBackground(new Color(204, 0, 0));
		VentanaVentas.setSize(1000, 620);
		VentanaVentas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		VentanaVentas.setResizable(false);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		VentanaVentas.setLocation(dim.width/2-VentanaVentas.getSize().width/2, dim.height/2-VentanaVentas.getSize().height/2);
		VentanaVentas.getContentPane().setLayout(null);
		
		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVentas.dispose();
				Usuarios.main(args);
			}
		});
		btnUsuarios.setFocusPainted(false);
		btnUsuarios.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnUsuarios.setForeground(new Color(178, 34, 34));
		btnUsuarios.setBackground(Color.WHITE);
		btnUsuarios.setIcon(new ImageIcon(Ventas.class.getResource("/imagenes/UsuariosIcon.png")));
		btnUsuarios.setBounds(6, 6, 235, 123);
		VentanaVentas.getContentPane().add(btnUsuarios);
		
		JButton btnInventario = new JButton("  Inventario");
		btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVentas.dispose();
				Inventario.main(args);
			}
		});
		btnInventario.setFocusPainted(false);
		btnInventario.setIcon(new ImageIcon(Ventas.class.getResource("/imagenes/InventarioIcon.png")));
		btnInventario.setForeground(new Color(178, 34, 34));
		btnInventario.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnInventario.setBackground(new Color(255, 255, 255));
		btnInventario.setBounds(248, 6, 235, 123);
		VentanaVentas.getContentPane().add(btnInventario);
		
		JButton btnVentas = new JButton("  Ventas");
		btnVentas.setFocusPainted(false);
		btnVentas.setIcon(new ImageIcon(Ventas.class.getResource("/imagenes/VentasIcon.png")));
		btnVentas.setForeground(Color.WHITE);
		btnVentas.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnVentas.setBackground(new Color(139, 0, 0));
		btnVentas.setBounds(495, 6, 235, 123);
		VentanaVentas.getContentPane().add(btnVentas);
		
		JButton btnOpciones = new JButton("  Opciones");
		btnOpciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVentas.dispose();
				Opciones.main(args);
			}
		});
		btnOpciones.setFocusPainted(false);
		btnOpciones.setIcon(new ImageIcon(Ventas.class.getResource("/imagenes/OpcionesIcon.png")));
		btnOpciones.setForeground(new Color(178, 34, 34));
		btnOpciones.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnOpciones.setBackground(new Color(255, 255, 255));
		btnOpciones.setBounds(742, 6, 235, 123);
		VentanaVentas.getContentPane().add(btnOpciones);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(16, 141, 954, 10);
		VentanaVentas.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(16, 576, 954, 10);
		VentanaVentas.getContentPane().add(panel_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(105, 105, 105));
		tabbedPane.setBounds(26, 163, 940, 406);
		VentanaVentas.getContentPane().add(tabbedPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Nueva", new ImageIcon(Ventas.class.getResource("/imagenes/AddRed.png")), panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label = new JLabel("NUEVA VENTA");
		label.setBounds(12, 12, 152, 25);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(178, 34, 34));
		label.setFont(new Font("Dialog", Font.BOLD, 18));
		label.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 0, 0)));
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("Nombre del Cliente");
		label_1.setBounds(22, 70, 107, 16);
		label_1.setForeground(new Color(178, 34, 34));
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("RIF");
		label_2.setBounds(22, 98, 107, 16);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(new Color(178, 34, 34));
		panel_2.add(label_2);
		
		JLabel label_3 = new JLabel("PRODUCTO");
		label_3.setBounds(22, 215, 122, 25);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(new Color(178, 34, 34));
		label_3.setFont(new Font("Dialog", Font.BOLD, 18));
		label_3.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 0, 0)));
		panel_2.add(label_3);
		
		JLabel label_4 = new JLabel("Por C\u00F3digo");
		label_4.setBounds(22, 265, 68, 16);
		label_4.setForeground(new Color(178, 34, 34));
		panel_2.add(label_4);
		
		txtACodigo = new JTextField();
		txtACodigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BD op = new BD();
				try {
					if(op.BuscarRepuestoCod(Integer.parseInt(txtACodigo.getText())) == false){
						JOptionPane.showMessageDialog(null, "ERROR: NO EXISTE NINGUN REPUESTO CON ESTE CODIGO", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
						txtACodigo.requestFocus();
						txtACodigo.selectAll();
						return;
					}
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtAUnidades.requestFocus();
				txtAUnidades.selectAll();
			}
		});
		txtACodigo.setBounds(108, 263, 68, 20);
		txtACodigo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 0, 0)));
		txtACodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtACodigo.setForeground(new Color(178, 34, 34));
		txtACodigo.setFont(new Font("Dialog", Font.BOLD, 12));
		txtACodigo.setColumns(10);
		panel_2.add(txtACodigo);
		
		JLabel label_5 = new JLabel("Por Nombre");
		label_5.setBounds(22, 304, 68, 16);
		label_5.setForeground(new Color(178, 34, 34));
		panel_2.add(label_5);
		
		JButton btnAlertas = new JButton("<html><center>ALERTAS DE <br />INVENTARIO</center></html>");
		btnAlertas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alertas.main(args);
			}
		});
		btnAlertas.setFocusPainted(false);
		btnAlertas.setBounds(306, 294, 148, 64);
		btnAlertas.setEnabled(false);
		btnAlertas.setForeground(new Color(255, 255, 0));
		btnAlertas.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(124, 252, 0)));
		btnAlertas.setBackground(new Color(0, 128, 0));
		panel_2.add(btnAlertas);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(464, 0, 471, 375);
		panel_7.setBackground(new Color(128, 0, 0));
		panel_2.add(panel_7);
		panel_7.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 43, 447, 320);
		scrollPane.setOpaque(true);
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(0, 128, 0));
		
		scrollPane.getViewport().setBackground(Color.WHITE);
		panel_7.add(scrollPane);
		
		TablaNuevaVenta = new JTable();
		TablaNuevaVenta.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00B0", "Nombre", "P.U", "Cantidades", "IVA", "Total"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(TablaNuevaVenta);
		
		
		
		TablaNuevaVenta.setBorder(new MatteBorder(1, 0, 1, 0, (Color) Color.WHITE));
		TablaNuevaVenta.setRowSelectionAllowed(false);
		TablaNuevaVenta.setForeground(new Color(128, 0, 0));
		TablaNuevaVenta.setBackground(new Color(255, 255, 255));
		TablaNuevaVenta.setFont(new Font("SansSerif", Font.BOLD, 10));
		
		TablaNuevaVenta.setGridColor(Color.WHITE);
		
		JLabel lblNVenta = new JLabel("N\u00B0 Venta:");
		lblNVenta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNVenta.setForeground(new Color(255, 255, 255));
		lblNVenta.setBounds(311, 15, 148, 16);
		panel_7.add(lblNVenta);
		
		JLabel lblTotalVenta = new JLabel("Total: 0.00 BsS.");
		lblTotalVenta.setForeground(new Color(255, 255, 255));
		lblTotalVenta.setBounds(12, 15, 204, 16);
		panel_7.add(lblTotalVenta);
		
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(Color.WHITE);
		headerRenderer.setForeground(new Color(178, 34, 34));
		headerRenderer.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 0; i < TablaNuevaVenta.getModel().getColumnCount(); i++) {
			TablaNuevaVenta.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		}
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment( JLabel.CENTER );
	    TablaNuevaVenta.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );   
	    TablaNuevaVenta.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
	    TablaNuevaVenta.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
	    TablaNuevaVenta.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
	    TablaNuevaVenta.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
	    
		
		txtIdentidad = new JTextField();
		txtIdentidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtRIF.requestFocus();
				txtRIF.selectAll();
			}
		});
		txtIdentidad.setBounds(147, 68, 114, 20);
		txtIdentidad.setFont(new Font("Dialog", Font.BOLD, 12));
		txtIdentidad.setForeground(new Color(178, 34, 34));
		txtIdentidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 0, 0)));
		panel_2.add(txtIdentidad);
		txtIdentidad.setColumns(10);
		
		txtRIF = new JTextField();
		txtRIF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTelefono.requestFocus();
				txtTelefono.selectAll();
			}
		});
		txtRIF.setBounds(147, 96, 114, 20);
		txtRIF.setFont(new Font("Dialog", Font.BOLD, 12));
		txtRIF.setForeground(new Color(178, 34, 34));
		txtRIF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 0, 0)));
		panel_2.add(txtRIF);
		txtRIF.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Cliente");
		lblNewLabel.setBounds(244, 17, 39, 16);
		lblNewLabel.setForeground(new Color(105, 105, 105));
		panel_2.add(lblNewLabel);
		
		JComboBox comboBoxClientes = new JComboBox();
		comboBoxClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BD op = new BD();
				op.Conectar("Electro_Auto");
				
				
				if(!comboBoxClientes.getSelectedItem().toString().equalsIgnoreCase("seleccione")){
					try{
						  String selectSQL = "SELECT Nombre, RIF, Telefono FROM Clientes WHERE Nombre = ?";
						  PreparedStatement preparedStatement = op.conn.prepareStatement(selectSQL);
						  preparedStatement.setString(1, comboBoxClientes.getSelectedItem().toString().toLowerCase());
						 
						  ResultSet rs = preparedStatement.executeQuery();
						  while (rs.next()) {
						  	String Nombre = rs.getString("Nombre");
						  	String RIF = rs.getString("RIF");
						  	String Telefono = rs.getString("Telefono");
						  
						  	txtIdentidad.setText(WordUtils.capitalizeFully(Nombre));
						  	txtRIF.setText(RIF);
						  	txtTelefono.setText(Telefono);
						  	
						  }
						 
						  
					}
					catch(Exception ayy){
							  System.err.println(ayy);
						  }
				}
				
			}
		});
		comboBoxClientes.setBounds(288, 13, 166, 24);
		comboBoxClientes.setModel(new DefaultComboBoxModel(new String[] {"Seleccione"}));
		comboBoxClientes.setForeground(new Color(0, 0, 0));
		comboBoxClientes.setBackground(new Color(192, 192, 192));
		panel_2.add(comboBoxClientes);
		
		JButton btnNewButton = new JButton("<html><center>Registrar <br />Nuevo Cliente</center></html>");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtIdentidad.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO CLIENTE ES OBLIGATORIO", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(!txtTelefono.getText().trim().equalsIgnoreCase("") && !txtTelefono.getText().trim().matches("[0-9-]+")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO TELEFONO SOLO ACEPTA NUMEROS Y RAYAS (-)", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
					txtTelefono.requestFocus();
					txtTelefono.selectAll();
					return;
				}
				
				BD op = new BD();
				try {
					if(op.BuscarCliente(txtIdentidad.getText()) == true){
						JOptionPane.showMessageDialog(null, "ERROR: ESTE CLIENTE YA EXISTE DENTRO DEL LISTADO!", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
						txtIdentidad.requestFocus();
						txtIdentidad.selectAll();
						return;
					}
					if(op.BuscarCliente(txtIdentidad.getText()) == false && !comboBoxClientes.getSelectedItem().toString().equalsIgnoreCase(txtIdentidad.getText())){
						op.RegistrarCliente(txtIdentidad.getText(), txtRIF.getText(), txtTelefono.getText());
						JOptionPane.showMessageDialog(null, "CLIENTE REGISTRADO!", "Electro Auto Carrillo", JOptionPane.INFORMATION_MESSAGE);
						
					}
					
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
				 BD conexion = new BD();
				  Statement stmt = null;
				
				  conexion.Conectar("Electro_Auto");
				    try{
				    	
				    	String selectSQL = "SELECT Nombre FROM Clientes";
						  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
						  ResultSet rs = preparedStatement.executeQuery(selectSQL);
						  comboBoxClientes.setModel(new DefaultComboBoxModel(new String[] {"Seleccione"}));
						  
						 
						  while (rs.next()) {
							  comboBoxClientes.addItem(WordUtils.capitalizeFully(rs.getString("Nombre")));
							 
						  }
				    }
						  catch(Exception ayo){
							  System.err.println(ayo);
						  }
				    
				    
				    
				    
				
			}
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBounds(288, 62, 152, 37);
		btnNewButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 191, 255)));
		btnNewButton.setBackground(new Color(135, 206, 235));
		btnNewButton.setForeground(new Color(0, 0, 0));
		panel_2.add(btnNewButton);
		
		JComboBox comboBoxProductos = new JComboBox();
		comboBoxProductos.setBounds(108, 300, 166, 24);
		comboBoxProductos.setForeground(new Color(0, 0, 0));
		comboBoxProductos.setBackground(new Color(192, 192, 192));
		panel_2.add(comboBoxProductos);
		
		JLabel lblUnidades = new JLabel("Unidades");
		lblUnidades.setBounds(22, 342, 68, 16);
		lblUnidades.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnidades.setForeground(new Color(178, 34, 34));
		panel_2.add(lblUnidades);
		
		JButton btnRegistrarVenta = new JButton("REGISTRAR VENTA");
		btnRegistrarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(TablaNuevaVenta.getRowCount() < 1 ){
					JOptionPane.showMessageDialog(null, "ERROR: LA VENTA DEBE CONTENER AL MENOS 1 REPUESTO", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				BD op = new BD();
				
				try {
					op.RegistrarVenta(TablaNuevaVenta, txtIdentidad.getText(), txtRIF.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				txtIdentidad.setText("");
		  		Total = 0.00;
		  		comboBoxProductos.setSelectedItem("Seleccione");
		  		comboBoxClientes.setSelectedItem("Seleccione");
		  		txtRIF.setText("");
		  		txtAUnidades.setText("");
		  		txtTelefono.setText("");
		  		lblTotalVenta.setText("Total: 0.00 BsS.");
		  		btnRegistrarVenta.setEnabled(false);
		  		
		  		
		  		
		  		NVenta ++;
		  		if(NVenta < 10) lblNVenta.setText("N° Venta: 0000" + NVenta);
				if(NVenta >= 10) lblNVenta.setText("N° Venta: 000" + NVenta);
				if(NVenta >= 100) lblNVenta.setText("N° Venta: 00" + NVenta);
				if(NVenta >= 1000) lblNVenta.setText("N° Venta: 0" + NVenta); 
				if(NVenta >= 10000) lblNVenta.setText("N° Venta: " + NVenta);  
		  		
		  		txtIdentidad.requestFocus();
		  		
		  		DefaultTableModel dm = (DefaultTableModel) TablaNuevaVenta.getModel();
				int rowCount = dm.getRowCount();
				//Remove rows one by one from the end of the table
				for (int i = rowCount - 1; i >= 0; i--) {
				    dm.removeRow(i);
				}
			}
		});
		
		txtAUnidades = new JTextField();
		txtAUnidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtACodigo.getText().equalsIgnoreCase("") && comboBoxProductos.getSelectedItem().toString().equalsIgnoreCase("seleccione")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE INTRODUCIR UN CODIGO O ELEGIR EL NOMBRE DEL PRODUCTO", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
					txtACodigo.requestFocus();
					return;
				}
				
				if(!txtACodigo.getText().equalsIgnoreCase("") && !txtACodigo.getText().matches("[0-9]+")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO CODIGO SOLO ACEPTA NÚMEROS", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
					txtACodigo.requestFocus();
					txtACodigo.selectAll();
					return;
				}
				BD op = new BD();
				try {
					if(!txtACodigo.getText().equalsIgnoreCase("") && op.BuscarRepuestoCod(Integer.parseInt(txtACodigo.getText())) == false){
						JOptionPane.showMessageDialog(null, "ERROR: NO EXISTE NINGUN REPUESTO CON ESTE CODIGO", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
						txtACodigo.requestFocus();
						txtACodigo.selectAll();
						return;
					}
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(txtAUnidades.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO UNIDADES ES OBLIGATORIO", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
					txtAUnidades.requestFocus();
					return;
				}
				if(!txtAUnidades.getText().matches("[0-9]+")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO UNIDADES SOLO ACEPTA NUMEROS", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
					txtAUnidades.requestFocus();
					txtAUnidades.selectAll();
					return;
				}
				if(Integer.parseInt(txtAUnidades.getText()) <= 0){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO UNIDADES SOLO ACEPTA NUMEROS MAYORES A 0!", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
					txtAUnidades.requestFocus();
					txtAUnidades.selectAll();
					return;
				}
				
				
				op.Conectar("Electro_Auto");
				int disponibles = 0;
				try{
					String UnitsSQL = "";
					PreparedStatement preparedStatement = null;
					
					  
					  
					  
					String selectSQL = "", nombre = "";
					Double precio = 0.0, IVA = 0.0;
					preparedStatement = null;
					  if(!txtACodigo.getText().equalsIgnoreCase("") && txtACodigo.getText().matches("[0-9]+")) {
						  selectSQL = "SELECT Nombre, Precio, IVA FROM Repuestos WHERE Codigo = ?";
						  preparedStatement = op.conn.prepareStatement(selectSQL);
						  preparedStatement.setInt(1, Integer.parseInt(txtACodigo.getText()));
					  }
					  if(!comboBoxProductos.getSelectedItem().toString().equalsIgnoreCase("seleccione")) {
						  selectSQL = "SELECT Nombre, Precio, IVA FROM Repuestos WHERE Nombre = ?";
						  preparedStatement = op.conn.prepareStatement(selectSQL);
						  preparedStatement.setString(1, comboBoxProductos.getSelectedItem().toString().toLowerCase());
					  }
					 
					Double total = 0.0;
					 
					 ResultSet rs = preparedStatement.executeQuery();
					  while (rs.next()) {
					  	nombre = rs.getString("Nombre");
					  	precio = rs.getDouble("Precio");
					  	IVA = rs.getDouble("IVA");
					  	DecimalFormat df = new DecimalFormat("#.##");
					  	
					    total = (precio + IVA) * Integer.parseInt(txtAUnidades.getText());
					  	
					  	
					  	Total += total;
					  }
					  
					  
					  if(!txtACodigo.getText().equalsIgnoreCase("") && txtACodigo.getText().matches("[0-9]+")) {
						  UnitsSQL = "SELECT Unidades FROM Repuestos WHERE Codigo = ?";
						  preparedStatement = op.conn.prepareStatement(UnitsSQL);
						  preparedStatement.setInt(1, Integer.parseInt(txtACodigo.getText()));
					  }
					  if(!comboBoxProductos.getSelectedItem().toString().equalsIgnoreCase("seleccione")) {
						  UnitsSQL = "SELECT Unidades FROM Repuestos WHERE Nombre = ?";
						  preparedStatement = op.conn.prepareStatement(UnitsSQL);
						  preparedStatement.setString(1, comboBoxProductos.getSelectedItem().toString().toLowerCase());
					  }
					  
					  rs = preparedStatement.executeQuery();
					  while (rs.next()) {
					  	disponibles = rs.getInt("Unidades");
					    if(disponibles < Integer.parseInt(txtAUnidades.getText())){
					    	JOptionPane.showMessageDialog(null, "<html>ERROR: EL PRODUCTO (" + WordUtils.capitalizeFully(nombre) + ") NO CUENTA CON TAL CANTIDAD DE UD. DISPONIBLES!<br />"
					    			+ "UD. DISPONIBLES: " + disponibles + " </html>", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
							txtAUnidades.requestFocus();
							txtAUnidades.selectAll();
					    	return;
					    }
					  }
					  DecimalFormat df = new DecimalFormat("#.##");
					  DefaultTableModel model = (DefaultTableModel) TablaNuevaVenta.getModel();
					  model.addRow(new Object[]{TablaNuevaVenta.getRowCount() + 1, WordUtils.capitalizeFully(nombre), precio,
					  			txtAUnidades.getText(), IVA, df.format(total).replaceAll(",", ".")});
					  
					  
					  String insertTableSQL = "UPDATE Repuestos SET Unidades = Unidades - ? WHERE Nombre = ?";
						preparedStatement = op.conn.prepareStatement(insertTableSQL);
						preparedStatement.setInt(1, Integer.parseInt(TablaNuevaVenta.getValueAt(TablaNuevaVenta.getRowCount() - 1, 3).toString()));
						preparedStatement.setString(2, TablaNuevaVenta.getValueAt(TablaNuevaVenta.getRowCount() - 1, 1).toString().toLowerCase());
						
						
						
					
				preparedStatement.executeUpdate();
					  
					 
					  op.Desconectar();
				}
				catch(Exception ayy){
						  System.err.println(ayy);
					  }
				
				
				
				
				DecimalFormat df = new DecimalFormat("#,###.##");
				  lblTotalVenta.setText("Total: " + df.format(Total) + " BsS.");
		  		txtACodigo.requestFocus();
		  		txtACodigo.selectAll();
		  	
		  		
		  		
				
				
				btnRegistrarVenta.setEnabled(true);
			}
		});
		txtAUnidades.setBounds(108, 338, 68, 20);
		txtAUnidades.setHorizontalAlignment(SwingConstants.CENTER);
		txtAUnidades.setForeground(new Color(178, 34, 34));
		txtAUnidades.setFont(new Font("Dialog", Font.BOLD, 12));
		txtAUnidades.setColumns(10);
		txtAUnidades.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 0, 0)));
		panel_2.add(txtAUnidades);
		
		
		
		JButton btnAgregarProducto = new JButton("");
		btnAgregarProducto.setFocusPainted(false);
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtACodigo.getText().equalsIgnoreCase("") && comboBoxProductos.getSelectedItem().toString().equalsIgnoreCase("seleccione")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE INTRODUCIR UN CODIGO O ELEGIR EL NOMBRE DEL PRODUCTO", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
					txtACodigo.requestFocus();
					return;
				}
				
				if(!txtACodigo.getText().equalsIgnoreCase("") && !txtACodigo.getText().matches("[0-9]+")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO CODIGO SOLO ACEPTA NÚMEROS", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
					txtACodigo.requestFocus();
					txtACodigo.selectAll();
					return;
				}
				BD op = new BD();
				try {
					if(!txtACodigo.getText().equalsIgnoreCase("") && op.BuscarRepuestoCod(Integer.parseInt(txtACodigo.getText())) == false){
						JOptionPane.showMessageDialog(null, "ERROR: NO EXISTE NINGUN REPUESTO CON ESTE CODIGO", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
						txtACodigo.requestFocus();
						txtACodigo.selectAll();
						return;
					}
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(txtAUnidades.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO UNIDADES ES OBLIGATORIO", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
					txtAUnidades.requestFocus();
					return;
				}
				if(!txtAUnidades.getText().matches("[0-9]+")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO UNIDADES SOLO ACEPTA NUMEROS", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
					txtAUnidades.requestFocus();
					txtAUnidades.selectAll();
					return;
				}
				if(Integer.parseInt(txtAUnidades.getText()) <= 0){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO UNIDADES SOLO ACEPTA NUMEROS MAYORES A 0!", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
					txtAUnidades.requestFocus();
					txtAUnidades.selectAll();
					return;
				}
				
				
				op.Conectar("Electro_Auto");
				int disponibles = 0;
				try{
					String UnitsSQL = "";
					PreparedStatement preparedStatement = null;
					
					  
					  
					  
					String selectSQL = "", nombre = "";
					Double precio = 0.0, IVA = 0.0;
					preparedStatement = null;
					  if(!txtACodigo.getText().equalsIgnoreCase("") && txtACodigo.getText().matches("[0-9]+")) {
						  selectSQL = "SELECT Nombre, Precio, IVA FROM Repuestos WHERE Codigo = ?";
						  preparedStatement = op.conn.prepareStatement(selectSQL);
						  preparedStatement.setInt(1, Integer.parseInt(txtACodigo.getText()));
					  }
					  if(!comboBoxProductos.getSelectedItem().toString().equalsIgnoreCase("seleccione")) {
						  selectSQL = "SELECT Nombre, Precio, IVA FROM Repuestos WHERE Nombre = ?";
						  preparedStatement = op.conn.prepareStatement(selectSQL);
						  preparedStatement.setString(1, comboBoxProductos.getSelectedItem().toString().toLowerCase());
					  }
					 
					Double total = 0.0;
					 
					 ResultSet rs = preparedStatement.executeQuery();
					  while (rs.next()) {
					  	nombre = rs.getString("Nombre");
					  	precio = rs.getDouble("Precio");
					  	IVA = rs.getDouble("IVA");
					  	DecimalFormat df = new DecimalFormat("#.##");
					  	
					    total = (precio + IVA) * Integer.parseInt(txtAUnidades.getText());
					  	
					  	
					  	Total += total;
					  }
					 
					  
					  if(!txtACodigo.getText().equalsIgnoreCase("") && txtACodigo.getText().matches("[0-9]+")) {
						  UnitsSQL = "SELECT Unidades FROM Repuestos WHERE Codigo = ?";
						  preparedStatement = op.conn.prepareStatement(UnitsSQL);
						  preparedStatement.setInt(1, Integer.parseInt(txtACodigo.getText()));
					  }
					  if(!comboBoxProductos.getSelectedItem().toString().equalsIgnoreCase("seleccione")) {
						  UnitsSQL = "SELECT Unidades FROM Repuestos WHERE Nombre = ?";
						  preparedStatement = op.conn.prepareStatement(UnitsSQL);
						  preparedStatement.setString(1, comboBoxProductos.getSelectedItem().toString().toLowerCase());
					  }
					  
					  rs = preparedStatement.executeQuery();
					  while (rs.next()) {
					  	disponibles = rs.getInt("Unidades");
					    if(disponibles < Integer.parseInt(txtAUnidades.getText())){
					    	JOptionPane.showMessageDialog(null, "<html>ERROR: EL PRODUCTO (" + WordUtils.capitalizeFully(nombre) + ") NO CUENTA CON TAL CANTIDAD DE UD. DISPONIBLES!<br />"
					    			+ "UD. DISPONIBLES: " + disponibles + " </html>", "Electro Auto Carrillo - Error", JOptionPane.WARNING_MESSAGE);
							txtAUnidades.requestFocus();
							txtAUnidades.selectAll();
					    	return;
					    }
					  }
					  DecimalFormat df = new DecimalFormat("#.##");
					  DefaultTableModel model = (DefaultTableModel) TablaNuevaVenta.getModel();
					  model.addRow(new Object[]{TablaNuevaVenta.getRowCount() + 1, WordUtils.capitalizeFully(nombre), precio,
					  			txtAUnidades.getText(), IVA, df.format(total).replaceAll(",", ".")});
					  
					  
					  String insertTableSQL = "UPDATE Repuestos SET Unidades = Unidades - ? WHERE Nombre = ?";
						preparedStatement = op.conn.prepareStatement(insertTableSQL);
						preparedStatement.setInt(1, Integer.parseInt(TablaNuevaVenta.getValueAt(TablaNuevaVenta.getRowCount() - 1, 3).toString()));
						preparedStatement.setString(2, TablaNuevaVenta.getValueAt(TablaNuevaVenta.getRowCount() - 1, 1).toString().toLowerCase());
						
						
						
					
				preparedStatement.executeUpdate();
					  
					 
					  op.Desconectar();
				}
				catch(Exception ayy){
						  System.err.println(ayy);
					  }
				
				
				
				
				 DecimalFormat df = new DecimalFormat("#,###.##");
				  lblTotalVenta.setText("Total: " + df.format(Total) + " BsS.");
		  		txtACodigo.requestFocus();
		  		txtACodigo.selectAll();
		  	
		  		
		  		
				
				
				btnRegistrarVenta.setEnabled(true);
			}
		});
		btnAgregarProducto.setToolTipText("Agrega un producto a la venta");
		btnAgregarProducto.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
		btnAgregarProducto.setIcon(new ImageIcon(Ventas.class.getResource("/imagenes/AddProduct.png")));
		btnAgregarProducto.setBackground(new Color(255, 255, 255));
		btnAgregarProducto.setBounds(162, 215, 52, 37);
		panel_2.add(btnAgregarProducto);
		
		
		btnRegistrarVenta.setEnabled(false);
		btnRegistrarVenta.setToolTipText("Registra la venta en la base de datos");
		btnRegistrarVenta.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
		btnRegistrarVenta.setBackground(new Color(255, 255, 255));
		btnRegistrarVenta.setForeground(new Color(128, 0, 0));
		btnRegistrarVenta.setBounds(302, 215, 152, 37);
		panel_2.add(btnRegistrarVenta);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelfono.setForeground(new Color(178, 34, 34));
		lblTelfono.setBounds(22, 126, 107, 16);
		panel_2.add(lblTelfono);
		
		txtTelefono = new JTextField();
		txtTelefono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!txtTelefono.getText().trim().equalsIgnoreCase("") && !txtTelefono.getText().trim().matches("[0-9-]+")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO TELEFONO SOLO ACEPTA NUMEROS Y RAYAS (-)", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
					txtTelefono.requestFocus();
					txtTelefono.selectAll();
					return;
				}
				
				txtACodigo.requestFocus();
				txtACodigo.selectAll();
			}
		});
		txtTelefono.setForeground(new Color(178, 34, 34));
		txtTelefono.setFont(new Font("Dialog", Font.BOLD, 12));
		txtTelefono.setColumns(10);
		txtTelefono.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 0, 0)));
		txtTelefono.setBounds(147, 128, 114, 20);
		panel_2.add(txtTelefono);
		tabbedPane.setForegroundAt(0, new Color(255, 255, 255));
		tabbedPane.setBackgroundAt(0, new Color(105, 105, 105));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		tabbedPane.addTab("Modificar", new ImageIcon(Ventas.class.getResource("/imagenes/EditRed.png")), panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("C\u00F3digo");
		lblNewLabel_1.setBounds(681, 20, 44, 16);
		lblNewLabel_1.setForeground(new Color(178, 34, 34));
		panel_3.add(lblNewLabel_1);
		
		JLabel lblMCliente = new JLabel("Cliente:");
		
		txtMCodigo = new JTextField();
		txtMCodigo.setBounds(735, 18, 68, 20);
		txtMCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtMCodigo.setForeground(new Color(178, 34, 34));
		txtMCodigo.setFont(new Font("Dialog", Font.BOLD, 12));
		txtMCodigo.setColumns(10);
		txtMCodigo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 0, 0)));
		panel_3.add(txtMCodigo);
		
		JLabel lblMTotal = new JLabel("Total: 0,00 BsS.");
		
		txtMCodigo.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  
			    if(txtMCodigo.getText().matches("[0-9]+")){
			    	BD operacion = new BD();
			    	DefaultTableModel md = (DefaultTableModel) TablaMVenta.getModel();
			    	for (int i = md.getRowCount() - 1; i >= 0; i--) {
					    md.removeRow(i);
					}
					  operacion.Conectar("Electro_Auto");
					  
					  try{
						  String selectSQL = "";
						  PreparedStatement preparedStatement = null;
						  
								 selectSQL = "SELECT Cliente, Producto, Unidades, Total FROM Ventas WHERE N_Venta = ?";
								 preparedStatement = operacion.conn.prepareStatement(selectSQL);
								  preparedStatement.setInt(1, Integer.parseInt(txtMCodigo.getText()));
							  
						  Double contador = 0.00;
						  ResultSet rs = preparedStatement.executeQuery();
						  while (rs.next()) {
						  	String Producto = rs.getString("Producto");
						  	String Cliente = rs.getString("Cliente");
						  	int Cantidades = rs.getInt("Unidades");
						  	Double Total = rs.getDouble("Total");
						  	Double Precio = Total / Cantidades;
						  	Double IVA = Precio * 0.16;
						  	DecimalFormat df = new DecimalFormat("0.00");
						  	md.addRow(new Object[]{TablaMVenta.getRowCount() + 1, WordUtils.capitalizeFully(Producto), df.format(Precio - IVA).replaceAll(",", "."), 
						  			Cantidades, df.format(IVA).replaceAll(",", "."), Total});
						  	lblMCliente.setText("Cliente: " + WordUtils.capitalizeFully(Cliente));
						  	
						  	contador += Total;
						  }
						  DecimalFormat df = new DecimalFormat("#,###.##");
						  lblMTotal.setText("Total: " + df.format(contador) + " BsS.");
						  
						
						  operacion.Desconectar();
					  }
					  catch(Exception x){
						  System.err.println(x);
					  }
			    }
			  }
			  public void removeUpdate(DocumentEvent e) {
				  if(txtMCodigo.getText().matches("[0-9]+")){
				    	BD operacion = new BD();
				    	DefaultTableModel md = (DefaultTableModel) TablaMVenta.getModel();
				    	for (int i = md.getRowCount() - 1; i >= 0; i--) {
						    md.removeRow(i);
						}
						  operacion.Conectar("Electro_Auto");
						  
						  try{
							  String selectSQL = "";
							  PreparedStatement preparedStatement = null;
							  
							  selectSQL = "SELECT Cliente, Producto, Unidades, Total FROM Ventas WHERE N_Venta = ?";
									 preparedStatement = operacion.conn.prepareStatement(selectSQL);
									  preparedStatement.setInt(1, Integer.parseInt(txtMCodigo.getText()));
								  
									  Double contador = 0.00;
							  ResultSet rs = preparedStatement.executeQuery();
							  while (rs.next()) {
							  	String Producto = rs.getString("Producto");
							  	String Cliente = rs.getString("Cliente");
							  	int Cantidades = rs.getInt("Unidades");
							  	Double Total = rs.getDouble("Total");
							  	Double Precio = Total / Cantidades;
							  	Double IVA = Precio * 0.16;
							  	DecimalFormat df = new DecimalFormat("0.00");
							  	md.addRow(new Object[]{TablaMVenta.getRowCount() + 1, WordUtils.capitalizeFully(Producto), df.format(Precio - IVA).replaceAll(",", "."), 
							  			Cantidades, df.format(IVA).replaceAll(",", "."), Total});
							  	lblMCliente.setText("Cliente: " + WordUtils.capitalizeFully(Cliente));
							  	contador += Total;
							  }
							  	
							  DecimalFormat df = new DecimalFormat("#,###.##");
							  lblMTotal.setText("Total: " + df.format(contador) + " BsS.");
							
							  operacion.Desconectar();
						  }
						  catch(Exception x){
							  System.err.println(x);
						  }
				    }
			  }
			  public void insertUpdate(DocumentEvent e) {
				  if(txtMCodigo.getText().matches("[0-9]+")){
				    	BD operacion = new BD();
				    	DefaultTableModel md = (DefaultTableModel) TablaMVenta.getModel();
				    	for (int i = md.getRowCount() - 1; i >= 0; i--) {
						    md.removeRow(i);
						}
						  operacion.Conectar("Electro_Auto");
						  
						  try{
							  String selectSQL = "";
							  PreparedStatement preparedStatement = null;
							  
							  selectSQL = "SELECT Cliente, Producto, Unidades, Total FROM Ventas WHERE N_Venta = ?";
									 preparedStatement = operacion.conn.prepareStatement(selectSQL);
									  preparedStatement.setInt(1, Integer.parseInt(txtMCodigo.getText()));
								  
									  Double contador = 0.00;
							  ResultSet rs = preparedStatement.executeQuery();
							  while (rs.next()) {
							  	String Producto = rs.getString("Producto");
							  	String Cliente = rs.getString("Cliente");
							  	int Cantidades = rs.getInt("Unidades");
							  	Double Total = rs.getDouble("Total");
							  	Double Precio = Total / Cantidades;
							  	Double IVA = Precio * 0.16;
							  	DecimalFormat df = new DecimalFormat("0.00");
							  	md.addRow(new Object[]{TablaMVenta.getRowCount() + 1, WordUtils.capitalizeFully(Producto), df.format(Precio - IVA).replaceAll(",", "."), 
							  			Cantidades, df.format(IVA).replaceAll(",", "."), Total});
							  	lblMCliente.setText("Cliente: " + WordUtils.capitalizeFully(Cliente));
							  	contador += Total;
							  }
							  	
							  DecimalFormat df = new DecimalFormat("#,###.##");
							  lblMTotal.setText("Total: " + df.format(contador) + " BsS.");
							
							  operacion.Desconectar();
						  }
						  catch(Exception x){
							  System.err.println(x);
						  }
				    }
			  }

			 
			  
			});
		
		
		lblMCliente.setBounds(12, 10, 275, 26);
		lblMCliente.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMCliente.setForeground(new Color(178, 34, 34));
		panel_3.add(lblMCliente);
		
		
		lblMTotal.setBounds(346, 12, 275, 16);
		lblMTotal.setForeground(new Color(178, 34, 34));
		lblMTotal.setFont(new Font("Dialog", Font.BOLD, 16));
		panel_3.add(lblMTotal);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(new Color(255, 255, 255));
		scrollPane_1.setBounds(12, 50, 907, 313);
		panel_3.add(scrollPane_1);
		
		TablaMVenta = new JTable();
		TablaMVenta.setFillsViewportHeight(true);
		TablaMVenta.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"N\u00B0", "Nombre", "P.U", "Cantidades", "IVA", "Total"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, true, true, true, true, true
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		scrollPane_1.setViewportView(TablaMVenta);
		tabbedPane.setForegroundAt(1, new Color(255, 255, 255));
		
		
		
		TablaMVenta.setBorder(new MatteBorder(1, 0, 1, 0, (Color) Color.WHITE));
		TablaMVenta.setRowSelectionAllowed(false);
		TablaMVenta.setForeground(new Color(128, 0, 0));
		TablaMVenta.setBackground(new Color(255, 255, 255));
		TablaMVenta.setShowHorizontalLines(true);
		TablaMVenta.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		TablaMVenta.setGridColor(Color.WHITE);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(TablaMVenta.getRowCount() < 1){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE ELEGIR UNA VENTA PARA MODIFICAR", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(txtMCodigo.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE INSERTAR UN CODIGO DE VENTA", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(!txtMCodigo.getText().matches("[0-9]+")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO CODIGO SOLO ACEPTA NUMEROS", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				BD op = new BD();
				try {
					op.ActualizarVenta(TablaMVenta, Integer.parseInt(txtMCodigo.getText()));
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				DefaultTableModel md = (DefaultTableModel) TablaMVenta.getModel();
		    	for (int i = md.getRowCount() - 1; i >= 0; i--) {
				    md.removeRow(i);
				}
				
				
				lblMCliente.setText("Cliente: ");
				lblMTotal.setText("Total: 0.00 BsS.");
				
				JOptionPane.showMessageDialog(null, "VENTA MODIFICADA", "Electro Auto Carrillo", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnActualizar.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(128, 0, 0)));
		btnActualizar.setBackground(new Color(255, 255, 255));
		btnActualizar.setForeground(new Color(128, 0, 0));
		btnActualizar.setBounds(821, 12, 98, 26);
		panel_3.add(btnActualizar);
		
		headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(Color.WHITE);
		headerRenderer.setForeground(new Color(178, 34, 34));
		headerRenderer.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 0; i < TablaMVenta.getModel().getColumnCount(); i++) {
			TablaMVenta.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		}
		
		
	    centerRenderer.setHorizontalAlignment( JLabel.CENTER );
	    TablaMVenta.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );   
	    TablaMVenta.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
	    TablaMVenta.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
	    TablaMVenta.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
	    TablaMVenta.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		tabbedPane.addTab("Eliminar", new ImageIcon(Ventas.class.getResource("/imagenes/DeleteRed.png")), panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblECliente = new JLabel("Cliente:");
		lblECliente.setForeground(new Color(178, 34, 34));
		lblECliente.setFont(new Font("Dialog", Font.BOLD, 16));
		lblECliente.setBounds(12, 14, 275, 30);
		panel_4.add(lblECliente);
		
		JLabel lblETotal = new JLabel("Total: 0,00 BsS.");
		lblETotal.setForeground(new Color(178, 34, 34));
		lblETotal.setFont(new Font("Dialog", Font.BOLD, 16));
		lblETotal.setBounds(363, 14, 275, 30);
		panel_4.add(lblETotal);
		
		txtECodigo = new JTextField();
		txtECodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtECodigo.setForeground(new Color(178, 34, 34));
		txtECodigo.setFont(new Font("Dialog", Font.BOLD, 12));
		txtECodigo.setColumns(10);
		txtECodigo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 0, 0)));
		txtECodigo.setBounds(782, 29, 68, 20);
		panel_4.add(txtECodigo);
		
		txtECodigo.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  
			    if(txtECodigo.getText().matches("[0-9]+")){
			    	BD operacion = new BD();
			    	DefaultTableModel md = (DefaultTableModel) TablaEVenta.getModel();
			    	for (int i = md.getRowCount() - 1; i >= 0; i--) {
					    md.removeRow(i);
					}
					  operacion.Conectar("Electro_Auto");
					  
					  try{
						  String selectSQL = "";
						  PreparedStatement preparedStatement = null;
						  
								 selectSQL = "SELECT Cliente, Producto, Unidades, Total FROM Ventas WHERE N_Venta = ?";
								 preparedStatement = operacion.conn.prepareStatement(selectSQL);
								  preparedStatement.setInt(1, Integer.parseInt(txtECodigo.getText()));
							  
						  Double contador = 0.00;
						  ResultSet rs = preparedStatement.executeQuery();
						  while (rs.next()) {
						  	String Producto = rs.getString("Producto");
						  	String Cliente = rs.getString("Cliente");
						  	int Cantidades = rs.getInt("Unidades");
						  	Double Total = rs.getDouble("Total");
						  	Double Precio = Total / Cantidades;
						  	Double IVA = Precio * 0.16;
						  	DecimalFormat df = new DecimalFormat("0.00");
						  	md.addRow(new Object[]{TablaEVenta.getRowCount() + 1, WordUtils.capitalizeFully(Producto), df.format(Precio - IVA).replaceAll(",", "."), 
						  			Cantidades, df.format(IVA).replaceAll(",", "."), Total});
						  	lblECliente.setText("Cliente: " + WordUtils.capitalizeFully(Cliente));
						  	
						  	contador += Total;
						  }
						  DecimalFormat df = new DecimalFormat("#,###.##");
						  lblETotal.setText("Total: " + df.format(contador) + " BsS.");
						  
						
						  operacion.Desconectar();
					  }
					  catch(Exception x){
						  System.err.println(x);
					  }
			    }
			  }
			  public void removeUpdate(DocumentEvent e) {
				  if(txtECodigo.getText().matches("[0-9]+")){
					  BD operacion = new BD();
				    	DefaultTableModel md = (DefaultTableModel) TablaEVenta.getModel();
				    	for (int i = md.getRowCount() - 1; i >= 0; i--) {
						    md.removeRow(i);
						}
						  operacion.Conectar("Electro_Auto");
						  
						  try{
							  String selectSQL = "";
							  PreparedStatement preparedStatement = null;
							  
									 selectSQL = "SELECT Cliente, Producto, Unidades, Total FROM Ventas WHERE N_Venta = ?";
									 preparedStatement = operacion.conn.prepareStatement(selectSQL);
									  preparedStatement.setInt(1, Integer.parseInt(txtECodigo.getText()));
								  
							  Double contador = 0.00;
							  ResultSet rs = preparedStatement.executeQuery();
							  while (rs.next()) {
							  	String Producto = rs.getString("Producto");
							  	String Cliente = rs.getString("Cliente");
							  	int Cantidades = rs.getInt("Unidades");
							  	Double Total = rs.getDouble("Total");
							  	Double Precio = Total / Cantidades;
							  	Double IVA = Precio * 0.16;
							  	DecimalFormat df = new DecimalFormat("0.00");
							  	md.addRow(new Object[]{TablaEVenta.getRowCount() + 1, WordUtils.capitalizeFully(Producto), df.format(Precio - IVA).replaceAll(",", "."), 
							  			Cantidades, df.format(IVA).replaceAll(",", "."), Total});
							  	lblECliente.setText("Cliente: " + WordUtils.capitalizeFully(Cliente));
							  	
							  	contador += Total;
							  }
							  DecimalFormat df = new DecimalFormat("#,###.##");
							  lblETotal.setText("Total: " + df.format(contador) + " BsS.");
							  
							
							  operacion.Desconectar();
						  }
						  catch(Exception x){
							  System.err.println(x);
						  }
				    }
			  }
			  public void insertUpdate(DocumentEvent e) {
				  if(txtECodigo.getText().matches("[0-9]+")){
					  BD operacion = new BD();
				    	DefaultTableModel md = (DefaultTableModel) TablaEVenta.getModel();
				    	for (int i = md.getRowCount() - 1; i >= 0; i--) {
						    md.removeRow(i);
						}
						  operacion.Conectar("Electro_Auto");
						  
						  try{
							  String selectSQL = "";
							  PreparedStatement preparedStatement = null;
							  
									 selectSQL = "SELECT Cliente, Producto, Unidades, Total FROM Ventas WHERE N_Venta = ?";
									 preparedStatement = operacion.conn.prepareStatement(selectSQL);
									  preparedStatement.setInt(1, Integer.parseInt(txtECodigo.getText()));
								  
							  Double contador = 0.00;
							  ResultSet rs = preparedStatement.executeQuery();
							  while (rs.next()) {
							  	String Producto = rs.getString("Producto");
							  	String Cliente = rs.getString("Cliente");
							  	int Cantidades = rs.getInt("Unidades");
							  	Double Total = rs.getDouble("Total");
							  	Double Precio = Total / Cantidades;
							  	Double IVA = Precio * 0.16;
							  	DecimalFormat df = new DecimalFormat("0.00");
							  	md.addRow(new Object[]{TablaEVenta.getRowCount() + 1, WordUtils.capitalizeFully(Producto), df.format(Precio - IVA).replaceAll(",", "."), 
							  			Cantidades, df.format(IVA).replaceAll(",", "."), Total});
							  	lblECliente.setText("Cliente: " + WordUtils.capitalizeFully(Cliente));
							  	
							  	contador += Total;
							  }
							  DecimalFormat df = new DecimalFormat("#,###.##");
							  lblETotal.setText("Total: " + df.format(contador) + " BsS.");
							  
							
							  operacion.Desconectar();
						  }
						  catch(Exception x){
							  System.err.println(x);
						  }
				    }
			  }

			 
			  
			});
		
		JLabel label_8 = new JLabel("C\u00F3digo");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setForeground(new Color(178, 34, 34));
		label_8.setBounds(782, 0, 68, 30);
		panel_4.add(label_8);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 52, 914, 311);
		panel_4.add(scrollPane_2);
		
		TablaEVenta = new JTable();
		TablaEVenta.setFillsViewportHeight(true);
		TablaEVenta.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"N\u00B0", "Nombre", "P.U", "Cantidades", "IVA", "Total"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		
	
		
		
		scrollPane_2.setViewportView(TablaEVenta);
		TablaEVenta.setShowHorizontalLines(true);
		TablaEVenta.setRowSelectionAllowed(false);
		TablaEVenta.setGridColor(new Color(0, 0, 205));
		TablaEVenta.setForeground(new Color(128, 0, 0));
		TablaEVenta.setFont(new Font("SansSerif", Font.BOLD, 6));
		TablaEVenta.setBorder(new MatteBorder(1, 0, 1, 0, (Color) Color.WHITE));
		TablaEVenta.setBackground(Color.WHITE);
		
		
		
		TablaEVenta.setBackground(new Color(255, 255, 255));
		TablaEVenta.setShowHorizontalLines(true);
		TablaEVenta.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		TablaEVenta.setGridColor(Color.WHITE);
		

		
		
		headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(Color.WHITE);
		headerRenderer.setForeground(new Color(178, 34, 34));
		headerRenderer.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 0; i < TablaEVenta.getModel().getColumnCount(); i++) {
			TablaEVenta.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		}
		
		
		
		 centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		 TablaEVenta.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );   
		 TablaEVenta.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		 TablaEVenta.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		 TablaEVenta.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
		 TablaEVenta.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
		 
		
		
		JButton btnEVenta = new JButton("");
		btnEVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(TablaEVenta.getRowCount() < 1 ){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE ELEGIR UNA VENTA PARA ELIMINAR", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(txtECodigo.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "ERROR: DEBE INSERTAR UN CODIGO DE VENTA", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(!txtECodigo.getText().matches("[0-9]+")){
					JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO CODIGO SOLO ACEPTA NUMEROS", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				BD op = new BD();
				try {
					op.EliminarVenta(TablaEVenta, Integer.parseInt(txtECodigo.getText()));
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				DefaultTableModel md = (DefaultTableModel) TablaEVenta.getModel();
		    	for (int i = md.getRowCount() - 1; i >= 0; i--) {
				    md.removeRow(i);
				}
				
				
				lblECliente.setText("Cliente: ");
				lblETotal.setText("Total: 0.00 BsS.");
				
				JOptionPane.showMessageDialog(null, "VENTA ELIMINADA", "Electro Auto Carrillo", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnEVenta.setFocusPainted(false);
		btnEVenta.setIcon(new ImageIcon(Ventas.class.getResource("/imagenes/DeleteSale.png")));
		btnEVenta.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(128, 0, 0)));
		btnEVenta.setBackground(new Color(255, 255, 255));
		btnEVenta.setForeground(new Color(128, 0, 0));
		btnEVenta.setBounds(862, 0, 61, 51);
		panel_4.add(btnEVenta);
		tabbedPane.setForegroundAt(2, new Color(255, 255, 255));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		tabbedPane.addTab("Visualizar", new ImageIcon(Ventas.class.getResource("/imagenes/ViewRed.png")), panel_5, null);
		panel_5.setLayout(null);
		
		JLabel label_6 = new JLabel("C\u00F3digo");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(new Color(178, 34, 34));
		label_6.setBounds(12, 5, 68, 30);
		panel_5.add(label_6);
		
		JLabel lblVTotal = new JLabel("Total: 0.00 BsS.");
		
		txtBCodigo = new JTextField();
		txtBCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtBCodigo.setForeground(new Color(178, 34, 34));
		txtBCodigo.setFont(new Font("Dialog", Font.BOLD, 12));
		txtBCodigo.setColumns(10);
		txtBCodigo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 0, 0)));
		txtBCodigo.setBounds(84, 10, 68, 20);
		panel_5.add(txtBCodigo);
		
		JLabel lblVCliente = new JLabel("Cliente:");
		
		txtBCodigo.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  
			    if(txtBCodigo.getText().matches("[0-9]+")){
			    	BD operacion = new BD();
			    	DefaultTableModel md = (DefaultTableModel) TablaVenta.getModel();
			    	for (int i = md.getRowCount() - 1; i >= 0; i--) {
					    md.removeRow(i);
					}
					  operacion.Conectar("Electro_Auto");
					  
					  try{
						  String selectSQL = "";
						  PreparedStatement preparedStatement = null;
						  
								 selectSQL = "SELECT Cliente, Producto, Unidades, Total FROM Ventas WHERE N_Venta = ?";
								 preparedStatement = operacion.conn.prepareStatement(selectSQL);
								  preparedStatement.setInt(1, Integer.parseInt(txtBCodigo.getText()));
							  
						  Double contador = 0.00;
						  ResultSet rs = preparedStatement.executeQuery();
						  while (rs.next()) {
						  	String Producto = rs.getString("Producto");
						  	String Cliente = rs.getString("Cliente");
						  	int Cantidades = rs.getInt("Unidades");
						  	Double Total = rs.getDouble("Total");
						  	Double Precio = Total / Cantidades;
						  	Double IVA = Precio * 0.16;
						  	DecimalFormat df = new DecimalFormat("0.00");
						  	md.addRow(new Object[]{TablaVenta.getRowCount() + 1, WordUtils.capitalizeFully(Producto), df.format(Precio - IVA).replaceAll(",", "."), 
						  			Cantidades, df.format(IVA).replaceAll(",", "."), Total});
						  	lblVCliente.setText("Cliente: " + WordUtils.capitalizeFully(Cliente));
						  	
						  	contador += Total;
						  }
						  DecimalFormat df = new DecimalFormat("#,###.##");
						  lblVTotal.setText("Total: " + df.format(contador) + " BsS.");
						  
						
						  operacion.Desconectar();
					  }
					  catch(Exception x){
						  System.err.println(x);
					  }
			    }
			  }
			  public void removeUpdate(DocumentEvent e) {
				  if(txtBCodigo.getText().matches("[0-9]+")){
				    	BD operacion = new BD();
				    	DefaultTableModel md = (DefaultTableModel) TablaVenta.getModel();
				    	for (int i = md.getRowCount() - 1; i >= 0; i--) {
						    md.removeRow(i);
						}
						  operacion.Conectar("Electro_Auto");
						  
						  try{
							  String selectSQL = "";
							  PreparedStatement preparedStatement = null;
							  
									 selectSQL = "SELECT Cliente, Producto, Unidades, Total FROM Ventas WHERE N_Venta = ?";
									 preparedStatement = operacion.conn.prepareStatement(selectSQL);
									  preparedStatement.setInt(1, Integer.parseInt(txtBCodigo.getText()));
								  
							  Double contador = 0.00;
							  ResultSet rs = preparedStatement.executeQuery();
							  while (rs.next()) {
							  	String Producto = rs.getString("Producto");
							  	String Cliente = rs.getString("Cliente");
							  	int Cantidades = rs.getInt("Unidades");
							  	Double Total = rs.getDouble("Total");
							  	Double Precio = Total / Cantidades;
							  	Double IVA = Precio * 0.16;
							  	DecimalFormat df = new DecimalFormat("0.00");
							  	md.addRow(new Object[]{TablaVenta.getRowCount() + 1, WordUtils.capitalizeFully(Producto), df.format(Precio - IVA).replaceAll(",", "."), 
							  			Cantidades, df.format(IVA).replaceAll(",", "."), Total});
							  	lblVCliente.setText("Cliente: " + WordUtils.capitalizeFully(Cliente));
							  	
							  	contador += Total;
							  }
							  DecimalFormat df = new DecimalFormat("#,###.##");
							  lblVTotal.setText("Total: " + df.format(contador) + " BsS.");
							  
							
							  operacion.Desconectar();
						  }
						  catch(Exception x){
							  System.err.println(x);
						  }
				    }
			  }
			  public void insertUpdate(DocumentEvent e) {
				  if(txtBCodigo.getText().matches("[0-9]+")){
				    	BD operacion = new BD();
				    	DefaultTableModel md = (DefaultTableModel) TablaVenta.getModel();
				    	for (int i = md.getRowCount() - 1; i >= 0; i--) {
						    md.removeRow(i);
						}
						  operacion.Conectar("Electro_Auto");
						  
						  try{
							  String selectSQL = "";
							  PreparedStatement preparedStatement = null;
							  
									 selectSQL = "SELECT Cliente, Producto, Unidades, Total FROM Ventas WHERE N_Venta = ?";
									 preparedStatement = operacion.conn.prepareStatement(selectSQL);
									  preparedStatement.setInt(1, Integer.parseInt(txtBCodigo.getText()));
								  
							  Double contador = 0.00;
							  ResultSet rs = preparedStatement.executeQuery();
							  while (rs.next()) {
							  	String Producto = rs.getString("Producto");
							  	String Cliente = rs.getString("Cliente");
							  	int Cantidades = rs.getInt("Unidades");
							  	Double Total = rs.getDouble("Total");
							  	Double Precio = Total / Cantidades;
							  	Double IVA = Precio * 0.16;
							  	DecimalFormat df = new DecimalFormat("0.00");
							  	md.addRow(new Object[]{TablaVenta.getRowCount() + 1, WordUtils.capitalizeFully(Producto), df.format(Precio - IVA).replaceAll(",", "."), 
							  			Cantidades, df.format(IVA).replaceAll(",", "."), Total});
							  	lblVCliente.setText("Cliente: " + WordUtils.capitalizeFully(Cliente));
							  	
							  	contador += Total;
							  }
							  DecimalFormat df = new DecimalFormat("#,###.##");
							  lblVTotal.setText("Total: " + df.format(contador) + " BsS.");
							  
							
							  operacion.Desconectar();
						  }
						  catch(Exception x){
							  System.err.println(x);
						  }
				    }
			  }

			 
			  
			});
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(12, 42, 911, 321);
		panel_5.add(scrollPane_3);
		
		TablaVenta = new JTable();
		TablaVenta.setFillsViewportHeight(true);
		TablaVenta.setRowSelectionAllowed(false);
		TablaVenta.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"N\u00B0", "Nombre", "P.U", "Cantidades", "IVA", "Total"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		TablaVenta.setBackground(new Color(255, 255, 255));
		scrollPane_3.setViewportView(TablaVenta);
		
		TablaVenta.setShowHorizontalLines(true);
		TablaVenta.setGridColor(new Color(255, 255, 255));
		TablaVenta.setForeground(new Color(128, 0, 0));
		TablaVenta.setFont(new Font("SansSerif", Font.BOLD, 12));
		TablaVenta.setBorder(new MatteBorder(1, 0, 1, 0, (Color) Color.WHITE));
		TablaVenta.setBackground(Color.WHITE);
		
		
		TablaVenta.setBackground(new Color(255, 255, 255));
		TablaVenta.setShowHorizontalLines(true);
		TablaVenta.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		TablaVenta.setGridColor(Color.WHITE);
		
		headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(Color.WHITE);
		headerRenderer.setForeground(new Color(178, 34, 34));
		headerRenderer.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 0; i < 		TablaVenta.getModel().getColumnCount(); i++) {
			TablaVenta.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		}
		
		 centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			TablaVenta.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );   
			TablaVenta.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
			TablaVenta.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
			TablaVenta.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
			TablaVenta.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
			
		
		
		JButton btnImprimirVenta = new JButton("");
		btnImprimirVenta.setFocusPainted(false);
		btnImprimirVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre = "", RIF = "", telefono = "";
				String search = lblVCliente.getText().toLowerCase();
				
				if(TablaVenta.getRowCount() >= 1){
					BD op = new BD();
					op.Conectar("Electro_Auto");
					
					try{
						  String selectSQL = "SELECT Nombre, RIF, Telefono FROM Clientes WHERE Nombre = ?";
						  PreparedStatement preparedStatement = op.conn.prepareStatement(selectSQL);
						  preparedStatement.setString(1, search.substring(9));
						
						  ResultSet rs = preparedStatement.executeQuery();
						  while (rs.next()) {
						  	nombre = rs.getString("Nombre");
						  	RIF = rs.getString("RIF");
						  	telefono = rs.getString("Telefono");
						  
						  
						  	
						  }
						 
						  
					}
					catch(Exception ayy){
							  System.err.println(ayy);
						  }
					
					
					 Document document = new Document();
					    // step 2
					    try {
							PdfWriter.getInstance(document, new FileOutputStream("C://VENTA_"+ txtBCodigo.getText() +".pdf"));
						} catch (FileNotFoundException wy) {
							// TODO Auto-generated catch block
							wy.printStackTrace();
						} catch (DocumentException wy) {
							// TODO Auto-generated catch block
							wy.printStackTrace();
						}
					    // step 3
					    document.open();
					    // step 4
					    
					    LocalDateTime ldt = LocalDateTime.now();
					    
					    Double contadortotal = 0.0;
					    
					    Paragraph parrafo2 = new Paragraph("Electro Auto Carrillo FP");
					    Paragraph parrafo3 = new Paragraph("N° Venta: 00" + txtBCodigo.getText() + "\n\n");
					    Paragraph Cliente = new Paragraph("Cliente / Razón Social: " + WordUtils.capitalizeFully(nombre) + "\n");
					    Paragraph rif = new Paragraph("RIF: " + RIF.toUpperCase() + "\n");
					    Paragraph Numero = new Paragraph("Número Teléfonico: " + telefono + "\n\n");
					   
					    
					    Paragraph paragraph = new Paragraph("DETALLES DE VENTA - " + DateTimeFormatter.ofPattern("dd-MM-yyyy - hh:mm a", Locale.ENGLISH).format(ldt) + "\n\n");
					    paragraph.setAlignment(1);
					   
					    parrafo2.setAlignment(1);
					    parrafo3.setAlignment(1);
					    
					    Cliente.setAlignment(2);
					    rif.setAlignment(2);
					    Numero.setAlignment(2);
					    
					    Cliente.setIndentationRight(60);
					    rif.setIndentationRight(60);
					    Numero.setIndentationRight(60);
					    
					    try {
					    	
					    	document.add(parrafo2);
					    	document.add(parrafo3);
					    	
					    	 if(!nombre.trim().equalsIgnoreCase("")){
					    		 document.add(Cliente);
					    		
							    }
							    if(!RIF.trim().equalsIgnoreCase("")){
							    	document.add(rif);
							    	
							    }
							    if(!telefono.trim().equalsIgnoreCase("")){
							    	document.add(Numero);
							    }
					    	
							document.add(paragraph);
						} catch (DocumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					    

					PdfPTable tabla = new PdfPTable(6);
					
					com.itextpdf.text.Font f1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);
					f1.setColor(BaseColor.WHITE);
					
					com.itextpdf.text.Font f2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);
					f2.setColor(BaseColor.BLACK);
					
					PdfPCell cell = new PdfPCell(new Phrase("N°", f1));
					cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					BaseColor myColor = WebColors.getRGBColor("#a20000");
					BaseColor blanco = WebColors.getRGBColor("#FFFFFF");
					cell.setBorder(Rectangle.BOTTOM);
					cell.setBackgroundColor(myColor);
					
					
					
					tabla.addCell(cell);
					cell = new PdfPCell(new Phrase("Producto", f1));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					myColor = WebColors.getRGBColor("#a20000");
					cell.setBorder(Rectangle.BOTTOM);
					cell.setBackgroundColor(myColor);
					
					tabla.addCell(cell);
					cell = new PdfPCell(new Phrase("P.U", f1));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					myColor = WebColors.getRGBColor("#a20000");
					cell.setBorder(Rectangle.BOTTOM);
					cell.setBackgroundColor(myColor);
					
					tabla.addCell(cell);
					cell = new PdfPCell(new Phrase("Unidades", f1));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					myColor = WebColors.getRGBColor("#a20000");
					cell.setBorder(Rectangle.BOTTOM);
					cell.setBackgroundColor(myColor);
					
					tabla.addCell(cell);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					
					cell = new PdfPCell(new Phrase("IVA", f1));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					myColor = WebColors.getRGBColor("#a20000");
					cell.setBorder(Rectangle.BOTTOM);
					cell.setBackgroundColor(myColor);
					tabla.addCell(cell);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					
					cell = new PdfPCell(new Phrase("Total", f1));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					myColor = WebColors.getRGBColor("#a20000");
					cell.setBorder(Rectangle.BOTTOM);
					cell.setBackgroundColor(myColor);
					tabla.addCell(cell);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					
		
					
					
					
					
					
					
					
					   for(int aw=0;aw<TablaVenta.getRowCount() ; aw++){
						   cell = new PdfPCell(new Phrase(TablaVenta.getValueAt(aw, 0).toString(), f2));
						   cell.setBorder(Rectangle.BOTTOM);
						   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						   tabla.addCell(cell);
						   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						   cell = new PdfPCell(new Phrase(TablaVenta.getValueAt(aw, 1).toString(), f2));
						   cell.setBorder(Rectangle.BOTTOM);
						   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						   tabla.addCell(cell);
						   cell = new PdfPCell(new Phrase(TablaVenta.getValueAt(aw, 2).toString(), f2));
						   cell.setBorder(Rectangle.BOTTOM);
						   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						   tabla.addCell(cell);
						   DecimalFormat format = new DecimalFormat("0.##");
						   cell = new PdfPCell(new Phrase(format.format(Double.parseDouble(TablaVenta.getValueAt(aw, 3).toString())), f2));
						   cell.setBorder(Rectangle.BOTTOM);
						   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						   tabla.addCell(cell);
						   cell = new PdfPCell(new Phrase(TablaVenta.getValueAt(aw, 4).toString(), f2));
						   cell.setBorder(Rectangle.BOTTOM);
						   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						   tabla.addCell(cell);
						   cell = new PdfPCell(new Phrase(TablaVenta.getValueAt(aw, 5).toString(), f2));
						   cell.setBorder(Rectangle.BOTTOM);
						   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						   tabla.addCell(cell);
						   
						   
						   
						   contadortotal += Double.parseDouble(TablaVenta.getValueAt(aw, 5).toString());
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
							Desktop.getDesktop().open(new File("C://VENTA_"+ txtBCodigo.getText() +".pdf"));
						} catch (DocumentException ay) {
							// TODO Auto-generated catch block
							ay.printStackTrace();
						} catch (IOException ay) {
							// TODO Auto-generated catch block
							ay.printStackTrace();
						} 
					    // step 6
					    document.close();
					
				}
				
				
				
			}
		});
		btnImprimirVenta.setIcon(new ImageIcon(Ventas.class.getResource("/imagenes/Printer.png")));
		btnImprimirVenta.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(128, 0, 0)));
		btnImprimirVenta.setBackground(new Color(255, 0, 0));
		btnImprimirVenta.setBounds(864, 5, 47, 31);
		panel_5.add(btnImprimirVenta);
		
		
		lblVTotal.setForeground(new Color(128, 0, 0));
		lblVTotal.setFont(new Font("Dialog", Font.BOLD, 18));
		lblVTotal.setBounds(575, 10, 271, 20);
		panel_5.add(lblVTotal);
		
		
		lblVCliente.setForeground(new Color(128, 0, 0));
		lblVCliente.setFont(new Font("Dialog", Font.BOLD, 18));
		lblVCliente.setBounds(170, 12, 271, 20);
		panel_5.add(lblVCliente);
		tabbedPane.setForegroundAt(3, new Color(255, 255, 255));
		
		 BD conexion = new BD();
		  Statement stmt = null;
		
		  conexion.Conectar("Electro_Auto");
		    try{
		    	
		    	String selectSQL = "SELECT Nombre FROM Clientes";
				  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
				  ResultSet rs = preparedStatement.executeQuery(selectSQL);
				  comboBoxClientes.setModel(new DefaultComboBoxModel(new String[] {"Seleccione"}));
				  
				 
				  while (rs.next()) {
					  comboBoxClientes.addItem(WordUtils.capitalizeFully(rs.getString("Nombre")));
					 
				  }
				  selectSQL = "SELECT Nombre FROM Repuestos";
				  preparedStatement = conexion.conn.prepareStatement(selectSQL);
				  rs = preparedStatement.executeQuery(selectSQL);
				  comboBoxProductos.setModel(new DefaultComboBoxModel(new String[] {"Seleccione"}));
				  
				  JButton btnBorrar = new JButton("");
				  btnBorrar.setFocusPainted(false);
				  btnBorrar.addActionListener(new ActionListener() {
				  	public void actionPerformed(ActionEvent e) {
				  		txtIdentidad.setText("");
				  		Total = 0.00;
				  		comboBoxProductos.setSelectedItem("Seleccione");
				  		comboBoxClientes.setSelectedItem("Seleccione");
				  		txtRIF.setText("");
				  		txtACodigo.setText("");
				  		txtAUnidades.setText("");
				  		txtTelefono.setText("");
				  		lblTotalVenta.setText("Total: 0.00 BsS.");
				  		btnRegistrarVenta.setEnabled(false);
				  		
				  		BD open = new BD();
						
						open.Conectar("Electro_Auto");
						
						try{
							open.conn.setAutoCommit(false);
							PreparedStatement preparedStatement = null;
							String insertTableSQL = "UPDATE Repuestos SET Unidades = Unidades + ? WHERE Nombre = ?";
							preparedStatement = open.conn.prepareStatement(insertTableSQL);
							for(int i = 0; i < TablaNuevaVenta.getRowCount(); i++){
								preparedStatement.setInt(1, Integer.parseInt(TablaNuevaVenta.getValueAt(i, 3).toString()));
								preparedStatement.setString(2, TablaNuevaVenta.getValueAt(i, 1).toString().toLowerCase());
								preparedStatement.addBatch();
							}
							
							
							
						
					preparedStatement.executeBatch();
					open.conn.commit();
					open.Desconectar();
						}
						catch(Exception yo){
							yo.printStackTrace();
						}
				  		
				  		txtIdentidad.requestFocus();
				  		
				  		DefaultTableModel dm = (DefaultTableModel) TablaNuevaVenta.getModel();
						int rowCount = dm.getRowCount();
						//Remove rows one by one from the end of the table
						for (int i = rowCount - 1; i >= 0; i--) {
						    dm.removeRow(i);
						}
				  	}
				  });
				  btnBorrar.setIcon(new ImageIcon(Ventas.class.getResource("/imagenes/Eraser.png")));
				  btnBorrar.setToolTipText("Borra todos los campos");
				  btnBorrar.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
				  btnBorrar.setBackground(Color.WHITE);
				  btnBorrar.setBounds(226, 215, 52, 37);
				  panel_2.add(btnBorrar);
				  
				  JButton btnActualizarCliente = new JButton("<html><center>Actualizar <br />Cliente</center></html>");
				  btnActualizarCliente.addActionListener(new ActionListener() {
				  	public void actionPerformed(ActionEvent e) {
				  		
				  		if(txtIdentidad.getText().trim().equalsIgnoreCase("")){
							JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO CLIENTE ES OBLIGATORIO", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						if(!txtTelefono.getText().trim().equalsIgnoreCase("") && !txtTelefono.getText().trim().matches("[0-9-]+")){
							JOptionPane.showMessageDialog(null, "ERROR: EL CAMPO TELEFONO SOLO ACEPTA NUMEROS Y RAYAS (-)", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
							txtTelefono.requestFocus();
							txtTelefono.selectAll();
							return;
						}
						
						
						BD op = new BD();
						try {
							if(op.BuscarCliente(txtIdentidad.getText()) == true && !txtIdentidad.getText().equalsIgnoreCase(comboBoxClientes.getSelectedItem().toString())){
								JOptionPane.showMessageDialog(null, "ERROR: ESTE CLIENTE YA EXISTE DENTRO DEL LISTADO!", "Electro Auto Carrillo - Error", JOptionPane.ERROR_MESSAGE);
								txtIdentidad.requestFocus();
								txtIdentidad.selectAll();
								return;
							}
							
								op.ActualizarCliente(txtIdentidad.getText(), txtRIF.getText(), txtTelefono.getText(), comboBoxClientes.getSelectedItem().toString());
								JOptionPane.showMessageDialog(null, "CLIENTE ACTUALIZADO!", "Electro Auto Carrillo", JOptionPane.INFORMATION_MESSAGE);
								
							
							
						} catch (NumberFormatException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						
						
						
						 BD conexion = new BD();
						  Statement stmt = null;
						
						  conexion.Conectar("Electro_Auto");
						    try{
						    	
						    	String selectSQL = "SELECT Nombre FROM Clientes";
								  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
								  ResultSet rs = preparedStatement.executeQuery(selectSQL);
								  comboBoxClientes.setModel(new DefaultComboBoxModel(new String[] {"Seleccione"}));
								  
								 
								  while (rs.next()) {
									  comboBoxClientes.addItem(WordUtils.capitalizeFully(rs.getString("Nombre")));
									 
								  }
						    }
								  catch(Exception ayo){
									  System.err.println(ayo);
								  }
						    
						   
						    
						    	
				  		
				  	}
				  });
				  btnActualizarCliente.setForeground(Color.BLACK);
				  btnActualizarCliente.setFocusPainted(false);
				  btnActualizarCliente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 191, 255)));
				  btnActualizarCliente.setBackground(new Color(135, 206, 235));
				  btnActualizarCliente.setBounds(288, 111, 152, 37);
				  panel_2.add(btnActualizarCliente);
				  
				 
				  while (rs.next()) {
					  comboBoxProductos.addItem(WordUtils.capitalizeFully(rs.getString("Nombre")));
					 
				  }
		    }
				  catch(Exception ayo){
					  System.err.println(ayo);
				  }
		    
		    try{
		    	
		    	String selectSQL = "SELECT Nombre, Unidades FROM Repuestos WHERE Unidades < 25";
				  PreparedStatement preparedStatement = conexion.conn.prepareStatement(selectSQL);
				  ResultSet rs = preparedStatement.executeQuery(selectSQL);
				  
				  
				 
				  while (rs.next()) {
					 ht.put(WordUtils.capitalizeFully(rs.getString("Nombre")), rs.getInt("Unidades"));
					 
				  }
				  
				  if(ht.isEmpty() == false){
					  btnAlertas.setEnabled(true);
				  }
		    }
				  catch(Exception ayo){
					  System.err.println(ayo);
				  }
		    
		     
		    
		    try{
		    	conexion.Conectar("Electro_Auto");
		    	String selectSQL = "SELECT N_Venta FROM ventas ORDER BY N_Venta DESC LIMIT 1";
		    	 Statement statement = conexion.conn.createStatement();
				   ResultSet rs = statement.executeQuery(selectSQL);
				  
				  
				 int busqueda = 0;
				  while (rs.next()) {
					 busqueda = rs.getInt("N_Venta");
					 
				  }
				  NVenta = busqueda + 1;
				 
				  if(NVenta < 10) lblNVenta.setText("N° Venta: 0000" + NVenta);
					if(NVenta >= 10) lblNVenta.setText("N° Venta: 000" + NVenta);
					if(NVenta >= 100) lblNVenta.setText("N° Venta: 00" + NVenta);
					if(NVenta >= 1000) lblNVenta.setText("N° Venta: 0" + NVenta); 
					if(NVenta >= 10000) lblNVenta.setText("N° Venta: " + NVenta); 
		    }
				  catch(Exception ayo){
					  System.err.println(ayo);
				  }
		   
		    BD op = new BD();
		    op.Conectar("Electro_Auto");
			try{
				  String selectSQL = "SELECT Rango FROM Usuarios WHERE Cedula = ?";
				  PreparedStatement preparedStatement = op.conn.prepareStatement(selectSQL);
				  preparedStatement.setInt(1, Acceso.Cedula);
				 
				  ResultSet rs = preparedStatement.executeQuery();
				  while (rs.next()) {
				  	Acceso.Cargo = rs.getString("Rango");
				  	
				  	
				  }
				 
				  
			}
			catch(Exception ayy){
					  System.err.println(ayy);
				  }
		
		VentanaVentas.setVisible(true);
		
		 
		
		txtIdentidad.requestFocus();
		
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
