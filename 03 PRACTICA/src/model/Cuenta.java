package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import javax.swing.JOptionPane;

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
        setNumero(numero);
        setTitular(titular != null && !titular.isEmpty() ? titular : "Desconocido");
        setSaldoMinimo(saldoMinimo);
        setSaldo(saldo);
        setFechaApertura(fechaApertura != null ? fechaApertura : LocalDate.now());
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        if (numero == null || numero < 1 || numero > 1000) {
            throw new IllegalArgumentException("Número debe estar entre 1 y 1000");
        }
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
        if (saldo == null || saldo < saldoMinimo) {
            throw new ESaldoNoValido("Saldo no válido [SALDO INFERIOR QUE EL SALDO MÍNIMO]");
        }
        this.saldo = saldo;
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
        Period periodo = Period.between(getFechaApertura(), LocalDate.now());
        return periodo.getYears() * 12 + periodo.getMonths();
    }

    @Override
    public int cumplirAnyo() {
        Period periodo = Period.between(getFechaApertura(), LocalDate.now());
        return periodo.getYears();
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
                JOptionPane.showMessageDialog(null, "Saldo actualizado con interés anual: " + nuevoSaldo);
            } else if (esDiaDeMes) {
                // Decrementar saldo por comisión mensual
                double comisionMensual = 10.0; // Ejemplo: comisión de 10€
                double nuevoSaldo = cuenta.getSaldo() - comisionMensual;
                cuenta.setSaldo(nuevoSaldo);
                JOptionPane.showMessageDialog(null, "Saldo actualizado con comisión mensual: " + nuevoSaldo);
            } else {
                JOptionPane.showMessageDialog(null, "No se cumple el periodo de cálculo.");
            }
        } catch (ESaldoNoValido ex) {       	
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Saldo Inválido", JOptionPane.ERROR_MESSAGE);
   
        }
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
