// Funciones Avanzadas
// Callbacks y Closures

// Callbacks
// Un callback es una función que se pasa como un argumento a otra función.
// Es decir, un parametro de entrada de la función es otra función que se ejecuta cuando se llama a la función principal.

// Ejemplo:
console.log("\n============ Callbacks =============\n")

let operaciones = (num1, num2, callback) => { // El parametro callback es una función sin importar el nombre que se se ejecutará cuando se llame a la función principal.
    let resultado = callback(num1, num2); // Callback puede adoptar cualquier funcionalidad que se requiera.
    console.log(" -> El resultado es: " + resultado);
}

// Callback suma
operaciones(2, 3, (num1, num2) => {
    return num1 + num2;
});

// Callback resta
operaciones(2, 3, (num1, num2) => {
    return num1 - num2;
});

// Callback saber si los numeros son pares
operaciones(2, 3, (num1, num2) => {

   let array = [num1, num2];
   let mensaje = "";

   for(numero of array) {
         if(numero % 2 == 0) {
              mensaje += "El número " + numero + " es par\t";
         }else{
              mensaje += "El número " + numero + " es impar\t";
         }
    }

    return mensaje;

});

// Closures
// Un closure es una función que tiene acceso a variables de alcance superior.
// Es decir, una función que se puede llamar desde una función que se encuentra fuera de su alcance.
// Normalmente se utiliza para crear una función que utilice variables de una función padre/madre.

// Ejemplo:
console.log("\n============ Closures =============\n")

let definirNombre = (nombre) => {
    
    let saludar = () => {
        console.log("Mi nombre es: " + nombre)
    }

    return saludar;
}

let saludo = definirNombre("Ana");
saludo();

