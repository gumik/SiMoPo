using System;
using System.Linq;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using AutomaticMessages.View;
using AutomaticMessages.Model;

namespace AutomaticMessages
{
    public partial class MainForm : Form
    {
        public MainForm()
        {
            InitializeComponent();
            mm.SmsError += new MessagesManager.SmsErrorEventHandler(mm_SmsError);
            mm.SmsSend += new MessagesManager.SmsSendEventHandler(mm_SmsSend);
        }

        void mm_SmsSend(object sender, SmsSendEventArgs args)
        {
            AppendToTestTextBox(String.Format("{0} -> {1}", args.Number, args.Text));
        }        

        void mm_SmsError(object sender, SmsErrorEventArgs args)
        {
            AppendToTestTextBox(args.Message);
        }

        private void AppendToTestTextBox(string text)
        {
            //this.Invoke(new Action(() => {
            testTextBox.Text = String.Concat(testTextBox.Text, text, Environment.NewLine);
            //}));
        }

        private void exitMenuItem_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void messagesMenuItem_Click(object sender, EventArgs e)
        {
            new MessagesForm().ShowDialog();
        }

        private void numbersMenuItem_Click(object sender, EventArgs e)
        {
            new NumbersForm().ShowDialog();
        }

        MessagesManager mm = new MessagesManager(new IncomingsParser(true));
    }
}