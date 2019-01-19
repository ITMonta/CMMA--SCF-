package TONICKXS.SCFT.entities;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
public class Gamme implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int idGamme;
	@OneToMany(mappedBy = "variante_gamme"  , cascade = CascadeType.ALL)
	private List<Variante_Gamme> variantes ;
	@Lob
	@Basic(fetch=FetchType.LAZY)
	protected byte[] C_Design ;
	private String C_designurl;
	private String Nom_Gamme;
	private Float Temps;
	
	@ManyToOne(cascade =CascadeType.MERGE)
	private Type Type_gamme;
	
	public int getIdGamme() {
		return idGamme;
	}
	public void setIdGamme(int idGamme) {
		this.idGamme = idGamme;
	}
	
	
	public List<Variante_Gamme> getVariantes() {
		return variantes;
	}
	public void setVariantes(List<Variante_Gamme> variantes) {
		
		this.variantes=variantes;
	}
	public String getNom_Gamme() {
		return Nom_Gamme;
	}
	public void setNom_Gamme(String nom_Gamme) {
		Nom_Gamme = nom_Gamme;
	}
	public Float getTemps() {
		return Temps;
	}
	public void setTemps(Float temps) {
		Temps = temps;
	}
	public Type getType_gamme() {
		return Type_gamme;
	}
	public void setType_gamme(Type type_gamme) {
		Type_gamme = type_gamme;
	}
	public String getC_designurl() {
		return C_designurl;
	}
	public void setC_designurl(String c_designurl) {
		C_designurl = c_designurl;
	}
	public byte[] getC_Design() {
		return C_Design;
	}
	public void setC_Design(byte[] c_Design) {
		C_Design = c_Design;
	}
	
}
