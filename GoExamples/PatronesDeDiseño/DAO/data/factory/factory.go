package factory

import (
	i "dao/data/interfaces"
	"dao/data/sql"
	"log"
)
// Se crea una funcion de tipo factory que recibe un string con el nombre de la base de datos a utilizar
// y devuelve una instancia de la interfaz UserDAO
func FactoryDao(name string) i.UserDAO {
	switch name {
	case "mysql": // Si el nombre es mysql, se devuelve una instancia de la clase MysqlUserDAO
		return &sql.UserMySql{}
	default: // Si no se cumple ninguna de las condiciones anteriores, se devuelve un log de error y se devuelve un nil
		log.Fatalf("FactoryDao: %s not found", name)
		return nil 
	}
}
