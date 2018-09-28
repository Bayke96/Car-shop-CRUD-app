package pantallas;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.apache.commons.lang3.text.WordUtils;


public class BD {
	
	public String Usuario = "root";
	public String Contraseña = "";
	public Connection conn = null;
	
	
	public void Conectar(String BD){
		
		   
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      //STEP 3: Open a connection
		      
		      conn = DriverManager.getConnection("jdbc:mysql://localhost/" + BD, Usuario, Contraseña);
		      
		   }
		   catch(Exception e){
			   System.err.println(e);
		   }
	}
	
	public void Desconectar(){
		try {
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	}
	
	
	
public BD(){
		
		String createDB = "CREATE DATABASE IF NOT EXISTS Electro_Auto";
		PreparedStatement preparedStatement = null;
		
		
		
		 try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      //STEP 3: Open a connection
		      
		      conn = DriverManager.getConnection("jdbc:mysql://localhost", Usuario, Contraseña);
		      preparedStatement = conn.prepareStatement(createDB);

		      preparedStatement.executeUpdate();
		      
		      Conectar("Electro_Auto");
		 	 String sqlCreate = "CREATE TABLE IF NOT EXISTS Usuarios" 
		      + "  (ID INTEGER NOT NULL AUTO_INCREMENT,"
		      + "   Cedula            INTEGER NOT NULL UNIQUE,"
		      + "   Contraseña          VARCHAR(256) NOT NULL,"
		      + "   Rango          VARCHAR(96) NOT NULL,"
		      + " PRIMARY KEY ( ID ))";

		 	 Statement stmt = conn.createStatement();
		 	 stmt.execute(sqlCreate);
		 	 
		 	sqlCreate = "CREATE TABLE IF NOT EXISTS Repuestos" 
		 		     + "  (Codigo INTEGER NOT NULL AUTO_INCREMENT,"
		 		     + "   Nombre            VARCHAR(128) NOT NULL UNIQUE,"
		 		     + "   Descripcion          VARCHAR(256) NOT NULL,"
		 		     + "   Precio          DOUBLE NOT NULL,"
		 		     + "   IVA          Double NOT NULL,"
		 		     + "   Unidades          INTEGER NOT NULL,"
		 		     + "   Total          DOUBLE NOT NULL,"
		 		     + " PRIMARY KEY ( Codigo ))";

		 			 stmt = conn.createStatement();
		 			 stmt.execute(sqlCreate);
		 			 
		 			 
		 			sqlCreate = "CREATE TABLE IF NOT EXISTS Clientes" 
				 		     + "  (ID INTEGER NOT NULL AUTO_INCREMENT,"
				 		     + "   Nombre            VARCHAR(128) NOT NULL UNIQUE,"
				 		     + "   RIF          VARCHAR(76) NULL,"
				 		    + "   Telefono          VARCHAR(50) NULL,"
				 		     + " PRIMARY KEY ( ID ))";

				 			 stmt = conn.createStatement();
				 			 stmt.execute(sqlCreate);
				 			 
				 			sqlCreate = "CREATE TABLE IF NOT EXISTS Ventas" 
						 		     + "  (ID INTEGER NOT NULL AUTO_INCREMENT,"
						 		     + "   N_Venta            INT NOT NULL,"
						 		     + "   Cliente            VARCHAR(128) NULL,"
						 		     + "   RIF          VARCHAR(75) NULL,"
						 		     + "   Producto            VARCHAR(128) NOT NULL,"
						 		     + "   Unidades            INTEGER NOT NULL,"
						 		     + "   Total          DOUBLE NOT NULL,"
						 		     + " PRIMARY KEY ( ID ))";

						 			 stmt = conn.createStatement();
						 			 stmt.execute(sqlCreate);
		 			 
		 			 
		      
		      Desconectar();
		   }
		   catch(Exception e){
			   System.err.println(e);
		   }
	      
	}



