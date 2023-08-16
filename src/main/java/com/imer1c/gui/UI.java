package com.imer1c.gui;

import com.imer1c.gui.components.Component;

import java.awt.*;

public interface UI<C extends Component> {
    void render(Graphics2D g, int mouseX, int mouseY);
}
