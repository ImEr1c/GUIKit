package com.imer1c.parser;

public class NumberValue {
    private final int value;
    private final boolean percentage;

    private NumberValue(int value, boolean percentage)
    {
        this.value = value;
        this.percentage = percentage;
    }

    public static NumberValue from(float value, boolean percentage)
    {
        return new NumberValue(value, percentage);
    }

    public int getValue(int value)
    {
        return this.percentage ? this.percentage(this.value, value) : this.value;
    }

    private int percentage(int percent, int value)
    {
        return (int)((double)percent / 100 * value);
    }

    public boolean isPercentage()
    {
        return percentage;
    }
}
