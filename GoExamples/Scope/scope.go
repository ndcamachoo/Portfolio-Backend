package main

import "fmt"

// Scope
// Scope es el alcance de una variable.
// Una variable declarada dentro de una función,
// es accesible dentro de la función.

// Una variable declarada fuera de una función,
// es accesible dentro y fuera de la función.

// Una variable declarada dentro de una estructura,
// es accesible dentro y fuera de la estructura.

// Una variable declarada fuera de una estructura,
// es accesible dentro y fuera de la estructura.

// Una variable declarada dentro de una clase,
// es accesible dentro y fuera de la clase.

// El alcance de una variable es el contexto en el que
// se declara.

// Ejemplo:
var name string

// name es accesible dentro y fuera de la función.

func ScopeGlobal() string {
	name = "Test"
	return name
}

// Ejemplo de scope local.

func ScopeLocal() int  {
	edad := 20
	return edad
}

func main() {
	
	// Ejecución de la función con la variable name de alcance global.

	fmt.Println(ScopeGlobal())
	fmt.Println(name)

	// Ejecución de la función con la variable name de alcance local.

	fmt.Println(ScopeLocal())
	//fmt.Println(edad) // La variable edad no existe en el alcance global y solo existe en el contexto de la función.

}