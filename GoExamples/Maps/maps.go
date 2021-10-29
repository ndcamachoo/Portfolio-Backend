package main

import "fmt"

func main() {

	// Maps
	// Maps son un tipo de dato que nos permite almacenar un conjunto de pares clave-valor.
	// Son como los diccionarios de Python, o un objeto literal de JS, pero en Go.

	// Crear un mapa vacío
	var mapa map[string]int

	// Crear un mapa con valores iniciales
	mapa = map[string]int{
		"uno":  1,
		"dos":  2,
		"tres": 3,
	}

	// Acceder a un valor
	fmt.Println(mapa["uno"])

	// Agregar un valor
	mapa["cuatro"] = 4

	// Eliminar un valor
	delete(mapa, "dos")

	// Recorrer un mapa
	for clave, valor := range mapa {
		fmt.Println(clave, valor)
	}

	// Visualizar el mapa
	fmt.Println(mapa)

	//  Verificar si una clave existe
	valor, existe := mapa["cuatro"]
	fmt.Println(valor, existe)

	// Make
	// Crea un mapa vacío y lo devuelve
	mapa2 := make(map[string]int)
	fmt.Println(mapa2)

	

}