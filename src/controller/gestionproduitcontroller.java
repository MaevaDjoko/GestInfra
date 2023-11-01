package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.gestionProduitDAO;
import connexion.connexionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.produit;

public class gestionproduitcontroller implements Initializable {

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement stat;
	produit p = null;
	String sql;
	ObservableList<produit>listep;

	   @FXML
	    private Button accueil;
	   
	    @FXML
	    private Tab gestionprod;

	    @FXML
	    private TextField idprod1;

	    @FXML
	    private TextField nomprod1;

	    @FXML
	    private TextField categorieprod1;

	    @FXML
	    private TextField puprod1;

	    @FXML
	    private TextField qprod1;

	    @FXML
	    private TextField remise1;

	    @FXML
	    private TextField pvp;

	    @FXML
	    private Button rchprod;

	    @FXML
	    private Button modifprod;

	    @FXML
	    private TableView<produit> table3;

	    @FXML
	    private TableColumn<produit, Integer> idprod2;

	    @FXML
	    private TableColumn<produit, String> nomprod2;

	    @FXML
	    private TableColumn<produit, String> catprod2;
	    
	    @FXML
	    private TableColumn<produit, Double> pu2;

	    @FXML
	    private TableColumn<produit, Integer> qprod2;

	    @FXML
	    private TableColumn<produit, Double> remise2;
	    
	    @FXML
	    private TableColumn<produit, Double> pvprod2;
	    
	    public ObservableList<produit> Produits = FXCollections.observableArrayList();

	    @FXML
	    private Button supprod;

	    @FXML
	    private Button select;

	    @FXML
	    private ComboBox<String> rechp;

	    @FXML
	    private TextField rch1p;

	    @FXML
	    private Button affichep;
	    
	    ObservableList<String> RechercheP = FXCollections.observableArrayList("id","nom");
	    
