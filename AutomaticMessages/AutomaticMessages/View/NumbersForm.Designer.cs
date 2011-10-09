namespace AutomaticMessages.View
{
    partial class NumbersForm
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
            this.cancelMenuItem = new System.Windows.Forms.MenuItem();
            this.numbersBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.messagesDataSet = new AutomaticMessages.Data.MessagesDataSet();
            this.numbersListBox = new System.Windows.Forms.ListBox();
            this.contextMenu = new System.Windows.Forms.ContextMenu();
            this.addContextMenuItem = new System.Windows.Forms.MenuItem();
            this.editContextMenuItem = new System.Windows.Forms.MenuItem();
            this.deleteContextMenuItem = new System.Windows.Forms.MenuItem();
            this.messageNameTextBox = new System.Windows.Forms.TextBox();
            this.messagesBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.messageTextTextBox = new System.Windows.Forms.TextBox();
            this.numbersTableAdapter = new AutomaticMessages.Data.MessagesDataSetTableAdapters.NumbersTableAdapter();
            this.messagesTableAdapter = new AutomaticMessages.Data.MessagesDataSetTableAdapters.MessagesTableAdapter();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.numbersBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.messagesDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.messagesBindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // mainMenu1
            // 
            this.mainMenu1.MenuItems.Add(this.addMenuItem);
            this.mainMenu1.MenuItems.Add(this.cancelMenuItem);
            // 
            // addMenuItem
            // 
            this.addMenuItem.Text = "Add";
            this.addMenuItem.Click += new System.EventHandler(this.addMenuItem_Click);
            // 
            // cancelMenuItem
            // 
            this.cancelMenuItem.Text = "Cancel";
            this.cancelMenuItem.Click += new System.EventHandler(this.cancelMenuItem_Click);
            // 
            // numbersBindingSource
            // 
            this.numbersBindingSource.DataMember = "Numbers";
            this.numbersBindingSource.DataSource = this.messagesDataSet;
            this.numbersBindingSource.CurrentChanged += new System.EventHandler(this.numbersBindingSource_CurrentChanged);
            // 
            // messagesDataSet
            // 
            this.messagesDataSet.DataSetName = "MessagesDataSet";
            this.messagesDataSet.Prefix = "";
            this.messagesDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // numbersListBox
            // 
            this.numbersListBox.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.numbersListBox.ContextMenu = this.contextMenu;
            this.numbersListBox.DataSource = this.numbersBindingSource;
            this.numbersListBox.DisplayMember = "Number";
            this.numbersListBox.Location = new System.Drawing.Point(3, 23);
            this.numbersListBox.Name = "numbersListBox";
            this.numbersListBox.Size = new System.Drawing.Size(234, 100);
            this.numbersListBox.TabIndex = 0;
            this.numbersListBox.ValueMember = "Number";
            this.numbersListBox.SelectedValueChanged += new System.EventHandler(this.numbersListBox_SelectedValueChanged);
            // 
            // contextMenu
            // 
            this.contextMenu.MenuItems.Add(this.addContextMenuItem);
            this.contextMenu.MenuItems.Add(this.editContextMenuItem);
            this.contextMenu.MenuItems.Add(this.deleteContextMenuItem);
            // 
            // addContextMenuItem
            // 
            this.addContextMenuItem.Text = "Add";
            this.addContextMenuItem.Click += new System.EventHandler(this.addMenuItem_Click);
            // 
            // editContextMenuItem
            // 
            this.editContextMenuItem.Text = "Edit";
            this.editContextMenuItem.Click += new System.EventHandler(this.editContextMenuItem_Click);
            // 
            // deleteContextMenuItem
            // 
            this.deleteContextMenuItem.Text = "Delete";
            this.deleteContextMenuItem.Click += new System.EventHandler(this.deleteContextMenuItem_Click);
            // 
            // messageNameTextBox
            // 
            this.messageNameTextBox.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.messageNameTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.messagesBindingSource, "Name", true));
            this.messageNameTextBox.Location = new System.Drawing.Point(3, 149);
            this.messageNameTextBox.Name = "messageNameTextBox";
            this.messageNameTextBox.ReadOnly = true;
            this.messageNameTextBox.Size = new System.Drawing.Size(234, 21);
            this.messageNameTextBox.TabIndex = 1;
            // 
            // messagesBindingSource
            // 
            this.messagesBindingSource.DataMember = "Messages";
            this.messagesBindingSource.DataSource = this.messagesDataSet;
            // 
            // messageTextTextBox
            // 
            this.messageTextTextBox.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.messageTextTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.messagesBindingSource, "Text", true));
            this.messageTextTextBox.Location = new System.Drawing.Point(3, 176);
            this.messageTextTextBox.Multiline = true;
            this.messageTextTextBox.Name = "messageTextTextBox";
            this.messageTextTextBox.ReadOnly = true;
            this.messageTextTextBox.Size = new System.Drawing.Size(234, 89);
            this.messageTextTextBox.TabIndex = 2;
            // 
            // numbersTableAdapter
            // 
            this.numbersTableAdapter.ClearBeforeFill = true;
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
            this.label1.Text = "Phones:";
            // 
            // label2
            // 
            this.label2.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.label2.Location = new System.Drawing.Point(3, 126);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(234, 20);
            this.label2.Text = "Message for selected:";
            // 
            // NumbersForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(96F, 96F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi;
            this.AutoScroll = true;
            this.ClientSize = new System.Drawing.Size(240, 268);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.messageTextTextBox);
            this.Controls.Add(this.messageNameTextBox);
            this.Controls.Add(this.numbersListBox);
            this.Menu = this.mainMenu1;
            this.Name = "NumbersForm";
            this.Text = "NumbersForm";
            this.Load += new System.EventHandler(this.NumbersForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.numbersBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.messagesDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.messagesBindingSource)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ListBox numbersListBox;
        private System.Windows.Forms.MenuItem addMenuItem;
        private System.Windows.Forms.MenuItem cancelMenuItem;
        private System.Windows.Forms.ContextMenu contextMenu;
        private System.Windows.Forms.MenuItem addContextMenuItem;
        private System.Windows.Forms.MenuItem editContextMenuItem;
        private System.Windows.Forms.MenuItem deleteContextMenuItem;
        private System.Windows.Forms.TextBox messageNameTextBox;
        private System.Windows.Forms.TextBox messageTextTextBox;
        private AutomaticMessages.Data.MessagesDataSet messagesDataSet;
        private System.Windows.Forms.BindingSource numbersBindingSource;
        private AutomaticMessages.Data.MessagesDataSetTableAdapters.NumbersTableAdapter numbersTableAdapter;
        private System.Windows.Forms.BindingSource messagesBindingSource;
        private AutomaticMessages.Data.MessagesDataSetTableAdapters.MessagesTableAdapter messagesTableAdapter;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;

    }
}