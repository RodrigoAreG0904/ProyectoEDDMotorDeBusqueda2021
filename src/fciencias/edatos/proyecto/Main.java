package fciencias.edatos.proyecto;

import fciencias.edatos.proyecto.gui.MarcoTexto;
import fciencias.edatos.proyecto.gui.MarcoRuta;
import java.io.IOException;
import fciencias.edatos.proyecto.LectorDeTextos;

public class Main /*extends JButton */{

  public static void main(String[] args) throws IOException{

    MarcoTexto miMarco = new MarcoTexto();
    miMarco.cerrar();

    MarcoRuta marco = new MarcoRuta();
    marco.ocultar();
    LectorDeTextos lector = marco.getLector();

    miMarco.setLector(lector);
  }
}
