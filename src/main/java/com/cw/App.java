package com.cw;

import com.cw.controller.AppController;
import com.cw.utils.PathUtils;
import com.cw.utils.Tool;
import com.cw.utils.file.FileOutputMode;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.cw.utils.logger.Logger;
import com.cw.utils.Worker;

import java.io.File;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        try {
            FXMLLoader loader = Tool.getFXMLLoader("AppGUI");
            Parent root = loader.load();

            Scene scene = new Scene(root, 650, 800);

            AppController controller = loader.getController();

            controller.init(stage);

            stage.getIcons().add(Tool.loadImage(Tool.getConfig("app.icon")));
            stage.setTitle(Tool.getConfig("app.name"));
            stage.setScene(scene);
            stage.setResizable(false);

            stage.setOnCloseRequest(we -> {
                Worker.shutdown();
                System.exit(0);
            });

            stage.show();

        } catch (Exception e) {
            Logger.error(e);
        }

    }

    @Override
    public void init() throws Exception {
        Logger.setLevel(Tool.getConfig("log.level"));

        File logFolder = new File(Tool.getConfig("log.path") + Tool.getConfig("app.name"));
        PathUtils.checkFolder(logFolder);

        Logger.enableFileOutput(logFolder, System.out, System.err, FileOutputMode.COMBINED);

        Logger.appInfo(Tool.getConfig("app.name"), Tool.getConfig("version.name"), Tool.getConfig("version.code"), Tool.getConfig("version.date"));
    }

    public static void main(String[] args) {
        launch(args);
    }

}