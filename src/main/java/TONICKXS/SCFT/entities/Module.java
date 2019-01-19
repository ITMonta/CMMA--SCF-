package TONICKXS.SCFT.entities;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Module implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int idModule;
	@ManyToOne
	private Type module_type;
	@OneToMany(mappedBy = "variante_module" , cascade = CascadeType.ALL , fetch= FetchType.LAZY)
	private List<Variante> variantes;
	private String Nom_Module;
	public int getIdModule() {
		return idModule;
	}
	public void setIdModule(int idModule) {
		this.idModule = idModule;
	}
	public Type getModule_type() {
		return module_type;
	}
	public void setModule_type(Type module_type) {
		this.module_type = module_type;
	}
	public List<Variante> getVariantes() {
		return variantes;
	}
	public void setVariantes(List<Variante> variantes) {
		this.variantes = variantes;
	}
	public String getNom_Module() {
		return Nom_Module;
	}
	public void setNom_Module(String nom_Module) {
		Nom_Module = nom_Module;
	}
	@Override
	public String toString() {
		return Nom_Module ;
	}
	
}
