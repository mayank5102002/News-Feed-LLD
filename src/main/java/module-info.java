module com.example.news_feed {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.news_feed to javafx.fxml;
    exports com.example.news_feed;
}