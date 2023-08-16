package com.imer1c.parser;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class TokenParser {
    private final String line;
    private final int end;
    private int index;

    public TokenParser(String line)
    {
        this.line = line;
        this.end = line.length() - 1;
    }

    public void parseAll(List<Token> list)
    {
        while (this.index < this.end)
        {
            list.add(this.parseNext());
        }
    }

    public char current()
    {
        return this.line.charAt(this.index);
    }

    public int advance()
    {
        return this.index++;
    }

    public Token parseNext()
    {
        char c = this.current();

        if (Character.isDigit(c))
        {
            return loopThrough(Character::isDigit, (s, i) -> new Token(s, Token.TokenType.NUMBER, i));
        }

        if (Character.isLetter(c))
        {
            return loopThrough(Character::isLetter, (s, i) -> new Token(s, Token.TokenType.WORD, i));
        }

        if (Character.isSpaceChar(c))
        {
            return loopThrough(Character::isSpaceChar, (s, i) -> new Token(s, Token.TokenType.SPACE, i));
        }

        int i = this.advance();

        return switch (c)
        {
            case '}' -> new Token("}", Token.TokenType.CLOSED_BRACKETS, i);
            case '{' -> new Token("{", Token.TokenType.OPEN_BRACKETS, i);
            case ';' -> new Token(";", Token.TokenType.SEMICOLON, i);
            case ':' -> new Token(":", Token.TokenType.COLON, i);
            case '@' -> new Token("@", Token.TokenType.AT, i);
            case '.' -> new Token(".", Token.TokenType.PERIOD, i);
            case '"' -> new Token("\"", Token.TokenType.QUOTATION_MARKS, i);
            default -> new Token(null, Token.TokenType.UNRECOGNIZED, i);
        };
    }

    private Token loopThrough(Predicate<Character> predicate, BiFunction<String, Integer, Token> tokenBuilder)
    {
        int startIndex = this.index;

        while (this.index < this.end && predicate.test(this.current()))
        {
            this.advance();
        }

        String s = this.line.substring(startIndex, this.index);

        return tokenBuilder.apply(s, startIndex);
    }
}
