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
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

public class Alertas {
	private static JTable table;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame VentanaAlertas = new JFrame("Electro Auto Carrillo - Acceso");
		VentanaAlertas.setTitle("Electro Auto Carrillo - Articulos escasos");
		VentanaAlertas.getContentPane().setBackground(new Color(0, 153, 255));
		VentanaAlertas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		VentanaAlertas.setResizable(false);
		VentanaAlertas.setSize(500, 500);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		VentanaAlertas.setLocation(dim.width/2-VentanaAlertas.getSize().width/2, dim.height/2-VentanaAlertas.getSize().height/2);
		VentanaAlertas.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(6, 47, 482, 385);
		VentanaAlertas.getContentPane().add(scrollPane);
		
		scrollPane.getViewport().setBackground(Color.WHITE);
		
		table = new JTable();
		table.setShowVerticalLines(true);
		table.setGridColor(new Color(0, 153, 255));
		table.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(255, 255, 255)));
		
		table.setForeground(new Color(0, 153, 255));
		table.setBackground(Color.WHITE);
		table.setShowHorizontalLines(true);
		table.setFont(new Font("SansSerif", Font.BOLD, 12));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"<html><b>Producto</b></html>", "<html><b>Unidades</b></html>"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		scrollPane.setViewportView(table);
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
			Set<String> keys = Ventas.ht.keySet();
	        for(String key: keys){
	        	model.addRow(new Object[]{key, Ventas.ht.get(key)});
	        }
			
		
	        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
			headerRenderer.setBackground(Color.WHITE);
			headerRenderer.setForeground(new Color(30, 144, 255));
			headerRenderer.setHorizontalAlignment(JLabel.CENTER);

			for (int i = 0; i < table.getModel().getColumnCount(); i++) {
			        table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
			}
		
			
			 DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			    centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			    table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
			    table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
			   
			
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAlertas.dispose();
			}
		});
		btnSalir.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.RED);
		btnSalir.setBounds(163, 438, 167, 28);
		VentanaAlertas.getContentPane().add(btnSalir);
		
		JButton btnImprimir = new JButton("");
		btnImprimir.setIcon(new ImageIcon(Alertas.class.getResource("/imagenes/Printer.png")));
		btnImprimir.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnImprimir.setBackground(new Color(0, 153, 255));
		btnImprimir.setBounds(449, 7, 39, 36);
		VentanaAlertas.getContentPane().add(btnImprimir);
		
		VentanaAlertas.setVisible(true);

	}
}
