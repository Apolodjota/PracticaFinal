/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.dao;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.File;

/**
 *
 * @author Apolo
 */
public class Connection {
    private static String URL = "data" + File.separatorChar;
    private static XStream xstream;
    private Connection (){
    }

    /**
     * @return the URL
     */
    public static String getURL() {
        return URL;
    }

    /**
     * @return the xstream
     */
    public static XStream getXstream() {
        if(xstream == null){
            xstream = new XStream(new JettisonMappedXmlDriver());
            //xstream.setMode(XStream.NO_REFERENCES);
            xstream.addPermission(AnyTypePermission.ANY);
        }
            return xstream;
    }
}
