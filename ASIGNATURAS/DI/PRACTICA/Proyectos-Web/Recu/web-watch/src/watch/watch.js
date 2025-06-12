const btn_ajustar = document.getElementById('btn-ajustar');
const btn_iluminar = document.getElementById('btn-iluminar');
const btn_incrementar = document.getElementById('btn-incrementar');
const btn_decrementar = document.getElementById('btn-decrementar');
const btn_informe = document.getElementById('btn-informe');

const dis_fondo = document.getElementById('display-fondo');
const dis_horas = document.getElementById('display-horas');
const dis_minutos = document.getElementById('display-minutos');

const CLAVE_ALMACENAMIENTO = 'registros_array';
const ESTADOS = {
    SIN_AJUSTAR: 0,
    AJUSTAR_HORAS: 1,
    AJUSTAR_MIN: 2,
};

let iluminado = false;
let estado = ESTADOS.SIN_AJUSTAR;
let horas = 0;
let minutos = 0;

function inicializarRegistroAcciones() {
    const guardado = localStorage.getItem(CLAVE_ALMACENAMIENTO);

    if (!guardado) {
        localStorage.setItem(CLAVE_ALMACENAMIENTO, JSON.stringify([]));
    }
}

function actualizarInterfaz() {
    switch (estado) {
        case ESTADOS.SIN_AJUSTAR:
            dis_horas.style.color = '#404040'; // Dark Gray
            dis_minutos.style.color = '#404040'; // Dark Gray
            dis_horas.textContent = horas.toString().padStart(2, '0');
            dis_minutos.textContent = minutos.toString().padStart(2, '0');
            btn_decrementar.enabled = false;
            btn_incrementar.enabled = false;
            break;
        case ESTADOS.AJUSTAR_HORAS:
            dis_horas.style.color = 'darkred';
            dis_minutos.style.color = '#404040'; // Dark Gray
            dis_horas.textContent = horas.toString().padStart(2, '0');
            dis_minutos.textContent = minutos.toString().padStart(2, '0');
            btn_decrementar.enabled = true;
            btn_incrementar.enabled = true;
            break;
        case ESTADOS.AJUSTAR_MIN:
            dis_horas.style.color = '#404040'; // Dark Gray
            dis_minutos.style.color = 'darkred';
            dis_horas.textContent = horas.toString().padStart(2, '0');
            dis_minutos.textContent = minutos.toString().padStart(2, '0');
            btn_decrementar.enabled = true;
            btn_incrementar.enabled = true;
            break;
    }
}

/**
 * Registra una acción con la hora y fecha actual.
 * @param {string} accion - Descripción de la acción realizada.
 */
function registrarAccion(accion) {
    const localTime = new Date();

    // Obtener hora y fecha locales;
    const hora_actual = localTime.getHours().toString().padStart(2, '0');
    const minuto_actual = localTime.getMinutes().toString().padStart(2, '0');
    const segundo_actual = localTime.getSeconds().toString().padStart(2, '0');

    const dia_actual = localTime.getDate().toString().padStart(2, '0');
    const mes_actual = localTime.getMonth().toString().padStart(2, '0');
    const anno_actual = localTime.getFullYear().toString().padStart(4, '0');

    const hora_completa = hora_actual + ':' + minuto_actual + ':' + segundo_actual;
    const fecha_completa = anno_actual + '/' + mes_actual + '/' + dia_actual;

    const nuevoRegistro = {
        fecha: fecha_completa,
        hora: hora_completa,
        accion: accion,
    };

    // Actualizar registros
    const accionesRegistradas = JSON.parse(localStorage.getItem(CLAVE_ALMACENAMIENTO));
    accionesRegistradas.push(nuevoRegistro);
    localStorage.setItem(CLAVE_ALMACENAMIENTO, JSON.stringify(accionesRegistradas));
}

// Event Listeners -------------------------------------------------------------

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
        dis_fondo.style.backgroundColor = 'black';
    } else {
        dis_fondo.style.backgroundColor = 'lightgreen';
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

document.addEventListener('DOMContentLoaded', () => {
    inicializarRegistroAcciones();
    actualizarInterfaz();
});
