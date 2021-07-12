package fciencias.edatos.proyecto.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import fciencias.edatos.proyecto.gui.LaminaTexto;

public class MarcoTexto extends JFrame{
  public MarcoTexto(){
    //setBounds(500,200,800,400);
    // este Toolkit permite saber las dimensiones de cualquier
    //pantalla en la que corra el programa y así la ventana sea proporcional a la pantalla
    Toolkit miPantalla = Toolkit.getDefaultToolkit();

    Dimension tamanioPantalla = miPantalla.getScreenSize();

    int altura = tamanioPantalla.height;
    int anchura = tamanioPantalla.width;

    setBounds(anchura/4, altura/4, anchura/2, altura/2);
    LaminaTexto miLamina = new LaminaTexto();
    add(miLamina);
    setVisible(true);
  }


  public void cerrar(){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
