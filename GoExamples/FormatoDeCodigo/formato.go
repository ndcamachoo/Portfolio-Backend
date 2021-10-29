package main

import "strconv"

func main() {

	// gofmt
	// gofmt permite formatear el código de un archivo
	// gofmt -w archivo.go

	// Si se quiere formatear todos los archivos de un directorio
	// gofmt -w directorio
	// gofmt -w .

	// Ejemplo:

	var a string
	a = "32"
	b, _ := strconv.Atoi(a)
	println(b)

	// Se ejecuta el comando gofmt -w formato.go para formatear el código

	// Para simplificar el código se puede usar el comando gofmt -s -w archivo.go

}
