package auth

import (
	"crypto/rsa"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"jwt/models"
	"log"
	"net/http"
	"time"

	jwt "github.com/golang-jwt/jwt/v4"     // Se importa el paquete jwt
	"github.com/golang-jwt/jwt/v4/request" // Se importa el paquete request para validar el token
)

// Los token se firmaran con la llave privada del usuario
// y se verificaran con la llave publica del mismo

// Para crear las llaves se usa el comando:

// Para la llave privada:
// openssl genrsa -out private.rsa 1024

// Para la llave publica:
// openssl rsa -in private.rsa -pubout -pubout > public.rsa.pub

var (
	privateKey *rsa.PrivateKey // Se crea una variable privada para almacenar la llave privada mediante la funcion rsa.PrivateKey de la libreria crypto/rsa
	publicKey *rsa.PublicKey // Se crea una variable publica para almacenar la llave publica mediante la funcion rsa.PublicKey de la libreria crypto/rsa
)

// Se cargan las llaves privada y publica al iniciar el programa
func init() {
	
	privateBytes, err := ioutil.ReadFile("./auth/keys/private.rsa") // Se lee el archivo privado y se almacena en la variable privadaBytes

	if err != nil {
		log.Fatal("No se pudo leer la llave privada") // Si no se pudo leer el archivo privado se muestra un mensaje de error
	}

	publicBytes, err := ioutil.ReadFile("./auth/keys/public.rsa.pub") // Se lee el archivo publico y se almacena en la variable publicBytes

	if err != nil {
		log.Fatal("No se pudo leer la llave publica") // Si no se pudo leer el archivo publico se muestra un mensaje de error
	}
 
	privateKey, err = jwt.ParseRSAPrivateKeyFromPEM(privateBytes) // Se almacena la llave privada en la variable privadaKey mediante la funcion jwt.ParseRSAPrivateKeyFromPEM de la libreria jwt

	if err != nil {
		log.Fatal("No se pudo parsear la llave privada") // Si no se pudo parsear la llave privada se muestra un mensaje de error
	}

	publicKey, err = jwt.ParseRSAPublicKeyFromPEM(publicBytes) // Se almacena la llave publica en la variable publicKey mediante la funcion jwt.ParseRSAPublicKeyFromPEM de la libreria jwt

	if err != nil {
		log.Fatal("No se pudo parsear la llave publica") // Si no se pudo parsear la llave publica se muestra un mensaje de error
	}

}

func GenerateJWT(user models.User) string { // Se crea la funcion GenerateJWT para generar un token

	claims := models.Claims{ // Se crea una variable de tipo Claims para almacenar los datos del usuario
		User: user,
		StandardClaims: jwt.StandardClaims{ // Se crea una variable de tipo StandardClaims para almacenar los datos del token
			ExpiresAt: time.Now().Add(time.Hour * 24).Unix(), // Se le asigna una fecha de expiracion de 24 horas
			Issuer: "JWT Go -> Nelson Camacho", // Se le asigna una empresa que lo creo
		},
	}

	token := jwt.NewWithClaims(jwt.SigningMethodRS256, claims) // Se crea un nuevo token con los datos del usuario y la firma

	result , err := token.SignedString(privateKey) // Se crea una variable result para almacenar el token firmado decodificado mediante la clave privada.

	if err != nil {
		fmt.Println("No se pudo firmar el token") // Si no se pudo firmar el token se muestra un mensaje de error
	}

	return result // Se retorna el token firmado

}

