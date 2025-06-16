/** @type {HTMLButtonElement} */
const btn_inicializar = document.getElementById('reset-button');
/** @type {HTMLButtonElement} */
const btn_cerrar = document.getElementById('close-button');

/** @type {HTMLTableSectionElement} */
const cont_registros = document.getElementById('log-entries-container');

const CLAVE_ALMACENAMIENTO = 'lista_registros';
const AVISO_SIN_REGISTROS = 'No hay registros disponibles.';

let registrosInforme = [];

// Functions -----------------------------------------------------------------------------------------------------------

/**
 * Carga la lista de registros desde el almacenamiento local (AL). Si esta no
 * existe crea una vacía en el AL.
 */
function cargarRegistros_desde_AL() {
    let registrosLocales = localStorage.getItem(CLAVE_ALMACENAMIENTO);

    if (!registrosLocales) {
        registrosLocales = JSON.stringify([]);
        localStorage.setItem(CLAVE_ALMACENAMIENTO, registrosLocales);
    }

    registrosInforme = JSON.parse(registrosLocales);
}

/**
 * Carga la lista de registros en la tabla. Si no hay registros, muestra un
 * mensaje de aviso.
 */
function cargarRegistros_a_tabla() {
    cont_registros.replaceChildren();

    if (registrosInforme.length === 0) {
        const aviso_fila = document.createElement('tr');
        const aviso_colu = document.createElement('td');

        aviso_colu.setAttribute('colspan', '3');
        aviso_colu.textContent = AVISO_SIN_REGISTROS;
        aviso_colu.style.textAlign = 'center';
        aviso_colu.style.color = 'gray';

        aviso_fila.appendChild(aviso_colu);

        cont_registros.appendChild(aviso_fila);
    }

    registrosInforme.forEach(registro => {
        const fecha_colu = document.createElement('td');
        const hora_colu = document.createElement('td');
        const accion_colu = document.createElement('td');
        const fila = document.createElement('tr');

        fecha_colu.textContent = registro.fecha;
        hora_colu.textContent = registro.hora;
        accion_colu.textContent = registro.accion;

        fila.appendChild(fecha_colu);
        fila.appendChild(hora_colu);
        fila.appendChild(accion_colu);

        cont_registros.appendChild(fila);
    });
}

// Event listeners -----------------------------------------------------------------------------------------------------

window.addEventListener('storage', evento => {
    if (evento.key === CLAVE_ALMACENAMIENTO) {
        registrosInforme = JSON.parse(evento.newValue);
        cargarRegistros_a_tabla();
    }
});

document.addEventListener('DOMContentLoaded', () => {
    cargarRegistros_desde_AL();
    cargarRegistros_a_tabla();
});

btn_inicializar.addEventListener('click', () => {
    registrosInforme.length = 0;
    localStorage.setItem(CLAVE_ALMACENAMIENTO, JSON.stringify(registrosInforme));
    cargarRegistros_a_tabla();
});

btn_cerrar.addEventListener('click', () => {
    window.close();
});
