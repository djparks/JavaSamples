package org.parksy.java.examples.filedownloader;

import java.awt.FlowLayout;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
 
public class Downloader extends JFrame
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final void main(String[] args) throws Exception
    {
    String site="http://www.mysite.com/file.zip";
    String filename="temp.zip";
    JFrame frm=new JFrame();
    JProgressBar current = new JProgressBar(0, 100);
    current.setSize(50,50);
    current.setValue(43);
    current.setStringPainted(true);
    frm.add(current);
    frm.setVisible(true);
    frm.setLayout(new FlowLayout());
    frm.setSize(400, 200);
    frm.setDefaultCloseOperation(EXIT_ON_CLOSE);
    try {
        URL url=new URL(site);
        HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();
        int filesize = connection.getContentLength();
        float totalDataRead=0;
            java.io.BufferedInputStream in = new java.io.BufferedInputStream(connection.getInputStream());
            java.io.FileOutputStream fos = new java.io.FileOutputStream(filename);
            java.io.BufferedOutputStream bout = new BufferedOutputStream(fos,1024);
            byte[] data = new byte[1024];
            int i=0;
            while((i=in.read(data,0,1024))>=0)
            {
            totalDataRead=totalDataRead+i;
            bout.write(data,0,i);
            float Percent=(totalDataRead*100)/filesize;
            current.setValue((int)Percent);
            }  
            bout.close();
            in.close();
    }
    catch(Exception e)
    {
         javax.swing.JOptionPane.showConfirmDialog((java.awt.Component)
                 null,e.getMessage(), "Error",
                 javax.swing.JOptionPane.DEFAULT_OPTION);
    }
}
}