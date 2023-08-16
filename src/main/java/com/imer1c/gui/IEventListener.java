package com.imer1c.gui;

import com.imer1c.utils.MouseButton;

public interface IEventListener {
    default void mouseClicked(int x, int y, MouseButton button, int clickCount) {}

    default void mousePressed(int x, int y, MouseButton button) {}

    default void mouseReleased(int x, int y, MouseButton button) {}

    default void mouseMoved(int x, int y) {}

    default void mouseDragged(int x, int y) {}

    default void mouseScrolled(int units, boolean down) {}

    default void mouseHovered() {}

    default void mouseLeave() {}

    default void keyTyped(char c) {}

    default void keyPressed(int key, char c) {}

    default void keyReleased(int key, char c) {}
}
