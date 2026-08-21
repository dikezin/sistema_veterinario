package com.mycompany.sistema_veterinario.front;

import javax.swing.*;

class VentasView extends JFrame {

    public VentasView() {

        setTitle("Registro de ventas");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }
    public static void main(String[] args) {
        new VentasView().setVisible(true);

    }


}

