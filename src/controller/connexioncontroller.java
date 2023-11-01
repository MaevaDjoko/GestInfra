package controller;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import connexion.connexionDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class connexioncontroller implements Initializable {
	
	   @FXML
	    private AnchorPane root;
	
	 @FXML
	    private TextField login;

	    @FXML
	    private PasswordField password;

	    @FXML
	    private Button connecter;
	    
	    @FXML
	    private Button inscrit;

	    @FXML
	    private Button mdp;

	   
	    @FXML
	    public void connexion(ActionEvent event) throws SQLException {

	    
	    	Connection conn=connexionDB.connect();
	    	 
	    	 PreparedStatement stat=null;
	    	 ResultSet rs=null;
	    	 String sql="SELECT * FROM utilisateur WHERE login= ? AND password= ?";
	    	 
	    	 try {
	    	 
	    	 
	    	 stat=conn.prepareStatement(sql);
	    	 stat.setString(1, login.getText().toString());
	    	 stat.setString(2, password.getText().toString());
	    	 
	    	 rs=stat.executeQuery();
	    	 
	    	 if(rs.next()) {
	    	 
	    	 //setText permet d'affecter un texte à un textfield
	    	 
	    	 
	    	 connecter.getScene().getWindow().hide();	//permet de cacher la fenetre courante ie la page de connexion pour ouvrir la page d'accueil
	    	 Stage stage= new Stage();
	    	 
	    	 FXMLLoader loader= new FXMLLoader();
	    	 
	    	loader.setLocation(getClass().getResource("/view/accueilview.fxml"));
	    	 Parent main=loader.load();
	    	 
	    	 Scene scene= new Scene(main);
	    	 stage.setScene(scene);
	    	 
	    	 stage.show();
	    	 
	    	//connexion.getScene().getWindow().hide();
	    	 
	    	 
	    	 }else {
	    	 	    	 
	    	 
	    	 Alert alert= new Alert(AlertType.ERROR);	//Alert permet d'afficher une fenetre contenant un msg
	    	 alert.setTitle("Alert");
	    	 alert.setHeaderText("Erreur");
	    	 alert.setContentText("mot de passe ou login incorrect");
	    	 alert.showAndWait();
	    	 
	    	 
	    	 }
	    	 
	    	 
	    	 
	    	 }catch (Exception e) {
	    	 // TODO: handle exception
	    	
	    	

	    }
	
	    		 
	    	 }
	   
	    @FXML
	    void inscription(ActionEvent event) throws SQLException {
	    	
	    	 try {
	    		
		    		 inscrit.getScene().getWindow().hide();
		    		 Stage stage= new Stage();
			    	 
			    	 FXMLLoader loader= new FXMLLoader();
			    	 
			    	loader.setLocation(getClass().getResource("/view/creationcompteview.fxml"));
			    	 Parent main=loader.load();
			    	 
			    	 Scene scene= new Scene(main);
			    	 stage.setScene(scene);
			    	 stage.show();
			    	 stage.setResizable(false);
			    	 
		    	 
		    	 
	    	 } catch (Exception e){
	    		 
	    	 }

	    }

	    @FXML
	    void motdepasse(ActionEvent event) {

	    	 try {
	    		
		    		 inscrit.getScene().getWindow().hide();
		    		 Stage stage= new Stage();
			    	 
			    	 FXMLLoader loader= new FXMLLoader();
			    	 
			    	loader.setLocation(getClass().getResource("/view/creationcompteview.fxml"));
			    	 Parent main=loader.load();
			    	 
			    	 Scene scene= new Scene(main);
			    	 stage.setScene(scene);
			    	 stage.show();
			    	 stage.setResizable(false);
			    	 
		    	 
		    	 
	    	 } catch (Exception e){
	    		 
	    	 }

	    }
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
