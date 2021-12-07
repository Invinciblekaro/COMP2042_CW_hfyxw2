package com.cw;

import com.cw.game.Config;
import com.cw.utils.Tool;
import org.junit.Test;
import com.cw.utils.PathUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * @author Wang_xubin
 * @date 2021/12/1 10:59 下午
 */
public class FileLoadTest {

    private transient final static ResourceBundle config = Tool.getConfig();

    @Test public void fileload() throws IOException {

        // Files.readAllBytes(FileSystems.getDefault().getPath("levelpacks/default.json"));
        // Tool.loadFile("/levelpacks/default.json");

        PathUtils.checkFolder(new File(System.getProperty("user.dir")));
        File file = new File(System.getProperty("user.dir") + "/" + config.getString("setting.filename"));
        // System.out.println(file.isFile());
        //
        if (!file.exists()) {
            file.createNewFile();
        }

        // System.out.println(System.getProperty("user.dir"));
        // System.out.println(System.getProperty("java.class.path"));
        // System.out.println(Tool.class.getResource("").getPath());

    }

    @Test public  void  loadconfig(){
        // ResourceBundle config = ResourceBundle.getBundle("config");
        File folder = Tool.loadFile(Tool.getConfig("level.folder"));

        System.out.println(folder.listFiles().length);
    }

    @Test public  void  gson(){
        // Gson gson = new Gson();
        //
        // HashMap map = new HashMap();
        // map.put("nam,e", "nobody");
        // map.put("age", 21);
        //
        // System.out.println(gson.toJson(map));
    }
}
