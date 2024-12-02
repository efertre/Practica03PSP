package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanVerUnoxUno extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField tfNumero, tfTitular, tfSaldo, tfFecha, tfComMant, tfTipo;
	private JLabel lblTitular, lblNumero, lblSaldo, lblFecha, lblNewLabel, lblComMant, lblTipo;
	private JButton btnAnterior, btnSiguiente, btnCalcular;

	public PanVerUnoxUno() {
		setLayout(null);

		addComponents();
		addListeners();
	}

	private void addListeners() {

		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
	}

	private void addComponents() {

		lblTitular = new JLabel("Titular:");
		lblTitular.setBounds(220, 38, 51, 16);
		add(lblTitular);

		lblNumero = new JLabel("Num de cta:");
		lblNumero.setBounds(49, 38, 82, 16);
		add(lblNumero);

		lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(159, 97, 38, 16);
		add(lblSaldo);

		lblFecha = new JLabel("Fecha de apertura:");
		lblFecha.setBounds(82, 130, 115, 16);
		add(lblFecha);

		tfNumero = new JTextField();
		tfNumero.setEnabled(false);
		tfNumero.setEditable(false);
		tfNumero.setBounds(131, 33, 51, 26);
		add(tfNumero);
		tfNumero.setColumns(10);

		tfTitular = new JTextField();
		tfTitular.setEditable(false);
		tfTitular.setEnabled(false);
		tfTitular.setColumns(10);
		tfTitular.setBounds(269, 33, 139, 26);
		add(tfTitular);

		tfSaldo = new JTextField();
		tfSaldo.setEditable(false);
		tfSaldo.setEnabled(false);
		tfSaldo.setColumns(10);
		tfSaldo.setBounds(209, 92, 139, 26);
		add(tfSaldo);

		tfFecha = new JTextField();
		tfFecha.setEditable(false);
		tfFecha.setEnabled(false);
		tfFecha.setColumns(10);
		tfFecha.setBounds(209, 125, 139, 26);
		add(tfFecha);

		lblComMant = new JLabel("Comisión de");
		lblComMant.setBounds(91, 162, 87, 16);
		add(lblComMant);

		tfComMant = new JTextField();
		tfComMant.setEditable(false);
		tfComMant.setEnabled(false);
		tfComMant.setColumns(10);
		tfComMant.setBounds(209, 163, 139, 26);
		add(tfComMant);

		lblTipo = new JLabel("Tipo de comisión:");
		lblTipo.setBounds(84, 206, 113, 16);
		add(lblTipo);

		tfTipo = new JTextField();
		tfTipo.setEditable(false);
		tfTipo.setEnabled(false);
		tfTipo.setColumns(10);
		tfTipo.setBounds(209, 201, 139, 26);
		add(tfTipo);

		JButton btnAnterior = new JButton("Anterior");

		
		btnAnterior.setBounds(34, 271, 87, 29);
		add(btnAnterior);

		JButton btnSiguiente = new JButton("Siguiente");

		btnSiguiente.setBounds(338, 271, 87, 29);
		add(btnSiguiente);

		JButton btnCalcular = new JButton("Calcular");

		btnCalcular.setBounds(188, 271, 87, 29);
		add(btnCalcular);

		JLabel lblNewLabel = new JLabel("mantenimiento:");
		lblNewLabel.setBounds(87, 178, 104, 16);
		add(lblNewLabel);
	}
}
