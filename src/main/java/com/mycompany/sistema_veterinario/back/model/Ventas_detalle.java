/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_veterinario.back.model;

/**
 *
 * @author dikeg
 */
public class Ventas_detalle {
     private int ventaId;
    private long producto_id;
    private int cantidad;
    private double precioUnitario;

    public Ventas_detalle(int ventaId, long producto_id, int cantidad, double precioUnitario) {
        this.ventaId = ventaId;
        this.producto_id = producto_id;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getVentaId() {
        return ventaId;
    }

    public void setVentaId(int ventaId) {
        this.ventaId = ventaId;
    }

    public long getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(long producto_id) {
        this.producto_id = producto_id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    
}
