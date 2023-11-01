package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;



import DAO.gestionFournisseurDAO;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import model.fournisseur;


public class gestionfournisseurcontroller implements Initializable {

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement stat;
	fournisseur f = null;
	String sql;
	ObservableList<fournisseur> listef;


    @FXML
    private Button accueil;

    @FXML
    private TextField identifiant;

    @FXML
    private TextField ville;

    @FXML
    private TextField categoriemat;

    @FXML
    private TextField nom;

    @FXML
    private TextField email;

    @FXML
    private TextField adresse;
    
    @FXML
    private ComboBox<String> rche;

    @FXML
    private TableView<fournisseur> table;

    @FXML
    private TableColumn<fournisseur, Integer> id;

    @FXML
    private TableColumn<fournisseur, String> nom1;

    @FXML
    private TableColumn<fournisseur, String> adresse1;

    @FXML
    private TableColumn<fournisseur, String> ville1;

    @FXML
    private TableColumn<fournisseur, String> Email;

    @FXML
    private TableColumn<fournisseur, String> categorie;
    
    public ObservableList<fournisseur> data = FXCollections.observableArrayList();

    ObservableList<String> listerche = FXCollections.observableArrayList("id","nom");
    
    @FXML
    private Button ajout;

    @FXML
    private Button supprime;

    @FXML
    private Button modifie;

    @FXML
    private Button affiche;
    
    @FXML
    private Button actualise;
    
    @FXML
    private TextField rcher;
    
    

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
    
    
    @FXML
    void afficher() throws SQLException {
    	
    	table.getItems().clear();
    	
    	try {
    		
    		Connection conn=connexionDB.connect();
    		String sql= "Select * from fournisseur";
    		PreparedStatement stat=conn.prepareStatement(sql);
    		ResultSet rs=stat.executeQuery();
    		
    		while(rs.next()) {
    			data.add(new fournisseur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
    		}
    		
    		conn.close();
    		
    	} catch (Exception e) {
    	
    		e.printStackTrace();
    		
    	}

    	id.setCellValueFactory(new PropertyValueFactory<fournisseur, Integer>("identifiant"));
    	nom1.setCellValueFactory(new PropertyValueFactory<fournisseur, String>("nom"));
    	adresse1.setCellValueFactory(new PropertyValueFactory<fournisseur, String>("adresse"));
    	ville1.setCellValueFactory(new PropertyValueFactory<fournisseur, String>("ville"));
    	Email.setCellValueFactory(new PropertyValueFactory<fournisseur, String>("email"));
    	categorie.setCellValueFactory(new PropertyValueFactory<fournisseur, String>("categorieM"));
    	
    	table.setItems(data);
    	
    }
  

    @FXML
    void ajouter() throws SQLException  {

		table.getItems().clear();
		
		String nom1 = nom.getText();
		String adresse1 = adresse.getText();
		String ville1 = ville.getText();
		String Email = email.getText();
		String categorie = categoriemat.getText();
		
		fournisseur f = new fournisseur();


		
		f.setNom(nom1);
		f.setAdresse(adresse1);
		f.setVille(ville1);
		f.setEmail(Email);
		f.setCategorieM(categorie);


		int status = gestionFournisseurDAO.ajouterFournisseur(f);

		if(status>0) {

			Alert alert= new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Enregistrement");
			alert.setContentText("fournisseur ajouté");
			alert.showAndWait();

			identifiant.clear();
			nom.clear();
			adresse.clear();

			ville.clear();
			email.clear();
			categoriemat.clear();

			




		}else {

			Alert alert= new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Enregistrement");
			alert.setContentText("fournisseur non ajouté");
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
    void supprimer() throws SQLException {

    	String query = "DELETE FROM fournisseur WHERE id=" + identifiant.getText() + "";
    	executeQuery(query);
    	table.getItems().clear();
    	
    	Alert alert1= new Alert(AlertType.INFORMATION);
		alert1.setTitle("Suppression");
		alert1.setHeaderText("Information");
		alert1.setContentText("fournisseur bien supprimé");
		alert1.showAndWait();	
		
    	afficher();
    	identifiant.clear();
    	
		
    	
    }

    @FXML
    void modifier() throws SQLException {
    	
    	
    	
    	String query = "UPDATE fournisseur set nom='" + nom.getText()+ "', adresse='" +adresse.getText()+ "',ville='" +ville.getText()+ "',email='" +email.getText()+ "',categorieM='" +categoriemat.getText()+"' WHERE id=" + identifiant.getText() + "";
    	executeQuery(query);
    	table.getItems().clear();
    	afficher();
    	
    	identifiant.clear();
		nom.clear();
		adresse.clear();

		ville.clear();
		email.clear();
		categoriemat.clear();
		
		Alert alert1= new Alert(AlertType.INFORMATION);
		alert1.setTitle("Modification");
		alert1.setHeaderText("Information");
		alert1.setContentText("Fournisseur bien modifié");
		alert1.showAndWait();	
		
    }



    @FXML
    void rechercher(ActionEvent event) {

    	String cbr= rche.getValue();
		String rech=rcher.getText();
		int rechok = Integer.parseInt(rech);
		if(table.getItems().isEmpty()!=true) {

		
		}else if(rech.equals("")) {
			Alert alert= new Alert(AlertType.ERROR);
			alert.setTitle("Information");
			alert.setHeaderText("recherche");
			alert.setContentText(" selectionner la catégorie svp");
			alert.showAndWait();
			
		}else if(cbr.equals("id")){
			
			//GestionFournisseurDAO.rechercheDAOid(rech);
			
			try {
				String sql ="SELECT * FROM fournisseur WHERE id= ?";
					Connection con=connexionDB.connect();
					PreparedStatement stat;
					stat = con.prepareStatement(sql);
					stat.setInt(1,rechok);
					ResultSet rs=stat.executeQuery();
					
					while(rs.next()) {

						data.add(new fournisseur(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6)));

					}
					con.close();
					
					
				}catch (SQLException e2) {
					// TODO: handle exception
					e2.printStackTrace();   
				}
	    	
	    	id.setCellValueFactory(new PropertyValueFactory<fournisseur,Integer>("identifiant"));



			nom1.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("nom"));
			nom1.setCellFactory(TextFieldTableCell.<fournisseur>forTableColumn());

			adresse1.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("adresse"));
			adresse1.setCellFactory(TextFieldTableCell.<fournisseur>forTableColumn());


			ville1.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("ville"));
			ville1.setCellFactory(TextFieldTableCell.<fournisseur>forTableColumn());

			
			Email.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("email"));
			Email.setCellFactory(TextFieldTableCell.<fournisseur>forTableColumn());
			
