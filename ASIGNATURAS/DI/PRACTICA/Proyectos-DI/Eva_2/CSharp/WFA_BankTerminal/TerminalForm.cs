using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;

namespace WFA_BankTerminal
{
    public partial class TerminalForm : Form
    {
        public class Operacion
        {
            public string Fecha;
            public string Hora;
            public string Tipo;
            public string Importe;
            public string Saldo;

            public Operacion(string tipo, string importe, string saldo)
            {
                Fecha = DateTime.Now.ToString("dd/MM/yyyy");
                Hora = DateTime.Now.ToString("HH:mm:ss");
                Tipo = tipo;
                Importe = importe;
                Saldo = saldo;
            }
        }
        private enum Estado { INTRODUCIR_TARJETA, SELECCION_OPERACION, CONSULTAR_SALDO, SACAR_DINERO, RETIRAR_DINERO, EXTRAER_TARJETA };

        private const decimal SALDO_INICIAL_TARJETA = 1000;
        private const string CONSULTAR_SALDO_LABEL = " <- Consultar Dinero";
        private const string SACAR_DINERO_LABEL = "Sacar Dinero -> ";
        private const string SALIR_LABEL = "Salir -> ";

        private decimal saldo_tarjeta;
        private decimal importe;
        private Estado estado;
        public List<Operacion> historial_operaciones = new List<Operacion>();

        public TerminalForm()
        {
            InitializeComponent();

            this.Text = "Terminal";

            toolTip.SetToolTip(btn_consultarSaldo, "Consultar Saldo");
            toolTip.SetToolTip(btn_sacarDinero, "Sacar Dinero");
            toolTip.SetToolTip(btn_salir, "Salir");

            saldo_tarjeta = SALDO_INICIAL_TARJETA;
            importe = 0.00m;

            textBox_consultarSaldo.Enabled = false;
            textBox_sacarDinero.Enabled = false;
            textBox_unused.Enabled = false;
            textBox_salir.Enabled = false;
            textBox_terminal.Enabled = false;

            estado = Estado.INTRODUCIR_TARJETA;
            ActualizarEstado();
        }

