package com.mycompany.sistema_veterinario.back.respository;
import com.mycompany.sistema_veterinario.back.config.conexion;
import com.mycompany.sistema_veterinario.back.model.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioRepository {
    public Usuarios buscarPorCorreo(String correo) throws Exception {
        String sql = """
                        SELECT cedula, nombre, apellido, correo, password_hash, rol
                    FROM usuarios
                    WHERE correo = ?
                """;
        try (Connection conexio = conexion.conectar();
             PreparedStatement sentencia = conexio.prepareStatement(sql)) {
            sentencia.setString(1, correo);
            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    return new Usuarios(
                            resultado.getString("cedula"),
                            resultado.getString("nombre"),
                            resultado.getString("apellido"),
                            resultado.getString("correo"),
                            resultado.getString("password_hash"),
                            resultado.getString("rol")

                    );
                }
            }
        }
        return null;
    }
}





