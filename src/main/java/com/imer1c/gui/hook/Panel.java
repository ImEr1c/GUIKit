package com.imer1c.gui.hook;

import com.imer1c.gui.Window;
import com.imer1c.utils.MouseButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Optional;

public class Panel extends JPanel {

    private Optional<Window> window = Optional.empty();

    public Panel()
    {

    }

    @Override
    protected void processMouseEvent(MouseEvent e)
    {
        this.window.ifPresent(w -> {
            int id = e.getID();
            switch (id)
            {
                case MouseEvent.MOUSE_PRESSED -> w.mousePressed(e.getX(), e.getY(), MouseButton.get(e.getButton()));
                case MouseEvent.MOUSE_RELEASED -> w.mouseReleased(e.getX(), e.getY(), MouseButton.get(e.getButton()));
                case MouseEvent.MOUSE_CLICKED -> w.mouseClicked(e.getX(), e.getY(), MouseButton.get(e.getButton()), e.getClickCount());
            }

            repaint();
        });
    }

    public void setWindow(Optional<Window> window)
    {
        this.window = window;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        this.window.ifPresent(w -> {
            w.render((Graphics2D) g);
        });
    }
}