        private void ActualizarEstado()
        {
            switch (estado)
            {
                case Estado.INTRODUCIR_TARJETA:

                    btn_consultarSaldo.Enabled = false;
                    btn_sacarDinero.Enabled = false;
                    btn_salir.Enabled = false;

                    btn_numpad_1.Enabled = false;
                    btn_numpad_2.Enabled = false;
                    btn_numpad_3.Enabled = false;
                    btn_numpad_4.Enabled = false;
                    btn_numpad_5.Enabled = false;
                    btn_numpad_6.Enabled = false;
                    btn_numpad_7.Enabled = false;
                    btn_numpad_8.Enabled = false;
                    btn_numpad_9.Enabled = false;
                    btn_numpad_0.Enabled = false;
                    btn_numpad_intro.Enabled = false;

                    btn_tarjetaIE.Enabled = true;
                    btn_retirarDinero.Enabled = false;
                    btn_informe.Enabled = true;

                    textBox_consultarSaldo.Text = null;
                    textBox_sacarDinero.Text = null;
                    textBox_salir.Text = null;
                    textBox_terminal.Text = "Introduzca la tarjeta";
                    break;

                case Estado.SELECCION_OPERACION:

                    btn_consultarSaldo.Enabled = true;
                    btn_sacarDinero.Enabled = true;
                    btn_salir.Enabled = true;

                    btn_numpad_1.Enabled = false;
                    btn_numpad_2.Enabled = false;
                    btn_numpad_3.Enabled = false;
                    btn_numpad_4.Enabled = false;
                    btn_numpad_5.Enabled = false;
                    btn_numpad_6.Enabled = false;
                    btn_numpad_7.Enabled = false;
                    btn_numpad_8.Enabled = false;
                    btn_numpad_9.Enabled = false;
                    btn_numpad_0.Enabled = false;
                    btn_numpad_intro.Enabled = false;

                    btn_tarjetaIE.Enabled = false;
                    btn_retirarDinero.Enabled = false;
                    btn_informe.Enabled = true;

                    textBox_consultarSaldo.Text = CONSULTAR_SALDO_LABEL;
                    textBox_sacarDinero.Text = SACAR_DINERO_LABEL;
                    textBox_salir.Text = SALIR_LABEL;
                    textBox_terminal.Text = "Seleccione una operación";

                    importe = 0.00m;
                    break;

                case Estado.CONSULTAR_SALDO:

                    btn_consultarSaldo.Enabled = false;
                    btn_sacarDinero.Enabled = false;
                    btn_salir.Enabled = true;

                    btn_numpad_1.Enabled = false;
                    btn_numpad_2.Enabled = false;
                    btn_numpad_3.Enabled = false;
                    btn_numpad_4.Enabled = false;
                    btn_numpad_5.Enabled = false;
                    btn_numpad_6.Enabled = false;
                    btn_numpad_7.Enabled = false;
                    btn_numpad_8.Enabled = false;
                    btn_numpad_9.Enabled = false;
                    btn_numpad_0.Enabled = false;
                    btn_numpad_intro.Enabled = false;

                    btn_tarjetaIE.Enabled = false;
                    btn_retirarDinero.Enabled = false;
                    btn_informe.Enabled = true;

                    textBox_consultarSaldo.Text = null;
                    textBox_sacarDinero.Text = null;
                    textBox_salir.Text = SALIR_LABEL;
                    textBox_terminal.Text = "Saldo" + Environment.NewLine + Environment.NewLine + saldo_tarjeta + " €";
                    break;

                case Estado.SACAR_DINERO:

                    btn_consultarSaldo.Enabled = false;
                    btn_sacarDinero.Enabled = false;
                    btn_salir.Enabled = true;

                    btn_numpad_1.Enabled = true;
                    btn_numpad_2.Enabled = true;
                    btn_numpad_3.Enabled = true;
                    btn_numpad_4.Enabled = true;
                    btn_numpad_5.Enabled = true;
                    btn_numpad_6.Enabled = true;
                    btn_numpad_7.Enabled = true;
                    btn_numpad_8.Enabled = true;
                    btn_numpad_9.Enabled = true;
                    btn_numpad_0.Enabled = true;
                    btn_numpad_intro.Enabled = true;

                    btn_tarjetaIE.Enabled = false;
                    btn_retirarDinero.Enabled = false;
                    btn_informe.Enabled = true;

                    textBox_consultarSaldo.Text = null;
                    textBox_sacarDinero.Text = null;
                    textBox_salir.Text = SALIR_LABEL;
                    textBox_terminal.Text = "Introduce el importe y pulse [->]" + Environment.NewLine + Environment.NewLine + importe + " €";
                    break;

                case Estado.RETIRAR_DINERO:

                    btn_consultarSaldo.Enabled = false;
                    btn_sacarDinero.Enabled = false;
                    btn_salir.Enabled = false;

                    btn_numpad_1.Enabled = false;
                    btn_numpad_2.Enabled = false;
                    btn_numpad_3.Enabled = false;
                    btn_numpad_4.Enabled = false;
                    btn_numpad_5.Enabled = false;
                    btn_numpad_6.Enabled = false;
                    btn_numpad_7.Enabled = false;
                    btn_numpad_8.Enabled = false;
                    btn_numpad_9.Enabled = false;
                    btn_numpad_0.Enabled = false;
                    btn_numpad_intro.Enabled = false;

                    btn_tarjetaIE.Enabled = false;
                    btn_retirarDinero.Enabled = true;
                    btn_informe.Enabled = true;

                    textBox_consultarSaldo.Text = null;
                    textBox_sacarDinero.Text = null;
                    textBox_salir.Text = null;
                    textBox_terminal.Text = "Retire dinero";
                    break;

                case Estado.EXTRAER_TARJETA:

                    btn_consultarSaldo.Enabled = false;
                    btn_sacarDinero.Enabled = false;
                    btn_salir.Enabled = false;

                    btn_numpad_1.Enabled = false;
                    btn_numpad_2.Enabled = false;
                    btn_numpad_3.Enabled = false;
                    btn_numpad_4.Enabled = false;
                    btn_numpad_5.Enabled = false;
                    btn_numpad_6.Enabled = false;
                    btn_numpad_7.Enabled = false;
                    btn_numpad_8.Enabled = false;
                    btn_numpad_9.Enabled = false;
                    btn_numpad_0.Enabled = false;
                    btn_numpad_intro.Enabled = false;

                    btn_tarjetaIE.Enabled = true;
                    btn_retirarDinero.Enabled = false;
                    btn_informe.Enabled = true;

                    textBox_consultarSaldo.Text = null;
                    textBox_sacarDinero.Text = null;
                    textBox_salir.Text = null;
                    textBox_terminal.Text = "Extraiga tarjeta";
                    break;
            }
        }

