package org.openjfx.wordoccurences;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;

public class HelloController implements Initializable {

    FileChooser fileChooser = new FileChooser();

    @FXML
    private TextArea textArea;

    @FXML
    void getText(MouseEvent event) throws IOException {
        File file = fileChooser.showOpenDialog(new Stage());
        Scanner scanner = new Scanner(file);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            LinkedHashMap<String, Integer> wordCount = new LinkedHashMap<>();

            while (scanner.hasNext()) {
                String word = scanner.next();
                word = word.toLowerCase();
                word = word.replaceAll("[^a-zA-Z ]", "");
                Integer count = wordCount.get(word);

                if (count != null)
                    count++;
                else
                    count = 1;
                wordCount.put(word, count);
            }

            scanner.close();

            List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(wordCount.entrySet());

            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                public int compare(
                        Map.Entry<String, Integer> entry1,
                        Map.Entry<String, Integer> entry2) {
                    return entry1.getValue() - entry2.getValue();
                }
            });

            Collections.reverse(list);

            int elementCount = 1;

            for (Map.Entry<String, Integer> a : list) {
                a.getKey();
                a.getValue();

                textArea.appendText(a.getKey() + "\t -> ");
                textArea.appendText(String.valueOf(a.getValue())  + "\n");

                if (elementCount >= 20)
                    break;
                elementCount++;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser.setInitialDirectory(new File("\\C:\\Users\\jcol_\\Documents"));
    }
}
