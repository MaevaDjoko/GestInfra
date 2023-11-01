package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class commande {
	
	private final IntegerProperty numcommande;
	final private StringProperty nomClient;
	final private StringProperty produit;
	final private DoubleProperty prixunitaire;
	final private IntegerProperty quantite;
	final private DoubleProperty prixtotal;
	final private ObjectProperty<String> date;
	
	public commande(int numcommande, String nomClient, String produit,double prixunitaire, int quantite, double prixtotal,  String date) {
		super();
		this.numcommande = new SimpleIntegerProperty(numcommande);
		this.nomClient = new SimpleStringProperty (nomClient);
		this.produit = new SimpleStringProperty (produit);
		this.prixunitaire = new SimpleDoubleProperty (prixunitaire);
		this.quantite = new SimpleIntegerProperty (quantite);
		this.prixtotal = new SimpleDoubleProperty (prixunitaire*quantite);
		this.date = new SimpleObjectProperty<String>(date);
	}
	
	public commande() {
		super();
		this.numcommande = new SimpleIntegerProperty();
		this.nomClient = new SimpleStringProperty ();
		this.produit = new SimpleStringProperty ();
		this.prixunitaire = new SimpleDoubleProperty ();
		this.quantite = new SimpleIntegerProperty ();
		this.prixtotal = new SimpleDoubleProperty ();
		this.date = new SimpleObjectProperty<String>();
	}

	public final IntegerProperty numcommandeProperty() {
		return this.numcommande;
	}
	

	public final int getNumcommande() {
		return this.numcommandeProperty().get();
	}
	

	public final void setNumcommande(final int numcommande) {
		this.numcommandeProperty().set(numcommande);
	}
	

	public final StringProperty nomClientProperty() {
		return this.nomClient;
	}
	

	public final String getNomClient() {
		return this.nomClientProperty().get();
	}
	

	public final void setNomClient(final String nomClient) {
		this.nomClientProperty().set(nomClient);
	}
	

	public final StringProperty produitProperty() {
		return this.produit;
	}
	

	public final String getProduit() {
		return this.produitProperty().get();
	}
	

	public final void setProduit(final String produit) {
		this.produitProperty().set(produit);
	}

	public final DoubleProperty prixunitaireProperty() {
		return this.prixunitaire;
	}
	

	public final double getPrixunitaire() {
		return this.prixunitaireProperty().get();
	}
	

	public final void setPrixunitaire(final double prixunitaire) {
		this.prixunitaireProperty().set(prixunitaire);
	}
	

	public final IntegerProperty quantiteProperty() {
		return this.quantite;
	}
	

	public final int getQuantite() {
		return this.quantiteProperty().get();
	}
	

	public final void setQuantite(final int quantite) {
		this.quantiteProperty().set(quantite);
	}
	

	public final DoubleProperty prixtotalProperty() {
		return this.prixtotal;
	}
	

	public final double getPrixtotal() {
		return this.prixtotalProperty().get();
	}
	

	public final void setPrixtotal(final double prixtotal) {
		this.prixtotalProperty().set(prixtotal);
	}
	

	public final ObjectProperty<String> dateProperty() {
		return this.date;
	}
	

	public final String getDate() {
		return this.dateProperty().get();
	}
	

	public final void setDate(final String date) {
		this.dateProperty().set(date);
	}
	
	

	
	
	

	
	
	
	
	
	
}
