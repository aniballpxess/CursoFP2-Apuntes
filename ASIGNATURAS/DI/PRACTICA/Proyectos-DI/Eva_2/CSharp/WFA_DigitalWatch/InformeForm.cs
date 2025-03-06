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
    public partial class InformeForm : Form
    {
        public List<Registro> lista_registros;
        public DataTable dt_informeRegistros;

        public InformeForm(List<Registro> registros)
        {
            InitializeComponent();
            this.Text = "Informe";

            lista_registros = registros;
            dt_informeRegistros = new DataTable();

            dt_informeRegistros.Columns.Add("Fecha");
            dt_informeRegistros.Columns.Add("Hora");
            dt_informeRegistros.Columns.Add("Boton");

            foreach (Registro reg in lista_registros)
            {
                DataRow fila = dt_informeRegistros.NewRow();
                fila["Fecha"] = reg.fecha;
                fila["Hora"] = reg.horaReloj;
                fila["Boton"] = reg.boton;
                dt_informeRegistros.Rows.Add(fila);
            }
            dgv_registros.DataSource = dt_informeRegistros;
        }

        private void btn_inicializar_Click(object sender, EventArgs e)
        {
            lista_registros.Clear();
            dt_informeRegistros.Clear();
        }

        private void btn_cerrar_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
