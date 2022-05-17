// Variables
// Las variables son elementos que se almacenan en la memoria del ordenador, permitiendo que se puedan reutilizar en diferentes partes del código.
// Estas variables pueden ser de cualquier tipo, como números, cadenas de texto, etc.

// Las variables se pueden declarar de tres formas:
// 1. la primera forma es con la palabra reservada var, seguida del nombre de la variable y una asignación de valor.
// 2. la segunda forma es con la palabra reservada let, seguida del nombre de la variable y una asignación de valor.
// 3. la tercera forma es con la palabra reservada const, seguida del nombre de la variable y una asignación de valor.

// La palabra reservada var se utiliza para declarar variables globales. 
// Las variables globales pueden ser accedidas desde cualquier parte del código.

// La palabra reservada let se utiliza para declarar variables locales en función de su contexto o Scope.
// Las variables locales solo pueden ser accedidas dentro de su contexto.

// La palabra reservada const se utiliza para declarar variables constantes.
// Las variables constantes no pueden ser modificadas en ninuguna parte del código despues de su declaración.

// Ejemplo:
// Var
var nombre = "Ana";
console.log(nombre);

nombre = "Cindy"; // Se puede reasignar el valor de la variable
console.log(nombre);

// Let
function saludar(){
    let nombreLet = "Ana";
    console.log(nombreLet);
}

console.log(nombreLet); // Error, la variable nombreLet no está definida por que solo existe dentro del contexto de la función saludar()

// Const
const PI = 3.1416;
console.log(PI);

PI = 3.14; // Error, la variable PI no puede ser reasignada porque es una constante


