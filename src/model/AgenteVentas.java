/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author Apolo
 */
public class AgenteVentas extends Persona {
    private Integer id;

    public AgenteVentas() {
    }
    
    public AgenteVentas(Integer id, String nombres, String apellidos, String cedula, String nacionalidad, LocalDate fechaNacimiento) {
        super(nombres, apellidos, cedula, nacionalidad, fechaNacimiento);
        this.id = id;   
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
    
    @Override
    public String toString() {
        return super.getNombres() + " " + super.getApellidos();
    }
    
}
