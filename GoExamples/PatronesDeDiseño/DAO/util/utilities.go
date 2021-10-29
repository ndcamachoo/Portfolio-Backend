package util

import (
	c "dao/config"
	"encoding/json"
	"os"
)


// Se crea una función para mapear el archivo de configuración a una estructura de datos
func GetConfig() (c.Configuration, error) {
	config := c.Configuration{} 	// Se crea una variable de tipo Configuration para almacenar los datos del archivo de configuración
	file, err := os.Open("./config/configuration.json") // Se abre el archivo de configuración utilizando la función Open del paquete os

	if err != nil {
		return c.Configuration{}, err // Se retorna un error si no se pudo abrir el archivo
	}
	defer file.Close() // Se cierra el archivo después de que se haya leído el archivo

	decoder := json.NewDecoder(file) // Se crea un nuevo decoder para leer el archivo de configuración

	err = decoder.Decode(&config) // Se decodifica el archivo de configuración y se guarda en la variable config

	if err != nil {
		return c.Configuration{}, err 	// Se retorna un error si no se pudo decodificar el archivo
	}

	return config, nil // Se retorna la configuración leída

}