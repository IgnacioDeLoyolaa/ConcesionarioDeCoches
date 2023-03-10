/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.SQLException;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Stock;
import vista.ScannerPro;

/**
 *
 * @author Ignacio Romero
 */
public class ControladorStock {

	public static boolean getStcock(String modelo) throws SQLException {

        Stock s = ManejoBaseDatos.hayStock(modelo);
        return s != null;
    }

    public static void insertarStock() throws SQLException {
        //Le Pedimos los datos del stock
        Stock s = ScannerPro.altaStock();
        //si el metodo getStock nos devuelve true, esto da a entender que ya existia dicho modelo
        //Sino lo inserta en BBDD
        if (getStcock(s.getModelo())) {
            System.out.println("Ese modelo ya esta dado de alta");
        } else {
            ManejoBaseDatos.insertarStock(s);
            System.out.println("Se ha insertado correctamente");
        }
    }

    public static void modificarStock() throws SQLException {
        //Le Pedimos el modelo
        String modelo = ScannerPro.pedirModdelo();
        Stock c = new Stock();
        c.setModelo(modelo);
        //Le Pedimos los datso restannte a modificar
        ScannerPro.altaStock(c);
        //Modificamos en BBDD
        ManejoBaseDatos.modificarStock(c);

    }

    public static void borrarStock() throws SQLException {

        //Le Pedimos el modelo y luego se borra de la BBDD
        String modelo = ScannerPro.pedirModdelo();
        Stock c = new Stock();
        c.setModelo(modelo);
        ManejoBaseDatos.borrarStock(c);
    }
    
}
