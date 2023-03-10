/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.SQLException;
import modelo.Cliente;
import modelo.Coche;
import modelo.Stock;
import vista.ScannerPro;
import vista.ScannerPro;

/**
 *
 * @author Ignacio Romero
 */
public class ControladorCliente {

	public static Cliente darAltaCliente() throws SQLException {

        //Pedimos todos los datos del cliente
        Cliente c = ScannerPro.altaClientes();

        //Lo inserto en la BBDD , y luego nos devuelve 0 , que significa que hay un duplicado sino; se ha realizado correctamen
        if (ManejoBaseDatos.insertarCliente(c) == 0) {
            System.out.println("Cliente duplicado");
            return null;
        } else {
            System.out.println("Se ha insertado correctamente");
            return c;
        }
    }

    public static void modificarCliente() throws SQLException {
        //Nos Pide el id del clliente
        String id = ScannerPro.pedirID();
        Cliente c = new Cliente();
        c.setId(id);
        //Nos piden los datos modificables restantes y hacemos la modificacion

        ScannerPro.altaClientes(c);
        ManejoBaseDatos.modificarCliente(c);

    }

    public static void borrarClientes() throws SQLException {

        //Pedimos la id y la borrramos de BBDD
        String id = ScannerPro.pedirID();
        Cliente c = new Cliente();
        c.setId(id);
        ManejoBaseDatos.borrarCliente(c);
    }

   

}
