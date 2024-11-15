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

public class CtrlCuentas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ruta;

	public CtrlCuentas(String ruta) {

		this.ruta = ruta;

	}

	public List<Cuenta> cargarDesdeFichero() throws FileNotFoundException, IOException, ClassNotFoundException {
		List<Cuenta> lista = new ArrayList<>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
			lista = (List<Cuenta>) ois.readObject();
		} 
		
		// SE CONTROLA LA EXCEPCIÓN EN VISTA
		
		return lista;
	}

	// Método para guardar objetos de una lista en un fichero
	public void guardarEnFichero(List<Cuenta> lista) throws FileNotFoundException, IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
			oos.writeObject(lista);
		} 
		
		// SE CONTROLA LA EXCEPCIÓN EN VISTA
	}
}
