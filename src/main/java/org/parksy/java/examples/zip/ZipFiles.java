package org.parksy.java.examples.zip;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipFiles {

	public void zipFile() {
		// These are the files to include in the ZIP file
		String[] filenames = new String[]{"filename1", "filename2"};

		// Create a buffer for reading the files
		byte[] buf = new byte[1024];

		try {
			// Create the ZIP file
			String outFilename = "outfile.zip";
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream( new FileOutputStream(outFilename))); //new ZipOutputStream(new FileOutputStream(outFilename));

			// Compress the files
			for (int i=0; i<filenames.length; i++) {
				FileInputStream in = new FileInputStream(filenames[i]);

				// Add ZIP entry to output stream.
				out.putNextEntry(new ZipEntry(filenames[i]));

				// Transfer bytes from the file to the ZIP file
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}

				// Complete the entry
				out.closeEntry();
				//TODO Need to handle exceptions
				in.close();
			}

			//TODO Need to handle exceptions
			// Complete the ZIP file
			out.close();
		} catch (IOException e) {
			//TODO Need to handle exceptions
			System.out.println(e.getLocalizedMessage() );
		}

	}
	
	public void listContents() {
		try {
		    // Open the ZIP file
		    ZipFile zf = new ZipFile("outfile.zip");

		    // Enumerate each entry
		    for (Enumeration entries = zf.entries(); entries.hasMoreElements();) {
		        // Get the entry name
		        String zipEntryName = ((ZipEntry)entries.nextElement()).getName();
		        System.out.println(""+ zipEntryName );
		        
		    }
		} catch (IOException e) {
			//TODO Need to handle exceptions
			System.out.println(e.getLocalizedMessage() );
		}
		
	}
	
	public void extractFile() {
		try {
		    // Open the ZIP file
		    String inFilename = "infile.zip";
		    ZipInputStream in = new ZipInputStream(new FileInputStream(inFilename));
		    //TODO: Faster??
//		    ZipInputStream in = new ZipInputStream( new BufferedOutputStream( new FileInputStream(inFilename)));

		    // Get the first entry
		    ZipEntry entry = in.getNextEntry();

		    // Open the output file
		    String outFilename = "o";
		    OutputStream out = new FileOutputStream(outFilename);

		    // Transfer bytes from the ZIP file to the output file
		    byte[] buf = new byte[1024];
		    int len;
		    while ((len = in.read(buf)) > 0) {
		        out.write(buf, 0, len);
		    }

		    //TODO: Lots of Bad error handling and eating exception
		    // Close the streams
		    out.close();
		    in.close();
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage() );
		}
		
	}

}
