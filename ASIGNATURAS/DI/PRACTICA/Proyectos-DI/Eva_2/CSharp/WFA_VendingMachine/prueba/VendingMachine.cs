using System;
using System.Drawing;
using System.Windows.Forms;

namespace prueba
{
    public partial class VendingMachine : Form
    {
        //----------------> Variables <----------------\\

        private const decimal precio_sinAlcohol = 1.50m;
        private const decimal precio_conAlcohol = 4.00m;
        private decimal saldo = 0.00m;
        private bool hayCambio = false;
        private bool language = true; // Español

        //----------------> Inicialización de Componentes <----------------\\
        public VendingMachine()
        {
            InitializeComponent();
            this.Text = "Vending Machine";

            btn_Cancel.Click += btn_Cancel_Click;
            btn_Change.Click += btn_Change_Click;

            btn_CocaCola.Click += btn_CocaCola_Click;
            btn_Fanta.Click += btn_Fanta_Click;
            btn_Fanta_Limon.Click += btn_Fanta_Limon_Click;
            btn_JackDaniels.Click += btn_JackDaniels_Click;

            btn_Drink_Pickup.Click += btn_Drink_Pickup_Click;
            btn_Drink_Pickup.Enabled = false;

            btn_Cancel.Cursor = Cursors.Hand;
            btn_Change.Cursor = Cursors.Hand;

            btn_CocaCola.Cursor = Cursors.Hand;
            btn_Fanta.Cursor = Cursors.Hand;
            btn_Fanta_Limon.Cursor = Cursors.Hand;
            btn_JackDaniels.Cursor = Cursors.Hand;

            btn_Drink_Pickup.Cursor = Cursors.Hand;
            btn_Language.Cursor = Cursors.Hand;

            UpdateDisplay();
        }

        //--------------------------------> Métodos <--------------------------------\\

        private void UpdateDisplay()
        {
            UpdateDrinksBtns();
            UpdateCoinsBtns();
            UpdateCancelBtn();
            UpdateChangeBtn();
        }

        private void UpdateDrinksBtns()
        {
            if (saldo < precio_sinAlcohol)
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
            if (saldo >= precio_sinAlcohol && saldo < precio_conAlcohol)
            {
                btn_CocaCola.Enabled = true;
                btn_Fanta.Enabled = true;
                btn_Fanta_Limon.Enabled = true;
                btn_JackDaniels.Enabled = false;

                btn_CocaCola.BackgroundImage = Properties.Resources.Enabled_Coca_Cola;
                btn_Fanta.BackgroundImage = Properties.Resources.Enabled_Fanta_Orange;
                btn_Fanta_Limon.BackgroundImage = Properties.Resources.Enabled_Fanta_Lemon;
                btn_JackDaniels.BackgroundImage = Properties.Resources.Disabled_JackDrink;
            }
            if (saldo >= precio_conAlcohol)
            {
                btn_CocaCola.Enabled = true;
                btn_Fanta.Enabled = true;
                btn_Fanta_Limon.Enabled = true;
                btn_JackDaniels.Enabled = true;

                btn_CocaCola.BackgroundImage = Properties.Resources.Enabled_Coca_Cola;
                btn_Fanta.BackgroundImage = Properties.Resources.Enabled_Fanta_Orange;
                btn_Fanta_Limon.BackgroundImage = Properties.Resources.Enabled_Fanta_Lemon;
                btn_JackDaniels.BackgroundImage = Properties.Resources.Enabled_JackD;
            }
        }

        private void UpdateCoinsBtns()
        {
            Money_Screen.Text = saldo.ToString("0.00") + "€"; // Actualiza el saldo (un poco feo?)

            if (saldo >= precio_conAlcohol)
            {
                btn_fifty_cent.Enabled = false;
                btn_one_euro.Enabled = false;
                btn_two_euro.Enabled = false;

                btn_fifty_cent.BackgroundImage = Properties.Resources._50cts_BW;
                btn_one_euro.BackgroundImage = Properties.Resources.Uno_euro_BW;
                btn_two_euro.BackgroundImage = Properties.Resources.Dos_euro_BW;
            }
            else
            {
                btn_fifty_cent.Enabled = true;
                btn_one_euro.Enabled = true;
                btn_two_euro.Enabled = true;

                btn_fifty_cent.BackgroundImage = Properties.Resources._50cts;
                btn_one_euro.BackgroundImage = Properties.Resources.unoeuro;
                btn_two_euro.BackgroundImage = Properties.Resources.doseuro;
            }
        }

