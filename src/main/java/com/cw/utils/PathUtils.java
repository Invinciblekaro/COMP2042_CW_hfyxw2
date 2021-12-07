package com.cw.utils;

import com.cw.utils.OS;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathUtils {
    public static void checkFolder(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
    }


    public static String getOSindependentPath() {

        String path = null;

        switch (OS.getType()) {
            case Windows:
                path = Paths.get(System.getenv("APPDATA"), new String[0]).toAbsolutePath() + "/";
                break;
            case MacOSX:
                path = Paths.get(System.getProperty("user.home"), new String[]{"Library/Application Support/"}).toAbsolutePath() + "/.";
                break;
            default:
                path = Paths.get(System.getProperty("user.home"), new String[0]).toAbsolutePath() + "/.";
        }

        return path;
    }


    public static Path getCurrentLocation() throws URISyntaxException {
        return Paths.get(PathUtils.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
    }

    public static void selectFileInExplorer(String path) throws IOException {
        path = path.replace("/", "\\");
        Runtime.getRuntime().exec(
                new String[]{
                        "explorer.exe",
                        "/select,",
                        "\"" + path + "\""
                });
    }
}