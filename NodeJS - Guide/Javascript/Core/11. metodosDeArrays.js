// Métodos de arrays
// Un método de un array es una función que se puede ejecutar sobre un array y devuelve un valor o un array con el resultado de la operación.
// Los métodos de arrays son:
//

// Referencia: https://developer.mozilla.org/es/docs/Web/JavaScript/Referencia/Objetos_globales/Array

// IMPORTANTE: Los métodos de arrays se trabanjan sobre el array original. No se devuelve un nuevo array.

// Array
let frutas = ["🍎", "🍌", "🍓", "🍈"]; // Array de frutas

// Manipulación de arrays
console.log("\n ================ Manipulación de arrays ================ \n");

// .length -> Devuelve el número de elementos del array. Es decír la longitud del array.
let cantFrutas = frutas.length; // 4
console.log(" -> Cantidad de frutas: " + cantFrutas);

// .push(elemento) -> Agrega un valor al final del array.
frutas.push("🍐"); // Agrega un valor al final del array
console.log(" push -> Lista de frutas: " + frutas);

// .pop() -> Elimina el último elemento del array.
frutas.push(); // Elimina el último elemento del array
console.log(" push -> Lista de frutas: " + frutas); // No hay peras

// .shift() -> Elimina el primer elemento del array.
frutas.shift(); // Elimina el primer elemento del array
console.log(" shift -> Lista de frutas: " + frutas); // No hay manzanas

// .unshift(elemento) -> Agrega un valor al inicio del array.
frutas.unshift("🍇"); // Agrega un valor al inicio del array
console.log(" unshift -> Lista de frutas : " + frutas); // 🍇, 🍌, 🍓, 🍈

// .splice(indiceDeInicio, cantidadDeElementos) -> Elimina elementos del array.
frutas.splice(1, 1); // Elimina el elemento en la posición 1 (el segundo elemento)
console.log(" splice -> Lista de frutas: " + frutas); // 🍇, 🍓, 🍈

// .reverse() -> Invierte el orden de los elementos del array.
frutas.reverse(); // Invierte el orden de los elementos del array
console.log(" reverse -> Lista de frutas: " + frutas); // 🍈, 🍓, 🍇

// .sort() -> Ordena los elementos del array.
frutas.sort(); // Ordena los elementos del array
console.log(" sort -> Lista de frutas: " + frutas); // 🍇, 🍓, 🍈 // Ordena alfabéticamente o numéricamente según el valor del elemento, si es un emoji se ordena en su representación en Unicode.

// Si el array esta declarado como una constante, se modifica igualmente ya que almacena una referencia a la misma dirección de memoria.
const frutas2 = ["🍎", "🍌", "🍓", "🍈"];
frutas2.push("🍐");
console.log(" push -> Lista de frutas: " + frutas2); // 🍎, 🍌, 🍓, 🍈, 🍐




