package fciencias.edatos.proyecto;

import fciencias.edatos.proyecto.Documento;
import java.util.LinkedList;

public class Resultado{
  //consulta que se realizo
  private String consulta;
  //puede ser cualquier otra estructura
  private LinkedList<Documento> docsConSimilitud =  new LinkedList<>();

  public Resultado(String nuevaConsulta, LinkedList<Documento> nuevaLista){
    this.consulta = nuevaConsulta;
    this.docsConSimilitud = nuevaLista;
  }

  //poner getters y setters
  public String getConsulta(){
    return this.consulta;
  }

  public void setConsulta(String nuevaConsulta){
    this.consulta = nuevaConsulta;
  }

  public LinkedList<Documento> getListaDoc(){
    return this.docsConSimilitud;
  }

  public void setListaDoc(LinkedList<Documento> nuevaLista){
    this.docsConSimilitud = nuevaLista;
  }
}
