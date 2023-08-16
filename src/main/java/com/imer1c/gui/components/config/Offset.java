package com.imer1c.gui.components.config;

public class Offset extends FourValues {
    private boolean enabled;

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    @Override
    public int getLeft()
    {
        return this.enabled ? super.getLeft() : 0;
    }

    @Override
    public int getBottom()
    {
        return this.enabled ? super.getBottom() : 0;
    }

    @Override
    public int getRight()
    {
        return this.enabled ? super.getRight() : 0;
    }

    @Override
    public int getTop()
    {
        return this.enabled ? super.getTop() : 0;
    }
}
