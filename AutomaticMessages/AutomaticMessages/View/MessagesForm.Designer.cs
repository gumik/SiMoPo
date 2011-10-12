namespace AutomaticMessages
{
    partial class MessagesForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;
        private System.Windows.Forms.MainMenu mainMenu1;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.mainMenu1 = new System.Windows.Forms.MainMenu();
            this.addMenuItem = new System.Windows.Forms.MenuItem();
            this.menuMenuItem = new System.Windows.Forms.MenuItem();
            this.messagesBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.messagesDataSet = new AutomaticMessages.Data.MessagesDataSet();
            this.namesListBox = new System.Windows.Forms.ListBox();
            this.messageTextBox = new System.Windows.Forms.TextBox();
            this.messagesTableAdapter = new AutomaticMessages.Data.MessagesDataSetTableAdapters.MessagesTableAdapter();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.menuItem1 = new System.Windows.Forms.MenuItem();
            this.menuItem2 = new System.Windows.Forms.MenuItem();
            ((System.ComponentModel.ISupportInitialize)(this.messagesBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.messagesDataSet)).BeginInit();
            this.SuspendLayout();
            // 
            // mainMenu1
            // 
            this.mainMenu1.MenuItems.Add(this.addMenuItem);
            this.mainMenu1.MenuItems.Add(this.menuMenuItem);
            // 
            // addMenuItem
            // 
            this.addMenuItem.Text = "Add";
            this.addMenuItem.Click += new System.EventHandler(this.addMenuItem_Click);
            // 
            // menuMenuItem
            // 
            this.menuMenuItem.MenuItems.Add(this.menuItem1);
            this.menuMenuItem.MenuItems.Add(this.menuItem2);
            this.menuMenuItem.Text = "Menu";
            // 
            // messagesBindingSource
            // 
            this.messagesBindingSource.DataMember = "Messages";
            this.messagesBindingSource.DataSource = this.messagesDataSet;
            // 
            // messagesDataSet
            // 
            this.messagesDataSet.DataSetName = "MessagesDataSet";
            this.messagesDataSet.Prefix = "";
            this.messagesDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // namesListBox
            // 
            this.namesListBox.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.namesListBox.DataSource = this.messagesBindingSource;
            this.namesListBox.DisplayMember = "Name";
            this.namesListBox.Location = new System.Drawing.Point(3, 23);
            this.namesListBox.Name = "namesListBox";
            this.namesListBox.Size = new System.Drawing.Size(234, 72);
            this.namesListBox.TabIndex = 0;
            this.namesListBox.ValueMember = "Text";
            // 
            // messageTextBox
            // 
            this.messageTextBox.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.messageTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.messagesBindingSource, "Text", true));
            this.messageTextBox.Location = new System.Drawing.Point(3, 121);
            this.messageTextBox.Multiline = true;
            this.messageTextBox.Name = "messageTextBox";
            this.messageTextBox.ReadOnly = true;
            this.messageTextBox.Size = new System.Drawing.Size(234, 144);
            this.messageTextBox.TabIndex = 1;
            // 
            // messagesTableAdapter
            // 
            this.messagesTableAdapter.ClearBeforeFill = true;
            // 
            // label1
            // 
            this.label1.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.label1.Location = new System.Drawing.Point(3, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(234, 20);
            this.label1.Text = "Messages:";
            // 
            // label2
            // 
            this.label2.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.label2.Location = new System.Drawing.Point(3, 98);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(234, 20);
            this.label2.Text = "Message text:";
            // 
            // menuItem1
            // 
            this.menuItem1.Text = "Edit";
            this.menuItem1.Click += new System.EventHandler(this.editMenuItem_Click);
            // 
            // menuItem2
            // 
            this.menuItem2.Text = "Delete";
            this.menuItem2.Click += new System.EventHandler(this.deleteMenuItem_Click);
            // 
            // MessagesForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(96F, 96F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi;
            this.AutoScroll = true;
            this.ClientSize = new System.Drawing.Size(240, 268);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.messageTextBox);
            this.Controls.Add(this.namesListBox);
            this.Menu = this.mainMenu1;
            this.Name = "MessagesForm";
            this.Text = "MessagesForm";
            this.Load += new System.EventHandler(this.MessagesForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.messagesBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.messagesDataSet)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ListBox namesListBox;
        private System.Windows.Forms.TextBox messageTextBox;
        private Data.MessagesDataSet messagesDataSet;
        private System.Windows.Forms.BindingSource messagesBindingSource;
        private AutomaticMessages.Data.MessagesDataSetTableAdapters.MessagesTableAdapter messagesTableAdapter;
        private System.Windows.Forms.MenuItem addMenuItem;
        private System.Windows.Forms.MenuItem menuMenuItem;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.MenuItem menuItem1;
        private System.Windows.Forms.MenuItem menuItem2;
    }
}