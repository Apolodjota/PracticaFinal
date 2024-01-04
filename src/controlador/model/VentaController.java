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
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import javax.sound.midi.Soundbank;
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

    private void mergeSort(Venta[] array, int ini, int fin, String field, Integer type) {
        if (ini < fin) {
            int medio = (ini + fin) / 2;
            mergeSort(array, ini, medio, field, type);
            mergeSort(array, medio + 1, fin, field, type);
            merge(array, ini, medio, fin, field, type);
        }
    }

    private void merge(Venta[] array, int ini, int m, int fin, String field, Integer type) {
        int i = ini;
        int j = m + 1;
        int k = 0;
        int n = fin - ini + 1;
        Venta[] arraySec = new Venta[n];
        while (i <= m && j <= fin) {
            if (array[i].comparar(array[j], field, type)) {
                arraySec[k] = array[i];
                i++;
            } else {
                arraySec[k] = array[j];
                j++;
            }
            k++;
        }
        while (i <= m) {
            arraySec[k] = array[i];
            i++;
            k++;
        }
        while (j <= fin) {
            arraySec[k] = array[j];
            j++;
            k++;
        }
        for (k = 0; k < n; k++) {
            array[ini + k] = arraySec[k];
        }
    }

    public ListaEnlazada<Venta> quickSort(ListaEnlazada<Venta> lista, Integer type, String field) {
        Venta[] m = lista.toArray();
        Integer n = m.length - 1;
        quickSort(m, type, field, 0, n);
        //setVentas(lista.toList(m));
        return lista = lista.toList(m);
    }

    private void quickSort(Venta[] m, Integer type, String field, int inicio, int fin) {
        if (inicio >= fin) return;
        
        Venta pivote = m[inicio];
        int elemIzq = inicio + 1;
        int elemDer = fin;
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
//    public ListaEnlazada<Venta> binaria(ListaEnlazada<Venta> lista, String field int X, int fin, int inicio) {
//            Venta[] m = this.quickSort(lista, 0, field).toArray();
//            int medio;
//            if (inicio > fin) {
//                return -1;
//            } else {
//                medio = (inicio + fin) / 2;
//                if (m[medio] > X) {
//                    return binaria(A, X, medio + 1, fin);
//                } else if (A[medio] > X) {
//                    return binaria(A, X, inicio, medio - 1);
//                } else {
//                    return medio;
//                }
//            }
//    }
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
  
//        System.out.println(vc.binariaLineal(vc.getVentas(), "350.0", "total").print());
        long startTime = System.nanoTime();
        VentaController vc = new VentaController();
        System.out.println(vc.mergeSort(vc.getVentas(), 0, "total").print());
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.println("Tiempo de ejecucion en nanosegundos: " + time);
        System.out.println("Tiempo de ejecucion en milisegundos: " + time/1000000);
    }
}
