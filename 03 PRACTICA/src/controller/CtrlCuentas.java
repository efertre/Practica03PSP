package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Cuenta;

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
	        // if (ois.available() > 0) { 
	        	
	            lista = (List<Cuenta>) ois.readObject();
//	        } else {
//	            System.out.println("El archivo está vacío.");
//	        }
	    } catch (FileNotFoundException e) {
	        System.out.println("El archivo no se encontró.");
	        lista = null;
	    } catch (IOException e) {
	        System.out.println("Error de entrada/salida: " + e.getMessage());
	    } catch (ClassNotFoundException e) {
	        System.out.println("Error al cargar las clases: " + e.getMessage());
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
	        System.out.println("El archivo no se pudo encontrar.");
	    } catch (IOException e) {
	        System.out.println("Error al escribir en el archivo: " + e.getMessage());
	    }
	}

}