// Se crea la funcion Login para validar el token	
func Login(w http.ResponseWriter, r *http.Request) { 

	var user models.User // Se crea una variable de tipo User para almacenar los datos del usuario	
	err := json.NewDecoder(r.Body).Decode(&user) // Se decodifica el usuario mediante la funcion json.NewDecoder de la libreria json en función del body de la peticion

	if err != nil {
		fmt.Println("No se pudo decodificar el usuario") // Si no se pudo decodificar el usuario se muestra un mensaje de error
	}

	if user.Name == "Nelson" && user.Password == "123456789" { // Se valida el usuario mediante la funcion == de la libreria fmt
		user.Password = "" // Se elimina la contraseña del usuario para no mostrarla en el token
		user.Role = "admin" // Se le asigna el rol de administrador al usuario

		token := GenerateJWT(user) // Se crea una variable token para almacenar el token generado

		result := models.ResponseToken{Token: token} // Se crea una variable result para almacenar el token generado

		jsonResult, err := json.Marshal(result) // Se codifica el token en formato json

		if err != nil {
			fmt.Println("No se pudo codificar el token") // Si no se pudo codificar el token se muestra un mensaje de error
		}

		w.WriteHeader(http.StatusOK) // Se le asigna un codigo de respuesta al usuario
		w.Header().Set("Content-Type", "application/json") // Se le asigna un tipo de contenido al usuario
		w.Write(jsonResult) // Se le envian los datos del token al usuario

	}else {
		w.WriteHeader(http.StatusUnauthorized) // Se le asigna un codigo de respuesta al usuario si no se pudo validar el token
		w.Header().Set("Content-Type", "application/json") // Se le asigna un tipo de contenido
		w.Write([]byte("Usuario o contraseña incorrectos")) // Se le envian el menasaje de error al usuario por las credenciales incorrectas
	}

}

func ValidateToken(w http.ResponseWriter, r *http.Request) { // Se crea la funcion ValidateToken para validar el token

	// request.OAuth2Extractor sirve para extraer el token de la peticion
	token, err := request.ParseFromRequestWithClaims(r, request.OAuth2Extractor, &models.Claims{}, func(token *jwt.Token) (interface{}, error) { // Se parsean los datos del token mediante la funcion ParseFromRequestWithClaims de la libreria request	
		return publicKey, nil // Se retorna la llave publica para validar el token en la funcion ParseFromRequestWithClaims de la libreria request
	})

	if err != nil {
		switch err.(type) { // Se valida el tipo de error
		case *jwt.ValidationError: // Si el error es de tipo ValidationError se muestra un mensaje de error
			vErr := err.(*jwt.ValidationError) // Se crea una variable vErr para almacenar el error de tipo ValidationError	
			switch vErr.Errors { // Se valida el error
			case jwt.ValidationErrorExpired: // Si el error es de tipo ValidationErrorExpired si se ha expirado el token muestra un mensaje de error
				w.WriteHeader(http.StatusUnauthorized) // Se le asigna un codigo de respuesta al usuario
				w.Header().Set("Content-Type", "application/json") // Se le asigna un tipo de contenido
				w.Write([]byte("El token ha expirado")) // Se le envian el mensaje de error al usuario

			case jwt.ValidationErrorSignatureInvalid: // Si el error es de tipo ValidationErrorSignatureInvalid si la firma del token es invalida muestra un mensaje de error
				w.WriteHeader(http.StatusUnauthorized) // Se le asigna un codigo de respuesta al usuario
				w.Header().Set("Content-Type", "application/json") // Se le asigna un tipo de contenido
				w.Write([]byte("La firma del token es invalida")) // Se le envian el mensaje de error al usuario

			default: // Si no se cumple ninguna de las condiciones anteriores se muestra un mensaje de error
				w.WriteHeader(http.StatusUnauthorized) // Se le asigna un codigo de respuesta al usuario
				w.Header().Set("Content-Type", "application/json") // Se le asigna un tipo de contenido
				w.Write([]byte("Token invalido")) // Se le envian el mensaje de error al usuario

			}
		
		default: // Si no se cumple ninguna de las condiciones anteriores se muestra un mensaje de error
			w.WriteHeader(http.StatusUnauthorized) // Se le asigna un codigo de respuesta al usuario
			w.Header().Set("Content-Type", "application/json") // Se le asigna un tipo de contenido
			w.Write([]byte("Token invalido")) // Se le envian el mensaje de error al usuario
		}
		
	}

	if token.Valid { // Si el token es valido
		w.WriteHeader(http.StatusAccepted) // Se le asigna un codigo de respuesta al usuario para indicar que el token es valido
		w.Header().Set("Content-Type", "application/json") // Se le asigna un tipo de contenido
		w.Write([]byte("Token valido")) // Se le envia un mensaje de confirmacion al usuario
	}else {
		w.WriteHeader(http.StatusUnauthorized) // Se le asigna un codigo de respuesta al usuario para indicar que el token es invalido
		w.Header().Set("Content-Type", "application/json") // Se le asigna un tipo de contenido
		w.Write([]byte("Token invalido")) // Se le envia un mensaje de error al usuario
	}

}

