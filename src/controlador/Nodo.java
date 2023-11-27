/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author Apolo
 */
public class Nodo <E> {
    private E data;
    private Nodo<E> next;

    public Nodo() {
        next = null;
    }

    public Nodo(E data) {
        this.data = data;
        next = null;
    }

    public Nodo(E data, Nodo<E> next) {
        this.data = data;
        this.next = next;
    }
    
    
    /**
     * @return the data
     */
    public E getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * @return the next
     */
    public Nodo<E> getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(Nodo<E> next) {
        this.next = next;
    }
    
}
