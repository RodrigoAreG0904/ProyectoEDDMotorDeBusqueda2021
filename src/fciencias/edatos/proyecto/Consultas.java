package fciencias.edatos.proyecto;

import fciencias.edatos.proyecto.Documento;
import fciencias.edatos.proyecto.PalabraContada;
import fciencias.edatos.proyecto.gui.MarcoError;
import java.util.LinkedList;
import java.text.Normalizer;

public class Consultas{

  private LinkedList<Resultado> cache =  new LinkedList<>();

  /**
   * Metodo que al darle una cadena busca cuantas veces aparece en un documento y cuantos documentos hay, 
   * pregunta en cuantos documentos esta la palabra para despues sacar la formula de similitud.
   * @param consulta. La cadena que contiene palabras que se buscaran en la cadena de que contiene cada
   * documento para ver la similitud de cada documento.
   * @param lista. La lista de documentos que previamente fueron cargados.
   * @param listaPalabra. Es una lista que contiene todas las palabras que hay en todos los documentos y
   * cuenta en cuantos aparece todas esa palabras.
   */
  public LinkedList<Documento> hacerConsulta(String consulta, LinkedList<Documento> lista, LinkedList<PalabraContada> listaPalabra){

    consulta = limpiaCadena(consulta);
    
    if(contiene(consulta)){
      Resultado encontrado = getResultado(consulta);
      lista = encontrado.getListaDoc();
      return lista;
    }else{
      String[] palabrasEnConsulta = consulta.split("\\s+");
      
      int tamanio = lista.size();
      
      for(int i = 0; i < tamanio; i++){
        Documento doc = lista.get(i);
        String cadena = doc.getCadena();

        cadena = limpiaCadena(cadena);
        String[] palabrasEnDocumento = cadena.split("\\s+");;
        int[] palabrasContadas = new int[palabrasEnConsulta.length];
        cuentaPalabras(palabrasEnConsulta,palabrasEnDocumento,palabrasContadas);

        String palabra = "";
        PalabraContada palabraBuscada = new PalabraContada("",0,0);
        int numeroDoc = 0;

        double enConsulta = 0;
        for(int j = 0; j < palabrasEnConsulta.length; j++){
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
        //para buscar el IDF*TF de las palabras que no se consultaron y para no repetirlas.
        for(int k = 0; k < palabrasEnDocumento.length; k++){
          palabra = palabrasEnDocumento[k];
          if(!sinRepetir.contains(palabra)){
            sinRepetir.add(palabra);
          }
        }

        String docSinPalabrasRepetidas = "";
        for(int m = 0; m < sinRepetir.size(); m++){
          String s = sinRepetir.get(m);
          docSinPalabrasRepetidas = s+ " " +docSinPalabrasRepetidas;
        }

        String[] palabrasSinConsultar = docSinPalabrasRepetidas.split("\\s+");
        int[] palabrasSinConsultarContadas = new int[palabrasSinConsultar.length];

        cuentaPalabras(palabrasSinConsultar,palabrasEnDocumento,palabrasSinConsultarContadas);

        double sinConsulta = 0;
        for(int l = 0; l < palabrasSinConsultar.length; l++){
          palabra = palabrasSinConsultar[l];
          palabraBuscada = getPalabra(palabra, listaPalabra);
          try{
            numeroDoc = palabraBuscada.getNumeroDoc();
          }catch(NullPointerException npe){
            MarcoError error = new MarcoError("la palabra" + " "+ palabraBuscada + "/" +palabra + " " + "no ha sido contada");
            error.ocultar();
          }
          double aux = similitud(palabrasSinConsultarContadas[l],  numeroDoc, tamanio);
          sinConsulta = sinConsulta + Math.pow(aux,2);
        }
        sinConsulta = Math.sqrt(sinConsulta);

        double similitud = enConsulta/sinConsulta;

        doc.setSimilitud(similitud);
      }

      lista = mergeSort(lista);
      LinkedList<Documento> listaAux = new LinkedList<>();
      for(int r = 0; r < 10; r++){
        Documento relevante = lista.get(r);
        listaAux.addLast(relevante);
      }
      Resultado nuevo = new Resultado(consulta, listaAux);
      cache.addFirst(nuevo);
      if(cache.size() > 10){
        cache.removeLast();
      }
      return listaAux;
    }
  }

  /**
   * Metodo que le pasamos 1 arreglo con palabras a buscar en un segundo arreglo que contenga las palabras de un documento,
   * y guarda la cuenta en un tercer arreglo de enteros.
   * @param palabrasAContar. El arreglo de palabras que vamos a buscar.
   * @param palabrasDondeContar. El arreglo donde vamos a buscar las palabras.
   * @param palabrasContadas. El arreglo de enteros donde guardaremos las veces que se encuentra la palabra.
   */
  public void cuentaPalabras(String[] palabrasAContar, String[] palabrasDondeContar, int[] palabrasContadas){
    int contador = 0;
    for(int j = 0; j < palabrasAContar.length; j++){
      for(int k = 0; k < palabrasDondeContar.length; k++){
        if(palabrasAContar[j].equalsIgnoreCase(palabrasDondeContar[k])){
          contador++;
        }
      }
      palabrasContadas[j] = contador; //se cuenta cuantas veces aparecio una palabra consultada
      contador = 0;
    }
  }

  /**
   * Metodo que regresa el producto de el IDF y TF de una palabra.
   * TF = log 2 f_(td) + 1 = (Math.log(ftd) / Math.log(2)) + 1, si ftd == 0 entonces 0  formula 
   * para calcular la relevancia de t en el documento.
   * IDF = log 2 ((N + 1)/N_t) = (Math.log((N + 1)/N_t) / Math.log(2)) formula para calcular 
   * la relevancia del termino en el conjunto total de documentos.
   * @param numeroOcurrencias. El numero de veces que aparece una palabra en un documento.
   * @param numeroDocumentos. El numero de documentos donde aparece la palabra buscada.
   * @param numeroDocumentosTotal. El numero de todos los documentos cargados.
   */
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

  /**
   * Metodo que al pasarle una cadena le quita signos de interrogacion, exclamacion, puntuacion y diacriticos.
   * @param cadena. La cadena a limpiar.
   * @return cadena. La cadena limpiada.
   */
  public String limpiaCadena(String cadena){
    //borramos caracteres ¿ ¡ ? ! , . ; :
    cadena = cadena.replaceAll("[¿¡?!,.;:]*", "");

    //separamos los caracteres compuestos (á = a´)
    cadena = Normalizer.normalize(cadena, Normalizer.Form.NFD);

    //borramos todos los signos diacriticos separados anteriormente
    cadena = cadena.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

    //guardamos cada palabra en arreglos para despues compararlas
    return cadena;
  }

  /**
   * Metodo que regresa una palabra por medio de su nombre.
   * @param nombreBuscado. El nombre de la palabra que queremos buscar.
   * @param listaPalabra. La lista donde vamos a buscar la Palabra.
   * @return PalabraContada. Si encuentra la palabra en la lista, la regresa.
   */
  public PalabraContada getPalabra(String nombreBuscado, LinkedList<PalabraContada> listaPalabra){
    int j = listaPalabra.size();
    if(j == 0){
      //Poner una ventana de error donde un doc es vacio
      System.out.println("Esta lista esta vacia, no contiene palabras");
      return null;
    }
    PalabraContada res;
    for(int i = 0; i < j; i++){
      res = listaPalabra.get(i);
      if(res.getNombre().equalsIgnoreCase(nombreBuscado)){
        return res;
      }
    }
    return null;
  }

  /**
   * Metodo que ordena una lista de documentos de mayo a menor por su similitud.
   * @param lista. La lista que se va a ordenar.
   * @return lista. La lista ya ordenada.
   */
  public LinkedList<Documento> mergeSort(LinkedList<Documento> lista){
	  if(lista.size() < 2){
      return lista;
    }
    LinkedList<Documento> izquierdo = new LinkedList<>();
    LinkedList<Documento> derecho = new LinkedList<>();
    for(int i = 0; i < lista.size(); i++){
      Documento doc = lista.get(i);
      if(i < lista.size()/2){
		  izquierdo.addLast(doc);
      }else{
		  derecho.addLast(doc);
      }
    }
    return mezcla(mergeSort(izquierdo), mergeSort(derecho));
  }

  /**
   * Metodo auxiliar que al pasarle dos listas las une de forma ordenada.
   * @param a. Una mitad de una lista que se va a ordenar.
   * @param b. Una mitad de una lista que se va a ordenar.
   * @return lista. Una lista ya ordenada uniendo las listas a y b.
   */
  public LinkedList<Documento> mezcla(LinkedList<Documento> a, LinkedList<Documento> b){
	  LinkedList<Documento> resultado = new LinkedList<>();
	  Documento auxA = a.getFirst();
	  Documento auxB = b.getFirst();

    double auxASimilitud = auxA.getSimilitud();
    double auxBSimilitud = auxB.getSimilitud();

	  while(auxA != null && auxB != null){
      if(auxASimilitud > auxBSimilitud){
	      resultado.addLast(auxA);
	      a.removeFirst();
        if(!a.isEmpty()){
          auxA = a.getFirst();
          auxASimilitud = auxA.getSimilitud();
        }else{
          auxA = null;
        }
	    }else{
	      resultado.addLast(auxB);
	      b.removeFirst();
        if(!b.isEmpty()){
          auxB = b.getFirst();
          auxBSimilitud = auxB.getSimilitud();
        }else{
          auxB = null;
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

  /**
   * Metodo auxiliar que al pasarle dos listas mete todos los elementos de la segunda en la primera.
   * @param lista. La lista receptora de elementos.
   * @param listaAuxiliar. La lista que pasara los elementos.
   */
  public void rellena(LinkedList<Documento> lista, LinkedList<Documento> listaAuxiliar){
    for(int i = 0; i < listaAuxiliar.size(); i++){
      Documento doc = listaAuxiliar.get(i);
      lista.addLast(doc);
    }
  }

  /** 
   * Metodo que nos dice si en la lista de Resultado existe un resultado con el nombre que buscamos.
   * @param nombre. El nombre con el que buscaremos un resultado en la lista de Resultado (cache).
   * @return true si la lista contiene un resultado con el nombre que nosotros buscamos, false en otro caso.
   */
  public boolean contiene(String nombre){
    //cuando está vacía.
    if(cache.isEmpty()){
      return false;
    }

    String nombreAComparar;
    //cuando tiene 1 elemento.
    if(cache.size() == 1){
      Resultado aComparar = cache.get(0);
      nombreAComparar = aComparar.getConsulta();
      return nombre.equalsIgnoreCase(nombreAComparar);
    }

    //cuando hay más de 1 elemento.
    for(int i = 0; i < cache.size(); i++){
      Resultado aComparar = cache.get(i);
      nombreAComparar = aComparar.getConsulta();
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
  public Resultado getResultado(String consulta){
    int j = cache.size();
    if(j == 0){
      MarcoError error = new MarcoError("Esta lista esta vacia, no contiene resultados");
      error.ocultar();
      return null;
    }
    Resultado res;
    for(int i = 0; i < j; i++){
      res = cache.get(i);
      if(res.getConsulta().equalsIgnoreCase(consulta)){
        return res;
      }
    }
    return null;
  }
}