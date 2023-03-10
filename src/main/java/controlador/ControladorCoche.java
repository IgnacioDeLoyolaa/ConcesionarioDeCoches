/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.SQLException;
import modelo.Coche;
import modelo.Stock;
import vista.ScannerPro;

/**
 *
 * @author Ignacio Romero
 */
public class ControladorCoche {

public static void matricularCoche() throws Exception {
        
        //Nos pide el modelo del coche
        String modelo = ScannerPro.pedirModdelo();
        
        //Comprueba que si en el stock , hay dicho modelo
        Stock s = ManejoBaseDatos.hayStock(modelo);
        if (s != null && s.getUnidades() != 0) {
            System.out.println("hay stock");
            Coche c = new Coche();
            c.setMarca(s.getMarca());
            c.setModelo(s.getModelo());
            
            //Nos pide los datos restantes (Matricula, precio y vendido)
            ScannerPro.matricularCoche(c);
            //Una vez tenemos todos los datos lo insertamos en la BBDD
            ManejoBaseDatos.insertarCoche(c);
            //Restamos las unidades del stcck en uno
            int numero=s.getUnidades();
            numero-=1;
            s.setUnidades(numero);
            //Llama a la BBDD para modifcar el stock con lo que habia antes pero con una uniddad menos
            ManejoBaseDatos.modificarStock(s);
        }else{
            System.out.println("No hay Stock");
        }
    }
     
}
