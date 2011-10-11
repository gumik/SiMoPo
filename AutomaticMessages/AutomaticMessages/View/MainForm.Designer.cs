namespace AutomaticMessages
{
    partial class MainForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MainForm));
            this.mainMenu1 = new System.Windows.Forms.MainMenu();
            this.exitMenuItem = new System.Windows.Forms.MenuItem();
            this.menuItem1 = new System.Windows.Forms.MenuItem();
            this.messagesMenuItem = new System.Windows.Forms.MenuItem();
            this.numbersMenuItem = new System.Windows.Forms.MenuItem();
            this.menuItem2 = new System.Windows.Forms.MenuItem();
            this.logListView = new System.Windows.Forms.ListView();
            this.logImageList = new System.Windows.Forms.ImageList();
            this.dateTimeColumnHeader = new System.Windows.Forms.ColumnHeader();
            this.numberColumnHeader = new System.Windows.Forms.ColumnHeader();
            this.textColumnHeader = new System.Windows.Forms.ColumnHeader();
            this.SuspendLayout();
            // 
            // mainMenu1
            // 
            this.mainMenu1.MenuItems.Add(this.exitMenuItem);
            this.mainMenu1.MenuItems.Add(this.menuItem1);
            // 
            // exitMenuItem
            // 
            this.exitMenuItem.Text = "Exit";
            this.exitMenuItem.Click += new System.EventHandler(this.exitMenuItem_Click);
            // 
            // menuItem1
            // 
            this.menuItem1.MenuItems.Add(this.messagesMenuItem);
            this.menuItem1.MenuItems.Add(this.numbersMenuItem);
            this.menuItem1.MenuItems.Add(this.menuItem2);
            this.menuItem1.Text = "Menu";
            // 
            // messagesMenuItem
            // 
            this.messagesMenuItem.Text = "Messages";
            this.messagesMenuItem.Click += new System.EventHandler(this.messagesMenuItem_Click);
            // 
            // numbersMenuItem
            // 
            this.numbersMenuItem.Text = "Numbers";
            this.numbersMenuItem.Click += new System.EventHandler(this.numbersMenuItem_Click);
            // 
            // menuItem2
            // 
            this.menuItem2.Text = "add log";
            this.menuItem2.Click += new System.EventHandler(this.menuItem2_Click);
            // 
            // logListView
            // 
            this.logListView.Columns.Add(this.dateTimeColumnHeader);
            this.logListView.Columns.Add(this.numberColumnHeader);
            this.logListView.Columns.Add(this.textColumnHeader);
            this.logListView.FullRowSelect = true;
            this.logListView.Location = new System.Drawing.Point(3, 3);
            this.logListView.Name = "logListView";
            this.logListView.Size = new System.Drawing.Size(234, 262);
            this.logListView.SmallImageList = this.logImageList;
            this.logListView.TabIndex = 0;
            this.logListView.View = System.Windows.Forms.View.Details;
            this.logImageList.Images.Clear();
            this.logImageList.Images.Add(((System.Drawing.Image)(resources.GetObject("resource"))));
            this.logImageList.Images.Add(((System.Drawing.Image)(resources.GetObject("resource1"))));
            // 
            // dateTimeColumnHeader
            // 
            this.dateTimeColumnHeader.Text = "Timestamp";
            this.dateTimeColumnHeader.Width = 60;
            // 
            // numberColumnHeader
            // 
            this.numberColumnHeader.Text = "Number";
            this.numberColumnHeader.Width = 75;
            // 
            // textColumnHeader
            // 
            this.textColumnHeader.Text = "Text";
            this.textColumnHeader.Width = 60;
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(96F, 96F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi;
            this.AutoScroll = true;
            this.ClientSize = new System.Drawing.Size(240, 268);
            this.Controls.Add(this.logListView);
            this.Menu = this.mainMenu1;
            this.Name = "MainForm";
            this.Text = "MainForm";
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.MenuItem exitMenuItem;
        private System.Windows.Forms.MenuItem messagesMenuItem;
        private System.Windows.Forms.MenuItem numbersMenuItem;
        private System.Windows.Forms.MenuItem menuItem1;
        private System.Windows.Forms.ListView logListView;
        private System.Windows.Forms.MenuItem menuItem2;
        private System.Windows.Forms.ImageList logImageList;
        private System.Windows.Forms.ColumnHeader dateTimeColumnHeader;
        private System.Windows.Forms.ColumnHeader numberColumnHeader;
        private System.Windows.Forms.ColumnHeader textColumnHeader;
    }
}