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
            this.Text = "Historial de Operaciones";

            // Creacion de la tabla
            Historial = new DataGridView
            {
                Dock = DockStyle.Fill, 
                AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill,
                AllowUserToAddRows = false
            };
            Historial.ColumnCount = 6;
            Historial.Columns[0].Name = "Date";
            Historial.Columns[1].Name = "Time";
            Historial.Columns[2].Name = "Credit";
            Historial.Columns[3].Name = "Go | Cancel";
            Historial.Columns[4].Name = "Drink";
            Historial.Columns[5].Name = "Price";
            this.Controls.Add(Historial);
        }
        private void HistoryForm_Load(object sender, EventArgs e) {}

        public void AddHistoryEntry(string credit, string goCancel, string drink, string price)
        {
            string date = DateTime.Now.ToString("dd/MM/yyyy");
            string time = DateTime.Now.ToString("HH:mm:ss");
            
            Historial.Rows.Add(date, time, credit, goCancel, drink, price);
            Historial.Refresh();
        }
    }
}
