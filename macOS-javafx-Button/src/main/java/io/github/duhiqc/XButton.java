package io.github.duhiqc;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class XButton extends Pane
{
    Circle exit = new Circle();
    Circle min = new Circle();
    Circle max = new Circle();
    Circle exitClick = new Circle();
    Circle minClick = new Circle();
    Circle maxClick = new Circle();
    Image image1 = new Image("image.png");
    Image image2 = new Image("exit.png");
    Image image3 = new Image("min.png");
    Image image4 = new Image("max.png");
    ImageView imageView = new ImageView();
    boolean focused;
    boolean isEnabled = false;

    public XButton(int x, int y, Stage stage)
    {
        x += 8;
        y += 8;
        this.setPrefSize(0, 0);
        int newX = x + 6;
        int newY = y + 6;
        exit.setFill(Color.web("#ff5048"));
        exit.setRadius(6);
        exit.setCenterX(newX);
        exit.setCenterY(newY);
        min.setFill(Color.web("#feb41e"));
        min.setRadius(6);
        min.setCenterX(newX + 20);
        min.setCenterY(newY);
        max.setFill(Color.web("#18c230"));
        max.setRadius(6);
        max.setCenterX(newX + 40);
        max.setCenterY(newY);
        imageView.setX(newX - 16);
        imageView.setY(newY - 16);
        this.getChildren().addAll(exit, min, max, imageView);
        exitClick.setRadius(6);
        minClick.setRadius(6);
        maxClick.setRadius(6);
        exitClick.setCenterX(exit.getCenterX());
        exitClick.setCenterY(exit.getCenterY());
        minClick.setCenterX(min.getCenterX());
        minClick.setCenterY(exit.getCenterY());
        maxClick.setCenterX(max.getCenterX());
        maxClick.setCenterY(exit.getCenterY());
        exitClick.setFill(Color.TRANSPARENT);
        minClick.setFill(Color.TRANSPARENT);
        maxClick.setFill(Color.TRANSPARENT);
        this.getChildren().addAll(exitClick, minClick, maxClick);
        new Thread(() -> {
            this.setOnMouseEntered(mouseEvent -> isEnabled = true);
            this.setOnMouseExited(mouseEvent -> isEnabled = false);
            addLister(stage);
            stage.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
                focused = t1;
                if (!t1)
                {
                    imageView.setImage(null);
                    exit.setFill(Color.rgb(230, 230, 230));
                    min.setFill(Color.rgb(230, 230, 230));
                    max.setFill(Color.rgb(230, 230, 230));
                } else {
                    exit.setFill(Color.web("#ff5048"));
                    min.setFill(Color.web("#feb41e"));
                    max.setFill(Color.web("#18c230"));
                }
            });
        }).start();
    }

    private void addLister(Stage stage)
    {
        fade(exitClick);
        exitClick.setOnMousePressed(mouseEvent -> new Thread(() -> {
            try {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    imageView.setImage(image2);
                    exit.setFill(Color.web("#ff8179"));
                    Thread.sleep(74);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());
        exitClick.setOnMouseReleased(mouseEvent ->
        {
            new Thread(() -> {
                try {
                    if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                        exit.setFill(Color.web("#ff5048"));
                        Thread.sleep(5);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            if (isEnabled && mouseEvent.getButton() == MouseButton.PRIMARY)
            {
                Platform.exit();
            }
        });
        fade(minClick);
        minClick.setOnMousePressed(mouseEvent -> new Thread(() -> {
            try {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    imageView.setImage(image3);
                    min.setFill(Color.web("#feb41e"));
                    imageView.setImage(null);
                    Thread.sleep(8);
                    min.setFill(Color.web("#ffe74d"));
                    Thread.sleep(18);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());
        minClick.setOnMouseReleased(mouseEvent -> {
            min.setFill(Color.web("#feb41e"));
            new Thread(() -> {
                try {
                    if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                        Thread.sleep(8);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
            if (isEnabled && mouseEvent.getButton() == MouseButton.PRIMARY)
            {
                stage.setIconified(true);
            }
        });
        fade(maxClick);
        maxClick.setOnMousePressed(mouseEvent -> {
            new Thread(() -> {
                try {
                    if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                        imageView.setImage(image4);
                        max.setFill(Color.web("#47f560"));
                        Thread.sleep(120);
                        imageView.setImage(image1);
                        max.setFill(Color.web("#18c230"));
                        Thread.sleep(8);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            if (isEnabled && mouseEvent.getButton() == MouseButton.PRIMARY)
            {
                stage.setFullScreen(true);
            }
        }
        );
    }

    private void fade(Circle click) {
        click.setOnMouseEntered(mouseEvent ->
        {
            exit.setFill(Color.web("#ff5048"));
            min.setFill(Color.web("#feb41e"));
            max.setFill(Color.web("#18c230"));
            imageView.setImage(image1);
        });
        click.setOnMouseExited(mouseEvent ->
        {
            if (!focused)
            {
                exit.setFill(Color.rgb(230, 230, 230));
                min.setFill(Color.rgb(230, 230, 230));
                max.setFill(Color.rgb(230, 230, 230));
            }
            imageView.setImage(null);
        });
    }
}
