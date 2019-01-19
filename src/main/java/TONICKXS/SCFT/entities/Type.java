package TONICKXS.SCFT.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Type implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int idType;
	@OneToMany(mappedBy = "module_type", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Module> modules= new ArrayList<>();
	@OneToMany(mappedBy = "Type_gamme",  cascade = CascadeType.ALL)
	private List<Gamme> gammes;
	private String  Nom_Type;
	private int nbrVariantes;
	private int Numero;
	
	
	
	public int getNumero() {
		return Numero;
	}
	public void setNumero(int numero) {
		Numero = numero;
	}
	public int getIdType() {
		return idType;
	}
	public void setIdType(int idType) {
		this.idType = idType;
	}
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
		
	}
	public String getNom_Type() {
		return Nom_Type;
	}
	public void setNom_Type(String nom_Type) {
		Nom_Type = nom_Type;
	}
	@Override
	public String toString() {
		return  Nom_Type ;
	}
	public List<Gamme> getGammes() {
		return gammes;
	}
	public void setGammes(List<Gamme> gammes) {
		this.gammes = gammes;
	}
	
	public int getNbrVariantes() {
		return nbrVariantes;
	}
	public void setNbrVariantes(int nbrVariantes) {
		this.nbrVariantes = nbrVariantes;
	}
	public void addModule(Module module) {
	    modules.add(module);
	    module.setModule_type(this);
	}
}
