package org.parksy.java.examples.java7copy;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CopyFromURL {
	
	public static void main(String[] args) throws IOException {
		
		//String sURL = "http://cdgmainportal.cdg.inside/pmit/CAE/Shared Documents/IT Related/efds.html";
		String sURL = "http://cdgmainportal.cdg.inside/pmit/CAE/Shared%20Documents/IT%20Related/efds.html";
		URL myUrl = new URL(sURL);
		
		String file = "c:\\test\\efds.html";
		File myFile = new File( file );
		
			org.apache.commons.io.FileUtils.copyURLToFile(myUrl, myFile);
		
	}

}
