package controller;

import java.io.IOException;
import java.io.ObjectInputStream;	
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Lista<E> implements Serializable {
    private static final long serialVersionUID = 1L; 
    private transient Node<E> inicio;

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
        Node<E> nuevoNodo = new Node<>(p);
        if (nuevoNodo == nuevoNodo.getSiguiente()) {
            throw new IllegalStateException("Ciclo detectado durante la inserción del nodo");
        }
        nuevoNodo.setSiguiente(this.getInicio());
        this.setInicio(nuevoNodo);
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // Serializa los campos no transitorios.
        Node<E> current = inicio;
        while (current != null) {
            out.writeObject(current.getPrincipal()); // Serializa solo el valor.
            current = current.getSiguiente();
        }
        out.writeObject(null); // Señal de fin de lista.
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Deserializa los campos no transitorios.
        Node<E> previous = null;
        while (true) {
            @SuppressWarnings("unchecked")
            E value = (E) in.readObject();
            if (value == null) break; // Fin de lista.
            Node<E> currentNode = new Node<>(value);
            if (previous == null) {
                inicio = currentNode;
            } else {
                previous.setSiguiente(currentNode);
            }
            previous = currentNode;
        }
    }

    
    public Node<E> getNodoInicio() {
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
