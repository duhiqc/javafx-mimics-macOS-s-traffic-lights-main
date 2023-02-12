package io.github.duhiqc;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainFX extends Application
{
    public static int width, height;
    Pane root = new Pane();
    HBox hBox = new HBox();
    Scene scene;
    public static Stage stage;

    public MainFX()
    {
        width = 300;
        height = 300;
        scene = new Scene(hBox, width, height);
        hBox.getChildren().add(root);
        scene.setFill(Color.TRANSPARENT);
    }

    @Override
    public void start(Stage stage)
    {
        MainFX.stage = stage;
        stage.initStyle(StageStyle.TRANSPARENT);
        root.getChildren().add(new XButton(8, 8, stage));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
