package fciencias.edatos.proyecto.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;
import fciencias.edatos.proyecto.Documento;
import fciencias.edatos.proyecto.LectorDeTextos;
import fciencias.edatos.proyecto.Consultas;
import fciencias.edatos.proyecto.PalabraContada;

public class MarcoTexto extends JFrame{

  private String consulta;
  private LectorDeTextos lector = new LectorDeTextos();
  private LinkedList<String> listaHistorial = new LinkedList<>();

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

    //JLabel textoError = new JLabel("Cadena invalida, la consulta debe contener menos de 200 caracteres");

    // un campo de texto donde van a hacer su consulta
    JTextField campo1 = new JTextField(80);
    miLamina.add(campo1);

    //el boton que al apretarlo pedira los archivos mas parecidos, hasta ahora solo imprime algo en consola xd
    JButton boton1 = new JButton("Consultar");

    JButton boton2 = new JButton("Ver Historial");

    boton1.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        String consulta = campo1.getText();
        consulta = consulta.trim();
        if(consulta.length() <= 0 || consulta.length() > 200){
          MarcoError error = new MarcoError("Cadena invalida, la consulta debe contener entre 1 a 200 caracteres");
          error.ocultar();
        }else{
          listaHistorial.add(consulta);
          LinkedList<Documento> listaDocumento = lector.getListaDocumento();
          LinkedList<PalabraContada> listaPalabra = lector.getListaPalabras();
          if(listaDocumento.isEmpty() || listaPalabra.isEmpty()){
            MarcoError error = new MarcoError("Los documentos no se han cargado correctamente");
            error.ocultar();
          } else{
            Consultas a = new Consultas();
            try{
              listaDocumento = a.hacerConsulta(consulta, listaDocumento, listaPalabra);
              MarcoResultado res = new MarcoResultado();
              JPanel lamina = res.getPanel();
              res.resultados(listaDocumento,lamina);
              res.ocultar();
            } catch(NullPointerException npe){
              MarcoError error = new MarcoError("la palabra no ha sido encontrada");
              error.ocultar();
            }
          }
        }
        System.out.println();
        //aqui podemos poner la consulta en una lista de strings y ponerla en el historial
      }
    });

    boton2.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        MarcoHistorial historial = new MarcoHistorial();
        historial.ocultar();
        JPanel lamina = historial.getPanel();

        historial.prueba(listaHistorial,lamina);
      }
    });

    miLamina.add(boton1);
    miLamina.add(boton2);

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
