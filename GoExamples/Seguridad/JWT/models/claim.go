package models

import (
	jwt "github.com/golang-jwt/jwt/v4" // Se importa el paquete jwt
)

// Se crean los claims para el token de autenticación según el estándar JWT
type Claims struct {
	User    `json:"user"`
	jwt.StandardClaims  // Esta estructura contiene los campos requeridos por el estándar JWT
}
