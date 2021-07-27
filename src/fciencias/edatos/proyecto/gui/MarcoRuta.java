package fciencias.edatos.proyecto.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import fciencias.edatos.proyecto.LectorDeTextos;
import java.io.IOException;
import java.util.LinkedList;
import fciencias.edatos.proyecto.Documento;

public class MarcoRuta extends JFrame{

  private LectorDeTextos ldt = new LectorDeTextos();

  
  public MarcoRuta(){
    // este Toolkit permite saber las dimensiones de cualquier
    //pantalla en la que corra el programa y así la ventana sea proporcional a la pantalla
    Toolkit miPantalla = Toolkit.getDefaultToolkit();

    Dimension tamanioPantalla = miPantalla.getScreenSize();

    int altura = tamanioPantalla.height;
    int anchura = tamanioPantalla.width;

    this.setBounds(anchura/4, altura/4, anchura/2, altura/2);
    //LaminaTexto2 miLamina = new LaminaTexto2();
    //add(miLamina);
    //setVisible(true);

    JPanel laminaTexto2 = new JPanel();

    //creamos una etiqueta para el espacio donde van a escribir
    JLabel texto2 = new JLabel("Especificar ruta de carpeta donde quieras buscar: ");
    laminaTexto2.add(texto2);

    // un campo de texto donde van a poner la ruta
    JTextField campo2 = new JTextField(80);
    laminaTexto2.add(campo2);

    //el boton que al apretarlo dara la ruta de la carpeta
    JButton boton2 = new JButton("Cargar archivos");

    boton2.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        LinkedList<Documento> lista = ldt.getListaDocumento();
        lista.clear(); //limpiamos la lista para cargar solo una vez los archivos de una carpeta
        String ruta = campo2.getText();
        if(ruta.equals("") || ruta == null){
          MarcoError error = new MarcoError("Debes de escribir una ruta valida");
          error.ocultar();
        }else{
          File folder = new File(ruta);
          try{
            laminaTexto2.add(ldt.barra);
            ldt.cargaArchivos(folder);
            ldt.cargaPalabras();
          } catch(IOException ioe){
            System.out.println("Ha habido un error.");
            MarcoError error = new MarcoError("Ha habido un problema de entrada y salida de archivos");
            error.ocultar();
          }
          MarcoExito exito = new MarcoExito("Los archivos se han cargado con exito");
          exito.ocultar();
        }
      }
    });
    laminaTexto2.add(boton2);

    //hagamos un setLector()

    this.add(laminaTexto2);
    this.setVisible(true);
  }

  public void ocultar(){
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
  }

  public LectorDeTextos getLector(){
    return this.ldt;
  }
}
