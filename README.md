# Contado Pares

## Ejemplo de como funciona la solucion del HashMap
```
arr[] = {10, 12, 10, 15, -1, 7, 6, 5, 4, 2, 1, 1, 1}
target = 11
```
`fullfillOcurrences`: almacena la frecuencia de cada número en el arreglo

| Key | Value |
|:----|:------|
| 10  | 2     |
| 12  | 1     |
| 15  | 1     |
| -1  | 1     |
| 7   | 1     |
| 6   | 1     |
| 5   | 1     |
| 4   | 1     |
| 2   | 1     |
| 1   | 3     |

Filtro por aquellos numeros que son menor al `target / 2`, ya que los numeros mayores a este ya los calcule antes.
> Por ejemplo, cuando encuentra el numero 6 no tiene sentido agregar el par (6,5), ya que lo agrego cuando encontro el 5, agregando el par (5,6).

Numeros filtrados: `fNumbers = [-1, 5, 4, 2, 1, 1, 1]`

Por cada numero que cumplio el filtro calculo los pares.

Primero, Calculo el numero objetivo de cada numero que cumplio con el filtro, es decir, busco el numero que sea igual al `target - fNumbers[i]`. 
> Por ejemplo, para `-1` el objetivo es `11 - (-1) = 12`.

| Numero (`fNumbers[i]`) | Objetivo `(tarjet - fNumbers[i])` |
|:--------------|:----------------------------------|
| -1            | 12                                |
| 5             | 6                                 |
| 4             | 7                                 |
| 2             | 9                                 |
| 1             | 10                                |

Entonces, pregunto si el numero objetivo se encuentra en el mapa o  si su frecuencia es distinta de cero.
> Por ejemplo, en este caso, primero pregunto por el `12`.

Pregunto si su frecuencia es distinta de cero para los casos en los que se repite el numero, para que no agregue nuevos pares demás para lo proxima vez que se encuentre con el mismo numero.
> Por ejemplo, el 1 esta repetido 3 veces, entonces lo calculo una vez e inmediatamente después actualizo su frecuencia en el mapa a 0, entonces la proxima vez que se encuentre con el 1, no ágrega pares demás ya que la frecuencia de su numero objetivo (en este caso, seria el 10) es cero.

Si se cumple la condición anterior, calculo la cantidad de pares que tengo que crear.

Primero, pregunto si el numero y el objetivo son distintos, entonces en ese caso, multiplico las frecuencias de ambos numeros, en caso contrario en el que son iguales, tengo que sumar recursivamente la frecuencia -1 de ese numero hacia atras hasta llegar a 1.
> Por ejemplo, si el `target = 2`, y tengo el siguiente `arreglo[] = {1,1,1,1}`.
La cantidad de pares que hay que colocar es 6, dado que la `frecuencia(1) = 4`, su `frecuencia(1)-1 = 3`, y su `suma recursiva hasta llegar a 1 ⟶ 3+2+1=6`.

Por ultimo, por cada numero filtrado, creo tantos pares `(number, objective)` como cantidad de pares me haya dado el resultado anterior.