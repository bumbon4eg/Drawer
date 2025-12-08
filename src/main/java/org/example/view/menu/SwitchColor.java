package org.example.view.menu;

import lombok.AllArgsConstructor;
import org.example.controller.MenuState;

import javax.swing.*;
import java.awt.*;

@AllArgsConstructor
public class SwitchColor implements AppCommand{
    private MenuState menuState;
    private boolean useDefault;
    private Color defaultColor;
    private JRadioButton rgbButton;
    @Override
    public void execute() {
        rgbButton.setSelected(!useDefault);
        Color color = useDefault
                ? defaultColor
                : JColorChooser.showDialog(null, "Выбор цвета", Color.BLACK);
        menuState.setColor(color);
    }
}
