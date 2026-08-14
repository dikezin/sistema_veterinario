package com.mycompany.sistema_veterinario.back.respository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mycompany.sistema_veterinario.back.config.conexion;
import com.mycompany.sistema_veterinario.back.model.Productos;
public class ProductosRepository {
    public boolean guardarProductos (Productos producto) throws SQLException{
        String sql = """
                INSERT INTO producto ( nombre, categoria, stock, precio)
                values (?,?,?,?,?)
                """;
        try (Connection conexionBD = conexion.conectar();
            PreparedStatement ps = conexionBD.prepareStatement(sql)){
            ps.setString(1,producto.getNombre());
            ps.setString(2,producto.getCategoria());
            ps.setInt(3,producto.getStock());
            ps.setDouble(4, producto.getPrecio());
            int filasGuardadas = ps.executeUpdate();

            return filasGuardadas > 0;

        }

    }

}
