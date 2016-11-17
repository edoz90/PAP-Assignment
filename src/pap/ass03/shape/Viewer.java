package pap.ass03.shape;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Edoardo Rosa
 */
public class Viewer extends Application implements ShapeViewer {

    private List<Shape> shapes; // List for the shapes
    private List<Node> draw; // List for the shapes to draw and add to scene
    private Label result;
    private Scene scene;
    private Group root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        shapes = new ArrayList<>();
        draw = new ArrayList<>();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        primaryStage.setTitle("Shape Viewer");

        root = new Group();

        result = new Label();
        result.setMinWidth(bounds.getWidth());
        result.setAlignment(Pos.TOP_RIGHT);

        scene = new Scene(root, bounds.getWidth(), bounds.getHeight(), Color.web("#2C3E50"));
        String cssURL = this.getClass().getResource("style.css").toExternalForm();
        scene.getStylesheets().add(cssURL);

        Button buttonCircle = new Button("Circle");
        buttonCircle.setOnAction((ActionEvent e) -> {
            String circle = getCircle();
            if (circle.length() > 1) {
                String center = circle.split(";")[0].replace("Center= ", "").trim();
                int radius = Integer.parseInt(circle.split(";")[1].replace("Radius= ", "").trim());
                int x = Integer.parseInt(center.split(",")[0].trim());
                int y = Integer.parseInt(center.split(",")[1].trim());

                Circle cShape = new Circle(new P2d(x, y), radius);
                shapes.add(cShape);
                update(shapes);
            }
        });

        Button buttonRect = new Button("Rectangle");
        buttonRect.setOnAction((ActionEvent e) -> {
            String rect = getRect();
            if (rect.length() > 1) {
                String upperLeft = rect.split(";")[0].replace("UpperLeft= ", "").trim();
                String bottomRight = rect.split(";")[1].replace("BottomRight= ", "").trim();
                int xA = Integer.parseInt(upperLeft.split(",")[0].trim());
                int yA = Integer.parseInt(upperLeft.split(",")[1].trim());
                int xB = Integer.parseInt(bottomRight.split(",")[0].trim());
                int yB = Integer.parseInt(bottomRight.split(",")[1].trim());

                Rect rShape = new Rect(new P2d(xA, yA), new P2d(xB, yB));
                shapes.add(rShape);
                update(shapes);
            }
        });

        Button buttonLine = new Button("Line");
        buttonLine.setOnAction((ActionEvent e) -> {
            String res = getLine();
            if (res.length() > 1) {
                String a = res.split(";")[0].replace("A= ", "").trim();
                String b = res.split(";")[1].replace("B= ", "").trim();
                int xA = Integer.parseInt(a.split(",")[0].trim());
                int yA = Integer.parseInt(a.split(",")[1].trim());
                int xB = Integer.parseInt(b.split(",")[0].trim());
                int yB = Integer.parseInt(b.split(",")[1].trim());

                Line lShape = new Line(new P2d(xA, yA), new P2d(xB, yB));
                shapes.add(lShape);
                update(shapes);
            }
        });

        Button buttonMove = new Button("MoveShapes");
        buttonMove.setOnAction((ActionEvent e) -> {
            String p = getPoint();
            if (p.length() > 1) {
                int x = Integer.parseInt(p.split(";")[0].replace("X= ", "").trim());
                int y = Integer.parseInt(p.split(";")[1].replace("Y= ", "").trim());
                V2d move = new V2d(x, y);
                shapes = Utils.moveShapes(shapes, move);
                update(shapes);
            }
        });

        Button buttonBBox = new Button("inBBox");
        buttonBBox.setOnAction((ActionEvent e) -> {
            String b = getBBox();
            if (b.length() > 1) {
                String upperLeft = b.split(";")[0].replace("UpperLeft= ", "").trim();
                String bottomRight = b.split(";")[1].replace("BottomRight= ", "").trim();
                int xA = Integer.parseInt(upperLeft.split(",")[0].trim());
                int yA = Integer.parseInt(upperLeft.split(",")[1].trim());
                int xB = Integer.parseInt(bottomRight.split(",")[0].trim());
                int yB = Integer.parseInt(bottomRight.split(",")[1].trim());
                BBox box = new BBox(new P2d(xA, yA), new P2d(xB, yB));
                List<Shape> temp = Utils.inBBox(shapes, box);
                update(temp);
                root.getChildren().add(drawBBox(xA, yA, xB, yB));
            }
        });

        Button buttonMaxPerim = new Button("maxPerim");
        buttonMaxPerim.setOnAction((ActionEvent e) -> {
            Shape perimShape = Utils.shapeWithMaxPerim(shapes);
            double perim = Utils.maxPerim(shapes);
            result.setText("Max Perim: " + perim);
            List<Shape> temp = Arrays.asList(perimShape);
            update(temp);
            root.getChildren().add(result);
        });

