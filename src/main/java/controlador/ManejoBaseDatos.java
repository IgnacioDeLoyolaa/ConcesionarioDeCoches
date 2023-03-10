package controlador;

import controlador.conexion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import modelo.Cliente;
import modelo.Coche;
import modelo.Empleado;
import modelo.Stock;
import modelo.Ventas;

public class ManejoBaseDatos {

	private static conexion conexion;

	public static void generarTextoPlano() throws IOException, SQLException {
		conexion = new conexion();
		Connection con = conexion.getConnection();

		File fichero = new File("VentasCoche1.txt");

		FileWriter fw = new FileWriter(fichero);

		String cadena = "";

		String sql_sacar_ventas_coche = "SELECT * FROM VENTAS INNER JOIN COCHES ON ventas.matricula = coches.matricula where vendido like '1' and fecha >= '01-01-2022' and fecha <= '31-12-2022'";

		Statement sentencia = con.createStatement();

		ResultSet resul_ventas_coche = sentencia.executeQuery(sql_sacar_ventas_coche);

		fw.write("FECHA\tMATRIC\t \n");
		while (resul_ventas_coche.next()) {

			cadena = resul_ventas_coche.getString(4) + "\t" + resul_ventas_coche.getString(1) + "\t"
					+ resul_ventas_coche.getString(2) + "\t" + resul_ventas_coche.getString(3) + "\t" + "\t"
					+ resul_ventas_coche.getString(6) + "\t" + resul_ventas_coche.getString(7) + "\t"
					+ resul_ventas_coche.getString(8) + "\n";
			fw.write(cadena);

		}
		fw.write("TOTALES \n");
		String sql_sacar_ventas_coche_total = "SELECT MARCA,MODELO,COUNT(*) AS UNIDADES,SUM(PRECIO) AS TOTAL FROM VENTAS INNER JOIN COCHES ON ventas.matricula = coches.matricula where vendido like '1' and fecha >= '01-01-2022' and fecha <= '31-12-2022' GROUP BY MARCA,MODELO ORDER BY MARCA,MODELO ASC";

		Statement sentencia_total = con.createStatement();

		ResultSet resul_ventas_coche_total = sentencia.executeQuery(sql_sacar_ventas_coche_total);
		while (resul_ventas_coche_total.next()) {
			cadena = resul_ventas_coche_total.getString(1) + "\t" + resul_ventas_coche_total.getString(2) + "\t"
					+ resul_ventas_coche_total.getString(3) + "\t" + resul_ventas_coche_total.getString(4) + "\n";
			fw.write(cadena);

		}
		fw.flush();
		fw.close();

		fichero.createNewFile();

	}

	public static void ficheroDom() throws DOMException, ParserConfigurationException, SQLException,
			TransformerFactoryConfigurationError, TransformerException {
		conexion = new conexion();
		Connection con = conexion.getConnection();
		new FicheroDOM().generar(con);

	}

	public static void ficheroXStream() throws SQLException {
		conexion = new conexion();
		Connection con = conexion.getConnection();
		new FicheroXSTREAM().generar(con);
	}

	public static void ficheroXStreamTodo() throws SQLException {
		conexion = new conexion();
		Connection con = conexion.getConnection();
		FicheroXSTREAM a = new FicheroXSTREAM();
		a.leerEmpleados(con);
		a.generar(con);
		a.generarClientes(con);
		a.generarEmpleados(con);
	}

	public static void LecturaXML() throws SQLException {
		conexion = new conexion();
		Connection con = conexion.getConnection();
		LecturaEmpleadoXml b = new LecturaEmpleadoXml();

	}

