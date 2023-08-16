package com.imer1c.parser.style;

import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.InsetsUIResource;
import java.awt.*;

public class StyleExpression<V> {
    private final V value;
    private final StyleExpressionType type;
    private final int index;

    public StyleExpression(V value, StyleExpressionType type, int index)
    {
        this.value = value;
        this.type = type;
        this.index = index;
    }

    public int getIndex()
    {
        return index;
    }

    public String getAsString()
    {
        return (String) this.value;
    }

    public int getAsInt()
    {
        return (int) this.value;
    }

    public double getAsDouble()
    {
        return (double) this.value;
    }

    public float getAsFloat()
    {
        return (float) this.value;
    }

    public FontUIResource getAsFont()
    {
        return (FontUIResource) this.value;
    }

    public Color getAsColor()
    {
        return (Color) this.value;
    }

    public ColorUIResource getAsUIColor()
    {
        return (ColorUIResource) this.value;
    }

    public Border getAsBorder()
    {
        return (Border) this.value;
    }

    public InsetsUIResource getAsInsets()
    {
        return (InsetsUIResource) this.value;
    }

    public String getValue()
    {
        return this.value.toString();
    }

    public StyleExpressionType getType()
    {
        return type;
    }

    public enum StyleExpressionType {
        IMPORT(false),
        STRING(true),
        INTEGER(true),
        DOUBLE(true),
        FLOAT(true),
        NAME(false),
        OPEN_PROPERTY(false),
        CLOSE_PROPERTY(false),
        FONT_METHOD(true),
        COLON(false),
        RGB_METHOD(true),
        HEX_METHOD(true),
        BORDER(true),
        INSETS(true),
        SPACE(false),
        UNRECOGNIZED(false),
        ;

        private final boolean value;

        StyleExpressionType(boolean value)
        {
            this.value = value;
        }

        public boolean isValue()
        {
            return value;
        }
    }
}
