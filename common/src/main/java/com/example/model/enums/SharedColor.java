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
                return 0xffe94d4d;
            case ORANGE:
                return 0xffe94d4d;
            case YELLOW:
                return 0xffe8e94d;
            case GREEN:
                return 0xff7add7a;
            case BLUE:
                return 0xff5959ff;
            case PURPLE:
                return 0xffe36ae3;
            case BLACK:
                return 0xff505050;
            case WHITE:
                return 0xfff8f8f8;
            default:
                return 0xFF000000;
        }
    }
}
