package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

import model.exceptions.ESaldoNoValido;

public class Cuenta implements FechaCalculable, Serializable {
	private static final long serialVersionUID = 1L;
	private Integer numero;
	private transient String titular;
	private Double saldo;
	private Double saldoMinimo;
	private LocalDate fechaApertura;

	public Cuenta(Integer numero, String titular, Double saldo, Double saldoMinimo, LocalDate fechaApertura)
			throws ESaldoNoValido {
		super();
		setNumero(numero);
		setTitular(titular);
		setSaldoMinimo(saldoMinimo);
		setSaldo(saldo);
		
		setFechaApertura(fechaApertura);

	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		if (numero >= 1 && numero <= 1000)
			this.numero = numero;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) throws ESaldoNoValido {
		if (saldo != null) {
			if (saldo < saldoMinimo)
				throw new ESaldoNoValido("Saldo no válido [SALDO INFERIOR QUE EL SALDO MÍNIMO]");

			this.saldo = saldo;
		}
	}

	public Double getSaldoMinimo() {
		return saldoMinimo;
	}

	public void setSaldoMinimo(double saldoMinimo) {
		this.saldoMinimo = saldoMinimo;
	}

	public LocalDate getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(LocalDate fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	@Override
	public int cumplirMes() {

		int dias = ((FechaCalculable.ANYO - getFechaApertura().getYear()) * 365
				+ (FechaCalculable.MES - getFechaApertura().getMonthValue()) * 30
				+ (FechaCalculable.DIA_DEL_MES - getFechaApertura().getDayOfMonth()));

		return dias / 30;
	}

	@Override
	public int cumplirAnyo() {
		int dias = ((FechaCalculable.ANYO - getFechaApertura().getYear()) * 365
				+ (FechaCalculable.MES - getFechaApertura().getMonthValue()) * 30
				+ (FechaCalculable.DIA_DEL_MES - getFechaApertura().getDayOfMonth()));

		return dias / 365;
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
		return "Cuenta [numero=" + numero + ", titular=" + titular + ", saldo=" + saldo + ", saldoMinimo=" + saldoMinimo
				+ ", fechaApertura=" + fechaApertura + "]";
	}
	
	

}
