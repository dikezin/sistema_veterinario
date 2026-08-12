package com.mycompany.sistema_veterinario;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author dikeg
 */
public class Productos {
  private String id;
    private String nombre;
    private String categoria;
    private int stock;
    private double precio;

    public Productos(String id, String nombre, String categoria, int stock, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.stock = stock;
        this.precio = precio;
    }

    // GETTERS
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getStock() {
        return stock;
    }

    public double getPrecio() {
        return precio;
    }

    // SETTERS
    public void setStock(int stock) {
        this.stock = stock;
    }
}
