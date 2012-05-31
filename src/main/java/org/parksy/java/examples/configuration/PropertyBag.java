package org.parksy.java.examples.configuration;

import java.util.Properties;
import java.io.*;

/**
 * File for setting and getting properties from application.jmx.properties file
 * 
 * 
 */
public class PropertyBag {

    static {
        loadProperties();
    }

    /**
     * @return the property
     */
    public static String getProperty(String key) {
        if (propFile == null)
            loadProperties();
        return propFile.getProperty(key);
    }

    /**
     * @param property
     *            the property to set
     */
    public static void setProperty(String property, String key) {
        if (propFile != null)
            propFile.setProperty(property, key);
    }

    private static final String FILE_NAME = "rhyme.xml";
    private static Properties propFile = null;

    public static void loadProperties() {
        if (propFile == null) {
            propFile = new Properties();
            try {
                propFile.loadFromXML(Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream(FILE_NAME));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }
    }
}
