package TONICKXS.SCFT.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.eclipse.persistence.jpa.config.Cascade;

@Entity
public class Variante_Gamme implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int idGammevariante;
	@ManyToOne
	private Variante Gamme_variantes;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Gamme variante_gamme ;
	
	public int getIdGammevariante() {
		return idGammevariante;
	}
	public void setIdGammevariante(int idGammevariante) {
		this.idGammevariante = idGammevariante;
	}
	public Variante getGamme_variantes() {
		return Gamme_variantes;
	}
	public void setGamme_variantes(Variante gamme_variantes) {
		Gamme_variantes = gamme_variantes;
	}
	public Gamme getVariante_gamme() {
		return variante_gamme;
	}
	public void setVariante_gamme(Gamme variante_gamme) {
		this.variante_gamme = variante_gamme;
	}


}
