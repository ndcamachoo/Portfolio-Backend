package config

// Se crea la estructura de configuracion para la base de datos

type Configuration struct {
	Engine string 
	Server string 
	Port   int 
	User   string 
	Pass   string 
	DBName string 
}