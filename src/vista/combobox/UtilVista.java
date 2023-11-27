/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.combobox;

import controlador.ListaEnlazada;
import controlador.exceptions.VacioException;
import controlador.model.AgenteVentaController;
import controlador.model.MarcaController;
import javax.swing.JComboBox;
import model.AgenteVentas;
import model.Color;
import model.Marca;

/**
 *
 * @author Apolo
 */
public class UtilVista {
    
    public static void cargarMarca(JComboBox cbxMarca) throws VacioException{
        MarcaController mc = new MarcaController();
        cbxMarca.removeAllItems();

        for(int i = 0; i < mc.getMarcas().getSize(); i++)
            cbxMarca.addItem(mc.getMarcas().get(i));
    }
    public static Marca getComboMarcas(JComboBox cbx){
        return (Marca)cbx.getSelectedItem();
    }
    
    public static void cargarColor(JComboBox cbxColor) throws VacioException{
        cbxColor.removeAllItems();
        for(Color c : Color.values())
            cbxColor.addItem(c);
        
    }
    public static Color getComboColor(JComboBox cbx){
        //return (Color)cbx.getSelectedItem();
        return Color.valueOf(cbx.getSelectedItem().toString());
    }
    
    public static void cargarAgenteVentas(JComboBox cbxAgentesVentas) throws VacioException{
        
        AgenteVentaController agvc = new AgenteVentaController();
        cbxAgentesVentas.removeAllItems();

        for(int i = 0; i < agvc.getAgentesVentas().getSize(); i++)
            cbxAgentesVentas.addItem(agvc.getAgentesVentas().get(i));
  
    }
    public static AgenteVentas getComboAgenteVentas(JComboBox cbx){
        return (AgenteVentas)cbx.getSelectedItem(); 
        //return cbx.getSelectedItem().toString(); 
    }
    
    
    
}
