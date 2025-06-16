/** @type {HTMLButtonElement} */
const btn_ajustar = document.getElementById('btn-ajustar');
/** @type {HTMLButtonElement} */
const btn_iluminar = document.getElementById('btn-iluminar');
/** @type {HTMLButtonElement} */
const btn_incrementar = document.getElementById('btn-incrementar');
/** @type {HTMLButtonElement} */
const btn_decrementar = document.getElementById('btn-decrementar');
/** @type {HTMLButtonElement} */
const btn_informe = document.getElementById('btn-informe');

/** @type {HTMLDivElement} */
const pant_fondo = document.getElementById('display-fondo');

/** @type {HTMLSpanElement} */
const pant_horas = document.getElementById('display-horas');
/** @type {HTMLSpanElement} */
const pant_minutos = document.getElementById('display-minutos');

const CLAVE_ALMACENAMIENTO = 'lista_registros';
const ESTADOS = {
    SIN_AJUSTAR: 0,
    AJUSTAR_HORAS: 1,
    AJUSTAR_MIN: 2,
};

let iluminado = false;
let estado = ESTADOS.SIN_AJUSTAR;
let horas = 0;
let minutos = 0;
let registrosReloj = [];

// Functions -----------------------------------------------------------------------------------------------------------

function cargarRegistrosAcciones() {
    let registrosLocales = localStorage.getItem(CLAVE_ALMACENAMIENTO);

    if (!registrosLocales) {
        registrosLocales = JSON.stringify([]);
        localStorage.setItem(CLAVE_ALMACENAMIENTO, registrosLocales);
    }

    registrosReloj = JSON.parse(registrosLocales);
}

function actualizarInterfaz() {
    switch (estado) {
        case ESTADOS.SIN_AJUSTAR:
            pant_horas.style.color = '#404040'; // Dark Gray
            pant_minutos.style.color = '#404040'; // Dark Gray
            pant_horas.textContent = horas.toString().padStart(2, '0');
            pant_minutos.textContent = minutos.toString().padStart(2, '0');
            btn_decrementar.disabled = true;
            btn_incrementar.disabled = true;
            break;
        case ESTADOS.AJUSTAR_HORAS:
            pant_horas.style.color = 'darkred';
            pant_minutos.style.color = '#404040'; // Dark Gray
            pant_horas.textContent = horas.toString().padStart(2, '0');
            pant_minutos.textContent = minutos.toString().padStart(2, '0');
            btn_decrementar.disabled = false;
            btn_incrementar.disabled = false;
            break;
        case ESTADOS.AJUSTAR_MIN:
            pant_horas.style.color = '#404040'; // Dark Gray
            pant_minutos.style.color = 'darkred';
            pant_horas.textContent = horas.toString().padStart(2, '0');
            pant_minutos.textContent = minutos.toString().padStart(2, '0');
            btn_decrementar.disabled = false;
            btn_incrementar.disabled = false;
            break;
    }
}

/**
 * Registra una acción con la hora y fecha actual.
 *
 * TODO - Refactor internal logic (**) into "utils.js"
 * @param {string} accion - Descripción de la acción realizada.
 */
function registrarAccion(accion) {
    const localDateTime = new Date(); // Auxiliar para obtener hora y fecha

    // Obtener hora local **
    const hora_actual = localDateTime.getHours().toString().padStart(2, '0');
    const minuto_actual = localDateTime.getMinutes().toString().padStart(2, '0');
    const segundo_actual = localDateTime.getSeconds().toString().padStart(2, '0');

    const hora_completa = hora_actual + ':' + minuto_actual + ':' + segundo_actual;

    // Obtener fecha local **
    const dia_actual = localDateTime.getDate().toString().padStart(2, '0');
    const mes_actual = localDateTime.getMonth().toString().padStart(2, '0');
    const anno_actual = localDateTime.getFullYear().toString().padStart(4, '0');

    const fecha_completa = anno_actual + '/' + mes_actual + '/' + dia_actual;

    // Actualizar registros
    const nuevoRegistro = {
        fecha: fecha_completa,
        hora: hora_completa,
        accion: accion,
    };

    registrosReloj.push(nuevoRegistro);
    localStorage.setItem(CLAVE_ALMACENAMIENTO, JSON.stringify(registrosReloj));
}

// Event Listeners -----------------------------------------------------------------------------------------------------

window.addEventListener('storage', event => {
    if (event.key === CLAVE_ALMACENAMIENTO) {
        registrosReloj = JSON.parse(event.newValue);
    }
});

window.addEventListener('beforeunload', () => {
    localStorage.removeItem(CLAVE_ALMACENAMIENTO);
    window.close();
});

document.addEventListener('DOMContentLoaded', () => {
    cargarRegistrosAcciones();
    actualizarInterfaz();
});

btn_ajustar.addEventListener('click', () => {
    switch (estado) {
        case ESTADOS.SIN_AJUSTAR:
            estado = ESTADOS.AJUSTAR_HORAS;
            break;
        case ESTADOS.AJUSTAR_HORAS:
            estado = ESTADOS.AJUSTAR_MIN;
            break;
        case ESTADOS.AJUSTAR_MIN:
            estado = ESTADOS.SIN_AJUSTAR;
            break;
    }
    actualizarInterfaz();
    registrarAccion('Ajustar');
});

btn_iluminar.addEventListener('click', () => {
    if (iluminado) {
        pant_fondo.style.backgroundColor = 'black';
    } else {
        pant_fondo.style.backgroundColor = 'lightgreen';
    }
    iluminado = !iluminado;
});

btn_incrementar.addEventListener('click', () => {
    switch (estado) {
        case ESTADOS.AJUSTAR_HORAS:
            horas = (horas + 1) % 24;
            break;
        case ESTADOS.AJUSTAR_MIN:
            minutos = (minutos + 1) % 60;
            break;
        default:
            break;
    }
    actualizarInterfaz();
    registrarAccion('Incrementar');
});

btn_decrementar.addEventListener('click', () => {
    switch (estado) {
        case ESTADOS.AJUSTAR_HORAS:
            horas = (horas - 1 + 24) % 24;
            break;
        case ESTADOS.AJUSTAR_MIN:
            minutos = (minutos - 1 + 60) % 60; // tiene sentido cuando haces los cálculos
            break;
        default:
            break;
    }
    actualizarInterfaz();
    registrarAccion('Decrementar');
});

btn_informe.addEventListener('click', () => {
    window.open('../log-table/log-table.html');
});