public boolean Acceso(String usuario, char[] contraseña){
	 try{
		boolean resultado = true;
	      //STEP 2: Register JDBC driver
		  ResultSet rs = null;
	      Conectar("Electro_Auto");
	      
	     
	      
	      
	     
	     
	      String query = "SELECT Cedula, Contraseña FROM Usuarios WHERE Cedula = ?";
		  PreparedStatement pstmt = conn.prepareStatement(query); // create a statement
		  pstmt.setString(1, usuario.toLowerCase()); // set input parameter
		  rs = pstmt.executeQuery();
		  String User = "", Pass = "", conversion = "";
		  while (rs.next()) {
		        User = rs.getString(1);
		        Pass = rs.getString(2);
		  }
		  for(int i = 0; i < contraseña.length; i++){
			  conversion += contraseña[i];
		  }
		  if(User.equalsIgnoreCase("")){
			  JOptionPane.showMessageDialog(null, "Número de cedula invalido", "ERROR", JOptionPane.ERROR_MESSAGE);
			  resultado = false;
			  return false;
		  }
		  String codigo = new String(contraseña);
		  
		  if(!Pass.equals(codigo)){
			  JOptionPane.showMessageDialog(null, "Contraseña invalida", "ERROR", JOptionPane.ERROR_MESSAGE);
			  resultado = false;
			  return false;
		  }
		 
		  
		
	      Desconectar();
	      
	      
	      
	     
	      Ventas.main(null);
	      
	      return resultado;
		  
	   }
	   catch(Exception e){
		   System.err.println(e);
		   return false;
	   }
	 finally{
		 Desconectar();
	 }
    
}

public String Rango(int cedula){
	String Cargo = "";
	 try{
		
	      //STEP 2: Register JDBC driver
		  ResultSet rs = null;
	      Conectar("Electro_Auto");
	      
	     
	      
	      
	     
	     
	      String query = "SELECT Rango FROM Usuarios WHERE Cedula = ?";
		  PreparedStatement pstmt = conn.prepareStatement(query); // create a statement
		  pstmt.setInt(1, cedula); // set input parameter
		  rs = pstmt.executeQuery();
		 
		  while (rs.next()) {
		       Cargo = rs.getString("Cargo");
		  }
		  
		 
		  
		
	      Desconectar();
	      
	      
	      
	     
	    
	      
	      return Cargo;
		  
	   }
	   catch(Exception e){
		   System.err.println(e);
		  
	   }
	 finally{
		 Desconectar();
	 }
   return Cargo;
}


public void CrearUsuario(int cedula, String contraseña, String rango) throws SQLException{

	
	String mensaje = "";
	Conectar("Electro_Auto");
	 String sqlCreate = "CREATE TABLE IF NOT EXISTS Usuarios" 
     + "  (ID INTEGER NOT NULL AUTO_INCREMENT,"
     + "   Cedula            INTEGER NOT NULL UNIQUE,"
     + "   Contraseña          VARCHAR(256) NOT NULL,"
     + "   SALT          VARCHAR(256) NOT NULL,"
     + "   Rango          VARCHAR(96) NOT NULL,"
     + " PRIMARY KEY ( ID ))";

	 Statement stmt = conn.createStatement();
	 stmt.execute(sqlCreate);
	
	
	 
	 Conectar("Electro_Auto");
	 
	 
	 String insertTableSQL = "INSERT INTO Usuarios"
				+ "(Cedula, Contraseña, Rango) VALUES"
				+ "(?, ?, ?)";
	PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
	preparedStatement.setInt(1, cedula);
	preparedStatement.setString(2, contraseña);
	preparedStatement.setString(3, rango);
	
	preparedStatement.executeUpdate();
	Desconectar();
	
}

