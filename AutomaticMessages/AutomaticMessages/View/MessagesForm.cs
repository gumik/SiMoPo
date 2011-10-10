using System;
using System.Linq;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using AutomaticMessages.Data;

namespace AutomaticMessages
{
    public partial class MessagesForm : Form
    {
        public MessagesForm()
        {
            InitializeComponent();
        }

        private void EditRow(MessagesDataSet.MessagesRow current)
        {
            var messageForm = new MessageForm()
            {
                MessageName = current.Name,
                MessageText = current.Text,
            };

            messageForm.Closing += (sender, args) => {
                if (messageForm.DialogResult != DialogResult.OK)
                {
                    return;
                }

                try
                {
                    current.Name = messageForm.MessageName;
                    current.Text = messageForm.MessageText;
                    CommitChanges();
                }                
                catch (Exception e)
                {
                    MessageBox.Show(String.Format("Error {0}", e.GetType()), "Error", 
                        MessageBoxButtons.OK, MessageBoxIcon.Hand, 
                        MessageBoxDefaultButton.Button1);
                    args.Cancel = true;
                }
            };

            messageForm.ShowDialog();
        }

        private void CommitChanges()
        {
            var changes = messagesDataSet.GetChanges();
            if (changes != null)
            {
                messagesTableAdapter.Update(changes as MessagesDataSet);  
                messagesDataSet.Merge(changes);
                messagesDataSet.AcceptChanges();
                messagesTableAdapter.Fill(messagesDataSet.Messages);
            }
        }

        private void MessagesForm_Load(object sender, EventArgs e)
        {
            // TODO: This line of code loads data into the 'messagesDataSet.Messages' table. You can move, or remove it, as needed.
            this.messagesTableAdapter.Fill(this.messagesDataSet.Messages);
        }

        private void addMenuItem_Click(object sender, EventArgs e)
        {
            var addedRow = messagesDataSet.Messages.AddMessagesRow();
            EditRow(addedRow);
        }

        private void okMenuItem_Click(object sender, EventArgs e)
        {
            DialogResult = DialogResult.OK;
        }

        private void cancelMenuItem_Click(object sender, EventArgs e)
        {
            DialogResult = DialogResult.Cancel;
        }

        private void editMenuItem_Click(object sender, EventArgs e)
        {
            var dataRowView = messagesBindingSource.Current as DataRowView;
            var current = dataRowView.Row as MessagesDataSet.MessagesRow;
            EditRow(current);
        }

        private void deleteMenuItem_Click(object sender, EventArgs e)
        {
            (messagesBindingSource.Current as DataRowView).Delete();            
            CommitChanges();
        }
    }
}