package mensaje

// Las funciones son privadas cuando comienzan con la primera letra mayúscula.
// Las funciones son públicas cuando comienzan con la primera letra minúscula.

// Las funciones privadas sólo pueden ser llamadas desde dentro de su paquete.
func funcionPrivada() string {
	return "Hola, soy un paquete mensaje privada"
}

// Las funciones públicas pueden ser llamadas desde cualquier parte del programa.
func FuncionPublica() string {
	return "Hola, soy un paquete mensaje publico"
}
