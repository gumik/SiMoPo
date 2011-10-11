using System;
using System.Linq;
using System.Collections.Generic;
using System.Text;
using AutomaticMessages.Data;
using AutomaticMessages.Data.LogDataSetTableAdapters;

namespace AutomaticMessages.Model
{
    class Logger
    {
        public Logger()
        {
            logDataSet = new LogDataSet();
            logTableAdapter = new LogTableAdapter();
            logTableAdapter.Fill(logDataSet.Log);
        }

        public LogDataSet LogDataSet { get { return logDataSet; } }

        public void AddLog(DateTime dateTime, string number, string text, bool success)
        {
            logDataSet.Log.AddLogRow(dateTime, number, text, success);
            var changes = logDataSet.GetChanges();
            logTableAdapter.Update(changes as LogDataSet);
            logDataSet.Merge(changes);
            logDataSet.AcceptChanges();
        }

        private LogDataSet logDataSet;
        private LogTableAdapter logTableAdapter;
    }
}
