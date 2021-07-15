package fciencias.edatos.proyecto.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;
import fciencias.edatos.proyecto.Documento;
import fciencias.edatos.proyecto.LectorDeTextos;
import fciencias.edatos.proyecto.Consultas;

public class MarcoTexto extends JFrame{

  private String consulta;
  private LectorDeTextos lector = new LectorDeTextos();

  public MarcoTexto(){
    //setBounds(500,200,800,400);
    // este Toolkit permite saber las dimensiones de cualquier
    //pantalla en la que corra el programa y así la ventana sea proporcional a la pantalla
    Toolkit miPantalla = Toolkit.getDefaultToolkit();

    Dimension tamanioPantalla = miPantalla.getScreenSize();

    int altura = tamanioPantalla.height;
    int anchura = tamanioPantalla.width;

    this.setBounds(anchura/4, altura/4, anchura/2, altura/2);
    JPanel miLamina = new JPanel();

    JLabel texto1 = new JLabel("Consulta: ");
    miLamina.add(texto1);

    // un campo de texto donde van a hacer su consulta
    JTextField campo1 = new JTextField(80);
    miLamina.add(campo1);

    //el boton que al apretarlo pedira los archivos mas parecidos, hasta ahora solo imprime algo en consola xd
    JButton boton1 = new JButton("Consultar");

    //DameTexto miEvento = new DameTexto();

    //boton1.addActionListener(miEvento);

    boton1.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        String consulta = campo1.getText();
        LinkedList<Documento> lista = lector.getLista();
    
        Consultas a = new Consultas();
        for(int i = 0; i<lista.size(); i++){
          Documento documento = lista.get(i);
          String cadena = documento.getCadena();
          a.hacerConsulta(consulta, cadena);
        }
      }
    });

    miLamina.add(boton1);

    this.add(miLamina);
    this.setVisible(true);
  }

  public void cerrar(){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public String getConsulta(){
    return this.consulta;
  }

  public LectorDeTextos getLector(){
    return this.lector;
  }

  public void setLector(LectorDeTextos nuevoLector){
    this.lector = nuevoLector;
  }
}

  /**public void setConsulta(String nuevaConsulta){
    this.consulta = nuevaConsulta;
  }
}

/**
class LaminaTexto extends JPanel{
  public LaminaTexto(){
    //creamos una etiqueta para el espacio donde van a escribir
    JLabel texto1 = new JLabel("Consulta: ");
    add(texto1);

    // un campo de texto donde van a hacer su consulta
    JTextField campo1 = new JTextField(80);
    add(campo1);

    //el boton que al apretarlo pedira los archivos mas parecidos, hasta ahora solo imprime algo en consola xd
    JButton boton1 = new JButton("Consutar");

    //DameTexto miEvento = new DameTexto();

    //boton1.addActionListener(miEvento);

    boton1.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        String consulta = campo1.getText();
        System.out.println(consulta);
      }
    });

    add(boton1);
  }
}

/**
class DameTexto implements ActionListener{
  @Override
  public void actionPerformed(ActionEvent e){
    String consulta = campo1.getText();
    return consulta;
  }
}**/
