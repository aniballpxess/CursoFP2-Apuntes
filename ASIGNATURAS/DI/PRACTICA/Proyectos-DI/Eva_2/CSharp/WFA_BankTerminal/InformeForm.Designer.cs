namespace WFA_BankTerminal
{
    partial class InformeForm
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
            this.btn_cerrar = new System.Windows.Forms.Button();
            this.btn_inicializar = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // btn_cerrar
            // 
            this.btn_cerrar.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_cerrar.Location = new System.Drawing.Point(503, 395);
            this.btn_cerrar.Name = "btn_cerrar";
            this.btn_cerrar.Size = new System.Drawing.Size(128, 43);
            this.btn_cerrar.TabIndex = 21;
            this.btn_cerrar.Text = "Cerrar";
            this.btn_cerrar.UseVisualStyleBackColor = true;
            this.btn_cerrar.Click += new System.EventHandler(this.btn_cerrar_Click);
            // 
            // btn_inicializar
            // 
            this.btn_inicializar.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_inicializar.Location = new System.Drawing.Point(369, 395);
            this.btn_inicializar.Name = "btn_inicializar";
            this.btn_inicializar.Size = new System.Drawing.Size(128, 43);
            this.btn_inicializar.TabIndex = 22;
            this.btn_inicializar.Text = "Inicializar";
            this.btn_inicializar.UseVisualStyleBackColor = true;
            this.btn_inicializar.Click += new System.EventHandler(this.btn_inicializar_Click);
            // 
            // InformeForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(643, 450);
            this.Controls.Add(this.btn_inicializar);
            this.Controls.Add(this.btn_cerrar);
            this.Name = "InformeForm";
            this.Text = "InformeForm";
            this.Load += new System.EventHandler(this.HistorialForm_Load);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button btn_cerrar;
        private System.Windows.Forms.Button btn_inicializar;
    }
}