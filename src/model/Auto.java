/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Apolo
 */
public class Auto {
    private Integer id;
    private Double precio;
    private String modelo;
    private Integer cantPuertas;
    private Boolean descapotable;
    private Integer id_Marca;
    private Integer id_Venta;
    private Color color;

    public Auto() {
    }

    public Auto(Integer id, Double precio, String modelo, Integer cantPuertas, Boolean descapotable, Integer id_Marca, Integer id_Venta, Color color) {
        this.id = id;
        this.precio = precio;
        this.modelo = modelo;
        this.cantPuertas = cantPuertas;
        this.descapotable = descapotable;
        this.id_Marca = id_Marca;
        this.id_Venta = id_Venta;
        this.color = color;
    }
    
    
    
    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the cantPuertas
     */
    public Integer getCantPuertas() {
        return cantPuertas;
    }

    /**
     * @param cantPuertas the cantPuertas to set
     */
    public void setCantPuertas(Integer cantPuertas) {
        this.cantPuertas = cantPuertas;
    }

    /**
     * @return the descapotable
     */
    public Boolean getDescapotable() {
        return descapotable;
    }

    /**
     * @param descapotable the descapotable to set
     */
    public void setDescapotable(Boolean descapotable) {
        this.descapotable = descapotable;
    }

    /**
     * @return the id_Marca
     */
    public Integer getId_Marca() {
        return id_Marca;
    }

    /**
     * @param id_Marca the id_Marca to set
     */
    public void setId_Marca(Integer id_Marca) {
        this.id_Marca = id_Marca;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the id_Venta
     */
    public Integer getId_Venta() {
        return id_Venta;
    }

    /**
     * @param id_Venta the id_Venta to set
     */
    public void setId_Venta(Integer id_Venta) {
        this.id_Venta = id_Venta;
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
    
    
}
