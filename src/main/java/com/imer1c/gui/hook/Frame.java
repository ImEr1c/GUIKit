package com.imer1c.gui.hook;

import com.imer1c.gui.Window;
import com.imer1c.utils.MouseButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Optional;

public class Frame extends JFrame {

    private static Frame instance;

    private Optional<Window> window = Optional.empty();
    private int mouseX, mouseY;

    public Frame(String title)
    {
        super(title);

        if (instance != null)
        {
            throw new IllegalStateException("There can only be one frame");
        }
        setContentPane(new Panel());

        MouseAdapter l = new MouseAdapter() {};
        getContentPane().addMouseListener(l);
        addMouseWheelListener(l);
        addMouseMotionListener(l);
        addKeyListener(new KeyAdapter() {});

        instance = this;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setWindow(new Window(800, 600));
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

            getContentPane().repaint();
        });
    }

    @Override
    protected void processMouseMotionEvent(MouseEvent e)
    {
        this.window.ifPresent(w -> {
            int id = e.getID();
            System.out.println(e.getY());
            switch (id)
            {
                case MouseEvent.MOUSE_MOVED -> w.mouseMoved(e.getX(), e.getY());
                case MouseEvent.MOUSE_DRAGGED -> w.mouseDragged(e.getX(), e.getY());
            }
        });
    }

    @Override
    protected void processMouseWheelEvent(MouseWheelEvent e)
    {
        this.window.ifPresent(w -> w.mouseScrolled(e.getScrollAmount(), e.getUnitsToScroll() > 0));
    }

    @Override
    protected void processKeyEvent(KeyEvent e)
    {
        this.window.ifPresent(w -> {
            int id = e.getID();
            switch (id)
            {
                case KeyEvent.KEY_TYPED -> w.keyTyped(e.getKeyChar());
                case KeyEvent.KEY_PRESSED -> w.keyPressed(e.getKeyCode(), e.getKeyChar());
                case KeyEvent.KEY_RELEASED -> w.keyReleased(e.getKeyCode(), e.getKeyChar());
            }
        });
    }

    @Override
    protected void processWindowEvent(WindowEvent e)
    {
        this.window.ifPresent(w -> {
            if (e.getID() == WindowEvent.WINDOW_CLOSING)
            {
                w.windowClosing();
            }
        });
    }

    @Override
    protected void processWindowFocusEvent(WindowEvent e)
    {
        this.window.ifPresent(w -> {
            int id = e.getID();
            switch (id)
            {
                case WindowEvent.WINDOW_GAINED_FOCUS -> w.windowGainFocus();
                case WindowEvent.WINDOW_LOST_FOCUS -> w.windowLostFocus();
            }
        });
    }

    @Override
    protected void processComponentEvent(ComponentEvent e)
    {
        this.window.ifPresent(w -> {
            int id = e.getID();
            Component component = e.getComponent();
            switch (id)
            {
                case ComponentEvent.COMPONENT_RESIZED -> w.windowResize(component.getWidth(), component.getHeight());
                case ComponentEvent.COMPONENT_MOVED -> w.windowMoved(component.getX(), component.getY());
            }
        });
    }

    public void setWindow(Window window)
    {
        this.window = Optional.ofNullable(window);
        ((Panel) this.getContentPane()).setWindow(Optional.ofNullable(window));
    }
}
