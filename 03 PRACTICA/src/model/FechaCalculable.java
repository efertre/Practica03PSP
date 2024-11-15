package model;

import java.util.Calendar;

/**
 * (Modelo) Desarrolla un interfaz a implementar sobre las clase hijas para los
 * cálculo con fechas. Deberá: 
 * 2.1.- Tener los métodos para controlar si se
 * cumplen meses o años. Dichos métodos no tendrán parámetros y compararán la
 * fecha de apertura de la cuenta y la fecha del sistema. 
 * 
 * 2.2.- Tendrá las
 * constantes de la clase Calendar (DAY_OF_MONTH, MONTH y YEAR) en español.
 */

public interface FechaCalculable {

	final int DIA_DEL_MES = Calendar.DAY_OF_MONTH;
	final int MES = Calendar.MONTH;
	final int ANYO = Calendar.YEAR;
	
	public void cumplirMes();
	public void cumplirAnyo();

}
