package pap.ass08.tempSensor;

import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author edoardo
 */
public class GUI extends Application {

    private static Label min;
    private static Label max;
    private static Label temp;
    private static Button start;
    private static Button stop;
    private static NumberTextField inputQuota;
    private static NumberTextField inputTime;
    private static GridPane gp;
    private static Controller c;
    private static boolean stopCheck;

    public static void main(String[] args) {
        stopCheck = false;
        try {
            c = Controller.getController();
        } catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            Platform.exit();
        }
        launch(args);
    }

    @Override
    public void stop() {
        c.stopRead();
        System.exit(0);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Temp Sensors");
        stage.setResizable(false);
        Group root = new Group();
        Scene scene = new Scene(root, 350, 140, Color.web("#2C3E50"));
        String cssURL = this.getClass().getResource("style.css").toExternalForm();
        scene.getStylesheets().add(cssURL);

        gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setPadding(new Insets(10, 10, 10, 10));

        this.setControls();
        this.setStat();
        this.setInput();

        root.getChildren().addAll(gp);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    private void setControls() {
        start = new Button("Start");
        stop = new Button("Stop");
        stop.setDisable(true);
        start.setOnMouseClicked((MouseEvent) -> {
            stop.setDisable(false);
            start.setDisable(true);
            c.startRead();
        });
        stop.setOnMouseClicked((MouseEvent) -> {
            start.setDisable(false);
            stop.setDisable(true);
            c.stopRead();
        });
        gp.add(start, 0, 0);
        gp.add(stop, 1, 0);
    }

    private void setStat() {
        Label minLab = new Label("Min:");
        Label maxLab = new Label("Max:");
        Label tempLab = new Label("Temp:");
        min = new Label("--");
        max = new Label("--");
        temp = new Label("--");
        gp.getColumnConstraints().add(new ColumnConstraints(60));
        gp.getColumnConstraints().add(new ColumnConstraints(90));
        gp.addRow(1, minLab, min, tempLab, temp);
        gp.addRow(2, maxLab, max);
    }

    private void setInput() {
        Label quotaLab = new Label("Quota: ");
        Label timeLab = new Label("Max Time: ");
        inputQuota = new NumberTextField();
        inputTime = new NumberTextField();
        inputQuota.setMaxWidth(70);
        inputTime.setMaxWidth(70);
        gp.addRow(3, quotaLab, inputQuota, timeLab, inputTime);

    }

    public static void updateTemp(double temp) {
        Platform.runLater(() -> {
            if (!GUI.stopCheck && !inputTime.getText().isEmpty() && !inputQuota.getText().isEmpty()) {
                if (temp > Integer.parseInt(inputQuota.getText())) {
                    GUI.stopCheck = true;
                    c.checkQuota(Integer.parseInt(inputTime.getText()));
                }
            } else if (GUI.stopCheck && temp <= Integer.parseInt(inputQuota.getText())) {
                GUI.stopCheck = false;
                c.reset();
            }
            GUI.temp.setText(new DecimalFormat("#.######").format(temp));
        });
    }

    public static void updateMin(double min) {
        Platform.runLater(() -> {
            GUI.min.setText(new DecimalFormat("#.######").format(min));
        });
    }

    public static void updateMax(double max) {
        Platform.runLater(() -> {
            GUI.max.setText(new DecimalFormat("#.######").format(max));
        });
    }

    public static void alert() {
        c.stopRead();
        GUI.stopCheck = false;
        start.setDisable(false);
        stop.setDisable(true);
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Over quota!");
            alert.setContentText("Temp was over quota (" + inputQuota.getText() +"°C) for more than " + inputTime.getText() + "ms");

            alert.showAndWait();
        });
    }

    public class NumberTextField extends TextField {

        @Override
        public void replaceText(int start, int end, String text) {
            if (validate(text)) {
                super.replaceText(start, end, text);
            }
        }

        @Override
        public void replaceSelection(String text) {
            if (validate(text)) {
                super.replaceSelection(text);
            }
        }

        private boolean validate(String text) {
            return ("".equals(text) || text.matches("[0-9]"));
        }
    }
}
