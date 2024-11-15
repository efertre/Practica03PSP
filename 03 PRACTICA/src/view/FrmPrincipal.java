package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class FrmPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	

	/**
	 * Crea el frame
	 */
	public FrmPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setLocationRelativeTo(null);	
		setTitle("Práctica 03 - Ema y Paula");
		

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JMenuBar mnPrincipal = new JMenuBar();
		contentPane.add(mnPrincipal, BorderLayout.NORTH);
		
		JMenu mnOpciones = new JMenu("Opciones");
		mnPrincipal.add(mnOpciones);
		
		JMenuItem mntmVaciarLista = new JMenuItem("Vaciar Lista");
		mnOpciones.add(mntmVaciarLista);
		mntmVaciarLista.setHorizontalAlignment(SwingConstants.CENTER);
		
		JMenuItem mntmCargarTest = new JMenuItem("Cargar Test");
		mnOpciones.add(mntmCargarTest);
		
		JSeparator separator = new JSeparator();
		mnOpciones.add(separator);
		
		JMenuItem mntmCargarDatos = new JMenuItem("Cargar datos");
		mnOpciones.add(mntmCargarDatos);
		mntmCargarDatos.setHorizontalAlignment(SwingConstants.CENTER);
		
		JMenuItem mntmGuardarDatos = new JMenuItem("GuardarDatos");
		mnOpciones.add(mntmGuardarDatos);
		mntmGuardarDatos.setHorizontalAlignment(SwingConstants.CENTER);
		
		JMenu mnVer = new JMenu("Ver");
		mnVer.setHorizontalAlignment(SwingConstants.CENTER);
		mnPrincipal.add(mnVer);
		
		JMenuItem mntmListar = new JMenuItem("Listar todos");
		mnVer.add(mntmListar);
		
		JMenuItem mntmUnoXUno = new JMenuItem("Visualizar uno a uno");
		mnVer.add(mntmUnoXUno);
		
		JMenu mnInsertar = new JMenu("Insertar");
		mnPrincipal.add(mnInsertar);
		
		JMenuItem mntmNewCtaAhorro = new JMenuItem("Nueva cuenta de ahorro");
		mnInsertar.add(mntmNewCtaAhorro);
		
		JMenuItem mntmNewCtaCorriente = new JMenuItem("Nueva cuenta corriente");
		mnInsertar.add(mntmNewCtaCorriente);
	}
}
