/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_veterinario;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author home
 */
public class Usuarios {
    String nombre;
    String apellido;
    String cedula;
    String correo; 
    String pass;
    String rol;
    public Usuarios(String cedula, String nombre, String apellido, String correo,
            String pass, String rol){
        this.nombre=nombre;
        this.apellido=apellido;
        this.cedula=cedula;
        this.correo=correo;
        this.pass=pass;
        this.rol=rol;
    }

    public String getNombre() {
        return nombre;
    }
public String getCedula(){
    return cedula;
}
    public String getApellido() {
        return apellido;
    }


    public String getCorreo() {
        return correo;
    }

    public String getPass() {
        return pass;
    }
    public String getRol(){
        return rol;
    }
     public boolean existe() {
        File archivo = new File("usuarios/" + cedula + ".json");
        return archivo.exists();
    }

    // Guardar usuario en JSON
    /*public void guardar() throws IOException {
        File carpeta = new File("usuarios");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        File archivo = new File(carpeta, cedula + ".json");

        FileWriter fw = new FileWriter(archivo);
        fw.write(
            "{\n" +
            "  \"cedula\": \"" + cedula + "\",\n" +
            "  \"nombre\": \"" + nombre + "\",\n" +
            "  \"correo\": \"" + correo + "\",\n" +
            "  \"password\": \"" + pass + "\"\n" +
            "}"
        );
        fw.close();
    }*/

            
            
}
