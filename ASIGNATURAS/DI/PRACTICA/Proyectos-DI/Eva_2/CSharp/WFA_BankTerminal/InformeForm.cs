using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WFA_BankTerminal
{
    public partial class InformeForm : Form
    {
        private DataGridView Historial;

        public InformeForm(List<TerminalForm.Operacion> historial_operaciones)
        {
            InitializeComponent();

            this.Text = "Informe";

            Historial = new DataGridView
            {
                Dock = DockStyle.Top,
                AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill,
                AllowUserToAddRows = false,
                ReadOnly = true,
                RowHeadersDefaultCellStyle = new DataGridViewCellStyle
                {
                    Font = new Font("Consolas", 12, FontStyle.Bold)
                },
                DefaultCellStyle = new DataGridViewCellStyle
                {
                    Font = new Font("Consolas", 12)
                }
            };
            Historial.ColumnCount = 5;
            Historial.Columns[0].Name = "Fecha";
            Historial.Columns[1].Name = "Hora";
            Historial.Columns[2].Name = "Operacion";
            Historial.Columns[3].Name = "Importe";
            Historial.Columns[4].Name = "Saldo";

            historial_operaciones.ForEach(op =>
            {
                Historial.Rows.Add(op.Fecha, op.Hora, op.Tipo, op.Importe, op.Saldo);
            });

            this.Controls.Add(Historial);

        }

        private void HistorialForm_Load(object sender, EventArgs e) { }

        private void btn_inicializar_Click(object sender, EventArgs e)
        {

        }

        private void btn_cerrar_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
