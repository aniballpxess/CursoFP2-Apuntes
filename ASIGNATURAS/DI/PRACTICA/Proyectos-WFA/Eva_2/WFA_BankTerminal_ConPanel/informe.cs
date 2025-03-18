using System;
using System.Collections.Generic;
using System.Data;
using System.Windows.Forms;

namespace WFA_BankTerminal_ConPanel
{
    public partial class informe : Form
    {
        private List<string[]> historialMovimientos; // Lista de movimientos

        public informe(List<string[]> historial)
        {
            InitializeComponent();
            historialMovimientos = historial;
            CargarHistorial();
        }

        private void CargarHistorial()
        {
            // Crear estructura de la tabla
            DataTable dt = new DataTable();
            dt.Columns.Add("Fecha");
            dt.Columns.Add("Hora");
            dt.Columns.Add("Operación");
            dt.Columns.Add("Importe (€)");
            dt.Columns.Add("Saldo (€)");

            // Agregar registros a la tabla
            foreach (var movimiento in historialMovimientos)
            {
                dt.Rows.Add(movimiento);
            }

            // Asignar la tabla al DataGridView
            tablaInforme.DataSource = dt;
        }

        private void reset_Click(object sender, EventArgs e)
        {
            // Limpiar la lista de registros
            historialMovimientos.Clear();

            // Limpiar la tabla en la interfaz
            tablaInforme.DataSource = null;
            tablaInforme.Rows.Clear();
            tablaInforme.Refresh();
        }

        private void Cerrar_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
