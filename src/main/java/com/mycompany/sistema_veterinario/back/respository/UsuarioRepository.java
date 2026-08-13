package com.mycompany.sistema_veterinario.back.respository;
import com.mycompany.sistema_veterinario.back.config.conexion;
import com.mycompany.sistema_veterinario.back.model.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public boolean guardarUsuario(Usuarios usuario) throws SQLException {
        String sql = """
        
                INSERT INTO usuarios
        (cedula, nombre, apellido, correo, password_hash, rol)
        VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conexionBD = conexion.conectar();
             PreparedStatement ps = conexionBD.prepareStatement(sql)) {

            ps.setString(1, usuario.getCedula());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getCorreo());
            ps.setString(5, usuario.getPass());
            ps.setString(6, usuario.getRol());

            int filasGuardadas = ps.executeUpdate();

            return filasGuardadas > 0;
        }
    }
    }






