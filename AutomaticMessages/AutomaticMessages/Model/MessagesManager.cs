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

            var numbersTableAdapter = new NumbersTableAdapter();
            numbersTableAdapter.Fill(messagesDataSet.Numbers);
            var messagesTableAdapter = new MessagesTableAdapter();
            messagesTableAdapter.Fill(messagesDataSet.Messages);
        }

        public delegate void SmsErrorEventHandler(object sender, SmsErrorEventArgs args);
        public event SmsErrorEventHandler SmsError = delegate { };

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
            catch (Exception e)
            {
                SmsError(this, new SmsErrorEventArgs(String.Format("Brak tekstu dla numeru: {0}", args.Number)));
                return;
            }

            var smsMessage = new SmsMessage(args.Number, text);

            try
            {
                smsMessage.Send();
                SmsSend(this, new SmsSendEventArgs(args.Number, text));
            }
            catch (Exception e)
            {
                SmsError(this, new SmsErrorEventArgs(String.Format("Błąd wysyłania wiadomości do: {0}", args.Number)));
            }
        }

        private IncomingsParser incomingParser;
        private MessagesDataSet messagesDataSet;
    }

    public class SmsErrorEventArgs : EventArgs
    {
        public SmsErrorEventArgs(string message)
        {
            Message = message;
        }

        public string Message { get; private set; }
    }

    public class SmsSendEventArgs : EventArgs
    {
        public SmsSendEventArgs(string number, string text)
        {
            Number = number;
            Text = text;
        }

        public string Number { get; private set; }
        public string Text { get; private set; }
    }
}
