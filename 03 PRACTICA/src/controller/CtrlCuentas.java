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
		Lista<Cuenta> lista1 = new Lista<>();

		for (int i = 0; i < cantidad; i++) {
			double tipo = Math.random(); // Determina si será CuentaAhorro o CuentaCorriente
			int numero = (int) (Math.random() * 1000) + 1; // Genera un número aleatorio para el número de cuenta
			String titular = "Titular" + (i + 1); // Genera un titular único
			double saldoMinimo = 350.0; // Definir un saldo mínimo válido
            double saldo = saldoMinimo + Math.random() * (10000 - saldoMinimo); // Saldo entre 350 y 10,000

			LocalDate fechaApertura = LocalDate.now()
	                .minusYears((long) (Math.random() * 5)) // Año aleatorio dentro de los últimos 5 años
	                .withMonth(1 + (int) (Math.random() * 12)) // Mes aleatorio entre 1 y 12
	                .withDayOfMonth(3); // Día fijo 3
			
			if (tipo < 0.5) {
				// Crear CuentaAhorro
				try {
					double interesAnual = (Math.random() * 10) / 100; // Genera un interés anual aleatorio
					double ahorro = Math.random() * 1000; // Genera una rentabilidad aleatoria
					CuentaAhorro ca = new CuentaAhorro(numero, titular,  saldo, fechaApertura,
							interesAnual, ahorro);
					lista1.insertarNodo(ca); // Inserta la cuenta en la lista
				} catch (ESaldoNoValido  e) {
					e.printStackTrace();
				}
			} else {
				// Crear CuentaCorriente
				try {
					TipoComision tipoComision = generarTipoComisionAleatorio(); // Selecciona un tipo aleatorio
					double comision = Math.random() * 50; // Genera una comisión aleatoria
					
					CuentaCorriente cc = new CuentaCorriente(numero, titular, saldo,
							fechaApertura, comision, tipoComision);
					lista1.insertarNodo(cc); // Inserta la cuenta en la lista
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
	    }
	    return lista1;
	}



	private TipoComision generarTipoComisionAleatorio() {
		
		    TipoComision[] valores = TipoComision.values();
		    int indiceAleatorio = (int) (Math.random() * valores.length);
		    return valores[indiceAleatorio];
		
	}
	
	public void calcularSaldo(Cuenta cuenta) {
		try {
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
		} catch (ESaldoNoValido ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Saldo Inválido",
					JOptionPane.ERROR_MESSAGE);

		}
	}
}
