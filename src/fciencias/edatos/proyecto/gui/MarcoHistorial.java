package fciencias.edatos.proyecto.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public class MarcoHistorial extends JFrame{

  private JPanel miLamina;

  public MarcoHistorial(){

    Toolkit miPantalla = Toolkit.getDefaultToolkit();

    Dimension tamanioPantalla = miPantalla.getScreenSize();

    int altura = tamanioPantalla.height;
    int anchura = tamanioPantalla.width;

    this.setBounds(anchura/4, altura/4, anchura/2, altura/2);
    miLamina = new JPanel();

    this.setTitle("Historial de busqueda (solo consultas)");

    this.add(miLamina);
    this.setVisible(true);
  }

  public void ocultar(){
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
  }

  public void prueba(LinkedList<String> listaHistorial, JPanel miLamina){
    String palabrasConsultadas = "";

    for(int i = 0; i<listaHistorial.size(); i++){
      String consulta = listaHistorial.get(i);
      palabrasConsultadas = consulta + "\n" + palabrasConsultadas;
    }

    JTextArea multi = new JTextArea(palabrasConsultadas,20,60);
    multi.setWrapStyleWord(true);
    multi.setLineWrap(true);
    multi.setEditable(false);

    miLamina.add(multi);
  }

  public JPanel getPanel(){
    return this.miLamina;
  }
}
