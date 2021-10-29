package models

// Se crea el token de respuesta para el usuario que se loguea en el sistema
type ResponseToken struct {
	Token  string `json:"token"`
}