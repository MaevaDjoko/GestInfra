package model;



import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class fournisseur {

	private final IntegerProperty identifiant;
	final private StringProperty nom;
	final private StringProperty adresse;
	final private StringProperty ville;
	final private StringProperty email;
	final private StringProperty categorieM;
	
	public fournisseur(int identifiant, String nom, String adresse, String ville,String email, String categorieM) {
		super();
		this.identifiant = new SimpleIntegerProperty(identifiant);
		this.nom = new SimpleStringProperty (nom);
		this.adresse = new SimpleStringProperty (adresse);
		this.ville = new SimpleStringProperty (ville);
		this.email = new SimpleStringProperty (email);
		this.categorieM = new SimpleStringProperty (categorieM);
	}
	
	public fournisseur() {
		super();
		this.identifiant = new SimpleIntegerProperty();
		this.nom = new SimpleStringProperty ();
		this.adresse = new SimpleStringProperty ();
		this.ville = new SimpleStringProperty ();
		this.email = new SimpleStringProperty ();
		this.categorieM = new SimpleStringProperty ();
	}

	public final IntegerProperty identifiantProperty() {
		return this.identifiant;
	}
	

	public final int getIdentifiant() {
		return this.identifiantProperty().get();
	}
	

	public final void setIdentifiant(final int identifiant) {
		this.identifiantProperty().set(identifiant);
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
	

	public final StringProperty adresseProperty() {
		return this.adresse;
	}
	

	public final String getAdresse() {
		return this.adresseProperty().get();
	}
	

	public final void setAdresse(final String adresse) {
		this.adresseProperty().set(adresse);
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
	

	public final StringProperty emailProperty() {
		return this.email;
	}
	

	public final String getEmail() {
		return this.emailProperty().get();
	}
	

	public final void setEmail(final String email) {
		this.emailProperty().set(email);
	}
	

	public final StringProperty categorieMProperty() {
		return this.categorieM;
	}
	

	public final String getCategorieM() {
		return this.categorieMProperty().get();
	}
	

	public final void setCategorieM(final String categorieM) {
		this.categorieMProperty().set(categorieM);
	}
	
	
	
	
	
	
	
}
