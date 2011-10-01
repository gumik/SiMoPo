using System;
using System.Linq;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using Microsoft.WindowsMobile.Status;

namespace AutomaticMessages
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            incomingParser = new IncomingsParser(true);
            incomingParser.CallMissed += new IncomingsParser.CallEventHandler(incomingParser_CallMissed);
        }

        void incomingParser_CallMissed(object sender, CallEventArgs args)
        {
            listBox1.Items.Add(args.Number);
        }

        IncomingsParser incomingParser;
    }
}