package com.cw.controller;

import com.cw.controller.cells.LeaderboardCell;
import com.cw.game.Game;
import com.cw.game.leaderboard.Leaderboard;
import com.cw.game.leaderboard.LeaderboardHandler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

/**
 * @author tanghui
 * @date 2021/12/6 8:53 下午
 */
public class HighScoreListController {

    @FXML
    public Button buttonClose;

    @FXML
    public ScrollPane pane;

    public void init(Stage newStage, AppController appController, Game game) {

        reload();

        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        pane.setStyle("-fx-background-color:transparent;");
    }

    public void reload() {
        CustomListView<Leaderboard> listView = new CustomListView<Leaderboard>(FXCollections.observableList(LeaderboardHandler.loadLeaderboard()));

        listView.setCellFactory(param -> new LeaderboardCell(pane.getMaxWidth() - 8));
        listView.setStyle("-fx-background-color: transparent");

        listView.setHScrollBarEnabled(false);

        listView.prefWidthProperty().bind(pane.maxWidthProperty());
        listView.prefHeightProperty().bind(pane.maxHeightProperty().subtract(10));

        listView.setOnMouseClicked(event -> {
            Leaderboard selectedItem = listView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                listView.getSelectionModel().clearSelection();
            }
        });

        pane.setContent(listView);
    }

    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();
    }

}
