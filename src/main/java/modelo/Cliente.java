/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Objects;

/**
 *
 * @author Ignacio Romero
 */
public class Cliente {

    //Creamos los atributos del ciente (los que nos dice la base de datos)
    private String id;
    private String nombre;
    private String apellidos;
    private double importe;

    //Creamos los metodos get y set de cada atributo
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
    //Creamos un constructor con todos los ddatos

    public Cliente(String id, String nombre, String apellidos, double importe) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.importe = importe;
    }

    //Creamos un constructor vacio por si nos es mas comodo ir rellendo cada atributo con su metodo set por comodidad
    public Cliente() {
    }

    //Generamos el ToString para imprimir cada atributo y sea legible
    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", importe=" + importe + '}';
    }

    //Sobreescribimos  el metodo equals para poder comparar los clientes de una forma mas comoda
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