			categorie.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("categorieM"));
			categorie.setCellFactory(TextFieldTableCell.<fournisseur>forTableColumn());
			
			
			table.setItems(data);
			rcher.clear();
			
		}else if(cbr.equals("nom")){
			
			

			try {
				String sql ="SELECT * FROM fournisseur WHERE nom=?";
					Connection con=connexionDB.connect();
					PreparedStatement stat;
					stat = con.prepareStatement(sql);
					stat.setString(2, rech);
					ResultSet rs=stat.executeQuery();
					
					while(rs.next()) {

						data.add(new fournisseur(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6)));

					}
					con.close();
					
					
				}catch (SQLException e2) {
					// TODO: handle exception
					e2.printStackTrace();   
				}



			nom1.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("nom"));
			nom1.setCellFactory(TextFieldTableCell.<fournisseur>forTableColumn());

			adresse1.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("adresse"));
			adresse1.setCellFactory(TextFieldTableCell.<fournisseur>forTableColumn());


			ville1.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("ville"));
			ville1.setCellFactory(TextFieldTableCell.<fournisseur>forTableColumn());

			
			Email.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("email"));
			Email.setCellFactory(TextFieldTableCell.<fournisseur>forTableColumn());
			
			categorie.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("categorieM"));
			categorie.setCellFactory(TextFieldTableCell.<fournisseur>forTableColumn());
			
			
			table.setItems(data);
			
		}
		}
			
    @FXML
    void selection(ActionEvent event) {



    	int n1 =table.getSelectionModel().getSelectedIndex();
		
		ObservableList<fournisseur> data =table.getItems();
			fournisseur f = data.get(n1); 
			
		
			int num=f.getIdentifiant();
			TextFormatter<Integer> a = new TextFormatter<>(new IntegerStringConverter(),num);
			identifiant.setTextFormatter(a);
			
	    	nom.setText(f.getNom());
	    	adresse.setText(f.getAdresse());
	    	ville.setText(f.getVille());
	    	email.setText(f.getEmail());
	    	categoriemat.setText(f.getCategorieM());
	    	
		
		

    }


	@FXML
    void actualiser(ActionEvent event) {

    	if(table.getItems().isEmpty()!=true) {

			table.getItems().clear();


		}else {

			try {
				Connection con = connexionDB.connect();

				String sql = "SELECT * FROM fournisseur";
				PreparedStatement stat = con.prepareStatement(sql);

				ResultSet rs = stat.executeQuery();
				while(rs.next()) {

					listef.add(new fournisseur(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6)));

				}
				con.close();

			} catch (Exception e) {

				e.printStackTrace();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



			id.setCellValueFactory(new PropertyValueFactory<fournisseur,Integer>("id"));
			//id.setCellFactory(TextFieldTableCell.<fournisseur>forTableColumn());

			id.setOnEditCommit((CellEditEvent<fournisseur, Integer> t)->{

				((fournisseur) t.getTableView().getItems().get(t.getTablePosition().getRow())).setIdentifiant(t.getNewValue());

			});


			nom1.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("nom"));
			nom1.setCellFactory(TextFieldTableCell.<fournisseur>forTableColumn());

			nom1.setOnEditCommit((CellEditEvent<fournisseur, String> t)->{

				((fournisseur) t.getTableView().getItems().get(t.getTablePosition().getRow())).setNom(t.getNewValue());

			});


			adresse1.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("ville"));
			adresse1.setCellFactory(TextFieldTableCell.<fournisseur>forTableColumn());

			adresse1.setOnEditCommit((CellEditEvent<fournisseur, String> t)->{

				((fournisseur) t.getTableView().getItems().get(t.getTablePosition().getRow())).setVille(t.getNewValue());

			});
			ville1.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("ville"));
			ville1.setCellFactory(TextFieldTableCell.<fournisseur>forTableColumn());

			ville1.setOnEditCommit((CellEditEvent<fournisseur, String> t)->{

				((fournisseur) t.getTableView().getItems().get(t.getTablePosition().getRow())).setVille(t.getNewValue());

			});
			Email.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("email"));
			Email.setCellFactory(TextFieldTableCell.<fournisseur>forTableColumn());

			Email.setOnEditCommit((CellEditEvent<fournisseur, String> t)->{

				((fournisseur) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());

			});


			table.setItems(listef);


		}

	}

    
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		rche.setItems(listerche);

}
	
	
	
}
