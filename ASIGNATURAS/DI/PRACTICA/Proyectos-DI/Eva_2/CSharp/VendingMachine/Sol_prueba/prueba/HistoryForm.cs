using System;
using System.Windows.Forms;

namespace prueba
{
    public partial class HistoryForm : Form
    {
        private DataGridView Historial;

        public HistoryForm()
        {
            InitializeComponent();

            // Nombre de la Ventana
            this.Text = "Historial de Operaciones";

            // Inicializar el DataGridView antes de usarlo
            Historial = new DataGridView
            {
                Dock = DockStyle.Fill, // Hace que ocupe todo el formulario
                AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill, // Ajusta el ancho automáticamente
                AllowUserToAddRows = false // Evita que el usuario agregue filas manualmente
            };

            // Definir columnas
            Historial.ColumnCount = 6;
            Historial.Columns[0].Name = "Date";
            Historial.Columns[1].Name = "Time";
            Historial.Columns[2].Name = "Credit";
            Historial.Columns[3].Name = "Go | Cancel";
            Historial.Columns[4].Name = "Drink";
            Historial.Columns[5].Name = "Price";

            // Agregar el DataGridView al formulario
            this.Controls.Add(Historial);
        }
        private void HistoryForm_Load(object sender, EventArgs e) {}

        public void AddHistoryEntry(string credit, string goCancel, string drink, string price)
        {
            // Obtener la fecha y hora actuales del sistema
            string date = DateTime.Now.ToString("dd/MM/yyyy"); // Día/Mes/Año
            string time = DateTime.Now.ToString("HH:mm:ss");   // Hora:Minuto:Segundo

            // Agregar fila con los valores
            Historial.Rows.Add(date, time, credit, goCancel, drink, price);
        }
    }
}
