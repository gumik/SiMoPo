using System;
using System.Linq;
using System.Collections.Generic;
using System.Text;
using Microsoft.WindowsMobile.Status;
using System.Threading;

namespace AutomaticMessages
{
    class IncomingsParser
    {
        public IncomingsParser(bool useFormThread)
        {
            incomingNumberState = new SystemState(SystemProperty.PhoneIncomingCallerNumber, useFormThread);
            missedCalsState = new SystemState(SystemProperty.PhoneMissedCalls, useFormThread);
            missedCalls = SystemState.PhoneMissedCalls;

            incomingNumberState.Changed += IncomingNumberStateChanged;
            missedCalsState.Changed += new ChangeEventHandler(missedCalsState_Changed);
        }

        void missedCalsState_Changed(object sender, ChangeEventArgs args)
        {
            if (args == null && args.NewValue == null)
            {
                return;
            }

            var newMissedCalls = (int)args.NewValue;

            if (newMissedCalls > missedCalls)
            {
                CallMissed(this, new CallEventArgs(number));
            }

            missedCalls = newMissedCalls;
        }
        
        public delegate void CallEventHandler(object sender, CallEventArgs args);
        public event CallEventHandler CallMissed = delegate { };

        private void IncomingNumberStateChanged(object sender, ChangeEventArgs args)
        {
            if (args != null && args.NewValue != null)
            {
                number = args.NewValue as string;
            }
        }

        private SystemState incomingNumberState;
        private SystemState missedCalsState;
        private string number;
        private int missedCalls;
    }

    class CallEventArgs : EventArgs
    {
        public CallEventArgs(string number)
        {
            Number = number;
        }

        public String Number { get; private set; }
    }
}
