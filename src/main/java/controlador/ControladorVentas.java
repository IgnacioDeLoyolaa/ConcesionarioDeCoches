/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.SQLException;
import modelo.Cliente;
import modelo.Coche;
import modelo.Empleado;
import modelo.Ventas;
import vista.ScannerPro;

/**
 *
 * @author Ignacio Romero
 */
public class ControladorVentas {
	
public static void comprarCoche() throws SQLException {
        
        //Pedimos los datos de la venta
        Ventas v = ScannerPro.pedirVenta();
        //Validamos el dni, si no esta dado de alta le damos la posibilidad de darlo de alta
        if (ManejoBaseDatos.hayDni(v.getDni()) == null) {
            
            //Si no existe le preguntamos si quiere darlo de alta
            if (ScannerPro.deseaContinuar()) {
                Empleado e = null;
                while (e == null) {
                    //Le pedimos los datos hasta que nos de un emleado que exista
                    e = ControladorEmpleado.darAltaEmpleado();
                }
                //Le metemos el nuevo dni
                v.setDni(e.getDni());
            } else {
                //Si no quiero darlo de alta finalizo el alta de la venta
                return;
            }

        }
        //Lo mismo que con el empledo pero con el cliente
        Cliente cl=ManejoBaseDatos.hayId(v.getId());
        if (cl == null) {
            System.out.println("El id no existe");
            if (ScannerPro.deseaContinuar()) {
                Cliente c = null;
                while (c == null) {
                    c = ControladorCliente.darAltaCliente();
                }
                v.setId(c.getId());
            } else {
                return;
            }
         
        }
        //Unicamente validamos que este matriculado
        Coche c = ManejoBaseDatos.hayMatricula(v.getMatricula());
        if (c == null) {
            System.out.println("La matricula no existe");
            return;
        }
        //Sumamos el precio del coche al importe del cliente
        //Tambien ponemos el vendido de la tabla coche a true
        cl.setImporte(c.getPrecio()+cl.getImporte());
        c.setVendido(true);
        ManejoBaseDatos.modificarCliente(cl);
        ManejoBaseDatos.modificarCoche(c);
        //Si hemos llegado hasta aqui insertamos la venta
        ManejoBaseDatos.insertarVenta(v);
        System.out.println("Venta realizada con exito");

    }

}
