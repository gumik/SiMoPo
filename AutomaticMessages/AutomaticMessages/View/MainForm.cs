using System;
using System.Linq;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using AutomaticMessages.View;

namespace AutomaticMessages
{
    public partial class MainForm : Form
    {
        public MainForm()
        {
            InitializeComponent();
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
    }
}