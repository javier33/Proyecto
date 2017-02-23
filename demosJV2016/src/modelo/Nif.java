package modelo;

/** Proyecto: Juego de la vida.
 *  Implementa el concepto de Nif según el modelo 1.2
 *  @since: prototipo1.2
 *  @source: Nif.java 
 *  @version: 1.2 - 2017/02/14
 *  @author: ajp
 */

public class Nif {
	private String texto;

	public Nif(String texto) {
		setTexto(texto);
	}

	public Nif() {
		this("00000001R");
	}

	public Nif(Nif nif) {
		this(nif.texto);
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		if (nifValido(texto)) {
			this.texto = texto;
		}
	}

	private boolean nifValido(String texto) {
		if (texto != null) {
			return	texto.matches("^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]") 
					&& letraNIFValida(texto);
		}
		return false;
	}

	/**
	 * Comprueba la validez de la letra de un NIF
	 * @param texto del NIF
	 * @return true si la letra es correcta.
	 */
	private boolean letraNIFValida(String texto) {
		int numeroNIF = Integer.parseInt(texto.substring(0,8));
		if (texto.charAt(8) == "TRWAGMYFPDXBNJZSQVHLCKE".charAt(numeroNIF % 23)) {
			return true;
		}
		return false;
	} 
	  
	@Override
	public String toString() {
		return texto;
	}

	/**
	 * hashCode() complementa al método equals y sirve para comparar objetos de forma 
	 * rápida en estructuras Hash. 
	 * Cuando Java compara dos objetos en estructuras de tipo hash (HashMap, HashSet etc)
	 * primero invoca al método hashcode y luego el equals.
	 * @return un número entero de 32 bit.
	 */
	@Override
	public int hashCode() {
		final int primo = 31;
		int result = 1;
		result = primo * result + ((texto == null) ? 0 : texto.hashCode());
		return result;
	}

	/**
	 * Dos objetos son iguales si: 
	 * Son de la misma clase.
	 * Tienen los mismos valores en los atributos; o son el mismo objeto.
	 * primero invoca al método hashcode y luego el equals.
	 * @return falso si no cumple las condiciones.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj != null && getClass() == obj.getClass()) {
			if (this == obj) {
				return true;
			}
			if (texto.equals(((Nif) obj).texto)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Genera un clon del propio objeto realizando una copia profunda.
	 * @return el objeto clonado.
	 */
	@Override
	public Nif clone() {
		// Utiliza el constructor copia.
		return new Nif(this);
	}

} // class
