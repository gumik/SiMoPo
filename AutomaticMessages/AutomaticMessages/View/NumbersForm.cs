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
    public partial class NumbersForm : Form
    {
        public NumbersForm()
        {
            InitializeComponent();
        }

        private void NumbersForm_Load(object sender, EventArgs e)
        {
            this.messagesTableAdapter.Fill(this.messagesDataSet.Messages);
            this.numbersTableAdapter.Fill(this.messagesDataSet.Numbers);
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
                numbersTableAdapter.Update(changes as MessagesDataSet);
                //messagesTableAdapter.Update(changes as MessagesDataSet);
                messagesDataSet.Merge(changes);
                messagesDataSet.AcceptChanges();
            }
        }

        private void addMenuItem_Click(object sender, EventArgs e)
        {
            var numberForm = new NumberForm();
            var result = numberForm.ShowDialog();

            if (result == DialogResult.OK)
            {
                messagesDataSet.Numbers.AddNumbersRow(
                    numberForm.Number, numberForm.MessagesRow);
            }
        }

        private void cancelMenuItem_Click(object sender, EventArgs e)
        {
            DialogResult = DialogResult.Cancel;
        }

        private void editContextMenuItem_Click(object sender, EventArgs e)
        {
            var dataRowView = numbersBindingSource.Current as DataRowView;
            var current = dataRowView.Row as MessagesDataSet.NumbersRow;

            var numberForm = new NumberForm() { Number = current.Number, MessageId = current.MessageId };
            var result = numberForm.ShowDialog();

            if (result == DialogResult.OK)
            {
                current.Number = numberForm.Number;
                current.MessageId = numberForm.MessageId;
                UpdateMessageBindingPosition();
            }
        }

        private void deleteContextMenuItem_Click(object sender, EventArgs e)
        {
            (numbersBindingSource.Current as DataRowView).Delete();
        }

        private void numbersListBox_SelectedValueChanged(object sender, EventArgs e)
        {

        }

        private void numbersBindingSource_CurrentChanged(object sender, EventArgs e)
        {
            UpdateMessageBindingPosition();
        }

        private void UpdateMessageBindingPosition()
        {
            var dataRowView = numbersBindingSource.Current as DataRowView;
            var row = dataRowView.Row as MessagesDataSet.NumbersRow;
            var messageId = row.MessageId;

            int i = 0;
            foreach (var message in messagesBindingSource.List.Cast<DataRowView>())
            {
                if ((message.Row as MessagesDataSet.MessagesRow).MessageId == messageId)
                {
                    messagesBindingSource.Position = i;
                    return;
                }
                ++i;
            }
        }
    }
}