const historial = document.getElementById('historial');

const pantalla_operacion = document.getElementById('operacion');
const pantalla_numero = document.getElementById('numero');

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

let operacionEnPantalla = "";
let numeroEnPantalla = "";
let primerNumero = null;
let operando = null;
let segundoNumero = null;
let resultado = null;

let historialOperaciones = [];

// ----------------- FUNCION DE CAMBIO DE ESTADOS ----------------- \\

function actualizarEstado(nuevoEstado) {
  estadoActual = nuevoEstado;

  switch (estadoActual) {
    case ESTADOS.SIN_PRIMER_INPUT:
      // HISTORIAL OPERACIONES
      historialOperaciones = [];
      historial.replaceChildren();
      // VALORES INTERNOS
      operacionEnPantalla = "";
      numeroEnPantalla = "";
      
      primerNumero = null;
      operando = null;
      segundoNumero = null;
      resultado = null;
      // INTERFÁZ CALCULADORA
      // Teclas
      btn_punto.disabled = false;
      btn_zero.disabled = false;
      btn_uno.disabled = false;
      btn_dos.disabled = false;
      btn_tres.disabled = false;
      btn_cuatro.disabled = false;
      btn_cinco.disabled = false;
      btn_seis.disabled = false;
      btn_siete.disabled = false;
      btn_ocho.disabled = false;
      btn_nueve.disabled = false;

      btn_sumar.disabled = true;
      btn_restar.disabled = true;
      btn_multiplicar.disabled = true;
      btn_dividir.disabled = true;

      btn_borrar.disabled = true;

      btn_calcular.disabled = true;

      btn_reiniciar.disabled = true;

      btn_limpiar.disabled = true;
      break;

    case ESTADOS.CON_PRIMER_INPUT:
      // VALORES INTERNOS
      primerNumero = null;
      operando = null;
      segundoNumero = null;
      resultado = null;
      // INTERFÁZ USUARIO
      // Teclas
      btn_punto.disabled = false;
      btn_zero.disabled = false;
      btn_uno.disabled = false;
      btn_dos.disabled = false;
      btn_tres.disabled = false;
      btn_cuatro.disabled = false;
      btn_cinco.disabled = false;
      btn_seis.disabled = false;
      btn_siete.disabled = false;
      btn_ocho.disabled = false;
      btn_nueve.disabled = false;

      btn_sumar.disabled = false;
      btn_restar.disabled = false;
      btn_multiplicar.disabled = false;
      btn_dividir.disabled = false;

      btn_borrar.disabled = false;

      btn_calcular.disabled = true;

      btn_reiniciar.disabled = false;

      btn_limpiar.disabled = false;
      break;

    case ESTADOS.SIN_SEGUNDO_INPUT:
      // VALORES INTERNOS
      numeroEnPantalla = "";
      segundoNumero = null;
      resultado = null;
      // INTERFÁZ USUARIO
      // Teclas
      btn_punto.disabled = false;
      btn_zero.disabled = false;
      btn_uno.disabled = false;
      btn_dos.disabled = false;
      btn_tres.disabled = false;
      btn_cuatro.disabled = false;
      btn_cinco.disabled = false;
      btn_seis.disabled = false;
      btn_siete.disabled = false;
      btn_ocho.disabled = false;
      btn_nueve.disabled = false;

      btn_sumar.disabled = true;
      btn_restar.disabled = true;
      btn_multiplicar.disabled = true;
      btn_dividir.disabled = true;

      btn_borrar.disabled = true;

      btn_calcular.disabled = true;

      btn_reiniciar.disabled = false;

      btn_limpiar.disabled = true;
      break;

    case ESTADOS.CON_SEGUNDO_INPUT:
      // VALORES INTERNOS
      segundoNumero = null;
      resultado = null;
      // INTERFÁZ USUARIO
      // Teclas
      btn_punto.disabled = false;
      btn_zero.disabled = false;
      btn_uno.disabled = false;
      btn_dos.disabled = false;
      btn_tres.disabled = false;
      btn_cuatro.disabled = false;
      btn_cinco.disabled = false;
      btn_seis.disabled = false;
      btn_siete.disabled = false;
      btn_ocho.disabled = false;
      btn_nueve.disabled = false;

      btn_sumar.disabled = false;
      btn_restar.disabled = false;
      btn_multiplicar.disabled = false;
      btn_dividir.disabled = false;

      btn_borrar.disabled = false;

      btn_calcular.disabled = false;

      btn_reiniciar.disabled = false;

      btn_limpiar.disabled = false;
      break;

    case ESTADOS.CON_RESULTADO:
      // INTERFÁZ USUARIO
      // Teclas
      btn_punto.disabled = true;
      btn_zero.disabled = true;
      btn_uno.disabled = true;
      btn_dos.disabled = true;
      btn_tres.disabled = true;
      btn_cuatro.disabled = true;
      btn_cinco.disabled = true;
      btn_seis.disabled = true;
      btn_siete.disabled = true;
      btn_ocho.disabled = true;
      btn_nueve.disabled = true;

      btn_sumar.disabled = true;
      btn_restar.disabled = true;
      btn_multiplicar.disabled = true;
      btn_dividir.disabled = true;

      btn_borrar.disabled = true;

      btn_calcular.disabled = true;

      btn_reiniciar.disabled = false;

      btn_limpiar.disabled = true;
      break;
  }
}

