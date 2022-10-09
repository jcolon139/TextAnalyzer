package application;
	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Main extends Application {

	private Text actionStatus;


	public static void main(String [] args) {

	    Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Word Occurences");
		Pane p = new Pane();
	    Button open = new Button("Click here to select a text file to analyze.");
	    open.setOnAction(new SingleFcButtonListener());
	    HBox open1 = new HBox(10);
	    open1.getChildren().addAll(open);

	    actionStatus = new Text();

	    VBox vbox = new VBox(30);
	    vbox.getChildren().addAll( open1,  actionStatus);
	    Scene scene = new Scene(vbox, 500,500); 
	    primaryStage.setScene(scene);
	    primaryStage.show();
	   
	    
	}

	private class SingleFcButtonListener implements EventHandler<ActionEvent> {

	    @Override
	    public void handle(ActionEvent e) {

	        showSingleFileChooser();
	    }
	}

	private String showSingleFileChooser() {

	    FileChooser fileChooser = new FileChooser();
	    File selectedFile = fileChooser.showOpenDialog(null);
	    
	    if (selectedFile != null) {

	        actionStatus.setText("File Selected: " + selectedFile.getName());
	    }
	    
	    StringBuilder stringBuffer = new StringBuilder();
	    try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {

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
			
			stringBuffer.append(list);

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return stringBuffer.toString();
	}
}