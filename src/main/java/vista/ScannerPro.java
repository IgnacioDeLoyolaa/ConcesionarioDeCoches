package vista;

import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import modelo.Cliente;
import modelo.Coche;
import modelo.Empleado;
import modelo.Stock;
import modelo.Ventas;

public class ScannerPro {

	private static Scanner sc = new Scanner(System.in);

	  public static String mostrarMenu() {
	        System.out.println("Introduce la opcion que quieres hacer:");
	        System.out.println("1: Matricular coche");
	        System.out.println("2: Dar alta cliente");
	        System.out.println("3: Dar alta empleado");
	        System.out.println("4: Añadir stock coche");
	        System.out.println("5: Comprar coche");
	        System.out.println("6: Dar de baja cliente");
	        System.out.println("7: Dar de baja empleado");
	        System.out.println("8: Dar de baja stock");
	        System.out.println("9: Modificar cliente");
	        System.out.println("10: Modificar empleado");
	        System.out.println("11: Modificar stock");
	        System.out.println("12: Volcar clientes");
	        System.out.println("13: Volcar coches");
	        System.out.println("14: Volcar ventas");
	        System.out.println("15: Volcar empleados");
	        System.out.println("16: Insertar Datos de cliente con un fichero xml");
	        System.out.println("17: Insertar Datos de Coche con un fichero xml");
	        System.out.println("18: Insertar Datos de Empleado con un fichero xml");
	        System.out.println("19: Salir");
	        
	        String opcion = sc.nextLine();
	        return opcion;

	    }

	public static int GestionEmpleados() {
		int opcion;

		System.out.println("Menú de gestión de lista, altas, bajas y modificaciones de empleados");

		System.out.println("Escoge la opción que quiera realizar:");
		System.out.println("1. Alta de empleados");
		System.out.println("2. Modificacion de empleados");
		System.out.println("3. Eliminacion o borrado de empleados");
		System.out.println("4. Mostrar Empleados");
		System.out.println("5. Volver");
		opcion = sc.nextInt();
		sc.nextLine();

		return opcion;

	}

	public static int GestionCoches() {
		int opcion;

		System.out.println("Menú de gestión de lista, altas, bajas y modificaciones de coches");

		System.out.println("Escoge la opción que quiera realizar:");
		System.out.println("1. Añadir Stock");
		System.out.println("1. Matricular un coche");
		System.out.println("2. Modificacion de un coche");
		System.out.println("3. Eliminacion o borrado de un coche");
		System.out.println("4. Venta de un coche a un cliente.");
		System.out.println("5. Mostrar coches");
		System.out.println("6. Salir");
		opcion = sc.nextInt();
		sc.nextLine();

		return opcion;

	}

	public static String pedirDNI() {
		System.out.println("Introduce el dni del vendedor");
		String dni = sc.nextLine();
		return dni;
	}

	public static int GestionClientes() {
		int opcion;

		System.out.println("Menú de gestión de lista, altas, bajas y modificaciones de clientes");

		System.out.println("Escoge la opción que quiera realizar:");
		System.out.println("1. Alta de un cliente");
		System.out.println("2. Modificacion de un cliente");
		System.out.println("3. Eliminacion o borrado de un cliente");
		System.out.println("4. Mostrar Clientes");
		System.out.println("5. Volver");
		opcion = sc.nextInt();
		sc.nextLine();

		return opcion;
	}

	public static Empleado pedirEmpleado() {
		// TODO Auto-generated method stub

		int opcion;
		String nombre = "";

		String apellido = "";

		String DNI = "";

		String salario = "";
		System.out.println("Dar de alta a un empleado");

		System.out.println("Por favor introduzca un nombre:");

		nombre = sc.nextLine();

		System.out.println("Por favor introduzca un apellido:");

		apellido = sc.nextLine();

		System.out.println("Por favor introduzca un DNI:");

		DNI = sc.nextLine();

		System.out.println("Por favor introduzca un salario: ");

		salario = sc.nextLine();

		Empleado e = new Empleado(nombre, apellido, DNI, Double.parseDouble(salario));

		return e;

	}

