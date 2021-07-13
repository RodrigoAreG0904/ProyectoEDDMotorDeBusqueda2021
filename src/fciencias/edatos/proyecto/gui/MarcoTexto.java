package fciencias.edatos.proyecto.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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

    DameTexto miEvento = new DameTexto();

    boton1.addActionListener(miEvento);
    add(boton1);
  }
}

class DameTexto implements ActionListener{
  @Override
  public void actionPerformed(ActionEvent e){
    System.out.println("aqui pondriamos las operaciones");
  }
}
