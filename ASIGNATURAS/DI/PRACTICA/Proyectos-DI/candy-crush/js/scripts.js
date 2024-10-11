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
