module org.openjfx.wordoccurences {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.openjfx.wordoccurences to javafx.fxml;
    exports org.openjfx.wordoccurences;
}