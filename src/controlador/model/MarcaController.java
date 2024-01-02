/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.model;

import controlador.ListaEnlazada;
import controlador.dao.DataAccessObject;
import model.Marca;

/**
 *
 * @author Apolo
 */
public class MarcaController extends DataAccessObject<Marca> {
    private ListaEnlazada <Marca> marcas = new ListaEnlazada<>();
    private Marca marca;

    public MarcaController() {
        super(Marca.class);
    }
    /**
     * @return the marcas
     */
    public ListaEnlazada<Marca> getMarcas() {
        if(marcas.isEmpty())
            marcas = this.listAll();
        return marcas;
    }

    /**
     * @param marcas the marcas to set
     */
    public void setMarcas(ListaEnlazada <Marca> marcas) {
        this.marcas = marcas;
    }

    /**
     * @return the marca
     */
    public Marca getMarca() {
       if (marca == null)
           marca = new Marca();
        return marca;
    }
    
    public Boolean save(){
        this.marca.setId(generated_ID());
        return this.save(marca);
    }

    @Override
    public String toString() {
        return marca.getId() +".- "+  marca.getNombre();
    }
    
    
    /**
     * @param marca the marca to set
     */
    public void setMarca(Marca marca) {
        this.marca = marca;
    }
//    public static void main(String[] args) {
//        MarcaController mc = new MarcaController();
//        mc.getMarca().setId(mc.generated_ID());
//        mc.getMarca().setNombre("GAC");
//        mc.getMarca().setEstado(true);
//        mc.save();
//        System.out.println(mc.listAll().print());
//        mc.setMarca(null);
//        
//        mc.getMarca().setId(mc.generated_ID());
//        mc.getMarca().setNombre("JAC");
//        mc.getMarca().setEstado(true);
//        mc.save();
//        System.out.println(mc.listAll().print());
//        mc.setMarca(null);
//        
//        mc.getMarca().setId(mc.generated_ID());
//        mc.getMarca().setNombre("Toyota");
//        mc.getMarca().setEstado(true);
//        mc.save();
//        System.out.println(mc.listAll().print());
//        mc.setMarca(null);
//        
//        mc.getMarca().setId(mc.generated_ID());
//        mc.getMarca().setNombre("HotWeels");
//        mc.getMarca().setEstado(true);
//        mc.save();
//        System.out.println(mc.listAll().print());
//        mc.setMarca(null);
//        
//        mc.getMarca().setId(mc.generated_ID());
//        mc.getMarca().setNombre("Audi");
//        mc.getMarca().setEstado(true);
//        mc.save();
//        System.out.println(mc.listAll().print());
//        mc.setMarca(null);
//   }
    
}
