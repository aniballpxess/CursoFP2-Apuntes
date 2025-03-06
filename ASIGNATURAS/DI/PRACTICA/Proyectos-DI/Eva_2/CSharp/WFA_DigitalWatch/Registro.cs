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

        public Registro(String boton)
        {
            this.fecha = DateTime.Now.ToString("dd/MM/yyyy");
            this.horaReloj = DateTime.Now.ToString("HH:mm");
            this.boton = boton;
        }
    }
}
