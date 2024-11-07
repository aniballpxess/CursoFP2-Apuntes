function generarTablero() 
{
    const coloresPosibles = ['#ffff00', '#00ffff', '#ee82ee', '#90ee90', '#ff7777', '#829be7'];
    const tabla = document.getElementById('tablero');
    tabla.innerHTML = '';
    const cuerpo = document.createElement('tbody');
    for (let i = 0; i < 8; i++) 
    {
        const fila = document.createElement('tr');
        const letraFila = String.fromCharCode(97 + i);
        for (let j = 0; j < 8; j++) 
        {
            const celda = document.createElement('td');
            const colorEscogido = coloresPosibles[Math.floor(Math.random() * coloresPosibles.length)];
            celda.style.backgroundColor = colorEscogido;
            celda.id = `${letraFila}${j + 1}`;
            fila.appendChild(celda);
        }
        cuerpo.appendChild(fila);
    }
    tabla.appendChild(cuerpo);
}

function evaluarCasilla() 
{
    const inputCasilla = document.getElementById('casilla');
    let casilla = inputCasilla.value;
    if (casilla.length > 2)
    {
        casilla = casilla.slice(0, 2);
        inputCasilla.value = casilla;
    }
    if (casilla.length === 2) 
    {
        const fila = casilla[0].toLowerCase();
        const columna = casilla[1];
        const filaValida = /^[a-h]$/;
        const columnaValida = /^[1-8]$/;
        const btn_arriba = document.getElementById('arriba');
        const btn_abajo = document.getElementById('abajo');
        const btn_izquierda = document.getElementById('izquierda');
        const btn_derecha = document.getElementById('derecha');
        if (filaValida.test(fila) && columnaValida.test(columna))
        {
            btn_arriba.disabled = (fila === 'a');
            btn_abajo.disabled = (fila === 'h');
            btn_izquierda.disabled = (columna === '1');
            btn_derecha.disabled = (columna === '8');
        }
        else 
        {
            btn_arriba.disabled = true;
            btn_abajo.disabled = true;
            btn_izquierda.disabled = true;
            btn_derecha.disabled = true;
        }
    }
}

function mover(direccion) 
{
    const input = document.getElementById('casilla');
    const casilla_movida = document.getElementById(input.value);
    let casilla_intercambiada;
    switch (direccion) 
    {
        case 'arriba':
            casilla_intercambiada = seleccionarArriba(casilla_movida);
            break;
        case 'abajo':
            casilla_intercambiada = seleccionarAbajo(casilla_movida);
            break;
        case 'izquierda':
            casilla_intercambiada = seleccionarIzquierda(casilla_movida);
            break;
        case 'derecha':
            casilla_intercambiada = seleccionarDerecha(casilla_movida);
            break;
        default:
            throw new Error("Algo ha salido mal al realizar el movimiento.");
    }
    realizarMovimiento(casilla_movida, casilla_intercambiada);
}

function seleccionarArriba(casilla) 
{
    const fila_de_arriba = String.fromCharCode(casilla.id.charCodeAt(0) - 1);
    const columna = casilla.id.charAt(1);
    const casilla_de_arriba = document.getElementById(`${fila_de_arriba}${columna}`);
    return casilla_de_arriba;
}

function seleccionarAbajo(casilla) 
{
    const fila_de_abajo = String.fromCharCode(casilla.id.charCodeAt(0) + 1);
    const columna = casilla.id.charAt(1);
    const casilla_de_abajo = document.getElementById(`${fila_de_abajo}${columna}`);
    return casilla_de_abajo;
}

function seleccionarIzquierda(casilla) 
{
    const fila = casilla.id.charAt(0);
    const columna_izquierda = String.fromCharCode(casilla.id.charCodeAt(1) - 1);
    const casilla_izquierda = document.getElementById(`${fila}${columna_izquierda}`);
    return casilla_izquierda;
}

function seleccionarDerecha(casilla) 
{
    const fila = casilla.id.charAt(0);
    const columna_derecha = String.fromCharCode(casilla.id.charCodeAt(1) + 1);
    const casilla_derecha = document.getElementById(`${fila}${columna_derecha}`);
    return casilla_derecha;
}

function realizarMovimiento(casilla_A, casilla_B) {
    const color_auxiliar = casilla_A.style.backgroundColor;
    casilla_A.style.backgroundColor = casilla_B.style.backgroundColor;
    casilla_B.style.backgroundColor = color_auxiliar;
}