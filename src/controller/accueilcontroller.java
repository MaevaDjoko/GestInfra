package controller;

import java.io.IOException;
import java.net.URL;

import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class accueilcontroller implements Initializable {
	
	 	@FXML
	    private AnchorPane Root;
	 
	   @FXML
	    private MenuItem deconnecter;
	   
	   @FXML
	    private MenuItem fgestc;

	    @FXML
	    private Button fournisseur;

	    @FXML
	    private Button client;

	    @FXML
	    private Button produit;

	    @FXML
	    private Button commandecl;
	    
	    @FXML
	    private Button materiel;

	    @FXML
	    private Button commandemat;

	    @FXML
	    void deconnexion(ActionEvent event) {
	    	 
	    	System.exit(0);
	    	 
	  	    }
	    
	    @FXML
	    void fengestcl(ActionEvent event) throws IOException {

	    	AnchorPane a = FXMLLoader.load(Main.class.getResource("/view/Gestioncommandeclientview.fxml"));
	    	Root.getChildren().setAll(a);
	    }
	    
	    @FXML
	    void gestclient(ActionEvent event) throws SQLException {
	    	
	    	
	    	 try {
	    		
		    		 client.getScene().getWindow().hide();	//permet de cacher la fenetre courante ie la page de connexion pour ouvrir la page d'accueil
			    	 Stage stage= new Stage();
			    	 
			    	 FXMLLoader loader= new FXMLLoader();
			    	 
			    	loader.setLocation(getClass().getResource("/view/Gestionclientview.fxml"));
			    	 Parent main1=loader.load();
			    	 
			    	 Scene scene= new Scene(main1);
			    	 stage.setScene(scene);
			    	 stage.show();
			    	 stage.setResizable(false);
			    	 
		    	 
		    	 
	    	 } catch (Exception e){
	    		 
	    	 }
	    
	    	
	    }
	    

	    @FXML
	    void gestcom(ActionEvent event) throws SQLException {
	    
	    	 try {
	    		
		    		 commandecl.getScene().getWindow().hide();	//permet de cacher la fenetre courante ie la page de connexion pour ouvrir la page d'accueil
			    	 Stage stage= new Stage();
			    	 
			    	 FXMLLoader loader= new FXMLLoader();
			    	 
			    	loader.setLocation(getClass().getResource("/view/Gestioncommandeclientview.fxml"));
			    	 Parent main1=loader.load();
			    	 
			    	 Scene scene= new Scene(main1);
			    	 stage.setScene(scene);
			    	 
			    	 stage.show();
			    	 stage.setResizable(false);
			    	 
		    	 
		    	 
	    	 } catch (Exception e){
	    		 
	    	 }
	    

	    }
	    


	    @FXML
	    void gestfourn(ActionEvent event) throws SQLException {
	    
	    	
	    	 try {
	    		
		    		 fournisseur.getScene().getWindow().hide();	//permet de cacher la fenetre courante ie la page de connexion pour ouvrir la page d'accueil
			    	 Stage stage= new Stage();
			    	 
			    	 FXMLLoader loader= new FXMLLoader();
			    	 
			    	loader.setLocation(getClass().getResource("/view/GestionFournisseurview.fxml"));
			    	 Parent main3=loader.load();
			    	 
			    	 Scene scene= new Scene(main3);
			    	 stage.setScene(scene);
			    	 
			    	 stage.show();
			    	 stage.setResizable(false);
			    	 
		    	 
		    	 
	    	 } catch (Exception e){
	    		 
	    	 }
	    
	    }
	    

	  
	    

	    @FXML
	    void gestprod(ActionEvent event) throws SQLException {
	    
	    	
	    	 try {
	    		
		    		 produit.getScene().getWindow().hide();	//permet de cacher la fenetre courante ie la page de connexion pour ouvrir la page d'accueil
			    	 Stage stage= new Stage();
			    	 
			    	 FXMLLoader loader= new FXMLLoader();
			    	 
			    	loader.setLocation(getClass().getResource("/view/GestionProduitview.fxml"));
			    	 Parent main=loader.load();
			    	 
			    	 Scene scene= new Scene(main);
			    	 stage.setScene(scene);
			    	 
			    	 stage.show();
			    	
			    	 
		    	 
		    	 
	    	 } catch (Exception e){
	    		 
	    	 }
	    
	    }

	
	
	
	
	
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
