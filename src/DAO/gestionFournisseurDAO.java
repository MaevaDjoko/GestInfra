package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connexion.connexionDB;
import model.fournisseur;


public class gestionFournisseurDAO {

	public static int ajouterFournisseur(fournisseur f) throws SQLException {
		Connection con=connexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		
		try {
			String sql="INSERT INTO fournisseur(id,nom,adresse,ville,email,categorieM) VALUES(?,?,?,?,?,?)";
			stat=con.prepareStatement(sql);
			stat.setInt(1, f.getIdentifiant());
			stat.setString(2, f.getNom());
			stat.setString(3, f.getAdresse());
			stat.setString(4, f.getVille());
			stat.setString(5, f.getEmail());
			stat.setString(6, f.getCategorieM());
			
			valeur=stat.executeUpdate();
			con.close();
			
			
		} catch(SQLException e) {
			
		}
		
		return valeur;
		
		
	}
	
	public static int modifierFournisseur(fournisseur f) throws SQLException {
		Connection con=connexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		
		try {
			String sql="UPDATE fournisseur set id=?,nom=?,adresse=?,ville=?,email=?,categorieM=? WHERE id=?";
			stat=con.prepareStatement(sql);
			stat.setInt(1, f.getIdentifiant());
			stat.setString(2, f.getNom());
			stat.setString(3, f.getAdresse());
			stat.setString(4, f.getVille());
			stat.setString(5, f.getEmail());
			stat.setString(6, f.getCategorieM());
			stat.setInt(7, f.getIdentifiant());
			
			valeur=stat.executeUpdate();
			con.close();
			
			
		} catch(SQLException e) {
			
		}
		
		return valeur;
	}
	
	
	public static int supprimerFournisseur(fournisseur f) throws SQLException {
		Connection con=connexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		
		try {
			String sql="DELETE from fournisseur WHERE id=?";
			stat=con.prepareStatement(sql);
			stat.setInt(1, f.getIdentifiant());
			
			
			valeur=stat.executeUpdate();
			con.close();
			
			
		} catch(SQLException e) {
			
		}
		
		return valeur;
	}
	
	

	public static fournisseur rechercheFournisseur(int identifiant) throws SQLException {
		Connection con=connexionDB.connect();
		PreparedStatement stat=null;
		fournisseur f= new fournisseur();
		ResultSet rs=null;
		
		try {
			String sql="Select * from fournisseur WHERE id=?";
			stat=con.prepareStatement(sql);
			stat.setInt(1, identifiant);			
						
		rs=stat.executeQuery();
		con.close();
		f.setIdentifiant(rs.getInt(1));
		f.setNom(rs.getString(2));
		f.setAdresse(rs.getString(3));
		f.setVille(rs.getString(4));
		f.setEmail(rs.getString(5));
		f.setCategorieM(rs.getString(6));
		
		
		} catch(SQLException e) {
			
		}
		
		
		return null;
		
		
	}


	
	
	
	
	
	
	
	
	
}
