package com.imer1c.utils;

public enum MouseButton {
    LEFT,
    MIDDLE,
    RIGHT;

    public static MouseButton get(int i)
    {
        return MouseButton.values()[i - 1];
    }
}
