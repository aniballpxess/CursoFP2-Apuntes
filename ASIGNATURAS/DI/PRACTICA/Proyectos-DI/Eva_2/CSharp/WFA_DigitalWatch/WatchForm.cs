using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WFA_DigitalWatch
{
    public partial class WatchForm : Form
    {
        private enum Estado { PARADO, AJUSTANDO_HORA, AJUSTANDO_MIN };
        private Estado estadoActual;
        public List<Registro> registros = new List<Registro>();

        private int min;
        private int hora;
        bool iluminado;

        public WatchForm()
        {
            InitializeComponent();

            toolTip1.SetToolTip(Btn_Ajustar, "Haz click para ajustar la hora");

            min = 00;
            hora = 00;
            iluminado = false;

            estadoActual = Estado.PARADO;
            ActualizarEstado();
        }

        private void ActualizarEstado()
        {
            switch (estadoActual)
            {
                case Estado.PARADO:
                    Btn_Incrementar.Enabled = false;
                    Btn_Decrementar.Enabled = false;
                    tb_hora.Text = hora.ToString("00");
                    tb_min.Text = min.ToString("00");
                    tb_hora.ForeColor = Color.White;
                    tb_min.ForeColor = Color.White;
                    break;

                case Estado.AJUSTANDO_HORA:
                    Btn_Incrementar.Enabled = true;
                    Btn_Decrementar.Enabled = true;
                    tb_hora.Text = hora.ToString("00");
                    tb_min.Text = min.ToString("00");
                    tb_hora.ForeColor = Color.Red;
                    tb_min.ForeColor = Color.White;
                    break;

                case Estado.AJUSTANDO_MIN:
                    Btn_Incrementar.Enabled = true;
                    Btn_Decrementar.Enabled = true;
                    tb_hora.Text = hora.ToString("00");
                    tb_min.Text = min.ToString("00");
                    tb_hora.ForeColor = Color.White;
                    tb_min.ForeColor = Color.Red;
                    break;
            }
        }

        private void Btn_Ajustar_Click(object sender, EventArgs e)
        {
            if (estadoActual == Estado.PARADO)
            {
                estadoActual = Estado.AJUSTANDO_HORA;
                ActualizarEstado();
                return;
            }
            if (estadoActual == Estado.AJUSTANDO_HORA)
            {
                estadoActual = Estado.AJUSTANDO_MIN;
                ActualizarEstado();
                return;
            }
            if (estadoActual == Estado.AJUSTANDO_MIN)
            {
                estadoActual = Estado.PARADO;
                ActualizarEstado();

                registros.Add(new Registro("Ajustar"));
                return;
            }
        }

        private void Btn_Decrementar_Click(object sender, EventArgs e)
        {
            if (estadoActual == Estado.AJUSTANDO_HORA)
            {
                hora--;
                if (hora < 0)
                {
                    hora = 23;
                }
                ActualizarEstado();

                registros.Add(new Registro("Ajustar"));
                return;
            }
            if (estadoActual == Estado.AJUSTANDO_MIN)
            {
                min--;
                if (min < 0)
                {
                    min = 59;
                }
                ActualizarEstado();

                registros.Add(new Registro("Ajustar"));
                return;
            }
        }

        private void Btn_Incrementar_Click(object sender, EventArgs e)
        {
            if (estadoActual == Estado.AJUSTANDO_HORA)
            {
                hora++;
                if (hora > 23)
                {
                    hora = 0;
                }
                ActualizarEstado();

                registros.Add(new Registro("Ajustar"));
                return;
            }
            if (estadoActual == Estado.AJUSTANDO_MIN)
            {
                min++;
                if (min > 59)
                {
                    min = 0;
                }
                ActualizarEstado();

                registros.Add(new Registro("Ajustar"));
                return;
            }
        }

        private void Btn_Informe_Click(object sender, EventArgs e)
        {
            InformeForm informeForm = new InformeForm(registros);
            informeForm.ShowDialog();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (iluminado)
            {
                tb_hora.BackColor = Color.Black;
                tb_min.BackColor = Color.Black;
                tb_separador.BackColor = Color.Black;
            }
            else
            {
                tb_hora.BackColor = Color.LightGreen;
                tb_min.BackColor = Color.LightGreen;
                tb_separador.BackColor = Color.LightGreen;
            }
            iluminado = !iluminado;
        }
    }
}
