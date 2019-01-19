package TONICKXS.SCFT.entities;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Machine implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8678808471232353783L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int idMachine;
	
	
	private String NomMachine;
	public int getIdMachine() {
		return idMachine;
	}
	public void setIdMachine(int idMachine) {
		this.idMachine = idMachine;
	}
	
	public String getNomMachine() {
		return NomMachine;
	}
	public void setNomMachine(String nomMachine) {
		NomMachine = nomMachine;
	}
	@Override
	public String toString() {
		return "" + NomMachine + "";
	}

	
}
