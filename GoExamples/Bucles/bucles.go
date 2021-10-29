package main

import "fmt"

var i int = 0

func main() {

	// En Go no existe el bucle while, pero si existe el bucle for.
	// El bucle for es una estructura de control que se ejecuta una vez
	// por cada elemento de una secuencia de valores.
	// El bucle for se puede construir de la siguiente manera:
	// for condicion {
	// 	instrucciones
	// }
	// La condicion se evalua una vez al comienzo del bucle.
	// Si la condicion es verdadera, se ejecutan las instrucciones.
	// Si la condicion es falsa, el bucle se termina.
	// La condicion puede ser una expresion o una sentencia.
	// La condicion puede ser una expresion booleana o una sentencia booleana.

	// Ejemplo:
	for i := 0; i < 10; i++ {
		fmt.Println(i)
	}

	fmt.Println("")

	// Ejemplo:
	for i < 10 {
		fmt.Println(i)
		i++
	}

	fmt.Println("")

	// Break
	// El break permite salir de un bucle.
	// El break se puede utilizar dentro de un bucle for o un bucle switch.
	
	// Ejemplo:
	for i := 0; i < 10; i++ {
		if i == 5 {
			fmt.Println("I es igual a 5")
			break
		}
		fmt.Println(i)

	}

	fmt.Println("")
	
	// Continue
	// El continue permite continuar con la ejecucion del bucle.
	// El continue se puede utilizar dentro de un bucle for o un bucle switch.
	// El continue se ejecuta la siguiente iteracion del bucle.

	// Ejemplo:
	for i := 0; i < 10; i++ {
		if i == 5 {
			fmt.Println("I es igual a 5")
			continue
		}
		fmt.Println(i)

	}

}