        private void UpdateCancelBtn() 
        {
            if (saldo > 0)
            {
                btn_Cancel.Enabled = true;
                btn_Cancel.BackColor = Color.Red;
            }
            else 
            {
                btn_Cancel.Enabled = false;
                btn_Cancel.BackColor = Color.Gray;
            }
        }

        private void UpdateChangeBtn() 
        {
            if (hayCambio == false)
            {
                btn_Change.Enabled = false;
                btn_Change.BackgroundImage = null;
                btn_Change.BackColor = Color.Black;
            }
            else 
            {
                btn_Change.Enabled = true;
                btn_Change.BackgroundImage = Properties.Resources.change;
                btn_Change.BackColor = Color.White;
            }
        }

        //--------------------------------> Botones Clicables <--------------------------------\\
        private void btn_fifty_cent_Click(object sender, EventArgs e)
        {
            if (saldo <= precio_conAlcohol)
            {
                saldo += 0.50m;
                UpdateDisplay();
            }
        }

        private void btn_one_euro_Click(object sender, EventArgs e)
        {
            if (saldo <= precio_conAlcohol)
            {
                saldo += 1.00m;
                UpdateDisplay();
            }
        }

        private void btn_two_euro_Click(object sender, EventArgs e)
        {
            if (saldo <= precio_conAlcohol)
            {
                saldo += 2.00m;
                UpdateDisplay();
            }
        }

        private void btn_Cancel_Click(object sender, EventArgs e)
        {
            saldo = 0.00m;
            hayCambio = true;
            UpdateDisplay();
        }

        private void btn_Change_Click(object sender, EventArgs e)
        {
            hayCambio = false;
            UpdateChangeBtn();
        }

        private void btn_CocaCola_Click(object sender, EventArgs e)
        {
            btn_Drink_Pickup.Enabled = true;
            btn_Drink_Pickup.BackgroundImage = Properties.Resources.Enabled_Coca_Cola;

            hayCambio = saldo > precio_sinAlcohol;
            saldo = 0.00m;
            UpdateDisplay();
        }

        private void btn_Fanta_Click(object sender, EventArgs e)
        {
            btn_Drink_Pickup.Enabled = true;
            btn_Drink_Pickup.BackgroundImage = Properties.Resources.Enabled_Fanta_Orange;

            hayCambio = saldo > precio_sinAlcohol;
            saldo = 0.00m;
            UpdateDisplay(); 
        }

        private void btn_Fanta_Limon_Click(object sender, EventArgs e)
        {
            btn_Drink_Pickup.Enabled = true;
            btn_Drink_Pickup.BackgroundImage = Properties.Resources.Enabled_Fanta_Lemon;

            hayCambio = saldo > precio_sinAlcohol;
            saldo = 0.00m;
            UpdateDisplay(); 
        }

        private void btn_JackDaniels_Click(object sender, EventArgs e)
        {
            btn_Drink_Pickup.Enabled = true;
            btn_Drink_Pickup.BackgroundImage = Properties.Resources.Enabled_JackD;

            hayCambio = saldo > precio_conAlcohol;
            saldo = 0.00m;
            UpdateDisplay();
        }

        private void btn_Drink_Pickup_Click(object sender, EventArgs e)
        {
            btn_Drink_Pickup.BackgroundImage = null;
            btn_Drink_Pickup.Enabled = false;
        }

        private void btn_Language_Click(object sender, EventArgs e)
        {

            if (language == true)
            {
                btn_Language.BackgroundImage = Properties.Resources.usa;

                L_T_Label_Coin.Text = "1 INSERTAR MONEDA";
                M_T_Label_Drink.Text = "2 ESCOGER BEBIDA";
                R_T_Label_Pickup.Text = "3 RECOGER BEBIDA";

                btn_Cancel.Text = "CANCELAR";
            }
            else
            {
                btn_Language.BackgroundImage = Properties.Resources.spain;

                L_T_Label_Coin.Text = "1 INSERT COIN";
                M_T_Label_Drink.Text = "2 CHOOSE DRINK";
                R_T_Label_Pickup.Text = "3 TAKE DRINK";

                btn_Cancel.Text = "CANCEL";

            }

            language = !language;
        }

        private void btn_History_Click(object sender, EventArgs e)
        {

            HistoryForm Hfm = new HistoryForm();
            Hfm.ShowDialog();
        }
    }
}