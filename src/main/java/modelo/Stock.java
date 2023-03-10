/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Ignacio Romero
 */
public class Stock {
    
    private String marca;
    private String modelo;
    private int unidades;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public Stock(String marca, String modelo, int unidades) {
        this.marca = marca;
        this.modelo = modelo;
        this.unidades = unidades;
    }

    public Stock() {
    }

    @Override
    public String toString() {
        return "Stock{" + "marca=" + marca + ", modelo=" + modelo + ", unidades=" + unidades + '}';
    }
    
    
    
    
}
