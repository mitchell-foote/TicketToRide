package com.example.model.enums;

/**
 * Created by Mitchell Foote on 9/22/2017.
 */
public enum SharedColor {
    RAINBOW,
    RED,
    ORANGE,
    YELLOW,
    GREEN,
    BLUE,
    PURPLE,
    BLACK,
    WHITE;

    public static int sharedColorToHex(SharedColor color) {
        switch (color) {
            case RED:
                return 0xFFFF0000;
            case ORANGE:
                return 0xFFFFA500;
            case YELLOW:
                return 0xFFFFFF00;
            case GREEN:
                return 0xFF00FF00;
            case BLUE:
                return 0xFF0000FF;
            case PURPLE:
                return 0xFFFF00FF;
            case BLACK:
                return 0xFF505050;
            case WHITE:
                return 0xFFFFFFFF;
            default:
                return 0xFF000000;
        }
    }
}