	public static Empleado pedirEmpleado(Empleado e) {
		// TODO Auto-generated method stub

		System.out.println("Por favor introduzca un nombre:");

		String nombre = sc.nextLine();

		System.out.println("Por favor introduzca un apellido:");

		String apellido = sc.nextLine();

		System.out.println("Por favor introduzca un salario: ");

		String salario = sc.nextLine();

		e.setNombre(nombre);
		e.setApellidos(apellido);
		e.setSalario(Double.parseDouble(salario));
		return e;

	}
	public static Cliente altaClientes() {

        Cliente cl = new Cliente();
        System.out.println("Introduce el ID");
        String id = sc.nextLine();
        cl.setId(id);
        System.out.println("Introduce el nombre");
        String nombre = sc.nextLine();
        cl.setNombre(nombre);
        System.out.println("Introduce los apellidos");
        String apellidos = sc.nextLine();
        cl.setApellidos(apellidos);
        System.out.println("Introduce el importe");
        double importe = sc.nextDouble();
        sc.nextLine();
        cl.setImporte(importe);
        return cl;
    }
	 public static String pedirID() {
	        System.out.println("Introduce el id del cliente");
	        String id = sc.nextLine();
	        return id;
	    }
	 public static void altaClientes(Cliente cl) {

	        System.out.println("Introduce el nombre");
	        String nombre = sc.nextLine();
	        cl.setNombre(nombre);
	        System.out.println("Introduce los apellidos");
	        String apellidos = sc.nextLine();
	        cl.setApellidos(apellidos);
	        System.out.println("Introduce el importe");
	        double importe = sc.nextDouble();
	        sc.nextLine();
	        cl.setImporte(importe);

	    }
	 public static String pedirModdelo() {
	        System.out.println("Introduce el modelo del coche");
	        String modelo = sc.nextLine();
	        return modelo;
	    }
	 public static void matricularCoche(Coche c) {

	        System.out.println("Introduce la matricula");
	        String matricula = sc.nextLine();
	        c.setMatricula(matricula);
	        System.out.println("Introduce el precio");
	        double precio = sc.nextDouble();
	        sc.nextLine();
	        c.setPrecio(precio);
	        c.setVendido(false);
	    }
	 
	 public static Stock altaStock() {

	        Stock s = new Stock();
	        System.out.println("Introduce la marca");
	        String marca = sc.nextLine();
	        s.setMarca(marca);
	        System.out.println("Introduce el modelo");
	        String modelo = sc.nextLine();
	        s.setModelo(modelo);
	        System.out.println("Introduce el numero de unidades");
	        int unidades = sc.nextInt();
	        sc.nextLine();
	        s.setUnidades(unidades);

	        return s;
	    }
	 public static void altaStock(Stock s) {

	        System.out.println("Introduce la marca");
	        String marca = sc.nextLine();
	        s.setMarca(marca);

	        System.out.println("Introduce el numero de unidades");
	        int unidades = sc.nextInt();
	        sc.nextLine();
	        s.setUnidades(unidades);

	    }
	 public static Ventas pedirVenta() {

	        Ventas v = new Ventas();
	        System.out.println("DNI del vendedor");
	        String dni = sc.nextLine();
	        v.setDni(dni);
	        System.out.println("ID del cliente");
	        String id = sc.nextLine();
	        v.setId(id);
	        System.out.println("Matricula del coche");
	        String matricula = sc.nextLine();
	        v.setMatricula(matricula);
	        v.setFecha(new Date());

	        return v;

	    }
	 public static boolean deseaContinuar() {
	        System.out.println("Desea continuar dando de alta los datos restantes? Y/N");
	        return sc.nextLine().equalsIgnoreCase("Y");
	    }
	    public static Empleado altaEmpleados() {

	        Empleado e = new Empleado();
	        System.out.println("Introduce el DNI");
	        String dni = sc.nextLine();
	        e.setDni(dni);
	        System.out.println("Introduce el nombre");
	        String nombre = sc.nextLine();
	        e.setNombre(nombre);
	        System.out.println("Introduce los apellidos");
	        String apellidos = sc.nextLine();
	        e.setApellidos(apellidos);
	        System.out.println("Introduce el salario");
	        double salario = sc.nextDouble();
	        sc.nextLine();
	        e.setSalario(salario);
	        return e;
	    }

}
