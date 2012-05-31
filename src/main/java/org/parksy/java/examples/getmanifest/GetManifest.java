package org.parksy.java.examples.getmanifest;

import java.net.URL;
import java.util.Properties;
import java.util.jar.Manifest;

public class GetManifest {

	/**
	 * To get from calling class
	 * Class<?> caller = null; 
try { 
    caller = Class.forName(new Throwable().fillInStackTrace().getStackTrace()[1].getClassName()); 
} catch (ClassNotFoundException e) { 
    e.printStackTrace(System.err); 
}
	 */
	public static void main(String[] args) {

	}

	public void getManifest() {
		Class clazz = this.getClass(); 
		String className = clazz.getSimpleName() + ".class"; 
		String classPath = clazz.getResource(className).toString(); 
		if (!classPath.startsWith("jar")) { 
			// Class not from JAR 
			return; // null; 
		} 
		String manifestPath = classPath.substring(0, classPath.lastIndexOf("!") + 1) + "/META-INF/MANIFEST.MF"; 
		Manifest manifest = null; 
		try { 
			//	    manifest = new Manifest(new URL(manifestPath).openStream());

			Properties prop = new Properties(); 
			prop.load(this.getClass().getResourceAsStream("/META-INF/MANIFEST.MF")); 
			System.out.println("All attributes:" + prop.stringPropertyNames()); 
			//	    System.out.println(prop.getProperty("{whatever attribute you want}")); 

			//        Attributes attributes = manifest.getMainAttributes(); 
			//        String impVersion = attributes.getValue("Implementation-Version"); 
			//        mVersionString = impVersion; 

		} catch (Exception e) { 
			e.printStackTrace(System.err); 
		}		
	}
}
