package main

import "fmt"

func main() {

	// Range
	// Range es una estructura que nos permite recorrer una colección de elementos
	// y obtener una referencia a cada uno de ellos.

	// Ejemplo 1
	// En este ejemplo vamos a recorrer un slice de enteros y obtener una referencia
	// a cada uno de ellos.

	// Declaramos un slice de enteros
	numeros := []int{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}

	// Recorremos el slice
	for i, numero := range numeros {
		// Imprimimos el valor del índice y el valor del elemento
		fmt.Println(i, numero)
	}

	fmt.Println("")

	// Ejemplo 2
	// En este ejemplo vamos a recorrer un slice de strings y obtener una referencia
	// a cada uno de ellos.

	// Declaramos un slice de strings
	nombres := []string{"Juan", "Pedro", "Luis", "Carlos", "José", "Miguel", "Ricardo", "Jorge", "Álvaro", "Javier"}

	// Recorremos el slice
	for i, nombre := range nombres {
		// Imprimimos el valor del índice y el valor del elemento
		fmt.Println(i, nombre)
	}

	fmt.Println("")

	// Ejemplo 3
	// En este ejemplo vamos a recorrer un map y obtener una referencia
	// a cada uno de sus elementos.

	// Declaramos un map
	personas := map[string]string{
		"nombre":  "Juan",
		"apellido": "Pérez",
		"edad": "30",
	}

	// Recorremos el map
	for key, value := range personas {
		// Imprimimos el valor de la llave y el valor del elemento
		fmt.Println(key, value)
	}

}