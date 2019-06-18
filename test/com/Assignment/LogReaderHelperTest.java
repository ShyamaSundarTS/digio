package com.Assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import main.java.com.Assignment.LogReadHelper;

public class LogReaderHelperTest {

	LogReadHelper logReaderHelper = spy(LogReadHelper.class);
	Scanner sc = null;
	
	
	@Test
	public void evaluate_filterTopResults_Success() {
		
		Map<String, Integer> ipAddressMap = new HashMap<String, Integer>(); 
		ipAddressMap.put("177.71.128.21", 4);
		ipAddressMap.put("168.41.191.40", 2);
		ipAddressMap.put("168.41.191.41", 2);
		ipAddressMap.put("168.41.191.43", 1);
		ipAddressMap.put("168.41.191.9", 1);
		
		assertEquals(3, logReaderHelper.filterTopResults(ipAddressMap,3).size());
	}
	
	@Test
	public void evaluate_filterTopResults_Failure() {
		
		Map<String, Integer> ipAddressMap = new HashMap<String, Integer>(); 
		ipAddressMap.put("177.71.128.21", 4);
		ipAddressMap.put("168.41.191.40", 2);
		
		assertEquals(2, logReaderHelper.filterTopResults(ipAddressMap,3).size());
	}
	
	@Test
	public void evaluate_filterTopResults_noRecords_Failure() {
		
		Map<String, Integer> ipAddressMap = new HashMap<String, Integer>(); 
		
		assertEquals(0, logReaderHelper.filterTopResults(ipAddressMap,3).size());
	}
	
	@Test
	public void evaluate_filterTopResults_noFailure() {
		
		Map<String, Integer> ipAddressMap = new HashMap<String, Integer>(); 
		ipAddressMap.put("177.71.128.21", 4);
		ipAddressMap.put("168.41.191.40", 2);
		ipAddressMap.put("168.41.191.41", 2);
		ipAddressMap.put("168.41.191.43", 1);
		ipAddressMap.put("168.41.191.9", 1);
		
		assertEquals(0, logReaderHelper.filterTopResults(ipAddressMap,0).size());
	}
	
	@Test
	public void evaluate_sortAndReverseMap_Success() {
		
		Map<String, Integer> ipAddressMap = new HashMap<String, Integer>(); 
		ipAddressMap.put("168.41.191.43", 1);
		ipAddressMap.put("168.41.191.40", 3);
		ipAddressMap.put("168.41.191.9", 1);
		ipAddressMap.put("177.71.128.21", 4);
		ipAddressMap.put("168.41.191.41", 2);
		
		Map<String, Integer> sortedMap = new HashMap<String, Integer>(); 
		sortedMap.put("177.71.128.21", 4);
		sortedMap.put("168.41.191.40", 3);
		sortedMap.put("168.41.191.41", 2);
		sortedMap.put("168.41.191.43", 1);
		sortedMap.put("168.41.191.9", 1);

		
		assertEquals(sortedMap, logReaderHelper.sortAndReverseMap(ipAddressMap));
	}
}
