module org.example.module03homework {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.module03homework to javafx.fxml;
    exports org.example.module03homework;
}