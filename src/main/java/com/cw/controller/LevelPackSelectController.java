package com.cw.controller;

import com.cw.controller.cells.LevelPackCell;
import com.cw.game.Game;
import com.cw.game.levels.LevelPack;
import com.cw.game.levels.LevelPackHandler;
import com.cw.utils.Colors;
import com.cw.utils.file.Downloader;
import com.cw.utils.Tool;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kuusisto.tinysound.TinySound;
import com.cw.utils.logger.Logger;
import com.cw.utils.AlertGenerator;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

public class LevelPackSelectController implements CommandLineAvailabale {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private ScrollPane pane;
    @FXML
    private Label labelLevelPack;
    @FXML
    private Button buttonBack;
    @FXML
    private Button downloadLevelpackButton;

    public Stage stage;
    public Image icon = Tool.loadImage("icon.png");
    public final ResourceBundle bundle = Tool.getBundle();
    public AppController appController;
    public Game game;

    public void init(Stage stage, AppController controller, Game game) {
        this.stage = stage;
        this.appController = controller;
        this.game = game;

        reload();

        pane.setHbarPolicy(ScrollBarPolicy.NEVER);
        pane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

        // pane.setStyle("-fx-background-color:transparent;");

        mainPane.setOnKeyReleased(event -> {
            if (event.getCode().toString().equals(controller.bundle.getObject("shortcut.debug.console"))) {
                showCommandLine();
                Logger.info("openend debug console");
                event.consume();
            }

            if (event.getCode().toString().equals("ESCAPE")) {
                back();
                event.consume();
            }
        });

        downloadLevelpackButton.setDisable(true);

        downloadLevelpackButton.setOnAction(event -> {
            TextInputDialog textInputDialog = new TextInputDialog();
            textInputDialog.setHeaderText(null);
            Stage dialogStage = (Stage) textInputDialog.getDialogPane().getScene().getWindow();
            dialogStage.getIcons().add(icon);
            dialogStage.initOwner(stage);
            textInputDialog.setContentText("URL of the raw levelpack");
            Optional<String> result = textInputDialog.showAndWait();
            if (result.isPresent()) {
                new Downloader().download(result.get());
                reload();
            }

        });

        stage.setOnCloseRequest(event -> {
            // TODO achievementhandling

            TinySound.shutdown();
            Platform.exit();
            System.exit(0);
        });

        mainPane.setStyle("-fx-base: " + Colors.BACKGROUND);
    }

    public void reload() {
        CustomListView<LevelPack> listView = new CustomListView<LevelPack>(FXCollections.observableList(LevelPackHandler.getAllLevelPacks()));

        listView.setCellFactory(param -> new LevelPackCell(pane.getMaxWidth() - 14));
        listView.setStyle("-fx-background-color: transparent");

        listView.setHScrollBarEnabled(false);

        listView.prefWidthProperty().bind(pane.maxWidthProperty());
        listView.prefHeightProperty().bind(pane.maxHeightProperty().subtract(10));

        listView.setOnMouseClicked(event -> {
            LevelPack selectedPack = listView.getSelectionModel().getSelectedItem();
            if (selectedPack != null) {
                listView.getSelectionModel().clearSelection();
                game.setLevelPack(selectedPack);

                try {
                    FXMLLoader fxmlLoader = Tool.getFXMLLoader("LevelSelectGUI");

                    Parent root = (Parent) fxmlLoader.load();
                    Stage newStage = new Stage();
                    newStage.setScene(new Scene(root, 650, 800));
                    newStage.getScene().getStylesheets().add("css/style.css");
                    newStage.setTitle(Tool.getConfig("app.name") + " - Level Select");
                    newStage.initOwner(stage);

                    newStage.getIcons().add(icon);
                    LevelSelectController newController = fxmlLoader.getController();
                    newController.init(newStage, getAppController(), game);

                    newStage.initModality(Modality.NONE);
                    newStage.setResizable(false);
                    stage.hide();
                    newStage.show();
                } catch (IOException e1) {
                    Logger.error(e1);
                }
            }
        });

        pane.setContent(listView);
    }

    private LevelPackSelectController getAppController() {
        return this;
    }

    public void back() {
        stage.close();
        appController.stage.show();
    }

    @Override
    public void showCommandLine() {
        try {
            appController.getCommandLine().showCommandLine("Debug Console", 400, 250, 400, 200, -1, -1, true);
        } catch (IOException e) {
            AlertGenerator.showAlert(AlertType.ERROR, "Error", "", "An error occurred while opening the debug console.\n\nDetails:\n" + e.getMessage(), icon, stage, null, false);
            Logger.error(e);
        }
    }
}