namespace WFA_DigitalWatch
{
    partial class WatchForm
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
            this.Btn_Incrementar = new System.Windows.Forms.Button();
            this.Btn_Ajustar = new System.Windows.Forms.Button();
            this.Btn_Decrementar = new System.Windows.Forms.Button();
            this.Lbl_Decrementar = new System.Windows.Forms.Label();
            this.Lbl_Incrementar = new System.Windows.Forms.Label();
            this.Lbl_Ajustar = new System.Windows.Forms.Label();
            this.Btn_Informe = new System.Windows.Forms.Button();
            this.button1 = new System.Windows.Forms.Button();
            this.Lbl_Iluminar = new System.Windows.Forms.Label();
            this.toolTip1 = new System.Windows.Forms.ToolTip(this.components);
            this.tb_hora = new System.Windows.Forms.TextBox();
            this.tb_separador = new System.Windows.Forms.TextBox();
            this.tb_min = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // Btn_Incrementar
            // 
            this.Btn_Incrementar.BackColor = System.Drawing.SystemColors.ControlDark;
            this.Btn_Incrementar.FlatAppearance.BorderColor = System.Drawing.SystemColors.ControlDarkDark;
            this.Btn_Incrementar.FlatStyle = System.Windows.Forms.FlatStyle.Popup;
            this.Btn_Incrementar.Location = new System.Drawing.Point(460, 244);
            this.Btn_Incrementar.Margin = new System.Windows.Forms.Padding(10);
            this.Btn_Incrementar.Name = "Btn_Incrementar";
            this.Btn_Incrementar.Size = new System.Drawing.Size(48, 48);
            this.Btn_Incrementar.TabIndex = 0;
            this.Btn_Incrementar.UseVisualStyleBackColor = false;
            this.Btn_Incrementar.Click += new System.EventHandler(this.Btn_Incrementar_Click);
            // 
            // Btn_Ajustar
            // 
            this.Btn_Ajustar.BackColor = System.Drawing.SystemColors.ControlDark;
            this.Btn_Ajustar.FlatAppearance.BorderColor = System.Drawing.SystemColors.ControlDarkDark;
            this.Btn_Ajustar.FlatStyle = System.Windows.Forms.FlatStyle.Popup;
            this.Btn_Ajustar.Location = new System.Drawing.Point(90, 308);
            this.Btn_Ajustar.Margin = new System.Windows.Forms.Padding(10);
            this.Btn_Ajustar.Name = "Btn_Ajustar";
            this.Btn_Ajustar.Size = new System.Drawing.Size(48, 48);
            this.Btn_Ajustar.TabIndex = 1;
            this.Btn_Ajustar.UseVisualStyleBackColor = false;
            this.Btn_Ajustar.Click += new System.EventHandler(this.Btn_Ajustar_Click);
            // 
            // Btn_Decrementar
            // 
            this.Btn_Decrementar.BackColor = System.Drawing.SystemColors.ControlDark;
            this.Btn_Decrementar.FlatAppearance.BorderColor = System.Drawing.SystemColors.ControlDarkDark;
            this.Btn_Decrementar.FlatStyle = System.Windows.Forms.FlatStyle.Popup;
            this.Btn_Decrementar.Location = new System.Drawing.Point(460, 308);
            this.Btn_Decrementar.Margin = new System.Windows.Forms.Padding(10);
            this.Btn_Decrementar.Name = "Btn_Decrementar";
            this.Btn_Decrementar.Size = new System.Drawing.Size(48, 48);
            this.Btn_Decrementar.TabIndex = 2;
            this.Btn_Decrementar.UseVisualStyleBackColor = false;
            this.Btn_Decrementar.Click += new System.EventHandler(this.Btn_Decrementar_Click);
            // 
            // Lbl_Decrementar
            // 
            this.Lbl_Decrementar.AutoSize = true;
            this.Lbl_Decrementar.Font = new System.Drawing.Font("Consolas", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Lbl_Decrementar.Location = new System.Drawing.Point(521, 322);
            this.Lbl_Decrementar.Name = "Lbl_Decrementar";
            this.Lbl_Decrementar.Size = new System.Drawing.Size(108, 19);
            this.Lbl_Decrementar.TabIndex = 3;
            this.Lbl_Decrementar.Text = "Decrementar";
            // 
            // Lbl_Incrementar
            // 
            this.Lbl_Incrementar.AutoSize = true;
            this.Lbl_Incrementar.Font = new System.Drawing.Font("Consolas", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Lbl_Incrementar.Location = new System.Drawing.Point(521, 258);
            this.Lbl_Incrementar.Name = "Lbl_Incrementar";
            this.Lbl_Incrementar.Size = new System.Drawing.Size(108, 19);
            this.Lbl_Incrementar.TabIndex = 4;
            this.Lbl_Incrementar.Text = "Incrementar";
            // 
            // Lbl_Ajustar
            // 
            this.Lbl_Ajustar.AutoSize = true;
            this.Lbl_Ajustar.Font = new System.Drawing.Font("Consolas", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Lbl_Ajustar.Location = new System.Drawing.Point(5, 322);
            this.Lbl_Ajustar.Name = "Lbl_Ajustar";
            this.Lbl_Ajustar.Size = new System.Drawing.Size(72, 19);
            this.Lbl_Ajustar.TabIndex = 5;
            this.Lbl_Ajustar.Text = "Ajustar";
            // 
            // Btn_Informe
            // 
            this.Btn_Informe.BackColor = System.Drawing.SystemColors.ControlDark;
            this.Btn_Informe.FlatAppearance.BorderColor = System.Drawing.SystemColors.ControlDarkDark;
            this.Btn_Informe.FlatStyle = System.Windows.Forms.FlatStyle.Popup;
            this.Btn_Informe.Font = new System.Drawing.Font("Consolas", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Btn_Informe.Location = new System.Drawing.Point(249, 369);
            this.Btn_Informe.Margin = new System.Windows.Forms.Padding(10);
            this.Btn_Informe.Name = "Btn_Informe";
            this.Btn_Informe.Size = new System.Drawing.Size(116, 48);
            this.Btn_Informe.TabIndex = 6;
            this.Btn_Informe.Text = "Informe";
            this.Btn_Informe.UseVisualStyleBackColor = false;
            this.Btn_Informe.Click += new System.EventHandler(this.Btn_Informe_Click);
            // 
            // button1
            // 
            this.button1.BackColor = System.Drawing.SystemColors.ControlDark;
            this.button1.FlatAppearance.BorderColor = System.Drawing.SystemColors.ControlDarkDark;
            this.button1.FlatStyle = System.Windows.Forms.FlatStyle.Popup;
            this.button1.Location = new System.Drawing.Point(90, 244);
            this.button1.Margin = new System.Windows.Forms.Padding(10);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(48, 48);
            this.button1.TabIndex = 7;
            this.button1.UseVisualStyleBackColor = false;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // Lbl_Iluminar
            // 
            this.Lbl_Iluminar.AutoSize = true;
            this.Lbl_Iluminar.Font = new System.Drawing.Font("Consolas", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Lbl_Iluminar.Location = new System.Drawing.Point(5, 258);
            this.Lbl_Iluminar.Name = "Lbl_Iluminar";
            this.Lbl_Iluminar.Size = new System.Drawing.Size(81, 19);
            this.Lbl_Iluminar.TabIndex = 8;
            this.Lbl_Iluminar.Text = "Iluminar";
            // 
            // tb_hora
            // 
            this.tb_hora.BackColor = System.Drawing.SystemColors.WindowText;
            this.tb_hora.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.tb_hora.Font = new System.Drawing.Font("Consolas", 72F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.tb_hora.ForeColor = System.Drawing.SystemColors.Window;
            this.tb_hora.Location = new System.Drawing.Point(151, 244);
            this.tb_hora.Multiline = true;
            this.tb_hora.Name = "tb_hora";
            this.tb_hora.Size = new System.Drawing.Size(125, 112);
            this.tb_hora.TabIndex = 9;
            this.tb_hora.Text = "00";
            // 
            // tb_separador
            // 
            this.tb_separador.BackColor = System.Drawing.SystemColors.WindowText;
            this.tb_separador.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.tb_separador.Font = new System.Drawing.Font("Consolas", 72F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.tb_separador.ForeColor = System.Drawing.SystemColors.Window;
            this.tb_separador.Location = new System.Drawing.Point(282, 244);
            this.tb_separador.Multiline = true;
            this.tb_separador.Name = "tb_separador";
            this.tb_separador.Size = new System.Drawing.Size(50, 112);
            this.tb_separador.TabIndex = 10;
            this.tb_separador.Text = ":";
            // 
            // tb_min
            // 
            this.tb_min.BackColor = System.Drawing.SystemColors.WindowText;
            this.tb_min.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.tb_min.Font = new System.Drawing.Font("Consolas", 72F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.tb_min.ForeColor = System.Drawing.SystemColors.Window;
            this.tb_min.Location = new System.Drawing.Point(338, 244);
            this.tb_min.Multiline = true;
            this.tb_min.Name = "tb_min";
            this.tb_min.Size = new System.Drawing.Size(109, 112);
            this.tb_min.TabIndex = 11;
            this.tb_min.Text = "00";
            // 
            // WatchForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(725, 509);
            this.Controls.Add(this.tb_min);
            this.Controls.Add(this.tb_separador);
            this.Controls.Add(this.tb_hora);
            this.Controls.Add(this.Lbl_Iluminar);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.Btn_Informe);
            this.Controls.Add(this.Lbl_Ajustar);
            this.Controls.Add(this.Lbl_Incrementar);
            this.Controls.Add(this.Lbl_Decrementar);
            this.Controls.Add(this.Btn_Decrementar);
            this.Controls.Add(this.Btn_Ajustar);
            this.Controls.Add(this.Btn_Incrementar);
            this.Name = "WatchForm";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button Btn_Incrementar;
        private System.Windows.Forms.Button Btn_Ajustar;
        private System.Windows.Forms.Button Btn_Decrementar;
        private System.Windows.Forms.Label Lbl_Decrementar;
        private System.Windows.Forms.Label Lbl_Incrementar;
        private System.Windows.Forms.Label Lbl_Ajustar;
        private System.Windows.Forms.Button Btn_Informe;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Label Lbl_Iluminar;
        private System.Windows.Forms.ToolTip toolTip1;
        private System.Windows.Forms.TextBox tb_hora;
        private System.Windows.Forms.TextBox tb_separador;
        private System.Windows.Forms.TextBox tb_min;
    }
}

