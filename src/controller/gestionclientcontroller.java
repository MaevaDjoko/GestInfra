package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.gestionClientDAO;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import model.client;


public class gestionclientcontroller implements Initializable {

	
	 @FXML
	    private TextField idclient;

	    @FXML
	    private TextField nomc;

	    @FXML
	    private TextField prenomc;

	    @FXML
	    private TextField cni;

	    @FXML
	    private TextField villec;

	    @FXML
	    private TextField telc;

	    @FXML
	    private Button ajoutc;

	    @FXML
	    private Button modifiec;

	    @FXML
	    private Button supprimec;

	    @FXML
	    private Button accueil;

	    @FXML
	    private TableView<client> table2;
	    
	    @FXML
	    private ComboBox<String> rchp;

	    @FXML
	    private TableColumn<client, Integer> idc1;

	    @FXML
	    private TableColumn<client, String> nomc1;

	    @FXML
	    private TableColumn<client, String> prenomc1;

	    @FXML
	    private TableColumn<client, String> cni1;
	    
	    @FXML
	    private TableColumn<client, String> villec1;

	    @FXML
	    private TableColumn<client, String> telc1;
	    
	    public ObservableList<client> Clients = FXCollections.observableArrayList();
	    
	    public ObservableList<String> listerchp = FXCollections.observableArrayList("Identifiant","Nom");

	    @FXML
	    private Button affiche;

	    @FXML
	    private Button recherchepr;

	    @FXML
	    private Button selectionne;

