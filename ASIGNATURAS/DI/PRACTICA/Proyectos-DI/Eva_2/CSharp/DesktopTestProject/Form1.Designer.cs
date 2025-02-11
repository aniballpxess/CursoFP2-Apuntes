namespace DesktopTestProject
{
    partial class MainForm
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
            this.btn_clickThis = new System.Windows.Forms.Button();
            this.tb_userMessage = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // btn_clickThis
            // 
            this.btn_clickThis.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_clickThis.Location = new System.Drawing.Point(326, 108);
            this.btn_clickThis.Name = "btn_clickThis";
            this.btn_clickThis.Size = new System.Drawing.Size(120, 42);
            this.btn_clickThis.TabIndex = 0;
            this.btn_clickThis.Text = "Click Me!";
            this.btn_clickThis.UseVisualStyleBackColor = true;
            this.btn_clickThis.Click += new System.EventHandler(this.Btn_ClickMe_Click);
            // 
            // tb_userMessage
            // 
            this.tb_userMessage.Location = new System.Drawing.Point(326, 177);
            this.tb_userMessage.Multiline = true;
            this.tb_userMessage.Name = "tb_userMessage";
            this.tb_userMessage.Size = new System.Drawing.Size(120, 71);
            this.tb_userMessage.TabIndex = 1;
            this.tb_userMessage.TextChanged += new System.EventHandler(this.TB_UserMessage_TextChanged);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.tb_userMessage);
            this.Controls.Add(this.btn_clickThis);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btn_clickThis;
        private System.Windows.Forms.TextBox tb_userMessage;
    }
}

