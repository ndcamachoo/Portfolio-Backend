package main

import (
	"fmt"
)

func main() {
	// Definir variables
	/*
		var nombre string  // Variable de tipo string
		var edad int       // Variable de tipo int
		var sueldo float32 // Variable de tipo float32
		var casado bool    // Variable de tipo bool
	*/
	// Pueden ser agrupadas de la siguiente manera

	var (
		nombre string
		edad   int
		sueldo float32
		casado bool
	)

	// Asignar valores a las variables

	nombre = "Juan"
	edad = 30
	sueldo = 12.345
	casado = false

	// Pueden ser declaradas en de otra manera

	nombre, edad, sueldo, casado = "Juan", 32, 30000.50, true

	// Imprimir valores de las variables

	fmt.Println(nombre)
	fmt.Println(edad)
	fmt.Println(sueldo)
	fmt.Println(casado)
	fmt.Println("=====================")

	// Imprimir valores de las variables con formato

	fmt.Println("Nombre:", nombre)
	fmt.Println("Edad:", edad)
	fmt.Println("Sueldo:", sueldo)
	fmt.Println("Casado:", casado)

	fmt.Println("=====================")

	// Se pueden declarar variables de forma dinámica

	pi := 3.1416 // Automaticamente se crea una variable de tipo float32 con el valor 3.1416 utilizando la palabra reservada :=. Solamente se puede utilizar una vez al momento de declarar la variable.
	fmt.Println(pi)

	// Para definir una contante se utiliza la palabra reservada const

	const nombreConstante = "Nelson"
	fmt.Println(nombreConstante)

}
