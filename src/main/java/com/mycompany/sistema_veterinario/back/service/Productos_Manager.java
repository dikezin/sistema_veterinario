/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_veterinario.back.service;
import com.mycompany.sistema_veterinario.back.respository.ProductosRepository;
import com.mycompany.sistema_veterinario.back.model.Productos;

/**
 *
 * @author dikeg
 */

public class Productos_Manager {
  
    private static final String ARCHIVO = "archivos/productos.txt";

    // GUARDAR PRODUCTO
    public static boolean guardarProducto(Productos p) throws Exception{
    ProductosRepository pr = new ProductosRepository();
    return pr.guardarProductos(p);

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

  



   


