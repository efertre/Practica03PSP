package controller;

import java.io.Serializable;

public class Lista<E> implements Serializable {
    private static final long serialVersionUID = 1L; 
	private Node<E> inicio;

	public Lista() {
		this.setInicio(null);
	}

	public void mostrarLista() {
		Node<E> aux = this.getInicio();
		while (aux != null) {
			System.out.println(aux.getPrincipal().toString());
			aux = aux.getSiguiente();
		}
	}

	public void insertarNodo(E p) {
		Node<E> nuevoNodo = new Node(p);
		nuevoNodo.setSiguiente(this.getInicio());
		this.setInicio(nuevoNodo);
	}
	
	public Node<E> getNodoInicio(){
		return getInicio();
	}

	public Node<E> getInicio() {
		return inicio;
	}

	public void setInicio(Node<E> inicio) {
		this.inicio = inicio;
	}

	public class Node<E> implements Serializable {
	    private static final long serialVersionUID = 1L; 
		private Node<E> siguiente;
		E principal;

		public Node(E p) {
			this.siguiente = null;
			this.principal = p;

		}

		public Node<E> getSiguiente() {
			return this.siguiente;
		}

		public void setSiguiente(Node<E> siguiente) {

			this.siguiente = siguiente;
		}

		public E getPrincipal() {
			return principal;
		}

		public void setPrincipal(E p) {
			this.principal = p;
		}

	}

}