	    @FXML
	    void Accueil(ActionEvent event) {


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
	    
	    
	    @FXML
	    void affip() {

	    	table3.getItems().clear();
	    	
	    	try {
	    		
	    		Connection conn=connexionDB.connect();
	    		String sql= "Select * from produit";
	    		PreparedStatement stat=conn.prepareStatement(sql);
	    		ResultSet rs=stat.executeQuery();
	    		
	    		while(rs.next()) {
	    			Produits.add(new produit(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getInt(5),rs.getDouble(6),rs.getDouble(7)));
	    		}
	    		
	    		conn.close();
	    		
	    	} catch (Exception e) {
	    	
	    		e.printStackTrace();
	    		
	    	}

	    	idprod2.setCellValueFactory(new PropertyValueFactory<produit, Integer>("identifiantp"));
	    	nomprod2.setCellValueFactory(new PropertyValueFactory<produit, String>("nom"));
	    	catprod2.setCellValueFactory(new PropertyValueFactory<produit, String>("categoriep"));
	    	pu2.setCellValueFactory(new PropertyValueFactory<produit, Double>("prixunitaire"));
	    	qprod2.setCellValueFactory(new PropertyValueFactory<produit, Integer>("quantite"));
	    	remise2.setCellValueFactory(new PropertyValueFactory<produit, Double>("remise"));
	    	pvprod2.setCellValueFactory(new PropertyValueFactory<produit, Double>("prixvente"));
	    	
	    	table3.setItems(Produits);	
	    }

	    @FXML
	    void ajouterp(ActionEvent event) throws SQLException {
	    	
	    
	    	table3.getItems().clear();
			
			String nomp1 = nomprod1.getText();
			String categoriep1 = categorieprod1.getText();
			String prixunit = puprod1.getText();
	    	double punit = Double.parseDouble(prixunit);
			String quantitep = qprod1.getText();
			int quantp = Integer.parseInt(quantitep);
			String remisep = remise1.getText();
			double rmise = Double.parseDouble(remisep);
			String pvp1 = pvp.getText();
			double pvente = Double.parseDouble(pvp1);
			
			
			produit p = new produit();
		
			p.setNom(nomp1);
			p.setCategoriep(categoriep1);
			p.setPrixunitaire(punit);
			p.setQuantite(quantp);
			p.setRemise(rmise);
			p.setPrixvente(pvente);


			int status = gestionProduitDAO.ajouterProduit(p);

			if(status>0) {

				Alert alert= new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Enregistrement");
				alert.setContentText("Produit ajouté");
				alert.showAndWait();

				nomprod1.clear();
				categorieprod1.clear();
				puprod1.clear();
				qprod1.clear();
				remise1.clear();
				pvp.clear();
				



			}else {

				Alert alert= new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Enregistrement");
				alert.setContentText("Produit non ajouté");
				alert.showAndWait();	


			} 	
	    	
	    }

	    private void executeQuery(String query) throws SQLException {
			// TODO Auto-generated method stub
	    	Connection conn=connexionDB.connect();
	    	java.sql.Statement st;
	    	try {
	    		st=conn.createStatement();
	    		st.executeUpdate(query);
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
		}
	  
	    @FXML
	    void modifierprod(ActionEvent event) throws SQLException {

	    	String query = "UPDATE produit SET nom='" + nomprod1.getText()+ "', categoriep='" +categorieprod1.getText()+ "' , prixunitaire=" +puprod1.getText()+ ", quantite=" +qprod1.getText()+ ",remise='" +remise1.getText()+"', prixvente=" +pvp.getText()+ " WHERE id=" + idprod1.getText() + "";
	    	executeQuery(query);
	    	table3.getItems().clear();
	    	affip();
	    	
			
			Alert alert1= new Alert(AlertType.INFORMATION);
			alert1.setTitle("Modification");
			alert1.setHeaderText("Information");
			alert1.setContentText("Produit bien modifié");
			alert1.showAndWait();	
			
	    	
	    }

	    @FXML
	    void rechercherprod(ActionEvent event) {

	    	String cbr2= rechp.getValue();
			String rech2=rch1p.getText();
			int rechok2 = Integer.parseInt(rech2);
			if(table3.getItems().isEmpty()!=true) {

			
			}else if(rech2.equals("")) {
				Alert alert= new Alert(AlertType.ERROR);
				alert.setTitle("Information");
				alert.setHeaderText("recherche");
				alert.setContentText(" selectionner la catégorie svp");
				alert.showAndWait();
				
			}else if(cbr2.equals("id")){
				
				//GestionFournisseurDAO.rechercheDAOid(rech);
				
				try {
					String sql ="SELECT * FROM produit WHERE id= ?";
						Connection con=connexionDB.connect();
						PreparedStatement stat;
						stat = con.prepareStatement(sql);
						stat.setInt(1,rechok2);
						ResultSet rs=stat.executeQuery();
						
						while(rs.next()) {

							Produits.add(new produit(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getDouble(4),rs.getInt(5), rs.getDouble(6),rs.getDouble(7)));

						}
						con.close();
						
						
					}catch (SQLException e2) {
						// TODO: handle exception
						e2.printStackTrace();   
					}
		    	
		    	idprod2.setCellValueFactory(new PropertyValueFactory<produit,Integer>("identifiantp"));



				nomprod2.setCellValueFactory(new PropertyValueFactory<produit,String>("nom"));
				nomprod2.setCellFactory(TextFieldTableCell.<produit>forTableColumn());

				catprod2.setCellValueFactory(new PropertyValueFactory<produit,String>("categoriep"));
				catprod2.setCellFactory(TextFieldTableCell.<produit>forTableColumn());


				pu2.setCellValueFactory(new PropertyValueFactory<produit,Double>("prixunitaire"));

				
				qprod2.setCellValueFactory(new PropertyValueFactory<produit,Integer>("quantite"));
				
				
				remise2.setCellValueFactory(new PropertyValueFactory<produit,Double>("remise"));
				
				
				pvprod2.setCellValueFactory(new PropertyValueFactory<produit,Double>("prixvente"));
				
				
				table3.setItems(Produits);
				rch1p.clear();
				
			}else if(cbr2.equals("nom")){
				
				

				try {
					String sql ="SELECT * FROM produit WHERE nom=?";
						Connection con=connexionDB.connect();
						PreparedStatement stat;
						stat = con.prepareStatement(sql);
						stat.setString(2, rech2);
						ResultSet rs=stat.executeQuery();
						
						while(rs.next()) {

							Produits.add(new produit(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getDouble(4),rs.getInt(5), rs.getDouble(6),rs.getDouble(7)));

						}
						con.close();
						
						
					}catch (SQLException e2) {
						// TODO: handle exception
						e2.printStackTrace();   
					}




				nomprod2.setCellValueFactory(new PropertyValueFactory<produit,String>("nom"));
				nomprod2.setCellFactory(TextFieldTableCell.<produit>forTableColumn());

				catprod2.setCellValueFactory(new PropertyValueFactory<produit,String>("categoriep"));
				catprod2.setCellFactory(TextFieldTableCell.<produit>forTableColumn());


				pu2.setCellValueFactory(new PropertyValueFactory<produit,Double>("prixunitaire"));

				
				qprod2.setCellValueFactory(new PropertyValueFactory<produit,Integer>("quantite"));
				
				
				remise2.setCellValueFactory(new PropertyValueFactory<produit,Double>("remise"));
				
				
				pvprod2.setCellValueFactory(new PropertyValueFactory<produit,Double>("prixvente"));
				
				
				table3.setItems(Produits);
				
			}
	    }
	  

	    @FXML
	    void selectp(ActionEvent event) {

	    	int n2 =table3.getSelectionModel().getSelectedIndex();
			
			ObservableList<produit> Produits =table3.getItems();
				produit p = Produits.get(n2); 
				

				int num=p.getIdentifiantp();
				TextFormatter<Integer> a = new TextFormatter<>(new IntegerStringConverter(),num);
				idprod1.setTextFormatter(a);
				

		    	nomprod1.setText(p.getNom());
		    	categorieprod1.setText(p.getCategoriep());
		    	
		    	TextFormatter<Double> punit = new TextFormatter<>(new DoubleStringConverter(),p.getPrixunitaire());
		    	puprod1.setTextFormatter(punit);

		    	int quantp=p.getQuantite();
		    	TextFormatter<Integer> n = new TextFormatter<>(new IntegerStringConverter(),quantp);
		    	qprod1.setTextFormatter(n);
		    	
		    	TextFormatter<Double> rmise = new TextFormatter<>(new DoubleStringConverter(),p.getRemise());
		    	remise1.setTextFormatter(rmise);
		    	
		    	TextFormatter<Double> pvente = new TextFormatter<>(new DoubleStringConverter(),p.getPrixvente());
		    	pvp.setTextFormatter(pvente);
		    	
		    	
	    }

	    
	    
	    @FXML
	    void supprimerprod(ActionEvent event) throws SQLException {

	    	String query = "DELETE FROM produit WHERE id=" + idprod1.getText() + "";
	    	executeQuery(query);
	    	table3.getItems().clear();
	    	
	    	Alert alert1= new Alert(AlertType.INFORMATION);
			alert1.setTitle("Suppression");
			alert1.setHeaderText("Information");
			alert1.setContentText("Produit bien supprimé");
			alert1.showAndWait();	
			
	    	affip();
	    	
	    	idprod1.clear();
	    	nomprod1.clear();
	    	categorieprod1.clear();
	    	puprod1.clear();
	    	qprod1.clear();
	    	remise1.clear();
	    	pvp.clear();
	    }
	   
	
	
	
	
	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		rechp.setItems(RechercheP);
	}

}
