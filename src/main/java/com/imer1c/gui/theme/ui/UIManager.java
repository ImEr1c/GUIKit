package com.imer1c.gui.theme.ui;

import com.imer1c.gui.components.Component;
import com.imer1c.gui.theme.Theme;

public class UIManager {
    private static final Theme THEME = new Theme();

    public static ComponentUI<?> buildUI(Component component)
    {
        return THEME.buildUI(component);
    }
}
