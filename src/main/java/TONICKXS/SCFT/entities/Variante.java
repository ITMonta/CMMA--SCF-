package TONICKXS.SCFT.entities;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Variante implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int idVariante;
	@ManyToOne
	private Module variante_module;
	@OneToMany(mappedBy = "Gamme_variantes" )
	private List<Variante_Gamme> Gammes;
	@OneToMany(mappedBy = "variante_operation", cascade = CascadeType.ALL )
	private List<Operation> operations;
	@Lob
	@Basic(fetch=FetchType.LAZY)
	protected byte[] picture;
	private String image;
	private int numero;
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getNom_variante() {
		return Nom_variante;
	}
	public void setNom_variante(String nom_variante) {
		Nom_variante = nom_variante;
	}
	private String Nom_variante;
	public int getIdVariante() {
		return idVariante;
	}
	public void setIdVariante(int idVariante) {
		this.idVariante = idVariante;
	}
	public Module getVariante_module() {
		return variante_module;
	}
	public void setVariante_module(Module variante_module) {
		this.variante_module = variante_module;
	}

	public List<Variante_Gamme> getGammes() {
		return Gammes;
	}
	public void setGammes(List<Variante_Gamme> gammes) {
		Gammes = gammes;
	}
	public List<Operation> getOperations() {
		return operations;
	}
	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return Nom_variante;
	}
	
	
	
	
}
