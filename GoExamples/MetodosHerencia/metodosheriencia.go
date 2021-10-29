package main

import "fmt"

// Ejemplo de una estructura
type Persona struct {
	Nombre string
	Edad   int
}

// Ejemplo de un método en una estructura
func (p Persona) Saludar() {
	fmt.Println("Hola, soy", p.Nombre, "y tengo", p.Edad, "años")
}

// Herencia
// Ejemplo de una estructura con herencia
type Empleado struct {
	Persona // Atributo heredado
	Cargo string 
}

func main() {
	
	// Declaramos una variable de tipo Persona
	var p Persona
	
	// Asignamos valores a los campos
	p.Nombre = "Nelson"
	p.Edad = 25
	
	// Llamamos al método
	p.Saludar()

	// Declaramos una variable de tipo Empleado
	var e Empleado

	// Asignamos valores a los campos
	e.Nombre = "Nelson"
	e.Edad = 25
	e.Cargo = "Programador"

	// Llamamos al método
	e.Saludar()


}