package com.imer1c.gui.theme;

import com.imer1c.gui.components.CheckBox;
import com.imer1c.gui.components.Component;
import com.imer1c.gui.theme.ui.CheckboxUI;
import com.imer1c.gui.theme.ui.ComponentUI;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Theme {

    private final Map<Class<? extends Component>, Supplier<ComponentUI<?>>> UIs = new HashMap<>();

    public Theme()
    {
        initDefaultUIs();
    }

    public void initDefaultUIs()
    {
        UIs.put(CheckBox.class, CheckboxUI::new);
    }

    public ComponentUI<?> buildUI(Component component)
    {
        return this.UIs.get(component.getClass()).get();
    }
}
