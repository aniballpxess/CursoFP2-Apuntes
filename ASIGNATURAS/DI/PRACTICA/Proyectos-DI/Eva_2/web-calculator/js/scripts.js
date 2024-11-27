const pantalla = document.getElementById('pantalla');
const btn_sumar = document.getElementById('btn_S');
const btn_calcular = document.getElementById('btn_C');
const btn_reiniciar = document.getElementById('btn_R');

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

// TODO - Plantearse una estructura y funcionamiento diferentes (como la de Fran)
function updateButtonStates() {
  switch (estadoActual) {
    case ESTADOS.SIN_PRIMER_INPUT:
      btn_sumar.disabled = true;
      btn_calcular.disabled = true;
      btn_reiniciar.disabled = true;
      break;
    case ESTADOS.CON_PRIMER_INPUT:
      btn_sumar.disabled = false;
      btn_calcular.disabled = true;
      btn_reiniciar.disabled = false;
      break;
    case ESTADOS.SIN_SEGUNDO_INPUT:
      btn_sumar.disabled = true;
      btn_calcular.disabled = true;
      btn_reiniciar.disabled = false;
      break;
    case ESTADOS.CON_SEGUNDO_INPUT:
      btn_sumar.disabled = true;
      btn_calcular.disabled = false;
      btn_reiniciar.disabled = false;
      break;
    case ESTADOS.CON_RESULTADO:
      btn_sumar.disabled = true;
      btn_calcular.disabled = true;
      btn_reiniciar.disabled = false;
      break;
  }
}

function loadCalc() {
  estadoActual = ESTADOS.SIN_PRIMER_INPUT;
  updateButtonStates();
}

function addDigit(digit) {
  pantalla.value = pantalla.value + digit.value;
  if (estadoActual === ESTADOS.SIN_PRIMER_INPUT) {
    estadoActual = ESTADOS.CON_PRIMER_INPUT;
    updateButtonStates();
  }
  if (estadoActual === ESTADOS.SIN_SEGUNDO_INPUT) {
    estadoActual = ESTADOS.CON_SEGUNDO_INPUT;
    updateButtonStates();
  }
}

function addNumber() {
  primerNumero = Number(pantalla.value);
  pantalla.value = null;
  estadoActual = ESTADOS.SIN_SEGUNDO_INPUT;
  updateButtonStates();
}

// TODO - Arreglar los botones estando activos
function calculate() {
  segundoNumero = Number(pantalla.value);
  const result = primerNumero + segundoNumero;
  pantalla.value = result;
  estadoActual = ESTADOS.CON_RESULTADO;
  updateButtonStates();
}

function resetCalc() {
  pantalla.value = null;
  primerNumero = null;
  segundoNumero = null;
  estadoActual = ESTADOS.SIN_PRIMER_INPUT;
  updateButtonStates();
}