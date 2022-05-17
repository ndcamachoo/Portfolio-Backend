// Promesas
// Una promesa es un objeto que representa la terminación o el fracaso de una operación.
// Una promesa se puede resolver o rechazar.
//
// Para resolver una promesa, se utiliza la palabra reservada "resolve" y para rechazarla, se utiliza la palabra reservada "reject".
//
// Sintaxis:
// new Promise((resolve, reject) => {
//     // Código
// });

// Ejemplo:
console.log("\n============ Promesas =============\n")

const promesa = new Promise((resolve, reject) => {
    setTimeout(() => {  // Función de retardo de ejemplo. Puede demorar el tiempo necesario.
        resolve("Promesa resuelta! ♥"); // Resuelve la promesa. Retorna una cadena de texto.
    }, 1000); // Espera 1 segundo para resolver la promesa.
});

//console.log(typeof promesa); // Muestra la promesa. Esta función no se ejecuta, la variable promesa es un objeto de tipo promesa.
//console.log(promesa);  // La promesa al ejecutarse

// Resolver una promesa
// Para resolver una promesa, la función se ejecutará normalmente hasta que encuentre la función resolve y ejecute su contenido. 
// Si por alguna razón la función falla y ejecuta un reject, la promesa se rechaza.

// Para resolver una promesa se utiliza un método llamado "then".
// Sintaxis:
// promesa.then((resolve) => {
//     // Código
// });

// En caso de que la promesa se rechace, se utiliza un método llamado "catch".
// Sintaxis:
// promesa.catch((reject) => {
//     // Código
// });

// Ejecución de la promesa
//promesa.then((resolve) => {
//    console.log(resolve); // Muestra la cadena de texto. Se ejecuta si la promesa se resuelve.
//}).catch((reject) => {
//    console.log(reject); // Muestra la cadena de texto. En este ejemplo no seleccionamos un caso para rechazar la promesa, por lo tanto nunca se ejecutará el catch.
//});


// Ejemplo 2:
const promesa2 = (numeroPar) => {
    return new Promise((resolve, reject) => {
        if (numeroPar % 2 === 0) {
            resolve("El número es par!"); // Resolvemos la promesa si el numero ingresado es par.
        } else {
            reject("El número es impar!"); // Rechazamos la promesa si el numero ingresado es impar.
        }
    });
}

// Ejecución de la promesa
promesa2(1).then((resolve) => {
    console.log(resolve); // Muestra la cadena de texto. Se ejecuta si la promesa se resuelve.
}).catch((reject) => {
    console.log(reject); // Muestra la cadena de texto. En este ejemplo no seleccionamos un caso para rechazar la promesa, por lo tanto nunca se ejecutará el catch.
});





