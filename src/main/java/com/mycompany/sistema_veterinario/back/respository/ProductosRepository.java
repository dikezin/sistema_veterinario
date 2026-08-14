package com.mycompany.sistema_veterinario.back.respository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mycompany.sistema_veterinario.back.config.conexion;
import com.mycompany.sistema_veterinario.back.model.Productos;
public class ProductosRepository {
    public ArrayList<Productos> listarProductos() throws Exception{
        ArrayList<Productos> productos = new ArrayList<>();
        String sql = """
                SELECT codigo, nombre, categoria, stock, precio, activo
                 from productos 
                order by nombre
                """;
        try (Connection conexionDB = conexion.conectar();
             PreparedStatement ps = conexionDB.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                Productos producto = new Productos(

                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getInt("stock"),
                        rs.getDouble("precio"),
                        rs.getBoolean("activo")
                );
                productos.add(producto);
            }
        }

return productos;
    }
    public boolean guardarProductos (Productos producto) throws SQLException{
        String sql = """
                INSERT INTO productos (codigo, nombre, categoria, stock, precio, activo)
                values (?,?,?,?,?,?)
                """;
        try (Connection conexionBD = conexion.conectar();
            PreparedStatement ps = conexionBD.prepareStatement(sql))

        {
            ps.setString(1,producto.getCodigo());
            ps.setString(2,producto.getNombre());
            ps.setString(3,producto.getCategoria());
            ps.setInt(4,producto.getStock());
            ps.setDouble(5, producto.getPrecio());
            ps.setBoolean(6,producto.getActivo());
            int filasGuardadas = ps.executeUpdate();

            return filasGuardadas > 0;

        }

    }

    public boolean eliminarProducto(String codigo) throws SQLException {
        String sql = "DELETE FROM productos WHERE codigo = ?";

        try (Connection conexionBD = conexion.conectar();
             PreparedStatement ps = conexionBD.prepareStatement(sql)) {

            ps.setString(1, codigo);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean comprarProducto(String codigo, int cantidad) throws SQLException {
        String sql = """
                UPDATE productos
                SET stock = stock - ?
                WHERE codigo = ? AND stock >= ?
                """;

        try (Connection conexionBD = conexion.conectar();
             PreparedStatement ps = conexionBD.prepareStatement(sql)) {

            ps.setInt(1, cantidad);
            ps.setString(2, codigo);
            ps.setInt(3, cantidad);
            return ps.executeUpdate() > 0;
        }
    }

}
