/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ControladorCliente;
import controlador.ControladorCoche;
import controlador.ControladorEmpleado;
import controlador.ControladorStock;
import controlador.ControladorVentas;
import controlador.FicheroDOM;
import controlador.FicheroPlano;
import controlador.ManejoBaseDatos;
import controlador.conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import modelo.Coche;
import vista.ScannerPro;

/**
 *
 * @author Ignacio Romero
 */
public class Inicio {

	 public static void main(String[] args) throws Exception {

	      // ManejoFicheros.volcarCoches();
		 
		//System.out.println(ManejoBaseDatos.getClientes());
		
		
		//ManejoBaseDatos.escribirClientesXML();
		

	        do {
	            // Muestra el menu y recoge la opcion seleccionda por teclado
	            String opcion = ScannerPro.mostrarMenu();
	            switch (opcion) {
	                case "1":
	                    ControladorCoche.matricularCoche();
	                    break;
	                case "2":
	                    ControladorCliente.darAltaCliente();
	                    break;
	                case "3":
	                    ControladorEmpleado.darAltaEmpleado();
	                    break;
	                case "4":
	                    ControladorStock.insertarStock();
	                    break;
	                case "5":
	                    ControladorVentas.comprarCoche();
	                    break;
	                case "6":
	                    ControladorCliente.borrarClientes();
	                    break;
	                case "7":
	                    ControladorEmpleado.borrarEmpleados();
	                    break;
	                case "8":
	                    ControladorStock.borrarStock();
	                    break;
	                case "9":
	                    ControladorCliente.modificarCliente();
	                    break;
	                case "10":
	                    ControladorEmpleado.modificarEmpleado();
	                    break;
	                case "11":
	                    ControladorStock.modificarStock();
	                    break;
	                case "12":
	                	
	                	ManejoBaseDatos.generarTextoPlano();
	                    break;
	                case "13":
	                	ManejoBaseDatos.ficheroDom();
	                    break;
	                case "14":
	                	 ManejoBaseDatos.ficheroXStream();
	                    break;
	                case "15":
	                 ManejoBaseDatos.ficheroXStreamTodo();
	                    break;
	                case "16":
	                	System.out.println(ManejoBaseDatos.getClientes());
	                    ManejoBaseDatos.escribirClientesXML();
	                case "17":
	                	System.out.println(ManejoBaseDatos.getCoches());
	                	ManejoBaseDatos.escribirCochesXML();
	                case "18":
	                	System.out.println(ManejoBaseDatos.getEpleados());
	                	ManejoBaseDatos.escribirEmpleadosXML();
	                case "19":
	                	System.out.println("Gracias");

	                    return;
	                default:
	                    System.out.println("Esa opcion no existe");

	            }
	            //este bucle es infinito, por lo que para salir del debemos salir del metodo con un return que es lo que hace la opcion 16
	        } while (true);

	    }
}
