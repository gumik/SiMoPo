using System;
using System.Linq;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Text;
using System.Windows.Forms;
using AutomaticMessages.Model;
using AutomaticMessages.Data;
using System.Threading;

namespace AutomaticMessages.View
{
    public partial class LogViewControl : UserControl
    {
        public LogViewControl()
        {
            InitializeComponent();
            logItemDictionary = new Dictionary<int, LogListItem>();
        }

        public Logger Logger 
        {
            get { return logger; }
            set { SetLogger(value); }
        }

        public bool FillWithData { get; set; }

        private void AddRow(LogDataSet.LogRow logRow)
        {
            Monitor.Enter(logItemDictionary);

            if (logRow.LogId > 0 && !logItemDictionary.ContainsKey(logRow.LogId))
            {
                var item = new LogListItem(logRow.LogId, logRow.DateTime, logRow.Number, logRow.Text, logRow.Success);
                logListView.Items.Add(item);
                logItemDictionary[logRow.LogId] = item;
            }

            Monitor.Exit(logItemDictionary);
        }

        private void SetLogger(Logger value)
        {
            if (logger != null)
            {
                logger.LogDataSet.Log.RowChanged -= Log_RowChanged;
            }
            logger = value;
            logger.LogDataSet.Log.RowChanged += Log_RowChanged;
            logger.LogDataSet.Log.RowDeleting += Log_RowDeleted;

            if (FillWithData)
            {
                foreach (var row in logger.LogDataSet.Log.Rows.Cast<LogDataSet.LogRow>())
                {
                    AddRow(row);
                }
            }
        }

        private void Log_RowChanged(object sender, DataRowChangeEventArgs e)
        {
            if (e.Action == DataRowAction.Add)
            {
                var row = e.Row as LogDataSet.LogRow;
                AddRow(row);
            }
        }

        void Log_RowDeleted(object sender, DataRowChangeEventArgs e)
        {
            if (e.Action == DataRowAction.Delete)
            {
                var logId = (e.Row as LogDataSet.LogRow).LogId;
                LogListItem logListItem;
                
                if (logItemDictionary.TryGetValue(logId, out logListItem))
                {
                    logListView.Items.Remove(logListItem);
                    logItemDictionary.Remove(logId);
                }
            }
        }

        private Logger logger;
        private Dictionary<int, LogListItem> logItemDictionary;

        private class LogListItem : ListViewItem
        {
            public LogListItem(int id, DateTime dateTime, string number, string text, bool success)
                : base()
            {
                this.Id = id;
                this.SubItems.Add(number);
                this.SubItems.Add(text);
                this.SubItems.Add(dateTime.ToString());
                this.ImageIndex = success ? 1 : 0;
            }

            public int Id { get; private set; }
        }
    }
}
