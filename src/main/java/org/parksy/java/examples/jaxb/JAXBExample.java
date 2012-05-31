package org.parksy.java.examples.jaxb;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


/**
 * Sample JAXB Application
 * @author dparks
 * Based off of a very simple example at http://www.mkyong.com/java/jaxb-hello-world-example/
 *  
 *  Note: The XML Schema generator schemagen derives a schema from a set of Java classes. 
 */
public class JAXBExample {
	
	
	private static final String FILE_XML = "C:\\test\\jaxb.xml";
	private static final String USER_NAME = "darryl";

	public static void main(String[] args) {


		prettyPrint("Marshal");
		//Marshal
		try {

			Customer customer = new Customer();
			customer.setId(100);
			customer.setName(USER_NAME);
			customer.setAge(29);

			File file = new File(FILE_XML);
			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// OR SIMPLIFIED:
			//JAXB.marshal(customer, new File("C:/file.xml"));
			
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(customer, file);
			jaxbMarshaller.marshal(customer, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}


		//UnMarshall
		prettyPrint("Un Marshal!");
		try {

			File file = new File(FILE_XML);
			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Customer customer = (Customer) jaxbUnmarshaller.unmarshal(file);
			
			// OR SIMPLIFIED
			//Customer customer = JAXB.unmarshall(new File("C:/file.xml"), Customer.class);
			
			
			System.out.println(customer);

		} catch (JAXBException e) {
			e.printStackTrace();
		}	
	}

	private static void prettyPrint(String string) {
		System.out.println("");
		System.out.println("// ******************************************************************");
		System.out.println("// **** " + string );
		System.out.println("// ******************************************************************");
		
	}
}