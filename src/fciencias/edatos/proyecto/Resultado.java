package fciencias.edatos.proyecto;

import fciencias.edatos.proyecto.Documento;
import java.util.LinkedList;

public class Resultado{

  /** La consulta que se realizo. */
  private String consulta;
  /** Lista de documentos ya ordenada con la similitud de la consulta realizada. */
  private LinkedList<Documento> docsConSimilitud =  new LinkedList<>();

  /**
   * Constructor del resultado de la consulta que se realizo.
   * @param nuevaConsulta. la consulta  que se realizo.
   * @param nuevaLista. la lista de documentos ya ordenada.
  */
  public Resultado(String nuevaConsulta, LinkedList<Documento> nuevaLista){
    this.consulta = nuevaConsulta;
    this.docsConSimilitud = nuevaLista;
  }

  /**
   * Metodo que regresa la consulta hecha.
   * @return consulta. La consulta hecha.
   */
  public String getConsulta(){
    return this.consulta;
  }

  /**
   * Metodo que actualiza la consulta que se hizo.
   * @param nuevaConsulta. La consulta a cambiar.
   */
  public void setConsulta(String nuevaConsulta){
    this.consulta = nuevaConsulta;
  }

  /**
   * Metodo que regresa la lista de documentos que fueron el resultado de una consulta.
   * @return docsConSimilitud. La lista de documentos.
   */
  public LinkedList<Documento> getListaDoc(){
    return this.docsConSimilitud;
  }

  /**
   * Metodo que actualiza la lista de documentos que fueron el resultado de una consulta.
   * @param nuevaLista. La lista de documentos a cambiar.
   */
  public void setListaDoc(LinkedList<Documento> nuevaLista){
    this.docsConSimilitud = nuevaLista;
  }
}
