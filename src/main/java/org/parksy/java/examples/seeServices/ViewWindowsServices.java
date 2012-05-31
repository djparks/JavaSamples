package org.parksy.java.examples.seeServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ViewWindowsServices {

	public void list() {
		String os = System.getProperty("os.name").toLowerCase();
		if ( os.startsWith("windows")) {
			BufferedReader in = null;
			try {
				Process p = Runtime.getRuntime().exec("net start");
				in = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String text;
				while ((text = in.readLine()) != null ){
					System.out.println(text);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					if (in != null ){
						in.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else
		{
			System.out.println("This is only works on Windows");
		}

	}

}
