package main

import "fmt"

func main() {

	// Array
	// El arreglo es una estructura de datos que almacena un conjunto de datos de un mismo tipo.
	// Los arreglos se declaran de la siguiente manera:

	var arreglo [5]int

	// El arreglo se declara con la palabra reservada "var" y se le asigna un nombre.
	// El arreglo se le define una longitud, que es la cantidad de elementos que contiene.
	// El arreglo se le define un tipo de dato, que es el tipo de dato que contiene.

	// Para asignar valores a un arreglo se utiliza la notación [indice].
	// El indice debe ser un número entero, que indica la posición del elemento que se desea asignar.
	// El indice empieza en 0.

	arreglo[0] = 10
	arreglo[1] = 20
	arreglo[2] = 30
	arreglo[3] = 40
	arreglo[4] = 50

	// Para imprimir un arreglo se utiliza la notación [indice].

	fmt.Println(arreglo[0])
	fmt.Println(arreglo[1])
	fmt.Println(arreglo[2])
	fmt.Println(arreglo[3])
	fmt.Println(arreglo[4])

	// El arreglo se puede declarar sin asignarle un valor inicial.
	// En este caso, todos los elementos del arreglo se inicializan con el valor 0.

	var arreglo2 [10]int

	fmt.Println(arreglo2)

	// De igual manera, el arreglo se pueden asignar valores iniciales.
	// En este caso, todos los elementos del arreglo se inicializan con el valor 10.

	arreglo3 := [10]int{10, 10, 10, 10, 10, 10, 10, 10, 10, 10}
	fmt.Println(arreglo3)


	// Slice
	// Un slice es una estructura de datos que almacena un conjunto de datos de un mismo tipo.
	// Los slices se declaran de la siguiente manera:	

	var slice []int

	// El slice se declara con la palabra reservada "var" y se le asigna un nombre.
	// El slice se le define una longitud, que es la cantidad de elementos que contiene.
	// El slice se le define un tipo de dato, que es el tipo de dato que contiene.

	// Para asignar valores a un slice se utiliza la notación [indice].
	// El indice debe ser un número entero, que indica la posición del elemento que se desea asignar.
	// El indice empieza en 0.

	slice = append(slice, 10)
	slice = append(slice, 20)
	slice = append(slice, 30)
	slice = append(slice, 40)
	slice = append(slice, 50)

	// Para imprimir un slice se utiliza la notación [indice].

	fmt.Println(slice)


}