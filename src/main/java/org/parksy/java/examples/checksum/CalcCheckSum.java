package org.parksy.java.examples.checksum;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;

public class CalcCheckSum {
	
	public long calcCheckSum( String filename ) throws IOException {
		    // Compute Adler-32 checksum
		    CheckedInputStream cis = new CheckedInputStream( new FileInputStream(filename), new Adler32());
		    byte[] tempBuf = new byte[128];
		    while (cis.read(tempBuf) >= 0) {
		    }
		    return cis.getChecksum().getValue();
		
	}

}
