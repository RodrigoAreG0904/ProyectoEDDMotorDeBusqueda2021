package fciencias.edatos.proyecto;

public class Documento{
  private String nombre;
  private String cadena;

  public Documento(String nuevoNombre, String nuevaCadena){
    this.nombre = nuevoNombre;
    this.cadena = nuevaCadena;
  }

  public String getNombre(){
    return this.nombre;
  }

  public void setNombre(String nuevoNombre){
    this.nombre = nuevoNombre;
  }

  public String getCadena(){
    return this.cadena;
  }

  public void setCadena(String nuevaCadena){
    this.cadena = nuevaCadena;
  }

  public void concatenaCadena(String agregar){
    this.cadena = cadena + " " + agregar;
  }
}
