package main

import (
	"fmt"
	"jwt/auth"
	"net/http"
)

func main() {

	// JWT
	// JWT es una librería que nos permite crear y validar tokens
	// JWT permite crear tokens con una firma digital, es decir, un hash
	// que identifica al usuario

	// JWT esta basado en una estructura de datos llamada Claims
	// Claims son datos que se incluyen en el token, y que pueden ser
	// leidos por el servidor para validar la autenticidad del token
	// y para obtener información adicional del usuario

	// JWT esta compuesto por un header y un payload
	// Header es una estructura de datos que contiene información
	// del token, como por ejemplo el algoritmo de firma digital
	// y el tipo de token

	// Payload es una estructura de datos que contiene la información
	// del usuario, como por ejemplo el nombre, el correo, la fecha de
	// creación del token, etc.

	// JWT es un token cifrado, por lo que no se puede leer el contenido
	// del token directamente, sino que se debe leer una firma digital
	// que lo identifica, y que permite validar el contenido del token
	// y obtener información del usuario

	// ======================================================================

	// Se crea un servidor web de prueba para verificar el funcionamiento del JWT

	// Mediante NewServerMux() se crea un servidor web con un mux
	// que permite manejar las peticiones del usuario

	mux := http.NewServeMux()
	// Se crea una ruta para el endpoint /login
	mux.HandleFunc("/login", auth.Login)

	// Se crea una ruta para el endpoint /validate
	mux.HandleFunc("/validate", auth.ValidateToken)
	
	http.ListenAndServe(":8080", mux)
	fmt.Println("Servidor iniciado en el puerto 8080")

	
}