using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

using Operacion = WFA_BankTerminal.TerminalForm.Operacion;

namespace WFA_BankTerminal
{
    public partial class InformeForm : Form
    {
        public List<Operacion> lista_operaciones;
        public DataTable dt_informeOperaciones;

        public InformeForm(List<Operacion> historial_operaciones)
        {
            InitializeComponent();
            this.Text = "Informe";

            lista_operaciones = historial_operaciones;
            dt_informeOperaciones = new DataTable();

            dt_informeOperaciones.Columns.Add("Fecha");
            dt_informeOperaciones.Columns.Add("Hora");
            dt_informeOperaciones.Columns.Add("Operacion");
            dt_informeOperaciones.Columns.Add("Importe (€)");
            dt_informeOperaciones.Columns.Add("Saldo (€)");

            foreach (Operacion op in lista_operaciones)
            {
                DataRow fila = dt_informeOperaciones.NewRow();
                fila["Fecha"] = op.Fecha;
                fila["Hora"] = op.Hora;
                fila["Operacion"] = op.Tipo;
                fila["Importe (€)"] = op.Importe;
                fila["Saldo (€)"] = op.Saldo;
                dt_informeOperaciones.Rows.Add(fila);
            }

            dgv_operaciones.DataSource = dt_informeOperaciones;
        }

        private void btn_inicializar_Click(object sender, EventArgs e)
        {
            lista_operaciones.Clear();
            dt_informeOperaciones.Clear();
        }

        private void btn_cerrar_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
