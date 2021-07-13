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
import java.util.LinkedList;
import fciencias.edatos.proyecto.Documento;

public class LectorDeTextos{

  private LinkedList<Documento> listaDocumento = new LinkedList<>();

  public void cargaArchivos(File folder) throws IOException{
    String path = folder.getAbsolutePath();
    for(File file : folder.listFiles()){
      if(!file.isDirectory()){
        Documento nuevo = new Documento(file.getName(),"");
        carga(path + "\\"+file.getName(), nuevo);
        listaDocumento.add(0,nuevo);
      } else {
        cargaArchivos(file);
      }
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

}
