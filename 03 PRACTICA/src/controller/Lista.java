package controller;

import java.io.IOException;
import java.io.ObjectInputStream;	
import java.io.ObjectOutputStream;
import java.io.Serializable;

import model.Cuenta;

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
    	
    private void writeObject(ObjectOutputStream salida) throws IOException {
        salida.defaultWriteObject(); // Serializa los campos no transitorios (no transient).
        Node<E> actual = inicio; // Empieza desde el nodo inicial de la lista.
        while (actual != null) {
            salida.writeObject(actual.getPrincipal()); // Escribe en el flujo el valor del nodo actual (SOLO SERIALIZA EL VALOR).
            actual = actual.getSiguiente(); // Avanza al siguiente nodo en la lista.
        }
        salida.writeObject(null); // Escribe un valor nulo como marcador de fin de lista.
    }

    private void readObject(ObjectInputStream entrada) throws IOException, ClassNotFoundException {
        entrada.defaultReadObject(); // Deserializa los campos no transitorios definidos en la clase.

        Node<E> anterior = null; // Variable que rastrea el último nodo deserializado.
        while (true) { // Ciclo infinito que se rompe al encontrar el marcador de fin de lista.
            @SuppressWarnings("unchecked")
            E valor = (E) entrada.readObject(); // Lee el siguiente objeto del flujo de entrada.
            if (valor == null) break; // Si el objeto es nulo, se alcanzó el final de la lista.

            Node<E> nodoActual = new Node<>(valor); // Crea un nuevo nodo con el valor leído.
            if (anterior == null) { 
                inicio = nodoActual; // Si es el primer nodo, lo asigna como inicio de la lista.
            } else {
                anterior.setSiguiente(nodoActual); // Conecta el nodo anterior con el nodo actual.
            }
            anterior = nodoActual; // Actualiza el nodo anterior al nodo actual.
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
        private static final long serialVersionUID = 2L; 
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


    public void ordenarPorNumeroCuenta() {
        // Si la lista está vacía (inicio es null), no se realiza ninguna operación.
        if (inicio == null) return;

        // Variable para determinar si se realizaron intercambios en el ciclo actual.
        boolean intercambiado;
        
        // Se inicia un ciclo que seguirá ejecutándose mientras se realicen intercambios.
        do {
            // Nodo actual es el primero de la lista.
            Node<Cuenta> actual = (Lista<E>.Node<Cuenta>) inicio;
            
            // Nodo anterior (inicialmente nulo).
            Node<Cuenta> anterior = null;
            
            // Nodo siguiente al nodo actual.
            Node<Cuenta> siguiente = (Lista<E>.Node<Cuenta>) inicio.getSiguiente();
            
            // Se establece que no se ha realizado ningún intercambio aún.
            intercambiado = false;

            // Se recorre la lista comparando los nodos actuales y siguientes.
            while (siguiente != null) {
                // Si el número de cuenta del nodo actual es mayor que el siguiente, se intercambian los nodos.
                if (actual.getPrincipal().getNumero() > siguiente.getPrincipal().getNumero()) {
                    // Si el nodo anterior es nulo, significa que estamos en el primer nodo,
                    // así que el inicio de la lista se actualiza para que apunte al siguiente nodo.
                    if (anterior == null) {
                        inicio = (Lista<E>.Node<E>) siguiente;
                    } else {
                        // Si el nodo anterior no es nulo, se actualiza su puntero siguiente para que apunte al siguiente nodo.
                        anterior.setSiguiente(siguiente);
                    }

                    // Actualiza el puntero siguiente del nodo actual para que apunte al siguiente del siguiente nodo.
                    actual.setSiguiente(siguiente.getSiguiente());
                    
                    // El siguiente nodo ahora apunta al nodo actual.
                    siguiente.setSiguiente(actual);

                    // Se marca que se ha realizado un intercambio.
                    intercambiado = true;
                    
                    // Se actualizan los nodos para continuar con la comparación en el siguiente ciclo.
                    anterior = siguiente;
                    siguiente = actual.getSiguiente();
                } else {
                    // Si no hay intercambio, se avanza un nodo en la lista.
                    anterior = actual;
                    actual = siguiente;
                    siguiente = siguiente.getSiguiente();
                }
            }
        // Si se realizó un intercambio, el ciclo continúa para asegurarse de que todos los elementos estén ordenados.
        } while (intercambiado);
    }


    
    
}
