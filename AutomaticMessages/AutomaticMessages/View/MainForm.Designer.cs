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
            this.mainMenu1 = new System.Windows.Forms.MainMenu();
            this.exitMenuItem = new System.Windows.Forms.MenuItem();
            this.menuItem1 = new System.Windows.Forms.MenuItem();
            this.messagesMenuItem = new System.Windows.Forms.MenuItem();
            this.numbersMenuItem = new System.Windows.Forms.MenuItem();
            this.testTextBox = new System.Windows.Forms.TextBox();
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
            // testTextBox
            // 
            this.testTextBox.Location = new System.Drawing.Point(3, 3);
            this.testTextBox.Multiline = true;
            this.testTextBox.Name = "testTextBox";
            this.testTextBox.Size = new System.Drawing.Size(234, 262);
            this.testTextBox.TabIndex = 0;
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(96F, 96F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi;
            this.AutoScroll = true;
            this.ClientSize = new System.Drawing.Size(240, 268);
            this.Controls.Add(this.testTextBox);
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
        private System.Windows.Forms.TextBox testTextBox;
    }
}