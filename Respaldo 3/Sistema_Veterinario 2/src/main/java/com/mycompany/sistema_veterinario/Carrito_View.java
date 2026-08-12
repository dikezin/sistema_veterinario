/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sistema_veterinario;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dikeg
 */
public class Carrito_View extends javax.swing.JFrame {

  private Carrito_Manager carrito;
    private Tienda_View tienda;

    public Carrito_View(Carrito_Manager carrito, Tienda_View tienda) {
        this.carrito = carrito;
        this.tienda = tienda;
       initComponents(); // Este llama al método de abajo (el de NetBeans)
        cargarCarrito();
    }

    public void cargarCarrito() {
        DefaultTableModel modelo = (DefaultTableModel) TablaCarrito.getModel();
        modelo.setRowCount(0);

        for (Productos p : carrito.getProductos()) {
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNombre(),
                p.getPrecio()
            });
        }
    }
   
    // </editor-fold>d")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TablaCarrito = new javax.swing.JTable();
        btRegresar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TablaCarrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "PRECIO"
            }
        ));
        jScrollPane1.setViewportView(TablaCarrito);

        btRegresar.setText("REGRESAR");
        btRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegresarActionPerformed(evt);
            }
        });

        jButton1.setText("COMPRAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(btRegresar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btRegresar)
                    .addComponent(jButton1))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegresarActionPerformed
this.setVisible(false);
        tienda.setVisible(true);
// TODO add your handling code here:
    }//GEN-LAST:event_btRegresarActionPerformed
private void generarFacturaCarrito(double sub, double iva, double total) {
    // Usamos el tiempo actual para que el nombre del archivo sea único
    String nombreArchivo = "Factura_Carrito_" + System.currentTimeMillis() + ".txt";

    try (java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(nombreArchivo))) {
        writer.println("========================================");
        writer.println("           VeterinariaDesk             ");
        writer.println("       RUC: 1234567890001              ");
        writer.println("========================================");
        writer.println("Fecha: " + new java.util.Date());
        writer.println("----------------------------------------");
        writer.println(String.format("%-20s %10s", "PRODUCTO", "PRECIO"));
        
        for (Productos p : carrito.getProductos()) {
            writer.println(String.format("%-20s %10.2f", p.getNombre(), p.getPrecio()));
        }

        writer.println("----------------------------------------");
        writer.println("Subtotal:       $" + String.format("%.2f", sub));
        writer.println("IVA (15%):      $" + String.format("%.2f", iva));
        writer.println("TOTAL A PAGAR:  $" + String.format("%.2f", total));
        writer.println("----------------------------------------");
        writer.println("   ¡Gracias por su compra en!           ");
        writer.println("        VeterinariaDesk                ");
        writer.println("========================================");
        
    } catch (java.io.IOException e) {
        JOptionPane.showMessageDialog(this, "Error al crear archivo: " + e.getMessage());
    }
}
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if (carrito.getProductos().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El carrito está vacío");
            return;
        }

        double subtotal = 0;
        for (Productos p : carrito.getProductos()) {
            subtotal += p.getPrecio();
        }

        double iva = subtotal * 0.15; // IVA del 15%
        double total = subtotal + iva;

        // 1. Generar la factura en archivo TXT
        generarFacturaCarrito(subtotal, iva, total);

        // 2. AQUÍ VA LA LIMPIEZA:
        carrito.vaciarCarrito(); // <--- ESTO ES LO QUE FALTABA
        
        // 3. Actualizar la tabla para que se vea vacía
        cargarCarrito();

        JOptionPane.showMessageDialog(this, "Venta finalizada. Factura guardada exitosamente.");
       // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
   


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaCarrito;
    private javax.swing.JButton btRegresar;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
