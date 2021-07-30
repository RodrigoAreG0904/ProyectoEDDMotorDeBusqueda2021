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

    this.setBounds(anchura/4, altura/4, anchura/2, altura/2);
    miLamina = new JPanel();
    //miLamina.setLayout(new GridLayout(2,1,5,10));

    this.setTitle("Resultados de la consulta (nombres de los documentos)");

    //prueba(miLamina);

    this.add(miLamina);
    this.setVisible(true);
  }

  public void ocultar(){
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
  }

  public void resultados(LinkedList<Documento> listaDocumentos, JPanel miLamina){
    String resultadosGenerados = "";

    for(int i = 0; i<listaDocumentos.size(); i++){
      Documento doc = listaDocumentos.get(i);
      double similitud = doc.getSimilitud();
      if(similitud > 0){
        //solo tiene que presentarse el nombre, la similitud es para ver que si lo haga bien
        resultadosGenerados = resultadosGenerados + doc.getNombre() +"\n\n";
      }
    }
    resultadosGenerados = resultadosGenerados.replaceAll(".txt", "");
    if(resultadosGenerados.equals("")){
      resultadosGenerados = "Ningun archivo coincide con tu consulta";
    }
    JTextArea multi = new JTextArea(resultadosGenerados,20,60);
    multi.setWrapStyleWord(true);
    multi.setLineWrap(true);
    multi.setEditable(false);

    miLamina.add(multi);

  }

  public JPanel getPanel(){
    return this.miLamina;
  }
}
