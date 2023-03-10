package controlador;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


public class FicheroDOM {
	public void generar(Connection conexion) throws ParserConfigurationException, DOMException, SQLException, TransformerFactoryConfigurationError, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // creando estancia
		DocumentBuilder builder = factory.newDocumentBuilder();
		DOMImplementation implementation = builder.getDOMImplementation();
		Document document = implementation.createDocument(null, "Personas", null); // creando estancia

			//Creamos un documento vacío, de nombre document,con el nodo raíz EMPLEADOS
		// creas la etiqueta padre del
																						// todo xml
			document.setXmlVersion("1.0");
			
			String sql_empleados="SELECT * FROM EMPLEADOS";
			Statement sentencia = conexion.createStatement();
			
			ResultSet resul_emplea = sentencia.executeQuery(sql_empleados);
			
			while(resul_emplea.next()) {
				Element raiz = document.createElement("empleado"); // nodo persona
				document.getDocumentElement().appendChild(raiz);
				CrearElemento("DNI",resul_emplea.getString(1),raiz,document);
				CrearElemento("nombre", resul_emplea.getString(2), raiz, document);
				CrearElemento("apellidos", resul_emplea.getString(3), raiz, document);
				CrearElemento("salario", resul_emplea.getString(4), raiz, document);
			}
			
			Source source = new DOMSource(document);
			Result result = new StreamResult(new java.io.File("Empleado.xml"));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);

	}
	
	static void CrearElemento(String datoEmple, String valor, Element raiz, Document document) {

		Element elem = document.createElement(datoEmple);
		Text text = document.createTextNode(valor); // damos valor
		raiz.appendChild(elem); // pegamos el elemento hijo a la raiz
		elem.appendChild(text); // pegamos el valor
	}
	public void generarClientes(Connection conexion) throws ParserConfigurationException, DOMException, SQLException, TransformerFactoryConfigurationError, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // creando estancia
		DocumentBuilder builder = factory.newDocumentBuilder();
		DOMImplementation implementation = builder.getDOMImplementation();
		Document document = implementation.createDocument(null, "Personas", null); // creando estancia

			//Creamos un documento vacío, de nombre document,con el nodo raíz EMPLEADOS
		// creas la etiqueta padre del
																						// todo xml
			document.setXmlVersion("1.0");
			
			String sql_clientes="SELECT * FROM CLIENTES";
			Statement sentencia = conexion.createStatement();
			
			ResultSet resul_clientes = sentencia.executeQuery(sql_clientes);
			
			while(resul_clientes.next()) {
				Element raiz = document.createElement("cliente"); // nodo persona
				document.getDocumentElement().appendChild(raiz);
				CrearElemento("ID",resul_clientes.getString(1),raiz,document);
				CrearElemento("nombre", resul_clientes.getString(2), raiz, document);
				CrearElemento("importe", resul_clientes.getString(3), raiz, document);
			}
			
			Source source = new DOMSource(document);
			Result result = new StreamResult(new java.io.File("Cliente.xml"));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);

	}
}