        Button buttonContains = new Button("Contains");
        buttonContains.setOnAction((ActionEvent e) -> {
            String p = getPoint();
            if (p.length() > 1) {
                int x = Integer.parseInt(p.split(";")[0].replace("X= ", "").trim());
                int y = Integer.parseInt(p.split(";")[1].replace("Y= ", "").trim());
                P2d cont = new P2d(x, y);
                boolean res = Utils.contains(shapes, cont);
                result.setText("P2d(" + x + ", " + y + "): " + res);
                root.getChildren().add(result);
            }
        });

        Button buttonGetContaining = new Button("GetContaining");
        buttonGetContaining.setOnAction((ActionEvent e) -> {
            String p = getPoint();
            if (p.length() > 1) {
                int x = Integer.parseInt(p.split(";")[0].replace("X= ", "").trim());
                int y = Integer.parseInt(p.split(";")[1].replace("Y= ", "").trim());
                P2d cont = new P2d(x, y);
                List<Shape> temp = Utils.getContaining(shapes, cont);
                update(temp);
            }
        });

        Button buttonOrderX = new Button("sortShapeByX");
        buttonOrderX.setOnAction((ActionEvent e) -> {
            String res = "";
            for (Shape s : Utils.sortShapeByX(shapes)) {
                res += s.printShape() + System.getProperty("line.separator");
            }
            result.setText(res);
            root.getChildren().add(result);
        });

        // This button redraw all shapes
        Button buttonReset = new Button("Reset");
        Tooltip t = new Tooltip();
        t.setText("Redraw all shapes");
        buttonReset.setTooltip(t);
        buttonReset.setOnMouseEntered((MouseEvent event) -> {
            Point2D p = buttonReset.localToScreen(buttonReset.getLayoutBounds().getMinX(), buttonReset.getLayoutBounds().getMinY());
            t.show(buttonReset, p.getX() - 30, p.getY() - 30);
        });
        buttonReset.setOnMouseExited((MouseEvent event) -> {
            t.hide();
        });
        buttonReset.setOnAction((ActionEvent e) -> {
            update(shapes);
            root.getChildren().remove(result);
        });

        HBox hbox = new HBox(10, buttonLine, buttonCircle, buttonRect, buttonMove, buttonBBox,
                buttonMaxPerim, buttonContains, buttonGetContaining, buttonOrderX, buttonReset);
        hbox.setAlignment(Pos.BOTTOM_LEFT);
        hbox.setMinHeight(bounds.getHeight());
        hbox.setPadding(new Insets(15, 120, 35, 12));

        root.getChildren().addAll(hbox);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    @Override
    public void update(List<Shape> shapes) {
        root.getChildren().removeAll(draw);
        draw = new ArrayList<>();
        shapes.stream().forEach((s) -> {
            if (s instanceof pap.ass03.shape.Line) {
                P2d a = ((pap.ass03.shape.Line) s).getA();
                P2d b = ((pap.ass03.shape.Line) s).getB();
                draw.add(drawLine(a.getX(), a.getY(), b.getX(), b.getY()));
            } else if (s instanceof pap.ass03.shape.Circle) {
                P2d c = ((pap.ass03.shape.Circle) s).getC();
                int r = ((pap.ass03.shape.Circle) s).getR();
                draw.add(drawCircle(c.getX(), c.getY(), r));
            } else if (s instanceof pap.ass03.shape.Rect) {
                P2d a = ((pap.ass03.shape.Rect) s).getA();
                P2d b = ((pap.ass03.shape.Rect) s).getB();
                draw.add(drawRect(a.getX(), a.getY(), b.getX(), b.getY()));
            }
        });
        root.getChildren().addAll(draw);
    }

    private String getCircle() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Circle (x,y) r");
        dialog.setHeaderText("Please insert coordinates and radius");

