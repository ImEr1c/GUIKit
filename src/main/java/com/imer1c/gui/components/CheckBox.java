package com.imer1c.gui.components;

import com.imer1c.utils.MouseButton;

public class CheckBox extends Component {

    private final String label;
    private boolean value;

    public CheckBox(String label)
    {
        this(label, false);
    }

    public CheckBox(String label, boolean value)
    {
        this.label = label;
        this.value = value;
    }

    public boolean isTrue()
    {
        return this.value;
    }

    @Override
    public void mouseClicked(int x, int y, MouseButton button, int clickCount)
    {
        this.value = !this.value;
        System.out.println("A");
    }

    public String getLabel()
    {
        return label;
    }
}