public boolean BuscarUsuario(String nombre) throws SQLException{
	String resultado = "";
	boolean encontrado = false;
	Conectar("Electro_Auto");
	try{
		  String selectSQL = "SELECT Cedula FROM Usuarios WHERE Cedula = ?";
		  PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setString(1, nombre.toLowerCase());
		  ResultSet rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	resultado = rs.getString("Cedula");
		  }
		  
	}
	catch(Exception e){
			  System.err.println(e);
		  }
	Desconectar();
	if(resultado == "") encontrado = false;
	if(resultado != "") encontrado = true;
    return encontrado;
}

public void ActualizarUsuario(int cedula, int ncedula, String rango) throws SQLException{
	Conectar("Electro_Auto");
	
	
	
	String insertTableSQL = "";
	PreparedStatement preparedStatement = null;
	
		 insertTableSQL = "UPDATE Usuarios SET Cedula = ?, Rango = ? WHERE Cedula = ?";
			preparedStatement = conn.prepareStatement(insertTableSQL);
			preparedStatement.setInt(1, ncedula);
			preparedStatement.setString(2, rango);
			preparedStatement.setInt(3, cedula);
	
	
		
	preparedStatement.executeUpdate();
	Desconectar();
	
	
}

public boolean VerificarContraseña(char[] password){
	String resultado = "";
	boolean encontrado = false;
	Conectar("Electro_Auto");
	try{
		  String selectSQL = "SELECT Contraseña FROM Usuarios WHERE Cedula = ?";
		  PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setInt(1, Acceso.Cedula);
		  ResultSet rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	resultado = rs.getString("Contraseña");
		  }
		  
	}
	catch(Exception e){
			  System.err.println(e);
		  }
	Desconectar();
	if(!resultado.equals(String.valueOf(password))) encontrado = false;
	if(resultado.equals(String.valueOf(password))) encontrado = true;
    return encontrado;
}

public void ActualizarContraseña(char[] password) throws SQLException{
	Conectar("Electro_Auto");
	
	
	
	String insertTableSQL = "";
	PreparedStatement preparedStatement = null;
	
		 insertTableSQL = "UPDATE Usuarios SET Contraseña = ? WHERE Cedula = ?";
			preparedStatement = conn.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, String.valueOf(password));
			preparedStatement.setInt(2, Acceso.Cedula);
			
	
	
		
	preparedStatement.executeUpdate();
	Desconectar();
	
	
}

public void EliminarUsuario(int cedula) throws SQLException{
	Conectar("Electro_Auto");
	
	 
	 String insertTableSQL = "DELETE FROM Usuarios where Cedula = ?";
	PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
	preparedStatement.setInt(1, cedula);
	preparedStatement .executeUpdate();
	Desconectar();
}

// METODOS DE MATERIALES // 



public void CrearRepuesto(String nombre, String descripcion, double Precio, int unidades) throws SQLException{
	String mensaje = "";
	Conectar("Electro_Auto");
	 String sqlCreate = "CREATE TABLE IF NOT EXISTS Repuestos" 
     + "  (Codigo INTEGER NOT NULL AUTO_INCREMENT,"
     + "   Nombre            VARCHAR(128) NOT NULL UNIQUE,"
     + "   Descripcion          VARCHAR(256) NOT NULL,"
     + "   Precio          DOUBLE NOT NULL,"
     + "   IVA          Double NOT NULL,"
     + "   Unidades          INTEGER NOT NULL,"
     + "   Total          DOUBLE NOT NULL,"
     + " PRIMARY KEY ( Codigo ))";

	 Statement stmt = conn.createStatement();
	 stmt.execute(sqlCreate);
	
	
	 
	 Conectar("Electro_Auto");
	 
	 
	 String insertTableSQL = "INSERT INTO Repuestos"
				+ "(Nombre, Descripcion, Precio, IVA, Unidades, Total) VALUES"
				+ "(?, ?, ?, ?, ?, ?)";
	PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
	preparedStatement.setString(1, nombre.toLowerCase());
	preparedStatement.setString(2, descripcion.toLowerCase());
	DecimalFormat df = new DecimalFormat("#.##");
	
	preparedStatement.setDouble(3, Precio);
	preparedStatement.setDouble(4, Double.parseDouble(df.format(Precio * 0.16).replaceAll(",", ".")));
	preparedStatement.setInt(5, unidades);
	double IVA = Precio * 0.16;
	double Total = (Precio + IVA) * unidades;
	preparedStatement.setDouble(6, Double.parseDouble(df.format(Total).replaceAll(",", ".")));
	preparedStatement .executeUpdate();
	Desconectar();
	
}

