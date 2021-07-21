package fciencias.edatos.proyecto;

import java.text.ParseException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.lang.NullPointerException;
import java.util.LinkedList;
import fciencias.edatos.proyecto.Documento;
import fciencias.edatos.proyecto.PalabraContada;
import javax.swing.*;

public class LectorDeTextos{

  private LinkedList<PalabraContada> listaPalabra = new LinkedList<>();
  private LinkedList<Documento> listaDocumento = new LinkedList<>();
  public JProgressBar barra = new JProgressBar();

  public void cargaArchivos(File folder) throws IOException{
    String path = folder.getAbsolutePath();
    int i= 0;
    barra.setStringPainted(true);

    try{
      for(File file : folder.listFiles()){
        if(!file.isDirectory()){
          Documento nuevo = new Documento(file.getName(),"");
          carga(path + "\\"+file.getName(), nuevo);
          listaDocumento.add(0,nuevo);
          i++;
          barra.setString(i + " archivo(s) completado(s)");
        } else {
          cargaArchivos(file);
        }
      }
    } catch(NullPointerException npe){
      System.out.println("La ruta otorgada tiene problemas");
    }
  }

  public void cargaPalabras(){
    for(Documento doc : listaDocumento){
      String cadena = doc.getCadena();
      cadena = cadena.replaceAll("[¿¡?!,.;:]*", "");
      cadena = Normalizer.normalize(cadena, Normalizer.Form.NFD);
      cadena = cadena.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
      String[] palabrasEnDocumento = cadena.split("\\s+");
      LinkedList<String> sinRepetir = new LinkedList<>();
      //si hay palabras repetidas no las tomamos en cuenta, solo queremos saber
      //en cuantos documentos se encuentra la palabra, no cuantas veces aparece
      for(int i=0; i<palabrasEnDocumento.length; i++){
        String palabra = palabrasEnDocumento[i];
        if(!sinRepetir.contains(palabra)){
          sinRepetir.add(palabra);
        }
      }

      for(int j=0; j<sinRepetir.size(); j++){
        String palabra = sinRepetir.get(j);
        if(contiene(palabra)){
          try{
            PalabraContada palabraAModificar = getPalabra(palabra);
            int numeroDoc = palabraAModificar.getNumeroDoc();
            palabraAModificar.setNumeroDoc(numeroDoc + 1);
          }catch(NullPointerException npe){
            System.out.println("Hubo un error");
          }
        }else{
          PalabraContada palabraNueva = new PalabraContada(palabra,0,1);
          listaPalabra.add(0,palabraNueva);
        }
      }
    }
  }

  public boolean contiene(String nombre){
    //cuando está vacía.
    if(listaPalabra.isEmpty()){
      return false;
    }

    String nombreAComparar;
    //cuando tiene 1 elemento.
    if(listaPalabra.size() == 1){
      PalabraContada aComparar = listaPalabra.get(0);
      nombreAComparar = aComparar.getNombre();
      return nombre.equalsIgnoreCase(nombreAComparar);
    }

    //cuando hay más de 1 elemento.
    for(int i = 0; i<listaPalabra.size(); i++){
      PalabraContada aComparar = listaPalabra.get(i);
      nombreAComparar = aComparar.getNombre();
      if(nombre.equalsIgnoreCase(nombreAComparar)){
        return true;
      }
    }
    return false;
  }

  public PalabraContada getPalabra(String nombreBuscado){
    int j = listaPalabra.size();
    if(j==0){
      //Poner una ventana de error donde un doc es vacio
      System.out.println("Esta lista esta vacia, no contiene palabras");
      return null;
    }
    PalabraContada res;
    for(int i = 0; i<j; i++){
      res = listaPalabra.get(i);
      if(res.getNombre().equalsIgnoreCase(nombreBuscado)){
        return res;
      }
    }
    return null;
  }

  public void carga(String archivo, Documento doc) throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(archivo)));
    String linea = in.readLine();
    while(linea != null){
      doc.concatenaCadena(linea);
      linea = in.readLine();
    }
    in.close();
  }

  public void imprimeLista(){
    for(int i=0; i<listaDocumento.size(); i++){
      Documento aImprimir = listaDocumento.get(i);
      System.out.println("Nombre del Archivo: " + aImprimir.getNombre() + "\nContenido de Texto: " + aImprimir.getCadena() + "\n");

    }
  }

  public LinkedList<Documento> getListaDocumento(){
    return this.listaDocumento;
  }

  public void setListaDocumento(LinkedList<Documento> nuevaLista){
    this.listaDocumento = nuevaLista;
  }

  public LinkedList<PalabraContada> getListaPalabras(){
    return this.listaPalabra;
  }

  public void setListaPalabras(LinkedList<PalabraContada> nuevaLista){
    this.listaPalabra = nuevaLista;
  }

  public void imprimeNumDoc(){
    for(int i=0; i<listaPalabra.size(); i++){
      PalabraContada palabra = listaPalabra.get(i);
      System.out.println(palabra.getNombre() + " : " +  palabra.getNumeroDoc() +" veces");
    }
  }
}




/**
package fciencias.edatos.proyecto;

import java.text.ParseException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.lang.NullPointerException;
import java.util.LinkedList;
import fciencias.edatos.proyecto.Documento;
import javax.swing.*;

public class LectorDeTextos{

  private LinkedList<Documento> listaDocumento = new LinkedList<>();
  public JProgressBar barra = new JProgressBar();

  public void cargaArchivos(File folder) throws IOException{
    String path = folder.getAbsolutePath();
    int i= 0;
    barra.setStringPainted(true);

    try{
      for(File file : folder.listFiles()){
        if(!file.isDirectory()){
          Documento nuevo = new Documento(file.getName(),"");
          carga(path + "\\"+file.getName(), nuevo);
          listaDocumento.add(0,nuevo);
          i++;
          barra.setString(i + " archivo(s) completado(s)");
        } else {
          cargaArchivos(file);
        }
      }
    } catch(NullPointerException npe){
      System.out.println("La ruta otorgada tiene problemas");
    }
  }

  public void carga(String archivo, Documento doc) throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(archivo)));
    String linea = in.readLine();
    while(linea != null){
      doc.concatenaCadena(linea);
      linea = in.readLine();
    }
    in.close();
  }

  public void imprimeLista(){
    for(int i=0; i<listaDocumento.size(); i++){
      Documento aImprimir = listaDocumento.get(i);
      System.out.println("Nombre del Archivo: " + aImprimir.getNombre() + "\nContenido de Texto: " + aImprimir.getCadena() + "\n");

    }
  }

  public LinkedList<Documento> getLista(){
    return this.listaDocumento;
  }

  public void setLista(LinkedList<Documento> nuevaLista){
    this.listaDocumento = nuevaLista;
  }
}
*/
