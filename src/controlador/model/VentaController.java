/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.model;

//import controlador.Tda.listas.ListaEnlazada;
import controlador.ListaEnlazada;
import controlador.dao.DataAccessObject;
import controlador.exceptions.VacioException;
import controlador.util.Utilidades;
import java.lang.reflect.InvocationTargetException;
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

    public Boolean guardar() {
        venta.setId(generated_ID());
        return save(venta);
    }

    public Boolean update(int fila) {
        return update(venta, fila);
    }

    public String generatedCode() {
        StringBuilder code = new StringBuilder();
        Integer length = listAll().getSize() + 1;
        Integer pos = length.toString().length();
        for (int i = 0; i < (10 - pos); i++) {
            code.append("0");
        }
        code.append(length.toString());
        return code.toString();
    }

    public Double generarTotal() throws VacioException {
        Double total = 0.0;
        for (int i = 0; i < venta.getAutosVenderan().getSize(); i++) {
            Auto auto = venta.getAutosVenderan().get(i);
            total += auto.getPrecio();
        }
        return total;
    }

    public ListaEnlazada<Venta> mergeSort(ListaEnlazada<Venta> lista, Integer type, String field) {
        Venta[] array = lista.toArray();
        mergeSort(array, 0, array.length - 1, field, type);
        return lista = lista.toList(array);
    }

    private void mergeSort(Venta[] m, Integer ini, Integer fin, String field, Integer type) {
        if (ini < fin) {
            int medio = (ini + fin) / 2;
            mergeSort(m, ini, medio, field, type);
            mergeSort(m, medio + 1, fin, field, type);
            merge(m, ini, medio, fin, field, type);
        }
    }

    private void merge(Venta[] m, Integer ini, Integer medio, Integer fin, String field, Integer type) {
        Integer izq = ini;
        Integer der = medio + 1;
        Integer k = 0;
        Integer n = fin - ini + 1;
        Venta[] result = new Venta[n];
        while (izq <= medio && der <= fin) {
            if (m[izq].comparar(m[der], field, type)) {
                result[k] = m[izq];
                izq++;
            } else {
                result[k] = m[der];
                der++;
            }
            k++;
        }
        while (izq <= medio) {
            result[k] = m[izq];
            izq++;
            k++;
        }
        while (der <= fin) {
            result[k] = m[der];
            der++;
            k++;
        }
        for (k = 0; k < n; k++) {
            m[ini + k] = result[k];
        }
    }

    public ListaEnlazada<Venta> quickSort(ListaEnlazada<Venta> lista, Integer type, String field) {
        Venta[] m = lista.toArray();
        Integer fin = m.length - 1;
        quickSort(m, type, field, 0, fin);
        //setVentas(lista.toList(m));
        return lista = lista.toList(m);
    }

    private void quickSort(Venta[] m, Integer type, String field, Integer inicio, Integer fin) {
        
        if (inicio >= fin) return;
        
        Venta pivote = m[inicio];
        Integer elemIzq = inicio + 1;
        Integer elemDer = fin;
        while (elemIzq <= elemDer) {
            while (elemIzq <= fin && m[elemIzq].comparar(pivote, field, type)) {
                elemIzq++;
            }
            while (elemDer > inicio && !m[elemDer].comparar(pivote, field, type)) {
                elemDer--;
            }
            if (elemIzq < elemDer) {
                Venta temp = m[elemIzq];
                m[elemIzq] = m[elemDer];
                m[elemDer] = temp;
            }
        }
        if (elemDer > inicio) {
            Venta temp = m[inicio];
            m[inicio] = m[elemDer];
            m[elemDer] = temp;
        }
        quickSort(m, type, field, inicio, elemDer - 1);
        quickSort(m, type, field, elemDer + 1, fin);
    }

    public ListaEnlazada<Venta> buscar(ListaEnlazada<Venta> lista, String text) throws VacioException, Exception {
        ListaEnlazada<Venta> lo = this.quickSort(lista, 0, "id");
        Venta[] m = lo.toArray();
        ListaEnlazada<Venta> result = new ListaEnlazada<>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (m[i].getId().toString().toLowerCase().contains(text.toLowerCase())) {
                result.add(m[i]);
            }
        }
        return result;
    }
    
    public Venta binaria(ListaEnlazada<Venta> lista, String field, String text) throws InvocationTargetException, IllegalAccessException{
        Venta[] m = this.quickSort(lista, 0, field).toArray();
        Integer fin = m.length - 1;
        Integer inicio = 0;
        Venta result = binaria(m, fin, inicio, field, text);
        if(result != null)
            return result;
        else System.out.println("No se pudo encontrar na");
        return null;
        
    }
    public Venta binaria(Venta[] m, int fin, int inicio,String field, String text) throws InvocationTargetException, IllegalAccessException {
            if (inicio > fin) {
                return null;
            } else {
                Integer medio = (inicio + fin) / 2;
                Object data = Utilidades.getData(m[medio], field);
                System.out.println(data.toString());
                //if(Double.parseDouble(data.toString()) == Double.parseDouble(text))
                if (data.toString().equalsIgnoreCase(text)) {
                    return m[medio];
                } else 
                    if (data.toString().compareToIgnoreCase(text) < 0) {
                        return binaria(m,fin, medio + 1, field, text);
                    } else {
                        return binaria(m, medio - 1, inicio, field, text);
                }
            }
    }
    public ListaEnlazada<Venta> binariaLineal(ListaEnlazada<Venta> lista, String texto, String field) throws InvocationTargetException, IllegalAccessException {
        ListaEnlazada<Venta> lo = this.quickSort(lista, 0, field);
        Venta[] m = lo.toArray();
        Integer inicio = 0;
        Integer fin = m.length - 1;
        ListaEnlazada<Venta> result = new ListaEnlazada<>();
        //System.out.println(Utilidades.getData(m[inicio], field));
        //Field fiel = Utilidades.getField(lista.getClass(), field);
        //Object type = Utilidades.getTypeField(venta.getClass(), texto);
        //System.out.println(type.toString());
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            Object data = Utilidades.getData(m[medio], field);
            
            
            if (Double.parseDouble(data.toString()) == Double.parseDouble(texto)){
                System.out.println("Hola mundo");
                result.add(m[medio]);
                break;
            } else {
                if (Double.parseDouble(data.toString()) > Double.parseDouble(texto)) {
                    fin = medio - 1;
                } else {
                    inicio = medio + 1;
                }
            }
        }
        return result;
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
        if (venta == null) {
            venta = new Venta();
        }
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
        if (ventas.isEmpty()) {
            ventas = listAll();
        }
        return ventas;
    }

    /**
     * @param ventas the ventas to set
     */
    public void setVentas(ListaEnlazada<Venta> ventas) {
        this.ventas = ventas;
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InterruptedException {
  
////        System.out.println(vc.binariaLineal(vc.getVentas(), "350.0", "total").print());
//        long startTime = System.nanoTime();
//        VentaController vc = new VentaController();
//        System.out.println(vc.mergeSort(vc.getVentas(), 0, "total").print());
//        long endTime = System.nanoTime();
//        long time = endTime - startTime;
//        System.out.println("Tiempo de ejecucion en nanosegundos: " + time);
//        System.out.println("Tiempo de ejecucion en milisegundos: " + time/1000000);

        VentaController vc = new VentaController();
        System.out.println(vc.mergeSort(vc.getVentas(), 0, "total").print());
        Venta venta = vc.binaria(vc.getVentas(), "total", "350.0");
        if(venta != null)
            System.out.println(venta.toString());   
        else System.out.println("Nulo");
            


    }
}
