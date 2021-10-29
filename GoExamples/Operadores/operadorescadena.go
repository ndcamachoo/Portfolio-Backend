package main

import (
	"fmt"
	"strings"
)

func main() {

	// Operadores de cadena

	// Operador +
	// Concatena dos cadenas
	var cadena1 string = "Hola"
	var cadena2 string = " mundo"
	var cadena3 string = cadena1 + cadena2
	fmt.Println(cadena3)

	// Operador +=
	// Concatena dos cadenas
	cadena1 += cadena2
	fmt.Println(cadena1)

	// Operador ==
	// Compara dos cadenas
	var cadena4 string = "Hola"
	var cadena5 string = "Hola"
	var cadena6 string = "mundo"
	fmt.Println(cadena4 == cadena5)

	// Operador !=
	// Compara dos cadenas
	fmt.Println(cadena4 != cadena6)

	// Operador <
	// Compara dos cadenas
	fmt.Println(cadena4 < cadena6)

	// Operador >
	// Compara dos cadenas
	fmt.Println(cadena4 > cadena6)

	// Operador <=
	// Compara dos cadenas
	fmt.Println(cadena4 <= cadena6)

	// Operador >=
	// Compara dos cadenas
	fmt.Println(cadena4 >= cadena6)

	// Operador []
	// Devuelve un caracter de una cadena
	fmt.Println(cadena1[0])

	// Operador [:]
	// Devuelve una subcadena de una cadena
	fmt.Println(cadena1[0:3])

	// Operador len
	// Devuelve la longitud de una cadena
	fmt.Println(len(cadena1))

	// Libreria strings
	// https://golang.org/pkg/strings/

	// Operador strings.Index
	// Devuelve la posicion de un caracter en una cadena
	fmt.Println(strings.Index(cadena1, "a"))

	// Operador strings.LastIndex
	// Devuelve la posicion de un caracter en una cadena
	fmt.Println(strings.LastIndex(cadena1, "a"))

	// Operador strings.Count
	// Cuenta la cantidad de veces que aparece un caracter en una cadena
	fmt.Println(strings.Count(cadena1, "a"))

	// Operador strings.Contains
	// Devuelve true si la cadena contiene el caracter
	fmt.Println(strings.Contains(cadena1, "a"))

	// Operador strings.HasPrefix
	// Devuelve true si la cadena empieza con el prefijo
	fmt.Println(strings.HasPrefix(cadena1, "H"))

	// Operador strings.HasSuffix
	// Devuelve true si la cadena termina con el sufijo
	fmt.Println(strings.HasSuffix(cadena1, "o"))

	// Operador strings.Replace
	// Reemplaza una cadena por otra
	fmt.Println(strings.Replace(cadena1, "a", "e", -1))

	// Operador strings.ToLower
	// Convierte una cadena a minusculas
	fmt.Println(strings.ToLower(cadena1))

	// Operador strings.ToUpper
	// Convierte una cadena a mayusculas
	fmt.Println(strings.ToUpper(cadena1))

	// Operador strings.Trim
	// Elimina espacios en blanco de una cadena
	fmt.Println(strings.Trim(cadena1, " "))

	// Operador strings.TrimLeft
	// Elimina espacios en blanco de una cadena
	fmt.Println(strings.TrimLeft(cadena1, " "))

	// Operador strings.TrimRight
	// Elimina espacios en blanco de una cadena
	fmt.Println(strings.TrimRight(cadena1, " "))

	// Operador strings.TrimSpace
	// Elimina espacios en blanco de una cadena
	fmt.Println(strings.TrimSpace(cadena1))

	// Operador strings.Split
	// Divide una cadena en subcadenas
	fmt.Println(strings.Split(cadena1, "a"))

	// Operador strings.Join
	// Junta una cadena de subcadenas
	fmt.Println(strings.Join([]string{cadena1, cadena2}, " "))

	// Operador strings.ToTitle
	// Convierte una cadena a mayusculas
	fmt.Println(strings.ToTitle(cadena1))


}