package com.cw.utils;

import com.cw.App;
import com.cw.game.Config;
import com.cw.game.sound.SoundHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author Wang_xubin
 * @date 2021/11/22 11:48 下午
 */
public class Tool {

    public static final ResourceBundle bundle = ResourceBundle.getBundle("application");
    public static final ResourceBundle config = ResourceBundle.getBundle("config");

    private static volatile Tool tool;
    public static Tool getInstance() {
        if (tool == null) {
            synchronized (Tool.class) {
                if (tool == null) {
                    tool = new Tool();
                }
            }
        }
        return tool;
    }


    // load image
    public static Image loadImage(String name) {
        return new Image(Objects.requireNonNull(Tool.class.getResourceAsStream(config.getString("image.path") + name)));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = getFXMLLoader(fxml);
        return fxmlLoader.load();
    }

    public static FXMLLoader getFXMLLoader(String fxml) throws IOException {
        return new FXMLLoader(Tool.class.getResource(config.getString("view.path") + fxml + ".fxml"));
    }

    public static File loadFile(String filename) {
        return new File(Objects.requireNonNull(Tool.class.getResource(filename)).getFile());
    }

    public static URL loadSound(String filename) throws URISyntaxException, MalformedURLException {
        return Tool.class.getResource(config.getString("sound.path") + filename+ ".wav").toURI().toURL();
    }

    public static ResourceBundle getBundle()  {
        return bundle;
    }

    public static ResourceBundle getConfig()  {
        return config;
    }

    public static String getDir()  {
        return System.getProperty("user.dir");
    }

    public static String getClasspath()  {
        return App.class.getResource("").getPath();
    }

    public static String getConfig(String key)  {
        return config.getString(key);
    }

}