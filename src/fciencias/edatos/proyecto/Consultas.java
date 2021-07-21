package fciencias.edatos.proyecto;

import fciencias.edatos.proyecto.Documento;
import fciencias.edatos.proyecto.PalabraContada;
import java.util.LinkedList;
import java.text.Normalizer;

public class Consultas{

  public Consultas(){}

  public void hacerConsulta(String consulta, LinkedList<Documento> lista, LinkedList<PalabraContada> listaPalabra){

    String[] palabrasEnConsulta = limpiaCadena(consulta);

    int tamanio = lista.size(); //numero total de documentos
    for(int i = 0; i<tamanio; i++){
      Documento doc = lista.get(i);
      String cadena = doc.getCadena();

      String[] palabrasEnDocumento = limpiaCadena(cadena);

      int[] palabrasContadas = new int[palabrasEnConsulta.length];

      cuentaPalabras(palabrasEnConsulta,palabrasEnDocumento,palabrasContadas);
      /**
      int contador = 0;
      for(int j = 0; j<palabrasEnConsulta.length; j++){
        for(int k = 0; k<palabrasEnDocumento.length; k++){
          if(palabrasEnConsulta[j].equalsIgnoreCase(palabrasEnDocumento[k])){
            contador++;
          }
        }
        palabrasContadas[j] = contador; //se cuenta cuantas veces aparecio una palabra consultada
        contador = 0;
      }**/
      double enConsulta=0;
      for(int j = 0; j<palabrasEnConsulta.length; j++){
        String palabra = palabrasEnConsulta[j];
        PalabraContada palabraBuscada = getPalabra(palabra, listaPalabra);
        int numeroDoc = palabraBuscada.getNumeroDoc();

        enConsulta = enConsulta + similitud(palabrasContadas[j], numeroDoc, tamanio);
      }
      //En esta parte del codigo hay un error y se traba mi compu, pero no se cual error es.
      /**
      LinkedList<String> sinRepetir = new LinkedList<>();
      //si hay palabras repetidas no las tomamos en cuenta, solo queremos saber
      //en cuantos documentos se encuentra la palabra, no cuantas veces aparece
      for(int k=0; k<palabrasEnDocumento.length; i++){
        String palabra = palabrasEnDocumento[k];
        if(!sinRepetir.contains(palabra)){
          sinRepetir.add(palabra);
        }
      }**/


      /**
      String docSinPalabrasRepetidas = "";
      for(String s: sinRepetir){
        docSinPalabrasRepetidas = docSinPalabrasRepetidas + s;
      }
      String[] palabrasSinConsultar = docSinPalabrasRepetidas.split("\\s+");
      int[] palabrasSinConsultarContadas = new int[sinRepetir.size()];

      cuentaPalabras(palabrasSinConsultar,palabrasEnDocumento,palabrasSinConsultarContadas);

      double sinConsulta=0;
      for(int l = 0; l<palabrasSinConsultar.length; l++){
        String palabra = palabrasSinConsultar[l];
        PalabraContada palabraBuscada = getPalabra(palabra, listaPalabra);
        int numeroDocum = palabraBuscada.getNumeroDoc();

        sinConsulta = sinConsulta + similitud(palabrasSinConsultarContadas[l], numeroDocum, tamanio);
      }
**/

      System.out.println(doc.getNombre() + "\nSimilitud de palabras consultadas: " + enConsulta + "\nSimilitud de palabras sin consultar: " + 0);//sinConsulta);
      //imprimir(palabrasEnConsulta, palabrasEnDocumento, palabrasContadas);
    }
  }

  public void cuentaPalabras(String[] palabrasAContar, String[] palabrasDondeContar, int[] palabrasContadas){
    int contador = 0;
    for(int j = 0; j<palabrasAContar.length; j++){
      for(int k = 0; k<palabrasDondeContar.length; k++){
        if(palabrasAContar[j].equalsIgnoreCase(palabrasDondeContar[k])){
          contador++;
        }
      }
      palabrasContadas[j] = contador; //se cuenta cuantas veces aparecio una palabra consultada
      contador = 0;
    }
  }

  // TF = log 2 f_(td) + 1 = (Math.log(ftd) / Math.log(2)) + 1, si ftd == 0 entonces 0  formula para calcular la relevancia de t en el documento
  // IDF = log 2 ((N + 1)/N_t) = (Math.log((N + 1)/N_t) / Math.log(2)) formula para calcular la relevancia del termino en el conjunto total de documentos.
  //funcion (aun no decido como ponerle de nombre, todavia no es la formula de similitud completa)
  /****/

  /* Nota para recordar, tenemos que hacer esta operacion con cada palabra que se encuentre en la consulta y por cada palabra que se encuentre en el documento*/
  public double similitud(double numeroOcurrencias, double numeroDocumentos, double numeroDocumentosTotal){
    double tf, idf;
    if(numeroOcurrencias > 0){
      tf = (Math.log(numeroOcurrencias) / Math.log(2.0)) + 1; //usamos el logaritmo en base 2
    }else{
      tf = 0;
    }
    if(numeroDocumentos > 0 && numeroDocumentosTotal > 0){
      idf = Math.log((numeroDocumentosTotal + 1)/numeroDocumentos) / Math.log(2.0);
    }else{
      idf = 0;
    }
    return tf * idf;
  }

  public String[] limpiaCadena(String cadena){
    //borramos caracteres ¿ ¡ ? ! , . ; :
    cadena = cadena.replaceAll("[¿¡?!,.;:]*", "");

    //separamos los caracteres compuestos (á = a´)
    cadena = Normalizer.normalize(cadena, Normalizer.Form.NFD);

    //borramos todos los signos diacríticos separados anteriormente
    cadena = cadena.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

    //guardamos cada palabra en arreglos para despues compararlas
    return cadena.split("\\s+");
  }

  /**
   *
   */
  public PalabraContada getPalabra(String nombreBuscado, LinkedList<PalabraContada> listaPalabra){
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

  public void imprimir(String[] consulta, String[] documento, int[] palabrasContadas){
    /** imprime todo el documento en una cadena
    String completa = "";
    for(String e : documento){
      completa = completa + e + " ";
    }
    System.out.print(completa + "\n");
    **/

    for(int i = 0; i<consulta.length; i++){
      System.out.println(consulta[i] + ": " +palabrasContadas[i]);
    }

  }
}
