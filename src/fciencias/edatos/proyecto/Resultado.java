package fciencias.edatos.proyecto;

import fciencias.edatos.proyecto.Documento;
import java.util.LinkedList;

public class Resultado{
  //consulta que se resalizo
  private String consulta;
  //puede ser cualquier otra estructura
  private LinkedList<Documento> docsConSimilitud =  new LinkedList<>();

  public Resultado(String nuevaConsulta, LinkedList<Documento> nuevaLista){
    this.consulta = nuevaConsulta;
    this.docsConSimilitud = nuevaLista;
  }

  //poner getters y setters
}
