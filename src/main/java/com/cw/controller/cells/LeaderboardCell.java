package com.cw.controller.cells;

import com.cw.game.leaderboard.Leaderboard;
import com.cw.game.leaderboard.PlayerInfo;
import com.cw.game.levels.Level;
import com.cw.utils.Colors;
import com.cw.utils.Tool;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * @author tanghui
 * @date 2021/12/7 7:51 下午
 */
public class LeaderboardCell extends ListCell<Leaderboard> {

    private double maxWidth;

    public LeaderboardCell(double maxWidth) {
        this.maxWidth = maxWidth;
    }

    @Override
    protected void updateItem(Leaderboard item, boolean empty) {
        super.updateItem(item, empty);

        if (!empty) {

            VBox vBox = new VBox();
            List<PlayerInfo> players = item.getPlayerInfos();

            for (PlayerInfo player : players) {
                HBox hbox = new HBox();

                Label username = new Label(String.valueOf(player.getName()+ " - "));
                username.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
                username.getStyleClass().add("greylabel");

                Label point = new Label(String.valueOf(player.getPoint()));
                point.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
                point.getStyleClass().add("greylabel");

                hbox.getChildren().add(username);
                hbox.getChildren().add(point);

                vBox.getChildren().add(hbox);
            }

            // Label levelName = new Label(String.valueOf(item.getLevelName()));
            // levelName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            // levelName.getStyleClass().add("greylabel");

            // hbox.getChildren().add(levelName);

            Region r = new Region();
            vBox.getChildren().add(r);
            HBox.setHgrow(r, Priority.ALWAYS);

            // HBox.setMargin(labelLevelName, new Insets(0, 0, 0, 25));
            // HBox.setMargin(labelAuthor, new Insets(0, 0, 0, 10));

            vBox.setAlignment(Pos.CENTER);
            vBox.setStyle("-fx-background-color: " + Colors.SELECT_LEVEL);
            vBox.setPadding(new Insets(15));

            TitledPane titledPane1 = new TitledPane(item.getLevelName(), vBox);
            setGraphic(titledPane1);
            setPadding(new Insets(10));
        } else {
            setGraphic(null);
        }

        setStyle("-fx-background-color: transparent");
    }

}
