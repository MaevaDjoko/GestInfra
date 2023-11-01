package controller;

import java.net.URL;

import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class gestioncommaterielcontroller implements Initializable {

	  @FXML
	    private Button accueil;

	    @FXML
	    void Accueil(ActionEvent event) throws SQLException {
	    	 
	    	 try {
	    
	    	 accueil.getScene().getWindow().hide();	//permet de cacher la fenetre courante ie la page de connexion pour ouvrir la page d'accueil
	    	 Stage stage= new Stage();
	    	 
	    	 FXMLLoader loader= new FXMLLoader();
	    	 
	    	loader.setLocation(getClass().getResource("/view/accueilview.fxml"));
	    	 Parent main=loader.load();
	    	 
	    	 Scene scene= new Scene(main);
	    	 stage.setScene(scene);
	    	 
	    	 stage.show();
	    	 
	    	 

	    	 
	    	 }catch (Exception e) {
	    	 // TODO: handle exception	

	    }
	
	    		 
	    }
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
