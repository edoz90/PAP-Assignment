package pap.ass06.GOL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;

/**
 * @author edoardo
 */
public class Viewer extends Application {

    private static int rows;
    private static int cols;
    private static int core;
    private static GridPane gridpane;
    private static ControllerGOF c;
    private static Matrix m;
    private static Label status;

    public static void main(String[] args) {
        rows = Integer.parseInt(args[0]);
        cols = Integer.parseInt(args[1]);
        core = Integer.parseInt(args[2]);
        c = ControllerGOF.getInstance(rows, cols, core);
        m = new Matrix(rows, cols);
        launch(args);
    }

    public static void updateGrid(ArrayList<Cell> diff, int t) {
        Platform.runLater(() -> {
            Cell[] a = new Cell[diff.size()];
            // avoid concurrent modification with JavaFX threads
            diff.toArray(a);
            for (int i = 0; i < a.length; i++) {
                try {
                    getNodeFromGridPane(a[i].getRow(), a[i].getCol(), t);
                } catch (Exception e) {
                }
            }
        });

    }

    private static synchronized void getNodeFromGridPane(int row, int column, int turn) throws Exception {
        // for small grid could be better 'stream' than 'parallelStream'
        gridpane.getChildren()
                .parallelStream()
                .filter(node -> GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column)
                .findFirst().ifPresent(b -> {
            if (m.getState(row, column, turn)) {
                b.getStyleClass().remove("dead");
                b.getStyleClass().add("alive");
            } else {
                b.getStyleClass().remove("alive");
                b.getStyleClass().add("dead");
            }
        });
    }

    @Override
    public void stop() {
        System.out.println("CIAO!");
        c.stopGame();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game of Life");
        primaryStage.setResizable(false);
        Group root = new Group();
        Scene scene = new Scene(root, 22.39 * this.cols, 32.65 * this.rows, Color.web("#2C3E50"));
        String cssURL = this.getClass().getResource("style.css").toExternalForm();
        scene.getStylesheets().add(cssURL);

        status = new Label("STOP");
        Button start = new Button("Start");
        Button stop = new Button("Stop");
        stop.setDisable(true);
        start.setOnMouseClicked((MouseEvent) -> {
            stop.setDisable(false);
            start.setDisable(true);
            status.setText("Running...");
            c.setMatrix(m);
            c.startGame();
        });
        stop.setOnMouseClicked((MouseEvent) -> {
            start.setDisable(false);
            stop.setDisable(true);
            status.setText("STOP");
            c.stopGame();
        });
        HBox hbox = new HBox(10, start, stop, status);
        hbox.setAlignment(Pos.TOP_LEFT);
        hbox.setPadding(new Insets(10, 10, 10, 10));

        gridpane = new GridPane();
        GridPane.setMargin(gridpane, new Insets(50, 5, 5, 5));
        gridpane.setPadding(new Insets(50, 5, 5, 5));
        gridpane.setStyle("-fx-hgap: 2; -fx-vgap: 2;");
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < cols; k++) {
                addButton(gridpane, i, k);
            }
        }

        root.getChildren().addAll(gridpane, hbox);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest((WindowEvent we) -> {
        });
    }

    private void addButton(GridPane grid, int x, int y) {
        final Button temp = new Button();
        temp.setPrefSize(20, 20);
        temp.setOnMouseClicked((MouseEvent t) -> {
            if (m.getState(x, y, 0)) {
                temp.getStyleClass().remove("alive");
                temp.getStyleClass().add("dead");
                m.setState(x, y, false, 0);
            } else {
                temp.getStyleClass().remove("dead");
                temp.getStyleClass().add("alive");
                m.setState(x, y, true, 0);
            }
        });
        if (m.getState(x, y, 0)) {
            temp.getStyleClass().remove("dead");
            temp.getStyleClass().add("alive");
        }
        grid.add(temp, y, x);
    }

    public void updateLabel() {
        System.out.println("LIVE: " + c.getLive());
    }
}
