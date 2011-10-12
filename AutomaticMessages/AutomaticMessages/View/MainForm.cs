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
using AutomaticMessages.Data;
using AutomaticMessages.Data.LogDataSetTableAdapters;

namespace AutomaticMessages
{
    public partial class MainForm : Form
    {
        public MainForm()
        {
            InitializeComponent();

            incomingParser = new IncomingsParser(true);
            messagesManager = new MessagesManager(incomingParser);
            logger = new Logger();

            messagesManager.SmsSend += new MessagesManager.SmsSendEventHandler(messagesManager_SmsSend);

            logViewControl1.Logger = logger;
        }

        void messagesManager_SmsSend(object sender, SmsSendEventArgs args)
        {
            logger.AddLog(DateTime.Now, args.Number, args.Text, args.Success);
        }

        private void exitMenuItem_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void messagesMenuItem_Click(object sender, EventArgs e)
        {
            new MessagesForm().ShowDialog();
            messagesManager.ReloadConfig();
        }

        private void numbersMenuItem_Click(object sender, EventArgs e)
        {
            new NumbersForm().ShowDialog();
            messagesManager.ReloadConfig();
        }

        private void clearMenuItem_Click(object sender, EventArgs e)
        {
            logger.ClearAll();
        }

        private Logger logger;
        private IncomingsParser incomingParser;
        private MessagesManager messagesManager;


        private bool a = true;
        private void menuItem2_Click(object sender, EventArgs e)
        {
            logger.AddLog(DateTime.Now, "123455", "test", a);
            a = !a;
        }
    }
}