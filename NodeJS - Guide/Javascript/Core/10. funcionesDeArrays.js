// Funciones de arrays
// Las funciones de los arrays permiten manipular los elementos del array mediante una funci贸n o callback que se pasa como par谩metro y devuelve un valor o un array con el resultado de la operaci贸n.
// IMPORTANTE: Las funciones de arrays no modifican el array original.

// Referencia: https://developer.mozilla.org/es/docs/Web/JavaScript/Referencia/Objetos_globales/Array

console.log("\n ================ Funciones de arrays ================ \n");

// Array
let frutas = ["馃崕", "馃崒", "馃崜", "馃崍"]; // Array de frutas

// .forEach(callback) -> Ejecuta una funci贸n para cada elemento del array. Es similar a un for or, pero no devuelve ning煤n valor.
console.log(" \n ==== .forEach ====");
frutas.forEach((fruta, indice) => {
    console.log(" " + indice + " -> " + fruta);
});

// .map(callback) -> Devuelve un nuevo array con el resultado de ejecutar una funci贸n para cada elemento del array.
console.log(" \n ==== .map ====");
let nuevoArray2 = frutas.map((fruta) => {
    return "馃Ш" + fruta; // Por cada elemento del array de frutas, se a帽ade una 馃Ш al principio de cada elemento.
}); // NuevoArray2 es un nuevo array con los resultados de la funci贸n map // 馃Ш馃崕, 馃Ш馃崒, 馃Ш馃崜, 馃Ш馃崍
console.log(" -> Nuevo array map: " + nuevoArray2);

// .filter(callback) -> Devuelve un nuevo array con los elementos del array que cumplen la condici贸n.
console.log(" \n ==== .filter ====");
let nuevoArray3 = frutas.filter((fruta) => {
    return fruta === "馃崍"; // Devuelve un nuevo array con los elementos del array que cumplen la condici贸n.
}) // NuevoArray3 es un nuevo array con los resultados de la funci贸n filter // 馃崍
console.log(" -> Nuevo array filter: " + nuevoArray3);

// .reduce(callback) -> Devuelve un valor o un array con el resultado de la operaci贸n.
console.log(" \n ==== .reduce ====");
let nuevoArray4 = frutas.reduce((acumulador, fruta) => {
    return acumulador + fruta; // Devuelve un valor o un array con el resultado de la operaci贸n.
}) // NuevoArray4 es un nuevo array con los resultados de la funci贸n reduce // En este caso, el resultado es un string con todas las frutas concatenadas.
console.log(" -> Nuevo array reduce: " + nuevoArray4);

// .join(separador) -> Devuelve una cadena con los elementos del array separados por un separador.
console.log(" \n ==== .join ====");
let cadenaFrutas = frutas.join(" - "); // Devuelve una cadena con los elementos del array separados por un separador
console.log(" -> Lista de frutas: " + cadenaFrutas); //  "馃崕 - 馃崒 - 馃崜 - 馃崍" Esto es un String

// .concat(array) -> Devuelve un nuevo array con los elementos del array concatenados con los elementos del array pasado como par谩metro.
console.log(" \n ==== .concat ====");
let nuevoArray = frutas.concat(["馃崚", "馃崓"]); // Devuelve un nuevo array con los elementos del array concatenados con los elementos del array pasado como par谩metro
console.log(" -> Lista de frutas: " + nuevoArray); // 馃崕,馃崒,馃崜,馃崍,馃崚,馃崓

// .indexOf(elemento) -> Devuelve el 铆ndice del primer elemento del array que coincida con el elemento pasado como par谩metro.
console.log(" \n ==== .indexOf ====");
let indiceFruta = frutas.indexOf("馃崍"); // Devuelve el 铆ndice del primer elemento del array que coincida con el elemento pasado como par谩metro
console.log(" -> 脥ndice de la fruta 馃崍: " + indiceFruta); // 3

// .lastIndexOf(elemento) -> Devuelve el 铆ndice del 煤ltimo elemento del array que coincida con el elemento pasado como par谩metro.
console.log(" \n ==== .lastIndexOf ====");
let indiceFruta2 = frutas.lastIndexOf("馃崍"); // Devuelve el 铆ndice del 煤ltimo elemento del array que coincida con el elemento pasado como par谩metro
console.log(" -> 脥ndice de la fruta 馃崍: " + indiceFruta2); // 3