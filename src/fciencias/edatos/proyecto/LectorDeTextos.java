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

public class LectorDeTextos{

  public void imprimeArchivos(File folder) throws IOException{
    String path = folder.getAbsolutePath();
    for(File file : folder.listFiles()){
      if(!file.isDirectory()){
        carga(path + "\\"+file.getName());
      } else {
        imprimeArchivos(file);
      }
    }
  }

  public void carga(String archivo) throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(archivo)));
    String linea = in.readLine();
    while(linea != null){
      System.out.println(linea);
      linea = in.readLine();
    }
    in.close();
  }

}
