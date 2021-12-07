package com.cw.controller;

import com.cw.game.Game;
import com.cw.game.settings.GameSize;
import com.cw.controller.cells.ComboBoxLanguageCell;
import com.cw.controller.cells.ComboBoxResolutionCell;
import com.cw.utils.Colors;
import com.cw.utils.Tool;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.cw.utils.logger.Logger;
import com.cw.utils.AlertGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SettingsController implements CommandLineAvailabale {

    @FXML
    public TextField textUsername;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label labelLevelPack;
    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonSave;
    @FXML
    private ComboBox<GameSize> comboBoxResolution;
    @FXML
    private ComboBox<LanguageType> comboBoxLanguage;
    @FXML
    private CheckBox checkBoxSound;
    @FXML
    private Slider sliderVolume;
    @FXML
    private TableView<Control> tableViewControls;

    public Stage stage;
    public Image icon = Tool.loadImage("icon.png");
    public final ResourceBundle bundle = Tool.getBundle();
    public AppController appController;
    public Game game;

    public void init(Stage stage, AppController controller, Game game) {
        this.stage = stage;
        this.appController = controller;
        this.game = game;

        comboBoxResolution.setCellFactory(param -> new ComboBoxResolutionCell());
        comboBoxResolution.setButtonCell(new ComboBoxResolutionCell());
        comboBoxResolution.getItems().addAll(GameSize.values());

        comboBoxLanguage.setCellFactory(param -> new ComboBoxLanguageCell());
        comboBoxLanguage.setButtonCell(new ComboBoxLanguageCell());
        comboBoxLanguage.getItems().addAll(LanguageType.values());

        checkBoxSound.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == true) {
                sliderVolume.setDisable(false);
            } else {
                sliderVolume.setDisable(true);
            }
        });

        // preselect
        textUsername.setText(game.getSettings().getUsername());
        comboBoxResolution.setValue(game.getSettings().getGameSize());
        comboBoxLanguage.setValue(game.getSettings().getLanguage());
        if (game.getSettings().getMuted()) {
            checkBoxSound.setSelected(false);
            sliderVolume.setDisable(true);
        } else {
            checkBoxSound.setSelected(true);
            sliderVolume.setDisable(false);
        }
        sliderVolume.setValue(game.getSettings().getVolume() * 100);

        initTableViewControls();

        mainPane.setStyle("-fx-base: " + Colors.BACKGROUND);
    }

    public void save() {
        game.getSettings().setGameSize(comboBoxResolution.getValue());
        game.getSettings().setLanguage(comboBoxLanguage.getValue());
        game.getSettings().setMuted(!checkBoxSound.isSelected());
        game.getSettings().setVolume(sliderVolume.getValue() / 100.0);
        game.getSettings().save();

        game.refreshSoundHandler();
        stage.close();
        appController.stage.show();

        // TODO load correct language file
    }

    public void back() {
        stage.close();
        appController.stage.show();
    }

    private void initTableViewControls() {
        ArrayList<Control> controls = new ArrayList<>();
        controls.add(new Control("Start Ball", "Space / Left Mouse Button"));
        controls.add(new Control("Move Paddle", "Arrow Keys / Mouse"));
        controls.add(new Control("Pause", "P"));
        controls.add(new Control("Exit Level / Back", "Esc"));

        TableColumn<Control, String> columnAction = new TableColumn<>("Action");
        columnAction.setCellValueFactory(param -> {
            Control control = (Control) param.getValue();
            return new SimpleStringProperty(control.getAction());
        });
        tableViewControls.getColumns().add(columnAction);
        columnAction.setStyle("-fx-alignment: CENTER; -fx-font-weight: bold;");
        columnAction.prefWidthProperty().bind(tableViewControls.widthProperty().divide(2).subtract(2));
        columnAction.setResizable(false);
        columnAction.setSortable(false);

        TableColumn<Control, String> columnKey = new TableColumn<>("Key");
        columnKey.setCellValueFactory(param -> {
            Control control = (Control) param.getValue();
            return new SimpleStringProperty(control.getKey());
        });
        tableViewControls.getColumns().add(columnKey);
        columnKey.setStyle("-fx-alignment: CENTER; -fx-font-weight: bold;");
        columnKey.prefWidthProperty().bind(tableViewControls.widthProperty().divide(2).subtract(2));
        columnKey.setResizable(false);
        columnKey.setSortable(false);

        tableViewControls.getItems().addAll(controls);
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