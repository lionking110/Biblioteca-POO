### Punto 2.5: "Analisis de la Pila de llamadas para 30 Interacciones".

# Analisis de la Pila (Stack Trace) para _"calcularMulta(30, 1000)"_:

1. La JVM reserva un espacio en memoria (Stack Frame) para llamada original con _"dias=30"_

2. Como no es el caso base, se genera una llamada _"calcularMulta(29, 1000)"_ que se apile sobre la atenrior

3. Este proceso se repite sucesivamente hasta llegar a _"calcularMulta(0, 1000)"_. - 31 llamadas en total contando el nivel 0

4. Al llegar a 0, se activa el Caso Base y la pila comienza a resolverse, desapilandose desde arriba hacia abajo,
   sumando el 1% ($10) en cada retorno hasta llegar a la llamda inicial, devolviendo el total de $300
