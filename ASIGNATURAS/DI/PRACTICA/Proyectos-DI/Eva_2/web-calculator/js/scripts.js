const pantalla = document.getElementById('pantalla');

const btn_reiniciar = document.getElementById('btn_E'); // 'E' de 'Empezar de cero'
const btn_limpiar = document.getElementById('btn_L');
const btn_borrar = document.getElementById('btn_B');

const btn_sumar = document.getElementById('btn_S');
const btn_restar = document.getElementById('btn_R');
const btn_multiplicar = document.getElementById('btn_M');
const btn_dividir = document.getElementById('btn_D');
const btn_calcular = document.getElementById('btn_C');

const btn_punto = document.getElementById('btn_P');
const btn_zero = document.getElementById('btn_0');
const btn_uno = document.getElementById('btn_1');
const btn_dos = document.getElementById('btn_2');
const btn_tres = document.getElementById('btn_3');
const btn_cuatro = document.getElementById('btn_4');
const btn_cinco = document.getElementById('btn_5');
const btn_seis = document.getElementById('btn_6');
const btn_siete = document.getElementById('btn_7');
const btn_ocho = document.getElementById('btn_8');
const btn_nueve = document.getElementById('btn_9');

const ESTADOS = {
  SIN_PRIMER_INPUT: 1,
  CON_PRIMER_INPUT: 2,
  SIN_SEGUNDO_INPUT: 3,
  CON_SEGUNDO_INPUT: 4,
  CON_RESULTADO: 5,
};

let estadoActual = null;

let primerNumero = null;
let segundoNumero = null;
let resultado = null;

// TODO - Plantearse una estructura y funcionamiento diferentes (como la de Fran)
function actualizarEstado(nuevoEstado) {
  estadoActual = nuevoEstado;

  switch (estadoActual) {
    case ESTADOS.SIN_PRIMER_INPUT:
      // Control
      btn_limpiar.disabled = true;
      btn_borrar.disabled = true;
      // Operandos
      btn_sumar.disabled = true;
      btn_restar.disabled = true;
      btn_multiplicar.disabled = true;
      btn_dividir.disabled = true;
      btn_calcular.disabled = true;
      break;

    case ESTADOS.CON_PRIMER_INPUT:
      // Control
      btn_limpiar.disabled = false;
      btn_borrar.disabled = false;
      // Operandos
      btn_sumar.disabled = false;
      btn_restar.disabled = false;
      btn_multiplicar.disabled = false;
      btn_dividir.disabled = false;
      btn_calcular.disabled = true;
      break;

    case ESTADOS.SIN_SEGUNDO_INPUT:
      // Control
      btn_limpiar.disabled = true;
      btn_borrar.disabled = true;
      // Operandos
      btn_sumar.disabled = false;
      btn_restar.disabled = false;
      btn_multiplicar.disabled = false;
      btn_dividir.disabled = false;
      btn_calcular.disabled = true;
      break;

    case ESTADOS.CON_SEGUNDO_INPUT:
      // Control
      btn_limpiar.disabled = false;
      btn_borrar.disabled = false;
      // Operandos
      btn_sumar.disabled = false;
      btn_restar.disabled = false;
      btn_multiplicar.disabled = false;
      btn_dividir.disabled = false;
      btn_calcular.disabled = false;
      break;

    case ESTADOS.CON_RESULTADO:
      // Control
      btn_limpiar.disabled = true;
      btn_borrar.disabled = true;
      // Operandos
      btn_sumar.disabled = false;
      btn_restar.disabled = false;
      btn_multiplicar.disabled = false;
      btn_dividir.disabled = false;
      btn_calcular.disabled = false;
      break;
  }
}

function cargarCalcualdora() {
  cargarBotones();
  actualizarEstado(ESTADOS.SIN_PRIMER_INPUT);
}

function cargarBotones() {
  // Inicializar valores
  btn_reiniciar.value = "C";
  btn_limpiar.value = "CE";
  btn_borrar.value = "&#x232B;";
  btn_dividir.value = "/";

  btn_siete.value = "7";
  btn_ocho.value = "8";
  btn_nueve.value = "9";
  btn_multiplicar.value = "x";

  btn_cuatro.value = "4";
  btn_cinco.value = "5";
  btn_seis.value = "6";
  btn_restar.value = "&minus;";

  btn_uno.value = "1";
  btn_dos.value = "2";
  btn_tres.value = "3";
  btn_sumar.value = "&plus;";

  btn_zero.value = "0";
  btn_punto.value = ".";
  btn_calcular.value = "&equals;";

  // Inicializar iconos
  btn_reiniciar.innerHTML = btn_reiniciar.value;
  btn_limpiar.innerHTML = btn_limpiar.value;
  btn_borrar.innerHTML = btn_borrar.value;
  btn_dividir.innerHTML = btn_dividir.value;

  btn_siete.innerHTML = btn_siete.value;
  btn_ocho.innerHTML = btn_ocho.value;
  btn_nueve.innerHTML = btn_nueve.value;
  btn_multiplicar.innerHTML = btn_multiplicar.value;

  btn_cuatro.innerHTML = btn_cuatro.value;
  btn_cinco.innerHTML = btn_cinco.value;
  btn_seis.innerHTML = btn_seis.value;
  btn_restar.innerHTML = btn_restar.value;

  btn_uno.innerHTML = btn_uno.value;
  btn_dos.innerHTML = btn_dos.value;
  btn_tres.innerHTML = btn_tres.value;
  btn_sumar.innerHTML = btn_sumar.value;

  btn_zero.innerHTML = btn_zero.value;
  btn_punto.innerHTML = btn_punto.value;
  btn_calcular.innerHTML = btn_calcular.value;
}



// ------------------ VIEJO CÓDIGO ------------------ \\
// ------------------ VIEJO CÓDIGO ------------------ \\
// ------------------ VIEJO CÓDIGO ------------------ \\
// ------------------ VIEJO CÓDIGO ------------------ \\
// ------------------ VIEJO CÓDIGO ------------------ \\
// ------------------ VIEJO CÓDIGO ------------------ \\



function addDigit(digit) {
  pantalla.value = pantalla.value + digit.value;
  if (estadoActual === ESTADOS.SIN_PRIMER_INPUT) {
    actualizarEstado(ESTADOS.CON_PRIMER_INPUT);
  }
  if (estadoActual === ESTADOS.SIN_SEGUNDO_INPUT) {
    actualizarEstado(ESTADOS.CON_SEGUNDO_INPUT);
  }
}

function addNumber() {
  primerNumero = Number(pantalla.value);
  pantalla.value = null;
  actualizarEstado(ESTADOS.SIN_SEGUNDO_INPUT);
}

// TODO - Arreglar los botones estando activos
function calculate() {
  segundoNumero = Number(pantalla.value);
  const result = primerNumero + segundoNumero;
  pantalla.value = result;
  actualizarEstado(ESTADOS.CON_RESULTADO);
}

function resetCalc() {
  pantalla.value = null;
  primerNumero = null;
  segundoNumero = null;
  actualizarEstado(ESTADOS.SIN_PRIMER_INPUT);
}