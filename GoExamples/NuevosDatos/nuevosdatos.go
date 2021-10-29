package main

import "fmt"

// Type
// Type es una palabra reservada que indica que se está definiendo un tipo de dato.

type entero int
type cadena string

// Struct
// Struct es un tipo de dato que se define con llaves.
// Los campos de un struct se definen con la palabra reservada "campo".
// Los campos de un struct pueden ser de cualquier tipo de dato.
// Los campos de un struct pueden ser de tipos primitivos o de tipos struct.

// Ejemplo de struct
type persona struct {
	
	// Atributos
	nombre string
	edad   int

}


func main() {
	
	// Entero

	var edad entero
	edad = 20

	fmt.Println(edad)
	
	// Cadena

	var nombre cadena
	nombre = "Nelson"

	fmt.Println(nombre)

	// Struct

	persona1 := persona{
		nombre : "Nelson",
		edad   : 20,
	}

	fmt.Println(persona1)

	// Struct vacío

	persona2 := new(persona)
	persona2.nombre = "Nelson"
	persona2.edad = 20
	fmt.Println(persona2)

	// Casteo
	// Casteo es una operación que se realiza para convertir un tipo de dato a otro.

	var edad2 entero
	edad2 = entero(persona1.edad)

	fmt.Println(edad2)


}