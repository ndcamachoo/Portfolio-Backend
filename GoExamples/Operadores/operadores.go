package main

var (
	a, b int
)


func main() {
	
	// Crear una calculadora con los operadores +, -, * y /

	// Sumar
	a = 1
	b = 2
	c := a + b
	println(c)

	// Restar
	c = a - b
	println(c)

	// Multiplicar
	c = a * b
	println(c)

	// Dividir
	c = a / b
	println(c)


	// Operadores de comparación

	// Igual
	d := a == b
	println(d)

	// Distinto
	d = a != b
	println(d)

	// Menor que
	d = a < b
	println(d)

	// Mayor que
	d = a > b
	println(d)

	// Menor o igual que
	d = a <= b
	println(d)

	// Mayor o igual que
	d = a >= b
	println(d)

	// Operadores lógicos

	// Y
	//d = a && b

	// O
	//d = a || b

	// Negación
	//d = !a

	// Operadores de asignación

	// Asignación
	a = 1
	b = 2
	c = a + b
	println(c)

	// Suma y asignación
	a += b
	println(a)

	// Resta y asignación
	a -= b

	// Multiplicación y asignación
	a *= b

	// División y asignación
	a /= b

	// Operadores de incremento y decremento
	
	// Incremento
	a = 1
	a++

	// Decremento
	a = 1
	a--

}