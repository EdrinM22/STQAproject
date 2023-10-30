package project.stqaprojectf;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

import java.util.Optional;

public class RedBlackTreeVisualization extends Application {
    private RedBlackTree rbTree;
    private Pane treePane;

    public RedBlackTreeVisualization() {
        rbTree = new RedBlackTree();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Red-Black Tree Visualization");

        treePane = new Pane();
        treePane.setPrefSize(800, 600);
        treePane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        VBox controlPane = new VBox();
        controlPane.setPadding(new Insets(10));
        controlPane.setSpacing(10);

        Label titleLabel = new Label("Red-Black Tree Visualization");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Button insertButton = new Button("Insert Node");
        insertButton.setOnAction(e -> insertNode());

        Button deleteButton = new Button("Delete Node");
        deleteButton.setOnAction(e -> deleteNode());

        Button searchButton = new Button("Search Node");
        searchButton.setOnAction(e -> searchNode());

        Button resetButton = new Button("Reset Tree");
        resetButton.setOnAction(e -> resetTree());

        controlPane.getChildren().addAll(titleLabel, insertButton, deleteButton, searchButton, resetButton);

        Circle rootNodeCircle = createCircle(400, 50, 30, Color.BLACK);
        Label rootNodeLabel = createLabel(400, 50, "NIL", Color.WHITE);
        treePane.getChildren().addAll(rootNodeCircle, rootNodeLabel);

        Scene scene = new Scene(new HBox(controlPane, treePane), 900, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public boolean containsNode(Node node, int data) {
        if (node == null) {
            return false;
        }
        if (data == node.data) {
            return true;
        }
        if (data < node.data) {
            return containsNode(node.left, data);
        } else {
            return containsNode(node.right, data);
        }
    }
    private void insertNode() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Insert Node");
        dialog.setHeaderText("Insert a node into the Red-Black Tree");
        dialog.setContentText("Node value:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(value -> {
            int data = Integer.parseInt(value);
            if (containsNode(rbTree.root, data)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Insert Node");
                alert.setHeaderText(null);
                alert.setContentText("Node with value " + data + " is already inserted in the tree.");
                alert.showAndWait();
            } else {
                rbTree.insert(data);
                updateTreeVisualization();
            }
        });
    }

    private void deleteNode() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete Node");
        dialog.setHeaderText("Delete a node from the Red-Black Tree");
        dialog.setContentText("Node value:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(value -> {
            int data = Integer.parseInt(value);
            Node nodeToDelete = rbTree.findNode(rbTree.root ,data);
            if (nodeToDelete == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Delete Node");
                alert.setHeaderText(null);
                alert.setContentText("Node with value " + data + " is not found in the tree.");
                alert.showAndWait();
            } else {
                rbTree.delete(data);
                updateTreeVisualization();
            }
        });
    }


    private void searchNode() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Search Node");
        dialog.setHeaderText("Search for a node in the Red-Black Tree");
        dialog.setContentText("Node value to search:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(value -> {
            int data = Integer.parseInt(value);
            Node node = rbTree.findNode(rbTree.root, data);
            if (node != null) {highlightNode(node);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search Result");
                alert.setHeaderText(null);
                alert.setContentText("Node with value " + data + " not found in the tree.");
                alert.showAndWait();
            }
        });
    }
    private void resetTree() {
        rbTree = new RedBlackTree();
        treePane.getChildren().clear();

        Circle rootNodeCircle = createCircle(400, 50, 30, Color.BLACK);
        Label rootNodeLabel = createLabel(400, 50, "NIL", Color.WHITE);
        treePane.getChildren().addAll(rootNodeCircle, rootNodeLabel);
    }
    private void highlightNode(Node node) {
        if (node != null) {

            setNodeColor(node, Color.YELLOW);

            updateTreeVisualization();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Result");
            alert.setHeaderText(null);
            alert.setContentText("Node not found in the tree.");
            alert.showAndWait();
        }
    }

    private void setNodeColor(Node node, Color color) {
        if (node != null) {
            node.color = color;
        }
    }


    private void updateTreeVisualization() {
        treePane.getChildren().clear();
        drawTree(rbTree.getRoot(), 400, 50, 200);
    }

    private void drawTree(Node node, double x, double y, double offsetX) {
        if (node != null) {
            Circle circle = createCircle(x, y, 20, node.color);
            Label label = createLabel(x, y, String.valueOf(node.data), Color.WHITE);
            treePane.getChildren().addAll(circle, label);

            if (node.left != null) {
                Line leftLine = createLine(x - 10, y + 20, x - offsetX, y + 70);
                treePane.getChildren().add(leftLine);
                drawTree(node.left, x - offsetX, y + 70, offsetX / 2);
            }

            if (node.right != null) {
                Line rightLine = createLine(x + 10, y + 20, x + offsetX, y + 70);
                treePane.getChildren().add(rightLine);
                drawTree(node.right, x + offsetX, y + 70, offsetX / 2);
            }
        }
    }

    private Circle createCircle(double centerX, double centerY, double radius, Color color) {
        Circle circle = new Circle(centerX, centerY, radius);
        circle.setFill(color);
        circle.setStroke(Color.BLACK);
        return circle;
    }

    private Label createLabel(double x, double y, String text, Color color) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        label.setLayoutX(x - (label.getWidth() / 2));
        label.setLayoutY(y - (label.getHeight() / 2));
        label.setTextFill(color);
        return label;
    }
    private Line createLine(double startX, double startY, double endX, double endY) {
        Line line = new Line(startX, startY, endX, endY);
        line.setStrokeWidth(2);
        return line;
    }
}
