package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class client {

	private final IntegerProperty idClient;
	final private StringProperty nom;
	final private StringProperty prenom;
	final private StringProperty CNI;
	final private StringProperty ville;
	final private StringProperty tel;
	
	public client(int idClient, String nom, String prenom,String CNI, String ville, String tel) {
		super();
		this.idClient = new SimpleIntegerProperty(idClient);
		this.nom = new SimpleStringProperty (nom);
		this.prenom = new SimpleStringProperty (prenom);
		this.CNI = new SimpleStringProperty (CNI);
		this.ville = new SimpleStringProperty (ville);
		this.tel = new SimpleStringProperty(tel);
	}
	
	public client() {
		super();
		this.idClient = new SimpleIntegerProperty();
		this.nom = new SimpleStringProperty ();
		this.prenom = new SimpleStringProperty ();
		this.CNI = new SimpleStringProperty ();
		this.ville = new SimpleStringProperty ();
		this.tel = new SimpleStringProperty();
	}

	public final IntegerProperty idClientProperty() {
		return this.idClient;
	}
	

	public final int getIdClient() {
		return this.idClientProperty().get();
	}
	

	public final void setIdClient(final int idClient) {
		this.idClientProperty().set(idClient);
	}
	

	public final StringProperty nomProperty() {
		return this.nom;
	}
	

	public final String getNom() {
		return this.nomProperty().get();
	}
	

	public final void setNom(final String nom) {
		this.nomProperty().set(nom);
	}
	

	public final StringProperty prenomProperty() {
		return this.prenom;
	}
	

	public final String getPrenom() {
		return this.prenomProperty().get();
	}
	

	public final void setPrenom(final String prenom) {
		this.prenomProperty().set(prenom);
	}
	

	public final StringProperty CNIProperty() {
		return this.CNI;
	}
	

	public final String getCNI() {
		return this.CNIProperty().get();
	}
	

	public final void setCNI(final String CNI) {
		this.CNIProperty().set(CNI);
	}
	

	public final StringProperty villeProperty() {
		return this.ville;
	}
	

	public final String getVille() {
		return this.villeProperty().get();
	}
	

	public final void setVille(final String ville) {
		this.villeProperty().set(ville);
	}
	

	public final StringProperty telProperty() {
		return this.tel;
	}
	

	public final String getTel() {
		return this.telProperty().get();
	}
	

	public final void setTel(final String tel) {
		this.telProperty().set(tel);
	}
	

	
	
	
	
	
	
}
