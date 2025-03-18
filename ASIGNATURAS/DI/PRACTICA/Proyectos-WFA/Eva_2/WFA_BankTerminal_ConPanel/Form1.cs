using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;

namespace WFA_BankTerminal_ConPanel
{
    public partial class Form1 : Form
    {
        private int estado = 1; // 1 = Introducir Tarjeta, 2 = Seleccionar Operación, etc.
        private string montoIngresado = ""; // Almacena el monto a retirar
        private int saldoActual = 1000; // SALDO DEL USUARIO, comienza en 1000

        private List<string[]> historialMovimientos = new List<string[]>();

        public Form1()
        {
            InitializeComponent();

            // Vincular eventos de los botones numéricos y el botón ENTER
            btn_0.Click += btn_numero_Click;
            btn_1.Click += btn_numero_Click;
            btn_2.Click += btn_numero_Click;
            btn_3.Click += btn_numero_Click;
            btn_4.Click += btn_numero_Click;
            btn_5.Click += btn_numero_Click;
            btn_6.Click += btn_numero_Click;
            btn_7.Click += btn_numero_Click;
            btn__8.Click += btn_numero_Click;
            btn_9.Click += btn_numero_Click;
            btn_enter.Click += btn_enter_Click_1;

            ActualizarEstado();
        }

        private void ActualizarEstado()
        {
            switch (estado)
            {
                case 1: // Introducir tarjeta
                    label_texto.Text = "Introduzca tarjeta";
                    
                    btn_tarjeta.Enabled = true;
                    btn_dinero.Enabled = false;

                    consult_saldo.Enabled = false;
                    sacar_dinero.Enabled = false;
                    salir.Enabled = false;

                    btn_0.Enabled = false;
                    btn_1.Enabled = false;
                    btn_2.Enabled = false;
                    btn_3.Enabled = false;
                    btn_4.Enabled = false;
                    btn_5.Enabled = false;
                    btn_6.Enabled = false;
                    btn_7.Enabled = false;
                    btn__8.Enabled = false;
                    btn_9.Enabled = false;
                    btn_enter.Enabled = false;

                    break;

                case 2: // Seleccionar operación
                    label_texto.Text = "Seleccione operación";

                    btn_tarjeta.Enabled = false;
                    btn_dinero.Enabled = false;

                    consult_saldo.Enabled = true;
                    sacar_dinero.Enabled = true;
                    salir.Enabled = true;

                    btn_0.Enabled = false;
                    btn_1.Enabled = false;
                    btn_2.Enabled = false;
                    btn_3.Enabled = false;
                    btn_4.Enabled = false;
                    btn_5.Enabled = false;
                    btn_6.Enabled = false;
                    btn_7.Enabled = false;
                    btn__8.Enabled = false;
                    btn_9.Enabled = false;
                    btn_enter.Enabled = false;

                    break;

                case 3: // Consultar saldo
                    label_texto.Text = "Su saldo es: $" + saldoActual;
                    break;

                case 4: // Sacar dinero - 1
                    montoIngresado = ""; // Reiniciar monto al entrar
                    label_texto.Text = "Introduzca un importe y pulse ->";


                    btn_0.Enabled = true;
                    btn_1.Enabled = true;
                    btn_2.Enabled = true;
                    btn_3.Enabled = true;
                    btn_4.Enabled = true;
                    btn_5.Enabled = true;
                    btn_6.Enabled = true;
                    btn_7.Enabled = true;
                    btn__8.Enabled = true;
                    btn_9.Enabled = true;
                    btn_enter.Enabled = true;

                    btn_dinero.Enabled = false;


                    break;

                case 5: // Sacar dinero - 2 (confirmación)
                    label_texto.Text = "Confirme el retiro de $" + montoIngresado;

                    btn_dinero.Enabled = true;
                    break;
            }
        }

        private void btn_tarjeta_Click(object sender, EventArgs e)
        {
            if (estado == 1)
            {
                estado = 2;
            }
            ActualizarEstado();
        }

        private void consult_saldo_Click_1(object sender, EventArgs e)
        {
            if (estado == 2)
            {
                estado = 3; // Ir a "Consultar saldo"

                // Agregar registro de consulta de saldo
                historialMovimientos.Add(new string[]
                {
            DateTime.Now.ToString("yyyy-MM-dd"),  // Fecha actual
            DateTime.Now.ToString("HH:mm:ss"),  // Hora actual
            "Consultar saldo",  // Operación
            "-",  // Sin importe
            saldoActual.ToString() // Saldo actual
                });

                ActualizarEstado();
            }
        }

        private void sacar_dinero_Click_1(object sender, EventArgs e)
        {
            if (estado == 2)
            {
                estado = 4;
                ActualizarEstado();
            }
        }

        private void salir_Click(object sender, EventArgs e)
        {
            if (estado == 3 || estado == 4 || estado == 5)
            {
                estado = 2;
                montoIngresado = ""; // Reiniciar monto al salir
                ActualizarEstado();
            }
            else if (estado == 2)
            {
                estado = 1;
                ActualizarEstado();
            }
        }

        private void btn_numero_Click(object sender, EventArgs e)
        {
            if (estado == 4)
            {
                Button btn = (Button)sender;
                montoIngresado += btn.Text;
                label_texto.Text = "Monto: $" + montoIngresado;
            }
        }

        private void btn_enter_Click_1(object sender, EventArgs e)
        {
            if (estado == 4)
            {
                if (!string.IsNullOrEmpty(montoIngresado))
                {
                    estado = 5;
                    ActualizarEstado();
                }
                else
                {
                    MessageBox.Show("Por favor, ingrese un monto antes de continuar.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                }
            }
        }

        private void btn_dinero_Click(object sender, EventArgs e)
        {
            if (estado == 5)
            {
                int montoRetiro;
                if (int.TryParse(montoIngresado, out montoRetiro))
                {
                    if (montoRetiro > 0 && montoRetiro <= saldoActual)
                    {
                        saldoActual -= montoRetiro; // Restar saldo

                        MessageBox.Show("Retiro exitoso de $" + montoRetiro + "\nSaldo restante: $" + saldoActual,
                            "Confirmación", MessageBoxButtons.OK, MessageBoxIcon.Information);

                        // Registrar el retiro en el historial
                        historialMovimientos.Add(new string[]
                        {
                    DateTime.Now.ToString("yyyy-MM-dd"),  // Fecha actual
                    DateTime.Now.ToString("HH:mm:ss"),  // Hora actual
                    "Sacar dinero",  // Operación
                    montoRetiro.ToString(),  // Importe retirado
                    saldoActual.ToString() // Saldo después del retiro
                        });
                    }
                    else
                    {
                        MessageBox.Show("Fondos insuficientes o cantidad inválida.",
                            "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                    }
                }
                else
                {
                    MessageBox.Show("Ingrese un monto válido.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                }

                montoIngresado = ""; // Reiniciar monto
                estado = 2;
                ActualizarEstado();
            }
        }

        private void btn_Informe_Click(object sender, EventArgs e)
        {
            informe frmInforme = new informe(historialMovimientos);
            frmInforme.ShowDialog();
        }
    }
}