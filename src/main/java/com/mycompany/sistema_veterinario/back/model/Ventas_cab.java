/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_veterinario.back.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author dikeg
 */
public class Ventas_cab {
    private List<Ventas_detalle> detalles = new ArrayList<>();
    private int venta_cab_id;
    private int usuario_id;
    private LocalDate fecha;
    private double sub_total;
    private double total;
    public Ventas_cab (int venta_cab_id, int usuario_id, LocalDate fecha, double sub_total, double total){
        this.venta_cab_id=venta_cab_id;this.fecha=fecha;this.usuario_id=usuario_id;this.sub_total=sub_total;this.total=total;
    }

    public int getId() {
        return venta_cab_id;
    }

    public void setId(int venta_cab_id) {
        this.venta_cab_id = venta_cab_id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getSub_total() {
        return sub_total;
    }

    public void setSub_total(double sub_total) {
        this.sub_total = sub_total;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Ventas_detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Ventas_detalle> detalles) {
        this.detalles = detalles;
    }
    
    
}
