package controlador;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.w3c.dom.Element;

import com.thoughtworks.xstream.XStream;

import modelo.Cliente;
import modelo.Coche;
import modelo.Empleado;
import modelo.ListaClientes;
import modelo.ListaCoches;
import modelo.ListaEmpleado;

public class FicheroXSTREAM {

	public void generar(Connection conexion) throws SQLException {

		// Creamos un objeto de ListaPersonas
		ListaCoches listcoc = new ListaCoches();
		listcoc.setListado(ManejoBaseDatos.getCoches());

		// Usamos XSTREAM
		try {
			// Instanciamos XSTREAM
			XStream xstream = new XStream();

			/*
			 * En general, las etiquetas XML se corresponden con el nombre de los atributos
			 * de la clase, pero se pueden cambiar utilizando el mÃ©todo alias(). En este
			 * caso, cambiamos el de la calse ListaPersonas y el de la clase Persona.
			 */
			// Creamos las etiquetas para el fichero XML
			xstream.alias("ListaCochesVendido", ListaCoches.class);
			xstream.alias("CocheVendido", Coche.class);

			// Quitar etiqueta "lista" (que es un atributo de la clase ListaPersona)
			xstream.addImplicitCollection(ListaCoches.class, "listado");

			// Generar el fichero Personas.XML a partir de una lista de objetos.
			// Insertamos los objetos en un XML: toXML (objeto, OutputStream)
			xstream.toXML(listcoc, new FileOutputStream("cochesvendidos.XML"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void generarEmpleados(Connection conexion) throws SQLException {

		// Creamos un objeto de ListaPersonas
		ListaEmpleado listcoc = new ListaEmpleado();
		listcoc.setListadoempleado(ManejoBaseDatos.getEpleados());

		// Usamos XSTREAM
		try {
			// Instanciamos XSTREAM
			XStream xstream = new XStream();

			/*
			 * En general, las etiquetas XML se corresponden con el nombre de los atributos
			 * de la clase, pero se pueden cambiar utilizando el mÃ©todo alias(). En este
			 * caso, cambiamos el de la calse ListaPersonas y el de la clase Persona.
			 */
			// Creamos las etiquetas para el fichero XML
			xstream.alias("ListaEmpleados", ListaEmpleado.class);
			xstream.alias("Empleado", Empleado.class);

			// Quitar etiqueta "lista" (que es un atributo de la clase ListaPersona)
			xstream.addImplicitCollection(ListaEmpleado.class, "listadoempleado");

			// Generar el fichero Personas.XML a partir de una lista de objetos.
			// Insertamos los objetos en un XML: toXML (objeto, OutputStream)
			xstream.toXML(listcoc, new FileOutputStream("emple.XML"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void leerEmpleados(Connection conexion) throws SQLException {

		// Creamos un objeto de ListaPersonas
		

		// Usamos XSTREAM
		try {
			// Instanciamos XSTREAM
			XStream xstream = new XStream();

			/*
			 * En general, las etiquetas XML se corresponden con el nombre de los atributos
			 * de la clase, pero se pueden cambiar utilizando el mÃ©todo alias(). En este
			 * caso, cambiamos el de la calse ListaPersonas y el de la clase Persona.
			 */
			// Creamos las etiquetas para el fichero XML
			xstream.alias("ListaEmpleados", ListaEmpleado.class);
			xstream.alias("Empleado", Empleado.class);

			// Quitar etiqueta "lista" (que es un atributo de la clase ListaPersona)
			xstream.addImplicitCollection(ListaEmpleado.class, "listadoempleado");

			// Generar el fichero Personas.XML a partir de una lista de objetos.
			// Insertamos los objetos en un XML: toXML (objeto, OutputStream)
			ListaEmpleado l=(ListaEmpleado) xstream.fromXML(new File("emple.XML"));
			System.out.println(l.getListadoempleado());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public void generarClientes(Connection conexion) throws SQLException {

		// Creamos un objeto de ListaPersonas
		ListaClientes listcoc = new ListaClientes();
		listcoc.setListado(ManejoBaseDatos.getClientes());

		// Usamos XSTREAM
		try {
			// Instanciamos XSTREAM
			XStream xstream = new XStream();

			/*
			 * En general, las etiquetas XML se corresponden con el nombre de los atributos
			 * de la clase, pero se pueden cambiar utilizando el mÃ©todo alias(). En este
			 * caso, cambiamos el de la calse ListaPersonas y el de la clase Persona.
			 */
			// Creamos las etiquetas para el fichero XML
			xstream.alias("ListaClientes", ListaClientes.class);
			xstream.alias("Cliente", Cliente.class);

			// Quitar etiqueta "lista" (que es un atributo de la clase ListaPersona)
			xstream.addImplicitCollection(ListaClientes.class, "listadocliente");

			// Generar el fichero Personas.XML a partir de una lista de objetos.
			// Insertamos los objetos en un XML: toXML (objeto, OutputStream)
			xstream.toXML(listcoc, new FileOutputStream("Clientes.XML"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