// ----------------- FUNCION DE GESTIÓN PRINCIPAL ----------------- \\

function gestionarClick(btn_activado) {
  // Gestionar entrada
  switch (btn_activado.id) {
    case btn_punto.id:
    case btn_zero.id:
    case btn_uno.id:
    case btn_dos.id:
    case btn_tres.id:
    case btn_cuatro.id:
    case btn_cinco.id:
    case btn_seis.id:
    case btn_siete.id:
    case btn_ocho.id:
    case btn_nueve.id:
      gestionarDigito(btn_activado);
      break;
    case btn_sumar.id:
    case btn_restar.id:
    case btn_multiplicar.id:
    case btn_dividir.id:
      gestionarOperando(btn_activado);
      break;
    case btn_borrar.id:
      gestionarBorrado();
      break;
    case btn_calcular.id:
      gestionarCalculo();
      break;
    case btn_reiniciar.id:
      gestionarReinicio();
      break;
    case btn_limpiar.id:
      gestionarLimpieza();
    default:
      break;
  }
  // Actualizar Pantalla
  pantalla_operacion.textContent = operacionEnPantalla;
  pantalla_numero.textContent = numeroEnPantalla;
}

// --------------- FUNCIONES DE GESTION SECUNDARIAS --------------- \\

function gestionarDigito(btn_digito) {
  if (estadoActual === ESTADOS.SIN_PRIMER_INPUT) {
    numeroEnPantalla = numeroEnPantalla + btn_digito.value;
    actualizarEstado(ESTADOS.CON_PRIMER_INPUT);
    return;
  }
  if (estadoActual === ESTADOS.CON_PRIMER_INPUT) {
    numeroEnPantalla = numeroEnPantalla + btn_digito.value;
    return;
  }
  if (estadoActual === ESTADOS.SIN_SEGUNDO_INPUT) {
    numeroEnPantalla = numeroEnPantalla + btn_digito.value;
    actualizarEstado(ESTADOS.CON_SEGUNDO_INPUT);
    return;
  }
  if (estadoActual === ESTADOS.CON_SEGUNDO_INPUT) {
    numeroEnPantalla = numeroEnPantalla + btn_digito.value;
    return;
  }
}

function gestionarOperando(btn_operando) {
  if (estadoActual === ESTADOS.CON_PRIMER_INPUT) {
    primerNumero = parseFloat(numeroEnPantalla);
  }
  if (estadoActual === ESTADOS.CON_SEGUNDO_INPUT) {
    segundoNumero = parseFloat(numeroEnPantalla);
    resultado = realizarCalculo();
    guardarOperacion();
    primerNumero = resultado;
  }
  operando = btn_operando.value;
  actualizarEstado(ESTADOS.SIN_SEGUNDO_INPUT);
  mostrarOperacion();
}

function gestionarBorrado() {
  numeroEnPantalla = numeroEnPantalla.substring(0, numeroEnPantalla.length - 1);
  if (numeroEnPantalla.length === 0 && estadoActual === ESTADOS.CON_PRIMER_INPUT) {
    actualizarEstado(ESTADOS.SIN_PRIMER_INPUT);
  }
  if (numeroEnPantalla.length === 0 && estadoActual === ESTADOS.CON_SEGUNDO_INPUT) {
    actualizarEstado(ESTADOS.SIN_SEGUNDO_INPUT);
  }
}

