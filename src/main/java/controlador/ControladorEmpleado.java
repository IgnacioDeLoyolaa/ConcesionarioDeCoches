/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.SQLException;
import modelo.Cliente;
import modelo.Empleado;
import vista.ScannerPro;

/**
 *
 * @author Ignacio Romero
 */
public class ControladorEmpleado {

	public static Empleado darAltaEmpleado() throws SQLException {

        //Le pedimos todos los datos del empleado
        Empleado c =ScannerPro.altaEmpleados();

        //Se inserta en la BBDD si ya existia devuelve 0 y sino es que lo hizo bien
        if (ManejoBaseDatos.insertarEmpleado(c) == 0) {
            System.out.println("Cliente duplicado");
            return null;
        } else {
            System.out.println("Se ha insertado correctamente");
            return c;
        }
	}

	public static void modificarEmpleado() throws SQLException {
		// Le pedimos el dni
		String dni = ScannerPro.pedirDNI();
		Empleado c = new Empleado();
		c.setDni(dni);
		//Le pedimos los datos restantes del empleado a modificar
		ScannerPro.pedirEmpleado(c);
		// Se modifica
		ManejoBaseDatos.modificarEmpleados(c);

	}

	public static void borrarEmpleados() throws SQLException {
		//Le pedimos el dni y lo borramos de BBDD
		String dni = ScannerPro.pedirDNI();
		Empleado c = new Empleado();
		c.setDni(dni);
		ManejoBaseDatos.borrarEmpleado(c);
	}

	public static boolean ExisteEmpleado(String DNI) throws SQLException {
		Empleado e = ManejoBaseDatos.hayDni(DNI);
		return e != null;

	}

}
