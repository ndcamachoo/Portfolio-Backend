// Bucles

// Un bucle es una estructura de control que itera sobre un conjunto de datos. Es decir, que se repite una acción una determinada cantidad de veces.
// Un bucle se define con la palabra clave for, while o do while y cada uno de ellos tiene una sintaxis diferente.

// Por ejemplo, el bucle for se define con la sintaxis:
// for(inicialización; condición; incremento){
//     // Bloque de código
// }

// Ejemplo:
console.log("\n============ Bucles =============\n")

console.log("\n============ For =============\n")
for(let i = 0; i < 10; i++){ // El bucle for se ejecuta 10 veces, desde i = 0 hasta que la condición sea falsa (i menor que 10) y incrementa i en 1 cada vez que se ejecuta.
    console.log(" -> " + i); // Imprime el valor de i
}    

// Para el bucle while, se define con la sintaxis:
// while(condición){
//     // Bloque de código
// }

// Ejemplo:
console.log("\n============ While =============\n")
let i = 10;

while(i > 0){ // El bucle while se ejecuta mientras la condición sea verdadera (i mayor que 0) y decrementa i en 1 cada vez que se ejecuta.
    console.log(" -> " + i); // Imprime el valor de i
    i--; // Decrementa i en 1
}

// Para el bucle do while, se define con la sintaxis:
// do {
//     // Bloque de código
// } while (condición); // La condición se evalúa al finalizar el bloque de código. "Hacer algo mientras que la condición sea verdadera"

// Ejemplo:
console.log("\n============ Do While =============\n")
let j = 2;

do{
    console.log(" -> " + j*2); // Imprime el valor de j
    j++; // Multiplica j por 2
}while(j <= 10); // La condición se evalúa al finalizar el bloque de código. Mostrará los valores de j hasta que j sea mayor que 20. Tabla del 20


// Bucles anidados
// Un bucle anidado es un bucle que se ejecuta dentro de otro bucle.
// Ejemplo:
console.log("\n============ Bucles anidados =============\n")

let mensajito = ""

for (let i = 0; i < 5; i++){
    for (let j = 0; j < 5; j++){
        mensajito += "*";
    }
    mensajito += "\n";
}

console.log(mensajito);

// Recorrer un array con un bucle for
// Para recorrer un array con un bucle for, se define con la sintaxis de for of y se escribe:
// for(element of array){
//     // Bloque de código
// }

// Ejemplo:
console.log("\n============ For of =============\n")
let frutas = ["🍎", "🍌", "🍓", "🍈"]; // Array de frutas

console.log("-> Frutas: " + frutas); // Imprime el array de frutas

for(fruta of frutas){ // Recorre el array de frutas con un bucle for in (Normalmente se describe el elemento como singuular y el array como plural)
    console.log(" -> " + fruta); // Imprime el valor de cada elemento del array
}

// Recorrer un objeto con un bucle for
// Para recorrer un objeto con un bucle for, se define con la sintaxis de for in y se escribe:
// for(propiedad in objeto){
//     // Bloque de código
// }

// Ejemplo:
console.log("\n============ For in =============\n")
let persona = {
    nombre: "Ana",
    apellido: "Galarza",
    edad: 22,
    direccion: {
        calle: "Calle falsa 123",
    }
}

console.log("-> Persona: " + persona); // Imprime el objeto persona

for(propiedad in persona){ // Propiedad retorna el valor de cada clave del objeto
    console.log(" -> " + propiedad + ": " + persona[propiedad]); // Imprime la propiedad y el valor de cada propiedad del objeto
}

