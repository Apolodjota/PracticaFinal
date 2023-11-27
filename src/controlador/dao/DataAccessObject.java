/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.dao;

import com.thoughtworks.xstream.XStream;
import controlador.ListaEnlazada;
import controlador.exceptions.VacioException;
import java.io.FileOutputStream;
import java.io.FileReader;

/**
 *
 * @author Apolo
 */
public class DataAccessObject<T> implements TransferObject <T>{
    private XStream xstream;
    private Class clazz;
    private String URL;

    public DataAccessObject(Class <T> clazz) {
        xstream = Connection.getXstream();
        this.clazz = clazz;
        URL = Connection.getURL() + this.clazz.getSimpleName()+".json";
        
    }
    
    
    @Override
    public Boolean save(T data) {
        ListaEnlazada <T> list = listAll();
        
        list.add(data);
        
        try {
            
            this.xstream.toXML(list, new FileOutputStream(URL));
            return true;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        
    }

    @Override
    public Boolean update(T data, Integer i) {
        ListaEnlazada <T> list = listAll();
        
        try {
            list.update(data, i);
        } catch (VacioException e) {
            System.out.println(e.getMessage());
        }
        
        try {
            
            this.xstream.toXML(list, new FileOutputStream(URL));
            return true;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ListaEnlazada<T> listAll() {
        ListaEnlazada <T> list = new ListaEnlazada<>();
        try {
            list = (ListaEnlazada<T>) xstream.fromXML(new FileReader(URL));
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
}

    @Override
    public T find(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Integer generated_ID(){
        return listAll().getSize() + 1;
    }
}
