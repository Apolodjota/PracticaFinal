/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.tablas;

import controlador.ListaEnlazada;
import controlador.exceptions.VacioException;
import javax.swing.table.AbstractTableModel;
import model.Venta;

/**
 *
 * @author apolo
 */
public class ModeloTablaVenta extends AbstractTableModel{
    private ListaEnlazada <Venta> ventas;
    
    public int getRowCount() {
        return ventas.getSize();
    }

    public int getColumnCount() {
        return 3;    
    }

    public Object getValueAt(int i, int j) {
        Venta venta = null;
        try {
            venta = ventas.get(i);
        } catch (VacioException ex) {
            System.out.println(ex.getMessage());
        }
        switch (j) {
            case 0:
                return (venta != null) ? venta.getCodigo(): "";
            case 1:
                return (venta != null) ? venta.getTotal(): "";
            case 2:
                return (venta != null) ? venta.getId_AgenteVenta().toString(): "";
            default:
                return null;
        }
    }
    
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Codigo";
            case 1:
                return "Total";
            case 2:
                return "Id Vendedor";
            default:
                return null;
        }
    }

    /**
     * @return the autos
     */
    public ListaEnlazada<Venta> getVentas() {
        return ventas;
    }
    
    /**
     * @param ventas the autos to set
     */
    public void setVentas(ListaEnlazada <Venta> ventas) {
        this.ventas = ventas;
    }
}
