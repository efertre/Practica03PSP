package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PanVerUnoxUno extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	
	private JTextField tfNumero;
	private JTextField tfTitular;
	private JTextField tfSaldo;
	private JTextField tfFecha;
	private JTextField tfComMant;
	private JTextField tfTipo;
	public PanVerUnoxUno() {
		setLayout(null);
		
		JLabel lblTitular = new JLabel("Titular:");
		lblTitular.setBounds(42, 127, 51, 16);
		add(lblTitular);
		
		JLabel lblNumero = new JLabel("Num de cta:");
		lblNumero.setBounds(42, 79, 82, 16);
		add(lblNumero);
		
		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(42, 177, 51, 16);
		add(lblSaldo);
		
		JLabel lblFecha = new JLabel("Fecha de apertura:");
		lblFecha.setBounds(256, 79, 124, 16);
		add(lblFecha);
		
		tfNumero = new JTextField();
		tfNumero.setBounds(134, 74, 110, 26);
		add(tfNumero);
		tfNumero.setColumns(10);
		
		tfTitular = new JTextField();
		tfTitular.setColumns(10);
		tfTitular.setBounds(105, 122, 139, 26);
		add(tfTitular);
		
		tfSaldo = new JTextField();
		tfSaldo.setColumns(10);
		tfSaldo.setBounds(105, 172, 139, 26);
		add(tfSaldo);
		
		tfFecha = new JTextField();
		tfFecha.setColumns(10);
		tfFecha.setBounds(392, 74, 110, 26);
		add(tfFecha);
		
		JLabel lblComMant = new JLabel("Com.ón de mantenimiento:");
		lblComMant.setBounds(256, 127, 174, 16);
		add(lblComMant);
		
		tfComMant = new JTextField();
		tfComMant.setColumns(10);
		tfComMant.setBounds(442, 122, 110, 26);
		add(tfComMant);
		
		JLabel lblTipo = new JLabel("Tipo de comisión:");
		lblTipo.setBounds(256, 177, 124, 16);
		add(lblTipo);
		
		tfTipo = new JTextField();
		tfTipo.setColumns(10);
		tfTipo.setBounds(392, 172, 110, 26);
		add(tfTipo);
		
		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(42, 325, 87, 29);
		add(btnAnterior);
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBounds(435, 325, 87, 29);
		add(btnSiguiente);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setBounds(226, 325, 87, 29);
		add(btnCalcular);
	}
}
