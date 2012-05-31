package org.parksy.java.examples.watch;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;


public class Find2 
	extends SimpleFileVisitor<Path> {

		private final PathMatcher matcher;
		private int numMatches = 0;

		Find2(String pattern) {
			matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
			System.out.println("Finding for Pattern: " + pattern);
		}

		// Compares the glob pattern against
		// the file or directory name.
		void find(Path file) {
			Path name = file.getFileName();
			if (name != null && matcher.matches(name)) {
				numMatches++;
				System.out.println(file);
			}
		}

		// Prints the total number of
		// matches to standard out.
		void done() {
			System.out.println("Matched: "
					+ numMatches);
		}

		// Invoke the pattern matching
		// method on each file.
		@Override
		public FileVisitResult
		visitFile(Path file,
				BasicFileAttributes attrs) {
			find(file);
			return CONTINUE;
		}

		// Invoke the pattern matching
		// method on each directory.
		@Override
		public FileVisitResult
		preVisitDirectory(Path dir,
				BasicFileAttributes attrs) {
			find(dir);
			return CONTINUE;
		}

		@Override
		public FileVisitResult
		visitFileFailed(Path file,
				IOException exc) {
			System.err.println(exc);
			return CONTINUE;
		}


	static void usage() {
		System.err.println("java Find <path>" +
				" -name \"<glob_pattern>\"");
		System.exit(-1);
	}
	
	public static void main(String[] args) {

		if (args.length < 3 
				|| !args[1].equals("-name"))
			usage();

//		args = new String[] {"\\\\excalibur\\ietm\\Temp\\DParks\\test\\", "-name", "*.java"};
		for ( int i= 0; i< args.length; i++ ) {
			System.out.println("Arg"+ i + ": " + args[i]);
		}

		
		Path startingDir = Paths.get(args[0]);
		String pattern = args[2];

		Find2 finder = new Find2(pattern);
		try {
			Files.walkFileTree(startingDir, finder);
		} catch (IOException e) {
			System.out.println(	e.getMessage() );
		}
		finder.done();
	}
}

