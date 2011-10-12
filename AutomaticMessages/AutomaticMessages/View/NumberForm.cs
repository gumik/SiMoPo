using System;
using System.Linq;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using AutomaticMessages.Data;

namespace AutomaticMessages.View
{
    public partial class NumberForm : Form
    {
        public NumberForm()
        {
            InitializeComponent();
            this.messagesTableAdapter.Fill(this.messagesDataSet.Messages);
        }

        public string Number 
        { 
            get { return numberTextBox.Text; }
            set { numberTextBox.Text = value; }
        }

        public MessagesDataSet.MessagesRow MessagesRow 
        { 
            get 
            {
                var dataRowView = messagesBindingSource.Current as DataRowView;
                if (dataRowView == null)
                {
                    return null;
                }
                return dataRowView.Row as MessagesDataSet.MessagesRow;
            }
        }

        public int MessageId
        {
            get
            {
                var dataRowView = messagesBindingSource.Current as DataRowView;
                return (dataRowView.Row as MessagesDataSet.MessagesRow).MessageId;
            }

            set
            {
                int i = 0;
                foreach (var message in messagesBindingSource.List.Cast<DataRowView>())
                {
                    if ((message.Row as MessagesDataSet.MessagesRow).MessageId == value)
                    {
                        messagesBindingSource.Position = i;
                        return;
                    }
                    ++i;
                }
            }
        }

        private void numberTextBox_KeyDown(object sender, KeyEventArgs e)
        {
            properKey = ((e.KeyCode >= Keys.D0 && e.KeyCode <= Keys.D9) || e.KeyCode == Keys.Back);
        }

        private void numberTextBox_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (!properKey)
            {
                e.Handled = true;
            }
        }

        private void cancelMenuItem_Click(object sender, EventArgs e)
        {
            DialogResult = DialogResult.Cancel;
        }

        private bool properKey;
    }
}