package fciencias.edatos.proyecto;

public class Consultas{

  public Consultas(){}

  public void hacerConsulta(String consulta, String documento){
    //borramos caracteres ¿ ¡ ? ! , . ; :
    documento = documento.replaceAll("[¿¡?!,.;:]*", "");

    String[] palabrasEnConsulta = consulta.split("\\s+"); //[Hola, mi, nombre]
    String[] palabrasEnDocumento = documento.split("\\s+"); //[hola, ..]

    int[] palabrasContadas = new int[palabrasEnConsulta.length];
    int contador = 0;
    for(int i = 0; i<palabrasEnConsulta.length; i++){
      for(int j = 0; j<palabrasEnDocumento.length; j++){
        if(palabrasEnConsulta[i].equalsIgnoreCase(palabrasEnDocumento[j])){
          contador++;
        }
      }
      palabrasContadas[i] = contador; //se cuenta cuantas veces aparecio una palabra consultada
      contador = 0;
    }


    imprimir(palabrasEnConsulta, palabrasEnDocumento, palabrasContadas);
  }

  // TF = log 2 f_(td) + 1 = (Math.log(ftd) / Math.log(2)) + 1, si ftd == 0 entonces 0  formula para calcular la relevancia de t en el documento
  // IDF = log 2 ((N + 1)/N_t) = (Math.log((N + 1)/N_t) / Math.log(2)) formula para calcular la relevancia del termino en el conjunto total de documentos.
  //funcion (aun no decido como ponerle de nombre, todavia no es la formula de similitud completa)
  /****/
  public double similitud(double numeroOcurrencias, double numeroDocumentos, double numeroDocumentosTotal){
    double tf, idf;
    if(numeroOcurrencias > 0){
      tf = (Math.log(numeroOcurrencias) / Math.log(2.0)) + 1; //usamos el logaritmo en base 2
    }else{
      tf = 0;
    }
    if(numeroDocumentos > 0 && numeroDocumentosTotal>0){
      idf = Math.log((numeroDocumentosTotal + 1)/numeroDocumentos) / Math.log(2.0);
    }else{
      idf = 0;
    }
    return tf * idf;
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
