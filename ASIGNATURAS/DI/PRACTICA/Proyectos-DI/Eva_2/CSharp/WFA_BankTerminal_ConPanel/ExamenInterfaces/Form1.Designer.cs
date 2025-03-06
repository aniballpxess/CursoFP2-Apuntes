namespace ExamenInterfaces
{
    partial class Form1
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
            this.background = new System.Windows.Forms.Panel();
            this.panel_bottom = new System.Windows.Forms.Panel();
            this.panel_dinero = new System.Windows.Forms.Panel();
            this.teclado = new System.Windows.Forms.TableLayoutPanel();
            this.btn_enter = new System.Windows.Forms.Button();
            this.btn_0 = new System.Windows.Forms.Button();
            this.btn_1 = new System.Windows.Forms.Button();
            this.btn_2 = new System.Windows.Forms.Button();
            this.btn_3 = new System.Windows.Forms.Button();
            this.btn_4 = new System.Windows.Forms.Button();
            this.btn_5 = new System.Windows.Forms.Button();
            this.btn_6 = new System.Windows.Forms.Button();
            this.btn_7 = new System.Windows.Forms.Button();
            this.btn_9 = new System.Windows.Forms.Button();
            this.btn__8 = new System.Windows.Forms.Button();
            this.panel_retirar = new System.Windows.Forms.Panel();
            this.foto_dinero = new System.Windows.Forms.PictureBox();
            this.btn_dinero = new System.Windows.Forms.Button();
            this.panel_tarjeta = new System.Windows.Forms.Panel();
            this.foto_tarjeta = new System.Windows.Forms.PictureBox();
            this.btn_tarjeta = new System.Windows.Forms.Button();
            this.panel_informe = new System.Windows.Forms.Panel();
            this.btn_Informe = new System.Windows.Forms.Button();
            this.panel_top = new System.Windows.Forms.Panel();
            this.panel_t_mid = new System.Windows.Forms.Panel();
            this.label_texto = new System.Windows.Forms.Label();
            this.panel_t_der = new System.Windows.Forms.Panel();
            this.salir = new System.Windows.Forms.Button();
            this.sacar_dinero = new System.Windows.Forms.Button();
            this.panel_t_izq = new System.Windows.Forms.Panel();
            this.consult_saldo = new System.Windows.Forms.Button();
            this.toolTip1 = new System.Windows.Forms.ToolTip(this.components);
            this.background.SuspendLayout();
            this.panel_bottom.SuspendLayout();
            this.panel_dinero.SuspendLayout();
            this.teclado.SuspendLayout();
            this.panel_retirar.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.foto_dinero)).BeginInit();
            this.panel_tarjeta.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.foto_tarjeta)).BeginInit();
            this.panel_informe.SuspendLayout();
            this.panel_top.SuspendLayout();
            this.panel_t_mid.SuspendLayout();
            this.panel_t_der.SuspendLayout();
            this.panel_t_izq.SuspendLayout();
            this.SuspendLayout();
            // 
            // background
            // 
            this.background.BackColor = System.Drawing.SystemColors.ActiveBorder;
            this.background.Controls.Add(this.panel_bottom);
            this.background.Controls.Add(this.panel_top);
            this.background.Dock = System.Windows.Forms.DockStyle.Fill;
            this.background.Location = new System.Drawing.Point(0, 0);
            this.background.Name = "background";
            this.background.Size = new System.Drawing.Size(881, 547);
            this.background.TabIndex = 0;
            // 
            // panel_bottom
            // 
            this.panel_bottom.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panel_bottom.Controls.Add(this.panel_dinero);
            this.panel_bottom.Controls.Add(this.panel_informe);
            this.panel_bottom.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.panel_bottom.Location = new System.Drawing.Point(0, 276);
            this.panel_bottom.Name = "panel_bottom";
            this.panel_bottom.Size = new System.Drawing.Size(881, 271);
            this.panel_bottom.TabIndex = 1;
            // 
            // panel_dinero
            // 
            this.panel_dinero.Controls.Add(this.teclado);
            this.panel_dinero.Controls.Add(this.panel_retirar);
            this.panel_dinero.Controls.Add(this.panel_tarjeta);
            this.panel_dinero.Dock = System.Windows.Forms.DockStyle.Top;
            this.panel_dinero.Location = new System.Drawing.Point(0, 0);
            this.panel_dinero.Name = "panel_dinero";
            this.panel_dinero.Size = new System.Drawing.Size(879, 202);
            this.panel_dinero.TabIndex = 1;
            // 
            // teclado
            // 
            this.teclado.ColumnCount = 3;
            this.teclado.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.teclado.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.teclado.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.teclado.Controls.Add(this.btn_enter, 2, 3);
            this.teclado.Controls.Add(this.btn_0, 1, 3);
            this.teclado.Controls.Add(this.btn_1, 0, 2);
            this.teclado.Controls.Add(this.btn_2, 1, 2);
            this.teclado.Controls.Add(this.btn_3, 2, 2);
            this.teclado.Controls.Add(this.btn_4, 0, 1);
            this.teclado.Controls.Add(this.btn_5, 1, 1);
            this.teclado.Controls.Add(this.btn_6, 2, 1);
            this.teclado.Controls.Add(this.btn_7, 0, 0);
            this.teclado.Controls.Add(this.btn_9, 2, 0);
            this.teclado.Controls.Add(this.btn__8, 1, 0);
            this.teclado.Dock = System.Windows.Forms.DockStyle.Fill;
            this.teclado.Location = new System.Drawing.Point(267, 0);
            this.teclado.Name = "teclado";
            this.teclado.RowCount = 4;
            this.teclado.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 25F));
            this.teclado.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 25F));
            this.teclado.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 25F));
            this.teclado.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 25F));
            this.teclado.Size = new System.Drawing.Size(353, 202);
            this.teclado.TabIndex = 4;
            // 
            // btn_enter
            // 
            this.btn_enter.Dock = System.Windows.Forms.DockStyle.Fill;
            this.btn_enter.Location = new System.Drawing.Point(237, 153);
            this.btn_enter.Name = "btn_enter";
            this.btn_enter.Size = new System.Drawing.Size(113, 46);
            this.btn_enter.TabIndex = 11;
            this.btn_enter.Text = "->";
            this.btn_enter.UseVisualStyleBackColor = true;
            this.btn_enter.Click += new System.EventHandler(this.btn_enter_Click_1);
            // 
            // btn_0
            // 
            this.btn_0.Dock = System.Windows.Forms.DockStyle.Fill;
            this.btn_0.Location = new System.Drawing.Point(120, 153);
            this.btn_0.Name = "btn_0";
            this.btn_0.Size = new System.Drawing.Size(111, 46);
            this.btn_0.TabIndex = 10;
            this.btn_0.Text = "0";
            this.btn_0.UseVisualStyleBackColor = true;
            // 
            // btn_1
            // 
            this.btn_1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.btn_1.Location = new System.Drawing.Point(3, 103);
            this.btn_1.Name = "btn_1";
            this.btn_1.Size = new System.Drawing.Size(111, 44);
            this.btn_1.TabIndex = 6;
            this.btn_1.Text = "1";
            this.btn_1.UseVisualStyleBackColor = true;
            // 
            // btn_2
            // 
            this.btn_2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.btn_2.Location = new System.Drawing.Point(120, 103);
            this.btn_2.Name = "btn_2";
            this.btn_2.Size = new System.Drawing.Size(111, 44);
            this.btn_2.TabIndex = 7;
            this.btn_2.Text = "2";
            this.btn_2.UseVisualStyleBackColor = true;
            // 
            // btn_3
            // 
            this.btn_3.Dock = System.Windows.Forms.DockStyle.Fill;
            this.btn_3.Location = new System.Drawing.Point(237, 103);
            this.btn_3.Name = "btn_3";
            this.btn_3.Size = new System.Drawing.Size(113, 44);
            this.btn_3.TabIndex = 8;
            this.btn_3.Text = "3";
            this.btn_3.UseVisualStyleBackColor = true;
            // 
            // btn_4
            // 
            this.btn_4.Dock = System.Windows.Forms.DockStyle.Fill;
            this.btn_4.Location = new System.Drawing.Point(3, 53);
            this.btn_4.Name = "btn_4";
            this.btn_4.Size = new System.Drawing.Size(111, 44);
            this.btn_4.TabIndex = 3;
            this.btn_4.Text = "4";
            this.btn_4.UseVisualStyleBackColor = true;
            // 
            // btn_5
            // 
            this.btn_5.Dock = System.Windows.Forms.DockStyle.Fill;
            this.btn_5.Location = new System.Drawing.Point(120, 53);
            this.btn_5.Name = "btn_5";
            this.btn_5.Size = new System.Drawing.Size(111, 44);
            this.btn_5.TabIndex = 4;
            this.btn_5.Text = "5";
            this.btn_5.UseVisualStyleBackColor = true;
            // 
            // btn_6
            // 
            this.btn_6.Dock = System.Windows.Forms.DockStyle.Fill;
            this.btn_6.Location = new System.Drawing.Point(237, 53);
            this.btn_6.Name = "btn_6";
            this.btn_6.Size = new System.Drawing.Size(113, 44);
            this.btn_6.TabIndex = 5;
            this.btn_6.Text = "6";
            this.btn_6.UseVisualStyleBackColor = true;
            // 
            // btn_7
            // 
            this.btn_7.Dock = System.Windows.Forms.DockStyle.Fill;
            this.btn_7.Location = new System.Drawing.Point(3, 3);
            this.btn_7.Name = "btn_7";
            this.btn_7.Size = new System.Drawing.Size(111, 44);
            this.btn_7.TabIndex = 0;
            this.btn_7.Text = "7";
            this.btn_7.UseVisualStyleBackColor = true;
            // 
            // btn_9
            // 
            this.btn_9.Dock = System.Windows.Forms.DockStyle.Fill;
            this.btn_9.Location = new System.Drawing.Point(237, 3);
            this.btn_9.Name = "btn_9";
            this.btn_9.Size = new System.Drawing.Size(113, 44);
            this.btn_9.TabIndex = 2;
            this.btn_9.Text = "9";
            this.btn_9.UseVisualStyleBackColor = true;
            // 
            // btn__8
            // 
            this.btn__8.Dock = System.Windows.Forms.DockStyle.Fill;
            this.btn__8.Location = new System.Drawing.Point(120, 3);
            this.btn__8.Name = "btn__8";
            this.btn__8.Size = new System.Drawing.Size(111, 44);
            this.btn__8.TabIndex = 12;
            this.btn__8.Text = "8";
            this.btn__8.UseVisualStyleBackColor = true;
            // 
            // panel_retirar
            // 
            this.panel_retirar.Controls.Add(this.foto_dinero);
            this.panel_retirar.Controls.Add(this.btn_dinero);
            this.panel_retirar.Dock = System.Windows.Forms.DockStyle.Right;
            this.panel_retirar.Location = new System.Drawing.Point(620, 0);
            this.panel_retirar.Name = "panel_retirar";
            this.panel_retirar.Size = new System.Drawing.Size(259, 202);
            this.panel_retirar.TabIndex = 2;
            // 
            // foto_dinero
            // 
            this.foto_dinero.BackColor = System.Drawing.SystemColors.ButtonHighlight;
            this.foto_dinero.BackgroundImage = global::ExamenInterfaces.Properties.Resources.retirardinero;
            this.foto_dinero.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.foto_dinero.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.foto_dinero.Dock = System.Windows.Forms.DockStyle.Fill;
            this.foto_dinero.Location = new System.Drawing.Point(0, 0);
            this.foto_dinero.Name = "foto_dinero";
            this.foto_dinero.Size = new System.Drawing.Size(259, 126);
            this.foto_dinero.TabIndex = 3;
            this.foto_dinero.TabStop = false;
            // 
            // btn_dinero
            // 
            this.btn_dinero.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.btn_dinero.Location = new System.Drawing.Point(0, 126);
            this.btn_dinero.Name = "btn_dinero";
            this.btn_dinero.Size = new System.Drawing.Size(259, 76);
            this.btn_dinero.TabIndex = 1;
            this.btn_dinero.Text = "Retirar Dinero";
            this.btn_dinero.UseVisualStyleBackColor = true;
            this.btn_dinero.Click += new System.EventHandler(this.btn_dinero_Click);
            // 
            // panel_tarjeta
            // 
            this.panel_tarjeta.Controls.Add(this.foto_tarjeta);
            this.panel_tarjeta.Controls.Add(this.btn_tarjeta);
            this.panel_tarjeta.Dock = System.Windows.Forms.DockStyle.Left;
            this.panel_tarjeta.Location = new System.Drawing.Point(0, 0);
            this.panel_tarjeta.Name = "panel_tarjeta";
            this.panel_tarjeta.Size = new System.Drawing.Size(267, 202);
            this.panel_tarjeta.TabIndex = 0;
            // 
            // foto_tarjeta
            // 
            this.foto_tarjeta.BackColor = System.Drawing.SystemColors.ButtonHighlight;
            this.foto_tarjeta.BackgroundImage = global::ExamenInterfaces.Properties.Resources.introtarjeta;
            this.foto_tarjeta.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.foto_tarjeta.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.foto_tarjeta.Dock = System.Windows.Forms.DockStyle.Fill;
            this.foto_tarjeta.Location = new System.Drawing.Point(0, 0);
            this.foto_tarjeta.Name = "foto_tarjeta";
            this.foto_tarjeta.Size = new System.Drawing.Size(267, 126);
            this.foto_tarjeta.TabIndex = 2;
            this.foto_tarjeta.TabStop = false;
            // 
            // btn_tarjeta
            // 
            this.btn_tarjeta.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.btn_tarjeta.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.btn_tarjeta.Location = new System.Drawing.Point(0, 126);
            this.btn_tarjeta.Name = "btn_tarjeta";
            this.btn_tarjeta.Size = new System.Drawing.Size(267, 76);
            this.btn_tarjeta.TabIndex = 1;
            this.btn_tarjeta.Text = "Intr. /Extr Tarjeta";
            this.btn_tarjeta.UseVisualStyleBackColor = true;
            this.btn_tarjeta.Click += new System.EventHandler(this.btn_tarjeta_Click);
            // 
            // panel_informe
            // 
            this.panel_informe.Controls.Add(this.btn_Informe);
            this.panel_informe.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.panel_informe.Location = new System.Drawing.Point(0, 208);
            this.panel_informe.Name = "panel_informe";
            this.panel_informe.Size = new System.Drawing.Size(879, 61);
            this.panel_informe.TabIndex = 0;
            // 
            // btn_Informe
            // 
            this.btn_Informe.Location = new System.Drawing.Point(267, 3);
            this.btn_Informe.Name = "btn_Informe";
            this.btn_Informe.Size = new System.Drawing.Size(350, 55);
            this.btn_Informe.TabIndex = 0;
            this.btn_Informe.Text = "Informe";
            this.btn_Informe.UseVisualStyleBackColor = true;
            this.btn_Informe.Click += new System.EventHandler(this.btn_Informe_Click);
            // 
            // panel_top
            // 
            this.panel_top.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panel_top.Controls.Add(this.panel_t_mid);
            this.panel_top.Controls.Add(this.panel_t_der);
            this.panel_top.Controls.Add(this.panel_t_izq);
            this.panel_top.Dock = System.Windows.Forms.DockStyle.Top;
            this.panel_top.Location = new System.Drawing.Point(0, 0);
            this.panel_top.Name = "panel_top";
            this.panel_top.Size = new System.Drawing.Size(881, 271);
            this.panel_top.TabIndex = 0;
            // 
            // panel_t_mid
            // 
            this.panel_t_mid.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.panel_t_mid.Controls.Add(this.label_texto);
            this.panel_t_mid.Cursor = System.Windows.Forms.Cursors.No;
            this.panel_t_mid.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel_t_mid.Location = new System.Drawing.Point(104, 0);
            this.panel_t_mid.Name = "panel_t_mid";
            this.panel_t_mid.Size = new System.Drawing.Size(663, 269);
            this.panel_t_mid.TabIndex = 2;
            // 
            // label_texto
            // 
            this.label_texto.AutoSize = true;
            this.label_texto.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.label_texto.Cursor = System.Windows.Forms.Cursors.No;
            this.label_texto.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label_texto.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.label_texto.Location = new System.Drawing.Point(220, 146);
            this.label_texto.Name = "label_texto";
            this.label_texto.Size = new System.Drawing.Size(233, 32);
            this.label_texto.TabIndex = 0;
            this.label_texto.Text = "Introduzca tarjeta";
            // 
            // panel_t_der
            // 
            this.panel_t_der.Controls.Add(this.salir);
            this.panel_t_der.Controls.Add(this.sacar_dinero);
            this.panel_t_der.Dock = System.Windows.Forms.DockStyle.Right;
            this.panel_t_der.Location = new System.Drawing.Point(767, 0);
            this.panel_t_der.Name = "panel_t_der";
            this.panel_t_der.Size = new System.Drawing.Size(112, 269);
            this.panel_t_der.TabIndex = 1;
            // 
            // salir
            // 
            this.salir.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.salir.Location = new System.Drawing.Point(0, 180);
            this.salir.Name = "salir";
            this.salir.Size = new System.Drawing.Size(112, 89);
            this.salir.TabIndex = 1;
            this.salir.Text = "S";
            this.toolTip1.SetToolTip(this.salir, "Salir");
            this.salir.UseVisualStyleBackColor = true;
            this.salir.Click += new System.EventHandler(this.salir_Click);
            // 
            // sacar_dinero
            // 
            this.sacar_dinero.Dock = System.Windows.Forms.DockStyle.Top;
            this.sacar_dinero.Location = new System.Drawing.Point(0, 0);
            this.sacar_dinero.Name = "sacar_dinero";
            this.sacar_dinero.Size = new System.Drawing.Size(112, 88);
            this.sacar_dinero.TabIndex = 0;
            this.sacar_dinero.Text = "SD";
            this.toolTip1.SetToolTip(this.sacar_dinero, "Sacar Dinero");
            this.sacar_dinero.UseVisualStyleBackColor = true;
            this.sacar_dinero.Click += new System.EventHandler(this.sacar_dinero_Click_1);
            // 
            // panel_t_izq
            // 
            this.panel_t_izq.Controls.Add(this.consult_saldo);
            this.panel_t_izq.Dock = System.Windows.Forms.DockStyle.Left;
            this.panel_t_izq.Location = new System.Drawing.Point(0, 0);
            this.panel_t_izq.Name = "panel_t_izq";
            this.panel_t_izq.Size = new System.Drawing.Size(104, 269);
            this.panel_t_izq.TabIndex = 0;
            // 
            // consult_saldo
            // 
            this.consult_saldo.Dock = System.Windows.Forms.DockStyle.Top;
            this.consult_saldo.Location = new System.Drawing.Point(0, 0);
            this.consult_saldo.Name = "consult_saldo";
            this.consult_saldo.Size = new System.Drawing.Size(104, 88);
            this.consult_saldo.TabIndex = 0;
            this.consult_saldo.Text = "CS";
            this.toolTip1.SetToolTip(this.consult_saldo, "Consultar Saldo");
            this.consult_saldo.UseVisualStyleBackColor = true;
            this.consult_saldo.Click += new System.EventHandler(this.consult_saldo_Click_1);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(881, 547);
            this.Controls.Add(this.background);
            this.Name = "Form1";
            this.Text = "Form1";
            this.background.ResumeLayout(false);
            this.panel_bottom.ResumeLayout(false);
            this.panel_dinero.ResumeLayout(false);
            this.teclado.ResumeLayout(false);
            this.panel_retirar.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.foto_dinero)).EndInit();
            this.panel_tarjeta.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.foto_tarjeta)).EndInit();
            this.panel_informe.ResumeLayout(false);
            this.panel_top.ResumeLayout(false);
            this.panel_t_mid.ResumeLayout(false);
            this.panel_t_mid.PerformLayout();
            this.panel_t_der.ResumeLayout(false);
            this.panel_t_izq.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel background;
        private System.Windows.Forms.Panel panel_bottom;
        private System.Windows.Forms.Panel panel_dinero;
        private System.Windows.Forms.Panel panel_informe;
        private System.Windows.Forms.Panel panel_top;
        private System.Windows.Forms.TableLayoutPanel teclado;
        private System.Windows.Forms.Button btn_enter;
        private System.Windows.Forms.Button btn_0;
        private System.Windows.Forms.Button btn_1;
        private System.Windows.Forms.Button btn_2;
        private System.Windows.Forms.Button btn_3;
        private System.Windows.Forms.Button btn_4;
        private System.Windows.Forms.Button btn_5;
        private System.Windows.Forms.Button btn_6;
        private System.Windows.Forms.Button btn_7;
        private System.Windows.Forms.Button btn_8;
        private System.Windows.Forms.Button btn_9;
        private System.Windows.Forms.Panel panel_retirar;
        private System.Windows.Forms.Panel panel_tarjeta;
        private System.Windows.Forms.PictureBox foto_dinero;
        private System.Windows.Forms.PictureBox foto_tarjeta;
        private System.Windows.Forms.Button btn_tarjeta;
        private System.Windows.Forms.Button btn_Informe;
        private System.Windows.Forms.Panel panel_t_mid;
        private System.Windows.Forms.Label label_texto;
        private System.Windows.Forms.Panel panel_t_der;
        private System.Windows.Forms.Button salir;
        private System.Windows.Forms.Button sacar_dinero;
        private System.Windows.Forms.Panel panel_t_izq;
        private System.Windows.Forms.Button consult_saldo;
        private System.Windows.Forms.ToolTip toolTip1;
        private System.Windows.Forms.Button btn__8;
        private System.Windows.Forms.Button btn_dinero;
    }
}

