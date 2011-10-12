using System;
using System.Linq;
using System.Collections.Generic;
using System.Text;
using Microsoft.WindowsMobile.PocketOutlook;
using AutomaticMessages.Data;
using AutomaticMessages.Data.MessagesDataSetTableAdapters;

namespace AutomaticMessages.Model
{
    class MessagesManager
    {
        public MessagesManager(IncomingsParser incomingParser)
        {
            this.incomingParser = incomingParser;
            incomingParser.CallMissed += new IncomingsParser.CallEventHandler(incomingParser_CallMissed);

            messagesDataSet = new MessagesDataSet();
            messagesDataSet.BeginInit();
            messagesDataSet.DataSetName = "MessagesDataSet";
            messagesDataSet.Prefix = "";
            messagesDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            messagesDataSet.EndInit();

            numbersTableAdapter = new NumbersTableAdapter();
            numbersTableAdapter.Fill(messagesDataSet.Numbers);
            messagesTableAdapter = new MessagesTableAdapter();
            messagesTableAdapter.Fill(messagesDataSet.Messages);
        }

        public void ReloadConfig()
        {
            numbersTableAdapter.Fill(messagesDataSet.Numbers);
            messagesTableAdapter.Fill(messagesDataSet.Messages);
        }

        public delegate void SmsSendEventHandler(object sender, SmsSendEventArgs args);
        public event SmsSendEventHandler SmsSend = delegate { };

        void incomingParser_CallMissed(object sender, CallEventArgs args)
        {
            var results = from n in messagesDataSet.Numbers
                          join m in messagesDataSet.Messages on n.MessageId equals m.MessageId
                          where n.Number == args.Number
                          select m.Text;

            if (results == null)
            {
                return;
            }

            string text;
            try
            {
                text = results.First();
            }
            catch
            {
                return;
            }

            var smsMessage = new SmsMessage(args.Number, text);

            try
            {
                smsMessage.Send();
                SmsSend(this, new SmsSendEventArgs(args.Number, text, true));
            }
            catch (Exception e)
            {
                SmsSend(this, new SmsSendEventArgs(args.Number, text, false));
            }
        }

        private IncomingsParser incomingParser;
        private MessagesDataSet messagesDataSet;
        private MessagesTableAdapter messagesTableAdapter;
        private NumbersTableAdapter numbersTableAdapter;
    }

    public class SmsSendEventArgs : EventArgs
    {
        public SmsSendEventArgs(string number, string text, bool success)
        {
            Number = number;
            Text = text;
            Success = success;
        }

        public string Number { get; private set; }
        public string Text { get; private set; }
        public bool Success { get; private set; }
    }
}