public boolean BuscarRepuesto(String nombre) throws SQLException{
	String resultado = "";
	boolean encontrado = false;
	Conectar("Electro_Auto");
	try{
		  String selectSQL = "SELECT Nombre FROM Repuestos WHERE Nombre = ?";
		  PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setString(1, nombre.toLowerCase());
		  ResultSet rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	resultado = rs.getString("Nombre");
		  }
		  
	}
	catch(Exception e){
			  System.err.println(e);
		  }
	Desconectar();
	if(resultado == "") encontrado = false;
	if(resultado != "") encontrado = true;
    return encontrado;
}

public boolean BuscarRepuestoCod(int cod) throws SQLException{
	String resultado = "";
	boolean encontrado = false;
	Conectar("Electro_Auto");
	try{
		  String selectSQL = "SELECT Nombre FROM Repuestos WHERE Codigo = ?";
		  PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setInt(1, cod);
		  ResultSet rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	resultado = rs.getString("Nombre");
		  }
		  
	}
	catch(Exception e){
			  System.err.println(e);
		  }
	Desconectar();
	if(resultado == "") encontrado = false;
	if(resultado != "") encontrado = true;
    return encontrado;
}



public void ActualizarRepuesto(String nombre, String nuevonombre, String descripcion, double Precio, int unidades) throws SQLException{
	Conectar("Electro_Auto");
	
	
	
	String insertTableSQL = "";
	PreparedStatement preparedStatement = null;
	
		 insertTableSQL = "UPDATE Repuestos SET Nombre = ?, Descripcion = ?, Precio = ?, IVA = ?, Unidades = ?, Total = ? WHERE Nombre = ?";
			preparedStatement = conn.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, nuevonombre.toLowerCase());
			preparedStatement.setString(2, descripcion.toLowerCase());
			DecimalFormat df = new DecimalFormat("#.##");
			
			preparedStatement.setDouble(3, Precio);
			preparedStatement.setDouble(4, Double.parseDouble(df.format(Precio * 0.16).replaceAll(",", ".")));
			preparedStatement.setInt(5, unidades);
			double IVA = Precio * 0.16;
			double Total = (Precio + IVA) * unidades;
			preparedStatement.setDouble(6, Double.parseDouble(df.format(Total).replaceAll(",", ".")));
			preparedStatement.setString(7, nombre.toLowerCase());
		
	preparedStatement.executeUpdate();
	Desconectar();
	
	
}

public void EliminarRepuesto(String nombre) throws SQLException{
	Conectar("Electro_Auto");
	
	 
	 String insertTableSQL = "DELETE FROM Repuestos where Nombre = ?";
	PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
	preparedStatement.setString(1, nombre.toLowerCase());
	preparedStatement .executeUpdate();
	Desconectar();
}
	

// CLIENTES

public void RegistrarCliente(String nombre, String RIF, String Telefono) throws SQLException{
	String mensaje = "";
	Conectar("Electro_Auto");
	
	 
	 String insertTableSQL = "INSERT INTO Clientes"
				+ "(Nombre, RIF, Telefono) VALUES"
				+ "(?, ?, ?)";
	PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
	preparedStatement.setString(1, nombre.toLowerCase());
	preparedStatement.setString(2, RIF.toUpperCase());
	preparedStatement.setString(3, Telefono);
	preparedStatement .executeUpdate();
	Desconectar();
	
}

