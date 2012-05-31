package org.parksy.java.examples.jaxb.hello;

import java.util.*;
import javax.xml.bind.*;
import org.parksy.java.examples.jaxb.hello.generated.*;

public class Hello {

    private ObjectFactory of;
    private GreetingListType grList;

    public Hello(){
        of = new ObjectFactory();
        grList = of.createGreetingListType();
    }

    public void make( String t, String l ){
        GreetingType g = of.createGreetingType();
        g.setText( t );
        g.setLanguage( l );
        grList.getGreeting().add( g );
    }

    public void marshal() {
        try {
            JAXBElement<GreetingListType> gl = of.createGreetings( grList );
            JAXBContext jc = JAXBContext.newInstance( "hello" );
            Marshaller m = jc.createMarshaller();
            m.marshal( gl, System.out );
        } catch( JAXBException jbe ){
            // ...
        }
    }
    
    public static void main(String[] args) {
    	Hello h = new Hello();
    	h.make( "Bonjour, madame", "fr" ); 
    	h.make( "Hey, you", "en" ); 
    	h.marshal();
	}
}