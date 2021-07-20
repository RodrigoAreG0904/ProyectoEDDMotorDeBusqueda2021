package fciencias.edatos.proyecto.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MarcoHistorial extends JFrame{

  public MarcoHistorial(){
    //setBounds(500,200,800,400);
    // este Toolkit permite saber las dimensiones de cualquier
    //pantalla en la que corra el programa y así la ventana sea proporcional a la pantalla
    Toolkit miPantalla = Toolkit.getDefaultToolkit();

    Dimension tamanioPantalla = miPantalla.getScreenSize();

    int altura = tamanioPantalla.height;
    int anchura = tamanioPantalla.width;

    this.setBounds(anchura/4, altura/4, anchura/4, altura/2);
    JPanel miLamina = new JPanel();

    JLabel texto1 = new JLabel("Historial de busqueda (solo consultas)");
    miLamina.add(texto1);

    this.add(miLamina);
    this.setVisible(true);
  }

  public void ocultar(){
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
  }
}
