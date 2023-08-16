package com.imer1c;

import com.imer1c.parser.Token;
import com.imer1c.parser.TokenParser;
import com.imer1c.gui.hook.Frame;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException
    {
        Scanner scanner = new Scanner(new File("theme1.style"));

        List<Token> list = new ArrayList<>();

        while (scanner.hasNextLine())
        {
            TokenParser parser = new TokenParser(scanner.nextLine());
            parser.parseAll(list);
        }

        list.forEach(System.out::println);

        Frame frame = new Frame("");
        frame.setVisible(true);
    }
}
