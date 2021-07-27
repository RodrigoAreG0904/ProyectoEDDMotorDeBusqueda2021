package fciencias.edatos.proyecto;

public class Documento{

  /** El nombre del documento. */
  private String nombre;
  /** El texto que contienen el documento. */
  private String cadena;
  /** La similitud que tiene el documento con la consulta que se haga. */
  private double similitud;

  /** 
   * Constructor de documento que iniciamos con similtud 0 ya que no se han hecho consultas. 
   * @param nuevoNombre. el nombre que le pondremos al documento.
   * @param nuevaCadena. el texto que le asignaremos al documento cuando lo carguemos.
   */
  public Documento(String nuevoNombre, String nuevaCadena){
    this.nombre = nuevoNombre;
    this.cadena = nuevaCadena;
    this.similitud = 0.0;
  }
  
  /** 
   * Metodo que regresa el nombre del documento.
   * @return nombre. El nombre del documento.
   */
  public String getNombre(){
    return this.nombre;
  }

  /** 
   * Metodo que cambia el nombre del documento.
   * @param nuevoNombre. el nuevo nombre que se le pondra al documento.
   */
  public void setNombre(String nuevoNombre){
    this.nombre = nuevoNombre;
  }

  /** 
   * Metodo que regresa el texto del documento.
   * @return cadena. El texto que contiene el documento.
   */
  public String getCadena(){
    return this.cadena;
  }

  /** 
   * Metodo que cambia el texto del documento.
   * @param nuevoNombre. El nuevo texto que se le pondra al documento.
   */
  public void setCadena(String nuevaCadena){
    this.cadena = nuevaCadena;
  }

  /** 
   * Metodo que regresa la similitud del documento.
   * @return similitud. La similitud del documento.
   */
  public double getSimilitud(){
    return this.similitud;
  }

  /** 
   * Metodo que cambia la similitud del documento.
   * @param nuevoNombre. La nueva similitud que se le pondra al documento.
   */
  public void setSimilitud(double nuevaSimilitud){
    this.similitud = nuevaSimilitud;
  }

  /** 
   * Metodo que concatena una cadena con la cadena de este documento.
   * @param aConcatenar. Cadena que agregaremos a la cadena de este documento.
   */
  public void concatenaCadena(String aConcatenar){
    this.cadena = cadena + aConcatenar + " ";
  }
}
