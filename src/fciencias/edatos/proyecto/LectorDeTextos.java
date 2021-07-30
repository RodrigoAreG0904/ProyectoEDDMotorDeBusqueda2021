package fciencias.edatos.proyecto;

import fciencias.edatos.proyecto.Documento;
import fciencias.edatos.proyecto.PalabraContada;
import fciencias.edatos.proyecto.gui.MarcoError;
import fciencias.edatos.proyecto.gui.MarcoExito;
import java.lang.NullPointerException;
import java.util.LinkedList;
import java.text.ParseException;
import java.text.Normalizer;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class LectorDeTextos{

  /**
   * Lista de palabras, esta servira para contar en cuantos documentos se encuentra
   * cada palabra de todos los documentos una vez cargados. (como una base de datos para
   * no tener que calcularlo en cada consulta).
   */
  private LinkedList<PalabraContada> listaPalabra = new LinkedList<>();

  /** Lista que contiene a todos los documentos de una carpeta (elegida por el usuario). */
  private LinkedList<Documento> listaDocumento = new LinkedList<>();

  /**
   * Una barra de progreso que aparecera al cargar archivos y nos dira cuantos
   * archivos lleva cargados, una vez cargados todos aparecera una ventana que
   * le indique al usuario que ya se han acabado de cargar.
   */
  public JProgressBar barra = new JProgressBar();

  /**
   * Metodo que regresa la lista de documentos.
   * @return listaDocumento. La Lista de documentos.
   */
  public LinkedList<Documento> getListaDocumento(){
    return this.listaDocumento;
  }

  /**
   * Metodo que cambia la lista de documentos.
   * @param nuevaLista. La Lista de documentos.
   */
  public void setListaDocumento(LinkedList<Documento> nuevaLista){
    this.listaDocumento = nuevaLista;
  }

  /**
   * Metodo que regresa la lista de palabras.
   * @return listaPalabra. La Lista de palabras.
   */
  public LinkedList<PalabraContada> getListaPalabras(){
    return this.listaPalabra;
  }

  /**
   * Metodo que cambia la lista de palabras.
   * @param listaPalabra. La Lista de palabras.
   */
  public void setListaPalabras(LinkedList<PalabraContada> nuevaLista){
    this.listaPalabra = nuevaLista;
  }

  /**
   * Metodo que guarda todos los archivos de texo en una lista de Documento.
   * @param folder. La carpeta de donde se cargaran los archivos.
   */
  public void cargaArchivos(File folder) throws IOException{
    String path = folder.getAbsolutePath();
    int i= 0;
    barra.setStringPainted(true);

    String division = "";
    //Si no tomamos en cuenta esta cadena hay un error.
    if(path.contains("\\")){
      division = "\\"; // Esta ruta es para Windows
    }else{
      division ="/"; // Esta ruta es para Linux
    }

    try{
      for(File file : folder.listFiles()){
        if(!file.isDirectory()){
          Documento nuevo = new Documento(file.getName(),"");
          carga(path + division + file.getName(), nuevo);
          listaDocumento.add(0,nuevo);
          i++;
          barra.setString(i + " archivo(s) completado(s)");
        } else {
          cargaArchivos(file);
        }
      }
    } catch(NullPointerException npe){
      MarcoError error = new MarcoError("La ruta otorgada tiene problemas.");
      error.ocultar();
    }
  }

  /**
   * Metodo que guarda las palabras que aparecen en todos los documentos y el
   * numero de documentos en donde aparece la palabra.
   */
  public void cargaPalabras(){
    LinkedList<String> sinRepetir = new LinkedList<>();
    for(Documento doc : listaDocumento){
      String cadena = doc.getCadena();
      cadena = cadena.replaceAll("[¿¡?!,.;:]*", "");
      cadena = Normalizer.normalize(cadena, Normalizer.Form.NFD);
      cadena = cadena.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
      String[] palabrasEnDocumento = cadena.split("\\s+");

      sinRepetir = new LinkedList<>();
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
    if(sinRepetir.size()<=0){
      MarcoError error = new MarcoError("No se han cargado archivos, o el ultimo documento esta vacio.");
      error.ocultar();
    }else{
      MarcoExito exito = new MarcoExito("Los archivos se han cargado con exito");
      exito.ocultar();
    }
  }

  /**
   * Metodo que nos dice si en la lista de PalabraContada existe una palabra con el nombre que buscamos.
   * @param nombre. El nombre con el que buscaremos una palabra en la lista de PalabraContada.
   * @return true si la lista contiene una palabra con el nombre que nosotros buscamos, false en otro caso.
   */
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

  /**
   * Metodo que regresa una palabra que busca en la lista de PalabraContada.
   * @return palabra. la palabra que pedimos que busque en la lista de PalabraContada.
   */
  public PalabraContada getPalabra(String nombreBuscado){
    int j = listaPalabra.size();
    if(j==0){
      MarcoError error = new MarcoError("Esta lista esta vacia, no contiene palabras");
      error.ocultar();
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

  /**
   * Metodo que lee los documentos y guarda su contenido en una cadena concanenandolos.
   * @param archivo. El archivo que se va a leer y se convertira en Documento.
   * @param doc. El documento que vamos a usar para guardar todo el texto
   * del archivo que estamos leyndo.
   */
  public void carga(String archivo, Documento doc) throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(archivo)));
    String linea = in.readLine();
    while(linea != null){
      doc.concatenaCadena(linea);
      linea = in.readLine();
    }
    in.close();
  }

}
