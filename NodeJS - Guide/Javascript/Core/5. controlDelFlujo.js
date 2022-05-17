// Control de flujo:
// El control de flujo es una estructura de control que permite determinar si una condición es verdadera o falsa y actuar en consecuencia.
// El control de flujo se basa en la comparación de dos o más expresiones.

// Condicionales:
// Condicionales son expresiones que evalúan una condición y devuelven un valor booleano.

// Los condicionales se pueden evaluar mediante la función if o operador condicional (ternario) y switches.

// Función if:
// Si la condición es verdadera, se ejecuta el bloque de código, en caso contrario, se ejecuta el bloque de código alternativo.(Pueden haber más de un bloque alternativo)

// Sintaxis:
// if (condición) {
//     // Bloque de código
// } else {
//     // Bloque de código alternativo en caso de que la condición sea falsa
// }

// Si hay más de un bloque de código, se puede utilizar la palabra reservada else if.
// Sintaxis:
// if (condición) {
//     // Bloque de código
// } else if (condición) {
//     // Bloque de código alternativo en caso de que otra condición sea correcta  
// } else {
//     // Bloque de código alternativo en caso de que ninguna condición sea correcta
// }

// Ejemplo:
console.log("\n============ Condicionales =============\n")
let edad = 18;

if(edad > 18){
    console.log(" -> La persona es mayor de edad");
}else{
    console.log(" -> La persona es menor de edad");
}

// Ejemplo con else if:

if(edad > 18){
    console.log(" -> La persona es mayor de edad");
}else if(edad <= 18 && edad > 14){
    console.log(" -> La persona es adolescente");
}else {
    console.log(" -> La persona es menor de edad");
}

// If ternario:
// Un if ternario es una forma de escribir un if que se puede simplificar, es mas corto y más sencillo.
// Sintaxis:
// condición ? valor1 : valor2;

// Ejemplo:
console.log("\n============ If Ternario =============\n")
console.log(edad > 18 ? " -> La persona es mayor de edad" : " -> La persona es menor de edad");

// Switch:
// Un switch es una estructura de control que permite evaluar una expresión y ejecutar una sentencia dependiendo del valor de la expresión.
// Sintaxis:
// switch (expresión) {
//     case valor1:
//         // Bloque de código
//         break; // Para salir del bloque de código
//     case valor2:
//         // Bloque de código
//         break;
//     default:
//         // Bloque de código
//         break;
// }

// Ejemplo:
console.log("\n============ Switch =============\n")
let dia = "Lunes";

switch(dia){
    case "Lunes":
        console.log(" -> Es Lunes");
        break;
    case "Martes":
        console.log(" -> Es Martes");
        break;
    case "Miercoles":
        console.log(" -> Es Miercoles");
        break;
    case "Jueves":
        console.log(" -> Es Jueves");
        break;
    case "Viernes":
        console.log(" -> Es Viernes");
        break;
    default:
        console.log(" -> Es fin de semana");
        break;
}