package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

	public List<Cuenta> cargarDesdeFichero() {
	    List<Cuenta> lista = new ArrayList<>();
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
	        // Comprobamos si hay algo que leer
	    	lista = (List<Cuenta>) ois.readObject();
       
	    } catch (FileNotFoundException e) {
	        System.err.println("El archivo no se encontró.");
	        lista = null;
	    } catch (IOException e) {
	        System.err.println("No hay cuentas guardadas en el fichero.");
	    } catch (ClassNotFoundException e) {
	        System.err.println("Error al cargar las clases: " + e.getMessage());
	    }
	    
	    return lista;
	}

	public void guardarEnFichero(List<Cuenta> lista) {
	    if (lista == null || lista.isEmpty()) {
	        System.out.println("No hay datos para guardar.");
	        return; 
	    }
	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
	        oos.writeObject(lista);
	    } catch (FileNotFoundException e) {
	        System.err.println("El archivo no se pudo encontrar.");
	    } catch (IOException e) {
	        System.err.println("Error al escribir en el archivo: " + e.getMessage());
	    }
	}
	
	public List<Cuenta> cargarTest() throws ESaldoNoValido {
	    List<Cuenta> lista = new ArrayList<>();
	    final int CANT_CUENTA_CORRIENTE = 2;
	    final int CANT_CUENTA_AHORRO = 2;
	    int num_cuenta_aleatorio = 0;
	    int cont = 1;
	    
	    for (int i = 0; i < CANT_CUENTA_CORRIENTE; i++) {
	    	num_cuenta_aleatorio = (int) (Math.random() * 1000) + 1;
	    	System.out.println(num_cuenta_aleatorio);
	    	CuentaCorriente cc = new CuentaCorriente(num_cuenta_aleatorio, "TEST" + cont, 1000.0, 10.0, LocalDate.now(), 100.0, TipoComision.Estudio);
	    	lista.add(cc);
	    	
	    	cont++;
		}
	    
	    for (int i = 0; i < CANT_CUENTA_AHORRO; i++) {
	    	num_cuenta_aleatorio = (int) (Math.random() * 1000) + 1;
			System.out.println(num_cuenta_aleatorio);
	    	CuentaAhorro ca = new CuentaAhorro(num_cuenta_aleatorio, "TEST" + cont, 1000.0, 10.0, LocalDate.now(), 100.0, 200.0);
	    	lista.add(ca);
	    	
	    	cont++;
		}
	    
	    return lista;
	}

}
