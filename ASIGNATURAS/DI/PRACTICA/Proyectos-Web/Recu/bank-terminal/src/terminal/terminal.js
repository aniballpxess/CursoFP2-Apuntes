/** @type {HTMLButtonElement} */
const boton_0 = document.getElementById('btn-0');
/** @type {HTMLButtonElement} */
const boton_1 = document.getElementById('btn-1');
/** @type {HTMLButtonElement} */
const boton_2 = document.getElementById('btn-2');
/** @type {HTMLButtonElement} */
const boton_3 = document.getElementById('btn-3');
/** @type {HTMLButtonElement} */
const boton_4 = document.getElementById('btn-4');
/** @type {HTMLButtonElement} */
const boton_5 = document.getElementById('btn-5');
/** @type {HTMLButtonElement} */
const boton_6 = document.getElementById('btn-6');
/** @type {HTMLButtonElement} */
const boton_7 = document.getElementById('btn-7');
/** @type {HTMLButtonElement} */
const boton_8 = document.getElementById('btn-8');
/** @type {HTMLButtonElement} */
const boton_9 = document.getElementById('btn-9');
/** @type {HTMLButtonElement} */
const boton_C = document.getElementById('btn-C');

/** @type {HTMLButtonElement} */
const boton_CS = document.getElementById('btn-CA');
/** @type {HTMLButtonElement} */
const boton_SD = document.getElementById('btn-TM');
/** @type {HTMLButtonElement} */
const boton_S = document.getElementById('btn-E');

/** @type {HTMLButtonElement} */
const boton_IET = document.getElementById('btn-IOC');
/** @type {HTMLButtonElement} */
const boton_RD = document.getElementById('btn-GM');
/** @type {HTMLButtonElement} */
const boton_informe = document.getElementById('btn-records');

/** @type {HTMLDivElement} */
const texto_CS = document.getElementById('txt-CA');
/** @type {HTMLDivElement} */
const texto_SD = document.getElementById('txt-TM');
/** @type {HTMLDivElement} */
const texto_S = document.getElementById('txt-E');
/** @type {HTMLDivElement} */
const texto_principal = document.getElementById('txt-main');
/** @type {HTMLDivElement} */
const texto_dinero = document.getElementById('txt-money');

const ESTADOS = {
    INTRODUZCA_TARJETA: 0,
    SELECCIONE_OPERACION: 1,
    CONSULTAR_SALDO: 2,
    SACAR_DINERO_1: 3,
    SACAR_DINERO_2: 4,
    SALIR: 5,
};
const CLAVE_ALMACENAMIENTO = 'lista_registros';

const MSJ_INTRODUZIR_TARJETA = 'Introduzca tarjeta';
const MSJ_SELECCIONAR_OPERACION = 'Seleccione operación';
const MSJ_MOSTRAR_SALDO = 'Saldo';
const MSJ_INTRODUCIR_IMPORTE = 'Introduzca importe y pulse [->]';
const MSJ_RETIRAR_DINERO = 'Retire dinero';
const MSJ_EXTRAER_TARJETA = 'Extraiga tarjeta';

const MSJ_DIVISA = ' €';

let estadoActual = ESTADOS.INTRODUZCA_TARJETA;
let saldoCuenta = 1000;
let importe = 0;
let registrosTerminal = [];

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

    registrosTerminal = JSON.parse(registrosLocales);
}

