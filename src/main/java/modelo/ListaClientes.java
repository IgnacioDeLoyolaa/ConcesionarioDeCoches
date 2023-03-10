package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaClientes implements Serializable {

	  public List<Cliente> listadocliente = new ArrayList();

	    public List<Cliente> getListado() {
	        return listadocliente;
	    }

	    public void setListado(List<Cliente> listadocliente) {
	        this.listadocliente = listadocliente;
	    }
}
