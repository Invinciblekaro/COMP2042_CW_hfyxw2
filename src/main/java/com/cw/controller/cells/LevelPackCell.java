package com.cw.controller.cells;

import com.cw.game.levels.LevelPack;
import com.cw.utils.Colors;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class LevelPackCell extends ListCell<LevelPack> {
    private double maxWidth;

    public LevelPackCell(double maxWidth) {
        this.maxWidth = maxWidth;
    }

    @Override
    protected void updateItem(LevelPack item, boolean empty) {
        super.updateItem(item, empty);

        if (!empty) {
            HBox hbox = new HBox();

            Label labelLevelName = new Label(item.getPackageName());
            labelLevelName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            labelLevelName.setMaxWidth(maxWidth * 0.4);
            labelLevelName.getStyleClass().add("greylabel");

            Label labelAuthor = new Label("(by " + item.getPackageAuthor() + ")");
            labelAuthor.setStyle("-fx-font-size: 12px;");
            labelAuthor.setMaxWidth(maxWidth * 0.35);
            labelAuthor.getStyleClass().add("greylabel");

            Label labelVersion = new Label("Version " + item.getVersion());
            labelVersion.setStyle("-fx-font-size: 12px;");
            labelVersion.getStyleClass().add("greylabel");

            hbox.getChildren().add(labelLevelName);
            hbox.getChildren().add(labelAuthor);

            Region r = new Region();
            hbox.getChildren().add(r);
            HBox.setHgrow(r, Priority.ALWAYS);

            hbox.getChildren().add(labelVersion);

            HBox.setMargin(labelAuthor, new Insets(0, 0, 0, 10));

            hbox.setAlignment(Pos.CENTER);
            hbox.setStyle("-fx-background-color: " + Colors.SELECT_LEVELPACK +";" +
                    "   -fx-background-radius: 25;" +
                    "    -fx-border-radius: 25;");
            hbox.setPadding(new Insets(15));

            hbox.setMaxWidth(maxWidth);

            setGraphic(hbox);

            setPadding(new Insets(10));
        } else {
            setGraphic(null);
        }

        setStyle("-fx-background-color: transparent");
    }
}