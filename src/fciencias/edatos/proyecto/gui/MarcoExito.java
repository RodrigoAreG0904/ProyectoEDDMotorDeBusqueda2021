package fciencias.edatos.proyecto.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MarcoExito extends JFrame{

  public MarcoExito(String mensaje){
    this.setTitle("Exito");
    Toolkit miPantalla = Toolkit.getDefaultToolkit();

    Dimension tamanioPantalla = miPantalla.getScreenSize();

    int altura = tamanioPantalla.height;
    int anchura = tamanioPantalla.width;

    this.setBounds(anchura/4, altura/2, anchura/2, altura/4);
    JPanel miLamina = new JPanel();

    JLabel texto1 = new JLabel(mensaje);
    miLamina.add(texto1);

    this.add(miLamina);
    this.setVisible(true);
  }

  public void ocultar(){
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
  }
}
