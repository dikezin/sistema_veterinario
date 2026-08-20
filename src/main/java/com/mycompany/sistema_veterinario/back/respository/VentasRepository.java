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
import com.mycompany.sistema_veterinario.back.model.Productos;
import com.mycompany.sistema_veterinario.back.model.Usuarios;
import com.mycompany.sistema_veterinario.back.model.Ventas_cab;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author dikeg
 */
public class VentasRepository {
   
    public int venta_cab (Ventas_cab ventas) throws SQLException{
        String sql = """
                     insert into venta_cab (venta_cab_id, usuario_id, fecha, total)
                     values (?,?,?,?,)
                     """;
         try(
    Connection conBD = conexion.conectar();
    PreparedStatement ps = conBD.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS));
             
         {
             ps.setInt(1, ventas.getId());
            ps.setInt(2,ventas.getUsuario_id());
            ps.setDate(3,java.sql.Date.valueOf(ventas.getFecha()));
            ps.setDouble(4, ventas.getTotal());
            int filasGuardadas = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);
            }
throw new SQLException ("No se puede obtener el id de la venta");
                    } 
             
    }
   public boolean venta_detalle (int ventas_cab_id, ArrayList<Ventas_detalle> detalles) throws SQLException{
        String sql = """
                     insert into venta_detalle (venta_detalle_id, venta_cab_id, producto_id, subTotal)
                     values (?,?,?,?)
                     """;
         try(
    Connection conBD = conexion.conectar();
    PreparedStatement ps = conBD.prepareStatement(sql))
             
         {
            for (Ventas_detalle detalle : detalles){
                ps.setInt(1, ventas_cab_id);
                ps.setLong(2, detalle.getProducto_id());
                ps.setInt(3, detalle.getCantidad());
                double subtotal = detalle.getCantidad()*detalle.getPrecioUnitario();
                ps.setDouble(4,subtotal);
                ps.addBatch();
            }
         } 
             
    }
}

