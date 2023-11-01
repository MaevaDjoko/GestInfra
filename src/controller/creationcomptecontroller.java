package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class creationcomptecontroller implements Initializable {
	
	

    @FXML
    private TextField utilisateur;

    @FXML
    private PasswordField mdp;

    @FXML
    private Button inscrit;
    
    @FXML
    private Button conn;
    
    @FXML
    void connect(ActionEvent event) {
    	

   	 try {
   		
	    		 conn.getScene().getWindow().hide();
	    		 Stage stage= new Stage();
		    	 
		    	 FXMLLoader loader= new FXMLLoader();
		    	 
		    	loader.setLocation(getClass().getResource("/view/connexionview.fxml"));
		    	 Parent main=loader.load();
		    	 
		    	 Scene scene= new Scene(main);
		    	 stage.setScene(scene);
		    	 stage.show();
		    	 stage.setResizable(false);
		    	 
	    	 
	    	 
   	 } catch (Exception e){
   		 
   	 }


    }

    @FXML
    void inscription(ActionEvent event) {

    }
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
