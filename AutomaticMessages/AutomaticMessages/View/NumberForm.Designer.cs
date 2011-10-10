namespace AutomaticMessages.View
{
    partial class NumberForm
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
            this.numberTextBox = new System.Windows.Forms.TextBox();
            this.messagesBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.messagesDataSet = new AutomaticMessages.Data.MessagesDataSet();
            this.messagesComboBox = new System.Windows.Forms.ComboBox();
            this.messagesTableAdapter = new AutomaticMessages.Data.MessagesDataSetTableAdapters.MessagesTableAdapter();
            this.messageTextBox = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.messagesBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.messagesDataSet)).BeginInit();
            this.SuspendLayout();
            // 
            // numberTextBox
            // 
            this.numberTextBox.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.numberTextBox.Location = new System.Drawing.Point(3, 23);
            this.numberTextBox.MaxLength = 15;
            this.numberTextBox.Name = "numberTextBox";
            this.numberTextBox.Size = new System.Drawing.Size(234, 21);
            this.numberTextBox.TabIndex = 0;
            this.numberTextBox.KeyDown += new System.Windows.Forms.KeyEventHandler(this.numberTextBox_KeyDown);
            this.numberTextBox.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.numberTextBox_KeyPress);
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
            // messagesComboBox
            // 
            this.messagesComboBox.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.messagesComboBox.DataBindings.Add(new System.Windows.Forms.Binding("SelectedValue", this.messagesBindingSource, "MessageId", true));
            this.messagesComboBox.DataSource = this.messagesBindingSource;
            this.messagesComboBox.DisplayMember = "Name";
            this.messagesComboBox.Location = new System.Drawing.Point(3, 70);
            this.messagesComboBox.Name = "messagesComboBox";
            this.messagesComboBox.Size = new System.Drawing.Size(234, 22);
            this.messagesComboBox.TabIndex = 1;
            this.messagesComboBox.ValueMember = "MessageId";
            // 
            // messagesTableAdapter
            // 
            this.messagesTableAdapter.ClearBeforeFill = true;
            // 
            // messageTextBox
            // 
            this.messageTextBox.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.messageTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.messagesBindingSource, "Text", true));
            this.messageTextBox.Location = new System.Drawing.Point(3, 98);
            this.messageTextBox.Multiline = true;
            this.messageTextBox.Name = "messageTextBox";
            this.messageTextBox.ReadOnly = true;
            this.messageTextBox.Size = new System.Drawing.Size(234, 167);
            this.messageTextBox.TabIndex = 2;
            // 
            // label1
            // 
            this.label1.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.label1.Location = new System.Drawing.Point(3, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(234, 20);
            this.label1.Text = "Phone:";
            // 
            // label2
            // 
            this.label2.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.label2.Location = new System.Drawing.Point(3, 47);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(234, 20);
            this.label2.Text = "Message:";
            // 
            // NumberForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(96F, 96F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi;
            this.AutoScroll = true;
            this.ClientSize = new System.Drawing.Size(240, 268);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.messageTextBox);
            this.Controls.Add(this.messagesComboBox);
            this.Controls.Add(this.numberTextBox);
            this.Menu = this.mainMenu1;
            this.Name = "NumberForm";
            this.Text = "NumberForm";
            ((System.ComponentModel.ISupportInitialize)(this.messagesBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.messagesDataSet)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TextBox numberTextBox;
        private System.Windows.Forms.ComboBox messagesComboBox;
        private AutomaticMessages.Data.MessagesDataSet messagesDataSet;
        private System.Windows.Forms.BindingSource messagesBindingSource;
        private AutomaticMessages.Data.MessagesDataSetTableAdapters.MessagesTableAdapter messagesTableAdapter;
        private System.Windows.Forms.TextBox messageTextBox;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
    }
}