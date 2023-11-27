/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.tablas;

import controlador.ListaEnlazada;
import controlador.exceptions.VacioException;
import javax.swing.table.AbstractTableModel;
import model.Auto;

/**
 *
 * @author Apolo
 */
public class ModeloTablaAuto extends AbstractTableModel{
    private ListaEnlazada <Auto> autos;
    
    @Override
    public int getRowCount() {
        return autos.getSize();
    }

    @Override
    public int getColumnCount() {
        return 5;    
    }

    @Override
    public Object getValueAt(int i, int j) {
        Auto auto = null;
        try {
            auto = autos.get(i);
        } catch (VacioException ex) {
            System.out.println(ex.getMessage());
        }
        switch (j) {
            case 0:
                return (auto != null) ? auto.getModelo(): "";
            case 1:
                return (auto != null) ? auto.getPrecio(): "";
            case 2:
                return (auto != null) ? auto.getColor(): "";
            case 3:
                return (auto != null) ? auto.getDescapotable(): "";
            case 4:
                return (auto != null) ? auto.getCantPuertas(): "";
            default:
                return null;
        }
    }
    
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Modelo";
            case 1:
                return "Precio";
            case 2:
                return "Color";
            case 3:
                return "Descapotable";
            case 4:
                return "Puertas";
            default:
                return null;
        }
    }

    /**
     * @return the autos
     */
    public ListaEnlazada<Auto> getAutos() {
        return autos;
    }

    /**
     * @param autos the autos to set
     */
    public void setAutos(ListaEnlazada <Auto> autos) {
        this.autos = autos;
    }

}
