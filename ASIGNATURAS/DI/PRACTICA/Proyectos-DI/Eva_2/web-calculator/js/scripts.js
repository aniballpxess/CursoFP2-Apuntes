const pantalla = document.getElementById('pantalla');
const btn_sumar = document.getElementById('btn_S');
const btn_calcular = document.getElementById('btn_C');

let primerNumero = null;
let segundoNumero = null;

const ESTADOS = {
  SIN_PRIMER_INPUT: 1,
  CON_PRIMER_INPUT: 2,
  SIN_SEGUNDO_INPUT: 3,
  CON_SEGUNDO_INPUT: 4,
};

let estadoActual = ESTADOS.SIN_PRIMER_INPUT;

function updateButtonStates() {
  switch (estadoActual) {
    case ESTADOS.SIN_PRIMER_INPUT:
      btn_sumar.disabled = true;
      btn_calcular.disabled = true;
      break;
    case ESTADOS.CON_PRIMER_INPUT:
      btn_sumar.disabled = false;
      btn_calcular.disabled = true;
      break;
    case ESTADOS.SIN_SEGUNDO_INPUT:
      btn_sumar.disabled = true;
      btn_calcular.disabled = true;
      break;
    case ESTADOS.CON_SEGUNDO_INPUT:
      btn_sumar.disabled = true;
      btn_calcular.disabled = false;
      break;
  }
}

function addDigit(digit) {
  pantalla.value += digit.value;

  if (estadoActual === ESTADOS.SIN_PRIMER_INPUT) {
    estadoActual = ESTADOS.CON_PRIMER_INPUT;
  } else if (estadoActual === ESTADOS.SIN_SEGUNDO_INPUT) {
    estadoActual = ESTADOS.CON_SEGUNDO_INPUT;
  }

  updateButtonStates();
}

function addNumber() {
  if (pantalla.value) {
    primerNumero = Number(pantalla.value);
    pantalla.value = '';
    estadoActual = ESTADOS.SIN_SEGUNDO_INPUT;
    updateButtonStates();
  }
}

function calculate() {
  if (pantalla.value) {
    segundoNumero = Number(pantalla.value);
    const result = primerNumero + segundoNumero;
    pantalla.value = result;
    primerNumero = null;
    segundoNumero = null;
    estadoActual = ESTADOS.SIN_PRIMER_INPUT;
    updateButtonStates();
  }
}

function resetCalc() {
  pantalla.value = '';
  primerNumero = null;
  segundoNumero = null;
  estadoActual = ESTADOS.SIN_PRIMER_INPUT;
  updateButtonStates();
}

updateButtonStates();