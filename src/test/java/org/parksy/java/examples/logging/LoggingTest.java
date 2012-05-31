package org.parksy.java.examples.logging;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class LoggingTest {
	@Rule
	public MethodRule watchman = new TestWatchman() {
		public void starting(FrameworkMethod method) {
//			logger.info("{} being run...", method.getName());
		}
	};
//	final Logger logger = LoggerFactory.getLogger(LoggingTest.class);

	@Test
	public void testA() {
	}

	@Test
	public void testB() {
	}
}