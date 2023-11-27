/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controlador.ListaEnlazada;

/**
 *
 * @author Apolo
 */
public class Venta {
    private Integer id;
    private String codigo;
    private Double total;
    private Integer id_AgenteVenta;
    private ListaEnlazada<Auto> autosVenderan;

    public Venta() {
    }

    public Venta(Integer id, String codigo, Double total, Integer id_AgenteVenta, ListaEnlazada<Auto> autosVenderan) {
        this.id = id;
        this.codigo = codigo;
        this.total = total;
        this.id_AgenteVenta = id_AgenteVenta;
        this.autosVenderan = autosVenderan;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the id_AgenteVenta
     */
    public Integer getId_AgenteVenta() {
        return id_AgenteVenta;
    }

    /**
     * @param id_AgenteVenta the id_AgenteVenta to set
     */
    public void setId_AgenteVenta(Integer id_AgenteVenta) {
        this.id_AgenteVenta = id_AgenteVenta;
    }

    /**
     * @return the autosVenderan
     */
    public ListaEnlazada<Auto> getAutosVenderan() {
        if(autosVenderan == null)
            autosVenderan = new ListaEnlazada<>();
        return autosVenderan;
    }

    /**
     * @param autosVenderan the autosVenderan to set
     */
    public void setAutosVenderan(ListaEnlazada<Auto> autosVenderan) {
        this.autosVenderan = autosVenderan;
    }

    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }
    
    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    
    
}
