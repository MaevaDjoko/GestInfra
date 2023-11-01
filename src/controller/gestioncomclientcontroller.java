package controller;



import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;


import DAO.gestionCommandeDAO;
import connexion.connexionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.Printer.MarginType;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.commande;
import model.produit;

public class gestioncomclientcontroller implements Initializable {

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement stat;
	produit p = null;
	String sql;
	
	
	 @FXML
	  private Pane anchor;

	@FXML
	private AnchorPane Root;
	  
	@FXML
    private Button accueil;

    @FXML
    private TextField numcom;

  
    @FXML
    private TextField pt;

    @FXML
    private TextField quantitecl;
    
    @FXML
    private TextField prixunita;

    @FXML
    private DatePicker datecom;

    @FXML
    private TableView<commande> table4;

    @FXML
    private TableColumn<commande, Integer> numcom1;

    @FXML
    private TableColumn<commande, String> nomcl;

    @FXML
    private TableColumn<commande, String> produitcl;

    @FXML
    private TableColumn<commande, Double> prixu;

    @FXML
    private TableColumn<commande, Integer> qcom;

    @FXML
    private TableColumn<commande, Double> ptcom;
   

    @FXML
    private TableColumn<commande, String> datecom1;

    @FXML
    private TextField numfact;

    @FXML
    private TextField nomcli;

    @FXML
    private TextField designfact;

    @FXML
    private TextField pufact;

    @FXML
    private TextField qfact;

    @FXML
    private TextField ptfact;

    @FXML
    private Button imprimefact;

    @FXML
    private DatePicker datefact;

    @FXML
    private Button ajoutcom;

    @FXML
    private Button modifiecom;

    @FXML
    private Button supprimecom;

    @FXML
    private Button affichecom;

    @FXML
    private Button slctcom;

    @FXML
    private Button brechcom;
    
    @FXML
    private Button stat1;
    
    @FXML
    private ComboBox<String> prodcom;
    
    @FXML
    private ComboBox<String> nomclicom;

    @FXML
    private TextField recherchercom;
    
    @FXML
    private ComboBox<String> rcherparcom;
    
