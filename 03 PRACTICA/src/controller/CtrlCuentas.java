package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

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
	        int numero = 1001 + (int) (Math.random() * 9000); // Números mayores a 1000
	        String titular = "Titular" + numero;
	        double saldo = Math.random() * 10000 + 350; // Saldo aleatorio entre 350 y 10,000
	       
	        LocalDate fechaApertura = LocalDate.now().minusDays((long) (Math.random() * 365 * 5)); // Fecha aleatoria en los últimos 5 años

	        // Generar aleatoriamente si será CuentaAhorro o CuentaCorriente
	        boolean esCuentaAhorro = Math.random() < 0.5;

	        try {
	            if (esCuentaAhorro) {
	                // Atributos específicos de CuentaAhorro
	                double interesAnual = 0.01 + Math.random() * 0.05; // Interés entre 1% y 5%
	                double ahorros = 1000 + Math.random() * 9000; // Ahorros entre 1000 y 10000
	                CuentaAhorro cuentaAhorro = new CuentaAhorro(numero, titular, saldo, fechaApertura, interesAnual, ahorros);
	                lista.insertarNodo(cuentaAhorro);
	            } else {
	                // Atributos específicos de CuentaCorriente
	                double comisionMensual = 5 + Math.random() * 15; // Comisión entre 5 y 20
	                TipoComision tipoComision = generarTipoComisionAleatorio(); // Selecciona un tipo aleatorio
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
}
