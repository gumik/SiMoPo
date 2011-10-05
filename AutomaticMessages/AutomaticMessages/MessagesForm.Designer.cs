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
            this.menuItem = new System.Windows.Forms.MenuItem();
            this.okMenuItem = new System.Windows.Forms.MenuItem();
            this.cancelMenuItem = new System.Windows.Forms.MenuItem();
            this.messagesBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.messagesDataSet = new AutomaticMessages.MessagesDataSet();
            this.namesListBox = new System.Windows.Forms.ListBox();
            this.messageTextBox = new System.Windows.Forms.TextBox();
            this.messagesTableAdapter = new AutomaticMessages.MessagesDataSetTableAdapters.MessagesTableAdapter();
            this.contextMenu = new System.Windows.Forms.ContextMenu();
            this.menuItem1 = new System.Windows.Forms.MenuItem();
            this.editMenuItem = new System.Windows.Forms.MenuItem();
            ((System.ComponentModel.ISupportInitialize)(this.messagesBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.messagesDataSet)).BeginInit();
            this.SuspendLayout();
            // 
            // mainMenu1
            // 
            this.mainMenu1.MenuItems.Add(this.addMenuItem);
            this.mainMenu1.MenuItems.Add(this.menuItem);
            // 
            // addMenuItem
            // 
            this.addMenuItem.Text = "Add";
            this.addMenuItem.Click += new System.EventHandler(this.addMenuItem_Click);
            // 
            // menuItem
            // 
            this.menuItem.MenuItems.Add(this.okMenuItem);
            this.menuItem.MenuItems.Add(this.cancelMenuItem);
            this.menuItem.Text = "Menu";
            // 
            // okMenuItem
            // 
            this.okMenuItem.Text = "OK";
            this.okMenuItem.Click += new System.EventHandler(this.okMenuItem_Click);
            // 
            // cancelMenuItem
            // 
            this.cancelMenuItem.Text = "Cancel";
            this.cancelMenuItem.Click += new System.EventHandler(this.cancelMenuItem_Click);
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
            this.namesListBox.ContextMenu = this.contextMenu;
            this.namesListBox.DataSource = this.messagesBindingSource;
            this.namesListBox.DisplayMember = "Name";
            this.namesListBox.Location = new System.Drawing.Point(3, 3);
            this.namesListBox.Name = "namesListBox";
            this.namesListBox.Size = new System.Drawing.Size(234, 100);
            this.namesListBox.TabIndex = 0;
            this.namesListBox.ValueMember = "Text";
            // 
            // messageTextBox
            // 
            this.messageTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.messagesBindingSource, "Text", true));
            this.messageTextBox.Location = new System.Drawing.Point(3, 109);
            this.messageTextBox.Multiline = true;
            this.messageTextBox.Name = "messageTextBox";
            this.messageTextBox.ReadOnly = true;
            this.messageTextBox.Size = new System.Drawing.Size(234, 156);
            this.messageTextBox.TabIndex = 1;
            // 
            // messagesTableAdapter
            // 
            this.messagesTableAdapter.ClearBeforeFill = true;
            // 
            // contextMenu
            // 
            this.contextMenu.MenuItems.Add(this.menuItem1);
            this.contextMenu.MenuItems.Add(this.editMenuItem);
            // 
            // menuItem1
            // 
            this.menuItem1.Text = "Add";
            this.menuItem1.Click += new System.EventHandler(this.menuItem1_Click);
            // 
            // editMenuItem
            // 
            this.editMenuItem.Text = "Edit";
            this.editMenuItem.Click += new System.EventHandler(this.editMenuItem_Click);
            // 
            // MessagesForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(96F, 96F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi;
            this.AutoScroll = true;
            this.ClientSize = new System.Drawing.Size(240, 268);
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
        private MessagesDataSet messagesDataSet;
        private System.Windows.Forms.BindingSource messagesBindingSource;
        private AutomaticMessages.MessagesDataSetTableAdapters.MessagesTableAdapter messagesTableAdapter;
        private System.Windows.Forms.MenuItem okMenuItem;
        private System.Windows.Forms.MenuItem menuItem;
        private System.Windows.Forms.MenuItem addMenuItem;
        private System.Windows.Forms.MenuItem cancelMenuItem;
        private System.Windows.Forms.ContextMenu contextMenu;
        private System.Windows.Forms.MenuItem menuItem1;
        private System.Windows.Forms.MenuItem editMenuItem;
    }
}