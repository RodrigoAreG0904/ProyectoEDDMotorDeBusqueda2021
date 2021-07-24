package fciencias.edatos.proyecto;

import fciencias.edatos.proyecto.Documento;
import fciencias.edatos.proyecto.PalabraContada;
import java.util.LinkedList;
import java.text.Normalizer;

public class Consultas{

  public Consultas(){}

  public LinkedList<Documento> hacerConsulta(String consulta, LinkedList<Documento> lista, LinkedList<PalabraContada> listaPalabra){

    String[] palabrasEnConsulta = limpiaCadena(consulta);

    int tamanio = lista.size(); //numero total de documentos
    for(int i = 0; i<tamanio; i++){
      Documento doc = lista.get(i);
      String cadena = doc.getCadena();

      String[] palabrasEnDocumento = limpiaCadena(cadena);
      int[] palabrasContadas = new int[palabrasEnConsulta.length];
      cuentaPalabras(palabrasEnConsulta,palabrasEnDocumento,palabrasContadas);

      String palabra = "";
      PalabraContada palabraBuscada = new PalabraContada("",0,0);
      int numeroDoc = 0;

      double enConsulta=0;
      for(int j = 0; j<palabrasEnConsulta.length; j++){
        palabra = palabrasEnConsulta[j];
        palabraBuscada = getPalabra(palabra, listaPalabra);
        if(palabraBuscada == null){
          numeroDoc = 0;
        } else{
          numeroDoc = palabraBuscada.getNumeroDoc();
        }
        enConsulta = enConsulta + similitud(palabrasContadas[j], numeroDoc, tamanio);
      }

      LinkedList<String> sinRepetir = new LinkedList<>();
      //si hay palabras repetidas no las tomamos en cuenta, solo queremos saber
      //en cuantos documentos se encuentra la palabra, no cuantas veces aparece
      for(int k=0; k<palabrasEnDocumento.length; k++){
        palabra = palabrasEnDocumento[k];
        if(!sinRepetir.contains(palabra)){
          sinRepetir.add(palabra);
        }
      }

      String docSinPalabrasRepetidas = "";
      for(int m = 0; m<sinRepetir.size(); m++){
        String s = sinRepetir.get(m);
        docSinPalabrasRepetidas = s+ " " +docSinPalabrasRepetidas;
      }

      String[] palabrasSinConsultar = docSinPalabrasRepetidas.split("\\s+");
      int[] palabrasSinConsultarContadas = new int[palabrasSinConsultar.length];

      cuentaPalabras(palabrasSinConsultar,palabrasEnDocumento,palabrasSinConsultarContadas);

      double sinConsulta=0;
      for(int l = 0; l<palabrasSinConsultar.length; l++){
        palabra = palabrasSinConsultar[l];
        palabraBuscada = getPalabra(palabra, listaPalabra);
        try{
          numeroDoc = palabraBuscada.getNumeroDoc();
        }catch(NullPointerException npe){
          System.out.println("la palabra" + " "+ palabraBuscada + "/" +palabra + " " + "no ha sido contada");
        }
        double aux = similitud(palabrasSinConsultarContadas[l], numeroDoc, tamanio);
        sinConsulta = sinConsulta + Math.pow(aux,2);
      }
      sinConsulta = Math.sqrt(sinConsulta);

      double similitud = enConsulta/sinConsulta;

      doc.setSimilitud(similitud);
      //imprimir(palabrasEnConsulta, palabrasEnDocumento, palabrasContadas);
    }
    return mergeSort(lista);
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

  //mergeSort ordena de menor a mayor, pero se arregla regresando el reverso de la lista.
  public LinkedList<Documento> mergeSort(LinkedList<Documento> lista){
	  if(lista.size()<2){
      return lista;
    }
    LinkedList<Documento> izquierdo = new LinkedList<>();
    LinkedList<Documento> derecho = new LinkedList<>();
    for(int i = 0; i<lista.size(); i++){
      Documento doc = lista.get(i);
      if(i<lista.size()/2){
		  izquierdo.addLast(doc);
      }else{
		  derecho.addLast(doc);
      }
    }
    return mezcla(mergeSort(izquierdo), mergeSort(derecho));
  }

  public LinkedList<Documento> mezcla(LinkedList<Documento> a, LinkedList<Documento> b){
	  LinkedList<Documento> resultado = new LinkedList<>();
	  Documento auxA = a.getFirst();
	  Documento auxB = b.getFirst();

    double auxASimilitud = auxA.getSimilitud();
    double auxBSimilitud = auxB.getSimilitud();

	  while(auxA!=null && auxB!=null){
      if(auxASimilitud > auxBSimilitud){
	      resultado.addLast(auxA);
	      a.removeFirst();
        if(!a.isEmpty()){
          auxA = a.getFirst();
          auxASimilitud = auxA.getSimilitud();
        }else{
          auxA=null;
        }
	    }else{
	      resultado.addLast(auxB);
	      b.removeFirst();
        if(!b.isEmpty()){
          auxB = b.getFirst();
          auxBSimilitud = auxB.getSimilitud();
        }else{
          auxB=null;
        }
      }
    }
	  if (auxA == null){
	    rellena(resultado, b);
    } else if (auxB == null){
		  rellena(resultado, a);
    }
	  return resultado;
  }

  public void rellena(LinkedList<Documento> lista, LinkedList<Documento> listaAuxiliar){
    for(int i = 0; i<listaAuxiliar.size(); i++){
      Documento doc = listaAuxiliar.get(i);
      lista.addLast(doc);
    }
  }

}