	public static void escribirEmpleadosXML() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("empleados.xml"));
			document.getDocumentElement().normalize();

			System.out.printf("Elemento raiz: %s %n", document.getDocumentElement().getNodeName());
			// crea una lista con todos los nodos empleado
			NodeList empleados = document.getElementsByTagName("Empleado");
			System.out.printf("Nodos empleado a recorrer: %d %n", empleados.getLength());

			// recorrer la lista
			ArrayList<Empleado> listado = new ArrayList<>();
			for (int i = 0; i < empleados.getLength(); i++) {
				Node emple = empleados.item(i); // obtener un nodo empleado
				if (emple.getNodeType() == Node.ELEMENT_NODE) {// tipo de nodo
					Empleado e = new Empleado();

					// obtener los elementos del nodo
					Element elemento = (Element) emple;
					e.setDni(elemento.getElementsByTagName("dni").item(0).getTextContent());
					e.setNombre(elemento.getElementsByTagName("nombre").item(0).getTextContent());
					e.setApellidos(elemento.getElementsByTagName("apellidos").item(0).getTextContent());
					e.setSalario(Double.valueOf(elemento.getElementsByTagName("salario").item(0).getTextContent()));
					listado.add(e);
				}
			}

			for (Empleado empleado : listado) {
				ManejoBaseDatos.insertarEmpleado(empleado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void escribirClientesXML() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("Clientes.xml"));
			document.getDocumentElement().normalize();

			System.out.printf("Elemento raiz: %s %n", document.getDocumentElement().getNodeName());
			// crea una lista con todos los nodos empleado
			NodeList empleados = document.getElementsByTagName("Cliente");
			System.out.printf("Nodos empleado a recorrer: %d %n", empleados.getLength());

			// recorrer la lista
			ArrayList<Cliente> listado = new ArrayList<>();
			for (int i = 0; i < empleados.getLength(); i++) {
				Node emple = empleados.item(i); // obtener un nodo empleado
				if (emple.getNodeType() == Node.ELEMENT_NODE) {// tipo de nodo
					Cliente e = new Cliente();

					// obtener los elementos del nodo
					Element elemento = (Element) emple;
					e.setId(elemento.getElementsByTagName("id").item(0).getTextContent());
					e.setNombre(elemento.getElementsByTagName("nombre").item(0).getTextContent());
					e.setApellidos(elemento.getElementsByTagName("apellidos").item(0).getTextContent());
					e.setImporte(Double.valueOf(elemento.getElementsByTagName("importe").item(0).getTextContent()));
					listado.add(e);
				}
			}

			for (Cliente cliente : listado) {
				ManejoBaseDatos.insertarCliente(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void escribirCochesXML() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("cochesVendidos.xml"));
			document.getDocumentElement().normalize();

			System.out.printf("Elemento raiz: %s %n", document.getDocumentElement().getNodeName());
			// crea una lista con todos los nodos empleado
			NodeList empleados = document.getElementsByTagName("CocheVendido");
			System.out.printf("Nodos empleado a recorrer: %d %n", empleados.getLength());

			// recorrer la lista
			ArrayList<Coche> listado = new ArrayList<>();
			for (int i = 0; i < empleados.getLength(); i++) {
				Node emple = empleados.item(i); // obtener un nodo empleado
				if (emple.getNodeType() == Node.ELEMENT_NODE) {// tipo de nodo
					Coche e = new Coche();

					// obtener los elementos del nodo
					Element elemento = (Element) emple;
					e.setMatricula(elemento.getElementsByTagName("matricula").item(0).getTextContent());
					e.setMarca(elemento.getElementsByTagName("marca").item(0).getTextContent());
					e.setModelo(elemento.getElementsByTagName("modelo").item(0).getTextContent());
					e.setPrecio(Double.valueOf(elemento.getElementsByTagName("precio").item(0).getTextContent()));
					e.setVendido("1".equals(elemento.getElementsByTagName("vendido").item(0).getTextContent()));

					listado.add(e);
				}

			}

			for (Coche c : listado) {
				System.out.println("asdass");
				ManejoBaseDatos.insertarCoche(c);
				System.out.println("aaaaa");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int insertarCoche(Coche c) throws Exception {
		int filasInsertadas = 0;
		conexion = new conexion();
		Connection con = conexion.getConnection();

		String sql = "INSERT INTO coches (MATRICULA, MARCA, MODELO, PRECIO, VENDIDO) VALUES('" + c.getMatricula()
				+ "', '" + c.getMarca() + "', '" + c.getModelo() + "', " + c.getPrecio() + ", 0)";
		PreparedStatement sentencia = null;

		sentencia = con.prepareStatement(sql);
		System.out.println("cccc");
		filasInsertadas = sentencia.executeUpdate();
		System.out.println("asdasd");
		return filasInsertadas;
	}

	public static int insertarCliente(Cliente cl) throws SQLException {
		int filasInsertadas = 0;
		conexion = new conexion();
		Connection con = conexion.getConnection();

		String sql = "INSERT INTO clientes (ID, NOMBRE, APELLIDOS, IMPORTE) VALUES('" + cl.getId() + "', '"
				+ cl.getNombre() + "', '" + cl.getApellidos() + "', " + cl.getImporte() + ")";
		PreparedStatement sentencia;

		try {

			sentencia = con.prepareStatement(sql);

			filasInsertadas = sentencia.executeUpdate();
		} catch (Exception e) {

		}
		return filasInsertadas;
	}

	public static int borrarCliente(Cliente cl) throws SQLException {
		int filasInsertadas = 0;
		conexion = new conexion();
		Connection con = conexion.getConnection();

		String sql = "DELETE FROM CLIENTES WHERE id = '" + cl.getId() + "'";
		PreparedStatement sentencia;

		try {

			sentencia = con.prepareStatement(sql);

			filasInsertadas = sentencia.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("mierda");
		}
		return filasInsertadas;
	}

	public static int modificarCliente(Cliente cl) throws SQLException {
		int filasInsertadas = 0;
		conexion = new conexion();
		Connection con = conexion.getConnection();

		String sql = "UPDATE CLIENTES SET NOMBRE='" + cl.getNombre() + "', APELLIDOS='" + cl.getApellidos()
				+ "', IMPORTE=" + cl.getImporte() + " WHERE ID='" + cl.getId() + "'";
		System.out.println(sql);
		PreparedStatement sentencia;

		try {

			sentencia = con.prepareStatement(sql);

			filasInsertadas = sentencia.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("mierda");
		}
		return filasInsertadas;
	}

	public static int insertarEmpleado(Empleado em) throws SQLException {
		int filasInsertadas = 0;
		conexion = new conexion();
		Connection con = conexion.getConnection();

		String sql = "INSERT INTO empleados (DNI, NOMBRE, APELLIDOS, SALARIO) VALUES('" + em.getDni() + "', '"
				+ em.getNombre() + "', '" + em.getApellidos() + "', " + em.getSalario() + ")";
		PreparedStatement sentencia;

		try {

			sentencia = con.prepareStatement(sql);

			filasInsertadas = sentencia.executeUpdate();
		} catch (Exception e) {

		}
		return filasInsertadas;
	}

	public static int borrarEmpleado(Empleado em) throws SQLException {
		int filasInsertadas = 0;
		conexion = new conexion();
		Connection con = conexion.getConnection();

		String sql = "DELETE FROM empleados WHERE dni = '" + em.getDni() + "'";
		PreparedStatement sentencia;

		try {

			sentencia = con.prepareStatement(sql);

			filasInsertadas = sentencia.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("mierda");
		}
		return filasInsertadas;
	}

	public static int insertarStock(Stock s) throws SQLException {
		int filasInsertadas = 0;
		conexion = new conexion();
		Connection con = conexion.getConnection();

		String sql = "INSERT INTO stock (MARCA, MODELO, UNIDADES) VALUES('" + s.getMarca() + "', '" + s.getModelo()
				+ "', '" + s.getUnidades() + "')";
		PreparedStatement sentencia;

		try {

			sentencia = con.prepareStatement(sql);

			filasInsertadas = sentencia.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filasInsertadas;
	}

	public static int borrarStock(Stock s) throws SQLException {
		int filasInsertadas = 0;
		conexion = new conexion();
		Connection con = conexion.getConnection();

		String sql = "DELETE FROM stock WHERE modelo = '" + s.getModelo() + "'";
		PreparedStatement sentencia;

		try {

			sentencia = con.prepareStatement(sql);

			filasInsertadas = sentencia.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filasInsertadas;
	}

	public static Stock hayStock(String modelo) throws SQLException {
		Stock c = new Stock();

		conexion = new conexion();
		Connection con = conexion.getConnection();

		String sql = "SELECT * FROM STOCK WHERE MODELO='" + modelo + "'";
		PreparedStatement sentencia;
		try {

			sentencia = con.prepareStatement(sql);

			ResultSet resultSet = sentencia.executeQuery();

			if (resultSet.next()) {
				c.setUnidades(resultSet.getInt("UNIDADES"));
				c.setMarca(resultSet.getString("MARCA"));
				c.setModelo(modelo);
			} else {
				c = null;
			}
			resultSet.close();

		} catch (Exception e) {

		}
		return c;

	}

	public static int modificarStock(Stock cl) throws SQLException {
		int filasInsertadas = 0;
		conexion = new conexion();
		Connection con = conexion.getConnection();

		String sql = "UPDATE stock SET MARCA='" + cl.getMarca() + "', MODELO='" + cl.getModelo() + "', UNIDADES="
				+ cl.getUnidades() + " WHERE MODELO='" + cl.getModelo() + "'";
		PreparedStatement sentencia;

		try {

			sentencia = con.prepareStatement(sql);

			filasInsertadas = sentencia.executeUpdate();
		} catch (Exception e) {

		}
		return filasInsertadas;
	}

	public static int insertarVenta(Ventas v) throws SQLException {
		int filasInsertadas = 0;
		conexion = new conexion();
		Connection con = conexion.getConnection();

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");

		String fecha = format.format(v.getFecha());

		String sql = "INSERT INTO ventas (DNI, ID, MATRICULA, FECHA) VALUES('" + v.getDni() + "', '" + v.getId()
				+ "', '" + v.getMatricula() + "', TO_DATE('" + fecha + "', 'dd/MM/yy'))";
		PreparedStatement sentencia;
		System.out.println(sql);
		try {

			sentencia = con.prepareStatement(sql);
			filasInsertadas = sentencia.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filasInsertadas;
	}

	public static Empleado hayDni(String dni) throws SQLException {
		Empleado em = new Empleado();

		conexion = new conexion();
		Connection con = conexion.getConnection();

		String sql = "SELECT * FROM EMPLEADOs WHERE DNI='" + dni + "'";
		PreparedStatement sentencia;
		try {

			sentencia = con.prepareStatement(sql);

			ResultSet resultSet = sentencia.executeQuery();

			if (resultSet.next()) {
				em.setDni(resultSet.getString("DNI"));
				em.setNombre(resultSet.getString("NOMBRE"));
				em.setApellidos(resultSet.getString("APELLIDOS"));
				em.setSalario(resultSet.getInt("SALARIO"));
			} else {
				em = null;
			}
			resultSet.close();

		} catch (Exception e) {

		}
		return em;

	}

	public static ArrayList<Ventas> getVentas() throws SQLException {

		ArrayList<Ventas> listadoVentas = new ArrayList();

		conexion = new conexion();
		Connection con = conexion.getConnection();

		String sql = "SELECT VE.MATRICULA AS MATRICULA, VE.ID AS ID, VE.DNI AS DNI, CO.PRECIO AS PRECIO, VE.FECHA AS FECHA FROM VENTAS VE INNER JOIN COCHES CO ON CO.MATRICULA=VE.MATRICULA";
		PreparedStatement sentencia;
		try {

			sentencia = con.prepareStatement(sql);

			ResultSet resultSet = sentencia.executeQuery();

			while (resultSet.next()) {
				Ventas v = new Ventas();
				v.setDni(resultSet.getString("DNI"));
				v.setId(resultSet.getString("ID"));
				v.setMatricula(resultSet.getString("MATRICULA"));
				v.setFecha(resultSet.getDate("FECHA"));
				v.setPrecio(resultSet.getInt("PRECIO"));
				listadoVentas.add(v);
			}
			resultSet.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listadoVentas;

	}

	public static ArrayList<Coche> getCoches() throws SQLException {

		ArrayList<Coche> listadoCoches = new ArrayList();

		conexion = new conexion();
		Connection con = conexion.getConnection();

		String sql = "SELECT * FROM COCHES WHERE VENDIDO= 1";
		PreparedStatement sentencia;
		try {

			sentencia = con.prepareStatement(sql);

			ResultSet resultSet = sentencia.executeQuery();

			while (resultSet.next()) {
				Coche c = new Coche();
				c.setMatricula(resultSet.getString("MATRICULA"));
				c.setMarca(resultSet.getString("MARCA"));
				c.setModelo(resultSet.getString("MODELO"));
				c.setPrecio(resultSet.getInt("PRECIO"));
				c.setVendido(true);
				listadoCoches.add(c);
			}
			resultSet.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
		return listadoCoches;

	}

	public static ArrayList<Empleado> getEpleados() throws SQLException {

		ArrayList<Empleado> listadoEmpleados = new ArrayList();

		conexion = new conexion();
		Connection con = conexion.getConnection();

		String sql = "SELECT * FROM EMPLEADOS";
		PreparedStatement sentencia;
		try {

			sentencia = con.prepareStatement(sql);

			ResultSet resultSet = sentencia.executeQuery();

			while (resultSet.next()) {
				Empleado em = new Empleado();
				em.setDni(resultSet.getString("DNI"));
				em.setNombre(resultSet.getString("NOMBRE"));
				em.setApellidos(resultSet.getString("APELLIDOS"));
				em.setSalario(resultSet.getInt("SALARIO"));
				listadoEmpleados.add(em);
			}
			resultSet.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listadoEmpleados;

	}

	public static ArrayList<Cliente> getClientes() throws SQLException {

		ArrayList<Cliente> listadoClientes = new ArrayList();

		conexion = new conexion();
		Connection con = conexion.getConnection();

		String sql = "SELECT * FROM CLIENTES";
		PreparedStatement sentencia;
		try {

			sentencia = con.prepareStatement(sql);

			ResultSet resultSet = sentencia.executeQuery();

			while (resultSet.next()) {
				Cliente cl = new Cliente();
				cl.setId(resultSet.getString("ID"));
				cl.setNombre(resultSet.getString("NOMBRE"));
				cl.setApellidos(resultSet.getString("APELLIDOS"));
				cl.setImporte(resultSet.getInt("IMPORTE"));
				listadoClientes.add(cl);
			}
			resultSet.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listadoClientes;

	}

	public static Cliente hayId(String id) throws SQLException {
		Cliente cl = new Cliente();

		conexion = new conexion();
		Connection con = conexion.getConnection();

		String sql = "SELECT * FROM CLIENTES WHERE ID='" + id + "'";
		PreparedStatement sentencia;
		try {

			sentencia = con.prepareStatement(sql);

			ResultSet resultSet = sentencia.executeQuery();

			if (resultSet.next()) {
				cl.setId(resultSet.getString("ID"));
				cl.setNombre(resultSet.getString("NOMBRE"));
				cl.setApellidos(resultSet.getString("APELLIDOS"));
				cl.setImporte(resultSet.getInt("IMPORTE"));
			} else {
				cl = null;
			}
			resultSet.close();

		} catch (Exception e) {

		}
		return cl;

	}

	public static Coche hayMatricula(String matricula) throws SQLException {
		Coche c = new Coche();

		conexion = new conexion();
		Connection con = conexion.getConnection();

		String sql = "SELECT * FROM COCHES WHERE MATRICULA='" + matricula + "' AND VENDIDO=0";
		PreparedStatement sentencia;
		try {

			sentencia = con.prepareStatement(sql);

			ResultSet resultSet = sentencia.executeQuery();

			if (resultSet.next()) {
				c.setMatricula(resultSet.getString("MATRICULA"));
				c.setMarca(resultSet.getString("MARCA"));
				c.setModelo(resultSet.getString("MODELO"));
				c.setPrecio(resultSet.getInt("PRECIO"));
				c.setVendido(false);
			} else {
				c = null;
			}
			resultSet.close();

		} catch (Exception e) {

		}
		return c;

	}

	public static int modificarCoche(Coche cl) throws SQLException {
		int filasInsertadas = 0;
		conexion = new conexion();
		Connection con = conexion.getConnection();
		int vendido = 0;
		if (cl.isVendido()) {
			vendido = 1;
		}

		System.out.println(cl);
		String sql = "UPDATE coches SET MARCA='" + cl.getMarca() + "', MODELO='" + cl.getModelo() + "', PRECIO="
				+ cl.getPrecio() + ", VENDIDO=" + vendido + " WHERE MATRICULA='" + cl.getMatricula() + "'";
		PreparedStatement sentencia;
		System.out.println(sql);
		try {

			sentencia = con.prepareStatement(sql);

			filasInsertadas = sentencia.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filasInsertadas;
	}

	static int modificarEmpleados(Empleado c) throws SQLException {
		int filasInsertadas = 0;
		conexion = new conexion();
		Connection con = conexion.getConnection();

		String sql = "UPDATE EMPLEADOS SET NOMBRE='" + c.getNombre() + "', APELLIDOS='" + c.getApellidos()
				+ "', SALARIO=" + c.getSalario() + " WHERE DNI='" + c.getDni() + "'";
		PreparedStatement sentencia;

		try {

			sentencia = con.prepareStatement(sql);

			filasInsertadas = sentencia.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return filasInsertadas;
	}
}
