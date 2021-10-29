package main

import "fmt"

// Puntero
// Puntero es una variable que almacena una dirección de memoria.
// Una dirección de memoria es un número que representa una posición en memoria.

// Estas variables tienen que tener un tipo de dato que se pueda almacenar en memoria.
// Por ejemplo, una variable de tipo entero, una variable de tipo cadena, etc.

// Los punteros son utiles para pasar variables por referencia a una función.

// Los punteros se representan con el símbolo & antes del nombre de la variable.

// Ejemplo de función que recibe un puntero
func recibePuntero(puntero *int) *int {
	*puntero = 5
	return puntero
}

// Punteros en una variable de tipo struct
type persona struct {
	nombre string
	edad   int
}

// Función que recibe un puntero para modificar el valor de una variable de tipo struct
func modificaPersona(persona *persona) {
	(*persona).edad = 30
}

func main() {
	
	// Ejemplo 1 - Sin puntero
	// Declarar una variable de tipo entero
	var entero int
	entero = 10
	fmt.Println(entero)

	// Ejemplo 2 - Con puntero
	fmt.Println(&entero) // Imprime la dirección de memoria de la variable entero

	// Ejemplo 3 - funcion que recibe un puntero
	fmt.Println(recibePuntero(&entero))
	fmt.Println(entero)

	// Operaciones con punteros
	// * - Desreferencia el valor de un puntero
	// & - Obtiene la dirección de memoria de una variable
	// *& - Obtiene el valor de una variable
	// &* - Obtiene la dirección de memoria de un valor

	// Ejemplo 4 - Operaciones con punteros
	var entero2 *int
	entero2 = &entero // entero2 := &entero
	fmt.Println(entero2) // Imprime la dirección de memoria de la variable entero
	fmt.Println(*entero2) // Imprime el valor de la variable entero

	// Ejemplo 5 - Punteros en una variable de tipo struct
	persona1 := persona{
		nombre: "Nelson",
		edad:   20,
	}

	fmt.Println(persona1)
	modificaPersona(&persona1)
	fmt.Println(persona1)

}