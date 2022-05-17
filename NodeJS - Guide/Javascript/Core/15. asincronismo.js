// Asincronismo
// Un Script o un archivo de codigo que se ejecuta sea en NodeJS o en el navegador, es sincrono. 
// Es decir, que se ejecuta el código de forma secuencial (Linea por linea y espera a que cada linea se ejecute para poder ejecutar la siguiente).
// En algunos casos, es necesario que el código se ejecute en paralelo, es decir que no se espera a que se ejecute una línea antes de ejecutar la siguiente.
// Como por ejemplo, una petición HTTP, es decir, una petición a un servidor web. 

// Para ejecutar código en paralelo, se utiliza la palabra reservada "async" y "await".
// Async es una palabra reservada que indica que la función es asincrona.
// Await es una palabra reservada que indica que la función espera a que se ejecute una función asincrona.

// Sintaxis:
// async function nombreFuncion() {
//     // Código
// }

// await nombreFuncion();

// Ejemplo:
console.log("\n============ Asincronismo =============\n")

// Proceso sincrono

console.log(" -> Proceso sincrono: Comenzando...");
console.log(" -> Proceso sincrono: Procesando...");
console.log(" -> Proceso sincrono: Terminado!");


console.log("\n");
// Proceso asincrono

const procesoAsincrono = async () => {
    console.log(" -> Proceso asincrono: Comenzando...");
    await new Promise((resolve, reject) => { // Las promesas se ejecutan asincronamente por lo tanto utilizamos await para esperar a que se ejecute la función. await remplaza el then y catch de manerá sincronica.
        setTimeout(() => {
            console.log(" -> Proceso asincrono: Procesando...");
            resolve();
        }, 1000);
    });
    console.log(" -> Proceso asincrono: Terminado!");
}

procesoAsincrono(); // Toma el tiempo necesario para ejecutar el código.


console.log("\n Hola mundo! \n"); // No se espera a que se ejecute el código de la función asincrona. Sino que se ejecuta en paralelo.