        private void btn_consultarSaldo_Click(object sender, EventArgs e)
        {
            Operacion op = new Operacion("Consultar Saldo", null, saldo_tarjeta.ToString());
            historial_operaciones.Add(op);

            estado = Estado.CONSULTAR_SALDO;
            ActualizarEstado();
        }

        private void btn_sacarDinero_Click(object sender, EventArgs e)
        {
            estado = Estado.SACAR_DINERO;
            ActualizarEstado();
        }

        private void btn_salir_Click(object sender, EventArgs e)
        {
            if (estado == Estado.SELECCION_OPERACION)
            {
                estado = Estado.EXTRAER_TARJETA;
            }
            else
            {
                estado = Estado.SELECCION_OPERACION;
            }
            ActualizarEstado();
        }

        private void btn_numpad_1_Click(object sender, EventArgs e)
        {
            importe = importe * 10 + 1;
            estado = Estado.SACAR_DINERO;
            ActualizarEstado();
        }

        private void btn_numpad_2_Click(object sender, EventArgs e)
        {
            importe = importe * 10 + 2;
            estado = Estado.SACAR_DINERO;
            ActualizarEstado();
        }

        private void btn_numpad_3_Click(object sender, EventArgs e)
        {
            importe = importe * 10 + 3;
            estado = Estado.SACAR_DINERO;
            ActualizarEstado();
        }

        private void btn_numpad_4_Click(object sender, EventArgs e)
        {
            importe = importe * 10 + 4;
            estado = Estado.SACAR_DINERO;
            ActualizarEstado();
        }

        private void btn_numpad_5_Click(object sender, EventArgs e)
        {
            importe = importe * 10 + 5;
            estado = Estado.SACAR_DINERO;
            ActualizarEstado();
        }

        private void btn_numpad_6_Click(object sender, EventArgs e)
        {
            importe = importe * 10 + 6;
            estado = Estado.SACAR_DINERO;
            ActualizarEstado();
        }

        private void btn_numpad_7_Click(object sender, EventArgs e)
        {
            importe = importe * 10 + 7;
            estado = Estado.SACAR_DINERO;
            ActualizarEstado();
        }

        private void btn_numpad_8_Click(object sender, EventArgs e)
        {
            importe = importe * 10 + 8;
            estado = Estado.SACAR_DINERO;
            ActualizarEstado();
        }

        private void btn_numpad_9_Click(object sender, EventArgs e)
        {
            importe = importe * 10 + 9;
            estado = Estado.SACAR_DINERO;
            ActualizarEstado();
        }

        private void btn_numpad_0_Click(object sender, EventArgs e)
        {
            importe = importe * 10;
            estado = Estado.SACAR_DINERO;
            ActualizarEstado();
        }

        private void btn_numpad_intro_Click(object sender, EventArgs e)
        {
            saldo_tarjeta = saldo_tarjeta - importe;
            Operacion op = new Operacion("Sacar Dinero", importe.ToString(), saldo_tarjeta.ToString());
            historial_operaciones.Add(op);

            estado = Estado.RETIRAR_DINERO;
            ActualizarEstado();
        }

        private void btn_tarjetaIE_Click(object sender, EventArgs e)
        {
            if (estado == Estado.EXTRAER_TARJETA)
            {
                estado = Estado.INTRODUCIR_TARJETA;
            }
            else
            {
                estado = Estado.SELECCION_OPERACION;
            }
            ActualizarEstado();
        }

        private void btn_retirarDinero_Click(object sender, EventArgs e)
        {
            estado = Estado.SELECCION_OPERACION;
            ActualizarEstado();
        }

        private void btn_informe_Click(object sender, EventArgs e)
        {
            InformeForm informeForm = new InformeForm(historial_operaciones);
            informeForm.ShowDialog();
        }
    }
}
