package controller;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class statistiquescontroller implements Initializable {
	
	
	 @FXML
	    private BarChart<String, Number> barchart;

	    @FXML
	    private CategoryAxis date;

	    @FXML
	    private NumberAxis produit;
	    
	    
	    
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.setName("Nombre de produits vendus en fonction des dates");
		series.getData().add(new XYChart.Data<>("2021-12-08", 20));
		series.getData().add(new XYChart.Data<>("2021-11-30", 50));
		series.getData().add(new XYChart.Data<>("2021-12-02", 70));
		series.getData().add(new XYChart.Data<>("2021-12-21", 23));
		series.getData().add(new XYChart.Data<>("2021-12-02", 10));
		series.getData().add(new XYChart.Data<>("2022-06-02", 1));
		
		
		barchart.getData().add(series);
		
	}

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
