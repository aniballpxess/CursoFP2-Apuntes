function generarTablero() 
{
    const coloresPosibles = ['#ffff00', '#00ffff', '#ee82ee', '#90ee90', '#ff7777', '#829be7'];
    const tabla = document.getElementById('tablero');
    tabla.innerHTML = '';
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
        tabla.appendChild(fila);
    }
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
            btn_arriba.disabled = (fila === 'a') ? true : false;
            btn_abajo.disabled = (fila === 'h') ? true : false;
            btn_izquierda.disabled = (columna === '1') ? true : false;
            btn_derecha.disabled = (columna === '8') ? true : false;
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

function moverArriba() 
{
    const input = document.getElementById('casilla');
    const casilla = document.getElementById(input);
    const nuevaCasilla = String.fromCharCode(casilla.id.charCodeAt(0) + 1) + casilla.id.charAt(1);
    casilla.id

}

function moverAbajo() {
    
}

function moverIzquierda() {
    
}

function moverDerecha() {
    
}