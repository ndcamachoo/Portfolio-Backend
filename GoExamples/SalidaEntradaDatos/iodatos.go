package main

import "fmt"

func main() {

	// Salida de datos por pantalla

	fmt.Print("Salida de datos por pantalla\n")
	fmt.Println("Salida de datos por pantalla con salto de linea")

	// Salida de datos con formato de texto

	msg := "Salida de datos con formato de texto"
	fmt.Printf("%s",msg) //%s para cadenas de texto, %d para enteros, %f para flotantes

	// Entrada de datos por teclado

	var input string
	fmt.Scanln(&input)
	fmt.Println(input)


}