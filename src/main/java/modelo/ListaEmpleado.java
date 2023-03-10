package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaEmpleado implements Serializable{

	public List<Empleado> listadoempleado = new ArrayList();

	public List<Empleado> getListadoempleado() {
		return listadoempleado;
	}

	public void setListadoempleado(List<Empleado> listadoempleado) {
		this.listadoempleado = listadoempleado;
	}

}
