using System;
using System.Drawing;
using System.Windows.Forms;

namespace prueba
{
    public partial class VendingMachine : Form
    {
        //----------------> Variables <----------------\\

        private decimal saldo = 0; // Variable para almacenar el dinero ingresado
        private decimal saldo_Max = 4; // Saldo Máximo permitido
        private bool hayCambio = false;
        private bool language = true; // Español

        //----------------> Inicialización de Componentes <----------------\\
        public VendingMachine()
        {
            InitializeComponent();

            // Nombre de la Ventana
            this.Text = "Vending Machine";


            // Permite darle la funcionalidad al botón

            //-> Gestión del dinero
            btn_Cancel.Click += btn_Cancel_Click;
            btn_Change.Click += btn_Change_Click;
            //-> Bebidas
            btn_CocaCola.Click += btn_CocaCola_Click;
            btn_Fanta.Click += btn_Fanta_Click;
            btn_Fanta_Limon.Click += btn_Fanta_Limon_Click;
            btn_JackDaniels.Click += btn_JackDaniels_Click;
            btn_Drink_Pickup.Click += btn_Drink_Pickup_Click;

            ManageDrinks();
            ManageCancelBtn();
            btn_Drink_Pickup.Enabled = false;
            ManageChangeBtn();
            
        }

        //--------------------------------> Métodos <--------------------------------\\
        private void ManageDrinks()
        {
            if (saldo < 1.50m)
            {
                btn_CocaCola.Enabled = false;
                btn_Fanta.Enabled = false;
                btn_Fanta_Limon.Enabled = false;
                btn_JackDaniels.Enabled = false;

                btn_CocaCola.BackgroundImage = Properties.Resources.Disabled_Coca_ColaDrink;
                btn_Fanta.BackgroundImage = Properties.Resources.Disabled_Fanta_OrangeDrink;
                btn_Fanta_Limon.BackgroundImage = Properties.Resources.Disabled_Fanta_LemonDrink;
                btn_JackDaniels.BackgroundImage = Properties.Resources.Disabled_JackDrink;

            }
            else if (saldo >= 1.50m && saldo < 4.00m)
            {

                btn_CocaCola.Enabled = true;
                btn_Fanta.Enabled = true;
                btn_Fanta_Limon.Enabled = true;

                btn_CocaCola.Cursor = Cursors.Hand;
                btn_Fanta.Cursor = Cursors.Hand;
                btn_Fanta_Limon.Cursor = Cursors.Hand;

                btn_CocaCola.BackgroundImage = Properties.Resources.Enabled_Coca_Cola;
                btn_Fanta.BackgroundImage = Properties.Resources.Enabled_Fanta_Orange;
                btn_Fanta_Limon.BackgroundImage = Properties.Resources.Enabled_Fanta_Lemon;

            }
            else
            {
                btn_CocaCola.Enabled = true;
                btn_Fanta.Enabled = true;
                btn_Fanta_Limon.Enabled = true;
                btn_JackDaniels.Enabled = true;

                btn_JackDaniels.Cursor = Cursors.Hand;

                btn_CocaCola.BackgroundImage = Properties.Resources.Enabled_Coca_Cola;
                btn_Fanta.BackgroundImage = Properties.Resources.Enabled_Fanta_Orange;
                btn_Fanta_Limon.BackgroundImage = Properties.Resources.Enabled_Fanta_Lemon;
                btn_JackDaniels.BackgroundImage = Properties.Resources.Enabled_JackD;

            }
            btn_Drink_Pickup.Cursor = Cursors.Hand;
        }

        private void ActualizarMonedas()
        {
            Money_Screen.Text = saldo.ToString("0.00") + "€";

            // Desactivar los botones solo cuando el saldo supera 4€
            if (saldo >= saldo_Max)
            {
                btn_fifty_cent.Enabled = false;
                btn_fifty_cent.BackgroundImage = Properties.Resources._50cts_BW;

                btn_one_euro.Enabled = false;
                btn_one_euro.BackgroundImage = Properties.Resources.Uno_euro_BW;

                btn_two_euro.Enabled = false;
                btn_two_euro.BackgroundImage = Properties.Resources.Dos_euro_BW;

            } else
            {
                btn_fifty_cent.Enabled = true;
                btn_fifty_cent.BackgroundImage = Properties.Resources._50cts;

                btn_one_euro.Enabled = true;
                btn_one_euro.BackgroundImage = Properties.Resources.unoeuro;

                btn_two_euro.Enabled = true;
                btn_two_euro.BackgroundImage = Properties.Resources.doseuro;
            }
            ManageDrinks();
            ManageCancelBtn();
            ManageChangeBtn();

        }

        private void ManageCancelBtn() 
        {
            if (saldo > 0)
            {
                btn_Cancel.Enabled = true;
                btn_Cancel.BackColor = Color.Red;
                btn_Cancel.Cursor = Cursors.Hand;

            }
            else 
            {
                btn_Cancel.Enabled = false;
                btn_Cancel.BackColor = Color.Gray;
            }
        }

