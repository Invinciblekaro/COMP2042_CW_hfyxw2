package com.cw.controller;

import com.cw.commandLine.CommandLine;
import com.cw.commandLine.commands.CommandBundle;
import com.cw.game.Game;
import com.cw.game.settings.Settings;
import com.cw.utils.Colors;
import com.cw.utils.Tool;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import kuusisto.tinysound.TinySound;
import com.cw.utils.logger.Logger;
import com.cw.utils.AlertGenerator;

import java.io.IOException;
import java.util.*;

/**
 * @author Wang_xubin
 * @date 2021/11/22 11:48 下午
 */
public class AppController implements CommandLineAvailabale {


    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button buttonLevelSelect;
    @FXML
    private Button buttonSettings;
    @FXML
    private Button buttonHighScoreList;
    @FXML
    private Button buttonAbout;

    public Stage stage;
    public Image icon = Tool.loadImage("icon.png");
    public static final ResourceBundle bundle = Tool.getBundle();
    private Game game;
    private CommandLine cmd;

    public void init(Stage stage) {
        this.stage = stage;
        game = new Game();

        cmd = new CommandLine(null, icon, ResourceBundle.getBundle("_en", Locale.ENGLISH), new CommandBundle(game));

        mainPane.setOnKeyReleased(event -> {
            if (event.getCode().toString().equals(bundle.getObject("shortcut.debug.console"))) {
                showCommandLine();
                Logger.info("opened debug console");
                event.consume();
            }
        });

        stage.setOnCloseRequest(event -> {
            TinySound.shutdown();
            Platform.exit();
            System.exit(0);
        });

        mainPane.setStyle("-fx-base: " + Colors.BACKGROUND + ";");
        buttonLevelSelect.setStyle("-fx-base: " + Colors.BUTTON);
        buttonSettings.setStyle("-fx-base: " + Colors.BUTTON);
        // buttonHighScoreList.setStyle("-fx-base: " + Colors.BUTTON);
        buttonAbout.setStyle("-fx-base: " + Colors.BUTTON);

        //TODO re-activate
        // buttonHighScoreList.setDisable(true);

        Logger.info("successfully started");
    }

    public void showLevelSelect() {

        Settings settings = game.getSettings();

        if (Objects.equals(settings.getUsername(), "")) {
            TextInputDialog dialog = new TextInputDialog(Tool.getConfig("default.username"));
            dialog.setTitle("Set Username");
            dialog.setHeaderText("You should have a username!");
            dialog.setContentText("Please enter your username:");

            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                System.out.println("Your name: " + result.get());
            }

            // The Java 8 way to get the response value (with lambda expression).
            result.ifPresent(name -> {
                settings.setUsername(name);
                settings.save();
            });

        } else {
            try {

                FXMLLoader fxmlLoader = Tool.getFXMLLoader("LevelPackSelectGUI");
                Parent root = (Parent) fxmlLoader.load();
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root, 650, 800));
                newStage.getScene().getStylesheets().add("css/style.css");
                newStage.setTitle(Tool.getConfig("app.name") + " - Levelpack Select");
                newStage.initOwner(stage);

                newStage.getIcons().add(icon);
                LevelPackSelectController newController = fxmlLoader.getController();
                newController.init(newStage, this, game);

                newStage.initModality(Modality.NONE);
                newStage.setResizable(false);
                stage.hide();
                newStage.show();
            } catch (IOException e1) {
                Logger.error(e1);
            }
        }

    }

    public void showSettings() {
        try {
            FXMLLoader fxmlLoader = Tool.getFXMLLoader("SettingsGUI");

            Parent root = (Parent) fxmlLoader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root, 650, 800));
            newStage.setTitle(Tool.getConfig("app.name") + " - Settings");
            newStage.initOwner(stage);

            newStage.getIcons().add(icon);
            SettingsController newController = fxmlLoader.getController();
            newController.init(newStage, this, game);

            newStage.initModality(Modality.NONE);
            newStage.setResizable(false);
            stage.hide();
            newStage.show();
        } catch (IOException e1) {
            Logger.error(e1);
        }
    }

    public void showHighScoreList() {
        try {
            FXMLLoader fxmlLoader = Tool.getFXMLLoader("HighScoreListGUI");

            Parent root = (Parent) fxmlLoader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root, 650, 800));
            newStage.setTitle(Tool.getConfig("app.name") + " - High Score List");
            newStage.initOwner(stage);

            newStage.getIcons().add(icon);
            HighScoreListController newController = fxmlLoader.getController();
            newController.init(newStage, this, game);

            newStage.initModality(Modality.NONE);
            newStage.setResizable(false);
            // stage.hide();
            newStage.show();
        } catch (IOException e1) {
            Logger.error(e1);
        }
    }

    @Override
    public void showCommandLine() {
        try {
            cmd.showCommandLine("Debug Console", 400, 250, 400, 200, -1, -1, true);
        } catch (IOException e) {
            AlertGenerator.showAlert(Alert.AlertType.ERROR, "Error", "", "An error occurred while opening the debug console.\n\nDetails:\n" + e.getMessage(), icon, stage, null, false);
            Logger.error(e);
        }
    }

    public CommandLine getCommandLine() {
        return cmd;
    }

    public void about() {
        ArrayList<String> creditLines = new ArrayList<>();
        for (String currentLine : Tool.getConfig("credits").split("\n")) {
            creditLines.add(currentLine);
        }

        AlertGenerator.showAboutAlertWithCredits(Tool.getConfig("app.name"), Tool.getConfig("version.name"), Tool.getConfig("version.code"), Tool.getConfig("version.date"), Tool.getConfig("author"), creditLines, icon, stage, null, false);
    }

}
