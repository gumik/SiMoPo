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
    public partial class MessagesForm : Form
    {
        public MessagesForm()
        {
            InitializeComponent();
        }

        protected override void OnClosed(EventArgs e)
        {
            base.OnClosed(e);

            if (DialogResult != DialogResult.OK)
            {
                return;
            }

            var changes = messagesDataSet.GetChanges();
            if (changes != null)
            {
                messagesTableAdapter.Update(changes as MessagesDataSet);
                messagesDataSet.Merge(changes);
                messagesDataSet.AcceptChanges();
            }
        }

        private static void EditRow(MessagesDataSet.MessagesRow current)
        {
            var messageForm = new MessageForm()
            {
                MessageName = current.Name,
                MessageText = current.Text,
            };
            var result = messageForm.ShowDialog();

            if (result == DialogResult.OK)
            {
                current.Name = messageForm.MessageName;
                current.Text = messageForm.MessageText;
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
            (messagesBindingSource.Current as DataRowView).Row.Delete();
        }
    }
}