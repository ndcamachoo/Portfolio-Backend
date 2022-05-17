// Funciones
// Una función es un conjunto de instrucciones que se pueden ejecutar en cualquier momento.
// Una función puede recibir parámetros y devolver valores.
// Se pueden crear funciones sin nombre, pero es recomendable tener un nombre descriptivo.
// Se declaran con la palabra reservada function.

// Sintaxis:
// function nombreFuncion(parametros) {
//     // Instrucciones
//     return valor;
// }

// Ejemplo:
console.log("============ Funciones =============\n")

// Función con parámetros:
function sumar(numero1, numero2) {
     return numero1 + numero2; // Devuelve la suma de los parámetros (Number)
}

console.log(" -> Sumar: numero 1: " + 10 + " + numero 2: " + 20 + " es igual: " + sumar(10, 20));

// Función sin parámetros:
function saludar() {
    return "Hola Mundo!"; // Devuelve un valor (String)
}

console.log(" -> Saludar: " + saludar());

// Función sin parámetros y sin devolver valor:
function despedir() {
    console.log(" -> Adiós Mundo!"); // No devuelve ningún valor, solo imprime en consola
}

despedir();

// Guardar función en una variable:
let resta = function(numero1, numero2) {
    return numero1 - numero2;
}

console.log(" -> Restar: numero 1: " + 10 + " - numero 2: " + 20 + " es igual: " + resta(10, 20));

// Arrow Functions
// Una función flecha es una función anónima que se puede utilizar como una función normal.
// Se puede utilizar para reducir el código y evitar el uso de paréntesis.
// Se declaran con la palabra reservada =>

// Sintaxis:
// (parametros) => {
//     // Instrucciones
//     return valor;
// }

// Si la función solo tiene una línea de instrucciones, se puede escribir de la siguiente manera:
// (parametros) => valor;

// Ejemplo:
console.log("\n============ Funciones flecha =============\n")

// Función con parámetros y devolver valor:
let multiplicación = (numero1, numero2) => {
    return numero1 * numero2;
}
console.log(" -> Multiplicación: numero 1: " + 10 + " * numero 2: " + 20 + " es igual: " + multiplicación(10, 20));

// Función de una línea de instrucciones:
let división = (numero1, numero2) => numero1 / numero2;
console.log(" -> División: numero 1: " + 10 + " / numero 2: " + 20 + " es igual: " + división(10, 20));

// Si la función no tiene parámetros, se puede escribir de la siguiente manera: (Por convención, se utiliza un paréntesis vacío)
let potencia = () => {
    return 10 ** 2;
}
console.log(" -> Potencia: 10 ^ 2 es igual: " + potencia());

// Parámetros por defecto:
// Se pueden definir parámetros por defecto en la función. Esto permite que los parámetros no sean obligatorios en la llamada de la función.

// Sintaxis:
// (parametros = valorPorDefecto) => {
//     // Instrucciones
//     return valor;
// }

// Ejemplo:
console.log("\n============ Parámetros por defecto =============\n")

const saludarDefecto = (nombre = "Anónimo") => {
    return "Hola " + nombre;
}

console.log(" -> Saludar: " + saludarDefecto("Juan")); 
console.log(" -> Saludar: " + saludarDefecto()) // Saludar con nombre por defecto, como no se ingresa ningún parámetro, se utiliza el nombre por defecto "Anónimo"

// Spread Operator
// El operador spread (...) permite expandir una colección de elementos en una nueva colección.
// Se puede utilizar para convertir elementos en un array o un objeto.

// Sintaxis:
// function nombreFuncion(...muchosParametros) {
//     // Instrucciones
//     return valor;
// }

// Ejemplo:
console.log("\n============ Spread Operator =============\n")

let contarNumeros = (...numeros) => {
    return numeros.length;
}

console.log(" -> Contar numeros: " + contarNumeros(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));



