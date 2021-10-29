package sql

import (
	m "dao/model"
)

// Se crea una estructura para el modelo de usuario con MySql y se le implementan los metodos de la interfaz
type UserMySql struct {}

// Se crea una función para Crear un usuario con MySql
func (dao *UserMySql) Create(user *m.User) error {

	query := "INSERT INTO user (first_name, last_name, email) VALUES (?, ?, ?)" // Se crea la query para insertar un usuario
	db := get() // Se crea una conexión a la base de datos
	defer db.Close() // Se cierra la conexión a la base de datos

	stm, err := db.Prepare(query) // Se prepara la query

	if err != nil {
		return err // Si hay un error se retorna el error
	}

	defer stm.Close() // Se cierra el statement de la query

	data, err := stm.Exec(user.FirstName, user.LastName, user.Email) // Se ejecuta la query con los datos del usuario que se quiere insertar en la base de datos y se guarda el resultado en data

	if err != nil {
		return err // Si hay un error se retorna el error
	}

	id, err := data.LastInsertId() // Se obtiene el id del usuario que se inserto en la base de datos y se guarda en id

	if err != nil {
		return err // Si hay un error se retorna el error
	}

	user.Id = id // Se le asigna el id al usuario que se inserto en la base de datos
	return nil // Se retorna un nil si no hay error

}

// Se crea una función para obtener un listado de usuarios con MySql
func (dao *UserMySql) GetAll() ([]m.User, error) {

	query := "SELECT * FROM user" // Se crea la query para obtener un listado de usuarios
	db := get() // Se crea una conexión a la base de datos
	defer db.Close() // Se cierra la conexión a la base de datos

	rows, err := db.Query(query) // Se ejecuta la query y se guarda el resultado en rows

	if err != nil { 
		return nil, err // Si hay un error se retorna el error y el nil para el listado de usuarios
	}

	defer rows.Close() // Se cierra el resultado de la query

	users := []m.User{} // Se crea una lista de usuarios mediante un slice para guardar los usuarios que se obtienen de la base de datos

	for rows.Next() { // Se recorre el resultado de la query
		user := m.User{} // Se crea un usuario para guardar los datos del usuario que se obtiene de la base de datos
		err := rows.Scan(&user.Id, &user.FirstName, &user.LastName, &user.Email) // Se obtienen los datos del usuario que se obtiene de la base de datos y se guardan en user

		if err != nil {
			return nil, err // Si hay un error se retorna el error y el nil para el listado de usuarios
		}

		users = append(users, user) // Se agrega el usuario que se obtiene de la base de datos a la lista de usuarios
	}

	return users, nil // Se retorna el listado de usuarios y un nil si no hay error

}
