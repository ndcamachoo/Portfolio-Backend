package main

import (
	"Paquetes/mensaje"
	"fmt"
)

func main() {

	// Paquetes
	// Paquetes es una estructura de datos que contiene un conjunto de paquetes.
	// Un paquete es una colección de archivos que forman una unidad.
	// Un paquete puede contener archivos de código fuente, archivos de documentación,
	// archivos de imágenes, archivos de audio, archivos de video, archivos de datos, etc.

	// Módulos
	// Módulos son una forma de organizar el código fuente de un programa.
	// Un módulo es una colección de archivos que forman una unidad.
	// Un módulo puede contener archivos de código fuente, archivos de documentación,
	// archivos de imágenes, archivos de audio, archivos de video, archivos de datos, etc.

	fmt.Println(mensaje.FuncionPublica())

	// Comandos
	
	// go mod init <nombre_del_paquete> -> En este caso nuestro paquete es el nombre de la carpeta raiz del proyecto (Paquetes)
	// go mod tidy -> Limpia el código del paquete
	// go mod vendor -> Crea una carpeta vendor dentro del paquete
	// go mod download -> Descarga los paquetes que se encuentran en el repositorio de paquetes
	// go mod edit -> Edita el archivo go.mod
	// go mod graph -> Muestra el grafo de dependencias
	// go mod list -> Muestra la lista de paquetes que se encuentran en el repositorio de paquetes
	
}
