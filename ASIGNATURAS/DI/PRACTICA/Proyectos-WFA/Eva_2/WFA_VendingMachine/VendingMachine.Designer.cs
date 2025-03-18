namespace WFA_VendingMachine
{
    partial class VendingMachine
    {
        /// <summary>
        /// Variable del diseñador necesaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Limpiar los recursos que se estén usando.
        /// </summary>
        /// <param name="disposing">true si los recursos administrados se deben desechar; false en caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Código generado por el Diseñador de Windows Forms

        /// <summary>
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(VendingMachine));
            this.toolTip1 = new System.Windows.Forms.ToolTip(this.components);
            this.btn_Change = new System.Windows.Forms.Button();
            this.btn_Cancel = new System.Windows.Forms.Button();
            this.btn_fifty_cent = new System.Windows.Forms.Button();
            this.btn_one_euro = new System.Windows.Forms.Button();
            this.btn_two_euro = new System.Windows.Forms.Button();
            this.btn_Fanta_Limon = new System.Windows.Forms.Button();
            this.btn_JackDaniels = new System.Windows.Forms.Button();
            this.btn_Fanta = new System.Windows.Forms.Button();
            this.btn_CocaCola = new System.Windows.Forms.Button();
            this.Background = new System.Windows.Forms.TableLayoutPanel();
            this.M_Selection = new System.Windows.Forms.TableLayoutPanel();
            this.Coca_Price = new System.Windows.Forms.Label();
            this.Amstel_Price = new System.Windows.Forms.Label();
            this.Sprite_Price = new System.Windows.Forms.Label();
            this.Fanta_Price = new System.Windows.Forms.Label();
            this.M_T_Label_Drink = new System.Windows.Forms.Label();
            this.R_T_Label_Pickup = new System.Windows.Forms.Label();
            this.L_Compra = new System.Windows.Forms.Panel();
            this.Left_Bot_Container = new System.Windows.Forms.TableLayoutPanel();
            this.Left_Top_Container = new System.Windows.Forms.Panel();
            this.btn_History = new System.Windows.Forms.Button();
            this.btn_Language = new System.Windows.Forms.Button();
            this.Money_Screen = new System.Windows.Forms.Label();
            this.Coin_Container = new System.Windows.Forms.TableLayoutPanel();
            this.L_T_Label_Coin = new System.Windows.Forms.Label();
            this.btn_Drink_Pickup = new System.Windows.Forms.Button();
            this.Background.SuspendLayout();
            this.M_Selection.SuspendLayout();
            this.L_Compra.SuspendLayout();
            this.Left_Bot_Container.SuspendLayout();
            this.Left_Top_Container.SuspendLayout();
            this.Coin_Container.SuspendLayout();
            this.SuspendLayout();
            // 
            // btn_Change
            // 
            this.btn_Change.BackColor = System.Drawing.Color.White;
            this.btn_Change.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("btn_Change.BackgroundImage")));
            this.btn_Change.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.btn_Change.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btn_Change.Dock = System.Windows.Forms.DockStyle.Fill;
            this.btn_Change.FlatAppearance.BorderColor = System.Drawing.Color.Black;
            this.btn_Change.FlatAppearance.BorderSize = 2;
            this.btn_Change.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btn_Change.Location = new System.Drawing.Point(6, 50);
            this.btn_Change.Name = "btn_Change";
            this.btn_Change.Size = new System.Drawing.Size(118, 58);
            this.btn_Change.TabIndex = 11;
            this.toolTip1.SetToolTip(this.btn_Change, "Recoger Cambio");
            this.btn_Change.UseVisualStyleBackColor = false;
            // 
            // btn_Cancel
            // 
            this.btn_Cancel.BackColor = System.Drawing.Color.Red;
            this.btn_Cancel.Cursor = System.Windows.Forms.Cursors.No;
            this.btn_Cancel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.btn_Cancel.FlatAppearance.BorderColor = System.Drawing.Color.Black;
            this.btn_Cancel.FlatAppearance.BorderSize = 2;
            this.btn_Cancel.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btn_Cancel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_Cancel.Location = new System.Drawing.Point(6, 6);
            this.btn_Cancel.Name = "btn_Cancel";
            this.btn_Cancel.Size = new System.Drawing.Size(118, 35);
            this.btn_Cancel.TabIndex = 10;
            this.btn_Cancel.Text = "CANCEL";
            this.toolTip1.SetToolTip(this.btn_Cancel, "Cancelar");
            this.btn_Cancel.UseVisualStyleBackColor = false;
            // 
            // btn_fifty_cent
            // 
            this.btn_fifty_cent.BackColor = System.Drawing.Color.WhiteSmoke;
            this.btn_fifty_cent.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("btn_fifty_cent.BackgroundImage")));
            this.btn_fifty_cent.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.btn_fifty_cent.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btn_fifty_cent.Dock = System.Windows.Forms.DockStyle.Fill;
            this.btn_fifty_cent.Location = new System.Drawing.Point(5, 5);
            this.btn_fifty_cent.Name = "btn_fifty_cent";
            this.btn_fifty_cent.Size = new System.Drawing.Size(52, 48);
            this.btn_fifty_cent.TabIndex = 0;
            this.toolTip1.SetToolTip(this.btn_fifty_cent, "Cincuenta Céntimos");
            this.btn_fifty_cent.UseVisualStyleBackColor = false;
            this.btn_fifty_cent.Click += new System.EventHandler(this.btn_fifty_cent_Click);
            // 
            // btn_one_euro
            // 
            this.btn_one_euro.BackColor = System.Drawing.Color.WhiteSmoke;
            this.btn_one_euro.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("btn_one_euro.BackgroundImage")));
            this.btn_one_euro.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.btn_one_euro.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btn_one_euro.Dock = System.Windows.Forms.DockStyle.Fill;
            this.btn_one_euro.Location = new System.Drawing.Point(5, 61);
            this.btn_one_euro.Name = "btn_one_euro";
            this.btn_one_euro.Size = new System.Drawing.Size(52, 48);
            this.btn_one_euro.TabIndex = 1;
            this.toolTip1.SetToolTip(this.btn_one_euro, "Un euro");
            this.btn_one_euro.UseVisualStyleBackColor = false;
            this.btn_one_euro.Click += new System.EventHandler(this.btn_one_euro_Click);
            // 
            // btn_two_euro
            // 
            this.btn_two_euro.BackColor = System.Drawing.Color.WhiteSmoke;
            this.btn_two_euro.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("btn_two_euro.BackgroundImage")));
            this.btn_two_euro.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.btn_two_euro.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btn_two_euro.Dock = System.Windows.Forms.DockStyle.Fill;
            this.btn_two_euro.Location = new System.Drawing.Point(5, 117);
            this.btn_two_euro.Name = "btn_two_euro";
            this.btn_two_euro.Size = new System.Drawing.Size(52, 50);
            this.btn_two_euro.TabIndex = 2;
            this.toolTip1.SetToolTip(this.btn_two_euro, "Dos euros");
            this.btn_two_euro.UseVisualStyleBackColor = false;
            this.btn_two_euro.Click += new System.EventHandler(this.btn_two_euro_Click);
            // 
            // btn_Fanta_Limon
            // 
            this.btn_Fanta_Limon.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.btn_Fanta_Limon.BackgroundImage = global::WFA_VendingMachine.Properties.Resources.Enabled_Fanta_Lemon;
            this.btn_Fanta_Limon.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.btn_Fanta_Limon.Cursor = System.Windows.Forms.Cursors.No;
            this.btn_Fanta_Limon.Dock = System.Windows.Forms.DockStyle.Fill;
            this.btn_Fanta_Limon.Location = new System.Drawing.Point(5, 149);
            this.btn_Fanta_Limon.Name = "btn_Fanta_Limon";
            this.btn_Fanta_Limon.Size = new System.Drawing.Size(57, 63);
            this.btn_Fanta_Limon.TabIndex = 12;
            this.toolTip1.SetToolTip(this.btn_Fanta_Limon, "Fanta de Limón");
            this.btn_Fanta_Limon.UseVisualStyleBackColor = false;
            // 
            // btn_JackDaniels
            // 
            this.btn_JackDaniels.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.btn_JackDaniels.BackgroundImage = global::WFA_VendingMachine.Properties.Resources.Enabled_JackD;
            this.btn_JackDaniels.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.btn_JackDaniels.Cursor = System.Windows.Forms.Cursors.No;
            this.btn_JackDaniels.Dock = System.Windows.Forms.DockStyle.Fill;
            this.btn_JackDaniels.Location = new System.Drawing.Point(5, 220);
            this.btn_JackDaniels.Name = "btn_JackDaniels";
            this.btn_JackDaniels.Size = new System.Drawing.Size(57, 67);
            this.btn_JackDaniels.TabIndex = 11;
            this.toolTip1.SetToolTip(this.btn_JackDaniels, "Jack Daniels");
            this.btn_JackDaniels.UseVisualStyleBackColor = false;
            // 
            // btn_Fanta
            // 
            this.btn_Fanta.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.btn_Fanta.BackgroundImage = global::WFA_VendingMachine.Properties.Resources.Enabled_Fanta_Orange;
            this.btn_Fanta.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.btn_Fanta.Cursor = System.Windows.Forms.Cursors.No;
            this.btn_Fanta.Dock = System.Windows.Forms.DockStyle.Fill;
            this.btn_Fanta.Location = new System.Drawing.Point(5, 77);
            this.btn_Fanta.Name = "btn_Fanta";
            this.btn_Fanta.Size = new System.Drawing.Size(57, 64);
            this.btn_Fanta.TabIndex = 2;
            this.toolTip1.SetToolTip(this.btn_Fanta, "Fanta de Naranja");
            this.btn_Fanta.UseVisualStyleBackColor = false;
            // 
            // btn_CocaCola
            // 
            this.btn_CocaCola.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.btn_CocaCola.BackgroundImage = global::WFA_VendingMachine.Properties.Resources.Enabled_Coca_Cola;
            this.btn_CocaCola.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.btn_CocaCola.Cursor = System.Windows.Forms.Cursors.No;
            this.btn_CocaCola.Dock = System.Windows.Forms.DockStyle.Fill;
            this.btn_CocaCola.Location = new System.Drawing.Point(5, 5);
            this.btn_CocaCola.Name = "btn_CocaCola";
            this.btn_CocaCola.Size = new System.Drawing.Size(57, 64);
            this.btn_CocaCola.TabIndex = 0;
            this.toolTip1.SetToolTip(this.btn_CocaCola, "Coca Cola");
            this.btn_CocaCola.UseVisualStyleBackColor = false;
            // 
            // Background
            // 
            this.Background.BackColor = System.Drawing.SystemColors.ControlDark;
            this.Background.CellBorderStyle = System.Windows.Forms.TableLayoutPanelCellBorderStyle.InsetDouble;
            this.Background.ColumnCount = 3;
            this.Background.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.Background.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33334F));
            this.Background.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33334F));
            this.Background.Controls.Add(this.M_Selection, 1, 1);
            this.Background.Controls.Add(this.M_T_Label_Drink, 1, 0);
            this.Background.Controls.Add(this.R_T_Label_Pickup, 2, 0);
            this.Background.Controls.Add(this.L_Compra, 0, 1);
            this.Background.Controls.Add(this.L_T_Label_Coin, 0, 0);
            this.Background.Controls.Add(this.btn_Drink_Pickup, 2, 1);
            this.Background.Dock = System.Windows.Forms.DockStyle.Fill;
            this.Background.Location = new System.Drawing.Point(0, 0);
            this.Background.Name = "Background";
            this.Background.RowCount = 2;
            this.Background.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 10.88889F));
            this.Background.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 89.11111F));
            this.Background.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 20F));
            this.Background.Size = new System.Drawing.Size(591, 403);
            this.Background.TabIndex = 1;
            // 
            // M_Selection
            // 
            this.M_Selection.CellBorderStyle = System.Windows.Forms.TableLayoutPanelCellBorderStyle.Inset;
            this.M_Selection.ColumnCount = 2;
            this.M_Selection.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.M_Selection.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.M_Selection.Controls.Add(this.Coca_Price, 1, 0);
            this.M_Selection.Controls.Add(this.btn_Fanta_Limon, 0, 2);
            this.M_Selection.Controls.Add(this.btn_JackDaniels, 0, 3);
            this.M_Selection.Controls.Add(this.Amstel_Price, 1, 3);
            this.M_Selection.Controls.Add(this.Sprite_Price, 1, 2);
            this.M_Selection.Controls.Add(this.Fanta_Price, 1, 1);
            this.M_Selection.Controls.Add(this.btn_Fanta, 0, 1);
            this.M_Selection.Controls.Add(this.btn_CocaCola, 0, 0);
            this.M_Selection.Cursor = System.Windows.Forms.Cursors.No;
            this.M_Selection.Dock = System.Windows.Forms.DockStyle.Fill;
            this.M_Selection.Location = new System.Drawing.Point(228, 78);
            this.M_Selection.Margin = new System.Windows.Forms.Padding(30);
            this.M_Selection.Name = "M_Selection";
            this.M_Selection.RowCount = 4;
            this.M_Selection.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 25F));
            this.M_Selection.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 25.0505F));
            this.M_Selection.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 24.64647F));
            this.M_Selection.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 25F));
            this.M_Selection.Size = new System.Drawing.Size(133, 292);
            this.M_Selection.TabIndex = 9;
            // 
            // Coca_Price
            // 
            this.Coca_Price.AutoSize = true;
            this.Coca_Price.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.Coca_Price.Cursor = System.Windows.Forms.Cursors.No;
            this.Coca_Price.Dock = System.Windows.Forms.DockStyle.Fill;
            this.Coca_Price.Font = new System.Drawing.Font("Consolas", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Coca_Price.ForeColor = System.Drawing.Color.Lime;
            this.Coca_Price.Location = new System.Drawing.Point(69, 4);
            this.Coca_Price.Margin = new System.Windows.Forms.Padding(2);
            this.Coca_Price.Name = "Coca_Price";
            this.Coca_Price.Size = new System.Drawing.Size(60, 66);
            this.Coca_Price.TabIndex = 13;
            this.Coca_Price.Text = "1.5€";
            this.Coca_Price.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // Amstel_Price
            // 
            this.Amstel_Price.AutoSize = true;
            this.Amstel_Price.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.Amstel_Price.Cursor = System.Windows.Forms.Cursors.No;
            this.Amstel_Price.Dock = System.Windows.Forms.DockStyle.Fill;
            this.Amstel_Price.Font = new System.Drawing.Font("Consolas", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Amstel_Price.ForeColor = System.Drawing.Color.Lime;
            this.Amstel_Price.Location = new System.Drawing.Point(69, 219);
            this.Amstel_Price.Margin = new System.Windows.Forms.Padding(2);
            this.Amstel_Price.Name = "Amstel_Price";
            this.Amstel_Price.Size = new System.Drawing.Size(60, 69);
            this.Amstel_Price.TabIndex = 10;
            this.Amstel_Price.Text = "4€";
            this.Amstel_Price.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // Sprite_Price
            // 
            this.Sprite_Price.AutoSize = true;
            this.Sprite_Price.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.Sprite_Price.Cursor = System.Windows.Forms.Cursors.No;
            this.Sprite_Price.Dock = System.Windows.Forms.DockStyle.Fill;
            this.Sprite_Price.Font = new System.Drawing.Font("Consolas", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Sprite_Price.ForeColor = System.Drawing.Color.Lime;
            this.Sprite_Price.Location = new System.Drawing.Point(69, 148);
            this.Sprite_Price.Margin = new System.Windows.Forms.Padding(2);
            this.Sprite_Price.Name = "Sprite_Price";
            this.Sprite_Price.Size = new System.Drawing.Size(60, 65);
            this.Sprite_Price.TabIndex = 9;
            this.Sprite_Price.Text = "1.5€";
            this.Sprite_Price.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // Fanta_Price
            // 
            this.Fanta_Price.AutoSize = true;
            this.Fanta_Price.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.Fanta_Price.Cursor = System.Windows.Forms.Cursors.No;
            this.Fanta_Price.Dock = System.Windows.Forms.DockStyle.Fill;
            this.Fanta_Price.Font = new System.Drawing.Font("Consolas", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Fanta_Price.ForeColor = System.Drawing.Color.Lime;
            this.Fanta_Price.Location = new System.Drawing.Point(69, 76);
            this.Fanta_Price.Margin = new System.Windows.Forms.Padding(2);
            this.Fanta_Price.Name = "Fanta_Price";
            this.Fanta_Price.Size = new System.Drawing.Size(60, 66);
            this.Fanta_Price.TabIndex = 8;
            this.Fanta_Price.Text = "1.5€";
            this.Fanta_Price.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // M_T_Label_Drink
            // 
            this.M_T_Label_Drink.AutoSize = true;
            this.M_T_Label_Drink.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.M_T_Label_Drink.Dock = System.Windows.Forms.DockStyle.Fill;
            this.M_T_Label_Drink.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.M_T_Label_Drink.ForeColor = System.Drawing.Color.Lime;
            this.M_T_Label_Drink.Location = new System.Drawing.Point(201, 3);
            this.M_T_Label_Drink.Name = "M_T_Label_Drink";
            this.M_T_Label_Drink.Size = new System.Drawing.Size(187, 42);
            this.M_T_Label_Drink.TabIndex = 7;
            this.M_T_Label_Drink.Text = "2. CHOOSE DRINK";
            this.M_T_Label_Drink.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // R_T_Label_Pickup
            // 
            this.R_T_Label_Pickup.AutoSize = true;
            this.R_T_Label_Pickup.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.R_T_Label_Pickup.Dock = System.Windows.Forms.DockStyle.Fill;
            this.R_T_Label_Pickup.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.R_T_Label_Pickup.ForeColor = System.Drawing.Color.Lime;
            this.R_T_Label_Pickup.Location = new System.Drawing.Point(397, 3);
            this.R_T_Label_Pickup.Name = "R_T_Label_Pickup";
            this.R_T_Label_Pickup.Size = new System.Drawing.Size(188, 42);
            this.R_T_Label_Pickup.TabIndex = 3;
            this.R_T_Label_Pickup.Text = "3. TAKE DRINK";
            this.R_T_Label_Pickup.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // L_Compra
            // 
            this.L_Compra.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.L_Compra.Controls.Add(this.Left_Bot_Container);
            this.L_Compra.Controls.Add(this.Left_Top_Container);
            this.L_Compra.Dock = System.Windows.Forms.DockStyle.Fill;
            this.L_Compra.Location = new System.Drawing.Point(33, 78);
            this.L_Compra.Margin = new System.Windows.Forms.Padding(30);
            this.L_Compra.Name = "L_Compra";
            this.L_Compra.Size = new System.Drawing.Size(132, 292);
            this.L_Compra.TabIndex = 0;
            // 
            // Left_Bot_Container
            // 
            this.Left_Bot_Container.CellBorderStyle = System.Windows.Forms.TableLayoutPanelCellBorderStyle.InsetDouble;
            this.Left_Bot_Container.ColumnCount = 1;
            this.Left_Bot_Container.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.Left_Bot_Container.Controls.Add(this.btn_Change, 0, 1);
            this.Left_Bot_Container.Controls.Add(this.btn_Cancel, 0, 0);
            this.Left_Bot_Container.Cursor = System.Windows.Forms.Cursors.No;
            this.Left_Bot_Container.Dock = System.Windows.Forms.DockStyle.Fill;
            this.Left_Bot_Container.Location = new System.Drawing.Point(0, 176);
            this.Left_Bot_Container.Name = "Left_Bot_Container";
            this.Left_Bot_Container.RowCount = 2;
            this.Left_Bot_Container.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 39.45946F));
            this.Left_Bot_Container.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 60.54054F));
            this.Left_Bot_Container.Size = new System.Drawing.Size(130, 114);
            this.Left_Bot_Container.TabIndex = 1;
            // 
            // Left_Top_Container
            // 
            this.Left_Top_Container.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.Left_Top_Container.Controls.Add(this.btn_History);
            this.Left_Top_Container.Controls.Add(this.btn_Language);
            this.Left_Top_Container.Controls.Add(this.Money_Screen);
            this.Left_Top_Container.Controls.Add(this.Coin_Container);
            this.Left_Top_Container.Dock = System.Windows.Forms.DockStyle.Top;
            this.Left_Top_Container.Location = new System.Drawing.Point(0, 0);
            this.Left_Top_Container.Name = "Left_Top_Container";
            this.Left_Top_Container.Size = new System.Drawing.Size(130, 176);
            this.Left_Top_Container.TabIndex = 0;
            // 
            // btn_History
            // 
            this.btn_History.Cursor = System.Windows.Forms.Cursors.Default;
            this.btn_History.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.btn_History.Location = new System.Drawing.Point(62, 103);
            this.btn_History.Name = "btn_History";
            this.btn_History.Size = new System.Drawing.Size(64, 69);
            this.btn_History.TabIndex = 8;
            this.btn_History.Text = "Historial";
            this.btn_History.UseVisualStyleBackColor = true;
            this.btn_History.Click += new System.EventHandler(this.btn_History_Click);
            // 
            // btn_Language
            // 
            this.btn_Language.BackColor = System.Drawing.SystemColors.ControlDark;
            this.btn_Language.BackgroundImage = global::WFA_VendingMachine.Properties.Resources.usa;
            this.btn_Language.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.btn_Language.Dock = System.Windows.Forms.DockStyle.Top;
            this.btn_Language.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btn_Language.Location = new System.Drawing.Point(62, 0);
            this.btn_Language.Name = "btn_Language";
            this.btn_Language.Size = new System.Drawing.Size(64, 71);
            this.btn_Language.TabIndex = 7;
            this.btn_Language.UseVisualStyleBackColor = false;
            this.btn_Language.Click += new System.EventHandler(this.btn_Language_Click);
            // 
            // Money_Screen
            // 
            this.Money_Screen.AutoSize = true;
            this.Money_Screen.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.Money_Screen.Font = new System.Drawing.Font("Consolas", 16F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Money_Screen.ForeColor = System.Drawing.Color.Lime;
            this.Money_Screen.Location = new System.Drawing.Point(62, 74);
            this.Money_Screen.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.Money_Screen.Name = "Money_Screen";
            this.Money_Screen.Size = new System.Drawing.Size(72, 26);
            this.Money_Screen.TabIndex = 6;
            this.Money_Screen.Text = "0.00€";
            // 
            // Coin_Container
            // 
            this.Coin_Container.CellBorderStyle = System.Windows.Forms.TableLayoutPanelCellBorderStyle.Outset;
            this.Coin_Container.ColumnCount = 1;
            this.Coin_Container.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.Coin_Container.Controls.Add(this.btn_fifty_cent, 0, 0);
            this.Coin_Container.Controls.Add(this.btn_one_euro, 0, 1);
            this.Coin_Container.Controls.Add(this.btn_two_euro, 0, 2);
            this.Coin_Container.Dock = System.Windows.Forms.DockStyle.Left;
            this.Coin_Container.Location = new System.Drawing.Point(0, 0);
            this.Coin_Container.Name = "Coin_Container";
            this.Coin_Container.RowCount = 3;
            this.Coin_Container.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.Coin_Container.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.Coin_Container.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.Coin_Container.Size = new System.Drawing.Size(62, 172);
            this.Coin_Container.TabIndex = 5;
            // 
            // L_T_Label_Coin
            // 
            this.L_T_Label_Coin.AutoSize = true;
            this.L_T_Label_Coin.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.L_T_Label_Coin.Dock = System.Windows.Forms.DockStyle.Fill;
            this.L_T_Label_Coin.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.L_T_Label_Coin.ForeColor = System.Drawing.Color.Lime;
            this.L_T_Label_Coin.Location = new System.Drawing.Point(6, 3);
            this.L_T_Label_Coin.Name = "L_T_Label_Coin";
            this.L_T_Label_Coin.Size = new System.Drawing.Size(186, 42);
            this.L_T_Label_Coin.TabIndex = 1;
            this.L_T_Label_Coin.Text = "1. INSERT COIN";
            this.L_T_Label_Coin.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // btn_Drink_Pickup
            // 
            this.btn_Drink_Pickup.BackColor = System.Drawing.SystemColors.ControlText;
            this.btn_Drink_Pickup.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.btn_Drink_Pickup.Cursor = System.Windows.Forms.Cursors.No;
            this.btn_Drink_Pickup.Dock = System.Windows.Forms.DockStyle.Fill;
            this.btn_Drink_Pickup.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btn_Drink_Pickup.Location = new System.Drawing.Point(397, 51);
            this.btn_Drink_Pickup.Name = "btn_Drink_Pickup";
            this.btn_Drink_Pickup.Size = new System.Drawing.Size(188, 346);
            this.btn_Drink_Pickup.TabIndex = 5;
            this.btn_Drink_Pickup.UseVisualStyleBackColor = false;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(591, 403);
            this.Controls.Add(this.Background);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Background.ResumeLayout(false);
            this.Background.PerformLayout();
            this.M_Selection.ResumeLayout(false);
            this.M_Selection.PerformLayout();
            this.L_Compra.ResumeLayout(false);
            this.Left_Bot_Container.ResumeLayout(false);
            this.Left_Top_Container.ResumeLayout(false);
            this.Left_Top_Container.PerformLayout();
            this.Coin_Container.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion
        private System.Windows.Forms.ToolTip toolTip1;
        private System.Windows.Forms.TableLayoutPanel Background;
        private System.Windows.Forms.Label M_T_Label_Drink;
        private System.Windows.Forms.Label R_T_Label_Pickup;
        private System.Windows.Forms.Panel L_Compra;
        private System.Windows.Forms.TableLayoutPanel Left_Bot_Container;
        private System.Windows.Forms.Button btn_Change;
        private System.Windows.Forms.Button btn_Cancel;
        private System.Windows.Forms.Panel Left_Top_Container;
        private System.Windows.Forms.TableLayoutPanel Coin_Container;
        private System.Windows.Forms.Button btn_fifty_cent;
        private System.Windows.Forms.Button btn_one_euro;
        private System.Windows.Forms.Button btn_two_euro;
        private System.Windows.Forms.Label L_T_Label_Coin;
        private System.Windows.Forms.Button btn_Drink_Pickup;
        private System.Windows.Forms.TableLayoutPanel M_Selection;
        private System.Windows.Forms.Label Coca_Price;
        private System.Windows.Forms.Button btn_Fanta_Limon;
        private System.Windows.Forms.Button btn_JackDaniels;
        private System.Windows.Forms.Label Amstel_Price;
        private System.Windows.Forms.Label Sprite_Price;
        private System.Windows.Forms.Label Fanta_Price;
        private System.Windows.Forms.Button btn_Fanta;
        private System.Windows.Forms.Button btn_CocaCola;
        private System.Windows.Forms.Label Money_Screen;
        private System.Windows.Forms.Button btn_History;
        private System.Windows.Forms.Button btn_Language;
    }
}

