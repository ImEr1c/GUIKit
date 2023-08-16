package com.imer1c.parser;

public class Parsers {
    public static NumberValue parsePoint(String s)
    {
        boolean percentage = s.endsWith("%");

        return NumberValue.from(parseNumber(s), percentage);
    }

    private static float parseNumber(String s)
    {
        int i = 0;
        for (char c : s.toCharArray())
        {
            if (!Character.isDigit(c) && c != '.')
            {
                break;
            }
            i++;
        }

        return Float.parseFloat(s.substring(0, i));
    }
}
