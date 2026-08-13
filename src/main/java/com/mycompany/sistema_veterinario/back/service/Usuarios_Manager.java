/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_veterinario.back.service;
import com.mycompany.sistema_veterinario.back.respository.UsuarioRepository;
import com.mycompany.sistema_veterinario.back.model.Usuarios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author home
 */
public class Usuarios_Manager {
  private static final String ARCHIVO = "archivos/usuarios.txt";

    public static void guardar(Usuarios u) throws IOException {

        // Verificar si la cédula ya existe
        if (cedulaExiste(u.getCedula())) {
            throw new IOException("Cédula ya registrada");
        }

        FileWriter fw = new FileWriter(ARCHIVO, true);
        fw.write(u.getCedula().trim()+ ";" +u.getNombre().trim()+ ";" +
                 u.getApellido().trim() + ";" +
                 u.getCorreo().trim()+ ";" +
                u.getPass().trim()+ ";" +
               u.getRol().trim() + "\n");
        fw.close();
    }

    public static Usuarios login(String correo, String pass) {
        try {
            UsuarioRepository repository = new UsuarioRepository();
            Usuarios usuario = repository.buscarPorCorreo(correo);
            if (correo != null
                    && usuario.getPass().equals(pass)) {
                return usuario;
            }

        } catch (Exception e) {
            System.out.println("Error al iniciar sesion");
            e.printStackTrace();
        }
        return null;
    }

  public static void crearAdminSiNoExiste() {

    boolean adminExiste = false;

    try {
        File archivo = new File(ARCHIVO);

        if (archivo.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 6 && datos[5].equals("ADMIN")) {
                    adminExiste = true;
                    break;
                }
            }
            br.close();
        }

        if (!adminExiste) {
            FileWriter fw = new FileWriter(ARCHIVO, true);
            fw.write(
                "0000000000;Admin;Sistema;admin@admin.com;1234;ADMIN\n"
            );
            fw.close();
            System.out.println("ADMIN creado correctamente");
        }

    } catch (IOException e) {
        System.out.println("Error creando admin");
    }
}


    
    

    // VERIFICAR CÉDULA REPETIDA
    private static boolean cedulaExiste(String cedulaBuscada) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(ARCHIVO));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");

                if (datos[0].equals(cedulaBuscada)) {
                    br.close();
                    return true;
                }
            }

            br.close();
        } catch (Exception e) {
            // archivo no existe todavía
        }

        return false;
    }
}
