##Normalizer.normalize(texto, Normalizer.Form.NFD);##

Según el API de la Jdk los métodos normalize de java.text.Normalizer transforman un texto Unicode en su representación compuesta o descompuesta, lo que nos permite poder buscar y ordenar textos de una forma más sencilla después de la transformación.

La transformación está recogida en un estándar de Unicode que lo podemos consultar en Unicode Standard Annex #15 Unicode Normalization Forms.

De este estándar nos interesa la transformación llamada Canonical decomposition.

Esta transformación transforma los caracteres con tildes y diacríticos separandolos en dos caracteres. Por ejemplo

''
1.- á = a´
2.- ä = a¨
3.- â = a^
''

##p{InCombiningDiacriticalMarks}##

Lo utilizamos en expresiones regulares, y es independiente del lenguaje, por lo que lo podemos utilizar desde Java, Javascript, Php, Perl, Node.js y todo aquel lenguaje con una implementación básica de expresiones regulares.

Esta expresión representa los caracteres UTF-8 comprendidos entre

U+0300 hasta U+036F
entre los que se encuentran las tildes y todos los diacríticos en los que estamos interesados.

Entonces el código

''
1 texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
''

lo que hace es eliminar todos esos caracteres del texto, consiguiendo finalmente el resultado esperado.
