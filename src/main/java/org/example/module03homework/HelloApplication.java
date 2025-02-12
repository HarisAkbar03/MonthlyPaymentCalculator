package org.example.module03homework;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        // Create Labels
        Label lb1 = new Label("Annual Interest Rate:    ");
        Label lb2 = new Label("Number of Years:         ");
        Label lb3 = new Label("Loan Amount:              ");
        Label lb4 = new Label("Monthly Payment:        ");
        Label lb5 = new Label("Total Payment:            ");

        // Create TextFields
        TextField tf1 = new TextField();  // Annual Interest Rate
        TextField tf2 = new TextField();  // Number of Years
        TextField tf3 = new TextField();  // Loan Amount
        TextField tf4 = new TextField();  // Monthly Payment
        TextField tf5 = new TextField();  // Total Payment

        // Set text field width
        int textFieldWidth = 200;
        tf1.setPrefWidth(textFieldWidth);
        tf2.setPrefWidth(textFieldWidth);
        tf3.setPrefWidth(textFieldWidth);
        tf4.setPrefWidth(textFieldWidth);
        tf5.setPrefWidth(textFieldWidth);

        // Create HBoxes (each row contains a Label and a TextField)
        HBox row1 = new HBox(10, lb1, tf1);
        HBox row2 = new HBox(10, lb2, tf2);
        HBox row3 = new HBox(10, lb3, tf3);
        HBox row4 = new HBox(10, lb4, tf4);
        HBox row5 = new HBox(10, lb5, tf5);

        // Create the Calculate Button
        Button button = new Button("Calculate");
        button.setOnAction(e -> {
            try {
                // Parse inputs
                float annualRate = Float.parseFloat(tf1.getText()); // Annual Interest Rate
                int years = Integer.parseInt(tf2.getText());       // Number of Years
                float loanAmount = Float.parseFloat(tf3.getText()); // Loan Amount

                // Convert annual rate to monthly rate (divide by 12 and convert percentage to decimal)
                float monthlyRate = annualRate / 100 / 12;

                // Calculate total number of payments (months)
                int totalPayments = years * 12;

                // Calculate monthly payment using the formula
                float monthlyPayment = (loanAmount * monthlyRate * (float) Math.pow(1 + monthlyRate, totalPayments)) /
                        ((float) Math.pow(1 + monthlyRate, totalPayments) - 1);

                // Calculate total payment
                float totalPayment = monthlyPayment * totalPayments;

                // Set the results into the respective text fields
                tf4.setText(String.format("%.2f", monthlyPayment)); // Monthly Payment
                tf5.setText(String.format("%.2f", totalPayment));   // Total Payment
            } catch (NumberFormatException ex) {
                // Handle invalid input
                tf4.setText("Invalid input");
                tf5.setText("Invalid input");
            }
        });

        // Create a VBox to arrange all rows vertically
        VBox root = new VBox(15, row1, row2, row3, row4, row5, button);
        root.setStyle("-fx-padding: 20px;");

        // Create the scene
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        stage.setTitle("Loan Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
