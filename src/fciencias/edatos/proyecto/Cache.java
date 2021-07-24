package fciencias.edatos.proyecto;

import fciencias.edatos.proyecto.Resultado;
import java.util.LinkedList;

public class Cache{
  //puede ser cualquier otra estructura
  private LinkedList<Resultado> resultados =  new LinkedList<>();

  public Cache(LinkedList<Resultado> nuevaLista){
    this.resultados = nuevaLista;
  }

  //hacer un toString para que queden los nombres de los 10
  //documentos (maximo) y si no hay resultados regresar que no se han encontrado resultados
}
