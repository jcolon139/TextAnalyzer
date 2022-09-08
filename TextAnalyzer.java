/*Javier Colon Morales
 * 09-08-2022
 * CEN-3024C Software Development 1
 * Professor Mary Walauskis
 * 
 * Module 2 - SDLC Assignment
 * 
 * Write a text analyzer that reads a file and outputs statistics 
 * about that file. It should output the word frequencies of all words in the file, 
 * sorted by the most frequently used word. The output should be a set of pairs, each pair 
 * containing a word and how many times it occurred in the file.
 * 
 * Submit a screen shot of the top 20 words in the following file (a poem):
 * https://www.gutenberg.org/files/1065/1065-h/1065-h.htm (Links to an external site.)

     Notes:
        Ignore all HTML tags
        Ignore all text before the poem's title
        Ignore all text after the end of the poem
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TextAnalyzer {

	public static void main(String[] args) throws IOException {
			BufferedReader reader = Files.newBufferedReader(Paths.get("C:\\Users\\jcol_\\Desktop\\School Folder\\Bachelors Computing and Software Development Classes\\6_Fall 2022\\Software Development 1\\Module 2\\theRaven.txt"));
			Scanner fileScan = new Scanner(reader);
			Map<String, Integer> wordCount = new HashMap<String, Integer>();
			
			while(fileScan.hasNextLine()) {
				String word = fileScan.next();
				Integer count = wordCount.get(word);
				
				if (count != null)
					count++;
				else
					count = 1;
				wordCount.put(word, count);
			}	
			fileScan.close();
			
			//Map key sorting
			Map<String, Integer> sortedWordCount = wordCount.entrySet().stream()
					.sorted(Comparator.comparingInt(e-> -e.getValue()))
					.collect(Collectors.toMap(
							Map.Entry::getKey,
							Map.Entry::getValue,
							(a, b) -> {throw new AssertionError(); },
							LinkedHashMap::new
							));
			
			sortedWordCount.entrySet().forEach(System.out::println);
			
	}
}
