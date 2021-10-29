package main

var (
	a int
)

func main() {
	// Condicionales
	// Go tiene una sintaxis sencilla para realizar condicionales,
	// la cual se compone de una expresión booleana y dos expresiones
	// que se evalúan en consecuencia.
	//
	// La expresión booleana se evalúa en el momento de la declaración
	// de la condicional.
	//
	// La expresión booleana puede ser una constante, una variable,
	// una función, una expresión aritmética, etc.

	a = 5

	if (a == 1) {
		println("a es igual a 1")
	} else if (a == 2) {
		println("a es igual a 2")
	} else {
		println("a no son iguales a 1 ni a 2")
	}
		
	// Determinar si la variable es par o impar

	if (a % 2 == 0) {
		println("a es par")
	} else {
		println("a es impar")
	}

	// Switch

	switch a {
	case 1:
		println("a es igual a 1")
	case 2:
		println("a es igual a 2")
	default:
		println("a no son iguales a 1 ni a 2")	
	}

}