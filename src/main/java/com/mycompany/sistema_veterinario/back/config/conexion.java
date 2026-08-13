package com.mycompany.sistema_veterinario.back.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {

    private static final String URL =
            "jdbc:mysql://localhost:3306/sistema_veterinario"
                    + "?useSSL=false&serverTimezone=UTC";

    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
    public static void main(String[] args) {
        try (Connection conexion = conectar()) {
            System.out.println("Conexión exitosa");
        } catch (SQLException e) {
            System.out.println("Error de conexión");
            e.printStackTrace();
        }
    }
}