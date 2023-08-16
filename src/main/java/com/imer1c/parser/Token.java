package com.imer1c.parser;

public class Token {
    private final String value;
    private final TokenType type;
    private final int index;

    public Token(String value, TokenType type, int index)
    {
        this.value = value;
        this.type = type;
        this.index = index;
    }

    public int getIndex()
    {
        return index;
    }

    public String getValue()
    {
        return value;
    }

    public TokenType getType()
    {
        return type;
    }

    public boolean is(TokenType type)
    {
        return this.type == type;
    }

    @Override
    public String toString()
    {
        return "Token{" +
                "value='" + value + '\'' +
                ", type=" + type +
                '}';
    }

    public enum TokenType {
        WORD, SPACE, UNRECOGNIZED, QUOTATION_MARKS, PERIOD, AT, COLON, SEMICOLON, OPEN_BRACKETS, CLOSED_BRACKETS, NUMBER, END_OF_LINE

    }
}
