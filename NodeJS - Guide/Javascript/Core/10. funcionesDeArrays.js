// Funciones de arrays
// Las funciones de los arrays permiten manipular los elementos del array mediante una función o callback que se pasa como parámetro y devuelve un valor o un array con el resultado de la operación.
// IMPORTANTE: Las funciones de arrays no modifican el array original.

// Referencia: https://developer.mozilla.org/es/docs/Web/JavaScript/Referencia/Objetos_globales/Array

console.log("\n ================ Funciones de arrays ================ \n");

// Array
let frutas = ["🍎", "🍌", "🍓", "🍈"]; // Array de frutas

// .forEach(callback) -> Ejecuta una función para cada elemento del array. Es similar a un for or, pero no devuelve ningún valor.
console.log(" \n ==== .forEach ====");
frutas.forEach((fruta, indice) => {
    console.log(" " + indice + " -> " + fruta);
});

// .map(callback) -> Devuelve un nuevo array con el resultado de ejecutar una función para cada elemento del array.
console.log(" \n ==== .map ====");
let nuevoArray2 = frutas.map((fruta) => {
    return "🧺" + fruta; // Por cada elemento del array de frutas, se añade una 🧺 al principio de cada elemento.
}); // NuevoArray2 es un nuevo array con los resultados de la función map // 🧺🍎, 🧺🍌, 🧺🍓, 🧺🍈
console.log(" -> Nuevo array map: " + nuevoArray2);

// .filter(callback) -> Devuelve un nuevo array con los elementos del array que cumplen la condición.
console.log(" \n ==== .filter ====");
let nuevoArray3 = frutas.filter((fruta) => {
    return fruta === "🍈"; // Devuelve un nuevo array con los elementos del array que cumplen la condición.
}) // NuevoArray3 es un nuevo array con los resultados de la función filter // 🍈
console.log(" -> Nuevo array filter: " + nuevoArray3);

// .reduce(callback) -> Devuelve un valor o un array con el resultado de la operación.
console.log(" \n ==== .reduce ====");
let nuevoArray4 = frutas.reduce((acumulador, fruta) => {
    return acumulador + fruta; // Devuelve un valor o un array con el resultado de la operación.
}) // NuevoArray4 es un nuevo array con los resultados de la función reduce // En este caso, el resultado es un string con todas las frutas concatenadas.
console.log(" -> Nuevo array reduce: " + nuevoArray4);

// .join(separador) -> Devuelve una cadena con los elementos del array separados por un separador.
console.log(" \n ==== .join ====");
let cadenaFrutas = frutas.join(" - "); // Devuelve una cadena con los elementos del array separados por un separador
console.log(" -> Lista de frutas: " + cadenaFrutas); //  "🍎 - 🍌 - 🍓 - 🍈" Esto es un String

// .concat(array) -> Devuelve un nuevo array con los elementos del array concatenados con los elementos del array pasado como parámetro.
console.log(" \n ==== .concat ====");
let nuevoArray = frutas.concat(["🍒", "🍍"]); // Devuelve un nuevo array con los elementos del array concatenados con los elementos del array pasado como parámetro
console.log(" -> Lista de frutas: " + nuevoArray); // 🍎,🍌,🍓,🍈,🍒,🍍

// .indexOf(elemento) -> Devuelve el índice del primer elemento del array que coincida con el elemento pasado como parámetro.
console.log(" \n ==== .indexOf ====");
let indiceFruta = frutas.indexOf("🍈"); // Devuelve el índice del primer elemento del array que coincida con el elemento pasado como parámetro
console.log(" -> Índice de la fruta 🍈: " + indiceFruta); // 3

// .lastIndexOf(elemento) -> Devuelve el índice del último elemento del array que coincida con el elemento pasado como parámetro.
console.log(" \n ==== .lastIndexOf ====");
let indiceFruta2 = frutas.lastIndexOf("🍈"); // Devuelve el índice del último elemento del array que coincida con el elemento pasado como parámetro
console.log(" -> Índice de la fruta 🍈: " + indiceFruta2); // 3