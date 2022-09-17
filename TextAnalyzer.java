/*Javier Colon Morales
 * 09-16-2022
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;


public class TextAnalyzer {

	public static void main(String[] args) throws IOException {
			BufferedReader reader = Files.newBufferedReader(Paths.get("C:\\Users\\jcol_\\Desktop\\School Folder\\Bachelors Computing and Software Development Classes\\6_Fall 2022\\Software Development 1\\Module 2\\theRaven.txt"));
			Scanner fileScan = new Scanner(reader);
			LinkedHashMap<String, Integer> wordCount = new LinkedHashMap<>();
			
			while(fileScan.hasNextLine()) {
				String word = fileScan.next();
				word = word.toLowerCase();
				word = word.replaceAll("[^a-zA-Z ]", "");
				Integer count = wordCount.get(word);
				
				if (count != null)
					count++;
				else
					count = 1;
				wordCount.put(word, count);
			}	
			
			fileScan.close();

			List<Map.Entry<String, Integer> > list = new ArrayList<Map.Entry<String, Integer> >(wordCount.entrySet());

			Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
				public int compare(
						Map.Entry<String, Integer> entry1,
						Map.Entry<String, Integer> entry2)
				{
					return entry1.getValue() - entry2.getValue();
				}
			});
			
			Collections.reverse(list);
			
			int elementCount = 1;
			
			for (Entry<String, Integer> a : list) {
				System.out.println(a.getKey() + "\t -> " + a.getValue());
				if(elementCount >= 20)
					break;
				elementCount++;
			}

	}
}