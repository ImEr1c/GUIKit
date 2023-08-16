package com.imer1c.gui;

public interface IWindowEventListener {
    default void windowGainFocus() {}

    default void windowLostFocus() {}

    default void windowResize(int width, int height) {}

    default void windowClosing() {}

    default void windowMoved(int x, int y) {}
}
