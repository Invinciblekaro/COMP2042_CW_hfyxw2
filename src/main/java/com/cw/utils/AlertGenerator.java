package com.cw.utils;

import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AlertGenerator
{
    public static void showAlert(Alert.AlertType type, String contentText, Image icon) {
        show(type, type.name(), "", contentText, icon, null, null, true);
    }

    
    public static void showAlert(Alert.AlertType type, String title, String headerText, String contentText, Image icon, Window owner, String hexCodeColor, boolean centerOnScreen) {
        show(type, title, headerText, contentText, icon, owner, hexCodeColor, centerOnScreen);
    }

    
    public static void showAboutAlert(String appName, String versionName, String versionCode, String versionDate, String authors, Image icon, Window owner, String hexCodeColor, boolean centerOnScreen) {
        String content = "Version:    " + versionName + " (" + versionCode + ")\r\nDate:         " + versionDate + "\r\nAuthors:    " + authors + "\r\n";
        
        show(Alert.AlertType.INFORMATION, "About " + appName, appName, content, icon, owner, hexCodeColor, centerOnScreen);
    }

    
    public static void showAboutAlertWithCredits(String appName, String versionName, String versionCode, String versionDate, String authors, ArrayList<String> creditLines, Image icon, Window owner, String hexCodeColor, boolean centerOnScreen) {
        String content = "Version:     " + versionName + " (" + versionCode + ")\r\nDate:         " + versionDate + "\r\nAuthors:    " + authors + "\r\n";
        content = String.valueOf(content) + "\r\nCredits:\r\n";
        for (String line : creditLines)
        {
            content = String.valueOf(content) + line + "\r\n";
        }
        
        show(Alert.AlertType.INFORMATION, "About " + appName, appName, content, icon, owner, hexCodeColor, centerOnScreen);
    }

    
    private static void show(Alert.AlertType type, String title, String headerText, String contentText, Image icon, Window owner, String hexCodeColor, boolean centerOnScreen) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        Stage dialogStage = (Stage)alert.getDialogPane().getScene().getWindow();
        dialogStage.getIcons().add(icon);
        
        if (hexCodeColor != null)
        {
            alert.getDialogPane().setStyle("-fx-base: " + hexCodeColor);
        }
        if (owner != null) {
            
            dialogStage.initOwner(owner);

        
        }
        else if (centerOnScreen) {
            
            dialogStage.centerOnScreen();
        } 
        
        alert.showAndWait();
    }
}
