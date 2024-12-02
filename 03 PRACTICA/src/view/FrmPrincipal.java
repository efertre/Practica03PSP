package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.CtrlCuentas;
import model.Cuenta;
import model.exceptions.ESaldoNoValido;

public class FrmPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panPrincipal, panJList, panVerUnoxUno;
	
	private String ruta = "prueba.dat";

	private CtrlCuentas ctrl = new CtrlCuentas(ruta);
	private List<Cuenta> cuentas = new ArrayList<>() ;
	
	private JMenu mnOpciones, mnVer, mnInsertar;
	private JMenuBar mnPrincipal;
	private JMenuItem mntmVaciarLista, mntmCargarTest, mntmCargarDatos, mntmGuardarDatos, mntmListar, mntmUnoXUno, mntmNewCtaAhorro,  mntmNewCtaCorriente;
	
	

	/**
	 * Crea el frame
	 */
	public FrmPrincipal() {
		
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 380);
		
		setResizable(false);
		setLocationRelativeTo(null);	
		setTitle("Práctica 03 - Ema y Paula");
		
		
		addComponents();
		addListeners();
		
		
		
	}


	private void addListeners() {
		
		mntmVaciarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cuentas = null;
				((PanelJLista) panJList).actualizarLista(cuentas);
			}
		});
		

		mntmCargarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cuentas = ctrl.cargarDesdeFichero();
				
				
				// DEPURACIÓN 
				for (Cuenta cuenta : cuentas) {
					System.out.println(cuenta);
				}
				
				((PanelJLista) panJList).actualizarLista(cuentas);
				
			}
		});
		
		
		
		mntmGuardarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cuenta c;
				try {
					
					c = new Cuenta(1, "Prueba", 200.0, 100.0, LocalDate.now());
					cuentas.add(c);
					ctrl.guardarEnFichero(cuentas);
					
				} catch (ESaldoNoValido e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
			}
		});
		
		
		
		mntmListar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	
		        panJList = new PanelJLista(cuentas);

		        panPrincipal.remove(panVerUnoxUno);

		        // Añadir el nuevo panel
		        panPrincipal.add(panJList, BorderLayout.CENTER);

		        // Actualizar la interfaz
		        panPrincipal.revalidate();
		        panPrincipal.repaint();
		    }
		});
		
		mntmCargarTest.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	
		        try {
					
		        	cuentas = ctrl.cargarTest();
					((PanelJLista) panJList).actualizarLista(cuentas);
					
				} catch (ESaldoNoValido e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
			
		
		mntmUnoXUno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panVerUnoxUno = new PanVerUnoxUno();

				panPrincipal.remove(panJList);

		        // Añadir el nuevo panel
		        panPrincipal.add(panVerUnoxUno, BorderLayout.CENTER);

		        // Actualizar la interfaz
		        panPrincipal.revalidate();
		        panPrincipal.repaint(); 
			}
		});
	}


	private void addComponents() {
		
		
		cuentas = ctrl.cargarDesdeFichero();

		panPrincipal = new JPanel();
		panPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panPrincipal);
		panPrincipal.setLayout(new BorderLayout(0, 0));

		
		
		panJList = new PanelJLista(cuentas);
		
		
		mntmVaciarLista = new JMenuItem("Vaciar Lista");
		mntmVaciarLista.setHorizontalAlignment(SwingConstants.LEFT);
		
		mntmCargarTest = new JMenuItem("Cargar Test");
		mntmCargarTest.setHorizontalAlignment(SwingConstants.LEFT);
		
		mntmCargarDatos = new JMenuItem("Cargar Datos");
		mntmCargarDatos.setHorizontalAlignment(SwingConstants.LEFT);
		
		mntmGuardarDatos = new JMenuItem("Guardar Datos");
		mntmGuardarDatos.setHorizontalAlignment(SwingConstants.LEFT);
		
		mnOpciones = new JMenu("Opciones");
		
		mnOpciones.add(mntmVaciarLista);
		mnOpciones.add(mntmCargarTest);
		
		JSeparator separator = new JSeparator();
		mnOpciones.add(separator);
		
		mnOpciones.add(mntmCargarDatos);
		mnOpciones.add(mntmGuardarDatos);

		
		
		mntmListar = new JMenuItem("Listar todos");
		
		mntmUnoXUno = new JMenuItem("Visualizar uno a uno");


		mnVer = new JMenu("Ver");
		mnVer.setHorizontalAlignment(SwingConstants.CENTER);
		
		mnVer.add(mntmListar);
		mnVer.add(mntmUnoXUno);


		
		mntmNewCtaAhorro = new JMenuItem("Nueva cuenta de ahorro");
		mntmNewCtaCorriente = new JMenuItem("Nueva cuenta corriente");

		
		mnInsertar = new JMenu("Insertar");

		mnInsertar.add(mntmNewCtaAhorro);
		mnInsertar.add(mntmNewCtaCorriente);


		
		mnPrincipal = new JMenuBar();
		mnPrincipal.add(mnOpciones);
		mnPrincipal.add(mnVer);
		mnPrincipal.add(mnInsertar);

		
		
		panPrincipal.add(mnPrincipal, BorderLayout.NORTH);

		

		panPrincipal.add(panJList, BorderLayout.CENTER);
		panJList.setLayout(new BorderLayout(0, 0));
		
		

	}
}
