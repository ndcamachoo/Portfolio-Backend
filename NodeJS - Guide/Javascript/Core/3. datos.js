// Tipos de datos
// Los datos se pueden almacenar en una variable pueden ser:
// 1. Números (Number)
// 2. Cadenas de texto (String)
// 3. Booleanos (Boolean)
// 4. Arreglos (Array)
// 5. Objetos (Object)
// 6. Funciones (Function)
// 7. Null
// 8. Undefined
// 9. NaN

// Ejemplos:
// 1. Números
let numero = 10;
console.log("El valor de la variable numero es: " + numero + " y su tipo es: " + typeof numero);

// 2. Cadenas de texto
let cadena = "Hola mundo";
console.log("El valor de la variable cadena es: " + cadena + " y su tipo es: " + typeof cadena);

// 3. Booleanos
let booleano = true; // Los booleanos pueden ser true o false, 1 o 0 respectivamente
console.log("El valor de la variable booleano es: " + booleano + " y su tipo es: " + typeof booleano);

// 4. Arreglos
let arreglo = [1, 2, 3, 4, 5];
console.log("El valor de la variable arreglo es: " + arreglo + " y su tipo es: " + typeof arreglo);

// 5. Objetos
let objeto = {
    nombre: "Ana",
    apellido: "Galarza",
}
console.log("El valor de la variable objeto es: " + objeto + " y su tipo es: " + typeof objeto);

// 6. Funciones
let funcion = function(){
    console.log("Hola mundo");
}
console.log("El valor de la variable funcion es: " + funcion + " y su tipo es: " + typeof funcion);

// 7. Null
let nulo = null; // Los valores null y undefined son valores especiales que no tienen valor. Null es una variable que no tiene valor, undefined es un valor que no existe.
console.log("El valor de la variable nulo es: " + nulo + " y su tipo es: " + typeof nulo); // https://developer.mozilla.org/es/docs/Web/JavaScript/Reference/Global_Objects/null#diferencias_entre_null_y_undefined

// 8. Undefined
let indefinido;
console.log("El valor de la variable indefinido es: " + indefinido + " y su tipo es: " + typeof indefinido);

// 9. NaN
// El valor NaN es un valor especial que no se puede convertir a un número. NaN se representa con el *número* "Not a Number".
let nan = "Hola mundo";
nan = parseInt(nan); // El valor de la variable nan es NaN, porque la cadena "Hola mundo" no se puede convertir a un número
console.log("El valor de la variable nan es: " + nan + " y su tipo es: " + typeof nan);






