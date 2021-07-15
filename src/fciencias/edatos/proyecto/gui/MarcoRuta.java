package fciencias.edatos.proyecto.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import fciencias.edatos.proyecto.gui.LaminaTexto2;
import fciencias.edatos.proyecto.LectorDeTextos;
import java.io.IOException;
import java.util.LinkedList;
import fciencias.edatos.proyecto.Documento;

public class MarcoRuta extends JFrame{

  private LectorDeTextos ldt = new LectorDeTextos();

  //Frame
  public MarcoRuta(){
    //setBounds(500,200,800,400);
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
    JButton boton2 = new JButton("Ruta");

    boton2.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        String ruta = campo2.getText();
        if(ruta.equals("")){
          laminaTexto2.add(texto2);
        }else{
          File folder = new File(ruta);
          try{
            laminaTexto2.add(ldt.barra);
            ldt.cargaArchivos(folder);
            //ldt.imprimeLista();
            //
            laminaTexto2.add(ldt.completado);
          } catch(IOException ioe){
            System.out.println("Ha habido un error.");
          }
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

/**
class LaminaTexto2 extends JPanel{

  private LectorDeTextos ldt = new LectorDeTextos();

  public LaminaTexto2(){
    //creamos una etiqueta para el espacio donde van a escribir
    JLabel texto2 = new JLabel("Especificar ruta de carpeta donde quieras buscar: ");
    add(texto2);

    // un campo de texto donde van a poner la ruta
    JTextField campo2 = new JTextField(80);
    add(campo2);

    //el boton que al apretarlo dara la ruta de la carpeta
    JButton boton2 = new JButton("Ruta");

    boton2.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        String ruta = campo2.getText();
        if(ruta.equals("")){
          add(texto2);
        }else{
          File folder = new File(ruta);
          try{
            add(ldt.barra);
            ldt.cargaArchivos(folder);
            //ldt.imprimeLista();
            //
            add(ldt.completado);
          } catch(IOException ioe){
            System.out.println("Ha habido un error.");
          }
        }
      }
    });
    add(boton2);

  }

  public LectorDeTextos getLector(){
    return this.ldt;
  }

  //hagamos un setLector()
}
/**
class AccionRuta implements ActionListener{
  @Override
  public void actionPerformed(ActionEvent e){
    String ruta = miLamina.campo2.getText();
    if(ruta == null || ruta ==""){
      System.out.println("El campo es invalido");
    }else{
      System.out.println(ruta);
    }

    LectorDeTextos ldt = new LectorDeTextos();
    //busca la carpeta textos
    File currentDirFile = new File(".");
    String path = currentDirFile.getAbsolutePath();
    String rightPath = path.replace(".", "Textos");
    File folder = new File(rightPath);
    /**try{
      ldt.imprimeArchivos(folder);
    } catch(IOException ioe){
      System.out.println("Ha habido un error.");
    }

  }
}*/
