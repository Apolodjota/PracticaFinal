/*
"_{? MNH<:P)
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.util;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author apolo
 */
public class Utilidades {
    public static Field getField(Class clazz, String attribte) {
        Field[] fields = clazz.getFields();
        Field resp = null;
        for (Field f : fields) {
            if (attribte.equalsIgnoreCase(f.getName())) {
                resp = f;
            }
        }

        Field[] fields1 = clazz.getDeclaredFields();
        for (Field f : fields1) {
            if (attribte.equalsIgnoreCase(f.getName())) {
                resp = f;
            }
        }
        return resp;
    }
    public static Object getTypeField(Class clazz, String attribte) {
        Field field = getField(clazz, attribte);
        Object obj = field.getType();
        if(obj != null)
            return obj;
        return null;
    }

    public static Object getData(Object clas, String attribte) throws InvocationTargetException, IllegalAccessException {
        Class clazz = clas.getClass();
        Field f = getField(clazz, attribte);
        Object obj = null;
        if (f != null) {
            String auxAttribute = "get"+capitalize(attribte);
            Method method = null;
            for (Method m : clazz.getMethods()) {
                if (m.getName().equalsIgnoreCase(auxAttribute)) {
                    method = m;
                    break;
                }
            }
            for (Method m : clazz.getDeclaredMethods()) {
                if (m.getName().equalsIgnoreCase(auxAttribute)) {
                    method = m;
                    break;
                }
            }
            if (method != null)
                obj = method.invoke(clas);
        }
        return obj;
    }

    public static String capitalize(String cdn) {
        char[] caracteres = cdn.toCharArray();
        String aux = String.valueOf(caracteres[0]).toUpperCase();
        caracteres[0] = aux.charAt(0);
        return new String(caracteres);
    }
    
    public static String toFormatoFecha(Date fecha) {
        return new SimpleDateFormat("yyyy/MM/dd").format(fecha);
    }
}
