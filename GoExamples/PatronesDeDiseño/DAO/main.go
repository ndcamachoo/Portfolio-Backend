package main

import (
	"dao/data/factory"
	"dao/model"
	"dao/util"
	"fmt"
	"log"
)

func main() {

	// Patrón de diseño DAO
	// DAO: Data Access Object

	// El patron de diseño DAO es una estructura de datos que permite acceder a los datos de una base de datos y realizar operaciones CRUD.

	config, err := util.GetConfig() // Obtenemos la configuración de la base de datos

	if err != nil {
		log.Fatal(err) // Si hay un error, terminamos el programa con un mensaje de error
	}

	userDAO := factory.FactoryDao(config.Engine) // Creamos una instancia de la interfaz DAO que nos permite acceder a la base de datos especificada el archivo de configuración 

	user := model.User{ // Creamos una instancia de la estructura User
		Id:        1, // El id del usuario
		FirstName: "Nelson", // El nombre del usuario 
		LastName:  "Camacho", // El apellido del usuario
		Email:     "nel@test.com", // El correo del usuario
	}

	err = userDAO.Create(&user) // Creamos el usuario en la base de datos utilizando el método Create de la interfaz DAO

	if err != nil {
		log.Fatal(err) // Si hay un error, terminamos el programa con un mensaje de error
	}	

	fmt.Println(user) // Imprimimos el usuario creado

}
