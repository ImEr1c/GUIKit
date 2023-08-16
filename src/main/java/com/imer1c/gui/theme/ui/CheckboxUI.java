package com.imer1c.gui.theme.ui;

import com.imer1c.gui.components.CheckBox;

import java.awt.*;

public class CheckboxUI extends ComponentUI<CheckBox> {
    public CheckboxUI(CheckBox component)
    {
        super(component);
    }

    @Override
    public void render(Graphics2D g, int mouseX, int mouseY)
    {
        int x = this.component.getXWithOffset() + this.center(this.width, 16);
        int y = this.component.getYWithOffset() + this.center(this.height, 16);

        g.setColor(component.isTrue() ? Color.BLUE : Color.GRAY);
        g.fillRect(x, y, 16, 16);

        g.setColor(Color.DARK_GRAY);
        g.drawRect(x, y, 16, 16);
    }
}
