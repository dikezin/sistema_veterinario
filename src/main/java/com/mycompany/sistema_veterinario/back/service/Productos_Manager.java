/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_veterinario.back.service;
import com.mycompany.sistema_veterinario.back.model.Productos;

/**
 *
 * @author dikeg
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class Productos_Manager {
  
    private static final String ARCHIVO = "archivos/productos.txt";

    // GUARDAR PRODUCTO
    public static void guardarProducto(Productos p) {

        try {
            FileWriter fw = new FileWriter(ARCHIVO, true);
            fw.write(
                p.getId() + ";" +
                p.getNombre() + ";" +
                p.getCategoria() + ";" +
                p.getStock() + ";" +
                p.getPrecio() + "\n"
            );
            fw.close();
        } catch (Exception e) {
            System.out.println("Error al guardar producto");
        }
    }

    // LEER TODOS LOS PRODUCTOS
    public static ArrayList<Productos> listarProductos() {

        ArrayList<Productos> lista = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(ARCHIVO));
            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                Productos p = new Productos(
                    datos[0],                 // id
                    datos[1],                 // nombre
                    datos[2],                 // categoria
                    Integer.parseInt(datos[3]), // stock
                    Double.parseDouble(datos[4]) // precio
                );

                lista.add(p);
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Error leyendo productos");
        }

        return lista;
    }
    public static void eliminarProducto(String idProducto) {

    ArrayList<Productos> lista = listarProductos();

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {

        for (Productos p : lista) {
            if (!p.getId().equals(idProducto)) {
                bw.write(
                        p.getId() + ";" +
                                p.getNombre() + ";" +
                                p.getCategoria() + ";" +
                                p.getStock() + ";" +
                                p.getPrecio()
                );
                bw.newLine();
            } else {
            }
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
}
      public static void guardarLista(ArrayList<Productos> lista) {

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {

        for (Productos p : lista) {
            bw.write(
                p.getId() + ";" +
                p.getNombre() + ";" +
                p.getCategoria() + ";" +
                p.getStock() + ";" +
                p.getPrecio()
            );
            bw.newLine();
        }

    } catch (Exception e) {
        System.out.println("Error al guardar productos");
    }
}

    public static boolean comprarProducto(String idProducto) {
    ArrayList<Productos> lista = listarProductos();

    for (Productos p : lista) {
        if (p.getId().equals(idProducto)) {
            if (p.getStock() > 0) {
                p.setStock(p.getStock() - 1);
                guardarLista(lista);
                return true;
            }
            return false;
        }
    }
    return false;
}

}

  



   


