package com.yijie.libraryManagementSystem.tool;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc    FontTool.java
 * @author  yijie
 * @date    2021-01-05 10:50
 * @note    2021-01-05 10:50 yijie Created FontTool.java file
 */
public class FontTool {
    private static Map<String, Font> fonts = new HashMap<>();

    public static JLabel setFont(JLabel label) {
        return FontTool.setFont(label, null, null);
    }

    public static JLabel setFont(JLabel label, Integer fontSize) {
        return FontTool.setFont(label, fontSize, null);
    }

    public static JLabel setFont(JLabel label, String fontName) {
        return FontTool.setFont(label, null, fontName);
    }

    public static JLabel setFont(JLabel label, Integer fontSize, String fontName) {
        if (label == null) throw new RuntimeException("Label isn't set null;");
        if (fontSize == null) fontSize = 16;
        if (fontName == null || fontName.equals("")) fontName = "elePy";

        Font font = null;
        if (!fonts.containsKey(fontName)) {
            try {
                font = Font.createFont(
                        Font.TRUETYPE_FONT, FontTool.class.getResourceAsStream("/fonts/" + fontName + ".ttf")
                );
                GraphicsEnvironment.getLocalGraphicsEnvironment()
                        .registerFont(font);
            } catch (Exception e) {
                throw new RuntimeException("Font's resource isn't found.");
            }
            fonts.put(fontName, font);
        } else {
        }
        label.setFont(
                fonts.get(fontName).deriveFont((float) fontSize)
        );
        return label;
    }
}
