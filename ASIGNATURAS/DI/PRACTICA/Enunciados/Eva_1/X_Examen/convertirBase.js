/**
 * Convierte un número de una base numérica a otra.
 * 
 * @param {number} baseOrigen - La base del número de entrada (2 para binario, 8 para octal, 10 para decimal, 16 para hexadecimal).
 * @param {string} numero - El número a convertir, expresado en la base de origen.
 * @param {number} baseDestino - La base a la que se debe convertir el número (2 para binario, 8 para octal, 10 para decimal, 16 para hexadecimal).
 * @returns {string} - El número convertido, expresado en la base de destino.
 */
function convertirBase(baseOrigen, numero, baseDestino) {
    // Convierte el número de entrada de la base de origen a un número decimal (base 10).
    // parseInt() toma dos argumentos: el número como cadena y la base de ese número.
    let numeroDecimal = parseInt(numero, baseOrigen);

    // Convierte el número decimal a la base de destino.
    // toString() toma un argumento: la base a la que se va a convertir.
    let numeroConvertido = numeroDecimal.toString(baseDestino);

    // Devuelve el número convertido como una cadena.
    return numeroConvertido;
}