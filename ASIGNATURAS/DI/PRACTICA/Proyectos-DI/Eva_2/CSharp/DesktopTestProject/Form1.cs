using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace DesktopTestProject
{
    public partial class Form1 : Form
    {
        private EventLog eventLog;
        private String[] MESSAGES ;
        public Form1()
        {
            InitializeComponent();
            MESSAGES = new String[] {
                "¡Hola primo!",
                "¿Que hay de nuevo viejo?",
                "¡Comeme los huevos, gilipollas!",
                "Pero, ¿tu eres retrasado? ¿O qué?"
            };
            eventLog = new EventLog();
        }

        private void btnClickMe_Click(object sender, EventArgs e)
        {
            Random random = new Random();
            String selectedMsg;
            int msgPosition;

            msgPosition = random.Next(3);
            selectedMsg = MESSAGES[msgPosition];
            tb_userMessage.Text = selectedMsg;
        }

        private void tbUserMessage_TextChanged(object sender, EventArgs e)
        {
            eventLog.WriteEntry($"{DateTime.Now} : Texto modificado a '{tb_userMessage.Text}'.");
        }
    }
}
