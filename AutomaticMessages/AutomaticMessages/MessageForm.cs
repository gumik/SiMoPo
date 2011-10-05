using System;
using System.Linq;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace AutomaticMessages
{
    public partial class MessageForm : Form
    {
        public MessageForm()
        {
            InitializeComponent();
        }

        public string MessageName
        {
            get { return nameTextBox.Text; }
            set { nameTextBox.Text = value; }
        }

        public string MessageText
        {
            get { return messageTextBox.Text; }
            set { messageTextBox.Text = value; }
        }
    }
}