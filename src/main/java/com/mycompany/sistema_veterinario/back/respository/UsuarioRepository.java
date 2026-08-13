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
    public boolean guardarUsuario (Usuarios usuarios) throws Exception{
        String sql = """
                INSERT INTO usuarios
                (cedula, nombre, apellido, correo, password_hash, rol)
                values(?,?,?,?,?,?)
                
                """;
        try (Connection conexio = conexion.conectar());
            PreparedStatement ps = conexion.prepareStatement(sql) {

                ps.setString(1,usuarios.getCedula());
                ps.setString(2,usuarios.getNombre());
                ps.setString(3,usuarios.getApellido());
                ps.setString(4, usuarios.getCorreo());
                ps.setString(5, usuarios.getPass());
                ps.setString(6, usuarios.getRol());
            }
            int filasGuardadas= ps.executeUpdate();
        return filasGuardadas>0;
    }
}





