package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connexion.connexionDB;
import model.client;


public class gestionClientDAO {

	public static int ajouterClient(client c) throws SQLException {
		Connection con=connexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		
		try {
			String sql="INSERT INTO client(id,nom,prenom,cni,ville,tel) VALUES(?,?,?,?,?,?)";
			stat=con.prepareStatement(sql);
			stat.setInt(1, c.getIdClient());
			stat.setString(2, c.getNom());
			stat.setString(3, c.getPrenom());
			stat.setString(4, c.getCNI());
			stat.setString(5, c.getVille());
			stat.setString(6, c.getTel());
			
			valeur=stat.executeUpdate();
			con.close();
			
			
		} catch(SQLException e) {
			
		}
		
		return valeur;
		
		
	}
	
	public static int modifierClient(client c) throws SQLException {
		Connection con=connexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		
		try {
			String sql="UPDATE client set id=?,nom=?,prenom=?,cni=?,ville=?,tel=? WHERE id=?";
			stat=con.prepareStatement(sql);
			stat.setInt(1, c.getIdClient());
			stat.setString(2, c.getNom());
			stat.setString(3, c.getPrenom());
			stat.setString(4, c.getCNI());
			stat.setString(5, c.getVille());
			stat.setString(6, c.getTel());
			stat.setInt(7, c.getIdClient());
			
			valeur=stat.executeUpdate();
			con.close();
			
			
		} catch(SQLException e) {
			
		}
		
		return valeur;
	}
	
	public static int supprimerClient(client c) throws SQLException {
		Connection con=connexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		
		try {
			String sql="DELETE from client WHERE id=?";
			stat=con.prepareStatement(sql);
			stat.setInt(1, c.getIdClient());
			
			
			valeur=stat.executeUpdate();
			con.close();
			
			
		} catch(SQLException e) {
			
		}
		
		return valeur;
	}
	
	public static client rechercheClient(int idt) throws SQLException {
		Connection con=connexionDB.connect();
		PreparedStatement stat=null;
		client c= new client();
		ResultSet rs=null;
		
		try {
			String sql="Select * from client WHERE id=?";
			stat=con.prepareStatement(sql);
			stat.setInt(1, idt);			
						
		rs=stat.executeQuery();
		con.close();
		c.setIdClient(rs.getInt(1));
		c.setNom(rs.getString(2));
		c.setPrenom(rs.getString(3));
		c.setCNI(rs.getString(4));
		c.setVille(rs.getString(5));
		c.setTel(rs.getString(6));
		
		
		} catch(SQLException e) {
			
		}
		
		
		return null;
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
