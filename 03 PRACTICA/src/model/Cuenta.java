package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class Cuenta implements FechaCalculable, Serializable {
    private static final long serialVersionUID = 1L;
    private Integer numero;
    private String titular;
    private Double saldo;
    public static Double saldoMinimo = 350.0;
    private LocalDate fechaApertura;

    public Cuenta(Integer numero, String titular, Double saldo, LocalDate fechaApertura) {
        setNumero(numero);
        setTitular(titular);
        setSaldo(saldo);
        setFechaApertura(fechaApertura);
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular != null && !titular.isEmpty() ? titular : "Desconocido";
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura != null ? fechaApertura : LocalDate.now();
    }

    @Override
    public int cumplirMes() {
        return java.time.Period.between(fechaApertura, LocalDate.now()).getMonths();
    }


	@Override
	public int cumplirAnyo() {
		Period periodo = Period.between(getFechaApertura(), LocalDate.now());
		return periodo.getYears();
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject(); // Serializa los campos no transient
		oos.writeUTF(titular == null ? "" : titular); // Serializa manualmente `titular`
	}

	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		ois.defaultReadObject(); // Deserializa los campos no transient
		this.titular = ois.readUTF(); // Deserializa manualmente `titular`
	}

   
    @Override
    public String toString() {
        return String.format("Cuenta: " +
                             "Número: %d " +
                             "Titular: %s " +
                             "Saldo: %.2f " +
                             "Saldo mínimo: %.2f " +
                             "Fecha de apertura: %s",
                             numero, 
                             titular, 
                             saldo, 
                             saldoMinimo, 
                             fechaApertura);
    }
}
