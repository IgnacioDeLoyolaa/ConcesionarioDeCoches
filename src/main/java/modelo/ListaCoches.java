/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ignacio Romero
 */
public class ListaCoches implements Serializable {

    public List<Coche> listado = new ArrayList();

    public List<Coche> getListado() {
        return listado;
    }

    public void setListado(List<Coche> listado) {
        this.listado = listado;
    }

}
