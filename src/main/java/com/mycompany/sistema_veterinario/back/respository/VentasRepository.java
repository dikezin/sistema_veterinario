/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_veterinario.back.respository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mycompany.sistema_veterinario.back.model.Ventas_detalle;
import com.mycompany.sistema_veterinario.back.config.conexion;
import com.mycompany.sistema_veterinario.back.model.Ventas_cab;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author dikeg
 */
public class VentasRepository {
    public static void main(String[] args) {
        try {
            Ventas_cab venta = new Ventas_cab();
            VentasRepository vr = new VentasRepository();
            venta.setUsuario_id(1);
            venta.setFecha(LocalDate.now());
            ArrayList<Ventas_detalle> detalles = new ArrayList<>();
            Ventas_detalle detalle1 = new Ventas_detalle();
            detalle1.setProducto_id(2);
            detalle1.setCantidad(20);
            detalle1.setPrecioUnitario(12.5);
            Ventas_detalle detalle2 = new Ventas_detalle();
            detalle2.setProducto_id(5);
            detalle2.setCantidad(6);
            detalle2.setPrecioUnitario(10.00);

            detalles.add(detalle1);
            detalles.add(detalle2);

            double total = 0;
            for (Ventas_detalle detalle : detalles) {
                total += detalle.getCantidad()
                        * detalle.getPrecioUnitario();
            }
            venta.setTotal(total);
            venta.setDetalles(detalles);
            boolean registrada = vr.registrarVenta(venta);
            if (registrada) {
                System.out.println("venta registrada");
                System.out.println("Total: " + total);
            } else {
                System.out.println("No se pudo registrar la venta");
            }
        } catch (Exception e) {
            System.out.println("Error al registrar venta");
            System.out.println(e.getMessage());

        }
    }
   public boolean registrarVenta (Ventas_cab venta) throws SQLException{
       Connection conBD = null;

       try {
           conBD = conexion.conectar();

           // Iniciamos la transacción
           conBD.setAutoCommit(false);

           // 1. Guardamos cabecera y recuperamos su ID
           int ventaId = venta_cab(conBD, venta);

           // 2. Guardamos todos los detalles usando ese ID
           venta_detalle(conBD, ventaId, venta.getDetalles());

           // 3. Si todo salió bien, confirmamos
           conBD.commit();

           return true;

       } catch (SQLException e) {

           // Si algo falló, deshacemos todo
           if (conBD != null) {
               conBD.rollback();
           }

           throw e;

       } finally {

           if (conBD != null) {
               conBD.close();
           }
       }
   }
    private int venta_cab (Connection conDB, Ventas_cab ventas) throws SQLException{
        String sql = """
                     insert into venta_cab ( usuario_id, fecha, total)
                     values (?,?,?)
                     """;
         try(
    PreparedStatement ps = conDB.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS))
             
         {
            ps.setInt(1,ventas.getUsuario_id());
            ps.setDate(2,java.sql.Date.valueOf(ventas.getFecha()));
            ps.setDouble(3, ventas.getTotal());
            int filasGuardadas = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);
            }
throw new SQLException ("No se puede obtener el id de la venta");
                    } 
             
    }
   private boolean venta_detalle (Connection conDB, int ventas_cab_id, List<Ventas_detalle> detalles) throws SQLException{
        String sql = """
                     insert into venta_detalle (venta_cab_id, producto_id, cantidad,precioUnitario, subTotal)
                     values (?,?,?,?,?)
                     """;
         try(

    PreparedStatement ps = conDB.prepareStatement(sql))
             
         {
            for (Ventas_detalle detalle : detalles){
                ps.setInt(1, ventas_cab_id);
                ps.setLong(2, detalle.getProducto_id());
                ps.setInt(3, detalle.getCantidad());
                double subtotal = detalle.getCantidad()*detalle.getPrecioUnitario();
                ps.setDouble(4,detalle.getPrecioUnitario());
                ps.setDouble(5,subtotal);
                ps.addBatch();
            }
            int [] resultados = ps.executeBatch();
            return resultados.length>0;
         } 
             
    }
}

