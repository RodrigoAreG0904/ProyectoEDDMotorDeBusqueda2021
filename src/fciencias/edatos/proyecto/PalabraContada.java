package fciencias.edatos.proyecto;

public class PalabraContada{

  /** El nombre de la palabra*/
  private String nombre;
  /** El nombre del documento. */
  private int numeroOcu;
  /** Numero de documentos en donde aparece la palabra. */
  private int numeroDoc;

  /**
   * Constructor de PalabraContada .
   * @param nuevoNombre. el nombre de la palabra.
   * @param nuevoNumOcu. el nombre del documento donde se encuentra.
   * @param nuevoNumDoc. el numero de documentos donde aparece la palabra.
   */
  public PalabraContada(String nuevoNombre, int nuevoNumOcu, int nuevoNumDoc){
    this.nombre = nuevoNombre;
    this.numeroOcu = nuevoNumOcu;
    this.numeroDoc = nuevoNumDoc;
  }

  /**
   * Metodo que regresa el nombre de la palabra.
   * @return nombre. El nombre de la palabra.
   */
  public String getNombre(){
    return this.nombre;
  }

  /**
   * Metodo que actualiza el nombre de la palabra.
   * @param nuevoNombre. el nuevo nombre de la palabra.
   */
  public void setNombre(String nuevoNombre){
    this.nombre = nuevoNombre;
  }

  /**
   * Metodo que regresa el nombre del documento.
   * @return nombre. El nombre del documento.
   */
  public int getNumeroOcu(){
    return this.numeroOcu;
  }

  /**
   * Metodo que actualiza el nombre del documento.
   * @param nuevoNumOcu. el nuevo nombre del documento.
   */
  public void setNumeroOcu(int nuevoNumOcu){
    this.numeroOcu = nuevoNumOcu;
  }

  /**
   * Metodo que regresa el numero de documentos en donde aparece la palabra.
   * @return nombre. El numero de documentos en donde aparece la palabra.
   */
  public int getNumeroDoc(){
    return this.numeroDoc;
  }

  /**
   * Metodo que actualiza el numero de documentos en donde aparece la palabra.
   * @param nuevoNumDoc. el nuevo numero de documentos en donde aparece la palabra.
   */
  public void setNumeroDoc(int nuevoNumDoc){
    this.numeroDoc = nuevoNumDoc;
  }
}
