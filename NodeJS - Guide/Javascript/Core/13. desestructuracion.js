// Desestructuración

// La desestructuración o destructuring es una forma de extraer valores de un objeto o un array de forma más sencilla.
//
// Sintaxis arrays:
// let nombreArray = [valor1, valor2, valor3, ...];
// let [valor1, valor2, valor3, ...] = nombreArray;

// Ejemplo:
console.log("\n============ Desestructuración =============\n")

console.log("======== Arrays ========\n")

let frutas = ["🍎", "🍌", "🍓", "🍇"]; // Array de frutas
console.log(" -> Lista de frutas: " + frutas + "\n");

// Desestructuración
let [manzana, banana, fresa, uva] = frutas;

console.log(" -> manzana: " + manzana);
console.log(" -> banana: " + banana);
console.log(" -> fresa: " + fresa);
console.log(" -> uva: " + uva);

// Si no se desea un valor en particular, se puede omitir el índice que se encuentra entre corchetes.
//
// Ejemplo:
let frutas2 = ["🍊", "🥝", "🍒"];
let [naranja, , cereza] = frutas2; // El valor del kiwi no se utiliza

console.log(" -> Lista de frutas 2: " + frutas2 + "\n");
console.log(" -> naranja: " + naranja);
console.log(" -> cereza: " + cereza);

// Si se desea extrer un solo valor o mas y el resto de los valores extraerlos en otro array, se puede hacer de la siguiente manera:
let [manzana2, bannana2, ...otrasFrutas] = frutas; // ... es un operador de propagación de valores (spread operator) 
console.log("\n -> manzana: " + manzana2);
console.log(" -> bannana: " + bannana2);
console.log(" -> otrasFrutas: " + otrasFrutas);

// Sintaxis objetos:
// let nombreObjeto = {
//     propiedad1: valor1,
//     propiedad2: valor2,
// }
// let {propiedad1, propiedad2, ...} = nombreObjeto;

// Ejemplo:
console.log("\n======== Objetos ========\n")

let persona = {
    nombre: "Juan",
    apellido: "Perez",
    edad: 25
}

// Desestructuración
let {nombre, apellido, edad} = persona;

console.log(" -> Persona: " + persona + "\n");

console.log(" -> nombre: " + nombre);
console.log(" -> apellido: " + apellido);
console.log(" -> edad: " + edad);

// Si se desea extraer un valor de un objeto y el resto de los valores extraerlos en otro objeto, se puede hacer de la siguiente manera:
let {nombre: nombre2, ...otrosDatos} = persona;

console.log("\n -> Nombre: " + nombre2);
console.log(" -> Otros datos: " + otrosDatos);




