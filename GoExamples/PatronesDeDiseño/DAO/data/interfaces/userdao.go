package interfaces

import (
	m "dao/model"
)

// Se crea una interfaz para el manejo de usuarios en la base de datos y sus funciones

type UserDAO interface {
	Create(u *m.User) error
	//Update(u *m.User) error
	//Delete(id int) error
	//GetById(id int) (m.User, error)
	GetAll() ([]m.User, error)
}