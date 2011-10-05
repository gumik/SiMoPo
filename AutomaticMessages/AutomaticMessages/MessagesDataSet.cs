﻿namespace AutomaticMessages {
    using System.Linq;
    using System;    
    
    public partial class MessagesDataSet {

        public partial class MessagesDataTable
        {
            public void AddMessagesRow()
            {
                var messageName = "new message";

                while (this.Where((row) => row.Name == messageName).Count() > 0)
                {
                    messageName = String.Format("new message {0}", ++newMessageCounter);
                }

                AddMessagesRow(messageName, "");
            }

            private int newMessageCounter;
        }
    }
}
