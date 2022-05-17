// Objetos

// Un objeto es una colección de propiedades y métodos.
// Un objeto se describe con llaves {} y se establecen mediante llaves y valores como un diccionario.
// Un objeto puede contener propiedades de cualquier tipo de dato, incluyendo funciones.

// Sintaxis
// let objeto = {
//     propiedad1: valor1,
//     propiedad2: valor2,
//     propiedad3: valor3,
//     ...
// }

// Ejemplo:
console.log("\n============ Objeto =============\n")
let persona = {
    nombre: "Ana",
    apellido: "Galarza",
    casada: true,
    saludar: function(){
        console.log("Hola, soy " + this.nombre + " " + this.apellido);
    }   
}

console.log(persona);

// Acceder a las propiedades de un objeto
// Para acceder a una propiedad de un objeto se utiliza la notación de punto. 
// Sintaxis:
// objeto.propiedad 

// Si la propiedad no existe, el valor undefined se devuelve.


// Ejemplo:
console.log("\n============ Acceder a las propiedades de un objeto =============\n")
persona.saludar();
console.log(persona.casada? " -> Esta casada" : " -> No esta casada");
console.log(" -> Edad: " + persona.edad);