        private void ManageChangeBtn() 
        {
            if (hayCambio == false)
            {
                btn_Change.Enabled = false;
                btn_Change.Cursor = Cursors.No;
                btn_Change.BackgroundImage = null;
                btn_Change.BackColor = Color.Black;
            }
            else 
            {
                btn_Change.Enabled = true;
                btn_Change.Cursor = Cursors.Hand;
                btn_Change.BackgroundImage = Properties.Resources.change;
                btn_Change.BackColor = Color.White;
            }
        }

        //--------------------------------> Botones Clicables <--------------------------------\\
        private void btn_fifty_cent_Click(object sender, EventArgs e)
        {
            if (saldo <= saldo_Max) // Permitimos sobrepasar el límite de 4€, pero no más de 5€
            {
                saldo += 0.50m;
                ActualizarMonedas();
            }
        }

        private void btn_one_euro_Click(object sender, EventArgs e)
        {
            if (saldo <= saldo_Max) // Permitimos sobrepasar el límite de 4€, pero no más de 5€
            {
                saldo += 1.00m;
                ActualizarMonedas();
            }
        }

        private void btn_two_euro_Click(object sender, EventArgs e)
        {
            if (saldo <= saldo_Max) // Permitimos sobrepasar el límite de 4€, pero no más de 5€
            {
                saldo += 2.00m;
                ActualizarMonedas();
            }
        }

        private void btn_Cancel_Click(object sender, EventArgs e)
        {
            saldo = 0;
            ActualizarMonedas();
            ManageDrinks(); // Asegurar que las bebidas se deshabiliten
            hayCambio = true;
            ManageChangeBtn();
        }

        private void btn_Change_Click(object sender, EventArgs e)
        {
            hayCambio = false;
            ManageChangeBtn();
        }

        private void btn_CocaCola_Click(object sender, EventArgs e)
        {
            btn_Drink_Pickup.Enabled = true;
            btn_Drink_Pickup.BackgroundImage = Properties.Resources.Enabled_Coca_Cola;

            decimal precio = 1.50m;
            hayCambio = saldo > precio;
            ManageChangeBtn();

            saldo = 0;  // Reseteamos el saldo a 0
            ActualizarMonedas(); // Refrescamos la pantalla y los botones
        }

        private void btn_Fanta_Click(object sender, EventArgs e)
        {
            btn_Drink_Pickup.Enabled = true;
            btn_Drink_Pickup.BackgroundImage = Properties.Resources.Enabled_Fanta_Orange;

            decimal precio = 1.50m;
            hayCambio = saldo > precio;
            ManageChangeBtn();

            saldo = 0;  
            ActualizarMonedas(); 
        }

        private void btn_Fanta_Limon_Click(object sender, EventArgs e)
        {
            btn_Drink_Pickup.Enabled = true;
            btn_Drink_Pickup.BackgroundImage = Properties.Resources.Enabled_Fanta_Lemon;

            decimal precio = 1.50m;
            hayCambio = saldo > precio;
            ManageChangeBtn();

            saldo = 0;  
            ActualizarMonedas(); 
        }

        private void btn_JackDaniels_Click(object sender, EventArgs e)
        {
            btn_Drink_Pickup.Enabled = true;
            btn_Drink_Pickup.BackgroundImage = Properties.Resources.Enabled_JackD;

            decimal precio = 4.00m;
            hayCambio = saldo > precio;
            ManageChangeBtn();

            saldo = 0;  
            ActualizarMonedas();
        }

        private void btn_Drink_Pickup_Click(object sender, EventArgs e)
        {
            btn_Drink_Pickup.BackgroundImage = null;
        }

        private void btn_Language_Click(object sender, EventArgs e)
        {
            // Si la bandera es de España = Ponlo en Español
            // Si la bandera es de USA = Ponlo en Inglés

            if (language == true)
            {
                btn_Language.BackgroundImage = Properties.Resources.spain;

                // Traducción de labels
                L_T_Label_Coin.Text = "1 INSERTAR MONEDA";
                M_T_Label_Drink.Text = "2 ESCOGER BEBIDA";
                R_T_Label_Pickup.Text = "3 RECOGER BEBIDA";

                // Traducción de boton
                btn_Cancel.Text = "CANCELAR";
            }
            else
            {
                btn_Language.BackgroundImage = Properties.Resources.usa;

                // Traducción de labels
                L_T_Label_Coin.Text = "1 INSERT COIN";
                M_T_Label_Drink.Text = "2 CHOOSE DRINK";
                R_T_Label_Pickup.Text = "3 TAKE DRINK";

                // Traducción de boton
                btn_Cancel.Text = "CANCEL";

            }

            language = !language;
        }

        private void btn_History_Click(object sender, EventArgs e)
        {
            HistoryForm Hfm = new HistoryForm();

            Hfm.AddHistoryEntry("5€", "Go", "Coca-Cola", "2€");

            Hfm.Show();
        }
    }
}