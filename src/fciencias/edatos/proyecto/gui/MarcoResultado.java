package fciencias.edatos.proyecto.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;
import fciencias.edatos.proyecto.Documento;

public class MarcoResultado extends JFrame{

  private JPanel miLamina;

  public MarcoResultado(){
    //setBounds(500,200,800,400);
    // este Toolkit permite saber las dimensiones de cualquier
    //pantalla en la que corra el programa y así la ventana sea proporcional a la pantalla
    Toolkit miPantalla = Toolkit.getDefaultToolkit();

    Dimension tamanioPantalla = miPantalla.getScreenSize();

    int altura = tamanioPantalla.height;
    int anchura = tamanioPantalla.width;

    this.setBounds(anchura/4, altura/4, anchura/4, altura/2);
    miLamina = new JPanel();
    //miLamina.setLayout(new GridLayout(2,1,5,10));

    this.setTitle("Resultados de la consulta");

    //prueba(miLamina);

    this.add(miLamina);
    this.setVisible(true);
  }

  public void ocultar(){
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
  }

  public void resultados(LinkedList<Documento> listaDocumentos, JPanel miLamina){
    //falta ordenar la lista
    //y limitarla a 10 Documentos

    String resultadosGenerados="";
    int limite = 10;
    if(listaDocumentos.size()<10){
      limite = listaDocumentos.size();
    }
    //lo recorremos al reves por que el merge ordena de menor a mayor
    for(int i = 0; i<limite; i++){
      Documento doc = listaDocumentos.get(i);
      resultadosGenerados = resultadosGenerados + "Nombre:" + doc.getNombre() + " Similitud:"+ doc.getSimilitud() + "\n\n";
    }
    resultadosGenerados = resultadosGenerados.replaceAll(".txt", "");

    JTextArea multi = new JTextArea(resultadosGenerados,10,30);
    multi.setWrapStyleWord(true);
    multi.setLineWrap(true);
    multi.setEditable(false);

    miLamina.add(multi);
  }

  public JPanel getPanel(){
    return this.miLamina;
  }
}
