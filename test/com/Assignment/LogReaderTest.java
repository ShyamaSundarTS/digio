
package com.Assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import main.java.com.Assignment.LogReader;


  public class LogReaderTest {

	    LogReader logReader = spy(LogReader.class);
		Scanner sc = null;
		
		
		@Test
		public void evaluate_singleRecord() {
			
			Map<String, Integer> ipAddressMap = new HashMap<String, Integer>(); 
			ipAddressMap.put("177.71.128.21", 1);
			
			assertEquals(ipAddressMap, logReader.read("./test/com/data/single-record-example-data.log"));
		}
		
		@Test
		public void evaluate_noRecord() {
			
			Map<String, Integer> ipAddressMap = new HashMap<String, Integer>(); 
			
			assertEquals(ipAddressMap, logReader.read("./test/com/data/empty-data.log"));
		}
		
		@Test
		public void evaluate_duplicateRecord() {
			
			Map<String, Integer> ipAddressMap = new HashMap<String, Integer>(); 
			ipAddressMap.put("177.71.128.21", 2);
			
			assertEquals(ipAddressMap, logReader.read("./test/com/data/duplicate-record-example-data.log"));
		}
		
		@Test
		public void evaluate_errorResponseRecord() {
			
			Map<String, Integer> ipAddressMap = new HashMap<String, Integer>(); 
			
			assertEquals(ipAddressMap, logReader.read("./test/com/data/error-response-record-example-data.log"));
		}
  }
		 