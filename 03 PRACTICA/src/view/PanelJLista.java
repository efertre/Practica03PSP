package view;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Cuenta;


public class PanelJLista extends JPanel {

	public PanelJLista(Object objeto) {
		initComponents(objeto);
	}

	private void initComponents(Object objeto) {
		
		JScrollPane scrollPane = new JScrollPane();
		JList jList1 = new JList();
		
		
		jList1.setModel(modeloLista(objeto));
		scrollPane.setViewportView(jList1);
		
		}
	
	
	private DefaultListModel modeloLista(Object objeto) {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		
		if	(objeto instanceof Cuenta) {
			String infoObjeto = "Información del objeto:" + objeto.toString();
			modelo.addElement(infoObjeto);
		} else {
			modelo.addElement("No hay elementos");
		}
		
		return modelo;
		
	}
		

}
