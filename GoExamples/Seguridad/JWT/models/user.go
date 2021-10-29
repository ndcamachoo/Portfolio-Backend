package models

// Se crea la estructura de usuario con sus atributos que se utilizarán en el payload del token
type User struct {
	Name     string `json:"name"`
	Password string `json:"password,omitempty"`
	Role     string `json:"role"`
}
