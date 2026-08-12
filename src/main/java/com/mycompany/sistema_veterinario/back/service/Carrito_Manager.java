package com.mycompany.sistema_veterinario.back.service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.mycompany.sistema_veterinario.back.model.Productos;
import java.util.ArrayList;

/**
 *
 * @author dikeg
 */
public class Carrito_Manager {
    private ArrayList<Productos> productos = new ArrayList<>();

    public void agregarProducto(Productos p) {
        productos.add(p);
    }
 public void vaciarCarrito() {
        productos.clear();
    }
    public ArrayList<Productos> getProductos() {
        return productos;
    }

    public double total() {
        double suma = 0;
        for (Productos p : productos) {
            suma += p.getPrecio();
        }
        return suma;
    }

   
}
