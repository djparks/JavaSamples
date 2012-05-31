package org.parksy.java.examples.configuration;

import java.util.*;
import java.io.*;

public class XmlProperty {

	public void storeProperties() {
		Properties prop = new Properties();
		prop.setProperty("one-two", "buckle my shoe");
		prop.setProperty("three-four", "shut the door");
		prop.setProperty("five-six", "pick up sticks");
		prop.setProperty("seven-eight", "lay them straight");
		prop.setProperty("nine-ten", "a big, fat hen");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("rhyme.xml");
			prop.storeToXML(fos, "Rhyme");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void readProperties(String args[]) throws Exception {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("rhyme.xml");
		prop.loadFromXML(fis);
		prop.list(System.out);
		System.out.println("\nThe foo property: "
				+ prop.getProperty("nine-ten"));
	}

	// Sample to get from Classpath in or out of JAR
	public void loadProps2() {
		Properties configProp = new Properties();

		InputStream in = this
				.getClass()
				.getClassLoader()
				.getResourceAsStream("rhyme.xml");
//		.getResourceAsStream("net/viralpatel/resources/rhyme.xml");
		try {
			configProp.loadFromXML(in);
			//configProp.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
