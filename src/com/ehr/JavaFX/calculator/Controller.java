package com.ehr.JavaFX.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {

    private String operator = "";
    private long tempLong = 0;
    private boolean start = true;

    private Model model = new Model();

    @FXML
    private Text output;

    @FXML
    private void processNumpad(ActionEvent event) {
        if (start) {
            output.setText("");
            start = false;
        }
        String value = ((Button)event.getSource()).getText();
        output.setText(output.getText() + value);
    }

    @FXML
    private void processOperator(ActionEvent event) {
        String value = ((Button)event.getSource()).getText();

        if (!value.equals("=")) {
            if (!operator.isEmpty())
                return;

            operator = value;
            tempLong = Long.parseLong(output.getText());
            output.setText("");
        } else {
            if (operator.isEmpty())
                return;

            output.setText(String.valueOf(model.calculate(tempLong, Long.parseLong(output.getText()), operator)));
            operator = "";
            start = true;
        }
    }
}
