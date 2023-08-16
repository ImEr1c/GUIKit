package com.imer1c.parser.style;

import com.imer1c.parser.Token;

import java.util.ArrayList;
import java.util.List;

public class StyleExpressionParser {
    private final List<StyleExpression<?>> expressions = new ArrayList<>();
    private final List<Token> tokens;
    private final int end;
    private int line;
    private int index;

    public StyleExpressionParser(List<Token> tokens)
    {
        this.tokens = tokens;
        this.end = tokens.size() - 1;

        while (this.index < this.end)
        {
            this.expressions.add(this.parseNext());
        }
    }

    public int advance()
    {
        int i = this.index++;

        if (this.current().getType() == Token.TokenType.END_OF_LINE)
        {
            this.line++;
        }

        return i;
    }

    public Token current()
    {
        return this.tokens.get(this.index);
    }

    public Token peek()
    {
        if (this.index + 1 >= this.end)
        {
            return null;
        }

        return this.tokens.get(this.index + 1);
    }

    public StyleExpression<?> parseNext()
    {
        Token token = this.current();
        Token peek = this.peek();

        if (token.is(Token.TokenType.AT))
        {
            if (peek == null)
            {
                this.err("Expected a statement after the @", token);
            }

            if (!peek.is(Token.TokenType.WORD))
            {
                this.err("Expected a word after @", token);
            }

            if (peek.getValue().equals("import"))
            {
                this.advance();
                return new StyleExpression<>("@import", StyleExpression.StyleExpressionType.IMPORT, token.getIndex());
            }
            else
            {
                this.err("Expected a @import statement", peek);
            }
        }

        if (token.is(Token.TokenType.SPACE))
        {
            return new StyleExpression<>(token.getValue(), StyleExpression.StyleExpressionType.SPACE, this.advance());
        }

        if (token.is(Token.TokenType.QUOTATION_MARKS))
        {
            if (peek == null)
            {
                this.err("Expected \" to close the string before line end", token);
            }

            if (peek.is(Token.TokenType.WORD))
            {
                this.advance();
                Token t = this.current();

                StringBuilder builder = new StringBuilder();

                while (!t.is(Token.TokenType.QUOTATION_MARKS))
                {
                    builder.append(t.getValue());
                    this.advance();
                    t = this.current();

                    if (this.index >= this.end)
                    {
                        this.err("Expected \" before line end", t);
                    }
                }

                this.advance();
                return new StyleExpression<>(builder.toString(), StyleExpression.StyleExpressionType.STRING, token.getIndex());
            }
        }

        if (token.is(Token.TokenType.NUMBER))
        {
            if (peek.is(Token.TokenType.PERIOD))
            {
                this.advance();

                Token p = this.peek();

                if (p.is(Token.TokenType.NUMBER))
                {
                    this.advance();

                    Token p2 = this.peek();

                    String s = token.getValue() + peek.getValue() + p.getValue();

                    if (p2.is(Token.TokenType.WORD) && p2.getValue().equals("F"))
                    {
                        s += "F";
                        return new StyleExpression<>(Float.parseFloat(s), StyleExpression.StyleExpressionType.FLOAT, token.getIndex());
                    }
                    else
                    {
                        return new StyleExpression<>(Double.parseDouble(s), StyleExpression.StyleExpressionType.DOUBLE, token.getIndex());
                    }
                }
                else
                {
                    err("Expected a number after .", p);
                }
            }
            else if (peek.is(Token.TokenType.WORD))
            {
                String value = peek.getValue();

                if (value.equals("F"))
                {
                    return new StyleExpression<>(Float.parseFloat(token.getValue() + "F"), StyleExpression.StyleExpressionType.FLOAT, token.getIndex());
                }
                else if (value.equals("D"))
                {
                    return new StyleExpression<>(Double.parseDouble(token.getValue() + "D"), StyleExpression.StyleExpressionType.DOUBLE, token.getIndex());
                }
                else {
                    err("Expected a D or F after number for Double or Float", peek);
                }
            }
            else
            {
                return new StyleExpression<>(Integer.parseInt(token.getValue()), StyleExpression.StyleExpressionType.INTEGER, token.getIndex());
            }
        }

        return new StyleExpression<>(token.getValue(), StyleExpression.StyleExpressionType.UNRECOGNIZED, this.advance());
    }

    private void err(String err, Token t)
    {
        throw new IllegalArgumentException(err + " l: " + this.line + " c" + t.getIndex());
    }
}