	    @FXML
	    private TextField trech;


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
	    void affichercl() throws SQLException {

	    	table2.getItems().clear();
	    	
	    	try {
	    		
	    		Connection conn=connexionDB.connect();
	    		String sql= "Select * from client";
	    		PreparedStatement stat=conn.prepareStatement(sql);
	    		ResultSet rs=stat.executeQuery();
	    		
	    		while(rs.next()) {
	    			Clients.add(new client(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
	    		}
	    		
	    		conn.close();
	    		
	    	} catch (Exception e) {
	    	
	    		e.printStackTrace();
	    		
	    	}

	    	idc1.setCellValueFactory(new PropertyValueFactory<client, Integer>("idClient"));
	    	nomc1.setCellValueFactory(new PropertyValueFactory<client, String>("nom"));
	    	prenomc1.setCellValueFactory(new PropertyValueFactory<client, String>("prenom"));
	    	cni1.setCellValueFactory(new PropertyValueFactory<client, String>("CNI"));
	    	villec1.setCellValueFactory(new PropertyValueFactory<client, String>("ville"));
	    	telc1.setCellValueFactory(new PropertyValueFactory<client, String>("tel"));
	    	
	    	table2.setItems(Clients);	
	    	
	    }
	    
	   
	    @FXML
	    void ajoutercl() throws SQLException {

	    	table2.getItems().clear();
	    	
			String nomc1 = nomc.getText();
			String prenomc1 = prenomc.getText();
			String cni1 = cni.getText();
			String ville1 = villec.getText();
			String telc1 = telc.getText();
			
			
			client c = new client();


			c.setNom(nomc1);
			c.setPrenom(prenomc1);
			c.setCNI(cni1);
			c.setVille(ville1);
			c.setTel(telc1);


			int status = gestionClientDAO.ajouterClient(c);

			if(status>0) {

				Alert alert= new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Enregistrement");
				alert.setContentText("Client ajouté");
				alert.showAndWait();

				idclient.clear();
				nomc.clear();
				prenomc.clear();
				cni.clear();
				villec.clear();
				telc.clear();

				




			}else {

				Alert alert= new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Enregistrement");
				alert.setContentText("Client non ajouté");
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
	    void modifierc(ActionEvent event) throws SQLException {

	    	String query = "UPDATE client set nom='" + nomc.getText()+ "', prenom='" +prenomc.getText()+ "',cni='" +cni.getText()+ "',ville='" +villec.getText()+ "',tel='" +telc.getText()+"' WHERE id=" + idclient.getText() + "";
	    	executeQuery(query);
	    	table2.getItems().clear();
	    	affichercl();
	    	
	    	idclient.clear();
			nomc.clear();
			prenomc.clear();

			cni.clear();
			villec.clear();
			telc.clear();
			
			Alert alert1= new Alert(AlertType.INFORMATION);
			alert1.setTitle("Modification");
			alert1.setHeaderText("Information");
			alert1.setContentText("Client bien modifié");
			alert1.showAndWait();	

	    }

	    @FXML
	    void rechpar(ActionEvent event) {

	    	
	    	String cbr1= rchp.getValue();
			String rech1=trech.getText();
			int rechok1 = Integer.parseInt(rech1);
			if(table2.getItems().isEmpty()!=true) {

			
			}else if(rech1.equals(" ")) {
				Alert alert= new Alert(AlertType.ERROR);
				alert.setTitle("Information");
				alert.setHeaderText("recherche");
				alert.setContentText(" selectionner la catégorie svp");
				alert.showAndWait();
				
			}else if(cbr1.equals("Identifiant")){
				
				//GestionFournisseurDAO.rechercheDAOid(rech);
				
				try {
					String sql ="SELECT * FROM client WHERE id= ?";
						Connection con=connexionDB.connect();
						PreparedStatement stat;
						stat = con.prepareStatement(sql);
						stat.setInt(1,rechok1);
						ResultSet rs=stat.executeQuery();
						
						while(rs.next()) {

							Clients.add(new client(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6)));

						}
						con.close();
						
						
					}catch (SQLException e2) {
						// TODO: handle exception
						e2.printStackTrace();   
					}
		    	
		    	idc1.setCellValueFactory(new PropertyValueFactory<client,Integer>("idClient"));
		    	


				nomc1.setCellValueFactory(new PropertyValueFactory<client,String>("nom"));
				nomc1.setCellFactory(TextFieldTableCell.<client>forTableColumn());

				prenomc1.setCellValueFactory(new PropertyValueFactory<client,String>("prenom"));
				prenomc1.setCellFactory(TextFieldTableCell.<client>forTableColumn());


				cni1.setCellValueFactory(new PropertyValueFactory<client,String>("CNI"));
				cni1.setCellFactory(TextFieldTableCell.<client>forTableColumn());

				
				villec1.setCellValueFactory(new PropertyValueFactory<client,String>("ville"));
				villec1.setCellFactory(TextFieldTableCell.<client>forTableColumn());
				
				telc1.setCellValueFactory(new PropertyValueFactory<client,String>("tel"));
				telc1.setCellFactory(TextFieldTableCell.<client>forTableColumn());
				
				
				table2.setItems(Clients);
				trech.clear();
				
			}else if(cbr1.equals("Nom")){
				
				

				try {
					String sql ="SELECT * FROM client WHERE nom=?";
						Connection con=connexionDB.connect();
						PreparedStatement stat;
						stat = con.prepareStatement(sql);
						stat.setString(1, rech1);
						ResultSet rs=stat.executeQuery();
						
						while(rs.next()) {

							Clients.add(new client(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6)));

						}
						con.close();
						
						
					}catch (SQLException e2) {
						// TODO: handle exception
						e2.printStackTrace();   
					}



				prenomc1.setCellValueFactory(new PropertyValueFactory<client,String>("prenom"));
				prenomc1.setCellFactory(TextFieldTableCell.<client>forTableColumn());


				cni1.setCellValueFactory(new PropertyValueFactory<client,String>("CNI"));
				cni1.setCellFactory(TextFieldTableCell.<client>forTableColumn());

				
				villec1.setCellValueFactory(new PropertyValueFactory<client,String>("ville"));
				villec1.setCellFactory(TextFieldTableCell.<client>forTableColumn());
				
				telc1.setCellValueFactory(new PropertyValueFactory<client,String>("tel"));
				telc1.setCellFactory(TextFieldTableCell.<client>forTableColumn());
				
				
				table2.setItems(Clients);
				trech.clear();
			}
	    }

	    @FXML
	    void selectionner(ActionEvent event) {

	    	int n2 =table2.getSelectionModel().getSelectedIndex();
			
			ObservableList<client> Clients =table2.getItems();
				client c = Clients.get(n2); 
				
			
				int num=c.getIdClient();
				TextFormatter<Integer> a = new TextFormatter<>(new IntegerStringConverter(),num);
				idclient.setTextFormatter(a);
		    	
		    	nomc.setText(c.getNom());
		    	prenomc.setText(c.getPrenom());
		    	cni.setText(c.getCNI());
		    	villec.setText(c.getVille());
		    	telc.setText(c.getTel());
		    	
		    	
		    	
	    }
	    
	    @FXML
	    void supprimerc(ActionEvent event) throws SQLException {

	    	String query = "DELETE FROM client WHERE id=" + idclient.getText() + "";
	    	executeQuery(query);
	    	table2.getItems().clear();
	    	
	    	Alert alert1= new Alert(AlertType.INFORMATION);
			alert1.setTitle("Suppression");
			alert1.setHeaderText("Information");
			alert1.setContentText("client bien supprimé");
			alert1.showAndWait();	

	    	affichercl();
	    	
	    }

	
	   
	
	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		rchp.setItems(listerchp);
	}

}
