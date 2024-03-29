﻿using System;
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

        private void CommitChanges()
        {
            var changes = messagesDataSet.GetChanges();
            if (changes != null)
            {
                numbersBindingSource.EndEdit();
                //messagesTableAdapter.Update(changes as MessagesDataSet);
                numbersTableAdapter.Update(changes as MessagesDataSet);
                messagesDataSet.Merge(changes);
                messagesDataSet.AcceptChanges();
                //messagesTableAdapter.Fill(messagesDataSet.Messages);
                numbersTableAdapter.Fill(messagesDataSet.Numbers);
            }
        }

        private void ShowEditForm(NumberForm numberForm, Action method)
        {
            numberForm.Closing += (_, args) =>
            {
                if (numberForm.DialogResult != DialogResult.OK)
                {
                    return;
                }

                try
                {
                    method();
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

            numberForm.ShowDialog();
        }

        private MessagesDataSet.NumbersRow GetCurrentRow()
        {
            var dataRowView = numbersBindingSource.Current as DataRowView;

            if (dataRowView == null)
            {
                MessageBox.Show("Please select row.", "Error",
                        MessageBoxButtons.OK, MessageBoxIcon.Hand,
                        MessageBoxDefaultButton.Button1);
                return null;
            }

            var current = dataRowView.Row as MessagesDataSet.NumbersRow;
            return current;
        }

        private void addMenuItem_Click(object sender, EventArgs __)
        {
            var numberForm = new NumberForm();
            ShowEditForm(numberForm, () =>
                messagesDataSet.Numbers.AddNumbersRow(
                    numberForm.Number, numberForm.MessagesRow));
        }

        private void cancelMenuItem_Click(object sender, EventArgs __)
        {
            DialogResult = DialogResult.Cancel;
        }

        private void editMenuItem_Click(object sender, EventArgs __)
        {
            var current = GetCurrentRow();

            if (current == null)
            {
                return;
            }

            var numberForm = new NumberForm() { Number = current.Number, MessageId = current.MessageId };

            ShowEditForm(numberForm, () =>
            {
                current.Number = numberForm.Number;
                current.MessageId = numberForm.MessageId;
            });
        }

        private void deleteMenuItem_Click(object sender, EventArgs e)
        {
            var current = GetCurrentRow();
            if (current != null)
            {
                current.Delete();
                CommitChanges();
            }
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