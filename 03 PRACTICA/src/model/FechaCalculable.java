package model;

import java.time.LocalDate;
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

	final int DIA_DEL_MES = LocalDate.now().getDayOfMonth();
	final int MES = LocalDate.now().getMonthValue();
	final int ANYO = LocalDate.now().getYear();
	
	public int cumplirMes();
	public int cumplirAnyo();

}
