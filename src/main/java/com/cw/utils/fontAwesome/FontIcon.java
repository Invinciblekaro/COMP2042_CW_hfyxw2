package com.cw.utils.fontAwesome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import com.cw.utils.Tool;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class FontIcon extends Label {
    private static Map<String, Map<Integer, Font>> fonts;
    public static final String STYLE_CLASS = "fonticon";
    private static String FONT_FILE = Tool.getConfig("font.filepath");
    private String fontFile = FONT_FILE;

    static {
        fonts = new HashMap<>();
    }

    public static void setDefaultFontFile(String file) {
        FONT_FILE = file;
    }

    private int size = 14;

    private Font font;
    private Color color;

    public FontIcon(FontIconType type, int size, Color color) {
        this(type);
        setSize(size);
        setColor(color);
    }


    public FontIcon(FontIconType type) {
        this(new FontIconType[]{type});
    }


    public FontIcon(FontIconType... types) {
        this(FONT_FILE, types);
    }


    public FontIcon(FontIconType type, String fontFile) {
        this(new FontIconType[]{type});
    }


    public FontIcon(String fontFile, FontIconType... types) {
        this.fontFile = fontFile;
        setIcons(types);
        loadFont();
        getStyleClass().remove("label");
        getStyleClass().add("fonticon");
    }


    public void setIcons(FontIconType... types) {
        StringBuilder text = new StringBuilder();
        byte b;
        int i;

        FontIconType[] arrayOfFontIconType;
        for (i = (arrayOfFontIconType = types).length, b = 0; b < i; ) {
            FontIconType type = arrayOfFontIconType[b];

            text.append(type.getChar());
            b++;
        }

        setText(text.toString());
    }


    public void loadFont() {
        if (!fonts.containsKey(this.fontFile)) {
            fonts.put(this.fontFile, new HashMap<>());
        }

        Map<Integer, Font> localFonts = fonts.get(this.fontFile);
        if (!localFonts.containsKey(this.size)) {
            try {
                localFonts.put(this.size, Font.loadFont(new FileInputStream(Tool.loadFile(FONT_FILE)), this.size));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        this.font = (Font) ((Map) fonts.get(this.fontFile)).get(this.size);
        setFont(this.font);
    }


    public int getSize() {
        return this.size;
    }


    public void setSize(int size) {
        this.size = size;
        loadFont();
    }


    public Color getColor() {
        return this.color;
    }


    public void setColor(Color color) {
        this.color = color;
        setTextFill((Paint) color);
    }
}
