package main.java.com.Assignment;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LogReadHelper {

	/**Method to filter Map results based on the inputs
	 * @param sorted - Map to filter
	 * @param filtervalue - number of values to be filtered
	 * @return
	 */
	public Map<String, Integer> filterTopResults(Map<String, Integer> sorted, int filtervalue) {
		Map<String, Integer> mostActiveIps = sorted
				.entrySet()
				.stream()
				.limit(filtervalue)
				.collect(Collectors.toMap
						(Map.Entry::getKey, Map.Entry::getValue, (v1,v2) -> v1, LinkedHashMap::new));
		return mostActiveIps;
	}

	/**Method to sort the map based on value and reverse the result
	 * @param originalMap - Map to be acted upon
	 * @return
	 */
	public Map<String, Integer> sortAndReverseMap(Map<String, Integer> originalMap) {
		Map<String, Integer> sortedMap = originalMap
         .entrySet()
         .stream()
         .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
			   .collect(Collectors.toMap
					   (Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
		return sortedMap;
	}
	
}
