package main.java.com.Assignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


/**
 * Class is used to read log data and display details accordingly
 *
 */
public class LogReader {
	public static void main( String[] args )
    {
		LogReader reader = new LogReader();
		String fileName = "digio-programming-task-example-data.log";
		reader.read(fileName);
    }

	public Map<String, Integer> read(String fileName) {
		final Logger log = Logger.getLogger(LogReader.class);
		LogReadHelper readHelper = new LogReadHelper();
		String regex = "(\\d+(?:\\.\\d+){3}) (\\S+) (\\S+) \\[([^\\]]+)\\] \"([^\"]*?)\" (\\d+) (\\d+) \"([^\"]*?)\" \"([^\"]*?)\"";	   
    	int IP_ADDRESS = 1;
    	int URL = 5;
    	int HttpCode = 6;
		Map<String, Integer> ipAddressMap = new HashMap<String, Integer>(); 
		Map<String, Integer> urlMap = new HashMap<String, Integer>(); 
		try {
			   BufferedReader br = Files.newBufferedReader(Paths.get(fileName));
    		   String line;
    		   while ((line = br.readLine()) != null)   {

					Pattern pattern = Pattern.compile(regex);
    			    Matcher match = pattern.matcher(line);
				
					  if( match.find() && match.group(HttpCode).equals("200")){
							  if (ipAddressMap.containsKey(match.group(IP_ADDRESS))) { 
								  ipAddressMap.put(match.group(IP_ADDRESS), ipAddressMap.get(match.group(IP_ADDRESS)) + 1); 
								  } else {
									  ipAddressMap.put(match.group(IP_ADDRESS), 1); 
									} 
							  
							  if (urlMap.containsKey(match.group(URL))) { 
								  urlMap.put(match.group(URL), urlMap.get(match.group(URL)) + 1); 
								  } else {
									  urlMap.put(match.group(URL), 1); 
									} 
					  }
    		   	}
			
    		   
	            Map<String, Integer> sortedIpsAddressMap = readHelper.sortAndReverseMap(ipAddressMap);
	            
		        //sortedIpsAddressMap.forEach((key, value) -> log.info("Unique Ips"+key+" value :: "+value));
		        log.info("Unique IPs Total ::"+sortedIpsAddressMap.size()); 
		        
		        Map<String, Integer> mostActiveIps = readHelper.filterTopResults(sortedIpsAddressMap, 3);
		        
		        mostActiveIps.forEach((key, value) -> log.info("Top 3 Most Active Ips :: "+key));
		        
		        Map<String, Integer> sortedUrlMap = readHelper.sortAndReverseMap(urlMap);
	            
		        Map<String, Integer> mostVisitedUrls = readHelper.filterTopResults(sortedUrlMap, 3);

		        mostVisitedUrls.forEach((key, value) -> log.info("Top 3 Most Visited Urls :: "+ key.split(" ")[1]));
		        
		        
			} catch(IOException io	)
			{
				log.error("IOException Occured: " + io.getMessage());
			}
		return ipAddressMap;
	}
}
