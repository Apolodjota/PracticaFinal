/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

//import controlador.exceptions.LlenoException;
import controlador.exceptions.VacioException;

/**
 *
 * @author Apolo
 */
public class ListaEnlazada <E> {
    private Nodo <E> head;
    private Nodo <E> last;
    private Integer size;

    public ListaEnlazada() {
        head = null;
        last = null;
        size = 0;
    }
    
    /**
     * @return the size
     */
    public Integer getSize() {
        return size;
    }
    /**
     * @param size the size to set
     */
    public void setSize(Integer size) {
        this.size = size;
    }
    public Boolean isEmpty(){
        return head == null || getSize() ==0;
    }

    protected void addFirst(E data){
        if (isEmpty()) {
            Nodo <E> aux = new Nodo <>(data, null);
            head = aux;
            last = aux;
        }else{
            Nodo <E> headOld =  head;
            Nodo <E> aux = new Nodo <>(data, headOld);
            head = aux;
        }
        setSize((Integer) (getSize() + 1));
    }
    protected void addLast (E data){
        if (isEmpty()) {
            addFirst(data);
        }else{
            Nodo <E> aux = new Nodo <>(data, null);
            last.setNext(aux);
            last = aux;
            size++;
        }
        
    }
    
    public void add (E data){
        addLast(data);
    }
    
    public void add (E data, Integer post) throws VacioException{
        if (post == 0) {
            addFirst(data);
        } else if (post.intValue() == (size.intValue()-1)) {
            addLast(data);
        }else{
            Nodo <E> searchPreview = getNodo(post - 1);
            Nodo <E> search = getNodo(post);
            Nodo <E> aux = new Nodo<>(data, search);
            searchPreview.setNext(aux);
            setSize((Integer) (getSize() + 1));
        }
    }
     public E delete (Integer post) throws VacioException{
        if (isEmpty()) {
            throw new VacioException("Error, la lista esta vacia");
        } else if (post <0 || post >= getSize()){
            throw new IndexOutOfBoundsException("Error, esta fuera de los limites de la lista");
        }else if (post == 0){
            return deleteFirst();
        }else if (post == (getSize() - 1)){
           return deleteLast();
        }else{
            Nodo <E> preview = getNodo( post - 1);
            Nodo <E> actually = getNodo(post);
            E element = preview.getData();
            Nodo <E> next = actually.getNext();
            actually = null;
            preview.setNext(next);
            size--;
            
            return element;
        }
    }
    public E deleteFirst () throws VacioException{
        if(isEmpty()){
            throw new VacioException("Lista Vacia");
        }else{
            E element =  head.getData();
            Nodo<E> aux = head.getNext();
            head = aux;
            if(size.intValue() == 1){
                last = null;
            }
            size--;
            return element;
        }
    }
    public E deleteLast () throws VacioException{
        if(isEmpty()){
            throw new VacioException("Lista Vacia");
        }else{
            E element =  last.getData();
            Nodo<E> aux = getNodo(getSize() - 2);
            if (aux == null){
                last = null;
                if(size == 2){
                    last = head;
                }else{
                    head = null;
                }
            }else{
                last = null;
                last = aux;
                last.setNext(null);
            }
            size--;
            return element;
        }
    }
    
    public void clear(){
        head= null;
        last = null;
        size =0;
    }
    private Nodo <E> getNodo(Integer post) throws VacioException{
        if (isEmpty()) {
            throw new VacioException("Error, la lista esta vacia");
        } else if (post <0 || post >= getSize()){
            throw new IndexOutOfBoundsException("Error, esta fuera de los limites de la lista");
        }else if (post == 0){
            return head;
        }else if (post == (getSize() - 1)){
            return last;
        }else{
            Nodo <E> search = head;
            Integer cont = 0;
            
            while(cont < post){
                cont++;
                search = search.getNext();
            }
            return search;
        }
    }
    public void update (E data, Integer post) throws VacioException{
        if (isEmpty()) {
            throw new VacioException("Error, la lista esta vacia");
        } else if (post <0 || post >= getSize()){
            throw new IndexOutOfBoundsException("Error, esta fuera de los limites de la lista");
        }else if (post == 0){
            head.setData(data);
        }else if (post == (getSize() - 1)){
            last.setData(data);
        }else{
            Nodo <E> search = head;
            Integer cont = 0;
            
            while(cont < post){
                cont++;
                search = search.getNext();
            }
            search.setData(data);
        }
    }
    public E get(Integer index) throws VacioException{
        if(isEmpty()){
            throw new VacioException("Lista Vacia");
        }else if(index.intValue() < 0 || index.intValue() > size){
            throw new IndexOutOfBoundsException("Fuera de rango");
        }else if(index.intValue() == 0){
            return getFirst();
        }else if(index.intValue() == (size -1)){
            return getLast();
        }else{
            Nodo <E> search = getNodo(index);
            return search.getData();
        }
    }
    public String print(){
        StringBuilder sb = new StringBuilder();
        if(isEmpty()){
            sb.append("Lista vacia");
        }else{
            Nodo <E> aux = head;
            while (aux != null){
                sb.append(aux.getData().toString()).append("\n");
                aux = aux.getNext();
            }
        }
        return sb.toString();
    }
    
    public E getFirst() throws VacioException{
        if(isEmpty()){
            throw new VacioException("Lista Vacia");
        }else{
            return head.getData();
        }
    } 
    public E getLast() throws VacioException{
        if(isEmpty()){
            throw new VacioException("Lista Vacia");
        }else{
            return last.getData();
        }
    } 
    
//    public static void main(String[] args) {
//        ListaEnlazada<Integer> numerics = new ListaEnlazada<>();
//        for(int i = 0; i < 2; i++){
//            Integer aux = (int)(Math.random()*1000);
//            numerics.add(aux);
//        }
//        System.out.println(numerics.print());
//        System.out.println("Tamanio de la lista " + numerics.getSize());
//        try {
//            numerics.add(1, numerics.getSize());
//            System.out.println("---------------------------------------");
//            System.out.println(numerics.print());
//            
//            //numerics.print();
//            //System.out.println(numerics.getNodo(9).getData().toString());
//            System.out.println("Tamanio de la lista " + numerics.getSize());
//            
//            System.out.println("DELETE");
//            numerics.delete(2);
//            System.out.println("Size: " + numerics.getSize());
//            System.out.println(numerics.print());
//            
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        
//    }

}

