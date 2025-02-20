namespace ExamenInterfaces
{
    partial class informe
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
            this.reset = new System.Windows.Forms.Button();
            this.Cerrar = new System.Windows.Forms.Button();
            this.tablaInforme = new System.Windows.Forms.DataGridView();
            this.panel1 = new System.Windows.Forms.Panel();
            ((System.ComponentModel.ISupportInitialize)(this.tablaInforme)).BeginInit();
            this.panel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // reset
            // 
            this.reset.Location = new System.Drawing.Point(540, 348);
            this.reset.Name = "reset";
            this.reset.Size = new System.Drawing.Size(102, 35);
            this.reset.TabIndex = 0;
            this.reset.Text = "Inicializar";
            this.reset.UseVisualStyleBackColor = true;
            this.reset.Click += new System.EventHandler(this.reset_Click);
            // 
            // Cerrar
            // 
            this.Cerrar.Location = new System.Drawing.Point(662, 349);
            this.Cerrar.Name = "Cerrar";
            this.Cerrar.Size = new System.Drawing.Size(103, 33);
            this.Cerrar.TabIndex = 1;
            this.Cerrar.Text = "Cerrar";
            this.Cerrar.UseVisualStyleBackColor = true;
            this.Cerrar.Click += new System.EventHandler(this.Cerrar_Click);
            // 
            // tablaInforme
            // 
            this.tablaInforme.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.tablaInforme.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tablaInforme.Location = new System.Drawing.Point(0, 0);
            this.tablaInforme.Name = "tablaInforme";
            this.tablaInforme.RowHeadersWidth = 62;
            this.tablaInforme.RowTemplate.Height = 28;
            this.tablaInforme.Size = new System.Drawing.Size(800, 342);
            this.tablaInforme.TabIndex = 2;
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.tablaInforme);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Top;
            this.panel1.Location = new System.Drawing.Point(0, 0);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(800, 342);
            this.panel1.TabIndex = 3;
            // 
            // informe
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.Cerrar);
            this.Controls.Add(this.reset);
            this.Name = "informe";
            this.Text = "informe";
            ((System.ComponentModel.ISupportInitialize)(this.tablaInforme)).EndInit();
            this.panel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button reset;
        private System.Windows.Forms.Button Cerrar;
        private System.Windows.Forms.DataGridView tablaInforme;
        private System.Windows.Forms.Panel panel1;
    }
}