function gestionarCalculo() {
  segundoNumero = parseFloat(numeroEnPantalla);
  resultado = realizarCalculo();
  guardarOperacion();
  numeroEnPantalla = resultado;
  actualizarEstado(ESTADOS.CON_RESULTADO);
  mostrarOperacion();
}

function gestionarReinicio() {
  actualizarEstado(ESTADOS.SIN_PRIMER_INPUT);
}

function gestionarLimpieza() {
  if (estadoActual === ESTADOS.CON_PRIMER_INPUT) {
    actualizarEstado(ESTADOS.SIN_PRIMER_INPUT);
  }
  if (estadoActual === ESTADOS.CON_SEGUNDO_INPUT) {
    actualizarEstado(ESTADOS.SIN_SEGUNDO_INPUT);
  }
}

// --------------------- FUNCIONES AUXILIARES --------------------- \\

function guardarOperacion() {
  let operacion = `${primerNumero} ${operando} ${segundoNumero} = ${resultado}`;
  historialOperaciones.push(operacion);

  let registroOperacion = document.createElement('div');
  registroOperacion.setAttribute('class', 'operacion bg_gris_c');
  registroOperacion.textContent = operacion;
  historial.appendChild(registroOperacion);
}

function mostrarOperacion() {
  let operacion;
  if (estadoActual === ESTADOS.SIN_SEGUNDO_INPUT) {
    operacion = `${primerNumero} ${operando}`;
  }
  if (estadoActual === ESTADOS.CON_RESULTADO) {
    operacion = `${primerNumero} ${operando} ${segundoNumero} =`;
  }
  operacionEnPantalla = operacion;
}

function realizarCalculo() {
  let calculo;
  switch (operando) {
    case btn_sumar.value:
      calculo = primerNumero + segundoNumero;
      break;
    case btn_restar.value:
      calculo = primerNumero - segundoNumero;
      break;
    case btn_multiplicar.value:
      calculo = primerNumero * segundoNumero;
      break;
    case btn_dividir.value:
      calculo = primerNumero / segundoNumero;
      break;
    default:
      break;
  }
  return calculo;
}

// --------------------- CARGA DE CALCULADORA --------------------- \\

function cargarCalcualdora() {
  cargarTeclado();
  actualizarEstado(ESTADOS.SIN_PRIMER_INPUT);
}

function cargarTeclado() {
  // INICILIZAR ICONOS
  // Fila 1
  btn_reiniciar.textContent = "C";
  btn_limpiar.textContent = "CE";
  btn_borrar.innerHTML = "&#x232B;";
  btn_dividir.textContent = "/";
  // Fila 2
  btn_siete.textContent = "7";
  btn_ocho.textContent = "8";
  btn_nueve.textContent = "9";
  btn_multiplicar.textContent = "x";
  // Fila 3
  btn_cuatro.textContent = "4";
  btn_cinco.textContent = "5";
  btn_seis.textContent = "6";
  btn_restar.textContent = "-";
  // Fila 4
  btn_uno.textContent = "1";
  btn_dos.textContent = "2";
  btn_tres.textContent = "3";
  btn_sumar.textContent = "+";
  // Fila 5
  btn_zero.textContent = "0";
  btn_punto.textContent = ".";
  btn_calcular.textContent = "=";
  // INICIALIZAR VALORES
  // Fila 1
  btn_reiniciar.value == null;
  btn_limpiar.value == null;
  btn_borrar.value == null;
  btn_dividir.value = btn_dividir.textContent;
  // Fila 2
  btn_siete.value = btn_siete.textContent;
  btn_ocho.value = btn_ocho.textContent;
  btn_nueve.value = btn_nueve.textContent;
  btn_multiplicar.value = btn_multiplicar.textContent;
  // Fila 3
  btn_cuatro.value = btn_cuatro.textContent;
  btn_cinco.value = btn_cinco.textContent;
  btn_seis.value = btn_seis.textContent;
  btn_restar.value = btn_restar.textContent;
  // Fila 4
  btn_uno.value = btn_uno.textContent;
  btn_dos.value = btn_dos.textContent;
  btn_tres.value = btn_tres.textContent;
  btn_sumar.value = btn_sumar.textContent;
  // Fila 5
  btn_zero.value = btn_zero.textContent;
  btn_punto.value = btn_punto.textContent;
  btn_calcular.value == null;
}