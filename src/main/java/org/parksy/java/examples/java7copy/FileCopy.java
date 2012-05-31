package org.parksy.java.examples.java7copy;

import static java.nio.file.StandardCopyOption.*; 

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class FileCopy {
	
	public static void main(String[] args) {
		
		Path source = Paths.get("http://cdgmainportal.cdg.inside/pmit/CAE/Shared Documents/IT Related/efds.html");
		Path target = Paths.get("C:\\test\\");
		
		try {
			Files.copy(source, target, REPLACE_EXISTING);
			//Files.move(source, target);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
