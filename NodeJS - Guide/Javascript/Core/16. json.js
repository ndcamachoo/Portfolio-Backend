// JSON
// JSON es un estandar para la serialización y deserialización de objetos en cualquier lenguaje de programación.
// Se utiliza para enviar datos entre aplicaciones de una manerá entendible para cualquier lenguaje.
// JSON significa JavaScript Object Notation y se basa en la sintaxis de objetos de JavaScript.

// Un Objeto se representa como por ejemplo:
// let objeto = {
//     nombre: "Juan",
//     apellido: "Perez",
//     edad: 30
// }

// Una representación JSON de este objeto es:
// "{
//     "nombre": "Juan",
//     "apellido": "Perez",
//     "edad": 30
// }"

// Todas las propiedades de un objeto (nombre, apellido, edad) se representan como cadenas de texto entre comillas dobles y los valores igualmente a excepción de valores numericos, objetos, arreglos y valores booleanos.
// Las propiedades pueden ser de cualquier tipo de dato, incluyendo objetos y arreglos excepto funciones.
// La representación JSON en una cadena de texto (String)
// Se puede representar de manera manual el objeto

let objeto = {
    nombre: "Juan",
    apellido: "Perez",
    edad: 30
}

let objetoJSON = '{"nombre":"Juan","apellido":Perez","edad":30}';

console.log("\n============ JSON =============\n")

console.log("Objeto: " + objeto);
console.log("Tipo del Objeto: " + typeof objeto);

console.log("\nObjeto JSON: " + objetoJSON);
console.log("Tipo del Objeto JSON: " + typeof objetoJSON);

// Si el objeto contiene muchas propiedades, la representación JSON de manera manual puede ser muy larga y exausiva.
// Para evitar esto, se puede utilizar el método JSON.stringify() que convierte un objeto en una cadena de texto JSON.

console.log("\nObjeto JSON con método stringify(): " + JSON.stringify(objeto));
console.log("Tipo del Objeto JSON con método stringify(): " + typeof JSON.stringify(objeto));

// Transformación de JSON a un objeto
// Para transformar una cadena de texto JSON a un objeto, se utiliza el método JSON.parse() que convierte una cadena de texto JSON en un objeto.
// La cadena de texto JSON debe ser una cadena de texto que contiene un objeto JSON válido.

// Suponiendo que el objeto JSON que escribimos manualmente, entonces el objeto resultante de la transformación es:
let objetoJSON2 = '{"nombre":"Juan","apellido":"Perez","edad":30}';

console.log("\nObjeto JSON2: " + objetoJSON2);
console.log("Tipo del Objeto JSON2: " + typeof objetoJSON2);

console.log("\nObjeto JSON2 con método parse(): " + JSON.parse(objetoJSON2)); 
console.log("Tipo del Objeto JSON2 con método parse(): " + typeof JSON.parse(objetoJSON2)); // El tipo del objeto es "object" Vuelve a ser un objeto de JavaScript