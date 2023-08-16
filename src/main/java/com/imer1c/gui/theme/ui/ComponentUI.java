package com.imer1c.gui.theme.ui;

import com.imer1c.gui.UI;
import com.imer1c.gui.components.Component;
import com.imer1c.parser.NumberValue;
import com.imer1c.parser.Parsers;

import java.awt.*;

public abstract class ComponentUI<C extends Component> implements UI<C> {

    protected final C component;
    protected final NumberValue widthValue, heightValue;
    public int width, height;

    public ComponentUI(C component)
    {
        this.component = component;

        this.widthValue = Parsers.parsePoint(component.getWidth());
        this.heightValue = Parsers.parsePoint(component.getHeight());
    }

    public void calculateDimensions()
    {
        this.width = this.widthValue.getValue(this.component.parent.getUi().width);
        this.height = this.heightValue.getValue(this.component.parent.getUi().height);
    }

    public void update(Graphics2D g, int mouseX, int mouseY)
    {
        if (g == null)
        {
            return;
        }

        this.calculateDimensions();
        this.render(g, mouseX, mouseY);
    }

    protected int center(int total, int space)
    {
        return (total - space) / 2;
    }
}