function actualizarInterfaz() {
    switch (estadoActual) {
        case ESTADOS.INTRODUZCA_TARJETA:
            boton_0.disabled = true;
            boton_1.disabled = true;
            boton_2.disabled = true;
            boton_3.disabled = true;
            boton_4.disabled = true;
            boton_5.disabled = true;
            boton_6.disabled = true;
            boton_7.disabled = true;
            boton_8.disabled = true;
            boton_9.disabled = true;
            boton_C.disabled = true;

            boton_CS.disabled = true;
            boton_SD.disabled = true;
            boton_S.disabled = true;

            boton_IET.disabled = false;
            boton_RD.disabled = true;
            boton_informe.disabled = false;

            texto_CS.setAttribute('visibility', 'hidden');
            texto_SD.setAttribute('visibility', 'hidden');
            texto_S.setAttribute('visibility', 'hidden');
            texto_principal.setAttribute('visibility', 'visible');
            texto_dinero.setAttribute('visibility', 'hidden');

            texto_principal.textContent(MSJ_INTRODUZIR_TARJETA);
            texto_dinero.textContent(null);
            break;
        case ESTADOS.SELECCIONE_OPERACION:
            boton_0.disabled = true;
            boton_1.disabled = true;
            boton_2.disabled = true;
            boton_3.disabled = true;
            boton_4.disabled = true;
            boton_5.disabled = true;
            boton_6.disabled = true;
            boton_7.disabled = true;
            boton_8.disabled = true;
            boton_9.disabled = true;
            boton_C.disabled = true;

            boton_CS.disabled = false;
            boton_SD.disabled = false;
            boton_S.disabled = false;

            boton_IET.disabled = true;
            boton_RD.disabled = true;
            boton_informe.disabled = false;

            texto_CS.setAttribute('visibility', 'visible');
            texto_SD.setAttribute('visibility', 'visible');
            texto_S.setAttribute('visibility', 'visible');
            texto_principal.setAttribute('visibility', 'visible');
            texto_dinero.setAttribute('visibility', 'hidden');

            texto_principal.textContent(MSJ_SELECCIONAR_OPERACION);
            texto_dinero.textContent(null);
            break;
        case ESTADOS.CONSULTAR_SALDO:
            boton_0.disabled = true;
            boton_1.disabled = true;
            boton_2.disabled = true;
            boton_3.disabled = true;
            boton_4.disabled = true;
            boton_5.disabled = true;
            boton_6.disabled = true;
            boton_7.disabled = true;
            boton_8.disabled = true;
            boton_9.disabled = true;
            boton_C.disabled = true;

            boton_CS.disabled = true;
            boton_SD.disabled = true;
            boton_S.disabled = false;

            boton_IET.disabled = true;
            boton_RD.disabled = true;
            boton_informe.disabled = false;

            texto_CS.setAttribute('visibility', 'hidden');
            texto_SD.setAttribute('visibility', 'hidden');
            texto_S.setAttribute('visibility', 'hidden');
            texto_principal.setAttribute('visibility', 'visible');
            texto_dinero.setAttribute('visibility', 'visible');

            texto_principal.textContent(MSJ_MOSTRAR_SALDO);
            texto_dinero.textContent(saldoCuenta.toLocaleString('fr-FR') + MSJ_DIVISA);
            break;
        case ESTADOS.SACAR_DINERO_1:
            boton_0.disabled = false;
            boton_1.disabled = false;
            boton_2.disabled = false;
            boton_3.disabled = false;
            boton_4.disabled = false;
            boton_5.disabled = false;
            boton_6.disabled = false;
            boton_7.disabled = false;
            boton_8.disabled = false;
            boton_9.disabled = false;
            boton_C.disabled = false;

            boton_CS.disabled = true;
            boton_SD.disabled = true;
            boton_S.disabled = false;

            boton_IET.disabled = true;
            boton_RD.disabled = true;
            boton_informe.disabled = false;

            texto_CS.setAttribute('visibility', 'hidden');
            texto_SD.setAttribute('visibility', 'hidden');
            texto_S.setAttribute('visibility', 'hidden');
            texto_principal.setAttribute('visibility', 'visible');
            texto_dinero.setAttribute('visibility', 'visible');

            texto_principal.textContent(MSJ_INTRODUCIR_IMPORTE);
            texto_dinero.textContent(importe.toLocaleString('fr-FR') + MSJ_DIVISA);
            break;
        case ESTADOS.SACAR_DINERO_2:
            boton_0.disabled = true;
            boton_1.disabled = true;
            boton_2.disabled = true;
            boton_3.disabled = true;
            boton_4.disabled = true;
            boton_5.disabled = true;
            boton_6.disabled = true;
            boton_7.disabled = true;
            boton_8.disabled = true;
            boton_9.disabled = true;
            boton_C.disabled = true;

            boton_CS.disabled = true;
            boton_SD.disabled = true;
            boton_S.disabled = true;

            boton_IET.disabled = true;
            boton_RD.disabled = false;
            boton_informe.disabled = false;

            texto_CS.setAttribute('visibility', 'hidden');
            texto_SD.setAttribute('visibility', 'hidden');
            texto_S.setAttribute('visibility', 'hidden');
            texto_principal.setAttribute('visibility', 'visible');
            texto_dinero.setAttribute('visibility', 'hidden');

            texto_principal.textContent(MSJ_RETIRAR_DINERO);
            texto_dinero.textContent(null);
            break;
        case ESTADOS.SALIR:
            boton_0.disabled = true;
            boton_1.disabled = true;
            boton_2.disabled = true;
            boton_3.disabled = true;
            boton_4.disabled = true;
            boton_5.disabled = true;
            boton_6.disabled = true;
            boton_7.disabled = true;
            boton_8.disabled = true;
            boton_9.disabled = true;
            boton_C.disabled = true;

            boton_CS.disabled = true;
            boton_SD.disabled = true;
            boton_S.disabled = true;

            boton_IET.disabled = true;
            boton_RD.disabled = false;
            boton_informe.disabled = false;

            texto_CS.setAttribute('visibility', 'hidden');
            texto_SD.setAttribute('visibility', 'hidden');
            texto_S.setAttribute('visibility', 'hidden');
            texto_principal.setAttribute('visibility', 'visible');
            texto_dinero.setAttribute('visibility', 'hidden');

            texto_principal.textContent(MSJ_EXTRAER_TARJETA);
            texto_dinero.textContent(null);
            break;
    }
}

