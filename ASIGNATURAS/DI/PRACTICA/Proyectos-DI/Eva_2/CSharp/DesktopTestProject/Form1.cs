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
    public partial class MainForm : Form
    {
        private EventLog eventLog;
        private String[] MESSAGES ;
        public MainForm()
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

        private void Btn_ClickMe_Click(object sender, EventArgs e)
        {
            Random random = new Random();
            String selectedMsg;
            int msgPosition;

            msgPosition = random.Next(3);
            selectedMsg = MESSAGES[msgPosition];
            tb_userMessage.Text = selectedMsg;
        }

        private void TB_UserMessage_TextChanged(object sender, EventArgs e)
        {
            eventLog.WriteEntry($"{DateTime.Now} : Texto modificado a '{tb_userMessage.Text}'.");
        }

        private void MainForm_Close(object sender, EventArgs e)
        {
            foreach (EventLogEntry log in eventLog.Entries)
            {
                Console.WriteLine(log.Message);
            }
            eventLog.Close();
        }
    }
}
