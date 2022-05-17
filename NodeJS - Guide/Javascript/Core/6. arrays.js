// Arrays

// Un array es una colección de datos unidimensionales que se pueden acceder mediante un índice numérico.
// Para declarar un array se utiliza las llaves [] y se separan los valores con comas.
//
// Sintaxis:
// let nombreArray = [valor1, valor2, valor3, ...];
//
// Ejemplo:
console.log("\n============ Arrays =============\n")

let frutas = ["🍎", "🍌", "🍓", "🍈"]; // Array de frutas

// Para acceder a un valor del array se utiliza el índice que se encuentra entre corchetes. Cero es el primer valor del array.
console.log(" -> Lista de frutas: " + frutas);
console.log(" -> Esta es una manzana " + frutas[0]); // 🍎

// Agregar un valor al array:
// Un array puede ser modificado, es decir, se puede agregar o eliminar valores.
//
// Sintaxis:
// nombreArray[índice] = valor; // Para agregar un valor al array o modificarlo si ya existe
//
// Ejemplo: 
console.log("\n============ Operaciones con arrays =============\n")

console.log("======== Suma ========\n")
console.log(" -> Lista de frutas: " + frutas);
frutas[4] = "🍊"; // Agregar un valor al array en la posición 4 (El indice 4 no existia, por lo tanto se agrega un valor, si ya existía, se modifica)
console.log(" -> Agregar una naranja: " + frutas); 

// Matrices

// Una matriz es una colección de datos bidireccionales que se pueden acceder mediante dos indices numericos.
// Para declarar una matriz se utiliza las llaves [[], [], ...] y se separan cada fila con comas.
//
// Sintaxis:
// let nombreMatriz = [
//     [valor1, valor2, valor3, ...],
//     [valor1, valor2, valor3, ...],
//     [valor1, valor2, valor3, ...],
//     ...
// ];
// 
// Ejemplo:
console.log("\n============ Matrices =============\n")

let datos = [
    ["Nombre", "Apellido", "Edad"],
    ["Juan", "Perez", "30"],
    ["Pedro", "Gonzalez", "25"],
    ["Maria", "Lopez", "20"]
];

console.log(" -> Lista de datos: " + datos);
// Para acceder a un valor de la matriz se utiliza los indices que se encuentran entre corchetes.
console.log(" -> Nombre de Juan: " + datos[1][0]); // Juan

// Añadiendo una fila a la matriz
console.log("\n======== Añadiendo una fila a la matriz ========\n")
datos[4] = ["Jorge", "Gonzalez", "35"];
console.log(" -> Lista de datos con un nuevo dato: " + datos);