public boolean BuscarCliente(String nombre) throws SQLException{
	String resultado = "";
	boolean encontrado = false;
	Conectar("Electro_Auto");
	try{
		  String selectSQL = "SELECT Nombre FROM Clientes WHERE Nombre = ?";
		  PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setString(1, nombre.toLowerCase());
		  ResultSet rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	resultado = rs.getString("Nombre");
		  }
		  
	}
	catch(Exception e){
			  System.err.println(e);
		  }
	Desconectar();
	if(resultado == "") encontrado = false;
	if(resultado != "") encontrado = true;
    return encontrado;
}

public void ActualizarCliente(String nnombre, String RIF, String telefono, String nombre) throws SQLException{
	Conectar("Electro_Auto");
	
	
	
	String insertTableSQL = "";
	PreparedStatement preparedStatement = null;
	
		 insertTableSQL = "UPDATE Clientes SET Nombre = ?, RIF = ?, Telefono = ? WHERE Nombre = ?";
			preparedStatement = conn.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, nnombre.toLowerCase());
			preparedStatement.setString(2, RIF.toUpperCase());
			preparedStatement.setString(3, telefono);
			preparedStatement.setString(4, nombre.toLowerCase());
	
	
		
	preparedStatement.executeUpdate();
	Desconectar();
	
	
}

public void RegistrarVenta(JTable tabla, String cliente, String rif) throws SQLException{
	String mensaje = "";
	Conectar("Electro_Auto");
	 
	conn.setAutoCommit(false);
	 
	 
	 
	 
	 String insertTableSQL = "INSERT INTO Ventas"
				+ "(N_Venta, Cliente, RIF, Producto, Unidades, Total) VALUES"
				+ "(?, ?, ?, ?, ?, ?)";
	PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
	for(int i = 0; i < tabla.getRowCount(); i++){
		preparedStatement.setInt(1, Ventas.NVenta);
		preparedStatement.setString(2, cliente.toLowerCase());
		preparedStatement.setString(3, rif.toLowerCase());
		preparedStatement.setString(4, tabla.getValueAt(i, 1).toString().toLowerCase());
		preparedStatement.setDouble(5, Double.parseDouble(tabla.getValueAt(i, 3).toString().replaceAll(",", ".")));
		preparedStatement.setDouble(6, Double.parseDouble(tabla.getValueAt(i, 5).toString().replaceAll(",", ".")));
		preparedStatement.addBatch();
	}
	
	
	
	preparedStatement.executeBatch();
	conn.commit();
	Desconectar();
	
}

public void ActualizarVenta(JTable tabla, int cod) throws SQLException{
	String mensaje = "";
	Conectar("Electro_Auto");
	 
	conn.setAutoCommit(false);
	 
	 
	 
	 
	 String insertTableSQL = "UPDATE Ventas SET Producto = ?, Unidades = ?, Total = ? WHERE N_Venta = ? AND Total = ?";
	PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
	for(int i = 0; i < tabla.getRowCount(); i++){
		preparedStatement.setString(1, tabla.getValueAt(i, 1).toString().toLowerCase());
		preparedStatement.setInt(2, Integer.parseInt(tabla.getValueAt(i, 3).toString()));
		
		preparedStatement.setDouble(3, Double.parseDouble(tabla.getValueAt(i, 5).toString().replaceAll(",", ".")));
		preparedStatement.setInt(4, cod);
		preparedStatement.setDouble(5, Double.parseDouble(tabla.getValueAt(i, 5).toString().replaceAll(",", ".")));
		preparedStatement.addBatch();
	}
	
	
	
	preparedStatement.executeBatch();
	conn.commit();
	Desconectar();
	
}

public void EliminarVenta(JTable tabla, int cod) throws SQLException{
	
	Conectar("Electro_Auto");
	 

	 
	 
	 
	 
	 String insertTableSQL = "DELETE FROM VENTAS WHERE N_VENTA = ?";
	PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
	
		preparedStatement.setInt(1, cod);
		
	
	
	
	
	preparedStatement.executeUpdate();
	
	Desconectar();
	
}
	

}



