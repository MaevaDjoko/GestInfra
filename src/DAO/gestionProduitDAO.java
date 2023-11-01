package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connexion.connexionDB;
import model.produit;

public class gestionProduitDAO {

	public static int ajouterProduit(produit p) throws SQLException {
		Connection con=connexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		
		try {
			String sql="INSERT INTO produit(id,nom,categoriep,prixunitaire,quantite,remise,prixvente) VALUES(?,?,?,?,?,?,?)";
			stat=con.prepareStatement(sql);
			stat.setInt(1, p.getIdentifiantp());
			stat.setString(2, p.getNom());
			stat.setString(3, p.getCategoriep());
			stat.setDouble(4, p.getPrixunitaire());
			stat.setInt(5, p.getQuantite());
			stat.setDouble(6, p.getRemise());
			stat.setDouble(7, p.getPrixvente());
			
			valeur=stat.executeUpdate();
			con.close();
			
			
		} catch(SQLException e) {
			
		}
		
		return valeur;
		
		
	}
	
	public static int modifierProduit(produit p) throws SQLException {
		Connection con=connexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		
		try {
			String sql="UPDATE produit set id=?,nom=?,categoriep=?,prixunitaire=?,quantite=?,remise=?,prixvente=? WHERE id=?";
			stat=con.prepareStatement(sql);
			stat.setInt(1, p.getIdentifiantp());
			stat.setString(2, p.getNom());
			stat.setString(3, p.getCategoriep());
			stat.setDouble(4, p.getPrixunitaire());
			stat.setInt(5, p.getQuantite());
			stat.setDouble(6, p.getRemise());
			stat.setDouble(7, p.getPrixvente());
			stat.setInt(1, p.getIdentifiantp());
			
			valeur=stat.executeUpdate();
			con.close();
			
			
		} catch(SQLException e) {
			
		}
		
		return valeur;
	}
	
	public static int supprimerProduit(produit p) throws SQLException {
		Connection con=connexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		
		try {
			String sql="DELETE from client WHERE id=?";
			stat=con.prepareStatement(sql);
			stat.setInt(1, p.getIdentifiantp());
			
			
			valeur=stat.executeUpdate();
			con.close();
			
			
		} catch(SQLException e) {
			
		}
		
		return valeur;
	}
	
	public static produit rechercheProduit(int iden) throws SQLException {
		Connection con=connexionDB.connect();
		PreparedStatement stat=null;
		produit p= new produit();
		ResultSet rs=null;
		
		try {
			String sql="Select * from produit WHERE id=?";
			stat=con.prepareStatement(sql);
			stat.setInt(1, iden);			
						
		rs=stat.executeQuery();
		con.close();
		p.setIdentifiantp(rs.getInt(1));
		p.setNom(rs.getString(2));
		p.setCategoriep(rs.getString(3));
		p.setPrixunitaire(rs.getDouble(4));
		p.setQuantite(rs.getInt(5));
		p.setRemise(rs.getDouble(6));
		p.setPrixvente(rs.getDouble(7));
		
		
		} catch(SQLException e) {
			
		}
		
		
		return null;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
