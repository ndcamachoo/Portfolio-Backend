package main

import "fmt"

var a int

// Funciones
// Funciones son bloques de código que se ejecutan cuando se llaman.
// Las funciones pueden recibir parámetros y devolver valores.
// Las funciones pueden ser llamadas desde cualquier parte del programa.

// Declaración de una función
// Funciones pueden ser declaradas de la siguiente manera:
// func nombreFuncion(parametros) tipoRetorno {
// 	código
// }

// Ejemplo de una función

func suma(a int, b int) int {
	return a + b
}

// Funciones con parámetros opcionales
// Las funciones pueden tener parámetros opcionales.
// Para declarar un parámetro opcional, se debe colocar un signo de interrogación al final del tipo de dato del parámetro.	

func listado(params ...int) []int {
	
	var nums []int
	nums = params
	return nums

}

// Funciones autoejecutables
// Las funciones pueden ser autoejecutables.
// Las funciones se pueden ejecutar de la siguiente manera:

var f = func () string {
	return "Hola"
}()

// Función init
// Las funciones init se ejecutan al inicio de la ejecución del programa.

// Ejemplo de una función init

func init() {
	a = 1
}

func main() {

	
	// Llamada a una función
	// Las funciones se llaman de la siguiente manera:
	// nombreFuncion(parametros)

	// Ejemplo de una llamada a una función
	fmt.Println(suma(1, 2))

	
	// Ejemplo de una función con parámetros opcionales
	
	fmt.Println(listado(1,2,3,4,5))

	// Ejemplo de una función autoejecutable
	fmt.Println(f)

	// Ejemplo de una función init
	fmt.Println(a)

}