    public ObservableList<commande> Commandes =FXCollections.observableArrayList();
    private ObservableList<String> listcomboprod = FXCollections.observableArrayList();
    private ObservableList<String> listcomboclient = FXCollections.observableArrayList();
    public ObservableList<String> listerchp = FXCollections.observableArrayList("Numero");

    
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
    void affichercom() {
    	if(table4.getItems().isEmpty()!=true) {

			table4.getItems().clear();


		}else {

			try {
				Connection con = connexionDB.connect();

				String sql = "SELECT * FROM commande";
				PreparedStatement stat = con.prepareStatement(sql);

				ResultSet rs = stat.executeQuery();
				while(rs.next()) {


					Commandes.add(new commande(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getDouble(4),rs.getInt(5),rs.getDouble(6),rs.getString(7)));

				}
				con.close();

			} catch (Exception e) {

				e.printStackTrace();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			numcom1.setCellValueFactory(new PropertyValueFactory<commande, Integer>("numcommande"));
	    	nomcl.setCellValueFactory(new PropertyValueFactory<commande, String>("nomClient"));
	    	produitcl.setCellValueFactory(new PropertyValueFactory<commande, String>("produit"));
	    	prixu.setCellValueFactory(new PropertyValueFactory<commande, Double>("prixunitaire"));
	    	qcom.setCellValueFactory(new PropertyValueFactory<commande, Integer>("quantite"));
	    	ptcom.setCellValueFactory(new PropertyValueFactory<commande, Double>("prixtotal"));
	    	datecom1.setCellValueFactory(new PropertyValueFactory<commande, String>("date"));
	    	

			table4.setItems(Commandes);

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
    void ajoutercom(ActionEvent event) throws SQLException {
    	
    	

    	table4.getItems().clear();
    	
		String nom = nomclicom.getValue();
		String nomprod = prodcom.getValue();
		String pu = prixunita.getText();
		double pux = Double.parseDouble(pu);
		String qtec = quantitecl.getText();
		int qtecc = Integer.parseInt(qtec);
		LocalDate dtec = datecom.getValue();
		String datecc=dtec.toString();
		String pto = pt.getText();
		double ptot = Double.parseDouble(pto);
		
		commande com= new commande();
		//produit p = null;

	
		com.setNomClient(nom);
		com.setProduit(nomprod);
		com.setPrixunitaire(pux);
		com.setQuantite(qtecc);
		com.setPrixtotal(ptot);
		com.setDate(datecc);
		

	/*	try {
			Connection con = connexionDB.connect();

			String sql = "SELECT * FROM produit where nom = ?";
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1, nom);

			ResultSet rs = stat.executeQuery();
			while(rs.next()) {

				p=	new produit(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getDouble(4),rs.getInt(5),
						rs.getString(6),rs.getDouble(7));

			}
			con.close();

		} catch (Exception e) {

			e.printStackTrace();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		if(com.getQuantite()>p.getQuantite()) {

			Alert alert= new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Enregistrement");
			alert.setContentText("Manque de produit en stock. Produits restants "+ p.getQuantite());
			alert.showAndWait();	

		}else {


*/
	

			int status =gestionCommandeDAO.ajouterCommande(com);

			if(status>0) {

				Alert alert= new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Enregistrement");
				alert.setContentText("Commande ajoutée");
				alert.showAndWait();
				

				nomclicom.setValue(null);
				prodcom.setValue(null);
				prixunita.clear();
				
				quantitecl.clear();
				pt.clear();
				datecom.setValue(null);
              
				/*int qterest= p.getQuantite()-com.getQuantite();
				p.setQuantite(qterest);
				String query = "UPDATE produit SET quantite=" +qterest+ " WHERE nom='" + prodcom.getValue() + "'";
		    	executeQuery(query);*/




			}else {

				Alert alert= new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Enregistrement");
				alert.setContentText("Commande non ajoutée");
				alert.showAndWait();	


			} 	

		}

  
   

   

    @FXML
    void modifiercom(ActionEvent event) throws SQLException {

    	String query = "UPDATE commande set nclient='" + nomclicom.getValue()+ "', produitcom='" +prodcom.getValue()+ "',punitaire=" +prixunita.getText()+ ",quant=" +quantitecl.getText()+ ",ptotal=" +pt.getText()+", date='"+datecom.getValue()+"' WHERE numero=" + numcom.getText() + "";
    	executeQuery(query);
    	table4.getItems().clear();
    	affichercom();
    	
    	
		
		Alert alert1= new Alert(AlertType.INFORMATION);
		alert1.setTitle("Modification");
		alert1.setHeaderText("Information");
		alert1.setContentText("Commande bien modifié");
		alert1.showAndWait();	


    }

    @FXML
    void rechercomcli(ActionEvent event) {

    	String cbr1= rcherparcom.getValue();
		String rech1=recherchercom.getText();
		int rechok1 = Integer.parseInt(rech1);
		if(table4.getItems().isEmpty()!=true) {

		
		}else if(rech1.equals(" ")) {
			Alert alert= new Alert(AlertType.ERROR);
			alert.setTitle("Information");
			alert.setHeaderText("recherche");
			alert.setContentText(" selectionner la catégorie svp");
			alert.showAndWait();
			
		}else if(cbr1.equals("Numero")){
			
			//GestionFournisseurDAO.rechercheDAOid(rech);
			
			try {
				String sql ="SELECT * FROM commande WHERE numero= ?";
					Connection con=connexionDB.connect();
					PreparedStatement stat;
					stat = con.prepareStatement(sql);
					stat.setInt(1,rechok1);
					ResultSet rs=stat.executeQuery();
					
					while(rs.next()) {

						Commandes.add(new commande(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getDouble(4),rs.getInt(5), rs.getDouble(6),rs.getString(7)));

					}
					con.close();
					
					
				}catch (SQLException e2) {
					// TODO: handle exception
					e2.printStackTrace();   
				}
	    	
	    	numcom1.setCellValueFactory(new PropertyValueFactory<commande,Integer>("numcommande"));
	    	

			nomcl.setCellValueFactory(new PropertyValueFactory<commande,String>("nomClient"));
			nomcl.setCellFactory(TextFieldTableCell.<commande>forTableColumn());

			produitcl.setCellValueFactory(new PropertyValueFactory<commande,String>("produit"));
			produitcl.setCellFactory(TextFieldTableCell.<commande>forTableColumn());


			prixu.setCellValueFactory(new PropertyValueFactory<commande,Double>("prixunitaire"));
	
			
			qcom.setCellValueFactory(new PropertyValueFactory<commande,Integer>("quantite"));
			
			ptcom.setCellValueFactory(new PropertyValueFactory<commande,Double>("prixtotal"));
			datecom1.setCellValueFactory(new PropertyValueFactory<commande,String>("date"));
			datecom1.setCellFactory(TextFieldTableCell.<commande>forTableColumn());
			
			
			table4.setItems(Commandes);
			recherchercom.clear();
			
		}else if(cbr1.equals("Nom")){
			
			

			try {
				String sql ="SELECT * FROM commande WHERE nom=?";
					Connection con=connexionDB.connect();
					PreparedStatement stat;
					stat = con.prepareStatement(sql);
					stat.setString(1, rech1);
					ResultSet rs=stat.executeQuery();
					
					while(rs.next()) {

						Commandes.add(new commande(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getDouble(4),rs.getInt(5), rs.getDouble(6),rs.getString(7)));

					}
					con.close();
					
					
				}catch (SQLException e2) {
					// TODO: handle exception
					e2.printStackTrace();   
				}



			nomcl.setCellValueFactory(new PropertyValueFactory<commande,String>("nomClient"));
			nomcl.setCellFactory(TextFieldTableCell.<commande>forTableColumn());

			produitcl.setCellValueFactory(new PropertyValueFactory<commande,String>("produit"));
			produitcl.setCellFactory(TextFieldTableCell.<commande>forTableColumn());


			prixu.setCellValueFactory(new PropertyValueFactory<commande,Double>("prixunitaire"));
	
			
			qcom.setCellValueFactory(new PropertyValueFactory<commande,Integer>("quantite"));
			
			ptcom.setCellValueFactory(new PropertyValueFactory<commande,Double>("prixtotal"));
			
			qcom.setCellValueFactory(new PropertyValueFactory<commande,Integer>("quantite"));
			
			
			datecom1.setCellValueFactory(new PropertyValueFactory<commande,String>("date"));
			datecom1.setCellFactory(TextFieldTableCell.<commande>forTableColumn());
			
			
			table4.setItems(Commandes);
			
		}
    }

    @FXML
    void selectioncom(ActionEvent event) {

    	int n1 =table4.getSelectionModel().getSelectedIndex();

		ObservableList<commande> l =table4.getItems();
		commande p = l.get(n1); 

		int num=p.getNumcommande();
		TextFormatter<Integer> a = new TextFormatter<>(new IntegerStringConverter(),num);
		numcom.setTextFormatter(a);

		nomclicom.setValue(p.getNomClient());
		
		nomcli.setText(p.getNomClient());

		prodcom.setValue(p.getProduit());
		designfact.setText(p.getProduit());
		
		TextFormatter<Double> punit = new TextFormatter<>(new DoubleStringConverter(),p.getPrixunitaire());
    	prixunita.setTextFormatter(punit);
    	
    	TextFormatter<Double> punit1 = new TextFormatter<>(new DoubleStringConverter(),p.getPrixunitaire());
    	pufact.setTextFormatter(punit1);
    	
		int qte=p.getQuantite();
		TextFormatter<Integer> n = new TextFormatter<>(new IntegerStringConverter(),qte);
		quantitecl.setTextFormatter(n);
		
		int qte1=p.getQuantite();
		TextFormatter<Integer> n2 = new TextFormatter<>(new IntegerStringConverter(),qte1);
		qfact.setTextFormatter(n2);

		TextFormatter<Double> ptot = new TextFormatter<>(new DoubleStringConverter(),p.getPrixtotal());
    	pt.setTextFormatter(ptot);
    	
    	TextFormatter<Double> ptot1 = new TextFormatter<>(new DoubleStringConverter(),p.getPrixtotal());
    	ptfact.setTextFormatter(ptot1);
    	
		LocalDate datec=LocalDate.parse(p.getDate().toString());
		datecom.setValue(datec);


    }

    
    
    
    @FXML
    void supprimercom(ActionEvent event) throws SQLException {

    	String query = "DELETE FROM commande WHERE numero=" + numcom.getText() + "";
    	executeQuery(query);
    	table4.getItems().clear();
    	
    	Alert alert1= new Alert(AlertType.INFORMATION);
		alert1.setTitle("Suppression");
		alert1.setHeaderText("Information");
		alert1.setContentText("Commande bien supprimé");
		alert1.showAndWait();	
		
    	affichercom();
    	
    	
    }
	
	
   
    
    	void doprintSecond(Node anchor) {
	    	PrinterJob job= PrinterJob.createPrinterJob();
	    	Printer printer= Printer.getDefaultPrinter();
	    	PageLayout pagelayout=printer.createPageLayout(Paper.A3, PageOrientation.LANDSCAPE,MarginType.HARDWARE_MINIMUM);
	    	
	    	
	    	if (job!=null && job.showPrintDialog(Root.getScene().getWindow())) {
	    		boolean printed =job.printPage(pagelayout,anchor);
	    		if(printed) {
	    			job.endJob();
	    		}else {
	    			Alert alert= new Alert(AlertType.CONFIRMATION);
	    			alert.setTitle("Confirmation");
	    			alert.setHeaderText("Impression");
	    			alert.setContentText("Impression ratée");	
	    		}
	    	}else {
	    		Alert alert= new Alert(AlertType.CONFIRMATION);
    			alert.setTitle("Confirmation");
    			alert.setHeaderText("Impression");
    			alert.setContentText("Impression Réussie");
	    	}
	    	
	    }
	    @FXML
	    void imprimer(ActionEvent event) {
	    	
	    	imprimefact.setVisible(false);
	    	doprintSecond(anchor);
	    	

	    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
	        stage.close();
	    	

	    }

			

	    @FXML
	    void statistique(ActionEvent event) throws SQLException  {

	    	try {
	      	 Root.getScene().getWindow().hide();	//permet de cacher la fenetre courante ie la page de connexion pour ouvrir la page d'accueil
	      	 Stage stage= new Stage();
	      	 
	      	 FXMLLoader loader= new FXMLLoader();
	      	 
	      	loader.setLocation(getClass().getResource("/view/statistiqueview.fxml"));
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

		try {
			Connection con = connexionDB.connect();

			String sql = "SELECT nom FROM produit";
			PreparedStatement stat = con.prepareStatement(sql);

			ResultSet rs = stat.executeQuery();
			while(rs.next()) {

				listcomboprod.add(rs.getString(1));
				
			}
			con.close();

		} catch (Exception e) {

			e.printStackTrace();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		prodcom.setItems(listcomboprod);

		rcherparcom.setItems(listerchp);
		
		try {
			Connection con = connexionDB.connect();

			String sql = "SELECT nom FROM client";
			PreparedStatement stat = con.prepareStatement(sql);

			ResultSet rs = stat.executeQuery();
			while(rs.next()) {

				listcomboclient.add(rs.getString(1));
				
			}
			con.close();

		} catch (Exception e) {

			e.printStackTrace();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		nomclicom.setItems(listcomboclient);
	}

}
