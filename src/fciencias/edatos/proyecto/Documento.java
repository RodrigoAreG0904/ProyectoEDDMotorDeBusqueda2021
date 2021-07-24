package fciencias.edatos.proyecto;

public class Documento{

  private String nombre;
  private String cadena;
  private double similitud;

  public Documento(String nuevoNombre, String nuevaCadena){
    this.nombre = nuevoNombre;
    this.cadena = nuevaCadena;
    this.similitud = 0.0;
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

  public double getSimilitud(){
    return this.similitud;
  }

  public void setSimilitud(double nuevaSimilitud){
    this.similitud = nuevaSimilitud;
  }

  public void concatenaCadena(String aConcatenar){
    this.cadena = cadena + aConcatenar + " ";
  }
}
