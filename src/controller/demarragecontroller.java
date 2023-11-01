package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JProgressBar;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class demarragecontroller implements Initializable {

	   @FXML
	    private AnchorPane dema;

	    @FXML
	    private ProgressIndicator progressindic;

	    @FXML
	    private ProgressBar progressbar;
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub


		Task task= taskWorker(10);
		progressbar.progressProperty().unbind();
		progressindic.progressProperty().unbind();
		progressbar.progressProperty().bind( task.progressProperty());
		progressindic.progressProperty().bind( task.progressProperty());
		task.setOnSucceeded(e->{
			
			Stage stage = (Stage)dema.getScene().getWindow();
			stage.close();
			
			try {
				Parent root=FXMLLoader.load(getClass().getResource("/view/connexionview.fxml"));
				Stage stage2 = new Stage();
				Scene scene = new Scene(root);
				stage2.setScene(scene);
				stage2.show();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}); 
		
		Thread thread = new Thread(task);
		thread.start();
		
	}
	
	
	private Task taskWorker(int seconds){
		return new Task<Object>(){
			@Override
			protected Object call() throws Exception{
				for(int  i = 0; i <= seconds; i++){
					updateProgress(i, seconds);
					Thread.sleep(400);
				}
				return true;
			}

		};
	};


		
		

}
