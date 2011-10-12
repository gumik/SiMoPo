using System;
using System.Linq;
using System.Collections.Generic;
using System.Text;
using AutomaticMessages.Data;
using AutomaticMessages.Data.LogDataSetTableAdapters;
using System.Windows.Forms;
using System.Data;

namespace AutomaticMessages.Model
{
    public class Logger
    {
        public Logger()
        {
            logDataSet = new LogDataSet();
            logDataSet.BeginInit();
            logBindingSource = new BindingSource();
            logBindingSource.DataSource = logDataSet.Log;
            logTableAdapter = new LogTableAdapter();
            logTableAdapter.Fill(logDataSet.Log);
            logDataSet.EndInit();
        }

        public LogDataSet LogDataSet { get { return logDataSet; } }

        public void AddLog(DateTime dateTime, string number, string text, bool success)
        {
            logDataSet.Log.AddLogRow(dateTime, number, text, success);
            CommitChanges();
        }

        public void ClearAll()
        {
            foreach (var row in logBindingSource.List)
            {
                var rowView = row as DataRowView;
                rowView.Delete();
            }

            CommitChanges();
        }

        private void CommitChanges()
        {
            var changes = logDataSet.GetChanges();            
            if (changes != null)
            {
                logTableAdapter.Update(changes as LogDataSet);
                logDataSet.Merge(changes);
                logDataSet.AcceptChanges();
                logTableAdapter.Fill(logDataSet.Log);
            }
        }

        private LogDataSet logDataSet;
        private LogTableAdapter logTableAdapter;
        private BindingSource logBindingSource;
    }
}
