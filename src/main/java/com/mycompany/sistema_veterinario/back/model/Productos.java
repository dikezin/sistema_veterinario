package com.mycompany.sistema_veterinario.back.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author dikeg
 */
public class Productos {
  private String codigo;
    private String nombre;
    private String categoria;
    private int stock;
    private double precio;
    private boolean activo=false;


    public Productos(String codigo, String nombre, String categoria, int stock, double precio, boolean activo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.stock = stock;
        this.precio = precio;
        this.activo=activo;
    }

    // GETTERS
    public String getCodigo() {
        return codigo;
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
    public boolean getActivo(){return activo;}



    // SETTERS
    public void setStock(int stock) {
        this.stock = stock;
    }
}
