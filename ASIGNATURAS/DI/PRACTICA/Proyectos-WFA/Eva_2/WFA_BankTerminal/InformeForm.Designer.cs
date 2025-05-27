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
            this.components = new System.ComponentModel.Container();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle1 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle2 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle3 = new System.Windows.Forms.DataGridViewCellStyle();
            this.btn_cerrar = new System.Windows.Forms.Button();
            this.btn_inicializar = new System.Windows.Forms.Button();
            this.dgv_operaciones = new System.Windows.Forms.DataGridView();
            this.informeFormBindingSource = new System.Windows.Forms.BindingSource(this.components);
            ((System.ComponentModel.ISupportInitialize)(this.dgv_operaciones)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.informeFormBindingSource)).BeginInit();
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
            // dgv_operaciones
            // 
            this.dgv_operaciones.AllowUserToAddRows = false;
            this.dgv_operaciones.AllowUserToDeleteRows = false;
            this.dgv_operaciones.AllowUserToResizeColumns = false;
            this.dgv_operaciones.AllowUserToResizeRows = false;
            this.dgv_operaciones.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.dgv_operaciones.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.AllCells;
            this.dgv_operaciones.AutoSizeRowsMode = System.Windows.Forms.DataGridViewAutoSizeRowsMode.AllCells;
            dataGridViewCellStyle1.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleCenter;
            dataGridViewCellStyle1.BackColor = System.Drawing.SystemColors.Control;
            dataGridViewCellStyle1.Font = new System.Drawing.Font("Consolas", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            dataGridViewCellStyle1.ForeColor = System.Drawing.SystemColors.WindowText;
            dataGridViewCellStyle1.SelectionBackColor = System.Drawing.SystemColors.Highlight;
            dataGridViewCellStyle1.SelectionForeColor = System.Drawing.SystemColors.HighlightText;
            dataGridViewCellStyle1.WrapMode = System.Windows.Forms.DataGridViewTriState.True;
            this.dgv_operaciones.ColumnHeadersDefaultCellStyle = dataGridViewCellStyle1;
            this.dgv_operaciones.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridViewCellStyle2.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleCenter;
            dataGridViewCellStyle2.BackColor = System.Drawing.SystemColors.Window;
            dataGridViewCellStyle2.Font = new System.Drawing.Font("Consolas", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            dataGridViewCellStyle2.ForeColor = System.Drawing.SystemColors.ControlText;
            dataGridViewCellStyle2.SelectionBackColor = System.Drawing.SystemColors.Highlight;
            dataGridViewCellStyle2.SelectionForeColor = System.Drawing.SystemColors.HighlightText;
            dataGridViewCellStyle2.WrapMode = System.Windows.Forms.DataGridViewTriState.False;
            this.dgv_operaciones.DefaultCellStyle = dataGridViewCellStyle2;
            this.dgv_operaciones.Location = new System.Drawing.Point(12, 12);
            this.dgv_operaciones.MaximumSize = new System.Drawing.Size(0, 0);
            this.dgv_operaciones.Name = "dgv_operaciones";
            this.dgv_operaciones.ReadOnly = true;
            dataGridViewCellStyle3.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleCenter;
            dataGridViewCellStyle3.BackColor = System.Drawing.SystemColors.Control;
            dataGridViewCellStyle3.Font = new System.Drawing.Font("Consolas", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            dataGridViewCellStyle3.ForeColor = System.Drawing.SystemColors.WindowText;
            dataGridViewCellStyle3.SelectionBackColor = System.Drawing.SystemColors.Highlight;
            dataGridViewCellStyle3.SelectionForeColor = System.Drawing.SystemColors.HighlightText;
            dataGridViewCellStyle3.WrapMode = System.Windows.Forms.DataGridViewTriState.True;
            this.dgv_operaciones.RowHeadersDefaultCellStyle = dataGridViewCellStyle3;
            this.dgv_operaciones.RowHeadersVisible = false;
            this.dgv_operaciones.Size = new System.Drawing.Size(619, 377);
            this.dgv_operaciones.TabIndex = 23;
            // 
            // informeFormBindingSource
            // 
            this.informeFormBindingSource.DataSource = typeof(WFA_BankTerminal.InformeForm);
            // 
            // InformeForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(643, 450);
            this.Controls.Add(this.dgv_operaciones);
            this.Controls.Add(this.btn_inicializar);
            this.Controls.Add(this.btn_cerrar);
            this.Name = "InformeForm";
            this.Text = "InformeForm";
            ((System.ComponentModel.ISupportInitialize)(this.dgv_operaciones)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.informeFormBindingSource)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button btn_cerrar;
        private System.Windows.Forms.Button btn_inicializar;
        private System.Windows.Forms.DataGridView dgv_operaciones;
        private System.Windows.Forms.BindingSource informeFormBindingSource;
    }
}