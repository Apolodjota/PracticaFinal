/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.model;

import controlador.ListaEnlazada;
import controlador.dao.DataAccessObject;
import model.Auto;

/**
 *
 * @author Apolo
 */
public class AutoController extends DataAccessObject<Auto>{
    private ListaEnlazada<Auto> autos = new ListaEnlazada<>();
    private Auto auto;
    private Integer index = -1;
    
    public AutoController(){
        super(Auto.class);
    }

    /**
     * @return the autos
     */
    public ListaEnlazada<Auto> getAutos() {
        if(autos.isEmpty())
            autos = listAll();
        return autos;
    }
    public Boolean guardar(){
        auto.setId(generated_ID());
        return save(auto);
    }
    
    public Boolean actualizar(Integer i){
        return update(auto, i);
    }

    /**
     * @param autos the autos to set
     */
    public void setAutos(ListaEnlazada<Auto> autos) {
        this.autos = autos;
    }

    /**
     * @return the auto
     */
    public Auto getAuto() {
        if(auto == null)
            auto = new Auto();
        return auto;
    }

    /**
     * @param auto the auto to set
     */
    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    /**
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(Integer index) {
        this.index = index;
    }
    
}
