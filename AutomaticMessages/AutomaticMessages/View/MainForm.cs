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
            //mm.SmsError += new MessagesManager.SmsErrorEventHandler(mm_SmsError);
            //mm.SmsSend += new MessagesManager.SmsSendEventHandler(mm_SmsSend);

            incomingParser = new IncomingsParser(true);
            messagesManager = new MessagesManager(incomingParser);
            logger = new Logger();

            dateTime = DateTime.Now;
            //logBindingSource.DataSourceChanged += (sender, args) => MessageBox.Show("changed");
            //logBindingSource.AddingNew += (sender, args) => MessageBox.Show(args.NewObject.ToString());
            //logger.LogDataSet.Log.TableNewRow += (sender, args) => MessageBox.Show(args.Row.GetType().ToString());
            logger.LogDataSet.Log.RowChanged += new DataRowChangeEventHandler(Log_RowChanged);
        }

        void Log_RowChanged(object sender, DataRowChangeEventArgs e)
        {
            if (e.Action == DataRowAction.Add)
            {
                var logRow = e.Row as LogDataSet.LogRow;
                var item = new LogListItem(logRow.DateTime, logRow.Number, logRow.Text, logRow.Success);
                logListView.Items.Add(item);
            }
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

        private Logger logger;
        private IncomingsParser incomingParser;
        private MessagesManager messagesManager;
        private DateTime dateTime;

        private void menuItem2_Click(object sender, EventArgs e)
        {
            logger.AddLog(DateTime.Now, "123455", "test", true);
        }

        class LogListItem : ListViewItem
        {
            public LogListItem(DateTime dateTime, string number, string text, bool success) : base()
            {
                this.SubItems.Add(dateTime.ToString());
                this.SubItems.Add(number);
                this.SubItems.Add(text);
                this.ImageIndex = success ? 1 : 0;
            }
        }
    }
}