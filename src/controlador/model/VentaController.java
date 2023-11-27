/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.model;

//import controlador.Tda.listas.ListaEnlazada;
import controlador.ListaEnlazada;
import controlador.dao.DataAccessObject;
import controlador.exceptions.VacioException;
import model.Auto;
import model.Venta;
;
/**
 *
 * @author Apolo
 */
public class VentaController extends DataAccessObject<Venta> {
    private Venta venta = new Venta();
    private ListaEnlazada<Venta> ventas = new ListaEnlazada<>();
    private Integer index = -1;

    public VentaController() {
        super(Venta.class);
    }
 
    public Boolean guardar(){
        venta.setId(generated_ID());
        return save(venta);
    }
    public Boolean update(int fila){
        return update(venta, fila);
    }

    
    public String generatedCode(){
        StringBuilder code = new StringBuilder();
        Integer length = listAll().getSize() + 1;
        Integer pos = length.toString().length();
        for(int i = 0; i < (10 - pos); i++){
            code.append("0");
        }
        code.append(length.toString());
        return code.toString();    
    }
    
    public Double generarTotal() throws VacioException{
        Double total = 0.0;
        for (int i = 0; i < venta.getAutosVenderan().getSize(); i++) {
            Auto auto = venta.getAutosVenderan().get(i);
            total += auto.getPrecio();
        }
        return total;
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

    /**
     * @return the venta
     */
    public Venta getVenta() {
        if(venta == null)
            venta = new Venta();
        return venta;
    }

    /**
     * @param venta the venta to set
     */
    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    /**
     * @return the ventas
     */
    public ListaEnlazada<Venta> getVentas() {
        if(ventas.isEmpty())
            ventas = listAll();
        return ventas;
    }

    /**
     * @param ventas the ventas to set
     */
    public void setVentas(ListaEnlazada<Venta> ventas) {
        this.ventas = ventas;
    }
}
