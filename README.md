# Proyecto 01

## Integrantes:

**Diana Berenice Hernández Alonso**

- *correo: dibere07@ciencias.unam.mx*
- *no. de cuenta: 317183425*

**Rodrigo Arévalo Gaytán**

- *correo: rodrigoareg@ciencias.unam.mx*
- *no. de cuenta: 317285880*

## Descripción 💯

En esta proyecto hicimos un motor de busqueda que utiliza una formula TF-IDF para saber que tan similar es un documento con respecto a la consulta que el usuario hizo y le muestra los 10 documentos mas relevantes.

## Código para ejecutar la practica

Los siguientes comandos correran el proyecto el cual abrira una interfaz grafica con la que le usuario podra interactuar
```
$ ant build
$ ant jar
$ ant run
```

Si se quiere generar una carpeta con la documentación ejecute el siguiente comando.
```
$ ant doc
```

## Comentarios 👽

### **comentario de leer documentos**

Al leer los archivos se muestra una barra de carga al usuario para que sepa cuantos archivos se cargaron, pero si dentro de la carpeta que eligio el usuario existe otra carpeta está barra contara solamente los archivos que esten despues de esa carpeta, si la carpeta esta al final se contaran los archivos que esten dentro de esa carpeta.

### **comentario de consulta invalida**

Si el usuario quiere buscar un espacio en blanco, esta consulta no será aceptada ya que no es una palabra y si busca una palabra que no existe en los documentos aparecera que no hay resultados.

### **comentario de como limpiar cadenas de acentos**


### Normalizer.normalize(texto, Normalizer.Form.NFD);

Según el API de la Jdk los métodos normalize de java.text.Normalizer transforman un texto Unicode en su representación compuesta o descompuesta, lo que nos permite poder buscar y ordenar textos de una forma más sencilla después de la transformación.

La transformación está recogida en un estándar de Unicode que lo podemos consultar en Unicode Standard Annex #15 Unicode Normalization Forms.

De este estándar nos interesa la transformación llamada Canonical decomposition.

Esta transformación transforma los caracteres con tildes y diacríticos separandolos en dos caracteres. Por ejemplo

```
1.- á = a´
2.- ä = a¨
3.- â = a^
``` 

### p{InCombiningDiacriticalMarks}

Lo utilizamos en expresiones regulares, y es independiente del lenguaje, por lo que lo podemos utilizar desde Java, Javascript, Php, Perl, Node.js y todo aquel lenguaje con una implementación básica de expresiones regulares.

Esta expresión representa los caracteres UTF-8 comprendidos entre

U+0300 hasta U+036F
entre los que se encuentran las tildes y todos los diacríticos en los que estamos interesados.

Entonces el código

````
1 texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
````

lo que hace es eliminar todos esos caracteres del texto, consiguiendo finalmente el resultado esperado.
