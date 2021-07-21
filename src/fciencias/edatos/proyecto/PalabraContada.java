package fciencias.edatos.proyecto;

public class PalabraContada{

  //el nombre de la palabra
  private String nombre;
  //numero de veces que aparece en un documento
  private int numeroOcu;
  //numero de documentos en donde aparece
  private int numeroDoc;

  public PalabraContada(String nuevoNombre, int nuevoNumOcu, int nuevoNumDoc){
    this.nombre = nuevoNombre;
    this.numeroOcu = nuevoNumOcu;
    this.numeroDoc = nuevoNumDoc;
  }

  public String getNombre(){
    return this.nombre;
  }

  public void setNombre(String nuevoNombre){
    this.nombre = nuevoNombre;
  }

  public int getNumeroOcu(){
    return this.numeroOcu;
  }

  public void setNumeroOcu(int nuevoNumOcu){
    this.numeroOcu = nuevoNumOcu;
  }

  public int getNumeroDoc(){
    return this.numeroDoc;
  }

  public void setNumeroDoc(int nuevoNumDoc){
    this.numeroDoc = nuevoNumDoc;
  }

}
