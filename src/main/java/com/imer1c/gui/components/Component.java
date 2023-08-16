package com.imer1c.gui.components;

import com.imer1c.gui.IEventListener;
import com.imer1c.gui.components.config.FourValues;
import com.imer1c.gui.components.config.Offset;
import com.imer1c.gui.theme.Theme;
import com.imer1c.gui.theme.ui.CheckboxUI;
import com.imer1c.gui.theme.ui.ComponentUI;
import com.imer1c.gui.theme.ui.UIManager;

import java.awt.*;

public abstract class Component implements IEventListener, Container {
    private final Offset offset = new Offset();
    private final FourValues padding = new FourValues();
    private final FourValues margin = new FourValues();
    private Paint backgroundColor, hoverColor;
    private final ComponentUI<?> ui;
    private Color textColor;
    public Component parent;
    private boolean hovered;
    private String width, height;
    private int x, y;

    public Component()
    {
        this.ui = UIManager.buildUI(this);
    }

    public void setParent(Component parent)
    {
        if (this.parent != null)
        {
            throw new IllegalStateException("Parent is not null");
        }

        this.parent = parent;
    }

    public void render(Graphics2D g, int x, int y)
    {
        this.ui.update(g, x, y);
    }

    public int getXWithOffset()
    {
        return this.x + this.offset.getRight() - this.offset.getLeft();
    }

    public int getYWithOffset()
    {
        return this.y + this.offset.getBottom() - this.offset.getTop();
    }

    @Override
    public void mouseHovered()
    {
        this.hovered = true;
    }

    @Override
    public void mouseLeave()
    {
        this.hovered = false;
    }

    @Override
    public String getWidth()
    {
        return this.width;
    }

    @Override
    public String getHeight()
    {
        return this.height;
    }

    public ComponentUI<?> getUi()
    {
        return ui;
    }
}
