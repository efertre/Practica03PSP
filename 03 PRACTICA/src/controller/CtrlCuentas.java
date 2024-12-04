package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import model.Cuenta;
import model.CuentaAhorro;
import model.CuentaCorriente;
import model.TipoComision;
import model.exceptions.ESaldoNoValido;

public class CtrlCuentas {

	/**
	 * 
	 */
	
	private String ruta;

	public CtrlCuentas(String ruta) {

		this.ruta = ruta;

	}

	public Lista<Cuenta> cargarDesdeFichero()  {
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
	        return (Lista<Cuenta>) ois.readObject(); // Deserializa la lista
	    } catch (FileNotFoundException e) {
	        System.err.println("El archivo no se pudo encontrar.");
	    } catch (IOException e) {
	        e.printStackTrace();

	        //System.err.println("Error al leer del archivo: " + e.getStackTrace());
	    } catch (ClassNotFoundException e) {
	        System.err.println("Error: Clase no encontrada al deserializar.");
	    }
	    return null; // Retorna null si ocurre algún error
	}


	public void guardarEnFichero(Lista<Cuenta> lista) {
	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
	        oos.writeObject(lista); // Serializa la lista completa
	        System.out.println("La lista se ha guardado correctamente en: " + ruta);
	    } catch (FileNotFoundException e) {
	        System.err.println("El archivo no se pudo encontrar.");
	    } catch (IOException e) {
	        System.err.println("Error al escribir en el archivo: " + e.getMessage());
	    }
	}
	
	public Lista<Cuenta> cargarTest() throws ESaldoNoValido {
	    Lista<Cuenta> lista = new Lista<>();
	    final int CANT_CUENTA_CORRIENTE = 2;
	    final int CANT_CUENTA_AHORRO = 2;
	    int num_cuenta_aleatorio = 0;
	    int cont = 1;
	    
	    for (int i = 0; i < CANT_CUENTA_CORRIENTE; i++) {
	    	num_cuenta_aleatorio = (int) (Math.random() * 1000) + 1;
	    	
	    	CuentaCorriente cc = new CuentaCorriente(num_cuenta_aleatorio, "TEST" + cont, 1000.0, LocalDate.now(), 100.0, TipoComision.Estudio);
	    	lista.insertarNodo(cc);
	    	
	    	cont++;
		}
	    
	    for (int i = 0; i < CANT_CUENTA_AHORRO; i++) {
	    	num_cuenta_aleatorio = (int) (Math.random() * 1000) + 1;
		
	    	CuentaAhorro ca = new CuentaAhorro(num_cuenta_aleatorio, "TEST" + cont, 1000.0, LocalDate.now(), 100.0, 200.0);
	    	lista.insertarNodo(ca);
	    	
	    	cont++;
		}
	    
	    return lista;
	}
	
	// Metodo estatico que comprueba si una fecha es futura (devuelve false si no)s

	public static boolean comprobarFechaFutura(LocalDate fecha) {
	    return LocalDate.now().isBefore(fecha);
	}
	
	
	public Lista<Cuenta> generarCuentasAleatorias(Lista<Cuenta> lista, int cantidad) {
	    for (int i = 0; i < cantidad; i++) {    
	        // Generación de atributos aleatorios
	        int numero = 1001 + (int) (Math.random() * 9000); // Números mayores a 1000
	        String titular = "Titular" + numero;
	        
	        double saldoMinimo = 350.0; // Definir un saldo mínimo válido
	        double saldo = saldoMinimo + Math.random() * (10000 - saldoMinimo); // Saldo entre 350 y 10,000
//	        //LocalDate fechaApertura = LocalDate.now().minusDays((long) (Math.random() * 365 * 5)); // Fecha aleatoria en los últimos 5 años
//	     // Generar la fecha de apertura con el día 3
	        LocalDate fechaApertura = LocalDate.now()
	            .minusYears((long) (Math.random() * 5)) // Año aleatorio dentro de los últimos 5 años
	            .withMonth(1 + (int) (Math.random() * 12)) // Mes aleatorio entre 1 y 12
	            .withDayOfMonth(4); // Día fijo 3

	        
	        // Generar aleatoriamente si será CuentaAhorro o CuentaCorriente
	        boolean esCuentaAhorro = Math.random() < 0.5;
	   
	    	
	        
	        // Depuración de los valores generados
	        System.out.println("Generando cuenta " + (i + 1) + ":");
	        System.out.println("  Número: " + numero);
	        System.out.println("  Titular: " + titular);
	        System.out.println("  Saldo: " + saldo);
	        System.out.println("  Fecha de apertura: " + fechaApertura);
	        System.out.println("  Es CuentaAhorro: " + esCuentaAhorro);
	        
	        try {
	            if (esCuentaAhorro) {
	                // Atributos específicos de CuentaAhorro

	            	double interesAnual = 0.02;
	            	double ahorros = 2000;
	                
	                // Depuración de los atributos específicos de CuentaAhorro
	                System.out.println("  Interés Anual (CuentaAhorro): " + interesAnual);
	                System.out.println("  Ahorros (CuentaAhorro): " + ahorros);
	                
	                CuentaAhorro cuentaAhorro = new CuentaAhorro(numero, titular, saldo, fechaApertura, interesAnual, ahorros);
	                lista.insertarNodo(cuentaAhorro);
	            } else {
	                // Atributos específicos de CuentaCorriente
	            	
	                double comisionMensual = 5 + Math.random() * 15; // Comisión entre 5 y 20
	                TipoComision tipoComision = generarTipoComisionAleatorio(); // Selecciona un tipo aleatorio
	            
	                
	                // Depuración de los atributos específicos de CuentaCorriente
	                System.out.println("  Comisión Mensual (CuentaCorriente): " + comisionMensual);
	                System.out.println("  Tipo de Comisión (CuentaCorriente): " + tipoComision);
	                
	                CuentaCorriente cuentaCorriente = new CuentaCorriente(numero, titular, saldo, fechaApertura, comisionMensual, tipoComision);
	                lista.insertarNodo(cuentaCorriente);
	            }
	        } catch (ESaldoNoValido e) {
	            System.err.println("Error al generar cuenta: " + e.getMessage());
	        }
	    }
	    return lista;
	}



	private TipoComision generarTipoComisionAleatorio() {
		
		    TipoComision[] valores = TipoComision.values();
		    int indiceAleatorio = (int) (Math.random() * valores.length);
		    return valores[indiceAleatorio];
		
	}
	
	public void calcularSaldo(Cuenta cuenta) {
		LocalDate hoy = LocalDate.now();
		boolean esDiaDeMes = cuenta.getFechaApertura().getDayOfMonth() == hoy.getDayOfMonth();
		boolean esDiaDeAnio = esDiaDeMes && cuenta.getFechaApertura().getMonthValue() == hoy.getMonthValue();

		if (esDiaDeAnio) {
			// Incrementar saldo por interés anual
			double interesAnual = 0.05; // Ejemplo: 5% de interés
			double nuevoSaldo = cuenta.getSaldo() * (1 + interesAnual);
			cuenta.setSaldo(nuevoSaldo);
			JOptionPane.showMessageDialog(null, "Saldo actualizado con interés anual: " + String.format("%.2f", nuevoSaldo));
		} else if (esDiaDeMes) {
			// Decrementar saldo por comisión mensual
			double comisionMensual = 10.0; // Ejemplo: comisión de 10€
			double nuevoSaldo = cuenta.getSaldo() - comisionMensual;
			cuenta.setSaldo(nuevoSaldo); 
			JOptionPane.showMessageDialog(null, "Saldo actualizado con comisión mensual: " + 	String.format("%.2f", nuevoSaldo));
		} else {
			JOptionPane.showMessageDialog(null, "No se cumple el periodo de cálculo.");
		}
	}
}
