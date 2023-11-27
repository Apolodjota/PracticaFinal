/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.model;

import controlador.ListaEnlazada;
import controlador.dao.DataAccessObject;
import model.AgenteVentas;

/**
 *
 * @author apolo
 */
public class AgenteVentaController extends DataAccessObject<AgenteVentas>{
    private ListaEnlazada<AgenteVentas> agentesVentas ;
    private AgenteVentas agenteVenta;

    public AgenteVentaController() {
        super(AgenteVentas.class);
    }

    /**
     * @return the agentesVentas
     */
    public ListaEnlazada<AgenteVentas> getAgentesVentas() {
        if(agentesVentas == null)
            agentesVentas = listAll();
        return agentesVentas;
    }

    /**
     * @param agentesVentas the agentesVentas to set
     */
    public void setAgentesVentas(ListaEnlazada<AgenteVentas> agentesVentas) {
        this.agentesVentas = agentesVentas;
    }

    /**
     * @return the agenteVenta
     */
    public AgenteVentas getAgenteVenta() {
        if(agenteVenta == null)
            agenteVenta = new AgenteVentas();
        return agenteVenta;
    }

    /**
     * @param agenteVenta the agenteVenta to set
     */
    public void setAgenteVenta(AgenteVentas agenteVenta) {
        this.agenteVenta = agenteVenta;
    }
    
    public Boolean guardar(){
        agenteVenta.setId(generated_ID());
        return save(agenteVenta);
    }
    
//    public Boolean actualizar(Integer  i){
//        return update(agenteVenta, i);
//    }
    
    
    
}
