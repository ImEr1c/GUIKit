package com.imer1c.gui;

import com.imer1c.gui.components.CheckBox;
import com.imer1c.gui.theme.Theme;
import com.imer1c.utils.MouseButton;

import java.awt.*;

public class Window implements IEventListener, IWindowEventListener {
    private int width, height, mouseX, mouseY;
    private Paint background;
    private CheckBox box = new CheckBox("");

    public Window(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.box.setParent(null);
    }

    public void render(Graphics2D g)
    {
        this.box.render(g, this.mouseX, this.mouseY);
    }

    @Override
    public void mouseMoved(int x, int y)
    {
        this.mouseX = x;
        this.mouseY = y;
        if (x >= this.box.x && x <= this.box.x + this.box.totalWidth && y >= this.box.y && y <= this.box.y + this.box.totalHeight)
        {
            this.box.mouseHovered();
        }
        this.box.mouseLeave();
    }

    @Override
    public void mouseClicked(int x, int y, MouseButton button, int clickCount)
    {
        System.out.println(x);
        System.out.println(this.box.x);
        System.out.println(this.box.totalWidth);
        System.out.println(y);
        System.out.println(this.box.y);
        System.out.println(this.box.totalHeight);
        if (x >= this.box.x && x <= this.box.x + this.box.totalWidth && y >= this.box.y && y <= this.box.y + this.box.totalHeight)
        {
            this.box.mouseClicked(x, y, button, clickCount);
        }
    }
}
