namespace WFA_BankTerminal
{
    partial class TerminalForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.btn_consultarSaldo = new System.Windows.Forms.Button();
            this.btn_sacarDinero = new System.Windows.Forms.Button();
            this.btn_salir = new System.Windows.Forms.Button();
            this.textBox_consultarSaldo = new System.Windows.Forms.TextBox();
            this.textBox_sacarDinero = new System.Windows.Forms.TextBox();
            this.textBox_salir = new System.Windows.Forms.TextBox();
            this.textBox_unused = new System.Windows.Forms.TextBox();
            this.textBox_terminal = new System.Windows.Forms.TextBox();
            this.btn_numpad_1 = new System.Windows.Forms.Button();
            this.btn_numpad_2 = new System.Windows.Forms.Button();
            this.btn_numpad_3 = new System.Windows.Forms.Button();
            this.btn_numpad_4 = new System.Windows.Forms.Button();
            this.btn_numpad_5 = new System.Windows.Forms.Button();
            this.btn_numpad_6 = new System.Windows.Forms.Button();
            this.btn_numpad_7 = new System.Windows.Forms.Button();
            this.btn_numpad_8 = new System.Windows.Forms.Button();
            this.btn_numpad_9 = new System.Windows.Forms.Button();
            this.btn_numpad_0 = new System.Windows.Forms.Button();
            this.btn_numpad_intro = new System.Windows.Forms.Button();
            this.toolTip = new System.Windows.Forms.ToolTip(this.components);
            this.btn_informe = new System.Windows.Forms.Button();
            this.btn_tarjetaIE = new System.Windows.Forms.Button();
            this.btn_retirarDinero = new System.Windows.Forms.Button();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.pictureBox2 = new System.Windows.Forms.PictureBox();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox2)).BeginInit();
            this.SuspendLayout();
            // 
            // btn_consultarSaldo
            // 
            this.btn_consultarSaldo.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_consultarSaldo.Location = new System.Drawing.Point(12, 12);
            this.btn_consultarSaldo.Name = "btn_consultarSaldo";
            this.btn_consultarSaldo.Size = new System.Drawing.Size(50, 50);
            this.btn_consultarSaldo.TabIndex = 0;
            this.btn_consultarSaldo.Text = "CS";
            this.btn_consultarSaldo.UseVisualStyleBackColor = true;
            this.btn_consultarSaldo.Click += new System.EventHandler(this.btn_consultarSaldo_Click);
            // 
            // btn_sacarDinero
            // 
            this.btn_sacarDinero.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_sacarDinero.Location = new System.Drawing.Point(703, 12);
            this.btn_sacarDinero.Name = "btn_sacarDinero";
            this.btn_sacarDinero.Size = new System.Drawing.Size(50, 50);
            this.btn_sacarDinero.TabIndex = 2;
            this.btn_sacarDinero.Text = "SD";
            this.btn_sacarDinero.UseVisualStyleBackColor = true;
            this.btn_sacarDinero.Click += new System.EventHandler(this.btn_sacarDinero_Click);
            // 
            // btn_salir
            // 
            this.btn_salir.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_salir.Location = new System.Drawing.Point(703, 68);
            this.btn_salir.Name = "btn_salir";
            this.btn_salir.Size = new System.Drawing.Size(50, 50);
            this.btn_salir.TabIndex = 3;
            this.btn_salir.Text = "S";
            this.btn_salir.UseVisualStyleBackColor = true;
            this.btn_salir.Click += new System.EventHandler(this.btn_salir_Click);
            // 
            // textBox_consultarSaldo
            // 
            this.textBox_consultarSaldo.BackColor = System.Drawing.SystemColors.WindowText;
            this.textBox_consultarSaldo.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.textBox_consultarSaldo.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.textBox_consultarSaldo.ForeColor = System.Drawing.SystemColors.Window;
            this.textBox_consultarSaldo.Location = new System.Drawing.Point(68, 12);
            this.textBox_consultarSaldo.Multiline = true;
            this.textBox_consultarSaldo.Name = "textBox_consultarSaldo";
            this.textBox_consultarSaldo.Size = new System.Drawing.Size(303, 50);
            this.textBox_consultarSaldo.TabIndex = 4;
            this.textBox_consultarSaldo.Text = "  <- Consultar Saldo";
            // 
            // textBox_sacarDinero
            // 
            this.textBox_sacarDinero.BackColor = System.Drawing.SystemColors.WindowText;
            this.textBox_sacarDinero.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.textBox_sacarDinero.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.textBox_sacarDinero.ForeColor = System.Drawing.SystemColors.Window;
            this.textBox_sacarDinero.Location = new System.Drawing.Point(377, 12);
            this.textBox_sacarDinero.Multiline = true;
            this.textBox_sacarDinero.Name = "textBox_sacarDinero";
            this.textBox_sacarDinero.Size = new System.Drawing.Size(320, 50);
            this.textBox_sacarDinero.TabIndex = 5;
            this.textBox_sacarDinero.Text = "Sacar Dinero ->  ";
            this.textBox_sacarDinero.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            // 
            // textBox_salir
            // 
            this.textBox_salir.BackColor = System.Drawing.SystemColors.WindowText;
            this.textBox_salir.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.textBox_salir.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.textBox_salir.ForeColor = System.Drawing.SystemColors.Window;
            this.textBox_salir.Location = new System.Drawing.Point(377, 68);
            this.textBox_salir.Multiline = true;
            this.textBox_salir.Name = "textBox_salir";
            this.textBox_salir.Size = new System.Drawing.Size(320, 50);
            this.textBox_salir.TabIndex = 6;
            this.textBox_salir.Text = "Salir ->  ";
            this.textBox_salir.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            // 
            // textBox_unused
            // 
            this.textBox_unused.BackColor = System.Drawing.SystemColors.WindowText;
            this.textBox_unused.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.textBox_unused.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.textBox_unused.ForeColor = System.Drawing.SystemColors.Window;
            this.textBox_unused.Location = new System.Drawing.Point(68, 68);
            this.textBox_unused.Multiline = true;
            this.textBox_unused.Name = "textBox_unused";
            this.textBox_unused.Size = new System.Drawing.Size(303, 50);
            this.textBox_unused.TabIndex = 7;
            // 
            // textBox_terminal
            // 
            this.textBox_terminal.BackColor = System.Drawing.SystemColors.WindowText;
            this.textBox_terminal.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.textBox_terminal.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.textBox_terminal.ForeColor = System.Drawing.SystemColors.Window;
            this.textBox_terminal.Location = new System.Drawing.Point(68, 124);
            this.textBox_terminal.Multiline = true;
            this.textBox_terminal.Name = "textBox_terminal";
            this.textBox_terminal.Size = new System.Drawing.Size(629, 128);
            this.textBox_terminal.TabIndex = 8;
            this.textBox_terminal.Text = "Introduzca tarjeta";
            this.textBox_terminal.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // btn_numpad_1
            // 
            this.btn_numpad_1.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_numpad_1.Location = new System.Drawing.Point(306, 258);
            this.btn_numpad_1.Name = "btn_numpad_1";
            this.btn_numpad_1.Size = new System.Drawing.Size(50, 50);
            this.btn_numpad_1.TabIndex = 9;
            this.btn_numpad_1.Text = "1";
            this.btn_numpad_1.UseVisualStyleBackColor = true;
            this.btn_numpad_1.Click += new System.EventHandler(this.btn_numpad_1_Click);
            // 
            // btn_numpad_2
            // 
            this.btn_numpad_2.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_numpad_2.Location = new System.Drawing.Point(362, 258);
            this.btn_numpad_2.Name = "btn_numpad_2";
            this.btn_numpad_2.Size = new System.Drawing.Size(50, 50);
            this.btn_numpad_2.TabIndex = 10;
            this.btn_numpad_2.Text = "2";
            this.btn_numpad_2.UseVisualStyleBackColor = true;
            this.btn_numpad_2.Click += new System.EventHandler(this.btn_numpad_2_Click);
            // 
            // btn_numpad_3
            // 
            this.btn_numpad_3.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_numpad_3.Location = new System.Drawing.Point(418, 258);
            this.btn_numpad_3.Name = "btn_numpad_3";
            this.btn_numpad_3.Size = new System.Drawing.Size(50, 50);
            this.btn_numpad_3.TabIndex = 11;
            this.btn_numpad_3.Text = "3";
            this.btn_numpad_3.UseVisualStyleBackColor = true;
            this.btn_numpad_3.Click += new System.EventHandler(this.btn_numpad_3_Click);
            // 
            // btn_numpad_4
            // 
            this.btn_numpad_4.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_numpad_4.Location = new System.Drawing.Point(306, 314);
            this.btn_numpad_4.Name = "btn_numpad_4";
            this.btn_numpad_4.Size = new System.Drawing.Size(50, 50);
            this.btn_numpad_4.TabIndex = 12;
            this.btn_numpad_4.Text = "4";
            this.btn_numpad_4.UseVisualStyleBackColor = true;
            this.btn_numpad_4.Click += new System.EventHandler(this.btn_numpad_4_Click);
            // 
            // btn_numpad_5
            // 
            this.btn_numpad_5.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_numpad_5.Location = new System.Drawing.Point(362, 314);
            this.btn_numpad_5.Name = "btn_numpad_5";
            this.btn_numpad_5.Size = new System.Drawing.Size(50, 50);
            this.btn_numpad_5.TabIndex = 13;
            this.btn_numpad_5.Text = "5";
            this.btn_numpad_5.UseVisualStyleBackColor = true;
            this.btn_numpad_5.Click += new System.EventHandler(this.btn_numpad_5_Click);
            // 
            // btn_numpad_6
            // 
            this.btn_numpad_6.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_numpad_6.Location = new System.Drawing.Point(418, 314);
            this.btn_numpad_6.Name = "btn_numpad_6";
            this.btn_numpad_6.Size = new System.Drawing.Size(50, 50);
            this.btn_numpad_6.TabIndex = 14;
            this.btn_numpad_6.Text = "6";
            this.btn_numpad_6.UseVisualStyleBackColor = true;
            this.btn_numpad_6.Click += new System.EventHandler(this.btn_numpad_6_Click);
            // 
            // btn_numpad_7
            // 
            this.btn_numpad_7.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_numpad_7.Location = new System.Drawing.Point(306, 370);
            this.btn_numpad_7.Name = "btn_numpad_7";
            this.btn_numpad_7.Size = new System.Drawing.Size(50, 50);
            this.btn_numpad_7.TabIndex = 15;
            this.btn_numpad_7.Text = "7";
            this.btn_numpad_7.UseVisualStyleBackColor = true;
            this.btn_numpad_7.Click += new System.EventHandler(this.btn_numpad_7_Click);
            // 
            // btn_numpad_8
            // 
            this.btn_numpad_8.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_numpad_8.Location = new System.Drawing.Point(362, 370);
            this.btn_numpad_8.Name = "btn_numpad_8";
            this.btn_numpad_8.Size = new System.Drawing.Size(50, 50);
            this.btn_numpad_8.TabIndex = 16;
            this.btn_numpad_8.Text = "8";
            this.btn_numpad_8.UseVisualStyleBackColor = true;
            this.btn_numpad_8.Click += new System.EventHandler(this.btn_numpad_8_Click);
            // 
            // btn_numpad_9
            // 
            this.btn_numpad_9.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_numpad_9.Location = new System.Drawing.Point(418, 370);
            this.btn_numpad_9.Name = "btn_numpad_9";
            this.btn_numpad_9.Size = new System.Drawing.Size(50, 50);
            this.btn_numpad_9.TabIndex = 17;
            this.btn_numpad_9.Text = "9";
            this.btn_numpad_9.UseVisualStyleBackColor = true;
            this.btn_numpad_9.Click += new System.EventHandler(this.btn_numpad_9_Click);
            // 
            // btn_numpad_0
            // 
            this.btn_numpad_0.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_numpad_0.Location = new System.Drawing.Point(362, 426);
            this.btn_numpad_0.Name = "btn_numpad_0";
            this.btn_numpad_0.Size = new System.Drawing.Size(50, 50);
            this.btn_numpad_0.TabIndex = 18;
            this.btn_numpad_0.Text = "0";
            this.btn_numpad_0.UseVisualStyleBackColor = true;
            this.btn_numpad_0.Click += new System.EventHandler(this.btn_numpad_0_Click);
            // 
            // btn_numpad_intro
            // 
            this.btn_numpad_intro.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_numpad_intro.Location = new System.Drawing.Point(418, 426);
            this.btn_numpad_intro.Name = "btn_numpad_intro";
            this.btn_numpad_intro.Size = new System.Drawing.Size(50, 50);
            this.btn_numpad_intro.TabIndex = 19;
            this.btn_numpad_intro.Text = "->";
            this.btn_numpad_intro.UseVisualStyleBackColor = true;
            this.btn_numpad_intro.Click += new System.EventHandler(this.btn_numpad_intro_Click);
            // 
            // btn_informe
            // 
            this.btn_informe.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_informe.Location = new System.Drawing.Point(306, 482);
            this.btn_informe.Name = "btn_informe";
            this.btn_informe.Size = new System.Drawing.Size(162, 50);
            this.btn_informe.TabIndex = 20;
            this.btn_informe.Text = "Informe";
            this.btn_informe.UseVisualStyleBackColor = true;
            this.btn_informe.Click += new System.EventHandler(this.btn_informe_Click);
            // 
            // btn_tarjetaIE
            // 
            this.btn_tarjetaIE.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_tarjetaIE.Location = new System.Drawing.Point(12, 426);
            this.btn_tarjetaIE.Name = "btn_tarjetaIE";
            this.btn_tarjetaIE.Size = new System.Drawing.Size(288, 50);
            this.btn_tarjetaIE.TabIndex = 21;
            this.btn_tarjetaIE.Text = "Intr./Extr. tarjeta";
            this.btn_tarjetaIE.UseVisualStyleBackColor = true;
            this.btn_tarjetaIE.Click += new System.EventHandler(this.btn_tarjetaIE_Click);
            // 
            // btn_retirarDinero
            // 
            this.btn_retirarDinero.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_retirarDinero.Location = new System.Drawing.Point(474, 426);
            this.btn_retirarDinero.Name = "btn_retirarDinero";
            this.btn_retirarDinero.Size = new System.Drawing.Size(279, 50);
            this.btn_retirarDinero.TabIndex = 22;
            this.btn_retirarDinero.Text = "Retirar dinero";
            this.btn_retirarDinero.UseVisualStyleBackColor = true;
            this.btn_retirarDinero.Click += new System.EventHandler(this.btn_retirarDinero_Click);
            // 
            // pictureBox1
            // 
            this.pictureBox1.Image = global::WFA_BankTerminal.Properties.Resources.Retirar_dinero;
            this.pictureBox1.Location = new System.Drawing.Point(474, 258);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(279, 164);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pictureBox1.TabIndex = 23;
            this.pictureBox1.TabStop = false;
            // 
            // pictureBox2
            // 
            this.pictureBox2.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.pictureBox2.Image = global::WFA_BankTerminal.Properties.Resources.Intr__Extr__tarjeta;
            this.pictureBox2.Location = new System.Drawing.Point(12, 258);
            this.pictureBox2.Name = "pictureBox2";
            this.pictureBox2.Size = new System.Drawing.Size(288, 164);
            this.pictureBox2.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pictureBox2.TabIndex = 24;
            this.pictureBox2.TabStop = false;
            // 
            // TerminalForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(765, 545);
            this.Controls.Add(this.pictureBox2);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.btn_retirarDinero);
            this.Controls.Add(this.btn_tarjetaIE);
            this.Controls.Add(this.btn_informe);
            this.Controls.Add(this.btn_numpad_intro);
            this.Controls.Add(this.btn_numpad_0);
            this.Controls.Add(this.btn_numpad_9);
            this.Controls.Add(this.btn_numpad_8);
            this.Controls.Add(this.btn_numpad_7);
            this.Controls.Add(this.btn_numpad_6);
            this.Controls.Add(this.btn_numpad_5);
            this.Controls.Add(this.btn_numpad_4);
            this.Controls.Add(this.btn_numpad_3);
            this.Controls.Add(this.btn_numpad_2);
            this.Controls.Add(this.btn_numpad_1);
            this.Controls.Add(this.textBox_terminal);
            this.Controls.Add(this.textBox_unused);
            this.Controls.Add(this.textBox_salir);
            this.Controls.Add(this.textBox_sacarDinero);
            this.Controls.Add(this.textBox_consultarSaldo);
            this.Controls.Add(this.btn_salir);
            this.Controls.Add(this.btn_sacarDinero);
            this.Controls.Add(this.btn_consultarSaldo);
            this.Name = "TerminalForm";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox2)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btn_consultarSaldo;
        private System.Windows.Forms.Button btn_sacarDinero;
        private System.Windows.Forms.Button btn_salir;
        private System.Windows.Forms.TextBox textBox_consultarSaldo;
        private System.Windows.Forms.TextBox textBox_sacarDinero;
        private System.Windows.Forms.TextBox textBox_salir;
        private System.Windows.Forms.TextBox textBox_unused;
        private System.Windows.Forms.TextBox textBox_terminal;
        private System.Windows.Forms.Button btn_numpad_1;
        private System.Windows.Forms.Button btn_numpad_2;
        private System.Windows.Forms.Button btn_numpad_3;
        private System.Windows.Forms.Button btn_numpad_4;
        private System.Windows.Forms.Button btn_numpad_5;
        private System.Windows.Forms.Button btn_numpad_6;
        private System.Windows.Forms.Button btn_numpad_7;
        private System.Windows.Forms.Button btn_numpad_8;
        private System.Windows.Forms.Button btn_numpad_9;
        private System.Windows.Forms.Button btn_numpad_0;
        private System.Windows.Forms.Button btn_numpad_intro;
        private System.Windows.Forms.ToolTip toolTip;
        private System.Windows.Forms.Button btn_informe;
        private System.Windows.Forms.Button btn_tarjetaIE;
        private System.Windows.Forms.Button btn_retirarDinero;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.PictureBox pictureBox2;
    }
}

