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
      palabrasContadas[i] = contador;
      contador = 0;
    }

    imprimir(palabrasEnConsulta, palabrasEnDocumento, palabrasContadas);
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