// Event Listeners -----------------------------------------------------------------------------------------------------

window.addEventListener('storage', event => {
    if (event.key === CLAVE_ALMACENAMIENTO) {
        registrosTerminal = JSON.parse(event.newValue);
    }
});

window.addEventListener('beforeunload', () => {
    localStorage.removeItem(CLAVE_ALMACENAMIENTO);
    window.close();
});

document.addEventListener('DOMContentLoaded', () => {
    cargarRegistros_desde_AL();
    actualizarInterfaz();
    boton_informe.title = 'WTF is happening?!!!';
});

// Numpad buttons
boton_0.addEventListener('click', () => {
    // TODO: Handle boton_0 click
});

boton_1.addEventListener('click', () => {
    // TODO: Handle boton_1 click
});

boton_2.addEventListener('click', () => {
    // TODO: Handle boton_2 click
});

boton_3.addEventListener('click', () => {
    // TODO: Handle boton_3 click
});

boton_4.addEventListener('click', () => {
    // TODO: Handle boton_4 click
});

boton_5.addEventListener('click', () => {
    // TODO: Handle boton_5 click
});

boton_6.addEventListener('click', () => {
    // TODO: Handle boton_6 click
});

boton_7.addEventListener('click', () => {
    // TODO: Handle boton_7 click
});

boton_8.addEventListener('click', () => {
    // TODO: Handle boton_8 click
});

boton_9.addEventListener('click', () => {
    // TODO: Handle boton_9 click
});

boton_C.addEventListener('click', () => {
    // TODO: Handle boton_C (←) click
});

// Control buttons
boton_CS.addEventListener('click', () => {
    // TODO: Handle boton_CS click
});

boton_SD.addEventListener('click', () => {
    // TODO: Handle boton_SD click
});

boton_S.addEventListener('click', () => {
    // TODO: Handle boton_S click
});

// Action buttons
boton_IET.addEventListener('click', () => {
    // TODO: Handle Intr. Extr. Tarjeta click
});

boton_RD.addEventListener('click', () => {
    // TODO: Handle Retirar dinero click
});

boton_informe.addEventListener('click', () => {
    window.open('../log-table/log-table.html');
});
