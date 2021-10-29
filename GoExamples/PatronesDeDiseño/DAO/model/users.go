package model

// Se crea la estructura de usuario para ser usada en la base de datos

type User struct {
	Id        int64  
	FirstName string 
	LastName  string 
	Email     string
}
