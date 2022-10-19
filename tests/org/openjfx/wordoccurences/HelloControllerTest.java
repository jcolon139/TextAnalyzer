package org.openjfx.wordoccurences;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class HelloControllerTest {

    @Test
    void getText() throws IOException {
        File file = new File("C:\\Users\\jcol_\\IdeaProjects\\WordOccurences\\tests\\testFile");
        Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String word = scanner.next();
                word = word.toLowerCase();
                word = word.replaceAll("[^a-zA-Z ]", "");

                System.out.println(word);
            }
        scanner.close();
    }
}