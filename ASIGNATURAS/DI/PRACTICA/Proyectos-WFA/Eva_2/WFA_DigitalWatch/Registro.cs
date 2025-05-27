using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WFA_DigitalWatch
{
    public class Registro
    {
        public String fecha;
        public String horaReloj;
        public String boton;

        public Registro(String boton, int horas, int minutos)
        {
            this.fecha = DateTime.Now.ToString("dd/MM/yyyy");
            this.horaReloj = $"{horas:D2}:{minutos:D2}";
            this.boton = boton;
        }
    }
}
