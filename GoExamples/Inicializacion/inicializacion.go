package main

import (
	"fmt"
)

// Inicialización de una variable con una función
var f = func () string {
	return "Hola"
}()

func main() {

	// Inicialización
	// La inicialización se realiza en el momento en que se declara la variable.
	// En este caso, la variable "a" se inicializa con el valor "1".
	var a int = 1

	fmt.Println(a)

	// Inicialización de dos variables
	// En este caso, la variable "b" se inicializa con el valor "2" y la variable "c" con el valor "3".
	var b, c int = 2, 3

	fmt.Println(b, c)

	// Inicialización por dependencia
	// En este caso, la variable "d" se inicializa con el valor "4" y la variable "e" con el valor "5"

	var (
		e = "Hola"
		d = e
	)

	fmt.Println(d, e)

	// Inicialización de una variable con una función
	fmt.Println(f)

}