        ButtonType insertButton = new ButtonType("Insert", ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(insertButton, cancelButton);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 40, 40));

        TextField x = new TextField();
        x.setPromptText("X");
        x.setPrefWidth(50);
        TextField y = new TextField();
        y.setPromptText("Y");
        y.setPrefWidth(50);
        TextField r = new TextField();
        r.setPromptText("R");
        r.setPrefWidth(50);

        grid.add(new Label("X:"), 0, 0);
        grid.add(x, 1, 0);
        grid.add(new Label("Y:"), 2, 0);
        grid.add(y, 3, 0);
        grid.add(new Label("Radius:"), 1, 2);
        grid.add(r, 3, 2);

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> x.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == cancelButton) {
                dialog.close();
            }
            if (dialogButton == insertButton && checkInt(x.getText()) && checkInt(y.getText()) && checkInt(r.getText())) {
                return new Pair(x.getText() + "," + y.getText(), r.getText());
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Enter a number!");

                alert.showAndWait();
                alert.close();
            }
            return null;
        });

        Optional<Pair<String, String>> resultCircle = dialog.showAndWait();

        if (resultCircle.isPresent()) {
            String res = "Center= " + resultCircle.get().getKey() + "; Radius= " + resultCircle.get().getValue();
            System.out.println(res);
            return res;
        }
        return "";
    }

    private String getRect() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Rect (xA,yA) (xB,yB)");
        dialog.setHeaderText("Please insert coordinates for the upper left and bottom right points");

        ButtonType insertButton = new ButtonType("Insert", ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(insertButton, cancelButton);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 40, 40));

        TextField x1 = new TextField();
        x1.setPromptText("X Upper Left");
        x1.setPrefWidth(100);
        TextField y1 = new TextField();
        y1.setPromptText("Y Upper Left");
        y1.setPrefWidth(100);
        TextField x2 = new TextField();
        x2.setPromptText("X Bottom Right");
        x2.setPrefWidth(100);
        TextField y2 = new TextField();
        y2.setPromptText("Y Bottom Right");
        y2.setPrefWidth(100);

        grid.add(new Label("Xa:"), 0, 0);
        grid.add(x1, 1, 0);
        grid.add(new Label("Ya:"), 2, 0);
        grid.add(y1, 3, 0);
        grid.add(new Label("Xb:"), 0, 2);
        grid.add(x2, 1, 2);
        grid.add(new Label("Yb:"), 2, 2);
        grid.add(y2, 3, 2);

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> x1.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == cancelButton) {
                dialog.close();
            } else if (dialogButton == insertButton && checkInt(x1.getText()) && checkInt(y1.getText()) && checkInt(x2.getText()) && checkInt(y2.getText())) {
                return new Pair(x1.getText() + "," + y1.getText(), x2.getText() + "," + y2.getText());
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Enter a number!");

                alert.showAndWait();
                alert.close();
            }
            return null;
        });

        Optional<Pair<String, String>> resultRect = dialog.showAndWait();

        if (resultRect.isPresent()) {
            String res = "UpperLeft= " + resultRect.get().getKey() + "; BottomRight= " + resultRect.get().getValue();
            System.out.println(res);
            return res;
        }
        return "";
    }

    private String getLine() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Line (xA,yA) (xB,yB)");
        dialog.setHeaderText("Please insert coordinates for the two points");

        ButtonType insertButton = new ButtonType("Insert", ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(insertButton, cancelButton);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 40, 40));

        TextField x1 = new TextField();
        x1.setPromptText("Xa");
        x1.setPrefWidth(50);
        TextField y1 = new TextField();
        y1.setPromptText("Ya");
        y1.setPrefWidth(50);
        TextField x2 = new TextField();
        x2.setPromptText("Xb");
        x2.setPrefWidth(50);
        TextField y2 = new TextField();
        y2.setPromptText("Yb");
        y2.setPrefWidth(50);

        grid.add(new Label("Xa:"), 0, 0);
        grid.add(x1, 1, 0);
        grid.add(new Label("Ya:"), 2, 0);
        grid.add(y1, 3, 0);
        grid.add(new Label("Xb:"), 0, 2);
        grid.add(x2, 1, 2);
        grid.add(new Label("Yb:"), 2, 2);
        grid.add(y2, 3, 2);

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> x1.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == cancelButton) {
                dialog.close();
            } else if (dialogButton == insertButton && checkInt(x1.getText()) && checkInt(y1.getText()) && checkInt(x2.getText()) && checkInt(y2.getText())) {
                return new Pair(x1.getText() + "," + y1.getText(), x2.getText() + "," + y2.getText());
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Enter a number!");

                alert.showAndWait();
                alert.close();
            }
            return null;
        });

        Optional<Pair<String, String>> resultLine = dialog.showAndWait();

        if (resultLine.isPresent()) {
            String res = "A= " + resultLine.get().getKey() + "; B= " + resultLine.get().getValue();
            System.out.println(res);
            return res;
        }
        return "";
    }

    private String getBBox() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("BBox (xA,yA) (xB,yB)");
        dialog.setHeaderText("Please insert coordinates for the upper left and bottom right points");

        ButtonType insertButton = new ButtonType("Insert", ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(insertButton, cancelButton);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 40, 40));

        TextField x1 = new TextField();
        x1.setPromptText("Xa");
        x1.setPrefWidth(50);
        TextField y1 = new TextField();
        y1.setPromptText("Ya");
        y1.setPrefWidth(50);
        TextField x2 = new TextField();
        x2.setPromptText("Xb");
        x2.setPrefWidth(50);
        TextField y2 = new TextField();
        y2.setPromptText("Yb");
        y2.setPrefWidth(50);

        grid.add(new Label("Xa:"), 0, 0);
        grid.add(x1, 1, 0);
        grid.add(new Label("Ya:"), 2, 0);
        grid.add(y1, 3, 0);
        grid.add(new Label("Xb:"), 0, 2);
        grid.add(x2, 1, 2);
        grid.add(new Label("Yb:"), 2, 2);
        grid.add(y2, 3, 2);

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> x1.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == cancelButton) {
                dialog.close();
            } else if (dialogButton == insertButton && checkInt(x1.getText()) && checkInt(y1.getText()) && checkInt(x2.getText()) && checkInt(y2.getText())) {
                return new Pair(x1.getText() + "," + y1.getText(), x2.getText() + "," + y2.getText());
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Enter a number!");

                alert.showAndWait();
                alert.close();
            }
            return null;
        });

        Optional<Pair<String, String>> resultBBox = dialog.showAndWait();

        if (resultBBox.isPresent()) {
            String res = "UpperLeft= " + resultBBox.get().getKey() + "; BottomRight= " + resultBBox.get().getValue();
            System.out.println(res);
            return res;
        }

        return "";
    }

    private String getPoint() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("P2d (X, Y)");
        dialog.setHeaderText("Please insert coordinates of the point");

        ButtonType insertButton = new ButtonType("Insert", ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(insertButton, cancelButton);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 40, 40));

        TextField x1 = new TextField();
        x1.setPromptText("X");
        x1.setPrefWidth(50);
        TextField y1 = new TextField();
        y1.setPromptText("Y");
        y1.setPrefWidth(50);

        grid.add(new Label("X:"), 0, 0);
        grid.add(x1, 1, 0);
        grid.add(new Label("Y:"), 2, 0);
        grid.add(y1, 3, 0);

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> x1.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == cancelButton) {
                dialog.close();
            } else if (dialogButton == insertButton && checkInt(x1.getText()) && checkInt(y1.getText())) {
                return new Pair(x1.getText(), y1.getText());
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Enter a number!");

                alert.showAndWait();
                alert.close();
            }
            return null;
        });

        Optional<Pair<String, String>> resultPoint = dialog.showAndWait();

        if (resultPoint.isPresent()) {
            String res = "X= " + resultPoint.get().getKey() + "; Y= " + resultPoint.get().getValue();
            System.out.println(res);
            return res;
        }

        return "";
    }

    private boolean checkInt(String s) {
        String regex = "[0-9]+";
        return s.matches(regex);
    }

    private Node drawRect(int xA, int yA, int xB, int yB) {
        Rectangle r = new Rectangle(xA, yA,
                P2d.distance(new P2d(xA, yA), new P2d(xB, yA)),
                P2d.distance(new P2d(xA, yA), new P2d(xA, yB)));
        r.setFill(Color.web("#19B5FE"));
        r.setOnMouseClicked((MouseEvent t) -> {
            if (r.getFill().equals(Color.web("#19B5FE"))) {
                r.setFill(Color.web("#03A678"));
            } else {
                r.setFill(Color.web("#19B5FE"));
            }
        });
        return r;
    }

    private Node drawCircle(int x, int y, int r) {
        javafx.scene.shape.Circle c = new javafx.scene.shape.Circle(x, y, r);
        c.setFill(Color.web("#F9BF3B"));
        c.setOnMouseClicked((MouseEvent t) -> {
            if (c.getFill().equals(Color.web("#F9BF3B"))) {
                c.setFill(Color.web("#ABB7B7"));
            } else {
                c.setFill(Color.web("#F9BF3B"));
            }
        });
        return c;
    }

    private Node drawLine(int xA, int yA, int xB, int yB) {
        javafx.scene.shape.Line l = new javafx.scene.shape.Line(xA, yA, xB, yB);
        l.setStroke(Color.web("#F64747"));
        l.setStrokeWidth(3);
        l.setOnMouseClicked((MouseEvent t) -> {
            if (l.getStroke().equals(Color.web("#F64747"))) {
                l.setStroke(Color.web("#E4F1FE"));
            } else {
                l.setStroke(Color.web("#F64747"));
            }
        });
        return l;
    }

    private Node drawBBox(int xA, int yA, int xB, int yB) {
        Rectangle r = new Rectangle(xA, yA,
                P2d.distance(new P2d(xA, yA), new P2d(xB, yA)),
                P2d.distance(new P2d(xA, yA), new P2d(xA, yB)));
        r.setFill(Color.TRANSPARENT);
        r.setStroke(Color.RED);
        r.setStrokeWidth(3);
        r.getStrokeDashArray().addAll(3.0, 7.0, 3.0, 7.0);
        return r;
    }
}
