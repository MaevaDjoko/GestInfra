package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connexion.connexionDB;
import model.commande;


public class gestionCommandeDAO {

	public static int ajouterCommande(commande com) throws SQLException {
		Connection con=connexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		
		try {
			String sql="INSERT INTO commande (numero,nclient,produitcom,punitaire,quant,ptotal,date) VALUES(?,?,?,?,?,?,?)";
			stat=con.prepareStatement(sql);
			stat.setInt(1, com.getNumcommande());
			stat.setString(2, com.getNomClient());
			stat.setString(3, com.getProduit());
			stat.setDouble(4, com.getPrixunitaire());
			stat.setInt(5, com.getQuantite());
			stat.setDouble(6, com.getPrixtotal());
			stat.setString(7, com.getDate());
		
			valeur=stat.executeUpdate();
			con.close();
			
			
		} catch(SQLException e) {
			
		}
		
		return valeur;
		
		
	}
	
	public static int supprimerCommande(commande com) throws SQLException {
		Connection con=connexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		
		try {
			String sql="DELETE from commande WHERE numero=?";
			stat=con.prepareStatement(sql);
			stat.setInt(1, com.getNumcommande());
			
			
			valeur=stat.executeUpdate();
			con.close();
			
			
		} catch(SQLException e) {
			
		}
		
		return valeur;
	}
	

	public static int modifierCommande(commande com) throws SQLException {
		Connection con=connexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		
		try {
			String sql="UPDATE commande set numero=?,nclient=?,produitcom=?,punitaire=?,quant=?,ptotal=?,date=? WHERE numero=?";
			stat=con.prepareStatement(sql);
			stat.setInt(1, com.getNumcommande());
			stat.setString(2, com.getNomClient());
			stat.setString(3, com.getProduit());
			stat.setDouble(4, com.getPrixunitaire());
			stat.setInt(5, com.getQuantite());
			stat.setDouble(6, com.getPrixtotal());
			stat.setString(7, com.getDate());
			stat.setInt(8, com.getNumcommande());
			
			valeur=stat.executeUpdate();
			con.close();
			
		} catch(SQLException e) {
			
		}
		
		return valeur;
	}
		
	
	public static commande rechercheCommande(int num) throws SQLException {
		Connection con=connexionDB.connect();
		PreparedStatement stat=null;
		commande c= new commande();
		ResultSet rs=null;
		
		try {
			String sql="Select * from commande WHERE numero=?";
			stat=con.prepareStatement(sql);
			stat.setInt(1, num);			
						
		rs=stat.executeQuery();
		con.close();
		c.setNumcommande(rs.getInt(1));
		c.setNomClient(rs.getString(2));
		c.setProduit(rs.getString(3));
		c.setPrixunitaire(rs.getDouble(4));
		c.setQuantite(rs.getInt(5));
		c.setPrixtotal(rs.getDouble(6));
		c.setDate(rs.getString(7));
		
		
		} catch(SQLException e) {
			
		}
		
		
		return null;
		
		
	}
	
	
	
	
	
	
	
	
}
