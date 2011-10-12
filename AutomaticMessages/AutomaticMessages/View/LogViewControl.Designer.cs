namespace AutomaticMessages.View
{
    partial class LogViewControl
    {
        /// <summary> 
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

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

        #region Component Designer generated code

        /// <summary> 
        /// Required method for Designer support - do not modify 
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(LogViewControl));
            this.logListView = new System.Windows.Forms.ListView();
            this.statusImageColumnHeader = new System.Windows.Forms.ColumnHeader();
            this.numberColumnHeader = new System.Windows.Forms.ColumnHeader();
            this.textColumnHeader = new System.Windows.Forms.ColumnHeader();
            this.dateTimeColumnHeader = new System.Windows.Forms.ColumnHeader();
            this.logImageList = new System.Windows.Forms.ImageList();
            this.SuspendLayout();
            // 
            // logListView
            // 
            this.logListView.Columns.Add(this.statusImageColumnHeader);
            this.logListView.Columns.Add(this.numberColumnHeader);
            this.logListView.Columns.Add(this.textColumnHeader);
            this.logListView.Columns.Add(this.dateTimeColumnHeader);
            this.logListView.Dock = System.Windows.Forms.DockStyle.Fill;
            this.logListView.FullRowSelect = true;
            this.logListView.Location = new System.Drawing.Point(0, 0);
            this.logListView.Name = "logListView";
            this.logListView.Size = new System.Drawing.Size(150, 150);
            this.logListView.SmallImageList = this.logImageList;
            this.logListView.TabIndex = 0;
            this.logListView.View = System.Windows.Forms.View.Details;
            // 
            // statusImageColumnHeader
            // 
            this.statusImageColumnHeader.Text = "";
            this.statusImageColumnHeader.Width = 19;
            // 
            // numberColumnHeader
            // 
            this.numberColumnHeader.Text = "Number";
            this.numberColumnHeader.Width = 100;
            // 
            // textColumnHeader
            // 
            this.textColumnHeader.Text = "Text";
            this.textColumnHeader.Width = 93;
            // 
            // dateTimeColumnHeader
            // 
            this.dateTimeColumnHeader.Text = "Timestamp";
            this.dateTimeColumnHeader.Width = 150;
            this.logImageList.Images.Clear();
            this.logImageList.Images.Add(((System.Drawing.Image)(resources.GetObject("resource"))));
            this.logImageList.Images.Add(((System.Drawing.Image)(resources.GetObject("resource1"))));
            // 
            // LogViewControl
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(96F, 96F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi;
            this.Controls.Add(this.logListView);
            this.Name = "LogViewControl";
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ListView logListView;
        private System.Windows.Forms.ImageList logImageList;
        private System.Windows.Forms.ColumnHeader dateTimeColumnHeader;
        private System.Windows.Forms.ColumnHeader numberColumnHeader;
        private System.Windows.Forms.ColumnHeader textColumnHeader;
        private System.Windows.Forms.ColumnHeader statusImageColumnHeader;
    }
}
