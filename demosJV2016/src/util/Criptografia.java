/** 
 * Proyecto: Juego de la vida.
 *  Clase-utilidades de encriptación.
 *  @since: prototipo1.2
 *  @source: Criptografia.java 
 *  @version: 2.0 - 2017/03/11
 *  @author: ajp
 */

package util;

public class Criptografia {

	/**
	 * Método estático para encriptar un texto.
	 * Utiliza encriptación simple de Cesar basada en dos alfabetos desplazados 4 posiciones.
	 * Mantiene mayúsculas, minúsculas y espacios.
	 * @param textoClaro 
	 * @return textoEncriptado. 
	 * @ 
	 */
	public static String cesar(String textoClaro) {
		String alfaNormal =     "AaBbCcDdEeFfGgHhIiJjKkLlMmNnÑñOoPpQqRrSsTtUuVvXxYyZz0123456789!?$%&/#";
		String alfaDesplazado = "EeFfGgHhIiJjKkLlMmNnÑñOoPpQqRrSsTtUuVvXxYyZz0123456789!?$%&/#AaBbCcDd";
		char charAEncriptar;
		int posCharAEncriptar;
		StringBuilder  textoEncriptado = new StringBuilder();

		for (int i=0; i < textoClaro.length(); i++ ){
			charAEncriptar = textoClaro.charAt(i);
			posCharAEncriptar = alfaNormal.indexOf(charAEncriptar);
			textoEncriptado.append(alfaDesplazado.charAt(posCharAEncriptar));

		}
		return textoEncriptado.toString();
	}
	
	/**
	 * Método estático para encriptar un texto.
	 * Utiliza encriptación de Cesar basada en dos alfabetos desplazados aleatoriamente.
	 * @param textoClaro
	 * @return textoEncriptado. 
	 */
	public static String cesarAl(String textoClaro) {
		return cesarAl(textoClaro, null);
	}

	/**
	 * Método estático para encriptar un texto.
	 * Utiliza encriptación de Cesar basada en dos alfabetos desplazados aleatoriamente.
	 * No mantiene mayúsculas y minúsculas ni espacios.
	 * @param textoClaro
	 * @param secuenciaDesplazamientos
	 * @return textoEncriptado. 
	 */
	public static String cesarAl(String textoClaro, String secuenciaDesplamientos) {
		final int LONGITUD = 5;
		int desplazamiento;
		char charAEncriptar;
		int posCharAEncriptar;
		String alfaNormal = "ABCDEFGHIJKLMNÑOPQRSTUVXYZabcdefghijklmnñopqrstuvxyz0123456789!?$%&/ ";
		StringBuilder  textoEncriptado = new StringBuilder();
		if (secuenciaDesplamientos.length() < LONGITUD) {
			StringBuilder secuencia = new StringBuilder();
			for (int i=0; i < LONGITUD; i++) {
				secuencia.append((int) (Math.random()*10));
			}	
		secuenciaDesplamientos = secuencia.toString();
		}
		
		for (int i=0; i < textoClaro.length(); i++ ){
			desplazamiento = secuenciaDesplamientos.charAt(i%LONGITUD);
			StringBuilder alfaDesplazado = new StringBuilder(alfaNormal.substring(desplazamiento) 
					+ alfaNormal.substring(0,desplazamiento));

			charAEncriptar = textoClaro.charAt(i);
			posCharAEncriptar = alfaNormal.indexOf(charAEncriptar);
			textoEncriptado.append(alfaDesplazado.charAt(posCharAEncriptar));
		}

		return textoEncriptado.toString();
	}
	
	/**
	 * Encripta un texto según una clave utilizando OR exclusivo binario.
	 * El algoritmo es simétrico, con un texto encriptado con la misma clave se devuelve en claro. 
	 * @param texto - en claro.
	 * @param clave - de encriptación.
	 * @return - el texto encriptado.
	 */
	public static String orex(String texto, String clave) {

		StringBuilder aux = new StringBuilder();

		for (int i=0; i < texto.length(); i++)

			aux.append((char)(texto.charAt(i) ^ clave.charAt(i % clave.length())));

		return aux.toString();
	}

	
} // class
