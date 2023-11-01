package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class produit {
	private final IntegerProperty identifiantp;
	final private StringProperty nom;
	final private StringProperty categoriep;
	final private DoubleProperty prixunitaire;
	final private IntegerProperty quantite;
	final private DoubleProperty remise;
	final private DoubleProperty prixvente;
	
	
	
	public produit(int identifiantp, String nom, String categoriep, double prixunitaire, int quantite, double remise, double prixvente) {
		super();
		this.identifiantp = new SimpleIntegerProperty(identifiantp);
		this.nom = new SimpleStringProperty(nom);
		this.categoriep = new SimpleStringProperty(categoriep);
		this.prixunitaire = new SimpleDoubleProperty(prixunitaire);
		this.quantite = new SimpleIntegerProperty(quantite);
		this.remise = new SimpleDoubleProperty(remise);;
		this.prixvente = new SimpleDoubleProperty(prixunitaire*(1-remise));;
	}
	
	public produit() {
		super();
		this.identifiantp = new SimpleIntegerProperty();
		this.nom = new SimpleStringProperty();
		this.categoriep = new SimpleStringProperty();
		this.prixunitaire = new SimpleDoubleProperty();
		this.quantite = new SimpleIntegerProperty();
		this.remise = new SimpleDoubleProperty();;
		this.prixvente = new SimpleDoubleProperty();;
	}
	
	

	public final IntegerProperty identifiantpProperty() {
		return this.identifiantp;
	}
	

	public final int getIdentifiantp() {
		return this.identifiantpProperty().get();
	}
	

	public final void setIdentifiantp(final int identifiantp) {
		this.identifiantpProperty().set(identifiantp);
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
	

	public final StringProperty categoriepProperty() {
		return this.categoriep;
	}
	

	public final String getCategoriep() {
		return this.categoriepProperty().get();
	}
	

	public final void setCategoriep(final String categoriep) {
		this.categoriepProperty().set(categoriep);
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
	

	public final DoubleProperty remiseProperty() {
		return this.remise;
	}
	

	public final double getRemise() {
		return this.remiseProperty().get();
	}
	

	public final void setRemise(final double remise) {
		this.remiseProperty().set(remise);
	}
	

	public final DoubleProperty prixventeProperty() {
		return this.prixvente;
	}
	

	public final double getPrixvente() {
		return this.prixventeProperty().get();
	}
	

	public final void setPrixvente(final double prixvente) {
		this.prixventeProperty().set(prixvente);
	}
	
	
	
	
	
	
}
