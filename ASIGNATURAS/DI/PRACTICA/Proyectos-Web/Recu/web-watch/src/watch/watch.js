const btn_ajustar = document.getElementById('btn-ajustar');
const btn_iluminar = document.getElementById('btn-iluminar');
const btn_incrementar = document.getElementById('btn-incrementar');
const btn_decrementar = document.getElementById('btn-decrementar');

const dis_fondo = document.getElementById('display-fondo');
const dis_horas = document.getElementById('display-horas');
const dis_minutos = document.getElementById('display-minutos');

const ESTADOS = {
    SIN_AJUSTAR: 0,
    AJUSTAR_HORAS: 1,
    AJUSTAR_MIN: 2,
};

let iluminado = false;
let estado = ESTADOS.SIN_AJUSTAR;
let horas = 0;
let minutos = 0;

function cambiarEstado() {
    switch (estado) {
        case ESTADOS.SIN_AJUSTAR:
            dis_horas.style.color = '#404040'; // Dark Gray
            dis_minutos.style.color = '#404040'; // Dark Gray
            break;
        case ESTADOS.AJUSTAR_HORAS:
            dis_horas.style.color = 'darkred';
            dis_minutos.style.color = '#404040'; // Dark Gray
            break;
        case ESTADOS.AJUSTAR_MIN:
            dis_horas.style.color = '#404040'; // Dark Gray
            dis_minutos.style.color = 'darkred';
            break;
    }
}

btn_ajustar.addEventListener('click', () => {
    switch (estado) {
        case ESTADOS.SIN_AJUSTAR:
            estado = ESTADOS.AJUSTAR_HORAS;
            cambiarEstado();
            break;
        case ESTADOS.AJUSTAR_HORAS:
            estado = ESTADOS.AJUSTAR_MIN;
            cambiarEstado();
            break;
        case ESTADOS.AJUSTAR_MIN:
            estado = ESTADOS.SIN_AJUSTAR;
            cambiarEstado();
            break;
    }
});
btn_iluminar.addEventListener('click', () => {
    if (iluminado) {
        dis_fondo.style.backgroundColor = 'black';
    } else {
        dis_fondo.style.backgroundColor = 'lightgreen';
    }
    iluminado = !iluminado;
});
